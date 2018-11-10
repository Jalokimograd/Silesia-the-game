package game;

import algorithm.AStarNode;
import map.TerrainField;

public class GameManager {
	
	private TerrainField[][] terrainField;
	private AStarNode[][] aStarNode;
	private Profil profil[] = new Profil[8];
	private unit.GraphicManager unitGraphic = new unit.GraphicManager();
	
	public GameManager(String... name) {
		if(name.length < 8) 
			for(int i=0; i<name.length; ++i) {
				profil[i] = new Profil(name[i], 0, 0);
			}
	}
	
	public void setTerrainField(TerrainField[][] t) {terrainField = t;}
	public TerrainField[][] getTerrainField() {return terrainField;}
	
	public void setAStarNode(AStarNode[][] a) {aStarNode = a;}
	public AStarNode[][] getAStarNode() {return aStarNode;}
	
	public Profil getProfil(int x) {return profil[x];}
	
	public unit.GraphicManager getUnitGraphic() {return unitGraphic;}
}
