/**
 * 1 is true, 0 is false
 * 
 * @author CJ Duffee
 *
 */
public class xor {
	static Network n;

	public static void main(String args[]) {
		n = new Network(2, 3, 1, 1);
		float sucessRate = 0;
		int index = 0;
		while (sucessRate < 1) {
			if(index++==10) {
				System.exit(2);
			}
			sucessRate = getSucessRate();
			System.out.println(sucessRate);
			for (int i = 0; i <= 1; i++) {
				for (int k = 0; k <= 1; k++) {
					float[] input = { i, k };
					float[] output = { i ^ k };
					n.backPropagation(input, output);
				}
			}
		}
	}

	static float getSucessRate() {
		float right = 0;
		for (int i = 0; i <= 1; i++) {
			for (int k = 0; k <= 1; k++) {
				float[] input = { i, k };
				float[] output = n.getOutput(input);
				if (output[0] >= 0.5f && (i ^ k) == 1) {
					right++;
				}
			}
		}
		return (float) right / 4;
	}

}
