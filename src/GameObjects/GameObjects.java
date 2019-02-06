package GameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Utilities.Position;

/**
 * This class is the super class of all the objects used in this game.
 * 
 * @author FarisAlasmary
 * @version 1.0
 * 
 * @see GameObjects.Player
 * @see GameObjects.Bullet
 * @see GameObjects.FollowerEnemy
 * @see GameObjects.ShooterEnemy
 * 
 */

public class GameObjects {

	private double xTranslation = 0, yTranslation = 0; // translate to this point
	private int velX = 0, velY = 0; // translate by this velocity
	private double angle; // The rotation angle
	private BufferedImage characterImage;
	private Position position = new Position(); // it will be used for bullets
	private Position targetPosition = new Position(); // it will be used for bullets
	private boolean isAlive = true; // once it is launched, it is alive. This is the logic Dude ;-)
	
	/**
	 * Constructs the GameObjects object.
	 * 
	 * @param xTranslation is the x-coordinate of the object 
	 * @param yTranslation is the y-coordinate of the object
	 * @param angle is the rotation angle
	 * @param characterImage is the image that represent the object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage
	 * 
	 */
	public GameObjects(int xTranslation, int yTranslation, double angle, BufferedImage characterImage){
		this.setXTranslation(xTranslation);
		this.setYTranslation(yTranslation);
		this.setAngle(angle);
		this.setPosition(new Position(xTranslation, yTranslation));
		
		if(characterImage != null){
			this.setCharacterImage(characterImage);
		}else{
			throw new NullPointerException("There is no character image!!");
		}
		
	}

	/**
	 * Constructs the GameObjects object. This constructor is the most common in use.
	 *
	 * @param characterImage is the image that represent the object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage
	 * 
	 */
	public GameObjects(BufferedImage characterImage) {
		this.setPosition(new Position(xTranslation, yTranslation));
		if(characterImage != null){
			this.setCharacterImage(characterImage);
		}else{
			throw new NullPointerException("There is no character image!!");
		}
	}
	
	/**
	 * Constructs the GameObjects object.
	 * 
	 * @param characterImage is the image that represent the object on the panel
	 * @param position is the position of the object on the panel
	 * 
	 * @exception NullPointerException if there is no characterImage OR position
	 * 
	 */
	public GameObjects(BufferedImage characterImage, Position position) {
		if(characterImage != null && position != null){
			this.setCharacterImage(characterImage);
			this.setPosition(position);
		}else{
			throw new NullPointerException();
		}
	}
	
	/**
	 * The copy constructor.
	 * 
	 * @param other is the other GameObjects object that we want to make a copy of
	 * 
	 * @exception NullPointerException if there is no GameObjects object
	 * 
	 */
	public GameObjects(GameObjects other){
		this.setAngle(other.getAngle());
		this.setCharacterImage(other.getCharacterImage());
		this.setVelX(other.getVelX());
		this.setVelY(other.getVelY());
		this.setXTranslation(other.getXTranslation());
		this.setYTranslation(other.getYTranslation());
		if(other.getPosition() != null)
			this.setPosition(other.getPosition());
	}
	
	/**
	 * The x-coordinate getter
	 * 
	 * @return It returns the x-coordinate of the object
	 * 
	 */
	public double getXTranslation() {
		return xTranslation;
	}

	/**
	 * The x-coordinate setter
	 */
	public void setXTranslation(double xTranslation) {
		this.xTranslation = xTranslation;
	}
	
	/**
	 * The y-coordinate getter
	 * 
	 * @return It returns the y-coordinate of the object
	 * 
	 */
	public double getYTranslation() {
		return yTranslation;
	}

	/**
	 * The y-coordinate setter
	 */
	public void setYTranslation(double yTranslation) {
		this.yTranslation = yTranslation;
	}

	/**
	 * This is the setter of the position of the object.
	 * 
	 * @param position is simply the new position of the object
	 * 
	 * @exception NullPointerException if there is no position
	 * 
	 * @see Utilities.Position
	 * 
	 */
	public void setPosition(Position position){
		if(position != null){
			this.position = position;
			this.setXTranslation(this.position.getX());
			this.setYTranslation(this.position.getY());
		}else{
			throw new NullPointerException();
		}
	}
	
	/**
	 * The getter of the object position
	 * 
	 * @return It returns the reference of the current object position
	 * 
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * The targetPosition setter
	 * 
	 * @param targetPosition is the position of the target object. Usually it is either a FollowerEnemy or the mousePosition
	 * 
	 */
	public void setTargetPosition(Position targetPosition) {
		if(targetPosition != null)
			this.targetPosition = targetPosition;
		else
			throw new NullPointerException("There is no target position!");
	}
	
	/**
	 * The getter of the target position
	 * 
	 * @return It returns the reference of the current target position
	 * 
	 */
	public Position getTargetPosition() {
		return targetPosition;
	}

