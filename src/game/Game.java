package game;

import map.*;
import unit.*;
import algorithm.*;

import javax.swing.*;
import java.awt.*;

public class Game {
	
	public static GameManager gameManager;
	
	public static void main(String[] args) {

			
		EventQueue.invokeLater(() ->
		{
			Game.gameManager = new GameManager("Ja³okim");
			MainFrame frame = new MainFrame();
			


		});		
	}
}
