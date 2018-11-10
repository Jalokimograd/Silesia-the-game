package io;

import game.*;
import panel.*;

import java.awt.event.*;

public class InputActionNorthPanel implements KeyListener, MouseListener, MouseMotionListener {

	private Profil profil;
	private RunGame run;
	
	public InputActionNorthPanel(Profil profil, RunGame run) {
		this.profil = profil;
		this.run = run;
		
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
	public void mouseClicked(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	//======================================================= MouseMotionListener =================================
	@Override
	public void mouseDragged(MouseEvent e) {	
	}
	@Override
	public void mouseMoved(MouseEvent e) {	
		System.out.println("North   X: " + e.getX() + " Y: " + e.getY());
	}
}
