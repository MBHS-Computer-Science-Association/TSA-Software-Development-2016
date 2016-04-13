
public class CreatureVector {
	private float x;
	private float y;
	
	/**
	 * Sets the x and y position of the vector
	 * @param x
	 *            The x value of the vector.
	 * @param y
	 *            They y value of the vector.
	 */
	public CreatureVector(float x, float y) {
		setX(x);
		setY(y);
	}

	/**
	 * Adjusts the creature's angle from the @param theta that the neural network toolkit returned
	 */
	public void adjustAngle(float theta) {
//		float magnitude = this.getMagnitude();
//		theta += (float) Math.atan2(y, x);
//		if (theta > 2 * Math.PI) {
//			theta = (float) (theta - (2 * Math.PI));
//		}
//		setX(magnitude * (float) Math.cos(theta));
//		setY(magnitude * (float) Math.sin(theta));
	}

	/**
	 * @return the angle represented by the two-dimensional vector.
	 */
	public float getAngle() {
		return (float) Math.atan2(y, x);
	}
	
// ---------Set and Get methods----------
	public float getMagnitude() {
//		return (float) Math.sqrt(x * x + y * y);
		return 1.0F;
	}

	public float getSquaredMagnitude() 
	{

		return x * x + y * y;
	}
	
	public void setY(float y) 
	{
		this.y = y;
	}

	public void setX(float x) 
	{
		this.x = x;
	}

	public float getX() 
	{
		return x;
	}

	public float getY() 
	{
		return y;
	}

}
