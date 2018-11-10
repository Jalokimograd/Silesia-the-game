package map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * TerrainMenager s�u�y do szybkiego i skutecznego tworzenia TerrainField�w.
 * Przede wszystkim chodzi�o tutaj o to by mo�na by�o szybko i bez k�opotu
 * doda� nowe typy terenu.
 */

public class TerrainManager {
	
	private BufferedImage plainImage;
	private BufferedImage waterImage;
	private BufferedImage mountainImage;
	private BufferedImage resourceImage;
	
	public TerrainManager() {
		try {
			this.plainImage = ImageIO.read(new File("graphics/terrain/plain.png"));
			System.out.println("wczytanie grafiki r�wnin");
		} catch (IOException ex) {
			System.out.println("b��d przy wczytaniu grafiki r�wnin");
			System.exit(0);}
		
		try {
			this.waterImage = ImageIO.read(new File("graphics/terrain/water.png"));
			System.out.println("wczytanie grafiki wody");
		} catch (IOException ex) {
			System.out.println("b��d przy wczytaniu grafiki wody");
			System.exit(0);}
		
		try {
			this.mountainImage = ImageIO.read(new File("graphics/terrain/mountain.png"));
			System.out.println("wczytanie grafiki g�r");
		} catch (IOException ex) {
			System.out.println("b��d przy wczytaniu grafiki g�r");
			System.exit(0);}
		
		try {
			this.resourceImage = ImageIO.read(new File("graphics/terrain/resource.png"));
			System.out.println("wczytanie grafiki surowc�w");
		} catch (IOException ex) {
			System.out.println("b��d przy wczytaniu grafiki surowc�w");
			System.exit(0);}
	}
	
	
	public TerrainField plainField(int x, int y) {
		return new TerrainField(TerrainManager.MapType.PLAIN, x, y, plainImage);
	}
	public TerrainField waterField(int x, int y) {
		return new TerrainField(TerrainManager.MapType.WATER, x, y, waterImage);
	}
	public TerrainField mountainField(int x, int y) {
		return new TerrainField(TerrainManager.MapType.MOUNTAIN, x, y, mountainImage);
	}
	public TerrainField resourceField(int x, int y) {
		return new TerrainField(TerrainManager.MapType.RESOURCE, x, y, resourceImage);
	}
	
	public static enum MapType { 
		PLAIN('x', 0, 1.0),
		WATER('~', 0, 1.0),
		MOUNTAIN('A', 0, 0.3),
		RESOURCE('y', 5000, 0.8);
		
		private char typeName;
		private int resource;
		private double velocityMod;
		
		MapType(char typeName, int resource, double velocityMod) {
			this.resource = resource;
			this.velocityMod = velocityMod;
			this.typeName = typeName;
		}
		public char getTypeName() {return typeName;}
		public int getResource() {return resource;}
		public double getVelocityMod() {return velocityMod;}
	}
}
