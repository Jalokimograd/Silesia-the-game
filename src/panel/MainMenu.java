package panel;

import game.*;
import map.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;

public class MainMenu extends JPanel {
	
	private MainFrame mainFrame;
	private MenuPanel menuPanel;
	private OptionsPanel optionsPanel;
	private GamePanel gamePanel;
	private MainMenu self;
	
	private BufferedImage background;
	private int panelHeight;
	private int panelWidth;
	
	//=====================================================================	KONSTRUKTOR ========================================
	public MainMenu(MainFrame mainFrame) {
		
		setLayout(null);
		setVisible(false);
		this.self = this;	//Wiem, wiem, ale nawet je¿eli jest wbudowane podejœcie to to zapewne jest równie proste i szybkie
		this.mainFrame = mainFrame;
		panelWidth = mainFrame.getWidth();
		panelHeight = mainFrame.getHeight();
		setSize(panelWidth, panelHeight);
		
		menuPanel = new MenuPanel();
		optionsPanel = new OptionsPanel();
		gamePanel = new GamePanel();
		add(menuPanel);
		add(optionsPanel);
		add(gamePanel);
		
		try {
			this.background = ImageIO.read(new File("graphics/background/factory" + mainFrame.getFrameSize().getSize() + ".jpg"));
			System.out.println("graphics/background/factory" + mainFrame.getFrameSize().getSize() + ".jpg");
		} catch (IOException ex) {System.out.println("b³¹d przy wczytaniu grafiki t³a menu"); ex.printStackTrace();}
	}
	//=====================================================================	PAINT COMPONENT	=====================================
	@Override
	public void paintComponent(Graphics g) {
		
		panelWidth = mainFrame.getWidth();
		panelHeight = mainFrame.getHeight();
		setSize(panelWidth, panelHeight);
		
		g.drawImage(background, 0, 0, null);
		System.out.println("wyœwietlanm t³o");	
	}
	//=====================================================================	REFRESH	=============================================
	//Ta metoda jest wywo³ywana tylko przy zmianie rozdzielczoœci przez metodê MainFrame.setSize()
	public void refresh() {
		try {
			this.background = ImageIO.read(new File("graphics/background/factory" + mainFrame.getFrameSize().getSize() + ".jpg"));
			System.out.println("graphics/background/factory" + mainFrame.getFrameSize().getSize() + ".jpg");
		} catch (IOException ex) {System.out.println("b³¹d przy wczytaniu grafiki t³a menu"); ex.printStackTrace();}
	}
	//============================================================= KLASA WEWNÊTRZNA WYŒWIETLAJ¥CA PANEL MENU ===================
	private class MenuPanel extends JPanel {
		private JButton bStart;
		private JButton bOpcje;
		private JButton bKoniec;
		
		public MenuPanel() {
			setLayout(null);
			setVisible(true);
			//setBackground(Color.RED);
			setBounds(panelWidth/2 - 140, 50, 280, 300);
			
			bStart = new JButton("START");
			add(bStart);
			bStart.addActionListener(event -> {this.setVisible(false); self.repaint(); gamePanel.setVisible(true);});
			
			bOpcje = new JButton("OPCJE");
			add(bOpcje);
			bOpcje.addActionListener(event -> {this.setVisible(false); self.repaint(); optionsPanel.setVisible(true);});
			
			bKoniec = new JButton("KONIEC");
			add(bKoniec);		
			bKoniec.addActionListener(event -> System.exit(0));
		}
		@Override
		public void paintComponent(Graphics g) {
			//super.paintComponent(g);
			setBounds(panelWidth/2 - 140, 50, 280, 300);
			
			bStart.setBounds(40, 50, 200, 50);
			bOpcje.setBounds(40, 120, 200, 50);
			bKoniec.setBounds(40, 190, 200, 50);
			System.out.println("wyœwietlam panel menu");	
		}
	}
	//============================================================= KLASA WEWNÊTRZNA WYŒWIETLAJ¥CA PANEL OPCJI =================
	private class OptionsPanel extends JPanel {
		private JButton bSmall;
		private JButton bMedium;
		private JButton bLarge;
		private JButton bExtraLarge;
		private JButton bExtraLarge2;
		private JButton bMenu;
		
