package GameObjects;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.ResourceBundle.Control;

import Utilities.Position;

/**
 * This class is the one which is going to be used as the player in the game.
 * 
 * @author FarisAlasmary
 * @version 1.0
 * @see GameObjects.Player
 * 
 */

public class Player extends GameObjects{

	private BufferedImage playerBullet = this.LoadBufferedImage("/images/bullet.png");
	private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	
	/**
	 * Constructs the player object.
	 * 
	 * @param x is the x-coordinate of the player 
	 * @param y is the y-coordinate of the player
	 * @param angle is the rotation angle
	 * @param characterImage is the image that represent the player object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage
	 * 
	 */
	public Player(int x, int y, double angle, BufferedImage characterImage){
		super(x, y, angle, null);
	}
	
	/**
	 * Constructs the player object. This constructor is the most common in use.
	 *
	 * @param characterImage is the image that represent the player object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage
	 * 
	 */
	public Player(BufferedImage characterImage){
		super(characterImage);
	}
	
	/**
	 * Constructs the player object.
	 * 
	 * @param characterImage is the image that represent the player object on the panel
	 * @param position is the position of the player on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage OR position
	 * 
	 */
	public Player(BufferedImage characterImage, Position position) {
		super(characterImage, position);
	}

	/**
	 * The copy constructor.
	 * 
	 * @param other is the image that represent the player object on the panel
	 * 
	 * @exception NullPointerException if there is no GameObjects object
	 * 
	 * @see GameObjects.GameObjects
	 */
	public Player(GameObjects other){
		super(other);
	}
	
	/**
	 * The getter of the player's boundaries. It will be used to detect the collision
	 *  
	 * @return It returns the bounded rectangle 
	 * 
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)this.getXTranslation(), (int)this.getYTranslation(),
			    this.getCharacterImage().getWidth() -50, this.getCharacterImage().getHeight() -20);
	}

	/**
	 * This method is to fire bullets from the player.
	 * It will be invoked automatically inside the MouseHandler if the player clicked the mouse left-button.
	 * 
	 * @see Control.MouseHandler
	 */
	public void Fire(){
		PlayerBullets tempBullet = new PlayerBullets(this.playerBullet,
								   new Position(this.getTargetPosition().getX(), this.getTargetPosition().getY()), // the targetPosition is actually the mousePosition
							       new Player(this));
		this.bullets.add(tempBullet);
	}

	/**
	 * The getter of the player's bullets.
	 * 
	 * @return It returns a reference to a linked list of type "Bullet" which will be used as player's bullets.
	 *
	 */
	public LinkedList<Bullet> getBullets() {
		return this.bullets;
	}

	/**
	 * The setter of the player's bullets.
	 * 
	 * @param bullets is a reference to a linked list of type "Bullet" that is going to be used as player's bullets.
	 * 
	 * @exception NullPointerException if there is no GameObjects object
	 * 
	 */
	public void setBullets(LinkedList<Bullet> bullets) {
		if(bullets != null)
			this.bullets = bullets;
		else
			throw new NullPointerException("There is no LinkedList of type bullet!");
	}
}
