package Utilities;

/**
 * The Position class stores two value : the x-coordinate and the y-coordinate.
 * This class has been implemented in almost the whole game.
 * 
 * @author FarisAlasmary
 * @version 1.0
 *
 */
public class Position {

	private double x;
	private double y;
	
	/**
	 * Constructs the Position object.
	 * 
	 * @param x is the x-coordinate
	 * @param y is the y-coordinate
	 * 
	 */
	public Position(double x, double y){
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Copy constructor.
	 * 
	 * @param other is the object which is wanted to be copied
	 * @exception NullPointerException if there is no other position
	 * 
	 */
	public Position(Position other){
		if(other != null){
			this.setX(other.getX());
			this.setY(other.getY());
		}else{
			throw new NullPointerException("There is no other position!");
		}
	}
	
	/**
	 * This is the No-argument constructor.
	 * The default values are (0, 0) 
	 */
	public Position(){
		this(0, 0);
	}
	
	/**
	 * The x-coordinate getter
	 * 
	 * @return It returns the x-coordinate
	 * 
	 */
	public double getX() {
		return x;
	}

	/**
	 * The x-coordinate setter
	 * 
	 * @param x is the new x-coordinate
	 * 
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * The y-coordinate getter
	 * 
	 * @return It returns the y-coordinate
	 * 
	 */
	public double getY() {
		return y;
	}

	/**
	 * The y-coordinate setter
	 * 
	 * @param y is the new y-coordinate
	 * 
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * This method is used to find the angle between the current position and another one
	 * 
	 * @param other is the other position
	 * 
	 * @return It returns the angle between the two positions
	 * 
	 */
	public double getAngle(Position other){
		if(other != null)
			return Math.atan2(other.getY() - this.getY(), other.getX() - this.getX());
		else
			throw new NullPointerException("There is no other position!");
	}
	
	/**
	 * This method is used to find the angle between two positions
	 * 
	 * @param pos1 is the first position
	 * @param pos2 is the second position
	 * 
	 * @return It returns the angle between the two positions
	 * 
	 */
	public static double getAngle(Position pos1, Position pos2){
		if(pos1 != null && pos2 != null)
			return Math.atan2(pos2.getY() - pos1.getY(), pos2.getX() - pos1.getX());
		else
			throw new NullPointerException("There is no positions!");
	}
	
	public String toString(){
		return "(" + this.getX() + ", " + this.getY() + ")";
	}
	
	/**
	 * It checks if the coordinates are the same
	 */
	public boolean equals(Object o){
		if(o != null){
			if(o instanceof Position){
				Position other = (Position) o;
				if((this.getX() == other.getX()) && (this.getY() == other.getY()))
					return true;
				else
					return false;
			}else{
				throw new ClassCastException("The object is NOT of type Posistion");
			}
		}else{
			throw new NullPointerException("There is no object!!");
		}
	}	
}
