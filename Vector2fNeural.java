
public class Vector2fNeural {
	private float x;
	private float y;

	/**
	 * 
	 * @param x
	 *            The x value of the vector.
	 * @param y
	 *            They y value of the vector.
	 */
	public Vector2fNeural(float x, float y) {
		setX(x);
		setY(y);
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void adjustAngle(float theta) {
		float magnitude = this.getMagnitude();
		theta += (float) Math.atan2(y, x);
		if (theta > 2 * Math.PI) {
			theta = (float) (theta - (2 * Math.PI));
		}
		setX(magnitude * (float) Math.cos(theta));
		setY(magnitude * (float) Math.sin(theta));
	}

	/**
	 * @return the angle represented by the two-dimensional vector.
	 */
	public float getAngle() {
		return (float) Math.atan2(y, x);
	}

	public float getMagnitude() {
		return (float) Math.sqrt(x * x + y * y);
	}

	public float getSquaredMagnitude() {
		return x * x + y * y;
	}
}
