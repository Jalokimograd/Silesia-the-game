package io;

import game.*;
import panel.*;

import java.awt.event.*;

public class InputActionSouthPanel implements KeyListener, MouseListener, MouseMotionListener {

	private Profil profil;
	private RunGame panel;
	
	public InputActionSouthPanel(Profil profil, RunGame panel) {
		this.profil = profil;
		this.panel = panel;
		
		System.out.println(profil);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) {
		}
		
		if (i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) {
		}
		
		if (i == KeyEvent.VK_W || i == KeyEvent.VK_UP) {
		}
		
		if (i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) {
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	} 
	//================================================== MYSZ =============================================
	@Override
	public void mouseClicked(MouseEvent e) {// System.out.println("Clicked");
	}
	@Override
	public void mousePressed(MouseEvent e) {// System.out.println("Pressed");
	}
	@Override
	public void mouseReleased(MouseEvent e) {//System.out.println("Release");
	}
	@Override
	public void mouseEntered(MouseEvent e) {// System.out.println("Entered");
	}
	@Override
	public void mouseExited(MouseEvent e) { //System.out.println("Exited");
	}
	//======================================================= MouseMotionListener =================================
	@Override
	public void mouseDragged(MouseEvent e) {	//System.out.println("Dragged");
	}
	@Override
	public void mouseMoved(MouseEvent e) {	
		//	Tutaj ustalamy czy kursor zbli¿y³ sie do krawêdzi ramki, aby przesun¹æ mapê
		final int RANGE = 10; // Odleg³oœæ od krawêdzi
		
		//System.out.println("South   X: " + e.getX() + " Y: " + e.getY());
		//System.out.println("South   XX: " + panel.getBounds().getMaxX() + " YY: " + panel.getBounds().getMaxY());
		if((e.getY() > panel.getBounds().getMaxY()-RANGE) && e.getX() > panel.getBounds().getMaxX()-RANGE) profil.mouseShift = 4;
		else if((e.getY() > panel.getBounds().getMaxY()-RANGE) && e.getX() < panel.getBounds().getMinX()+RANGE) profil.mouseShift = 6;
		else if((e.getY() < panel.getBounds().getMinY()+RANGE) && e.getX() > panel.getBounds().getMaxX()-RANGE) profil.mouseShift = 2;
		else if((e.getY() < panel.getBounds().getMinY()+RANGE) && e.getX() < panel.getBounds().getMinX()+RANGE) profil.mouseShift = 8;
		else if(e.getY() > panel.getBounds().getMaxY()-RANGE) profil.mouseShift = 5;
		else if(e.getY() < panel.getBounds().getMinY()+RANGE) profil.mouseShift = 1;
		else if(e.getX() > panel.getBounds().getMaxX()-RANGE) profil.mouseShift = 3;
		else if(e.getX() < panel.getBounds().getMinX()+RANGE) profil.mouseShift = 7;
		else profil.mouseShift = 0;
	}
}
