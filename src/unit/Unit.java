package unit;

import game.*;
import java.awt.image.BufferedImage;
import java.util.*;
/*
 * Jest to abstrakcyjna nadklasa okre�laj�ca podstawowe parametry i metody ka�dego istotnego 
 * tworu t.j: pojazdy i budynki gracza, drzewa, kamienie, jednostki przeciwnika itp. 
 * generalnie wszystko co mo�na zniszczy�
 */

import game.Game;

public abstract class Unit {
	protected Profil profil;		//Wskazanie na w�a�ciciela
	
	protected int positionX;		//Pozycja naszego obiektu (Je�eli obiekt jest wi�kszy od 1x1 to oznacza to lewy g�ry r�g
	protected int positionY;
	protected int direction;
	
	protected int health;			//Wytrzyma�o��
	protected int maxHealth;
	protected int healthLevel;
	protected int armor;			//Pancerz - zawsze odejmowany jest od obra�e�
	protected int size;				//Rozmiar - budynki zwylke b�d� wi�ksze od 1
	protected int attack;
	protected int viewRange;
	protected int attackRange;
	
	protected BufferedImage[] unitImage = new BufferedImage[8];
	protected BufferedImage actualImage;
	
	protected BufferedImage selectedImage;
	protected BufferedImage[] healthImage = new BufferedImage[8];
	protected BufferedImage actualHealthImage;
	
	public Unit() {
		positionX = 0;
		positionY = 0;
		direction = '2';
		health = 0;
		armor = 0;
		size = 0;
		attack = 0;
		viewRange = 0;
	}
	public Unit(Profil profil, int x, int y, int direction, int health, int armor, int size, int attack, int viewRange, int attackRange) {
		this.profil = profil;
		this.positionX = x*32;
		this.positionY = y*32;
		this.direction = direction;
		this.health = health;
		this.maxHealth = health;
		this.healthLevel = (health)/7;
		this.armor = armor;
		this.size = size;
		this.attack = attack;
		this.viewRange = viewRange;
		this.attackRange = attackRange;
	}	
	public void setDamage(int damage) {
		if(damage>armor) {
			health = health - (damage - armor);
		}
		else health -= 1;	//Uzna�em �e nawet najmocniejszy pancerz mo�na skruszy�
		if(health<=0) {health=0; destroy();}	
	}	
	public int getHealth() {
		return health;
	}	
	public int getPositionX() {
		return positionX;
	}	
	public int getPositionY() {
		return positionY;
	}
	public int getDirection() {
		return direction;
	}
	public int getSize() {
		return size;
	}	
	public int getArmor() {
		return armor;
	}
	public void attack(Unit other) {
		int distX = other.getPositionX() - positionX;
		int distY = other.getPositionY() - positionY;
		int range = (int) Math.sqrt(distX * distX + distY * distY);
		if(range <= attackRange) {
			
			//Najpierw ustawiamy si� w kierunku wroga
			if((distX*2) > range) {
				if((distY*2) > range)
					direction = 2;
				else if((-distY*2) > range)
					direction = 4;
				else direction = 3;
			}
			if((-distX*2) > range) {
				if((distY*2) > range)
					direction = 0;
				else if((-distY*2) > range)
					direction = 6;
				else direction = 7;
			}
			else if(distY > 0) direction = 1;
			else direction = 5;
				
			//Teraz atakujemy
			other.setDamage(attack);
		}
	}
	protected void destroy() {
	}
	private void imageActualization() {
		actualImage = unitImage[direction];
	}
	private void imageHealthActualization() {
		actualHealthImage = healthImage[health/healthLevel];
	}
	
	public BufferedImage getImage() {
		imageActualization();
		return actualImage;
	}
	public BufferedImage getHealthImage() {
		imageHealthActualization();
		return actualHealthImage;
	}
	public BufferedImage getSelectedImage() {
		return selectedImage;
	}
}
