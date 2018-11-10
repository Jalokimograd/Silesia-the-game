package panel;

import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GraphicManager {
	
	private BufferedImage coalImage;
	private BufferedImage extractImage;
	private BufferedImage waterImage;
	private BufferedImage energyImage;
	private BufferedImage oilImage;

	public GraphicManager() {
		try {
			this.coalImage = ImageIO.read(new File("graphics/interface/coal.png"));
		} catch (IOException ex) {System.exit(0);}
		
		try {
			this.extractImage = ImageIO.read(new File("graphics/interface/extract.png"));
		} catch (IOException ex) {System.exit(0);}
		
		try {
			this.waterImage = ImageIO.read(new File("graphics/interface/water.png"));
		} catch (IOException ex) {System.exit(0);}
		
		try {
			this.energyImage = ImageIO.read(new File("graphics/interface/energy.png"));
		} catch (IOException ex) {System.exit(0);}

		try {
			this.oilImage = ImageIO.read(new File("graphics/interface/oil.png"));
		} catch (IOException ex) {System.exit(0);}

	}
	
	public BufferedImage getCoalImage() {return coalImage;}
	public BufferedImage getExtractImage() {return extractImage;}
	public BufferedImage getWaterImage() {return waterImage;}
	public BufferedImage getEnergyImage() {return energyImage;}
	public BufferedImage getOilImage() {return oilImage;}
}
