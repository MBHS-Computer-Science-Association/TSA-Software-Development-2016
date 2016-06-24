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
		while (true) {
			n = new Network(2, 2, 1, 2, false);
			float[] in1 = { 0.05f, 0.1f };
			float[] in2 = { 0, 10 };
			float[] z = { 0, 0 };
			float[] ou1 = { .01f, .99f };
			float[] ou2 = { 10f };
			int index = 1;
			System.out.println("*" + n.getOutput(in1)[0] + " " + n.getOutput(in1)[1]);
			while (index++ < 1000) {
				n.backPropagation(in1, ou1);
				// System.out.println("*" + n.getOutput(in1)[0]);
				 n.backPropagation(in2, ou2);
				// System.out.println("*" + n.getOutput(in2)[0]);
				// System.out.println("------------------ " + index);
			}
			while (index++ < 10000) {
				// n.backPropagation(in2, ou2);
			}
			System.out.println("*" + n.getOutput(in1)[0] + " " + n.getOutput(in1)[1]);
			// System.out.println("*" + n.getOutput(in2)[0]);
			if(n.getOutput(in1)[0]>1f) {
				System.exit(1);
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
