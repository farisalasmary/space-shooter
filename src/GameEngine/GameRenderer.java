package GameEngine;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.text.Utilities;

import Utilities.CustomizedButton;
import Utilities.Level;
import Utilities.Position;
import GameInterface.Menu;
import GameObjects.*;

/**
 * The GameRender class is used as a renderer for the game.
 * It receives an object of type GameEngine as a parameter of its constructor
 * 
 * @author FarisAlasmary
 * 
 * @version 1.0
 * 
 * @see GameEngine.GameEngine
 * @see Utilities.CustomizedButton
 * @see Utilities.Level
 *
 */

public class GameRenderer {

	private Image healthBar;
	private Image YouWin;
	private Image YouLose;
	private CustomizedButton MenuBT;
	private CustomizedButton NextBT;
	private CustomizedButton RestartBT;
	private Player rocket;
	private BufferedImage explosion = LoadBufferedImage("/images/explosion.png");
	private GameEngine gameEngine;
	private Random rand = new Random();
	private LinkedList<FollowerEnemy> followerEnemies = new LinkedList<FollowerEnemy>(); // the list of enemies of type "Follower"
	private LinkedList<ShooterEnemy> shooterEnemies = new LinkedList<ShooterEnemy>();   // the list of enemies of type "Shooter"
	private final Color dodgerblue3 = new Color(24, 116, 205);
	private boolean isPaused = false; // flag that indicates if the game is finished or not
	
	private Level currentLevel; // See the description of the Level class
	
	private int levelNumber = 1; // the initial level of the game
	private int score = 0; // the initial score
	private int lifeBar = 207; // Most suitable length that is compatible with the health bar image
	
	/**
	 * Constructs the GameRenderer 
	 * 
	 * @param gameEngine is the game engine that is important to connect the game classes together
	 * 
	 */
	public GameRenderer(GameEngine gameEngine){
		this.setGameEngine(gameEngine);
		this.healthBar = LoadImage("/images/healthbar.png"); // load the health bar image
		this.YouWin = LoadImage("/images/youwin.png"); // load you win image
		this.YouLose = LoadImage("/images/youlose.png"); // load you lose image
		
		this.NextBT = new CustomizedButton("/images/next.png");
		this.MenuBT = new CustomizedButton("/images/menubt.png");
		this.RestartBT = new CustomizedButton("/images/restart.png");
		
		this.levelUp(1);
		
		// create the player & give it an initial position
		this.rocket = new Player(LoadBufferedImage("/images/rocket.png"));
		this.rocket.setXTranslation(gameEngine.getScreenSize().getWidth() / 2 -100);
		this.rocket.setYTranslation(gameEngine.getScreenSize().getHeight() / 2 -100);
		
		initializeTheEnemies(); // create all enemies 
	}
	
	/**
	 * To move the player to the next level
	 * 
	 * @param levelNumber is the number of level that the player reaches
	 *  
	 */
	private void levelUp(int levelNumber){
		switch(levelNumber){
	// Level(numberOfFollowerEnemies, velocityOfFollowerEnemies,
	// 		 numberOfShooterEnemies, velocityOfShooterEnemiesBullets)
			case 1:
				this.setLevel(new Level(1, 2, 2, 10));
				break;
			case 2:
				this.setLevel(new Level(2, 4, 3, 15));
				break;
			case 3:
				this.setLevel(new Level(3, 4, 3, 17));
				break;
			case 4:
				this.setLevel(new Level(2, 5, 2, 20));
				break;
			case 5:
				this.setLevel(new Level(4, 6, 3, 23));
				break;
			case 6:
				this.setLevel(new Level(5, 6, 4, 25));
				break;
			case 7:
				this.setLevel(new Level(6, 6, 5, 30)); // The most difficult level in the game
				break;
			default : 
				this.setLevel(new Level(1, 2, 2, 7));
				this.levelNumber = 1;
				break;
		}
	}
	
	/**
	 * It is used to reinitialize the game to start the new level
	 * 
	 */
	public void reinitialize(){
		//if(!gameEngine.getGameLoop().isGameLooping())
		if(this.isPaused()){
			this.followerEnemies.clear(); // remove old enemies
			this.shooterEnemies.clear();  
			this.setPaused(false);	// to run the game
			this.getPlayer().setAlive(true);
			this.initializeTheEnemies();  // create new enemies
			this.lifeBar = 207; // refill the health bar. '207' is the most suitable length that is compatible with the health bar image
		}
	}
	
