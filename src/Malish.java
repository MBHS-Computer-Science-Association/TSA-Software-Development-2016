import java.util.Random;

public class Malish extends Animal {
	Random randy = new Random();
	final static int inputWidth = 4;
	final static int intermediateWidth = 3;
	final static int numIntermediate = 1;
	final static int outputWidth = 3;
	final static float heathDepletion = 0.001f;
	final static float turnSpeed = 1f;
	final static float moveSpeed = 0.5f;
	final static float energyOfActivation = 0.2f;
	/**
	 * Generates Generation 0 Malishes
	 */
	public Malish(float x, float y) {
		net = new Network(inputWidth, intermediateWidth, numIntermediate, outputWidth, energyOfActivation);
		super.setX(x);
		super.setY(y);
		super.setHealth(1f);
	}

	/**
	 * Generates Malishes when there is a mother
	 */
	public Malish(Malish mother) {
		net = new Network(mother.net, inputWidth, intermediateWidth, numIntermediate, outputWidth, energyOfActivation);
		super.setX(mother.getX());
		super.setY(mother.getY());
		super.adjustAngle((float) Math.PI / 4);
		super.setHealth(1f);
	}

	/**
	 * Changes the Malish's condition Ran each Tick Returns True of Dead,
	 * Returns False if Alive
	 */
	public boolean move(float[] input) {
		float[] output = net.getOutput(input); // Turn, Move
		if(output[0] > output[1] && output[0] > output[2]) //left
		{
			super.adjustAngle(-turnSpeed);
		} else if(output[2] > output[1]) //right
		{
			super.adjustAngle(turnSpeed);
		}else // straight 
		{
			
		}
		//super.adjustAngle(output[0] * turnSpeed);
		super.move(true);

		if (super.depleteHealth(heathDepletion)) {
			return true;
		}
		return false;
	}
}