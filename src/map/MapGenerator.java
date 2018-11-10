package map;

import game.*;
import algorithm.*;

import java.util.Random;


public class MapGenerator {
	
	private char[][] map;			//definiuje grafike terenu
	private TerrainField[][] terrainField;
	private TerrainManager terrainManager;
	private AStarNode[][] aStarNode;	//Od razu przy tworzeniu Mapy zajmiemy siê zrobieniem elementów terenu które bêda s³u¿y³y do przetwarzania przez algorytm A*
	
	private final int size;
	private final int density;
	private final int deep;
	//private final Game game;
	
	Random generator = new Random();
	
	public MapGenerator(MapSize ms) {
		size = ms.getSize();
		density = ms.getDensity();
		deep = ms.getDeep();
		//this.game = game;
		
		map = new char[size][size];
		terrainField = new TerrainField[size][size];
		aStarNode = new AStarNode[size][size];
		terrainManager = new TerrainManager();
		for(int i=0; i<size; ++i) {
			for(int j=0; j<size; ++j) {
				map[i][j] = 'x';
			}
		}
		generate();
		
		renderTerrainFields();
		gameInicjalization();
		
		aStarNodeInicjalization();
		
		//printMap();
	}
	public void printMap() {
		for(int i=0; i<size; ++i) {
			for(int j=0; j<size; ++j) {
				System.out.print(terrainField[i][j].getType() + " ");
			}
			System.out.println();
		}
	}
	private void generate() {
		
		//Woda
		for(int i=0; i<density; ++i) {layerGen(generator.nextInt(size), generator.nextInt(size), deep, 2, '~');}
		
		//Góry
		for(int i=0; i<density; ++i) {layerGen(generator.nextInt(size), generator.nextInt(size), deep, 3, 'A');}
		
		//Surowce
		for(int i=0; i<density*2; ++i) {layerGen(generator.nextInt(size), generator.nextInt(size), 3, 2, 'y');}
	}
	private void layerGen(int y, int x, int deep, int probability, char type) {
		try {
			if(map[y][x] == 'x') map[y][x] = type;
			else if(map[x][y] == type) deep = deep/2;
			else return;
			
			if(deep == 0) return;
			else --deep;
			
			if(generator.nextInt(probability) == 0)
				layerGen(y+1, x, deep, probability, type);
			if(generator.nextInt(probability) == 0)
				layerGen(y, x+1, deep, probability, type);
			if(generator.nextInt(probability) == 0)
				layerGen(y-1, x, deep, probability, type);
			if(generator.nextInt(probability) == 0)
				layerGen(y, x-1, deep, probability, type);	
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			return;
		}
	}
/*
 * x - domyœlny teren stepowy
 * ~ - woda
 * A - Góry
 * y - sórowce

 */
	private void renderTerrainFields() {	//Tutaj przepisujemy nasz¹ tablicê charów na tablicê struktur tereny
		for(int i=0; i<map.length; ++i)
			for(int j=0; j<map.length; ++j) {				
				switch(map[i][j]) {
				case 'x': terrainField[i][j] = terrainManager.plainField(i, j);
					break;
				case '~': terrainField[i][j] = terrainManager.waterField(i, j);
					break;
				case 'A': terrainField[i][j] = terrainManager.mountainField(i, j);
					break;
				case 'y': terrainField[i][j] = terrainManager.resourceField(i, j);
					break;		
				}
				
				aStarNode[i][j] = new AStarNode(i, j);
			}
	}
	private void aStarNodeInicjalization() {
		Game.gameManager.setAStarNode(aStarNode);
	}
	private void gameInicjalization() {
		Game.gameManager.setTerrainField(terrainField);
	}
	public char[][] getMap() {
		return map;
	}
	public int getSize() {
		return size;
	}
	public static enum MapSize { 
		EXTRA_SMALL(30, 2, 30),
		SMALL(50, 3, 40),
		MEDIUM(100, 5, 50),
		LARGE(200, 7, 55),
		EXTRA_LARGE(400, 12, 60);
		
		private int size;
		private int density;
		private int deep;
		
		MapSize(int size, int density, int deep) {
			this.size = size;
			this.density = density;
			this.deep = deep;
		}
		public int getSize() {return size;}
		public int getDensity() {return density;}
		public int getDeep() {return deep;}
	}
	
	// Funkcja do testowania 
	
	public static void main(String[] args) {
		MapGenerator map = new MapGenerator(MapGenerator.MapSize.SMALL);
		map.printMap();		
	}

}
