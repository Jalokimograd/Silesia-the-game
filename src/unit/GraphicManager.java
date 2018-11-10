package unit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicManager {
	private BufferedImage[] tankImage = new  BufferedImage[8];
	
	private BufferedImage[] factoryImage = new  BufferedImage[8];
	private BufferedImage[] mineImage = new  BufferedImage[8];
	private BufferedImage[] laboratoryImage = new  BufferedImage[8];
	private BufferedImage[] pumpImage = new  BufferedImage[8];
	private BufferedImage[] oilImage = new  BufferedImage[8];
	private BufferedImage[] generatorImage = new  BufferedImage[8];

	private BufferedImage[] selectImage = new  BufferedImage[4];
	private BufferedImage[] health1Image = new  BufferedImage[8];
	
	private BufferedImage[] build0Image = new  BufferedImage[2];
	private BufferedImage[] build1Image = new  BufferedImage[2];
	private BufferedImage[] build2Image = new  BufferedImage[2];
	private BufferedImage[] build3Image = new  BufferedImage[2];


	public GraphicManager() {
		for(int i=0; i<8; ++i) {
			try {
				this.tankImage[i] = ImageIO.read(new File("graphics/units/vehicles/tank/"+ i +".png"));
			} catch (IOException ex) {System.exit(0);}
						
			try {
				this.health1Image[i] = ImageIO.read(new File("graphics//special/health1_" + i +".png"));
			} catch (IOException ex) {System.exit(0);}
			
			
			//======================================================================================================
			try {
				this.factoryImage[i] = ImageIO.read(new File("graphics/units/buildings/factory/0.png"));
			} catch (IOException ex) {System.exit(0);}
			
			try {
				this.laboratoryImage[i] = ImageIO.read(new File("graphics/units/buildings/laboratory/0.png"));
			} catch (IOException ex) {System.exit(0);}
			
			try {
				this.mineImage[i] = ImageIO.read(new File("graphics/units/buildings/mine/0.png"));
			} catch (IOException ex) {System.exit(0);}
			
			try {
				this.pumpImage[i] = ImageIO.read(new File("graphics/units/buildings/pump/0.png"));
			} catch (IOException ex) {System.exit(0);}
			
			try {
				this.oilImage[i] = ImageIO.read(new File("graphics/units/buildings/oil/0.png"));
			} catch (IOException ex) {System.exit(0);}
			
			try {
				this.generatorImage[i] = ImageIO.read(new File("graphics/units/buildings/generator/0.png"));
			} catch (IOException ex) {System.exit(0);}
		}
		for(int i=0; i<4; ++i) {
			try {
				this.selectImage[i] = ImageIO.read(new File("graphics/special/select"+ i +".png"));
			} catch (IOException ex) {System.exit(0);}
		}
		
		for(int i=0; i<2; ++i) {
			try {
				this.build0Image[i] = ImageIO.read(new File("graphics/special/build0_"+ i +".png"));
			} catch (IOException ex) {System.exit(0);}
			
			try {
				this.build1Image[i] = ImageIO.read(new File("graphics/special/build1_"+ i +".png"));
			} catch (IOException ex) {System.exit(0);}
			
			try {
				this.build2Image[i] = ImageIO.read(new File("graphics/special/build2_"+ i +".png"));
			} catch (IOException ex) {System.exit(0);}
			
			try {
				this.build3Image[i] = ImageIO.read(new File("graphics/special/build3_"+ i +".png"));
			} catch (IOException ex) {System.exit(0);}
		}
		
		



	}
	
	public BufferedImage[] getTankImage() {return tankImage;}
	
	public BufferedImage[] getFactoryImage() {return factoryImage;}
	public BufferedImage[] getLaboratoryImage() {return laboratoryImage;}
	public BufferedImage[] getMineImage() {return mineImage;}
	public BufferedImage[] getPumpImage() {return pumpImage;}
	public BufferedImage[] getOilImage() {return oilImage;}
	public BufferedImage[] getGeneratorImage() {return generatorImage;}
	
	public BufferedImage getSelect0Image() {return selectImage[0];}
	public BufferedImage getSelect1Image() {return selectImage[1];}
	public BufferedImage getSelect2Image() {return selectImage[2];}
	public BufferedImage getSelect3Image() {return selectImage[3];}
	public BufferedImage[] gethealth1Image() {return health1Image;}
	
	public BufferedImage getBuild0ImageNO() {return build0Image[0];}
	public BufferedImage getBuild1ImageNO() {return build1Image[0];}
	public BufferedImage getBuild2ImageNO() {return build2Image[0];}
	public BufferedImage getBuild3ImageNO() {return build3Image[0];}
	
	public BufferedImage getBuild0ImageYES() {return build0Image[1];}
	public BufferedImage getBuild1ImageYES() {return build1Image[1];}
	public BufferedImage getBuild2ImageYES() {return build2Image[1];}
	public BufferedImage getBuild3ImageYES() {return build3Image[1];}


}
