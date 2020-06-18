package solve;

import util.Point2;

import java.util.Arrays;
import java.util.Comparator;

import static util.Point2.getDis;

/**
 * You can call it like this:<br>
 * var solution = new Dim2();<br>
 * var res = solution.solve(new Point2[]{p1, p2, ...});
 */
public class Dim2 {
	private Point2[] px, py;
	private Point2[] sy;    // save sy to reuse it in all sub procedure

	/**
	 * Solve the problem
	 * @param points the point set
	 * @return the point pair with smallest distance
	 */
	public Point2[] solve(Point2[] points) {
		int n = points.length;

		/* before start searching */

		px = new Point2[n];
		py = new Point2[n];
		System.arraycopy(points, 0, px, 0, n);
		System.arraycopy(points, 0, py, 0, n);
		Arrays.sort(px, Comparator.comparingLong(o -> o.x));
		Arrays.sort(py, Comparator.comparingLong(o -> o.y));

		sy = new Point2[n]; // the point in S, ranked by y

		// record the point index in px
		for (int i = 0; i < n; i++) {
			px[i].idx = i;
		}

		/* Search the ans */

		Point2[] res = find(0, n - 1, py);

		/* output the result by lexicographical order */

		if (Point2.smaller(res[0], res[1])) {
			return new Point2[]{res[0], res[1]};
		} else {
			return new Point2[]{res[1], res[0]};
		}
	}

	/**
	 * Find the pair with smallest distance
	 * @param x1 the left bound of px to find
	 * @param x2 the right bound of px to find (include)
	 * @param py the arr px[x1:x2] ranked by y
	 * @return the pair expressed in point index
	 */
	private Point2[] find(int x1, int x2, Point2[] py){
		switch (x2 - x1 + 1) {
			case 2:
				return new Point2[]{px[x1], px[x2]};
			case 3:
				double dis12 = getDis(px[x1], px[x1 + 1]);
				double dis23 = getDis(px[x1 + 1], px[x2]);
				double dis13 = getDis(px[x1], px[x2]);
				if (dis12 < dis23) {
					if (dis12 < dis13) {
						return new Point2[]{px[x1], px[x1 + 1]};
					} else {
						return new Point2[]{px[x1], px[x2]};
					}
				} else {
					if (dis23 < dis13) {
						return new Point2[]{px[x1 + 1], px[x2]};
					} else {
						return new Point2[]{px[x1], px[x2]};
					}
				}
		}

		/* Generate Qx, Rx, Qy, Ry */

		int mi = (x1 + x2) / 2;
		int idx1 = 0;
		int idx2 = 0;
		Point2[] qy = new Point2[mi - x1 + 1];
		Point2[] ry = new Point2[x2 - mi];

		for (Point2 p : py) {
			if (p.idx <= mi) {
				qy[idx1++] = p;
			} else {
				ry[idx2++] = p;
			}
		}

		/* Search recursively */

		Point2[] left = find(x1, mi, qy);
		Point2[] right = find(mi + 1, x2, ry);

		double dis1 = getDis(left[0], left[1]);
		double dis3 = getDis(right[0], right[1]);
		double delta = Math.min(dis1, dis3);

		/* Find minimum distance in crossing-area pair */

		// Generate S
		long x = px[mi].x;
		int cnt = 0;
		for (Point2 p : py) {
			if (x - delta <= p.x && p.x <= x + delta) {
				sy[cnt++] = p;
			}
		}

		Point2[] pairMin = new Point2[2];
		double disMin = delta;

		for (int i = 0; i < cnt; i++) {
			Point2 syi = sy[i];
			Point2 syj;

			// check one side
			for (int j = i + 1; j < cnt && j <= i + 7; j++) {
				syj = sy[j];
				double tmp = getDis(syi, syj);
				if (disMin > tmp) {
					disMin = tmp;
					pairMin[0] = syi;
					pairMin[1] = syj;
				}
			}
		}

		/* Compare and return */

		if (pairMin[0] != null) {
			return pairMin;
		} else if (dis1 < dis3) {
			return left;
		} else {
			return right;
		}
	}
}
