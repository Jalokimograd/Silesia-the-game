package game;

import unit.*;

import java.util.ArrayList;

/**
 * 
 * @author lenovo
 *
 */
public class Profil {
	private int viewX;
	private int viewY;
	
	public int mouseShift = 0;
	public int mouseX;
	public int mouseY;
	public int mouseStartX;
	public int mouseStartY;
	public boolean isMouseClicked = false;

	
	private int maxViewX;
	private int maxViewY;
	
	private final String name;
	private boolean visable;	//Okreœla czy gracz widzi dany teren

	
	private int extractSupply = 3000;
	private int coalSupply = 4000;
	private int waterSupply = 2000;
	private int oilSupply = 2000;
	
	private int energyIncrease = 200;	
	private int coalIncrease = 0;
	private int extractIncrease = 0;
	private int waterIncrease = 0;
	private int oilIncrease = 0;
	
	public java.util.List<Building> buildings = new ArrayList<>();
	public java.util.List<Vehicle> vehicles = new ArrayList<>();
	
	public Building buildingSelected;
	public java.util.List<Vehicle> vehiclesSelected = new ArrayList<>();
	
	public UnitManager.UnitCost building = UnitManager.UnitCost.NON;
	
	public Profil(String name, int x, int y) {
		this.name = name;
		this.viewX = x;
		this.viewY = y;
	}
	public void actualizationProfil() {
		switch(mouseShift) {
		case 1: incrementViewY(-16);
			break;
		case 2: incrementViewY(-16); incrementViewX(16);
			break;
		case 3: incrementViewX(16);
			break;
		case 4: incrementViewY(16); incrementViewX(16);
			break;
		case 5: incrementViewY(16); 
			break;
		case 6: incrementViewY(16); incrementViewX(-16);
			break;
		case 7: incrementViewX(-16);
			break;
		case 8: incrementViewY(-16); incrementViewX(-16);
		}
		
		//System.out.println(mouseShift);

	}
	public void setMaxBound(int frameWidth, int frameHeight, int mapSize) {
		maxViewX = mapSize - frameWidth;
		maxViewY = mapSize - frameHeight; 
		
		System.out.println(mapSize + " - " + frameWidth + " = " + maxViewX);
		System.out.println(mapSize + " - " + frameHeight + " = " + maxViewY);
	}
	public void addBuilding(Building build) {
		buildings.add(build);
	}
	public void addVehicle(Vehicle vc) {
		vehicles.add(vc);
	}
	public int getViewX() {
		return viewX;
	}
	public int getViewY() {
		return viewY;
	}
	public void incrementViewX(int x) {
		if(viewX+x<0) viewX=0;
		else if(viewX+x>maxViewX) viewX=maxViewX;
		else viewX += x;
		//System.out.println(viewX + " : " + maxViewX);
	} 
	public void incrementViewY(int y) {
		if(viewY+y<0) viewY=0;
		else if(viewY+y>maxViewY) viewY=maxViewY;
		else viewY += y;
		//System.out.println(viewY + " : " + maxViewY);
	}
	public int getCoalSupply() {return coalSupply;}
	public int getOilSupply() {return oilSupply;}
	public int getExtractSupply() {	return extractSupply;}
	public int getWaterSupply() {return waterSupply;}

	public int getEnergyIncrease() {return energyIncrease;}
	public int getCoalIncrease() {return coalIncrease;}
	public int getOilIncrease() {return oilIncrease;}
	public int getExtractIncrease() {return extractIncrease;}
	public int getWaterIncrease() {return waterIncrease;}
	
	public void setCoalSupply(int x) {coalSupply += x;}
	public void setOilSupply(int x) {oilSupply += x;}
	public void setExtractSupply(int x) {extractSupply += x;}
	public void setWaterSupply(int x) {waterSupply += x;}

	public void setCoalIncrease(int x) {coalIncrease += x;}
	public void setOilIncrease(int x) {oilIncrease += x;}
	public void setExtractIncrease(int x) {extractIncrease += x;}
	public void setWaterIncrease(int x) {waterIncrease += x;}
	public void setEnergyIncrease(int x) {energyIncrease += x;}
}