	private void initializeTheEnemies(){
		// create follower enemies
		FollowerEnemy followerEnemy;
		for(int i = 0; i < currentLevel.getNumberOfFollowerEnemies(); i++){
			followerEnemy = new FollowerEnemy(LoadBufferedImage("/images/followerEnemy.png"));
			followerEnemy.setVelX(currentLevel.getVelocityOfFollowerEnemies()); // set the movement velocity
			followerEnemy.setVelY(currentLevel.getVelocityOfFollowerEnemies()); 
			// set the position of the enemy
			followerEnemy.setXTranslation(this.rand.nextInt((int)gameEngine.getScreenSize().getWidth()  + 30));
			followerEnemy.setYTranslation(this.rand.nextInt((int)gameEngine.getScreenSize().getHeight() + 30));
			followerEnemies.add(followerEnemy); // add the enemy to the list 
		}	
		
		// create shooter enemies
		ShooterEnemy shooterEnemy;
		for(int i = 0; i < currentLevel.getNumberOfShooterEnemies(); i++){
			shooterEnemy = new ShooterEnemy(LoadBufferedImage("/images/shooterEnemy.png"));
			// set the position of the enemy
			shooterEnemy.setXTranslation(this.rand.nextInt((int)gameEngine.getScreenSize().getWidth()));//this.randGen.nextInt((int) this.screenSize.getWidth()));
			shooterEnemy.setYTranslation(this.rand.nextInt((int)gameEngine.getScreenSize().getHeight()));//this.randGen.nextInt((int) this.screenSize.getHeight()));
			shooterEnemy.setBullets(new LinkedList<Bullet>());
			// set the bullet velocity
			shooterEnemy.setBulletVelocity(this.currentLevel.getVelocityOfShooterEnemiesBullets());
			shooterEnemies.add(shooterEnemy); // add the enemy to the list 
		}	
	}
	
	/**
	 * The setter of the game engine
	 * 
	 * @return It returns a reverence to an object of type GameEngine
	 */
	public GameEngine getGameEngine() {
		return gameEngine;
	}

	/**
	 * The getter of the game engine
	 * 
	 * @param gameEngine is the new game engine
	 * 
	 * @exception NullPointerException if there is no game engine
	 * 
	 */
	public void setGameEngine(GameEngine gameEngine) {
		if(gameEngine != null)
			this.gameEngine = gameEngine;
		else
			throw new NullPointerException("There is no game engine!!");
	}

	/**
	 * It is going to render all the game objects
	 * 
	 * @param g is a reference to an object of type Graphics which is the one that is provided by JPanel or JFrame
	 * 
	 * @see java.awt.Graphics 
	 */
	public void renderAll(Graphics g){
		
	//    if(!gameEngine.getGameLoop().isGameLooping()){
		if(this.isPaused()){
		    drawHealthBarAndWinOrLose(g);
	    	return ; // DO NOT DRAW THE REST
	    }
		
	    drawHealthBarAndWinOrLose(g);
	    updateAndRenderShooterEnemies(g);
	    updateAndRenderFollowerEnemies(g);
	    updateAndRenderThePlayer(g);

	}
	
	private void updateAndRenderThePlayer(Graphics g){
		// move the player if he is alive and the game is not finished
	    if(this.getPlayer().isAlive() && !this.isPaused()){
	    	gameEngine.getKeyBoardHandler().MoveThePlayer();
	    	updateBullets(g, gameEngine.getMyMousePosition(), this.getPlayer().getBullets());
	    	this.getPlayer().render(g, gameEngine.getMyMousePosition());
	    }
	}
	
