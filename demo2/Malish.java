import java.util.Random;

public class Malish extends Animal {
	Random randy = new Random();
	final static float heathDepletion = 0.001f;
	final static float turnSpeed = 0.005f;
	final static float moveSpeed = 0.5f;
	final static int vision = 100;
	Network net;

	/**
	 * Instantiates the neural network toolkit and appends it to the creatures
	 */
	public Malish(float x, float y) {
		net = new Network(inputWidth, hiddenWidth, numHidden, outputWidth);
		super.setX(x);
		super.setY(y);
		super.setHealth(1f);
	}

	public Malish(Malish mother) {
		super.setX(mother.getX());
		super.setY(mother.getY());
		super.adjustAngle((float)Math.PI/4);
		super.setHealth(1f);
	}

	/**
	 * Changes the Malish's condition Ran each Tick Returns True of Dead,
	 * Returns boolean whether creature is dead or alive
	 */
	public boolean move(float[] input) 
	{
		if(health > 0)
		{
			return false;
		}
		return true;
	}
}