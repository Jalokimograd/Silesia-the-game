package unit;

import game.*;

import java.awt.image.BufferedImage;

import game.Game;

public class Building extends Unit {
	
	public Building(Profil profil, int x, int y, int direction, int health, int armor, int size, int attack, int viewRange, int attackRange) {
		super(profil, x, y, direction, health, armor, size, attack, viewRange, attackRange);
	}
	
	protected void destroy() {
		
		profil.buildings.remove(this);
		if(profil.buildingSelected == this) profil.buildingSelected = null;		
	}
	
	
	
	
	/**
	 * Metoda wywo�ywana przy ka�dym przelocie p�tli zegara,
	 * za��dza wszystkim czym zajmuje si� dany budynek mp: wydobycie,
	 * produkcja itp
	 */
	public void buildingRun() {		
	}
	public void order(UnitManager.UnitCost order) {}
	
	
}