	private void drawHealthBarAndWinOrLose(Graphics g){
	   // Health Bar 
	   g.setColor(dodgerblue3); // new Color(24, 116, 205) and its name is "dodgerblue3"
	   
	   // if the life bar still has some amount then draw it, otherwise mean the player died 
	   if(this.lifeBar > 0){
		   g.fillRect(80, 35, this.lifeBar, 25);
	   }else{
		   this.getPlayer().setAlive(false);
		   this.setPaused(true);
	   }
	   
	   g.drawImage(healthBar, 10, 10, null); // Draw the health bar
	    	
	   // Draw Win OR Lose
	   if(this.isPaused()){
		   this.getPlayer().setAlive(false); // DO NOT DRAW THE PLAYER
		   drawExplosion(g, this.getPlayer());
		   g.drawImage(YouLose, YouLose.getWidth(null) / 2, YouLose.getHeight(null) / 2, null); // show the "You Lose" Image
		   g.drawImage(RestartBT.getImage(), (YouWin.getWidth(null) / 2) + 450, YouWin.getHeight(null) / 2 + 280, null); // next button
		   g.drawImage(MenuBT.getImage(), (YouWin.getWidth(null) / 2) + 100, YouWin.getHeight(null) / 2 + 300, null); // menu button
		   
		   RestartBT.getPosition().setX(YouWin.getWidth(null) / 2 + 450); // to draw the bounded rectangle at the same coordinates
		   RestartBT.getPosition().setY(YouWin.getHeight(null) / 2 + 280);
		   
		   MenuBT.getPosition().setX(YouWin.getWidth(null) / 2 + 100);// to draw the bounded rectangle at the same coordinates
		   MenuBT.getPosition().setY(YouWin.getHeight(null) / 2 + 300);
		   
		   // detect if the button is clicked
		   if(RestartBT.getBounds().intersects(new Rectangle((int)gameEngine.getClickedMousePosition().getX(), (int)gameEngine.getClickedMousePosition().getY(), 5, 5))){
			   
			   // How to level up??
			  // levelUp(++levelNumber); // DO NOT LEVEL HIM UP
			   this.reinitialize();
			   this.setPaused(false);
			   
			// to reset the ClickedMousePosition to use it again when the user clicked again
			   gameEngine.getClickedMousePosition().setX(0);
			   gameEngine.getClickedMousePosition().setY(0);
			   
		   }else if(MenuBT.getBounds().intersects(new Rectangle((int)gameEngine.getClickedMousePosition().getX(), (int)gameEngine.getClickedMousePosition().getY(), 5, 5))){			   
			   // Go back to the main menu
			   disposeAndShowMenu();
			   
			// to reset the ClickedMousePosition to use it again when the user clicked again
			   gameEngine.getClickedMousePosition().setX(0); 
			   gameEngine.getClickedMousePosition().setY(0); 
		   }


	   }else if((this.shooterEnemies.size() == 0) && (this.followerEnemies.size() == 0)){
		   this.getPlayer().setAlive(false); // DO NOT DRAW THE PLAYER
		   g.drawImage(YouWin, YouWin.getWidth(null) / 2, YouWin.getHeight(null) / 2, null); // show the "You Win" Image
		   g.drawImage(NextBT.getImage(), (YouWin.getWidth(null) / 2) + 450, YouWin.getHeight(null) / 2 + 280, null); // next button
		   g.drawImage(MenuBT.getImage(), (YouWin.getWidth(null) / 2) + 100, YouWin.getHeight(null) / 2 + 300, null); // menu button
		   
		   NextBT.getPosition().setX(YouWin.getWidth(null) / 2 + 450); // to draw the bounded rectangle at the same coordinates
		   NextBT.getPosition().setY(YouWin.getHeight(null) / 2 + 280);
		   
		   MenuBT.getPosition().setX(YouWin.getWidth(null) / 2 + 100);// to draw the bounded rectangle at the same coordinates
		   MenuBT.getPosition().setY(YouWin.getHeight(null) / 2 + 300);
		   
		   // detect if the button is clicked
		   if(NextBT.getBounds().intersects(new Rectangle((int)gameEngine.getClickedMousePosition().getX(), (int)gameEngine.getClickedMousePosition().getY(), 5, 5))){
//			   System.out.println("Next");
			   
			   // How to level up??
			   levelUp(++levelNumber);
			   this.setPaused(true); // IT IS IMPORTANT TO DO THIS BEFORE REINITIALIZING 
			   this.reinitialize();
			   this.setPaused(false);
			   
			// to reset the ClickedMousePosition to use it again when the user clicked again
			   gameEngine.getClickedMousePosition().setX(0);
			   gameEngine.getClickedMousePosition().setY(0);
			   
		   }else if(MenuBT.getBounds().intersects(new Rectangle((int)gameEngine.getClickedMousePosition().getX(), (int)gameEngine.getClickedMousePosition().getY(), 5, 5))){
			   
			   // Go back to the main menu
			   disposeAndShowMenu();
			   
			// to reset the ClickedMousePosition to use it again when the user clicked again
			   gameEngine.getClickedMousePosition().setX(0); 
			   gameEngine.getClickedMousePosition().setY(0); 
		   }
	   }	    
	} // end of drawHealthBarAndWinOrLose() method
	
