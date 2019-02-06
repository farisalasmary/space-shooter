package Utilities;

/**
 * This class is the game level class. It simply contains the number of enemies, their 
 * velocity, and their bullets' velocity. Every level have different collection of numbers.
 * The higher the number of enemies, the more difficult the game will be and so on for other
 * variables.
 * 
 * @author FarisAlasmary
 * @version 1.0
 *
 */

public class Level {

	private int numberOfFollowerEnemies;
	private int numberOfShooterEnemies;
	private int velocityOfFollowerEnemies;
	private int velocityOfShooterEnemiesBullets;
	
	/**
	 * The constructor of the level class
	 * 
	 * @param numberOfFollowerEnemies
	 * @param velocityOfFollowerEnemies
	 * @param numberOfShooterEnemies
	 * @param velocityOfShooterEnemiesBullets
	 */
	
	public Level(int numberOfFollowerEnemies, int velocityOfFollowerEnemies,
				 int numberOfShooterEnemies, int velocityOfShooterEnemiesBullets){
		this.setNumberOfFollowerEnemies(numberOfFollowerEnemies);
		this.setVelocityOfFollowerEnemies(velocityOfFollowerEnemies);
		this.setNumberOfShooterEnemies(numberOfShooterEnemies);
		this.setVelocityOfShooterEnemiesBullets(velocityOfShooterEnemiesBullets);
	}
	
	/**
	 * Another constructor for the class
	 * 
	 * @param numberOfFollowerEnemies
	 * @param numberOfShooterEnemies
	 */
	
	public Level(int numberOfFollowerEnemies, int numberOfShooterEnemies){
		this(numberOfFollowerEnemies, 1, numberOfShooterEnemies, 10);
	}

	/**
	 * The copy constructor 
	 * 
	 * @param other
	 */
	public Level(Level other){
		this.setNumberOfFollowerEnemies(other.getNumberOfFollowerEnemies());
		this.setNumberOfShooterEnemies(other.getNumberOfShooterEnemies());
		this.setVelocityOfFollowerEnemies(other.getVelocityOfFollowerEnemies());
		this.setVelocityOfShooterEnemiesBullets(other.getVelocityOfShooterEnemiesBullets());
	}
	
	/**
	 * The empty constructor	
	 */
	public Level(){
		this(1, 1, 1, 10);
	}
	
	/**
	 * The getter of follower enemies number
	 * 
	 * @return the number of follower enemies 
	 */
	public int getNumberOfFollowerEnemies() {
		return numberOfFollowerEnemies;
	}

	/**
	 * The setter of the follower enemies
	 * 
	 * @param numberOfFollowerEnemies
	 * 
	 * @exception IllegalArgumentException if the number of enemies is negative 
	 */
	public void setNumberOfFollowerEnemies(int numberOfFollowerEnemies) {
		if(numberOfFollowerEnemies >= 0)
			this.numberOfFollowerEnemies = numberOfFollowerEnemies;
		else 
			throw new IllegalArgumentException("The number of follower enemies should be >= 0");
	}

	/**
	 * The getter of shooter enemies number
	 * 
	 * @return the number of shooter enemies 
	 */
	public int getNumberOfShooterEnemies() {
		return numberOfShooterEnemies;
	}

	/**
	 * The setter of the shooter enemies
	 * 
	 * @param numberOfShooterEnemies
	 * 
	 * @exception IllegalArgumentException if the number of enemies is negative 
	 */
	public void setNumberOfShooterEnemies(int numberOfShooterEnemies) {
		if(numberOfShooterEnemies >= 0)
			this.numberOfShooterEnemies = numberOfShooterEnemies;
		else
			throw new IllegalArgumentException("The number of shooter enemies should be >= 0");
	}

	/**
	 * The getter of the follower enemies velocity
	 * 
	 * @return velocity of follower enemies
	 */
	public int getVelocityOfFollowerEnemies() {
		return velocityOfFollowerEnemies;
	}

	/**
	 * The setter of the follower enemies velocity
	 * 
	 * @param velocityOfFollowerEnemies
	 * 
	 * @exception IllegalArgumentException if the velocity of enemies is negative
	 */
	public void setVelocityOfFollowerEnemies(int velocityOfFollowerEnemies) {
		if(velocityOfFollowerEnemies >= 0)
			this.velocityOfFollowerEnemies = velocityOfFollowerEnemies;
		else
			throw new IllegalArgumentException("The velocity of follower enemies should be >= 0");
	}

	/**
	 * The getter of shooters' bullets velocity 
	 * 
	 * @return velocity of shooter enemies bullets
	 */
	public int getVelocityOfShooterEnemiesBullets() {
		return velocityOfShooterEnemiesBullets;
	}

	/**
	 * The setter of shooters' bullets velocity 
	 * 
	 * @param velocityOfShooterEnemiesBullets
	 * 
	 * @exception IllegalArgumentException if the velocity of bullets is negative
	 */
	public void setVelocityOfShooterEnemiesBullets(int velocityOfShooterEnemiesBullets) {
		if(velocityOfShooterEnemiesBullets >= 0)
			this.velocityOfShooterEnemiesBullets = velocityOfShooterEnemiesBullets;
		else
			throw new IllegalArgumentException("The velocity of shooter enemies' bullets should be >= 0");
	}

	/**
	 * 
	 * toString method
	 * 
	 * @return It returns a string containing info about its fields 
	 */
	@Override
	public String toString() {
		return "Level [numberOfFollowerEnemies=" + numberOfFollowerEnemies
				+ ", numberOfShooterEnemies=" + numberOfShooterEnemies
				+ ", velocityOfFollowerEnemies=" + velocityOfFollowerEnemies
				+ ", velocityOfShooterEnemiesBullets="
				+ velocityOfShooterEnemiesBullets + "]";
	}

	/** 
	 * It checks if every thing is the same as that of the other level
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Level other = (Level) obj;
		if (numberOfFollowerEnemies != other.numberOfFollowerEnemies)
			return false;
		if (numberOfShooterEnemies != other.numberOfShooterEnemies)
			return false;
		if (velocityOfFollowerEnemies != other.velocityOfFollowerEnemies)
			return false;
		if (velocityOfShooterEnemiesBullets != other.velocityOfShooterEnemiesBullets)
			return false;
		return true;
	}
	
	
	
}
