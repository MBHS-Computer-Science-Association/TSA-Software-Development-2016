import java.util.Random;

public class Neuron {
	float[] weights;
	static final float mutationRate = 0.05f;
	static Random rand = new Random();

	public float[] getWeights() {
		return weights;
	}

	/**
	 * Constructor that generates random weights between -2 and 2
	 * for this neuron
	 * 
	 * @param width
	 */
	public Neuron(int width) {
		weights = new float[width];
		for (int i = 0; i < width; i++) {
			weights[i] = rand.nextFloat() * 2 * (rand.nextBoolean() ? 1 : -1);
		}
	}

	/**
	 * Takes mother neuron weights and can possibly mutate it based 
	 * on a generic mutation
	 * 
	 * @param mother
	 */
	public Neuron(Neuron mother) {
		float[] motherWeights = mother.getWeights();
		weights = new float[motherWeights.length];
		for (int i = 0; i < motherWeights.length; i++) {
			weights[i] = motherWeights.length;
		}
		if (rand.nextFloat() <= mutationRate) {
			int mutatedIndex = rand.nextInt(weights.length);
			weights[mutatedIndex] = rand.nextFloat() * 2 * (rand.nextBoolean() ? 1 : -1);
		}
	}
}