	private void updateAndRenderFollowerEnemies(Graphics g){
		FollowerEnemy followerEnemy;
		for(int i = 0; i < this.followerEnemies.size(); i++){
		    // check if the player collides with any follower enemy
			followerEnemy = followerEnemies.get(i);
			if(followerEnemy.isAlive() && followerEnemy.getBounds().intersects(this.getPlayer().getBounds())){
				this.lifeBar -= 50;
				followerEnemy.setAlive(false); // DO NOT DRAW IT ANY MORE
				this.followerEnemies.remove(i); // remove it from the list
				drawExplosion(g, followerEnemy);
			}
			
			// move the follower enemies if they are alive
			if(followerEnemy.isAlive()) // it takes the position of the player to move toward him
				followerEnemy.render(g, this.getPlayer().getPosition());
			
			// if the player's bullets hit the follower enemies
			for(int j = 0; j < this.getPlayer().getBullets().size(); j++){
				Bullet tempBullet = this.getPlayer().getBullets().get(j);
				if(followerEnemy.getBounds().intersects(tempBullet.getBounds()) && followerEnemy.isAlive()){
					this.getPlayer().getBullets().remove(j);
					followerEnemy.setAlive(false); // DO NOT DRAW IT ANY MORE
					drawExplosion(g, followerEnemy);
					this.followerEnemies.remove(i); // to remove it from the list	
					this.score += 10; // ADD SCORE
				}		
				
			}// The end of the inner loop
			
		} // The end of the outer loop
		
	} // The end of the method

	private void updateAndRenderShooterEnemies(Graphics g){
		ShooterEnemy shooterEnemy;
		for(int i = 0; i < this.shooterEnemies.size(); i++){
			shooterEnemy = this.shooterEnemies.get(i);
			// check if the player collides with any shooter enemy
			if(shooterEnemy.isAlive() && shooterEnemy.getBounds().intersects(this.getPlayer().getBounds())){
				shooterEnemy.setAlive(false); // DO NOT DRAW IT ANY MORE
				this.lifeBar -= 50;
				this.shooterEnemies.remove(i); // to remove it from the list
				drawExplosion(g, shooterEnemy);
			}
			
			// move the shooter enemies if they are alive
			if(shooterEnemy.isAlive())
				shooterEnemy.render(g, this.getPlayer().getPosition());
			
			// if the player's bullets hit any shooter enemy
			for(int j = 0; j < this.getPlayer().getBullets().size(); j++){
				Bullet tempBullet = this.getPlayer().getBullets().get(j);
				if(shooterEnemy.isAlive() && shooterEnemy.getBounds().intersects(tempBullet.getBounds())){
					this.getPlayer().getBullets().remove(j);
					shooterEnemy.setAlive(false); // DO NOT DRAW IT ANY MORE
					drawExplosion(g, shooterEnemy);
					this.shooterEnemies.remove(i); // to remove it from the list
					this.score += 10;
				}
			}
			
			// if shooter enemies' bullets hit the player
			for(int j = 0; j < shooterEnemy.getBullets().size(); j++){
				Bullet tempBullet = shooterEnemy.getBullets().get(j);
		    	updateBullets(g, gameEngine.getMyMousePosition(), shooterEnemy.getBullets()); // move and update shooter bullets
				if(shooterEnemy.isAlive() && this.getPlayer().getBounds().intersects(tempBullet.getBounds())){
					shooterEnemy.getBullets().remove(j);
					this.lifeBar -= 20;
				}
			}		
		}
	}
	
	private void updateBullets(Graphics g, Position mousePosition, LinkedList<Bullet> bullets){
		for(int i = 0; i < bullets.size(); i++){
			Bullet tempBullet = bullets.get(i);
			tempBullet.render(g, mousePosition);
			
			// I've chosen -64 to make sure that the is disappeared from the screen
			if((gameEngine.getScreenSize().getHeight() < tempBullet.getYTranslation()) ||
			   (gameEngine.getScreenSize().getWidth() < tempBullet.getXTranslation())  ||
			   (-64 > tempBullet.getYTranslation() || -64 > tempBullet.getXTranslation()))
				bullets.remove(i);
			//System.out.println("Linked List's size = " + bullets.size());
		}
	}
	
