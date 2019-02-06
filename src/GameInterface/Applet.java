package GameInterface;

import java.awt.Dimension;
import javax.swing.JApplet;
import GameEngine.GameEngine;


/**
 * This class is to be used inside a website to run the game online.
 * 
 * @author FarisAlasmary
 * @version 1.0
 * 
 */

public class Applet extends JApplet{


	private static final long serialVersionUID = 1L;

	private Dimension size;

	@Override
	public void init() {

		size = new Dimension(1350, 604);
		GameEngine game = new GameEngine(size); // it is just a normal panel
		this.add(game);
				
	}
	
}
