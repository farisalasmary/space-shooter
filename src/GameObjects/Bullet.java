package GameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import Utilities.Position;

/**
 * This class is used to control bullets in the game.
 * 
 * @author FarisAlasmary
 * @version 1.0
 * 
 * @see GameObjects.EnemyBullets
 * @see GameObjects.PlayerBullets
 * 
 */

public class Bullet extends GameObjects{

	private Player player;
	private int velocity = 10; // You can change it to whatever velocity you want
	
	/**
	 * Constructs the bullet object. This is the most common in use
	 * 
	 * @param bulletImage is the image of the bullet 
	 * @exception NullPointerException if there is no bulletImage 
	 *  
	 */
	public Bullet(BufferedImage bulletImage){
		super(bulletImage);
		super.setVelX(velocity);
		super.setVelY(velocity);
	}
	
	/**
	 * Constructs the bullet object.
	 * 
	 * @param bulletImage is the image of the bullet 
	 * @param targetPosition is the destination 
	 * @exception NullPointerException if there is no bulletImage OR targetPosition
	 * 
	 */
	public Bullet(BufferedImage bulletImage, Position targetPosition){
		super(bulletImage, targetPosition);
		this.setVelX(velocity);
		this.setVelY(velocity);
		this.setXTranslation(targetPosition.getX()); // this constructor different than the one above because it has to deal with the target position
		this.setYTranslation(targetPosition.getY()); // this constructor different than the one above because it has to deal with the target position
	}
	
	/**
	 * Constructs the bullet object.
	 * 
	 * @param bulletImage is the image of the bullet 
	 * @param targetPosition is the destination 
	 * @param player to take the rotation angle from and other stuffs
	 * 
	 * @exception NullPointerException if there is no bulletImage OR targetPosition OR player
	 * 
	 */
	public Bullet(BufferedImage bulletImage, Position targetPosition, Player player){
		super(bulletImage, targetPosition);
		this.setVelX(velocity);
		this.setVelY(velocity);
		this.setXTranslation(player.getPosition().getX());
		this.setYTranslation(player.getPosition().getY());
		this.setPlayer(player);
	}

	/**
	 * The setter of the bullet velocity
	 * 
	 * @param velocity is the new velocity of the bullet
	 * 
	 */
	public void setVelocity(int velocity){
		this.velocity = velocity;
		this.setVelX(velocity);
		this.setVelY(velocity);
	}
	
	/**
	 * This is the getter of the velocity variable
	 * 
	 * @return It returns the current velocity of the bullet
	 * 
	 */
	public int getVelocity(){
		return this.velocity;
	}
	
	/**
	 * The player getter
	 * 
	 * @return It returns a reference to a Player object
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * The player setter
	 * 
	 * @param player is the new player
	 * 
	 * @exception NullPointerException if there is no player
	 * 
	 * @see GameObjects.Player
	 */
	public void setPlayer(Player player) {
		if(player != null)
			this.player = player;
		else
			throw new NullPointerException("There is no player position!");
	}

	/**
	 * You can see it in the GameObjects class
	 * 
	 * @see GameObjects.GameObjects
	 */
	@Override
	public void render(Graphics g, Position targetPosition) {

		// move to the new position with a certain velocity
		TranslateToTheNewPosition();
		
		// It can be done by substitution directly in Math.atan2() but 
		// I put it here for readability purpose
		
		double xCharacter = (this.player.getCharacterImage().getWidth() / 2) + this.getXTranslation();
		double yCharacter = (this.player.getCharacterImage().getHeight() / 2 ) + this.getYTranslation();

		// rotate the character image by this angle
		AffineTransform at = new AffineTransform();
		at.rotate(this.player.getAngle(), xCharacter, yCharacter); // get the same angle from the player
		at.translate(this.getXTranslation(), this.getYTranslation());
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); // It sharpens the image and makes it smoother 
		g2d.drawImage(this.getCharacterImage(), at, null);
//		g2d.draw3DRect((int)this.getXTranslation(), (int)this.getYTranslation(), this.getCharacterImage().getWidth(), this.getCharacterImage().getHeight(), true);

		resetPosition(); // if you delete this line, the image will move in a weird behavior

	}
	
	/**
	 * You can see it in the GameObjects class
	 * 
	 * @see GameObjects.GameObjects
	 */
	// This method have to be overridden because the velocity will change depending on the angle
	@Override
	public void TranslateToTheNewPosition() {
		this.setXTranslation(this.getXTranslation() + 
							 (this.getVelX() * Math.cos(this.player.getAngle())));  
		this.setYTranslation(this.getYTranslation() + 
							 (this.getVelY() * Math.sin(this.player.getAngle())));
		this.getPosition().setX(this.getXTranslation());
		this.getPosition().setY(this.getYTranslation());
	}
	
	/**
	 * You can see it in the GameObjects class
	 * 
	 * @see GameObjects.GameObjects
	 */
	// This also have to be overridden to keep the bullet moving
	@Override
	public void resetPosition() {
		// NOTHING HERE
	}
	
}
