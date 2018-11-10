package unit;

import game.*;

public class Pump extends Building {
	
	private int resource = 0;
	public Pump(Profil profil, int x, int y, int direction, int health, int armor, int size, int attack, int viewRange, int attackRange) {
		super(profil, x, y, direction, health, armor, size, attack, viewRange, attackRange);
		
		for(int i=0; i<2; ++i)
			for(int j=0; j<2; ++j) {
				Game.gameManager.getTerrainField()[x+i][y+j].setOccupied(true);
				if(Game.gameManager.getTerrainField()[x+i][y+j].getType() == '~') resource++;
			}
		
		profil.setWaterIncrease(resource);
		
		unitImage = Game.gameManager.getUnitGraphic().getPumpImage();
		selectedImage = Game.gameManager.getUnitGraphic().getSelect1Image();
		healthImage = Game.gameManager.getUnitGraphic().gethealth1Image();
	}
	
	public void buildingRun() {
		profil.setWaterSupply(resource);
	}
	
	protected void destroy() {
		for(int i=0; i<2; ++i)
			for(int j=0; j<2; ++j) {
				Game.gameManager.getTerrainField()[(positionX/32)+i][(positionY/32)+j].setOccupied(false);
			}
		profil.setWaterIncrease(-resource);
		super.destroy();
	}
}