		public OptionsPanel() {				
			setLayout(null);
			setVisible(false);
			//setBackground(Color.RED);
			setBounds(panelWidth/2 - 140, 50, 280, 300);
			
			bSmall = new JButton("800 x 600");
			add(bSmall);
			bSmall.addActionListener(event -> mainFrame.setSize(MainFrame.FrameSize.SMALL));
			
			bMedium = new JButton("1280 x 800");
			add(bMedium);
			bMedium.addActionListener(event -> mainFrame.setSize(MainFrame.FrameSize.MEDIUM));
			
			bLarge= new JButton("1440 x 900");
			add(bLarge);
			bLarge.addActionListener(event -> mainFrame.setSize(MainFrame.FrameSize.LARGE));
			
			bExtraLarge = new JButton("1680 x 1050");
			add(bExtraLarge);
			bExtraLarge.addActionListener(event -> mainFrame.setSize(MainFrame.FrameSize.EXTRA_LARGE));
			
			bExtraLarge2 = new JButton("1920 x 1080");
			add(bExtraLarge2);
			bExtraLarge2.addActionListener(event -> mainFrame.setSize(MainFrame.FrameSize.EXTRA_LARGE2));
			
			bMenu = new JButton("Menu");
			add(bMenu);
			bMenu.addActionListener(event -> {this.setVisible(false); self.repaint(); menuPanel.setVisible(true);});
		}
		@Override
		public void paintComponent(Graphics g) {
			//super.paintComponent(g);
			setBounds(panelWidth/2 - 140, 50, 280, 400);
			
			bSmall.setBounds(40, 50, 200, 40);
			bMedium.setBounds(40, 100, 200, 40);
			bLarge.setBounds(40, 150, 200, 40);
			bExtraLarge.setBounds(40, 200, 200, 40);
			bExtraLarge2.setBounds(40, 250, 200, 40);
			bMenu.setBounds(40, 300, 200, 40);	
			
			System.out.println("wyœwietlanm panel opcji menu");
		}
	}
	//============================================================= KLASA WEWNÊTRZNA WYŒWIETLAJ¥CA OPCJE ROZGRYWKI =============
		private class GamePanel extends JPanel {
			private JButton bExtraSmall;
			private JButton bSmall;
			private JButton bMedium;
			private JButton bLarge;
			private JButton bExtraLarge;
			private JButton bMenu;
			
			
			public GamePanel() {				
				setLayout(null);
				setVisible(false);
				//setBackground(Color.RED);
				setBounds(panelWidth/2 - 140, 50, 280, 300);
				bExtraSmall = new JButton("Mapa: 30x30");
				add(bExtraSmall);
				bExtraSmall.addActionListener(event -> {
					new MapGenerator(MapGenerator.MapSize.EXTRA_SMALL);
					self.setVisible(false);
					mainFrame.displayRunGame();
					});
				
				
				bSmall = new JButton("Mapa: 50x50");
				add(bSmall);
				bSmall.addActionListener(event -> {
					new MapGenerator(MapGenerator.MapSize.SMALL);
					self.setVisible(false);
					mainFrame.displayRunGame();
					});
				
				
				bMedium = new JButton("Mapa: 100x100");
				add(bMedium);
				bMedium.addActionListener(event -> {
					new MapGenerator(MapGenerator.MapSize.MEDIUM);
					self.setVisible(false);
					mainFrame.displayRunGame();
					});
				
				
				bLarge= new JButton("Mapa: 200x200");
				add(bLarge);
				bLarge.addActionListener(event -> {
					new MapGenerator(MapGenerator.MapSize.LARGE);
					self.setVisible(false);
					mainFrame.displayRunGame();
					});
				
				
				bExtraLarge = new JButton("Mapa: 400x400");
				add(bExtraLarge);
				bExtraLarge.addActionListener(event -> {
					new MapGenerator(MapGenerator.MapSize.EXTRA_LARGE);
					self.setVisible(false);
					mainFrame.displayRunGame();
					});
				
				bMenu = new JButton("Menu");
				add(bMenu);
				bMenu.addActionListener(event -> {this.setVisible(false); self.repaint(); menuPanel.setVisible(true);});
			}
			@Override
			public void paintComponent(Graphics g) {
				//super.paintComponent(g);
				setBounds(panelWidth/2 - 140, 50, 280, 400);
				
				bExtraSmall.setBounds(40, 50, 200, 40);
				bSmall.setBounds(40, 100, 200, 40);
				bMedium.setBounds(40, 150, 200, 40);
				bLarge.setBounds(40, 200, 200, 40);
				bExtraLarge.setBounds(40, 250, 200, 40);	
				bMenu.setBounds(40, 300, 200, 40);	
				
				System.out.println("wyœwietlanm panel opcje gry menu");
			}
		}
}

