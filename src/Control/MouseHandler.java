package Control;

import java.awt.event.*;
import javax.swing.SwingUtilities;

import Utilities.Position;
import GameEngine.*;
import GameObjects.*;

/**
 * This class is used as a MouseListener to handle the direction of the player object.
 * It takes as two arguments the first one is a reference to an object of type "Position" which is the 
 * mouse position of the object wanted to be rotated. The second argument is a reference to an
 * object of type "GameEngine" which is the heart of the game that gives you full access to
 * other objects used inside the game.
 * 
 * @author FarisAlasmary
 * @version 1.0
 * @see GameObjects.Player
 * 
 */

public class MouseHandler implements MouseListener, MouseMotionListener{

	private Position mousePosition;
	private GameEngine gameEngine;
	
	/**
	 * Constructs a mouse handler to be used as a "MouseListener" for another class
	 * 
	 * @param mousePosition is the mouse's coordinates on the panel
	 * @param gameEngine is the game engine
	 * 
	 * @exception NullPointerException if there is no mousePosition OR no player object
	 * 
	 */
	public MouseHandler(Position mousePosition, GameEngine gameEngine){
		this.setMousePosition(mousePosition);
		this.setGameEngine(gameEngine);
	}

	/**
	 * The getter of the mouse position
	 * 
	 * @return It returns the reference of the mousePosition NOT OF A NEW ONE
	 * 
	 */
	public Position getMousePosition() {
		return mousePosition;
	}

	/**
	 * The setter of the mouse position
	 * 
	 * @param mousePosition
	 * @exception NullPointerException if there is no mousePosition
	 * 
	 */
	public void setMousePosition(Position mousePosition) {
		if(mousePosition != null)
			this.mousePosition = mousePosition;
		else
			throw new NullPointerException("There is no mousePosition");
	}

	/**
	 * The getter of the GameEngine object
	 * 
	 * @return It returns the reference of the GameEngine object
	 * 
	 */
	
	public GameEngine getGameEngine() {
		return gameEngine;
	}

	/**
	 * The setter of the GameEngine object
	 * 
	 * @param gameEngine
	 * @exception NullPointerException if there is no GameEngine object
	 * 
	 */
	public void setGameEngine(GameEngine gameEngine) {
		if(gameEngine != null)
			this.gameEngine = gameEngine;
		else
			throw new NullPointerException("There is no gameEngine!!");
	}

	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void mouseClicked(MouseEvent me){
		if(SwingUtilities.isLeftMouseButton(me)){
			mousePosition.setX(me.getX());
			mousePosition.setY(me.getY());
			
			this.getGameEngine().getClickedMousePosition().setX(me.getX()); // to be use to detect if a CustomedButton clicked
			this.getGameEngine().getClickedMousePosition().setY(me.getY()); // to be use to detect if a CustomedButton clicked
			
			if(this.getGameEngine().getGameRender().getPlayer().isAlive())
				this.getGameEngine().getGameRender().getPlayer().Fire(); // shoot when the left button clicked
		}
	}
	
	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void mouseMoved(MouseEvent me){
		mousePosition.setX(me.getX());
		mousePosition.setY(me.getY());
	}

	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void mouseDragged(MouseEvent me){
		mousePosition.setX(me.getX());
		mousePosition.setY(me.getY());		
	}
	
	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void mouseEntered(MouseEvent me){}
	
	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void mouseExited(MouseEvent me){}
	
	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void mousePressed(MouseEvent me){}
	
	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void mouseReleased(MouseEvent me){}


}
