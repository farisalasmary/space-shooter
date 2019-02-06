package GameObjects;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Utilities.Position;

/**
 * This is the class of shooter enemies who are the ones shooting at the player 
 * 
 * @author FarisAlasmary
 * @version 1.0
 *
 * @see GameObjects.GameObjects
 * 
 */

public class ShooterEnemy extends GameObjects implements Runnable{
	
	private BufferedImage enemyBullet = this.LoadBufferedImage("/images/enemyBullet.png");
	private LinkedList<Bullet> bullets;
	private int shootingDelay = 1000; // Shot a bullet every "delay" millisecond
	private int bulletVelocity = 10; // 

	/**
	 * Constructs the ShooterEnemy object.
	 * 
	 * @param xTranslation is the x-coordinate of the object 
	 * @param yTranslation is the y-coordinate of the object
	 * @param angle is the rotation angle
	 * @param characterImage is the image that represent the object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage
	 * 
	 */
	public ShooterEnemy(int xTranslation, int yTranslation, double angle, BufferedImage characterImage){
		super(xTranslation, yTranslation, angle, characterImage);
		Thread bulletsThread = new Thread(this);
		bulletsThread.start();
	}
	
	/**
	 * Constructs the ShooterEnemy object. This constructor is the most common in use.
	 *
	 * @param characterImage is the image that represent the object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage
	 * 
	 */
	public ShooterEnemy(BufferedImage characterImage) {
		super(characterImage);
		Thread bulletsThread = new Thread(this);
		bulletsThread.start();
	}
	
	/**
	 * Constructs the ShooterEnemy object.
	 * 
	 * @param characterImage is the image that represent the object on the panel
	 * @param position is the position of the object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage OR position
	 * 
	 */
	public ShooterEnemy(BufferedImage characterImage, Position position){
		super(characterImage, position);
		Thread bulletsThread = new Thread(this);
		bulletsThread.start();
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
		double xCharacter = (this.getCharacterImage().getWidth() / 2) + this.getXTranslation();
		double yCharacter = (this.getCharacterImage().getHeight() / 2) + this.getYTranslation();
		double xTarget = targetPosition.getX();
		double yTarget = targetPosition.getY();
//		System.out.println(this.getPosition());
		
		// rotate the character image by this angle
		AffineTransform at = new AffineTransform();
		this.setAngle(Math.atan2(yTarget - yCharacter, xTarget - xCharacter));
		//at.rotate(this.getAngle(), xCharacter, yCharacter);
		at.translate(this.getXTranslation(), this.getYTranslation());
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); // It clarifies the image and make it more smooth  
		g2d.drawImage(getCharacterImage(), at, null);
//		g2d.draw3DRect((int)this.getXTranslation(), (int)this.getYTranslation(), this.getCharacterImage().getWidth(), this.getCharacterImage().getHeight(), true);


		resetPosition(); // if you delete this line, the image will move in a weird behavior
		//	System.out.println("xMouse = " + xMouse + " ,yMouse = " + yMouse + " ,xCharacter = " + xCharacter + " ,yCharacter = " + yCharacter);

	}
	
	/**
	 * You can see it in the GameObjects class
	 * 
	 * @see GameObjects.GameObjects
	 */
	@Override
	public void TranslateToTheNewPosition() {
		// Do nothing 
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

	/**
	 * The getter of the shooter enemy's bullets.
	 * 
	 * @return It returns a reference to a linked list of type "Bullet" which will be used as shooter enemy's bullets.
	 *
	 */
	public LinkedList<Bullet> getBullets() {
		return bullets;
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
		this.bullets = bullets;
	}
	
	/**
	 * DO NOT INVOKE. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void run() {
		// Shoot every "delay" millisecond 
		while(this.isAlive()){
			
			try {
				Thread.sleep(shootingDelay);
			} catch (InterruptedException e) {
				System.out.println("The ShooterEnemy has been interrupted!");
				e.printStackTrace();
			}	
			
			EnemyBullets tempBullet = new EnemyBullets(this.enemyBullet, new Position(this.getXTranslation() + 20, this.getYTranslation()));
			tempBullet.setVelocity(bulletVelocity);
			tempBullet.setPlayer(new Player(this));
			bullets.add(tempBullet);

		}
	}

	public int getShootingDelay() {
		return shootingDelay;
	}

	public void setShootingDelay(int shootingDelay) {
		if(shootingDelay > 0)
			this.shootingDelay = shootingDelay;
		else
			throw new IllegalArgumentException("Delay time should be > 0");
	}

	public int getBulletVelocity() {
		return bulletVelocity;
	}

	public void setBulletVelocity(int bulletVelocity) {
		if(bulletVelocity > 0)
			this.bulletVelocity = bulletVelocity;
		else
			throw new IllegalArgumentException("Bullets  should be > 0");
	}
		
}
