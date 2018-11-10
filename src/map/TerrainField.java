package map;

import unit.*;

import java.awt.*;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class TerrainField {
	private final TerrainManager.MapType type;	//Typ terenu mp: góry, woda, œwiêty las na glebie torfowej fibrowej
	private final BufferedImage terrainImage;
	
	private final int positionX;
	private final int positionY;
	
	//private int height;		okreœla wysokoœæ, istotne dla pojazdów (rózne podwozia pozwalaj¹ pokonywaæ ró¿ne wysokoœci)
	private int resource;
	private final double velocityMod;
	private boolean occupied;	//false - wolny, true - zajêty 
	private Unit unitIndex;		//Je¿eli zajêty to wskazuje na konkretn¹ jednistkê która zajmuje
	
	public TerrainField(TerrainManager.MapType type, int x, int y, BufferedImage icon) {
		this.type = type;
		this.positionX = x;
		this.positionY = y;
		this.resource = type.getResource();
		this.velocityMod = type.getVelocityMod();
		this.occupied = false;
		this.terrainImage = icon;		
	}

	public char getType() {
		return type.getTypeName();
	}
	public int getPositionX() {
		return positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public boolean setUnit(Unit u) {
		if(occupied == true) return(false);
		unitIndex = u;
		return(true);
	}
	public Unit getUnit() {
		return unitIndex;
	}
	public boolean getOccupied() {
		return occupied;
	}
	public void setOccupied(boolean x) {
		occupied = x;
	}
	public BufferedImage getImage() {
		return terrainImage;
	}

}
