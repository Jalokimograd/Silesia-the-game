package unit;

import game.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tank extends Vehicle {
	
	public Tank(Profil profil, int x, int y, int direction, int health, int armor, int size, int attack, int viewRange, int attackRange, double velocity) {
		super(profil, x, y, direction, health, armor, size, attack, viewRange, attackRange, velocity);
		
		
		unitImage = Game.gameManager.getUnitGraphic().getTankImage();
		selectedImage = Game.gameManager.getUnitGraphic().getSelect0Image();
		healthImage = Game.gameManager.getUnitGraphic().gethealth1Image();
	}
}
