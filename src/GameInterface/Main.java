
package GameInterface;

import javax.swing.JFrame;

/**
 * This is the main class that will run the whole game. It just contains the main method.
 * 
 * @author FarisAlasmary
 * @version 1.0
 * 
 */

public class Main {
	
	private static final String GameName = "Star War II";
	
	public static void main(String args[]){

		Menu menu = new Menu();
			
		JFrame frame = new JFrame(GameName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(menu.getBackGround().getWidth(null), menu.getBackGround().getHeight(null));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(menu);
		frame.setVisible(true);
		
	}

}
