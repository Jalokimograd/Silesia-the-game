package panel;

import game.*;
import map.*;
import io.*;
import unit.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;


public class RunGame extends JPanel implements ActionListener {
	private MainFrame mainFrame;
	private InformationPanel informationPanel;
	private ResourcesPanel resourcesPanel;
	private RunPanel runPanel;
	private RunGame self;
	private Timer timer = new Timer(10, this);
	private int timeClick = 0;
	private InputActionSouthPanel io;
	private GraphicManager graphicManager = new GraphicManager();
	
	private int panelHeight;
	private int panelWidth;
	//=====================================================================	KONSTRUKTOR ========================================
	public RunGame(MainFrame mainFrame) {
		setLayout(new BorderLayout());
		setVisible(false);
		this.self = this;
		this.mainFrame = mainFrame;
		panelWidth = mainFrame.getWidth();
		panelHeight = mainFrame.getHeight();
		setSize(panelWidth, panelHeight);
		
		io = new InputActionSouthPanel(Game.gameManager.getProfil(0), this);
		addMouseListener(io);
		addMouseMotionListener(io);
		
		informationPanel = new InformationPanel();
		resourcesPanel = new ResourcesPanel();
		runPanel = new RunPanel();
		
		

		
		add(informationPanel, BorderLayout.SOUTH);
		add(resourcesPanel, BorderLayout.NORTH);
		add(runPanel, BorderLayout.CENTER);
		
		timer.start();
	}
	public void repaintRunPanel() {
		runPanel.repaint();
	}
	//przesówanie planszy po przybli¿eniu kursora do krawêdzi mapy
	private void mouseShift() {
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//System.out.println(Game.gameManager.getProfil(0).building.toString());
		
		Game.gameManager.getProfil(0).actualizationProfil();
		for(int i=0; i<Game.gameManager.getProfil(0).vehicles.size(); ++i) {
			Game.gameManager.getProfil(0).vehicles.get(i).move();
		}
		if(timeClick == 100) {
			timeClick = 0;
			for(int i=0; i<Game.gameManager.getProfil(0).buildings.size(); ++i) {
				Game.gameManager.getProfil(0).buildings.get(i).buildingRun();
			}
		}
		
		if(Game.gameManager.getProfil(0).buildingSelected instanceof Factory) {
			informationPanel.infoPanel.factoryPanel.setVisible(true);					
		}
		else informationPanel.infoPanel.factoryPanel.setVisible(false);
		
		
		this.repaint();
		++timeClick;
		
	}
	//=====================================================================	KLASA WEWNÊTRZNA PANELU DOLNEGO ========================================################
	public class InformationPanel extends JPanel {

		private OptionsPanel optionsPanel;
		private BuildingPanel buildingPanel;
		private InfoPanel infoPanel;
		
		public InformationPanel() {
			setPreferredSize(new Dimension(100, 200));
			setLayout(null);
			setBackground(Color.GRAY);
			
			optionsPanel = new OptionsPanel();
			buildingPanel = new BuildingPanel();
			infoPanel = new InfoPanel();
			
			add(optionsPanel);
			add(buildingPanel);
			add(infoPanel);
		}
		
		private class OptionsPanel extends JPanel {		
			private JButton bBuduj = new JButton("BUDUJ");
			private JButton bInformacje = new JButton("INFORMACJE");
			//private JButton bStatystyki = new JButton("STATYSTYKI");
			
			public OptionsPanel() {
				setBounds(0, 0, 146, 200);
				setLayout(null);
				setBackground(Color.BLACK);
				
				add(bBuduj); bBuduj.setBounds(9, 9, 128, 32);
				bBuduj.addActionListener(event -> {buildingPanel.setVisible(true); infoPanel.setVisible(false);});
				add(bInformacje); bInformacje.setBounds(9, 41, 128, 32);
				bInformacje.addActionListener(event -> {buildingPanel.setVisible(false); infoPanel.setVisible(true); infoPanelActualization();});
				//add(bStatystyki); bStatystyki.setBounds(9, 73, 128, 32);
			}
			private void infoPanelActualization() {
				
			}
					
		}
		private class BuildingPanel extends JPanel {
			private JButton bFactory = new JButton("Fabryka");
			private JButton bLaboratory = new JButton("Laboratorium");
			private JButton bMine = new JButton("Kopalnia");
			private JButton bOil = new JButton("Odwiert");
			private JButton bPump = new JButton("Pompa Wodna");
			private JButton bGenerator = new JButton("Elektrownia");
			private JButton bExit = new JButton("Anuluj");
			
			private ConstructionInfoPanel constructionInfoPanel = new ConstructionInfoPanel();
			
