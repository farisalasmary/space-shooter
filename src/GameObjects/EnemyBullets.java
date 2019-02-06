package GameObjects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Utilities.Position;

/**
 * This class is to deal with enemies' bullets.
 * 
 * @author FarisAlasmary
 * @version 1.0
 * 
 * @see GameObjects.Bullet
 * 
 */

public class EnemyBullets extends Bullet {
	
	private final int BOUNDS = 50;
	
	/**
	 * Constructs the EnemyBullets object. This is the most common in use
	 * 
	 * @param bulletImage is the image of the bullet 
	 * @exception NullPointerException if there is no bulletImage 
	 *  
	 */
	public EnemyBullets(BufferedImage bulletImage){
		super(bulletImage);
	}
	
	/**
	 * Constructs the EnemyBullets object.
	 * 
	 * @param bulletImage is the image of the bullet 
	 * @param targetPosition is the destination 
	 * 
	 * @exception NullPointerException if there is no bulletImage OR targetPosition
	 * 
	 */
	public EnemyBullets(BufferedImage bulletImage, Position targetPosition){
		super(bulletImage, targetPosition);
	}
	
	/**
	 * Constructs the EnemyBullets object.
	 * 
	 * @param bulletImage is the image of the bullet 
	 * @param targetPosition is the destination 
	 * @param player to take the rotation angle from and other stuffs
	 * 
	 * @exception NullPointerException if there is no bulletImage OR targetPosition OR player
	 * 
	 */
	public EnemyBullets(BufferedImage bulletImage, Position targetPosition, Player player){
		super(bulletImage, targetPosition, player);
	}
	
	/**
	 * You can see it in the GameObjects class
	 * 
	 * @see GameObjects.GameObjects
	 * @see GameObjects.Bullet
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)this.getXTranslation() + (this.BOUNDS / 2), (int)this.getYTranslation() + (this.BOUNDS / 2), this.getCharacterImage().getWidth(), this.getCharacterImage().getHeight());
	}
}
