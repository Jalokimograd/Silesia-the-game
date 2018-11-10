package unit;

import game.*;

public class Laboratory extends Building {
	
	private boolean research = false;
	private int researxhTime = 30;
	
	public Laboratory(Profil profil, int x, int y, int direction, int health, int armor, int size, int attack, int viewRange, int attackRange) {
		super(profil, x, y, direction, health, armor, size, attack, viewRange, attackRange);
		
		for(int i=0; i<2; ++i)
			for(int j=0; j<3; ++j) {
				Game.gameManager.getTerrainField()[x+i][y+j].setOccupied(true);
			}
		
		unitImage = Game.gameManager.getUnitGraphic().getLaboratoryImage();
		selectedImage = Game.gameManager.getUnitGraphic().getSelect2Image();
		healthImage = Game.gameManager.getUnitGraphic().gethealth1Image();
	}
	
	protected void destroy() {
		for(int i=0; i<2; ++i)
			for(int j=0; j<3; ++j) {
				Game.gameManager.getTerrainField()[(positionX/32)+i][(positionY/32)+j].setOccupied(false);
			}
		
		super.destroy();
	}
	
	public void buildingRun() {
		
		if(research == true) {
			if(researxhTime > 0) --researxhTime;
		}
	}
	
	public void order(UnitManager.UnitCost order) {
		if(profil.getExtractSupply() >= 2000 &&
		   profil.getOilSupply() >= 2000 &&
		   profil.getCoalSupply() >= 2000 &&
		   profil.getWaterSupply() >= 2000 &&
		   profil.getEnergyIncrease() >= 2000) {
			
		   profil.setExtractSupply(-2000);
		   profil.setOilSupply(-2000);
		   profil.setCoalSupply(-2000);
		   profil.setWaterSupply(-2000);
		   profil.setEnergyIncrease(-2000);
		}
	}
}