			public BuildingPanel() {
				setBounds(panelWidth - 600, 0, 600, 200);
				setLayout(null);
				setBackground(Color.BLUE);
				setVisible(true);
				
				add(constructionInfoPanel);	
				
				add(bFactory); bFactory.setBounds(9, 9, 64, 64); 
				add(bLaboratory); bLaboratory.setBounds(73, 9, 64, 64);
				add(bMine); bMine.setBounds(9, 73, 64, 64);
				add(bOil); bOil.setBounds(73, 73, 64, 64);
				add(bPump); bPump.setBounds(137, 73, 64, 64);
				add(bGenerator); bGenerator.setBounds(201, 73, 64, 64);
				add(bExit); bExit.setBounds(9, 137, 64, 64);
		
				
				bFactory.addActionListener(event -> {Game.gameManager.getProfil(0).building = UnitManager.UnitCost.FACTORY; });
				bLaboratory.addActionListener(event -> {Game.gameManager.getProfil(0).building = UnitManager.UnitCost.LABORATORY; });
				bMine.addActionListener(event -> {Game.gameManager.getProfil(0).building = UnitManager.UnitCost.MINE; });
				bOil.addActionListener(event -> {Game.gameManager.getProfil(0).building = UnitManager.UnitCost.OIL; });
				bPump.addActionListener(event -> {Game.gameManager.getProfil(0).building = UnitManager.UnitCost.PUMP; });
				bGenerator.addActionListener(event -> {Game.gameManager.getProfil(0).building = UnitManager.UnitCost.GENERATOR; });
				bExit.addActionListener(event -> {Game.gameManager.getProfil(0).building = UnitManager.UnitCost.NON; });
						
			}
			private class ConstructionInfoPanel extends JPanel {
				public ConstructionInfoPanel() {
					setBounds(300, 0, 300, 200);
					setBackground(Color.WHITE);
					setLayout(null);					
				}
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawString("KOSZT:    " + Game.gameManager.getProfil(0).building.getName(), 64, 16);
					g.drawString("WÊGIEL:   " + Game.gameManager.getProfil(0).building.getCoalCost(), 16, 46);
					g.drawString("NAFTA:    " + Game.gameManager.getProfil(0).building.getOilCost(), 16, 82);
					g.drawString("EKSTRAKT: " + Game.gameManager.getProfil(0).building.getExtractCost(), 16, 118);
					g.drawString("WODA:     " + Game.gameManager.getProfil(0).building.getWaterCost(), 16, 154);
					g.drawString("ENERGIA:  " + Game.gameManager.getProfil(0).building.getEnergyCost(), 16, 180);
				}
			}
		}	
		private class InfoPanel extends JPanel {
			
			private FactoryPanel factoryPanel = new FactoryPanel();
			private OtherInfoPanel otherInfoPanel = new OtherInfoPanel();
			
			public InfoPanel() {
				setBounds(panelWidth - 600, 0, 600, 200);
				setLayout(null);
				setBackground(Color.BLUE);
				setVisible(false);
				
				add(factoryPanel);
				add(otherInfoPanel);
			}
			
			private class FactoryPanel extends JPanel {
				private JButton bTank = new JButton("Czo³g");
				
				public FactoryPanel() {
					setBounds(0, 0, 300, 200);
					setLayout(null);
					setBackground(Color.BLACK);
					//setVisible(false);

					
					add(bTank); bTank.setBounds(9, 9, 128, 64); 			
					bTank.addActionListener(event -> {Game.gameManager.getProfil(0).buildingSelected.order(UnitManager.UnitCost.TANK); System.out.println("Zlecenie ");});
					
							
				}
			}
			private class OtherInfoPanel extends JPanel {
				private JButton bTank = new JButton("Usuñ");
				
