package algorithm;

import game.*;

import java.util.*;

public class AStarNode implements Comparable<AStarNode> {
	int x;
	int y;
	
	AStarNode pathParent;
	int costFromStart;
	int estimatedCostToGoal;
	
	public AStarNode(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getCost() {
		return costFromStart + estimatedCostToGoal;
	}

	public int compareTo(AStarNode other) {
		float otherValue = other.getCost();
		float thisValue = this.getCost();
		float v = thisValue - otherValue;
		return (v > 0) ? 1 : (v < 0) ? -1 : 0;
	}

	public int getCostTo(AStarNode node) {
		if (x == node.getX() || y == node.getY())
			return 10;
		else
			return 14;
	}
/**
 * Tutaj obliczamy potêcjaln¹ odleg³oœæ od celu.
 * Aktualnie jest obliczana prawie dok³adnie (zaokr¹glona do ca³oœci),
 * ale zapewne bêdê jeszcze testowa³ trywialniejsz¹ wersjê z sum¹ (|dx| + |dy|)
 */
	public int getEstimatedCost(AStarNode node) {
		int dx = this.x - node.getX();
		int dy = this.y - node.getY();
		return (int) Math.sqrt(dx * dx + dy * dy);
	}
/**
 * W szukaniu somsiadów u¿ywamy globalnej informacji o mapie która jest
 * w klasie: GAME.terrainField
 * @return
 */
	public ArrayList<AStarNode> getNeighbors() {
		ArrayList<AStarNode> list = new ArrayList<AStarNode>(8);
		int i=-1, j=-1, ii=1, jj=1;
		if(this.x == 0) i=0;
		else if(this.x == Game.gameManager.getAStarNode().length-1) ii=0;
		
		if(this.y == 0) j=0;
		else if(this.y == Game.gameManager.getAStarNode().length-1) jj=0;
		
		for(int a=i; a<=ii; ++a)
			for(int b=j; b<=jj; ++b) {
				if((a!=0 || b!=0) && Game.gameManager.getTerrainField()[x+a][y+b].getOccupied() == false && (Game.gameManager.getTerrainField()[x+a][y+b].getType() == 'x' || Game.gameManager.getTerrainField()[x+a][y+b].getType() == 'y')) list.add(Game.gameManager.getAStarNode()[x+a][y+b]);
			}			
		return list;
	}
}
