import util.Point2;
import util.Point3;

import java.util.Random;

public class ClosestPointTest2 {
	public static final boolean INFINITE = true;
	public static final int TEST = 1000;
	public static final int MAX_N = 1000;
	public static final int LOWER = -1000000;
	public static final int UPPER = 1000000;

	public static void main(String[] args) {
//		TestD1();
		TestD2();
//		TestD3();
	}

	private static void TestD1() {
		Random rand = new Random();

		for (int t = 0; t < TEST; t += INFINITE ? 0 : 1) {

			/* Generate data */

			int n = rand.nextInt(MAX_N - 1) + 2;
			Long[] data = new Long[n];
			for (int i = 0; i < n; i++) {
				data[i] = Math.abs(rand.nextLong()) % (UPPER - LOWER + 1) + LOWER;
			}

			/* Test */

			var res11 = (new solve.Dim1()).solve(data);
			var res12 = naive.Dim1.solve(data);

			if (res11[1] - res11[0] != res12[1] - res12[0]) {
				System.err.println("Test failed");
				System.exit(-1);
			}

			System.out.println("Test passed");
		}

		System.out.println("All tests Passed");
	}

	private static void TestD2() {
		Random rand = new Random();

		for (int t = 0; t < TEST; t += INFINITE ? 0 : 1) {

			/* Generate data */

			int n = rand.nextInt(MAX_N - 1) + 2;
			Point2[] data = new Point2[n];
			for (int i = 0; i < n; i++) {
				long x = Math.abs(rand.nextLong()) % (UPPER - LOWER + 1) + LOWER;
				long y = Math.abs(rand.nextLong()) % (UPPER - LOWER + 1) + LOWER;
				data[i] = new Point2(x, y);
			}

			/* Test */

			var res21 = (new solve.Dim2()).solve(data);
			var res22 = naive.Dim2.solve(data);

			double diff = Point2.getDis(res21[0], res21[1]) - Point2.getDis(res22[0], res22[1]);
			if (Math.abs(diff) > 0.000_0000_0001) {
				System.err.println("Test failed");
				System.exit(-1);
			}

			System.out.println("Test passed");
		}

		System.out.println("All tests Passed");
	}

	private static void TestD3() {
		Random rand = new Random();

		for (int t = 0; t < TEST; t += INFINITE ? 0 : 1) {

			/* Generate data */

			int n = rand.nextInt(MAX_N - 1) + 2;
			Point3[] data = new Point3[n];
			for (int i = 0; i < n; i++) {
				long x = Math.abs(rand.nextLong()) % (UPPER - LOWER + 1) + LOWER;
				long y = Math.abs(rand.nextLong()) % (UPPER - LOWER + 1) + LOWER;
				long z = Math.abs(rand.nextLong()) % (UPPER - LOWER + 1) + LOWER;
				data[i] = new Point3(x, y, z);
			}

			/* Test */

			var res31 = (new solve.Dim3()).solve(data);
			var res32 = naive.Dim3.solve(data);

			double diff = Point3.getDis(res31[0], res31[1]) - Point3.getDis(res32[0], res32[1]);
			if (Math.abs(diff) > 0.000_0000_0001) {
				System.err.println("Test failed");
				System.exit(-1);
			}

			System.out.println("Test passed");
		}

		System.out.println("All tests Passed");
	}
}