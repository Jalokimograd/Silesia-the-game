package unit;

import game.*;

public class UnitManager {

	//Unit(int x, int y, int direction, int health, int armor, int size, int attack, int viewRange, int attackRange, double velocity)
	public static void createTank(int x, int y, Profil profil) {profil.vehicles.add(new Tank(profil, x, y, 1, 200, 10, 1, 50, 15, 10, 1.3));}
	
	public static void createFactory(int x, int y, Profil profil) {profil.buildings.add(new Factory(profil, x, y, 1, 2000, 30, 3, 0, 20, 0));}
	public static void createLaboratory(int x, int y, Profil profil) {profil.buildings.add(new Laboratory(profil, x, y, 1, 1000, 20, 2, 0, 20, 0));}
	public static void createMine(int x, int y, Profil profil) {profil.buildings.add(new Mine(profil, x, y, 1, 500, 5, 4, 0, 10, 0));}
	public static void createPump(int x, int y, Profil profil) {profil.buildings.add(new Pump(profil, x, y, 1, 500, 5, 4, 0, 10, 0));}
	public static void createOil(int x, int y, Profil profil) {profil.buildings.add(new Oil(profil, x, y, 1, 200, 5, 4, 0, 10, 0));}
	public static void createGenerator(int x, int y, Profil profil) {profil.buildings.add(new Generator(profil, x, y, 1, 500, 5, 4, 0, 10, 0));}
	
	
	public static boolean ifEnoughtCost(Profil profil, UnitManager.UnitCost u) {
		if(profil.getExtractSupply() >= u.getExtractCost() &&
		   profil.getOilSupply() >= u.getOilCost() &&
		   profil.getCoalSupply() >= u.getCoalCost() &&
		   profil.getWaterSupply() >= u.getWaterCost() &&
		   profil.getEnergyIncrease() >= u.getEnergyCost()) {
			
		   profil.setExtractSupply(-u.getExtractCost());
		   profil.setOilSupply(-u.getOilCost());
		   profil.setCoalSupply(-u.getCoalCost());
		   profil.setWaterSupply(-u.getWaterCost());
		   profil.setEnergyIncrease(-u.getEnergyCost());
		   return true;
		}
		else return false;
	}
	//	Enum zawieraj¹cy koszty ró¿nych obiektów
	public static enum UnitCost { 
		TANK(50, 0, 100, 5, 0, 5, "Czo³g"),
		ZEPPELIN(200, 200, 0, 5, 0, 10, "Sterowiec"),
		
		FACTORY(200, 200, 0, 5, 0, 0, "Fabryka"),
		LABORATORY(200, 200, 0, 5, 0, 0, "Laboratorium"),
		MINE(50, 0, 0, 10, 10, 0, "Kopalnia"),
		GENERATOR(50, 0, 200, 10, 0, 0, "Elektrownia"),
		OIL(100, 0, 100, 10, 10, 0, "Odwiert"),
		PUMP(50, 0, 100, 0, 10, 0, "Pompa wodna"),
		
		NON(0, 0, 0, 0, 0, 0, " ");
		
		private int extractCost;
		private int oilCost;
		private int coalCost;
		
		private int waterCost;
		private int energyCost;
		
		private int timeCost;
		private String name;
		
		UnitCost(int extractCost, int oilCost, int coalCost, int waterCost, int energyCost, int timeCost, String name) {
			this.extractCost = extractCost;
			this.oilCost = oilCost;
			this.coalCost = coalCost;
			
			this.waterCost = waterCost;
			this.energyCost = energyCost;
			
			this.timeCost = timeCost;
			this.name = name;
			
		}
		public int getExtractCost() {return extractCost;}
		public int getOilCost() {return oilCost;}
		public int getCoalCost() {return coalCost;}
		
		public int getWaterCost() {return waterCost;}
		public int getEnergyCost() {return energyCost;}
		
		public int getTimeCost() {return timeCost;}
		public String getName() {return name;}
	}
}