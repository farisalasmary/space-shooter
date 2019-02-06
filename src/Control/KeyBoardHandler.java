package Control;

import java.awt.Dimension;
import java.awt.event.*;

import GameEngine.GameEngine;
import GameObjects.GameObjects;
import GameObjects.Player;

/**
 * This class is used as a keyListener to handle the control of the player object.
 * It takes as an argument a reference to an object of type "Player" which is the 
 * object wanted to be controlled 
 * 
 * @author FarisAlasmary
 * @version 1.0
 * @see GameObjects.Player
 * 
 */

public class KeyBoardHandler implements KeyListener{

	private boolean keyDown[] = new boolean[4]; // D = 0, A = 1, W = 2, S = 3 these are the buttons with there indices
	private int velocity;
	private Player player;
	private Dimension screenSize;
	
	// Default Controller
	private int Right = KeyEvent.VK_D;
	private int Left = KeyEvent.VK_A;
	private int Up = KeyEvent.VK_W;
	private int Down = KeyEvent.VK_S;
	
	/**
	 * Constructs a keyboard handler to be used as a "KeyListener" for another class
	 * 
	 * @param player is the object wanted to be controlled 
	 * @param velocity the velocity that is going to be used as the speed of the player
	 * @param screenSize is the size of the screen to keep the player inside the screen
	 * 
	 * @exception NullPointerException if there is no player OR no screenSize
	 * 
	 */
	
	public KeyBoardHandler(Player player, int velocity, Dimension screenSize){
		this.setPlayer(player);
		this.setVelocity(velocity);
		this.setScreenSize(screenSize);
	}
	
	/**
	 * The setter of the screenSize parameter
	 * 
	 * @param screenSize
	 * @exception NullPointerException if there is no object for screen size
	 * @see java.lang.NullPointerException
	 * 
	 */
	public void setScreenSize(Dimension screenSize){
		if(screenSize != null)
			this.screenSize = screenSize;
		else
			throw new NullPointerException("There is no ScreenSize!!");
	}
	
	/**
	 * The getter of the screenSize parameter
	 * 
	 * @return It returns a new object of type "Dimension" containing the screen size dimensions
	 * 
	 */
	public Dimension getScreenSize(){
		return new Dimension(this.screenSize);
	}
	
	/**
	 * The setter of the velocity parameter
	 * 
	 * @param velocity
	 * 
	 */
	public void setVelocity(int velocity){
		this.velocity = velocity;
	}
	
	/**
	 * The getter of the velocity parameter
	 * 
	 * @return It returns an integer value 
	 * 
	 */
	public int getVelocity(){
		return this.velocity;
	}
	
	/**
	 * The setter of the player parameter
	 * 
	 * @param player
	 * @exception NullPointerException if there is no player object
	 * @see java.lang.NullPointerException
	 * @see GameObjects.Player
	 */
	public void setPlayer(Player player){
		if(player != null)
			this.player = player;
		else
			throw new NullPointerException("There is no Player object!!");
	}
	
	/**
	 * The getter of the player parameter
	 * 
	 * @return It returns the reference of the same player object NOT A NEW ONE
	 * 
	 */
	public Player getPlayer() {
		return player;
	}
		
