import java.util.ArrayList;

public class Network {
	ArrayList<Neuron[]> brain = new ArrayList<>();
	int inputWidth;
	int intermediateWidth;
	int numIntermediate;
	int outputWidth;
	float energyOfActivation;
	ArrayList<float[]> elec = new ArrayList<>(); // electrical signals in brain

	/**
	 * Generates Random Neural Network
	 **/
	public Network(int inputWidth, int numIntermediate, int intermediateWidth, int outputWidth,
			float energyOfActivation) {
		this.inputWidth = inputWidth;
		this.numIntermediate = numIntermediate;
		this.intermediateWidth = intermediateWidth;
		this.outputWidth = outputWidth;
		this.energyOfActivation = energyOfActivation;
		brain.add(new Neuron[inputWidth]);
		for (int i = 0; i < numIntermediate; i++) {
			brain.add(new Neuron[intermediateWidth]);
		}

		for (int i = 0; i < brain.size(); i++) {
			Neuron[] level = brain.get(i);
			int nextWidth = 0;
			if (i == brain.size() - 1) {
				nextWidth = outputWidth;
			} else {
				nextWidth = brain.get(i + 1).length;
			}
			for (int n = 0; n < level.length; n++) {
				level[n] = new Neuron(nextWidth);
			}
		}
		buildElec();
	}

	/**
	 * Takes the mother network and mutates it
	 */
	public Network(Network net, int inputWidth, int numIntermediate, int intermediateWidth, int outputWidth,
			float energyOfActivation) {
		this.energyOfActivation = energyOfActivation;
		this.inputWidth = inputWidth;
		this.numIntermediate = numIntermediate;
		this.intermediateWidth = intermediateWidth;
		this.outputWidth = outputWidth;
		this.inputWidth = inputWidth;
		this.numIntermediate = numIntermediate;
		this.intermediateWidth = intermediateWidth;
		this.outputWidth = outputWidth;
		ArrayList<Neuron[]> motherBrain = new ArrayList<>();
		for (int i = 0; i < motherBrain.size(); i++) {
			Neuron[] motherNeurons = motherBrain.get(i);
			Neuron[] nuerons = new Neuron[motherNeurons.length];
			for (int j = 0; j < motherNeurons.length; j++) {
				nuerons[j] = new Neuron(motherNeurons[j]);
			}
			brain.add(new Neuron[motherNeurons.length]);
		}
		buildElec();
	}

	/**
	 * Builds the elec array
	 */
	void buildElec() {
		elec.add(new float[inputWidth]);
		for (int i = 0; i < numIntermediate; i++) {
			elec.add(new float[intermediateWidth]);
		}
		elec.add(new float[outputWidth]);
	}

	void clearElec() {
		for (int i = 0; i < elec.size(); i++) {
			float[] row = elec.get(i);
			for (int n = 0; n < row.length; n++) {
				row[n] = 0;
			}
		}
	}

	/**
	 * Takes Input into Network and Calculates to output
	 */
	public float[] getOutput(float[] input) {
		clearElec();
		float[] output;
		for (int i = 0; i < input.length; i++) {
			elec.get(0)[i] = input[i];
		}
		for (int i = 0; i < brain.size(); i++) {
			Neuron[] row = brain.get(i);
			float[] elecRow = elec.get(i);
			float[] nextElecRow = elec.get(i + 1);
			for (int n = 0; n < elecRow.length; n++) {
				for (int j = 0; j < nextElecRow.length; j++) {
					if (elecRow[n] >= energyOfActivation) {
						nextElecRow[j] += elecRow[n] * row[n].getWeights()[j];
					}
				}
			}
		}
		output = elec.get(elec.size() - 1);
		return output;
	}
}