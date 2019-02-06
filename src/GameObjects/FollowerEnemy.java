package GameObjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Utilities.Position;

/**
 * This is the class of follower enemies who are the ones following the player 
 * 
 * @author FarisAlasmary
 * @version 1.0
 *
 * @see GameObjects.GameObjects
 * 
 */

public class FollowerEnemy extends GameObjects {
	
	/**
	 * Constructs the FollowerEnemy object.
	 * 
	 * @param xTranslation is the x-coordinate of the object 
	 * @param yTranslation is the y-coordinate of the object
	 * @param angle is the rotation angle
	 * @param characterImage is the image that represent the object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage
	 * 
	 */
	public FollowerEnemy(int xTranslation, int yTranslation, double angle, BufferedImage characterImage){
		super(xTranslation, yTranslation, angle, characterImage);
	}
	
	/**
	 * Constructs the FollowerEnemy object. This constructor is the most common in use.
	 *
	 * @param characterImage is the image that represent the object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage
	 * 
	 */
	public FollowerEnemy(BufferedImage characterImage) {
		super(characterImage);
	}
	
	/**
	 * Constructs the FollowerEnemy object.
	 * 
	 * @param characterImage is the image that represent the object on the panel
	 * @param position is the position of the object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage OR position
	 * 
	 */
	public FollowerEnemy(BufferedImage characterImage, Position position){
		super(characterImage, position);
	}
	
	/**
	 * You can see it in the GameObjects class
	 * 
	 * @see GameObjects.GameObjects
	 */
	@Override
	public void TranslateToTheNewPosition() {
		super.setXTranslation(this.getXTranslation() + 
				 (this.getVelX() * Math.cos(this.getAngle())));  
		super.setYTranslation(this.getYTranslation() + 
				 (this.getVelY() * Math.sin(this.getAngle())));
		this.getPosition().setX(this.getXTranslation());
		this.getPosition().setY(this.getYTranslation());
		
	}
	
	/**
	 * You can see it in the GameObjects class
	 * 
	 * @see GameObjects.GameObjects
	 */
	@Override
	public Rectangle getBounds(){
		return new Rectangle((int)this.getXTranslation(), (int)this.getYTranslation(),
			    this.getCharacterImage().getWidth() -15, this.getCharacterImage().getHeight() -15);
	}
	
	/**
	 * You can see it in the GameObjects class
	 * 
	 * @see GameObjects.GameObjects
	 */
	@Override
	public void resetPosition() {
		// Do nothing 
	}
	
}


