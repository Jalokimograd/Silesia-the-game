package unit;

import game.*;

public class Generator extends Building {
	
	private int resource = 5;
	
	public Generator(Profil profil, int x, int y, int direction, int health, int armor, int size, int attack, int viewRange, int attackRange) {
		super(profil, x, y, direction, health, armor, size, attack, viewRange, attackRange);
		
		for(int i=0; i<2; ++i)
			for(int j=0; j<2; ++j) {
				Game.gameManager.getTerrainField()[x+i][y+j].setOccupied(true);
			}
		
		profil.setEnergyIncrease(resource);
		
		unitImage = Game.gameManager.getUnitGraphic().getGeneratorImage();
		selectedImage = Game.gameManager.getUnitGraphic().getSelect1Image();
		healthImage = Game.gameManager.getUnitGraphic().gethealth1Image();
	}
	
	protected void destroy() {
		for(int i=0; i<2; ++i)
			for(int j=0; j<2; ++j) {
				Game.gameManager.getTerrainField()[(positionX/32)+i][(positionY/32)+j].setOccupied(false);
			}
		profil.setEnergyIncrease(-resource);
		super.destroy();
	}
	
	
}
