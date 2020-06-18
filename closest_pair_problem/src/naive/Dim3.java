package naive;

import util.Point3;

import static util.Point3.getDis;

/**
 * You can call it like this:<br>
 * var res = Dim3.solve(new Point3[]{p1, p2, ...});
 */
public class Dim3 {
	/**
	 * Solve the problem
	 * @param points the point set
	 * @return the point pair with smallest distance
	 */
	public static Point3[] solve(Point3[] points){
		Point3[] res = new Point3[2];
		double min = Double.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < i; j++) {
				double dis = getDis(points[i], points[j]);
				if (dis < min) {
					min = dis;
					res[0] = points[i];
					res[1] = points[j];
				}
			}
		}

		if (Point3.smaller(res[0], res[1])) {
			return new Point3[]{res[0], res[1]};
		} else {
			return new Point3[]{res[1], res[0]};
		}
	}
}
