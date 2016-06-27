package org.ecclesia.demoGenetics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.ecclesia.neural.*;

/**
 * The genetics demo backend API that connects GUI to neural network
 * 
 * @author Sammy Shin
 *
 */
public class GeneticsLogic {

	//the neural network object
	Network net;			
	// the list of nucleotide lists (lists of genes)
	float[][] mutatedSet;
	/**
	 * The default constructor that instantializes the neural network object and list of genes
	 */
	public GeneticsLogic() {
		net = new Network(10, 30, 1, 1, true);
		mutatedSet = new float[20][10];
	}

	/**
	 * The function that calls on the neural network method to return output
	 * 
	 * @param seq
	 * @return
	 */
	public float[] getResult(float[] seq) {
		float[] ans = net.getOutput(seq);
		return ans;
	}

	/**
	 * Reads from a file of mutated genes and trains the network with them as well as the normal gene
	 */
	public void train() {
		float[] mutatedOutput = { 1.0F }; 
		float[] normalOutput = { 0.0F };  
		float[] normalGene = { 0.0F, 3.0F, 2.0F, 1.0F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 3.0F };
		for (int i = 0; i < 100; i++) {
			for (float[] mutatedGene : mutatedSet) {
				net.backPropagation(normalGene, normalOutput);
				net.backPropagation(mutatedGene, mutatedOutput);
			}
		}
	}
	
	/**
	 * The method that reads the mutatedGenes from a file and sets them to the lists of genes or mutatedSet
	 * 
	 * @throws IOException
	 */
	public void readTrainCases() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("demoGenetics/TrainCases.txt"));
		String line;
		int count = 0;
		while ((line = br.readLine()) != null) {

			int counter = 0;
			for (String a : line.split(" ")) {
				mutatedSet[count][counter] = Float.valueOf(a);
				counter++;
			}

			count++;
		}
		br.close();
	}
	
	/**
	 * Changes the received value from the GUI to the proper format and calls the getResult method
	 * @param list
	 * @return
	 */
	public float[] changeList(char[] list){
		float[] data = new float[10];
		for(int i = 0; i < 10; i++){
			if(Character.toLowerCase(list[i]) == 'a'){
				data[i] = 0.0F;
			}
			else if (Character.toLowerCase(list[i]) == 'c' ){
				data[i] = 1.0F;
			}
			else if(Character.toLowerCase(list[i]) == 'g'){
				data[i] = 2.0F;
			}else{
				data[i] = 3.0F;
			}
		}
		System.out.println(Arrays.toString(data));
		return getResult(data);
	}
	
	public void setNetwork(){
		net = new Network(10, 30, 1, 1, true);
	}
	
	// DELETE LATER BECAUS IT NOT GOOD
	public static void main(String[] args) throws IOException {
		GeneticsLogic l = new GeneticsLogic();
		// l.readTrainCases();

		float[] x = { 0.0F, 3.0F, 2.0F, 1.0F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 3.0F };
		System.out.println(Arrays.toString(l.getResult(x)));
		l.train();
		float[] y = { 3.0F, 0.0F, 1.0F, 0.0F, 2.0F, 3.0F, 3.0F, 2.0F, 2.0F, 1.0F };
		System.err.println(Arrays.toString(l.getResult(y)));
		float[] z = { 0.0F, 3.0F, 2.0F, 1.0F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 3.0F };
		System.out.println(Arrays.toString(l.getResult(z)));
		float[] e = { 0.0F, 3.0F, 2.0F, 1.0F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 3.0F };
		System.out.println(Arrays.toString(l.getResult(e)));

	}
}