				public OtherInfoPanel() {
					setBounds(300, 0, 300, 200);
					setLayout(null);
					setBackground(Color.GRAY);
					//setVisible(false);

					
					add(bTank); bTank.setBounds(9, 9, 128, 64); 			
					bTank.addActionListener(event -> {if(Game.gameManager.getProfil(0).buildingSelected != null) {Game.gameManager.getProfil(0).buildingSelected.setDamage(6000); System.out.println("Niszczenie Budynku");}});							
				}
			}
		}	
	}
	//=====================================================================	KLASA WEWNÊTRZNA PANELU GÓRNEGO ========================================#################
	public class ResourcesPanel extends JPanel {	
	
		private JButton menuButton;
		//private InfoComponent coalInfo;
		//private InfoComponent extractInfo;
		//private InfoComponent waterInfo;
		private ResourcesInfo resourcesInfo = new ResourcesInfo();
		
		
		public ResourcesPanel() {
			setPreferredSize(new Dimension(100, 50));
			setBackground(Color.GRAY);
			setLayout(null);
			
			menuButton = new JButton("MENU");
			add(menuButton);
			add(resourcesInfo);
			menuButton.addActionListener(event -> {self.setVisible(false); timer.stop(); mainFrame.displayMainMenu();});
			
			
			menuButton.setBounds(panelWidth-41, 9, 32, 32);
			
		}
		public void paintComponent(Graphics g) {
			g.drawImage(graphicManager.getCoalImage(), 9, 9, null);
			g.drawImage(graphicManager.getOilImage(), 159, 9, null);
			g.drawImage(graphicManager.getExtractImage(), 309, 9, null);
			g.drawImage(graphicManager.getWaterImage(), 459, 9, null);
			g.drawImage(graphicManager.getEnergyImage(), 609, 9, null);
		}
		private class ResourcesInfo extends JComponent {
			public ResourcesInfo() {
				//setPreferredSize(new Dimension(550, 32));
				setBounds(9, 9, 700, 32);
			}
			public void paintComponent(Graphics g) {

				g.drawString(": " + Game.gameManager.getProfil(0).getCoalSupply(), 40, 14);
				g.drawString(": " + Game.gameManager.getProfil(0).getOilSupply(), 190, 14);
				g.drawString(": " + Game.gameManager.getProfil(0).getExtractSupply(), 340, 14);
				g.drawString(": " + Game.gameManager.getProfil(0).getWaterSupply(), 490, 14);
				g.drawString(": " + Game.gameManager.getProfil(0).getEnergyIncrease(), 640, 14);
				
				g.drawString(": (" + Game.gameManager.getProfil(0).getCoalIncrease()+")", 40, 28);
				g.drawString(": (" + Game.gameManager.getProfil(0).getOilIncrease()+")", 190, 28);
				g.drawString(": (" + Game.gameManager.getProfil(0).getExtractIncrease()+")", 340, 28);
				g.drawString(": (" + Game.gameManager.getProfil(0).getWaterIncrease()+")", 490, 28);
			}
		}
	}
	//=====================================================================	KLASA WEWNÊTRZNA PANELU ŒRODKOWEGO =====================================#################
	private class RunPanel extends JPanel {
		//Granice okna potrzebne do wyœwietlania obrazu
		private int nb = 0;	//North Bound
		private int sb = 0; //South Bound
		private int wb = 0; //West Bound
		private int eb = 0; //East Bound
		
		//punkt wzglêdem którego bêdziemy wyœwietlaæ mapê
		private int nPoint = 0;	
		private int wPoint = 0;
		private int thisHeight = panelHeight-250;
		private int thisWidth = panelWidth;
		
		private int imageWidth;
		private InputActionCenterPanel io;
		
		public RunPanel() {
			setBackground(Color.WHITE);
			setLayout(null);
			//this.get
			io = new InputActionCenterPanel(Game.gameManager.getProfil(0), self);
			addKeyListener(io);
			addMouseListener(io);
			addMouseMotionListener(io);
			setFocusable(true);
			requestFocus(true);
			imageWidth = Game.gameManager.getTerrainField()[0][0].getImage().getHeight();
			Game.gameManager.getProfil(0).setMaxBound(thisWidth, thisHeight, (imageWidth * Game.gameManager.getTerrainField().length)-1);
		}
		@Override
		public void paintComponent(Graphics g) {
			boundCalc();
			for(int i=nb, y=0; i<=sb; ++i, ++y)
				for(int j=wb, x=0; j<=eb; ++j, ++x)
				{
					//if(Game.gameManager.getTerrainField()[j][i].getOccupied() == false)
					g.drawImage(Game.gameManager.getTerrainField()[j][i].getImage(), imageWidth*x - wPoint, imageWidth*y - nPoint, null);
				}
			
			//	Rysujemy wszystkie jednostki jakie nale¿¹ do danego gracza
			for(int i=0; i<Game.gameManager.getProfil(0).vehicles.size(); ++i) {
				g.drawImage(Game.gameManager.getProfil(0).vehicles.get(i).getImage(), Game.gameManager.getProfil(0).vehicles.get(i).getPositionX() - Game.gameManager.getProfil(0).getViewX(), Game.gameManager.getProfil(0).vehicles.get(i).getPositionY() - Game.gameManager.getProfil(0).getViewY(), null);
			}
			
			//	Jednostkom które s¹ zaznaczone dodajemy grafikê paska zdrowia (lekko powy¿ej) i obramowanie
			for(int i=0; i<Game.gameManager.getProfil(0).vehiclesSelected.size(); ++i) {
				g.drawImage(Game.gameManager.getProfil(0).vehiclesSelected.get(i).getSelectedImage(), Game.gameManager.getProfil(0).vehiclesSelected.get(i).getPositionX() - Game.gameManager.getProfil(0).getViewX(), Game.gameManager.getProfil(0).vehiclesSelected.get(i).getPositionY() - Game.gameManager.getProfil(0).getViewY(), null);
				g.drawImage(Game.gameManager.getProfil(0).vehiclesSelected.get(i).getHealthImage(), Game.gameManager.getProfil(0).vehiclesSelected.get(i).getPositionX() - Game.gameManager.getProfil(0).getViewX(), Game.gameManager.getProfil(0).vehiclesSelected.get(i).getPositionY()-8 - Game.gameManager.getProfil(0).getViewY(), null);

			}
			//	Rysujemy wszystkie budynki jakie nale¿¹ do danego gracza
			for(int i=0; i<Game.gameManager.getProfil(0).buildings.size(); ++i) {
				g.drawImage(Game.gameManager.getProfil(0).buildings.get(i).getImage(), Game.gameManager.getProfil(0).buildings.get(i).getPositionX() - Game.gameManager.getProfil(0).getViewX(), Game.gameManager.getProfil(0).buildings.get(i).getPositionY() - Game.gameManager.getProfil(0).getViewY(), null);
			}
			if(Game.gameManager.getProfil(0).buildingSelected != null) {
				g.drawImage(Game.gameManager.getProfil(0).buildingSelected.getSelectedImage(), Game.gameManager.getProfil(0).buildingSelected.getPositionX() - Game.gameManager.getProfil(0).getViewX(), Game.gameManager.getProfil(0).buildingSelected.getPositionY() - Game.gameManager.getProfil(0).getViewY(), null);
				g.drawImage(Game.gameManager.getProfil(0).buildingSelected.getHealthImage(), Game.gameManager.getProfil(0).buildingSelected.getPositionX() - Game.gameManager.getProfil(0).getViewX(), Game.gameManager.getProfil(0).buildingSelected.getPositionY()-8 - Game.gameManager.getProfil(0).getViewY(), null);

			}
	
			
			if(Game.gameManager.getProfil(0).building != UnitManager.UnitCost.NON) {
				if(Game.gameManager.getProfil(0).building == UnitManager.UnitCost.FACTORY) {
						g.drawImage(Game.gameManager.getUnitGraphic().getBuild3ImageYES(), Game.gameManager.getProfil(0).mouseX-16, Game.gameManager.getProfil(0).mouseY-16, null);
				}
				else if(Game.gameManager.getProfil(0).building == UnitManager.UnitCost.LABORATORY) {
						g.drawImage(Game.gameManager.getUnitGraphic().getBuild2ImageYES(), Game.gameManager.getProfil(0).mouseX-16, Game.gameManager.getProfil(0).mouseY-16, null);
				}
				else {
						g.drawImage(Game.gameManager.getUnitGraphic().getBuild1ImageYES(), Game.gameManager.getProfil(0).mouseX-16, Game.gameManager.getProfil(0).mouseY-16, null);					
				}	
			}
			
			
			
			//	Rysujemy obramowanie którym u¿ytkownik zaznacza obiekty na mapie			
			if(Game.gameManager.getProfil(0).isMouseClicked == true) {
				Graphics2D g2 = (Graphics2D) g;
				Rectangle2D rect = new Rectangle2D.Double();
				rect.setFrameFromDiagonal(Game.gameManager.getProfil(0).mouseStartX, Game.gameManager.getProfil(0).mouseStartY,  Game.gameManager.getProfil(0).mouseX, Game.gameManager.getProfil(0).mouseY);
				g2.draw(rect);
			}
			
			//System.out.println("Odœwierzenie RunPanel");
		}
		private void boundCalc() {
			wb = Game.gameManager.getProfil(0).getViewX()/imageWidth;
			nb = Game.gameManager.getProfil(0).getViewY()/imageWidth;
			
			eb = (Game.gameManager.getProfil(0).getViewX()+thisWidth)/imageWidth; if(eb>Game.gameManager.getTerrainField().length) eb = Game.gameManager.getTerrainField().length-1;
			sb = (Game.gameManager.getProfil(0).getViewY()+thisHeight)/imageWidth; if(sb>Game.gameManager.getTerrainField().length) sb = Game.gameManager.getTerrainField().length-1;
			
			//System.out.println(Game.gameManager.getProfil(0).getViewX() + " + "+ thisWidth + " / " + imageWidth + " = " + eb);
			
			wPoint = Game.gameManager.getProfil(0).getViewX() % imageWidth;
			nPoint = Game.gameManager.getProfil(0).getViewY() % imageWidth;
			
			//System.out.println(Game.gameManager.getProfil(0).getViewX() + " : " + (Game.gameManager.getProfil(0).getViewX() + panelWidth) + " X " + Game.gameManager.getProfil(0).getViewY() + " : " + (Game.gameManager.getProfil(0).getViewY() + panelHeight));
			//System.out.println(wb + " : " + eb + " X " + nb + " : " + sb);
			//Game.gameManager.getTerrainField().length
		}
	}
}
