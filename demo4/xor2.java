import org.ecclesia.neural.Network;

/**
 * 1 is true, 0 is false
 * 
 * @author CJ Duffee
 *
 */
public class xor2 {
	static Network n;

	public static void main(String args[]) {
		n = new Network(2, 2, 1, 1, false);
		float[] in1 = { 0, 1 };
		float[] in2 = { 1, 1 };
		float[] z = {0,0};
		float[] ou1 = { 0f };
		float[] ou2 = { 1f };
		int index = 1;
		System.out.println("*" + n.getOutput(in1)[0]);
		System.out.println("*" + n.getOutput(in2)[0]);
		while (index++ < 75) {
			n.backPropagation(z, ou1);
			// System.out.println("*" + n.getOutput(in1)[0]);
			n.backPropagation(z, ou2);
			// System.out.println("*" + n.getOutput(in2)[0]);
			// System.out.println("------------------ " + index);
		}
		System.out.println("*" + n.getOutput(in1)[0]);
		System.out.println("*" + n.getOutput(in2)[0]);

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
