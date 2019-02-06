package Utilities;

import java.awt.*;

import javax.swing.*;

/**
 * This is a customized button that will be used in the game
 * 
 * @author FarisAlasmary
 * @version 1.0
 *
 */
public class CustomizedButton {

	private Image btImage;
	private Position position;
	
	/**
	 * This is the constructor of the class
	 * 
	 * @param fileName the image file name that is going to be load as the button background
	 * @param position the coordinates of the button to be drawn at
	 */
	
	public CustomizedButton(String fileName, Position position){
		this.setImage(LoadImage(fileName));
		this.setPosition(position);
	}
	
	/**
	 * This is another constructor
	 * 
	 * @param fileName the image file name that is going to be load as the button background
	 */
	public CustomizedButton(String fileName){
		this.setImage(LoadImage(fileName));
		this.setPosition(new Position());
	}

	private Image LoadImage(String fileName) {
		return new ImageIcon(this.getClass().getResource(fileName)).getImage();
	}
 
	/**
	 * The getter of the image
	 * 
	 * @return it returns the image used in the button
	 */
	
	public Image getImage() {
		return btImage;
	}

	/**
	 * The setter of the image
	 * 
	 * @param btImage
	 * 
	 * @exception NullPointerException if there is no image
	 */
	public void setImage(Image btImage) {
		if(btImage != null)
			this.btImage = btImage;
		else
			throw new NullPointerException("There is no image!!");
	}
	
	/**
	 * The image bounds that is essential to detect the mouse click
	 * 
	 * @return it returns a rectangle that represents the bounds
	 */
	public Rectangle getBounds(){
		return new Rectangle((int)this.getPosition().getX(), (int)this.getPosition().getY(), this.getImage().getWidth(null), this.getImage().getHeight(null));
	}

	/**
	 * The getter of the button position
	 * 
	 * @return it returns the position of the button
	 * 
	 * @see Utilities.Position
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * The setter of the button position 
	 * 
	 * @param position
	 * 
	 * @exception NullPointerException if there is no position 
	 */
	public void setPosition(Position position) {
		if(position != null)
			this.position = position;
		else
			throw new NullPointerException("There is no position!!");

	}
	
}
