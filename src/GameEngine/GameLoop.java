package GameEngine;

/**
 * This class keeps the game running.
 * 
 * @author FarisAlasmary
 * @version 1.0
 *
 */
public class GameLoop implements Runnable{

	private final int TARGET_FPS = 60; // A 60-frame game
	private final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	private boolean isGameLooping; // the loop flag which determines that the game should loop or finish
	private int lastFpsTime;
	private GameEngine gameEngine;
	
	/**
	 * Constructs the GameLoop object.
	 * 
	 * @param gameEngine is the game engine object
	 * @param gameLoop is a boolean that determine if the game should start looping or not. Most of the time it 
	 * will be true but if the user exit the game it will be set to false to stop the thread.
	 * 
	 * @see GameEngine.GameEngine
	 * 
	 */
	public GameLoop(GameEngine gameEngine, boolean gameLoop){
		this.setGameEngine(gameEngine);
		this.setGameLooping(gameLoop);
	}
	
	/**
	 * Constructs the GameLoop object.
	 * 
	 * @param gameEngine is the game engine
	 * 
	 */
	public GameLoop(GameEngine gameEngine){
		this.setGameEngine(gameEngine);
		this.setGameLooping(false);
	}
	
	/**
	 * DO NOT INVOKE IT. THE JVM WILL TAKE CARE OF IT
	 */
	@Override
	public void run() {
		
		// keep looping round till the game ends	
		while(isGameLooping){
			gameLoop();
		}
	}
	
	private void gameLoop(){
		
		long lastLoopTime = System.nanoTime();
		
	      // check how long its been since the last update, this
	      // will be used to calculate how far the entities should
	      // move this loop
	      long now = System.nanoTime();
	      long updateLength = now - lastLoopTime;
	      lastLoopTime = now;

	      // update the frame counter
	      lastFpsTime += updateLength;
		      
		  // update our FPS counter if a second has passed since
		  // we last recorded
		  if (lastFpsTime >= 1000000000){
			  lastFpsTime = 0;
		  }
		      		      
		  // draw everything
		  this.getGameEngine().repaint();
		      
		  // we want each frame to take 10 milliseconds, to do this
		  // we've recorded when we started the frame. We add 10 milliseconds
		  // to this and then factor in the current time to give 
		  // us our final value to wait for
		  // remember this is in ms, whereas our lastLoopTime etc. vars are in ns.
		  try{
			  Thread.sleep(Math.abs(lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
		  }catch(InterruptedException e){
		   	  System.err.println("The game loop has been Interrupted!");
		  }catch(IllegalArgumentException e){
		   	  e.printStackTrace();
		  }catch(Exception e){
		   	  System.err.println("An error has occured inside \"gameLoop()\" method");
		  } 
	} // the end of "gameLoop()" method

	/**
	 * The getter of the game engine instance
	 * @return It returns a reference to an object of type 
	 * 
	 */
	public GameEngine getGameEngine() {
		return gameEngine;
	}

	/**
	 * The setter of the game engine instance
	 */
	public void setGameEngine(GameEngine gameEngine) {
		if(gameEngine != null)
			this.gameEngine = gameEngine;
		else
			throw new NullPointerException("There is no GameEngine!!");
	}
	
	/**
	 * the getter of the game loop flag
	 * 
	 * @return isGameLooping 
	 * 
	 */
	public boolean isGameLooping(){
		return this.isGameLooping;
	}
	
	/**
	 * the setter of the game loop flag
	 * 
	 * @param gameLoop
	 * 
	 */
	public void setGameLooping(boolean gameLoop){
		this.isGameLooping = gameLoop;
	}

}
