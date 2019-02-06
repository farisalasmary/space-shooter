package GameEngine;


import java.awt.*;
import javax.swing.*;
import Utilities.*;
import Control.*;

/**
 * This is the engine of the game.
 * This class is used to link other classes involved in the game together.
 * 
 * @author FarisAlasmary
 * @version 1.0
 *
 */

public class GameEngine extends JPanel{
	
	
	private static final long serialVersionUID = 1L;
	
	private Image backGround;
	private Position myMousePosition  = new Position(); // for the mouse's coordinates
	private Position clickedMousePosition = new Position();
	private Dimension screenSize;
	private KeyBoardHandler keyBoardHandler;
	private MouseHandler mouseHandler;
	private GameLoop gameLoop;
	private Font RetroComputerFont = LoadFont("/images/RetroComputer.ttf", Font.BOLD, 30);
	private GameRenderer gameRender;
	private String levelTXT = "LEVEL : ";
	private String scoreTXT = "Score : ";
	private JFrame frame;
	
	private int velocityOfThePlayer = 5;

	/**
	 * Constructs the game engine with the frame that is used to paint on. It is the recommended way to construct the engine
	 * 
	 * @param frame is the frame of the game which is used to get the size from
	 * 
	 */
	public GameEngine(JFrame frame){

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);	
		this.screenSize = new Dimension(frame.getWidth(), frame.getHeight());
		this.setScreenSize(screenSize);
		this.setFrame(frame);
		this.gameLoop = new GameLoop(this, true); // initialize the game loop
		this.gameRender = new GameRenderer(this); // Creating a GameRender to render and move the objects
		this.backGround = LoadImage("/images/background.png"); // load the background image of the panel
		
		// Creating Controller to handle the keyboard events
		this.keyBoardHandler = new KeyBoardHandler(gameRender.getPlayer(), velocityOfThePlayer, this.getScreenSize());
		this.addKeyListener(keyBoardHandler);
		
		// Creating MouseHandler to handle the mouse events
		mouseHandler = new MouseHandler(myMousePosition, this);
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		 
		gameLoop.setGameLooping(true);
		Thread theGameLoopThread = new Thread(gameLoop); // The game will NOT run without this thread
		theGameLoopThread.start();

	}
	
	/**
	 * Constructs the game engine with a specific size given as an argument.
	 * This way is not recommended since the size of the frame is going to change.
	 * 
	 * @param screenSize is the frame of the game which is used to get the size from
	 * 
	 */
	public GameEngine(Dimension screenSize){

		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);				
		this.setScreenSize(screenSize);
		frame = new JFrame();
		frame.setSize(screenSize);
		this.gameLoop = new GameLoop(this, true); // initialize the game loop
		this.gameRender = new GameRenderer(this); // Creating a GameRender to render and move the objects
		this.backGround = LoadImage("/images/background.png"); // load the background image of the panel
		
		// Creating Controller to handle the keyboard events
		this.keyBoardHandler = new KeyBoardHandler(gameRender.getPlayer(), velocityOfThePlayer, this.getScreenSize());
		this.addKeyListener(keyBoardHandler);
		
		// Creating MouseHandler to handle the mouse events
		mouseHandler = new MouseHandler(myMousePosition, this);
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		 
		gameLoop.setGameLooping(true);
		Thread theGameLoopThread = new Thread(gameLoop); // The game will NOT run without this thread
		theGameLoopThread.start();
	}
		
	/**
	 * It loads image of type Image
	 * 
	 * @param fileName
	 * @return It returns an image of type "Image"
	 * @see java.awt.Image
	 * 
	 */
	private Image LoadImage(String fileName) {
		return new ImageIcon(this.getClass().getResource(fileName)).getImage();
	}
		
	public void paintComponent(Graphics g){
		super.paintComponent(g); // This line is to clear the JPanel before using "repaint()" method

		// to check if the frame size changed and set "screenSize" to the new size 
	    if(frame.getWidth() != screenSize.getWidth() || frame.getHeight() != screenSize.getHeight())
	    	screenSize.setSize(frame.getWidth(), frame.getHeight());  
		
		g.drawImage(backGround, 0, 0, null);
	    g.setFont(RetroComputerFont.deriveFont(20));
	    g.setColor(Color.white);
	    g.drawString(levelTXT + gameRender.getLevelNumber(), (int)(this.screenSize.getWidth() / 2) - 100, 50);
	    g.drawString(scoreTXT + gameRender.getScore(), (int)this.screenSize.getWidth() - 250, 50);
	    
	    gameRender.renderAll(g); // Render ALL the game objects
	    
	}
	
	/**
	 * The screen size getter
	 * 
	 * @return It returns the size as an object of type Dimension
	 * @see java.awt.Dimension
	 */
	public Dimension getScreenSize() {
		return screenSize;
	}

	/**
	 * The screen size setter
	 * @param screenSize is the new screen size 
	 */
	public void setScreenSize(Dimension screenSize) {
		if(screenSize != null)
			this.screenSize = screenSize;
		else
			throw new NullPointerException("There is no object for the size!!");
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
	
	/**
	 * The frame getter
	 * 
	 * @return It returns the cuurent frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * The frame setter
	 * 
	 * @param frame is the new frame
	 */
	public void setFrame(JFrame frame) {
		if(frame != null)
			this.frame = frame;
		else
			throw new NullPointerException("There is no frame!!");
	}

	public Image getBackGround() {
		return backGround;
	}

	public void setBackGround(Image backGround) {
		this.backGround = backGround;
	}
	
	/**
	 * The keyboard handler getter
	 * 
	 * @return it returns the game keyboard handler
	 */
	public KeyBoardHandler getKeyBoardHandler() {
		return keyBoardHandler;
	}

	/**
	 * The keyboard handlersetter
	 * 
	 * @param controller is the new keyboard handler
	 */
	public void setKeyBoardHandler(KeyBoardHandler controller) {
		this.keyBoardHandler = controller;
	}

	public MouseHandler getMouseHandler() {
		return mouseHandler;
	}

	public void setMouseHandler(MouseHandler mouseHandler) {
		this.mouseHandler = mouseHandler;
	}

	public GameLoop getGameLoop() {
		return gameLoop;
	}

	public void setGameLoop(GameLoop gameLoop) {
		this.gameLoop = gameLoop;
	}
	
	public Position getMyMousePosition() {
		return myMousePosition;
	}

	public void setMyMousePosition(Position myMousePosition) {
		this.myMousePosition = myMousePosition;
	}

	/**
	 * @return the gameRender
	 */
	public GameRenderer getGameRender() {
		return gameRender;
	}

	/**
	 * @param gameRenderer the gameRender to set
	 */
	public void setGameRender(GameRenderer gameRenderer) {
		if(gameRenderer != null)
			this.gameRender = gameRenderer;
		else
			throw new NullPointerException("There is no gameRender!!");
	}

	public Position getClickedMousePosition() {
		return clickedMousePosition;
	}

	public void setClickedMousePosition(Position clickedMousePosition) {
		if(clickedMousePosition != null)
			this.clickedMousePosition = clickedMousePosition;
		else
			throw new NullPointerException("There is no clickedMousePosition!");
	}
	
}
	