	/**
	 * This is the getter of the rotation angle of the GameObjects object
	 * 
	 * @return It returns the angle of rotation 
	 * 
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * The rotation angle setter
	 * 
	 * @param angle is the new angle of the rotation
	 * 
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}

	/**
	 * The getter of the velocity in the x-axis direction
	 * 
	 * @return It returns the velocity in the x-axis direction
	 * 
	 */
	public int getVelX() {
		return velX;
	}

	/**
	 * The setter of the velocity in the x-axis direction
	 * 
	 * @param velX is the new velocity in x-axis direction
	 * 
	 */
	public void setVelX(int velX) {
		this.velX = velX;
	}

	/**
	 * The getter of the velocity in the y-axis direction
	 * 
	 * @return It returns the velocity in the y-axis direction
	 * 
	 */
	public int getVelY() {
		return velY;
	}

	/**
	 * The setter of the velocity in the y-axis direction
	 * 
	 * @param velY is the new velocity in y-axis direction
	 * 
	 */
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public String toString(){
		return "X = " + this.getYTranslation() + ", Y = " + this.getYTranslation();
	}

	/**
	 * The character image getter
	 * 
	 * @return It returns a reference of object of type BufferedImage that contains the character image
	 * 
	 */
	public BufferedImage getCharacterImage() {
		return characterImage;
	}

	/**
	 * The character image setter
	 * 
	 * @param characterImage is a buffered image which will be the new character image
	 * 
	 * @exception NullPointerException if there is no image
	 * 
	 */
	public void setCharacterImage(BufferedImage characterImage){
		if(characterImage != null)
			this.characterImage = characterImage;
		else
			throw new NullPointerException("There is no character image");
	}
	
	/**
	 * It will be invoked to render the object
	 * 
	 * @param g is a reference to an object of type "Graphics"
	 * 
	 * @see java.awt.Graphics
	 * 
	 */
	public void render(Graphics g){
		render(g, this.getPosition());
	}

	/**
	 * It will be invoked to render the object
	 * 
	 * @param g is a reference to an object of type "Graphics"
	 * @param targetPosition is a position of the target object usually the mouse position
	 * 
	 * @see java.awt.Graphics
	 * 
	 */
	public void render(Graphics g, Position targetPosition){
				
		// move to the new position with a certain velocity
		TranslateToTheNewPosition();
		
		// It can be done by substitution directly in Math.atan2() but 
		// I put it here for readability purpose
		double xCharacter = ((this.getCharacterImage().getWidth() / 2) + this.getXTranslation());
		double yCharacter = ((this.getCharacterImage().getHeight() / 2) + this.getYTranslation());
		double xTarget = targetPosition.getX();
		double yTarget = targetPosition.getY();
		
		// rotate the character image by this angle
		AffineTransform at = new AffineTransform();
		this.setAngle(Math.atan2(yTarget - yCharacter, xTarget - xCharacter));
		at.rotate(this.getAngle(), xCharacter, yCharacter);
		at.translate(this.getXTranslation(), this.getYTranslation());
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); // It clarifies the image and make it more smooth  
		g2d.drawImage(getCharacterImage(), at, null);
//		g2d.draw3DRect((int)this.getXTranslation(), (int)this.getYTranslation(), this.getCharacterImage().getWidth(), this.getCharacterImage().getHeight(), true);

		resetPosition(); // if you delete this line, the image will move in a weird behavior

	}
	
	/**
	 * The getter of the GameObject object's boundaries. It will be used to detect the collision
	 *  
	 * @return It returns the bounded rectangle of the object.
	 * 
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)this.getXTranslation(), (int)this.getYTranslation(), this.getCharacterImage().getWidth(), this.getCharacterImage().getHeight());
	}
	
	/**
	 * To change the position of the object to the new one. It is a protected method so it can't be invoked. It can be overridden inside the subclass 
	 */ 
	protected void TranslateToTheNewPosition(){
		this.setXTranslation(this.getXTranslation() + this.getVelX());  
		this.setYTranslation(this.getYTranslation() + this.getVelY());
		this.position.setX(this.getXTranslation());
		this.position.setY(this.getYTranslation());
	}
	
	/**
	 * To reset the position of the object to zero. It is a protected method so it can't be invoked. It can be overridden inside the subclass 
	 */ 
	protected void resetPosition(){
		this.setVelX(0);
		this.setVelY(0);
	}

	/**
	 * The getter of the boolean variable isAlive.
	 * 
	 * @return It returns a boolean that describes the state of the object either it is alive or not.
	 *  This is important when The GameRender is used to decide if this object going to be rendered or not.
	 * 
	 * @see GameEngine.GameRenderer
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * The setter of isAlive boolean.
	 * 
	 * @param isAlive is the new boolean value
	 * 
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	/**
	 * This method can be used to load buffered image and assign that image to a reference.
	 * 
	 * @param FileName is the name of the image inside the folder.
	 * 
	 * @return It returns a reference to a BufferedImage.
	 * 
	 */
	public BufferedImage LoadBufferedImage(String FileName){
		BufferedImage img = null;
		 
		try{
			img = ImageIO.read(this.getClass().getResourceAsStream(FileName));
		}catch(IOException e){
			System.out.println("Error : No image found!!");
		}
 
		return img;
	}	
	
}