	private void drawExplosion(Graphics g, GameObjects gameObject){
		g.drawImage(this.explosion, (int)gameObject.getXTranslation() - (gameObject.getCharacterImage().getWidth() / 2),
				   (int)gameObject.getYTranslation() - (gameObject.getCharacterImage().getHeight() / 2), null);
	}
	
	/**
	 * It loads an image of type Image
	 * 
	 * @param fileName is the image name in the same folder of the class
	 * 
	 * @return It returns an image of type Image
	 * 
	 * @see Image
	 * 
	 */
	public Image LoadImage(String fileName) {
		return new ImageIcon(this.getClass().getResource(fileName)).getImage();
	}
	
	private BufferedImage LoadBufferedImage(String FileName){
		BufferedImage img = null;
		 
		try{
			img = ImageIO.read(this.getClass().getResourceAsStream(FileName));
		}catch(IOException e){
			System.out.println("Error : No image found!!");
		}
 
		return img;
	}

	/**
	 * The getter of the player instance
	 * 
	 * @return It returns a reference of type player 
	 * 
	 */
	public Player getPlayer() {
		return rocket;
	}

	/**
	 * The setter of the player field
	 * 
	 * @param player is the new player 
	 * 
	 */
	public void setPlayer(Player player) {
		if(player != null)
			this.rocket = player;
		else
			throw new NullPointerException("There is no player object!!");
	}

	/**
	 * The getter of the level instance
	 * 
	 * @return It returns a reference to an object of type Level
	 * 
	 */
	public Level getLevel() {
		return currentLevel;
	}

	/**
	 * The setter of the level field
	 * 
	 * @param currentLevel is the new level
	 * 
	 */
	public void setLevel(Level currentLevel) {
		if(currentLevel != null)
			this.currentLevel = currentLevel;
		else
			throw new NullPointerException("There is no level object!!");
	}

	/**
	 * the getter of isPaused flag. This flag indicates whether the game is finished or not
	 * 
	 * @return isPaused
	 */
	
	public boolean isPaused() {
		return isPaused;
	}

	/**
	 * the setter of isPaused flag
	 * 	
	 * @param isPaused
	 */
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	/**
	 * The getter of the levelNumber field
	 * 
	 * @return It returns the levelNumber that the player reaches
	 * 
	 */
	public int getLevelNumber() {
		return levelNumber;
	}

	/**
	 * The setter of the level field
	 * 
	 * @param levelNumber is the new levelNumber
	 * 
	 */
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	/**
	 * The getter of the score field
	 * 
	 * @return It returns the player score
	 * 
	 */
	public int getScore() {
		return score;
	}

	/**
	 * The setter of the score field
	 * 
	 * @param score is the new score achieved by the player
	 * 
	 */
	public void setScore(int score) {
		this.score = score;
	}

	private void disposeAndShowMenu(){
	
		this.gameEngine.getFrame().dispose(); // dispose the game frame and go to the main menu
		Menu menu = new Menu();
		
		JFrame frame = new JFrame("Star War II");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(menu.getBackGround().getWidth(null), menu.getBackGround().getHeight(null));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.add(menu);
	}

	/**
	 * the getter of the list of enemies of type "Follower"
	 * 
	 * @return followerEnemies
	 */
	public LinkedList<FollowerEnemy> getFollowerEnemies() {
		return followerEnemies;
	}

	/**
	 * the setter of the list of enemies of type "Follower"
	 * 
	 * @param followerEnemies
	 */
	public void setFollowerEnemies(LinkedList<FollowerEnemy> followerEnemies) {
		this.followerEnemies = followerEnemies;
	}

	/**
	 * the getter of the list of enemies of type "Shooter"
	 * 
	 * @return shooterEnemies
	 */
	public LinkedList<ShooterEnemy> getShooterEnemies() {
		return shooterEnemies;
	}

	/**
	 * the setter of the list of enemies of type "Shooter"
	 * 
	 * @param shooterEnemies
	 */
	public void setShooterEnemies(LinkedList<ShooterEnemy> shooterEnemies) {
		this.shooterEnemies = shooterEnemies;
	}
	
}


