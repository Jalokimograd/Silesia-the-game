package game;

import panel.*;
import map.*;

import java.awt.*;
import javax.swing.*;

import map.MapGenerator;


/*
 * Jesto to klasa pe³ni¹ca rolê g³ównego okna
 * Okreœla nam rozmiar okna (który mo¿emy zmieniaæ w opcjach)
 * To ta ramka przyjmuje jako Panele inne mo¿liwoœci wyœwietlanie
 * takie jak:
 * -Menu
 * -Opcje
 * -Gra
 * i inne...
 */

public class MainFrame extends JFrame {	
	private MainMenu mainMenu;
	private RunGame runGame;
	
	private FrameSize size;	//jest to informacja dal innych paneli odnoœnie wielkoœci ramki
	
	public MainFrame() {
			
		size = FrameSize.MEDIUM;
		setSize(size.getWidth(), size.getHeight());
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setTitle("Sztuka persfazji");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	
		mainMenu = new MainMenu(this);
		
		add(mainMenu);
		//mainMenu.setVisible(true);
		//mainMenu.setVisible(false);
		//displayRunGame();
		displayMainMenu();
		
		
	}
	
	public void displayMainMenu() {	
		if(runGame != null) deleteRunGame();
		mainMenu.setVisible(true);
	}
	public void displayRunGame() {	
		if(runGame == null) createRunGame();
		runGame.setVisible(true);
	}
	private void createRunGame() {
		runGame = new RunGame(this);
		add(runGame);
	}
	private void deleteRunGame() {
		remove(runGame); 
		runGame = null;
	}
	
	//Domyœlne rozmiary naszego okna
	public static enum FrameSize {
		SMALL(800, 600, 1),
		MEDIUM(1280, 800, 2),
		LARGE(1440, 900, 3),
		EXTRA_LARGE(1680, 1050, 4),
		EXTRA_LARGE2(1920, 1080, 5),
		MAX(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height, 6);
		
		private int width;
		private int height;
		private int size;
		
		FrameSize(int width, int height, int size) {
			this.width = width;
			this.height = height;
			this.size = size;
		}
		public int getWidth() {return width;}
		public int getHeight() {return height;}
		public int getSize() {return size;}
	}
	public void setSize(MainFrame.FrameSize size) {
		this.size = size;
		//if(size.getSize() == 5) setUndecorated(true);
		//else setUndecorated(false);
		
		setSize(size.getWidth(), size.getHeight());
		mainMenu.refresh();
		repaint();
	}
	public MainFrame.FrameSize getFrameSize() {
		return size;
	}
}
