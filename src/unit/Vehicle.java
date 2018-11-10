package unit;

import algorithm.*;
import game.*;

import java.util.*;

public class Vehicle extends Unit {
	
	private LinkedList<AStarNode> plannedMoves;
	private double velocity;
	private boolean isMoved = false;
	private boolean hasGoal = false;
	private AStarNode actualGoal;
	private double exactX;
	private double exactY;
	
	private int destinationX;
	private int destinationY;
	
	public Vehicle(Profil profil, int x, int y, int direction, int health, int armor, int size, int attack, int viewRange, int attackRange, double velocity) {
		super(profil, x, y, direction, health, armor, size, attack, viewRange, attackRange);
		this.velocity = velocity;
	}
	//	Poszukujemy œcie¿ki do celu
	private void findPath() {
		plannedMoves = AStar.findPath(Game.gameManager.getAStarNode()[positionX/32][positionY/32], Game.gameManager.getAStarNode()[destinationX][destinationY]);
		if(plannedMoves.size() != 0) { isMoved = true;
	//	for(int i=0; i<plannedMoves.size(); i++)
			//System.out.println("droga: " + plannedMoves.get(i).getX() + " : " + plannedMoves.get(i).getY());
		}
		else isMoved = false;
		
		
	}
	//	Ustawiamy kolejny blok œcie¿ki jako najbli¿szy cel
	private void setActualGoal() {
		if(hasGoal) {
			positionX = actualGoal.getX()*32; 
			positionY = actualGoal.getY()*32;
			hasGoal = false;
		}
		 
		if(plannedMoves.size() != 0) {
			if(Game.gameManager.getTerrainField()[plannedMoves.getFirst().getX()][plannedMoves.getFirst().getY()].getOccupied() == false) {
				Game.gameManager.getTerrainField()[plannedMoves.getFirst().getX()][plannedMoves.getFirst().getY()].setOccupied(true);
				Game.gameManager.getTerrainField()[positionX/32][positionY/32].setOccupied(false);
				actualGoal = plannedMoves.removeFirst();
				//System.out.println("New goal =====================================");
				exactX = positionX;
				exactY = positionY;
				
				if(actualGoal.getX() - positionX/32 > 0) {
					if(actualGoal.getY() - positionY/32 > 0) direction = 4;
					else if(actualGoal.getY() - positionY/32 < 0) direction = 2;
					else direction = 3;
				}
				else if(actualGoal.getX() - positionX/32 < 0) {
					if(actualGoal.getY() - positionY/32 > 0) direction = 6;
					else if(actualGoal.getY() - positionY/32 < 0) direction = 0;
					else direction = 7;
				}
				else if(actualGoal.getY() - positionY/32 > 0) direction = 5;
				else direction = 1;
				
				hasGoal = true;
			} 
			else findPath();	
		}
		else isMoved = false;
		
	}
	public void move() {
		//System.out.println("move = " + isMoved);
		//System.out.println("goal= " + hasGoal);
		if(isMoved) {
			if(hasGoal) {
				positionX = (int) exactX;
				positionY = (int) exactY;
				//System.out.println("positionX: " + positionX);
				//System.out.println("positionY: " + positionY);
				//System.out.println("getX: " + actualGoal.getX()*32);
				//System.out.println("getY: " + actualGoal.getY()*32);
				switch(direction) {
				case 0: if(positionX <= actualGoal.getX()*32 && positionY <= actualGoal.getY()*32) setActualGoal();
						else {exactX -= velocity/1.41;
						exactY -= velocity/1.41;}
						break;
				case 1: if(positionY <= actualGoal.getY()*32) setActualGoal();
						else exactY -= velocity;
						break;
				case 2: if(positionX >= actualGoal.getX()*32 && positionY <= actualGoal.getY()*32) setActualGoal();
						else {exactX += velocity/1.41;
						exactY -= velocity/1.41;}
						break;
				case 3: if(positionX >= actualGoal.getX()*32) setActualGoal();
						else exactX += velocity;
						break;
				case 4: if(positionX >= actualGoal.getX()*32 && positionY >= actualGoal.getY()*32) setActualGoal();
						else{exactX += velocity/1.41;
						exactY += velocity/1.41; }
						break;
				case 5: if(positionY >= actualGoal.getY()*32) setActualGoal();
						else exactY += velocity;
						break;
				case 6: if(positionX <= actualGoal.getX()*32 && positionY >= actualGoal.getY()*32) setActualGoal();
						else {exactX -= velocity/1.41;
						exactY += velocity/1.41;}
						break;
				case 7: if(positionX <= actualGoal.getX()*32) setActualGoal();
						else exactX -= velocity;
						break;
				}				
			}
			else setActualGoal();
		}
	}
	public void setMove(int x, int y) {
		destinationX = x;
		destinationY = y;
		findPath();
	}
	
	protected void destroy() {
		profil.vehicles.remove(this);
		profil.vehiclesSelected.remove(this);
	}
	
	
}