	/**
	 * It will be invoked inside the GameRender class to move the player
	 * 
	 * @see GameEngine.GameRenderer
	 * 
	 */
	public void MoveThePlayer(){
		
		if(keyDown[2] && keyDown[0]){ // W & D
			player.setVelY(-velocity); //the minus sign because of the y-axis is reversed in computers' screens
			player.setVelX(velocity);
		
		}else if(keyDown[2] && keyDown[1]){ // W & A
			player.setVelY(-velocity); // the minus sign because of the y-axis is reversed in computers' screens
			player.setVelX(-velocity);
		
		}else if(keyDown[3] && keyDown[0]){ // S & D
			player.setVelY(velocity); // the plus sign because of the y-axis is reversed in computers' screens
			player.setVelX(velocity);
		
		}else if(keyDown[3] && keyDown[1]){ // S & A
			player.setVelY(velocity); // the plus sign because of the y-axis is reversed in computers' screens
			player.setVelX(-velocity);
		
		}
		
		else if(keyDown[0]){ // D
			player.setVelY(0); // to fix the y-axis
			player.setVelX(velocity);
		
		}else if(keyDown[1]){ // A
			player.setVelY(0); // to fix the y-axis
			player.setVelX(-velocity);
		
		}else if(keyDown[2]){ // W
			player.setVelY(-velocity); // the minus sign because of the y-axis is reversed in computers' screens
			player.setVelX(0); // to fix the x-axis
		
		}else if(keyDown[3]){ // S
			player.setVelY(velocity); // the plus sign because of the y-axis is reversed in computers' screens
			player.setVelX(0); // to fix the x-axis
		
		}	
		
		// if the player got out of the screen boundaries, move it to the opposite side
		
		if(this.player.getXTranslation() > (this.screenSize.getWidth() + 10))
			this.player.setXTranslation(-100);
		else if(this.player.getXTranslation() < -100)
			this.player.setXTranslation((this.screenSize.getWidth() + 10));
		else if(this.player.getYTranslation() < -100)
			this.player.setYTranslation((this.screenSize.getHeight() + 10));
		else if(this.player.getYTranslation() > (this.screenSize.getHeight() + 10))
			this.player.setYTranslation(-100);
		
	}
	
	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void keyPressed(KeyEvent ke){
		
		if(ke.getKeyCode() == KeyEvent.VK_D){
			keyDown[0] = true;
		}else if(ke.getKeyCode() == KeyEvent.VK_A){
			keyDown[1] = true;
		}else if(ke.getKeyCode() == KeyEvent.VK_W){
			keyDown[2] = true;
		}else if(ke.getKeyCode() == KeyEvent.VK_S){ // indices D = 0, A = 1, W = 2, S = 3
			keyDown[3] = true;
		}
	}
	
	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void keyReleased(KeyEvent ke){
		if(ke.getKeyCode() == this.getRight()){ 
			keyDown[0] = false;
		}else if(ke.getKeyCode() == this.getLeft()){
			keyDown[1] = false;
		}else if(ke.getKeyCode() == this.getUp()){
			keyDown[2] = false;
		}else if(ke.getKeyCode() == this.getDown()){ // indices D = 0, A = 1, W = 2, S = 3
			keyDown[3] = false;
		}
	}
	
	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void keyTyped(KeyEvent ke){}

	/**
	 * The getter of the button used to move the player to the right
	 * 
	 * @return It returns the key code of the button 
	 * 
	 */
	public int getRight() {
		return Right;
	}

	/**
	 * The setter of the button used to move the player to the right 
	 * 
	 * @param right is the key code of the button that is going to be used as a right control button. It is recommended
	 * to use KeyEvent class 
	 * 
	 */
	public void setRight(int right) {
		Right = right;
	}

	/**
	 * The getter of the button used to move the player to the left
	 * 
	 * @return It returns the key code of the button
	 * 
	 */
	public int getLeft() {
		return Left;
	}

	/**
	 * The setter of the button used to move the player to the left 
	 * 
	 * @param left is the key code of the button that is going to be used as a left control button. It is recommended
	 * to use KeyEvent class 
	 * 
	 */
	public void setLeft(int left) {
		Left = left;
	}

	/**
	 * The getter of the button used to move the player to the up
	 * 
	 * @return It returns the key code of the button
	 * 
	 */
	public int getUp() {
		return Up;
	}

	/**
	 * The setter of the button used to move the player to the up 
	 * 
	 * @param up is the key code of the button that is going to be used as a up control button. It is recommended
	 * to use KeyEvent class 
	 * 
	 */
	public void setUp(int up) {
		Up = up;
	}

	/**
	 * The getter of the button used to move the player to the down
	 * 
	 * @return It returns the key code of the button
	 * 
	 */
	public int getDown() {
		return Down;
	}

	/**
	 * The setter of the button used to move the player to the down 
	 * 
	 * @param down is the key code of the button that is going to be used as a down control button. It is recommended
	 * to use KeyEvent class 
	 * 
	 */
	public void setDown(int down) {
		Down = down;
	}

}

