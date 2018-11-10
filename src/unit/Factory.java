package unit;

import game.*;

public class Factory extends Building {
	
	private UnitManager.UnitCost produce = UnitManager.UnitCost.NON;
	private int time;
	private int resource = 5;
	
	public Factory(Profil profil, int x, int y, int direction, int health, int armor, int size, int attack, int viewRange, int attackRange) {
		super(profil, x, y, direction, health, armor, size, attack, viewRange, attackRange);
		
		
		for(int i=0; i<3; ++i)
			for(int j=0; j<4; ++j) {
				Game.gameManager.getTerrainField()[x+i][y+j].setOccupied(true);
			}
		
		profil.setExtractIncrease(resource);

		
		unitImage = Game.gameManager.getUnitGraphic().getFactoryImage();
		selectedImage = Game.gameManager.getUnitGraphic().getSelect3Image();
		healthImage = Game.gameManager.getUnitGraphic().gethealth1Image();
	}
	
	public void buildingRun() {
		profil.setExtractSupply(resource);
		
		if(produce != UnitManager.UnitCost.NON) {
			if(time > 0) --time;
			else {
				produce = UnitManager.UnitCost.NON;
				System.out.println(" created tank");
				UnitManager.createTank(positionX/32, (positionY/32)+3, profil);
			}
		}
	}
	
	public void order(UnitManager.UnitCost order) {
		if((produce == UnitManager.UnitCost.NON) && UnitManager.ifEnoughtCost(profil, order)) {
			produce = order;
			time = produce.getTimeCost();
		}		
	}
	
	protected void destroy() {
		for(int i=0; i<3; ++i)
			for(int j=0; j<4; ++j) {
				Game.gameManager.getTerrainField()[(positionX/32)+i][(positionY/32)+j].setOccupied(false);
			}
		profil.setExtractIncrease(-resource);
		super.destroy();
	}
}
