package org.ecclesia.neural.util;

import java.util.Random;

/**
 * Creates random weights for the network to use
 * 
 * @author Sammy Shin
 *
 */
public class WeightRandomizer {

	Random random = new Random();
	
	
	/**
	 * a util to return random weight for neural width indicated
	 * @param neuralWidth
	 * @return
	 */
	public float[] randomizer(int neuralWidth)
	{
		float[] output = new float[neuralWidth];
		
		for(int i = 0; i < output.length; i++)
		output[i] = random.nextFloat();
		
		return output;
	}
	
}
