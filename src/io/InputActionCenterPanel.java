package io;

import game.*;
import panel.*;
import unit.*;

import java.awt.event.*;

public class InputActionCenterPanel implements KeyListener, MouseListener, MouseMotionListener {
	
	private Profil profil;
	private RunGame panel;
	
	
	public InputActionCenterPanel(Profil profil, RunGame panel) {
		this.profil = profil;
		this.panel = panel;
		
		System.out.println(profil);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) {
			System.out.println("LEWO");
			profil.incrementViewX(-16);
			panel.repaintRunPanel();
			//profil.IncrementPosX(10);
		}
		
		if (i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) {
			System.out.println("PRAWO");
			profil.incrementViewX(16);
			panel.repaintRunPanel();
			//profil.IncrementPosX(-10);
		}
		
		if (i == KeyEvent.VK_W || i == KeyEvent.VK_UP) {
			System.out.println("GÓRA");
			profil.incrementViewY(-16);
			panel.repaintRunPanel();
			//profil.IncrementPosY(-10);
		}
		
		if (i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) {
			System.out.println("DÓ£");
			profil.incrementViewY(16);
			panel.repaintRunPanel();
			//profil.IncrementPosY(10);
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}
	//================================================== MYSZ =============================================
	@Override
	public void mouseClicked(MouseEvent e) {
		
		System.out.println("clicked " + e.getButton());
			
		if(profil.building != UnitManager.UnitCost.NON) {
			int x = (profil.mouseX + profil.getViewX())/32;
			int y = (profil.mouseY + profil.getViewY())/32;
			
			if(profil.building == UnitManager.UnitCost.FACTORY) {
				if(x < (Game.gameManager.getTerrainField().length-2) && y < (Game.gameManager.getTerrainField().length-2) && Game.gameManager.getTerrainField()[x][y].getOccupied() == false && Game.gameManager.getTerrainField()[x+1][y].getOccupied() == false
						 && Game.gameManager.getTerrainField()[x+2][y].getOccupied() == false && Game.gameManager.getTerrainField()[x][y+1].getOccupied() == false && Game.gameManager.getTerrainField()[x+1][y+1].getOccupied() == false
						 && Game.gameManager.getTerrainField()[x+2][y+1].getOccupied() == false && Game.gameManager.getTerrainField()[x][y+2].getOccupied() == false && Game.gameManager.getTerrainField()[x+1][y+2].getOccupied() == false
						 && Game.gameManager.getTerrainField()[x+2][y+2].getOccupied() == false) {
					if(UnitManager.ifEnoughtCost(profil, UnitManager.UnitCost.FACTORY)) {
						UnitManager.createFactory((e.getX() + profil.getViewX())/32, (e.getY() + profil.getViewY())/32, profil);
					}
						
				}
							
			}
			else if(profil.building == UnitManager.UnitCost.LABORATORY) {
				if(x < (Game.gameManager.getTerrainField().length-2) && y < (Game.gameManager.getTerrainField().length-2) && Game.gameManager.getTerrainField()[x][y].getOccupied() == false && Game.gameManager.getTerrainField()[x+1][y].getOccupied() == false
						 && Game.gameManager.getTerrainField()[x][y+1].getOccupied() == false && Game.gameManager.getTerrainField()[x+1][y+1].getOccupied() == false
						 && Game.gameManager.getTerrainField()[x][y+2].getOccupied() == false && Game.gameManager.getTerrainField()[x+1][y+2].getOccupied() == false) {
					if(UnitManager.ifEnoughtCost(profil, UnitManager.UnitCost.LABORATORY)) {
						UnitManager.createLaboratory((e.getX() + profil.getViewX())/32, (e.getY() + profil.getViewY())/32, profil);
					}
					
				}
			}
			else if(x < (Game.gameManager.getTerrainField().length-2) && y < (Game.gameManager.getTerrainField().length-2) && Game.gameManager.getTerrainField()[x][y].getOccupied() == false && Game.gameManager.getTerrainField()[x+1][y].getOccupied() == false
					 && Game.gameManager.getTerrainField()[x][y+1].getOccupied() == false && Game.gameManager.getTerrainField()[x+1][y+1].getOccupied() == false) {
				
					if(profil.building == UnitManager.UnitCost.MINE) if(UnitManager.ifEnoughtCost(profil, UnitManager.UnitCost.MINE)) UnitManager.createMine((e.getX() + profil.getViewX())/32, (e.getY() + profil.getViewY())/32, profil);
					if(profil.building == UnitManager.UnitCost.OIL) if(UnitManager.ifEnoughtCost(profil, UnitManager.UnitCost.OIL)) UnitManager.createOil((e.getX() + profil.getViewX())/32, (e.getY() + profil.getViewY())/32, profil);
					if(profil.building == UnitManager.UnitCost.PUMP) if(UnitManager.ifEnoughtCost(profil, UnitManager.UnitCost.PUMP)) UnitManager.createPump((e.getX() + profil.getViewX())/32, (e.getY() + profil.getViewY())/32, profil);
					if(profil.building == UnitManager.UnitCost.GENERATOR) if(UnitManager.ifEnoughtCost(profil, UnitManager.UnitCost.GENERATOR)) UnitManager.createGenerator((e.getX() + profil.getViewX())/32, (e.getY() + profil.getViewY())/32, profil);
			}
			
		}
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("pressed");
		profil.isMouseClicked = true;
		profil.mouseStartX = e.getX();
		profil.mouseStartY = e.getY();
		if(e.getButton() == 3)
			for(int i=0; i<profil.vehiclesSelected.size(); ++i) {
				
				profil.vehiclesSelected.get(i).setMove((profil.mouseX + profil.getViewX())/32, (profil.mouseY + profil.getViewY())/32);
			}
		if(e.getButton() == 1) {profil.vehiclesSelected.clear(); profil.buildingSelected = null;}
			
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("released");
		if(profil.isMouseClicked == true) {
			profil.isMouseClicked = false;
			int lessX;
			int lessY;
			int biggerX;
			int biggerY;
			
			if(profil.mouseX > profil.mouseStartX) {biggerX = profil.mouseX; lessX = profil.mouseStartX;}
			else {lessX = profil.mouseX; biggerX = profil.mouseStartX;}
			
			if(profil.mouseY > profil.mouseStartY) {biggerY = profil.mouseY; lessY = profil.mouseStartY;}
			else {lessY = profil.mouseY; biggerY = profil.mouseStartY;}
			
			//	Sprawdzamy wszystkie jednostki gracza i je¿eli któraœ ma wspó³¿êdne w obrêbie ramki to dodajemy j¹ do zaznaczonych
			for(int i=0; i<profil.vehicles.size(); ++i) {			
				if((profil.vehicles.get(i).getPositionX() - profil.getViewX()) > lessX && (profil.vehicles.get(i).getPositionX() - profil.getViewX()) < biggerX &&
			       (profil.vehicles.get(i).getPositionY() - profil.getViewY()) > lessY && (profil.vehicles.get(i).getPositionY() - profil.getViewY()) < biggerY	)
					profil.vehiclesSelected.add(profil.vehicles.get(i));
			}
			if(profil.vehiclesSelected.size() == 0) {
				for(int i=0; i<profil.buildings.size(); ++i) {			
					if((profil.buildings.get(i).getPositionX() - profil.getViewX()) > lessX && (profil.buildings.get(i).getPositionX() - profil.getViewX()) < biggerX &&
				       (profil.buildings.get(i).getPositionY() - profil.getViewY()) > lessY && (profil.buildings.get(i).getPositionY() - profil.getViewY()) < biggerY) {
						profil.buildingSelected = profil.buildings.get(i);
						break;
					}
						
				}
			}
		}
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println("entered");
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println("exited");
		
	}
	//======================================================= MouseMotionListener =================================
	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("dragged");
	//	System.out.println("X: " + e.getX() + " Y: " + e.getY());
		profil.mouseX = e.getX();
		profil.mouseY = e.getY();
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
	//	System.out.println("X: " + e.getX() + " Y: " + e.getY());
		profil.mouseX = e.getX();
		profil.mouseY = e.getY();
		
		if(e.getX() > panel.getBounds().getMaxX()-10) profil.mouseShift = 3;
		else if(e.getX() < panel.getBounds().getMinX()+10) profil.mouseShift = 7;
		else profil.mouseShift = 0;
		
	}
}