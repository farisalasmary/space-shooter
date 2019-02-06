package GameInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import GameEngine.GameEngine;

/**
 * This is the game menu class. It will be initialized and run by the Main class OR the Applet class
 * 
 * @author FarisAlasmary
 * @version 1.0
 *
 */

public class Menu extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JButton Start, Help, Exit;
	private Image backGround;
	private JPanel CenterPanel;
	private final String GameName = "Star War II";
	
	public Menu(){
		this.setLayout(new BorderLayout());
		this.setBackGround("/images/menu.png");
		this.setSize(backGround.getWidth(null), backGround.getHeight(null));
		
		CenterPanel = new JPanel(new BorderLayout());
		
		Start = new JButton("Start");
		Help  = new JButton("Help");
		Exit  = new JButton("Exit");
		
		Start.addActionListener(this);
		Help.addActionListener(this);
		Exit.addActionListener(this);
		
		CenterPanel.add(Start, BorderLayout.NORTH);		
		CenterPanel.add(Help, BorderLayout.CENTER);		
		CenterPanel.add(Exit, BorderLayout.SOUTH);
		
		add(CenterPanel, BorderLayout.SOUTH);
			
	}
	
	/**
	 * the getter of the background image
	 * 
	 * @return It returns a reference to the image used in the main menu
	 */
	public Image getBackGround(){
		return this.backGround;
	}
	
	/**
	 * the setter of the background image
	 * 
	 * @param fileName
	 */
	public void setBackGround(String fileName) {
		this.backGround = new ImageIcon(this.getClass().getResource(fileName)).getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(this.backGround, 0, 0, null);
		g2d.setFont(LoadFont("RetroComputer.ttf", Font.BOLD, 70));
		g2d.setColor(Color.white);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawString(this.GameName, this.backGround.getWidth(null) / 2 - 300, 150);
		g2d.setFont(LoadFont("RetroComputer.ttf", Font.BOLD, 20));
		g2d.drawString("By : Faris Alasmary", this.backGround.getWidth(null) / 2 - 150, 300);
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == Start){
			initializingTheGame();
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
			frame.dispose();
//			this.frame.dispose();
		}else if(ae.getSource() == Help){
			String message = "* How To Play : \n\n" + 
							 "1-Start the game and control the player using WASD keys\n\n" + 
							 "2-Move the mouse pointer to aim to enemies and click the left mouse button to fire bullets\n\n" + 
							 "3-Try to avoid the bullets of the shooter enemy\n\n" + 
							 "4-Also try to avoid collision with moving follower enemies\n";
			JOptionPane.showMessageDialog(null, message, "Help", JOptionPane.INFORMATION_MESSAGE);
		}else if(ae.getSource() == Exit){
			System.exit(0); // Successfully ended
		}
		
	}
	
	// start the game 
	private void initializingTheGame(){
		JFrame frame = new JFrame();
		frame.setSize(1140, 604); // this is almost the size of the background ;-)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameEngine game = new GameEngine(frame); // it is just a normal panel
		frame.add(game);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setTitle(this.GameName);
				
		// To set the frame exactly in the center of the screen
		frame.setLocationRelativeTo(null); // frame.setLocationRelativeTo(null);		

	}
	
	private Font LoadFont(String fileName, int style, int size){
		try {
			return Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream(fileName)).deriveFont(style, size);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
		
	}

	
}
