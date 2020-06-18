package solve;

import util.Point3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import static util.Point3.getDis;

/**
 * You can call it like this:<br>
 * var solution = new Dim3();<br>
 * var res = solution.solve(new Point3[]{p1, p2, ...});
 */
public class Dim3 {
	private Point3[] px, pz;

	/**
	 * Solve the problem
	 * @param points the point set
	 * @return the point pair with smallest distance
	 */
	public Point3[] solve(Point3[] points) {
		int n = points.length;

		/* before start searching */

		px = new Point3[n];
		pz = new Point3[n];
		System.arraycopy(points, 0, px, 0, n);
		System.arraycopy(points, 0, pz, 0, n);
		Arrays.sort(px, Comparator.comparingLong(o -> o.x));
		Arrays.sort(pz, Comparator.comparingLong(o -> o.z));

		// record the point index in px
		for (int i = 0; i < n; i++) {
			px[i].idx = i;
		}

		/* Search the ans */

		Point3[] res = find(0, n - 1, pz);

		/* output the result by lexicographical order */

		if (Point3.smaller(res[0], res[1])) {
			return new Point3[]{res[0], res[1]};
		} else {
			return new Point3[]{res[1], res[0]};
		}
	}

	/**
	 * Find the pair with smallest distance
	 * @param x1 the left bound of px to find
	 * @param x2 the right bound of px to find (include)
	 * @param pz the arr px[x1:x2] ranked by z
	 * @return the pair expressed in point index
	 */
	private Point3[] find(int x1, int x2, Point3[] pz){
		switch (x2 - x1 + 1) {
			case 2:
				return new Point3[]{px[x1], px[x2]};
			case 3:
				double dis12 = getDis(px[x1], px[x1 + 1]);
				double dis23 = getDis(px[x1 + 1], px[x2]);
				double dis13 = getDis(px[x1], px[x2]);
				if (dis12 < dis23) {
					if (dis12 < dis13) {
						return new Point3[]{px[x1], px[x1 + 1]};
					} else {
						return new Point3[]{px[x1], px[x2]};
					}
				} else {
					if (dis23 < dis13) {
						return new Point3[]{px[x1 + 1], px[x2]};
					} else {
						return new Point3[]{px[x1], px[x2]};
					}
				}
		}

		/* Generate Qx, Rx, Qz, Rz */

		int mi = (x1 + x2) / 2;
		int idx1 = 0;
		int idx2 = 0;
		Point3[] qz = new Point3[mi - x1 + 1];
		Point3[] rz = new Point3[x2 - mi];

		for (Point3 p : pz) {
			if (p.idx <= mi) {
				qz[idx1++] = p;
			} else {
				rz[idx2++] = p;
			}
		}

		/* Search recursively */

		Point3[] left = find(x1, mi, qz);
		Point3[] right = find(mi + 1, x2, rz);

		double dis1 = getDis(left[0], left[1]);
		double dis3 = getDis(right[0], right[1]);
		double delta = Math.min(dis1, dis3);

		/* Find minimum distance in crossing-area pair */

		long x = px[mi].x;

		// Generate S1 and S2
		ArrayList<Point3> s1 = new ArrayList<>();   // record all points in s1
		ArrayList<Long> segRef = new ArrayList<>(); // record which two seg a point in S1 should refer to
		ArrayList<Integer> sl1 = new ArrayList<>(); // location record in s2[j]
		ArrayList<Integer> sl2 = new ArrayList<>(); // location record in s2[j+1]
		HashMap<Long, ArrayList<Point3>> s2 = new HashMap<>();   // record all s2[]
		construct(pz, x, mi, delta, s1, segRef, sl1, sl2, s2);

		Point3[] pairMin = new Point3[2];
		double disMin = delta;

		for (int i = 0; i < s1.size(); i++) {
			Point3 s = s1.get(i);
			long segIdx = segRef.get(i);
			int loc1 = sl1.get(i);
			int loc2 = sl2.get(i);

			ArrayList<Point3> seg;

			// find in seg1
			seg = s2.get(segIdx);
			if (!seg.isEmpty()){
				// look from bottom to top (both sides)
				for (int j = Math.max(0, loc1 - 16); j < seg.size() && j <= loc1 + 16; j++) {
					Point3 st = seg.get(j);
					double tmp = getDis(s, st);
					if (tmp < disMin) {
						disMin = tmp;
						pairMin[0] = s;
						pairMin[1] = st;
					}
				}
			}

			// find in seg2
			seg = s2.get(segIdx + 1);
			if (!seg.isEmpty()) {
				// look from bottom to top (both sides)
				for (int j = Math.max(0, loc2 - 16); j < seg.size() && j <= loc2 + 16; j++) {
					Point3 st = seg.get(j);
					double tmp = getDis(s, st);
					if (tmp < disMin) {
						disMin = tmp;
						pairMin[0] = s;
						pairMin[1] = st;
					}
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

	/**
	 * Construct S1 and S2
	 * @param pz the sorted point arr
	 * @param x the cutting line x = x*
	 * @param idx the index of px[mi]
	 * @param delta min(minimum dis in Q, minimum dis in R)
	 * @param s1 record all points in S1
	 * @param segRef record which two seg a point in S1 should refer to
	 * @param sl1 location record in s2[j]
	 * @param sl2 location record in s2[j+1]
	 * @param s2 record all s2[] and the points in them
	 */
	private static void construct(Point3[] pz, long x, int idx, double delta,
	                              ArrayList<Point3> s1,
	                              ArrayList<Long> segRef,
	                              ArrayList<Integer> sl1,
	                              ArrayList<Integer> sl2,
	                              HashMap<Long, ArrayList<Point3>> s2) {
		// get the start of y to partition
		long yMin = Long.MAX_VALUE;
		for (Point3 p : pz) {
			if (yMin > p.y) {
				yMin = p.y;
			}
		}

		for (Point3 p : pz) {
			if (Math.abs(x - p.x) > delta) {
				continue;
			}

			long no = (long)((p.y - yMin) / delta); // in [no*delta:(no+1)*delta] part
			if (p.idx <= idx) {     // in qx (S1)
				s1.add(p);

				// build seg ref. save the smaller index of S2 segment
				if (no == 0) {
					no = -1;
				} else {
					no = (no - 1) / 2;
				}
				segRef.add(no);

				// build location
				ArrayList<Point3> arr;
				arr = s2.computeIfAbsent(no, k -> new ArrayList<>());
				sl1.add(Math.max(arr.size()-1, 0));
				arr = s2.computeIfAbsent(no+1, k -> new ArrayList<>());
				sl2.add(Math.max(arr.size()-1, 0));

			} else {                // in rx (S2)
				// add it to correct S2[j]
				no = no / 2;
				s2.computeIfAbsent(no, k -> new ArrayList<>()).add(p);
			}
		}
	}
}
