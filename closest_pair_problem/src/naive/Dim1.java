package naive;

import java.util.Arrays;

/**
 * You can call it like this:<br>
 * var res = Dim1.solve(new Long[]{p1, p2, ...});
 */
public class Dim1 {
	/**
	 * Solve the problem
	 * @return the point pair with smallest distance
	 */
	public static Long[] solve(Long[] points) {
		int n = points.length;

		Arrays.sort(points);

		long min = Integer.MAX_VALUE;
		int idx = 0;
		for (int i = 0; i < n - 1; i++) {
			long dis = points[i+1] - points[i];
			if (dis < min) {
				min = dis;
				idx = i;
			}
		}

		return new Long[]{points[idx], points[idx + 1]};
	}
}
