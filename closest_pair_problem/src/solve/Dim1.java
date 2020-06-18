package solve;

import java.util.Arrays;

/**
 * You can call it like this:<br>
 * var solution = new Dim1();<br>
 * var res = solution.solve(new Long[]{p1, p2, ...});
 */
public class Dim1 {
	private Long[] points;

	/**
	 * Get distance of two points.
	 * Give the index of two points
	 */
	private long getDis(int idx1, int idx2) {
		return Math.abs(points[idx2] - points[idx1]);
	}

	/**
	 * Solve the problem
	 * @return the point pair with smallest distance
	 */
	public Long[] solve(Long[] points) {
		this.points = points;
		Arrays.sort(points);
		int[] res = find(0, points.length - 1);
		return new Long[]{points[res[0]], points[res[1]]};
	}

	/**
	 * Find the pair with smallest distance
	 * @param lo the lower bound for searching
	 * @param hi the upper bound for searching
	 * @return the index of the point pair
	 */
	private int[] find(int lo, int hi) {
		switch (hi - lo + 1) {
			case 2:
				return new int[]{lo, hi};
			case 3:
				if (getDis(lo, lo + 1) <= getDis(lo + 1, lo + 2)) {
					return new int[]{lo, lo + 1};
				} else {
					return new int[]{lo + 1, lo + 2};
				}
		}

		int mi = (lo + hi) / 2;
		int[] left = find(lo, mi);
		int[] right = find(mi + 1, hi);

		long dis1 = getDis(left[0], left[1]);
		long dis2 = getDis(mi, mi + 1);
		long dis3 = getDis(right[0], right[1]);

		if (dis1 <= dis2 && dis1 <= dis3) {
			return left;
		} else if (dis2 <= dis1 && dis2 <= dis3) {
			return new int[]{mi, mi + 1};
		} else {
			return right;
		}
	}
}
