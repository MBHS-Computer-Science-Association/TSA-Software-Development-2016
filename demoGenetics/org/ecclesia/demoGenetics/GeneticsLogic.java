package org.ecclesia.demoGenetics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.ecclesia.neural.*;

public class GeneticsLogic {
	Network net;
	float[][] trainer;
	float[][] trainer2;
	
	public GeneticsLogic(){
		net = new Network(10, 5, 1, 1, true);
		trainer = new float[20][10];
		trainer2 = new float[20][10];
	}

	public float[] getStage(float[] seq){
			float[] ans = net.getOutput(seq);
		return ans;
	}
	
	public void train(){
		float[] s = {1.0F};
		float[] a = {0.0F};
		for(int i = 0; i < 100; i++){//
		for(float[] x : trainer){
			net.backPropagation(x, s);
		}
		for(float[] y : trainer2){
			net.backPropagation(y, a);
		}
		}//
	}
	
	
	private void readTrainCases() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("TrainCases.txt"));
		String line;
		int count = 0;
		while((line = br.readLine()) != null){
			
			int counter = 0;
			for(String a : line.split(" ")){
				trainer[count][counter] = Float.valueOf(a);
				counter ++;
			}
			
			count++;
		}
		//////////////////////////////////////
		br = new BufferedReader(new FileReader("TrainCases2.txt"));
		String line2;
		int count2 = 0;
		while((line2 = br.readLine()) != null){
			
			int counter2 = 0;
			for(String a2 : line2.split(" ")){
				trainer2[count2][counter2] = Float.valueOf(a2);
				counter2++;
			}
			
			count2++;
		}
		//System.out.println(Arrays.deepToString(trainer));
		
	}
	
	public static void main(String[] args) throws IOException{
		GeneticsLogic l = new GeneticsLogic();
		//l.readTrainCases();
		
		float[] x = {0.0F, 3.0F, 2.0F, 1.0F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 3.0F};
		System.out.println(Arrays.toString(l.getStage(x)));
		l.train();
		float[] y = {3.0F, 0.0F, 1.0F, 0.0F, 2.0F, 3.0F, 3.0F, 2.0F, 2.0F, 1.0F};
		System.err.println(Arrays.toString(l.getStage(y)));
		float[] z = {0.0F, 3.0F, 2.0F, 1.0F, 2.0F, 0.0F, 1.0F, 1.0F, 1.0F, 3.0F};
		System.out.println(Arrays.toString(l.getStage(z)));

		
	}
}
