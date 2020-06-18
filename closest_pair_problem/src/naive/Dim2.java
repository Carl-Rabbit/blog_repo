package naive;

import util.Point2;

import static util.Point2.getDis;

/**
 * You can call it like this:<br>
 * var res = Dim2.solve(new Point2[]{p1, p2, ...});
 */
public class Dim2 {
	/**
	 * Solve the problem
	 * @param points the point set
	 * @return the point pair with smallest distance
	 */
	public static Point2[] solve(Point2[] points) {
		Point2[] res = new Point2[2];
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

		if (Point2.smaller(res[0], res[1])) {
			return new Point2[]{res[0], res[1]};
		} else {
			return new Point2[]{res[1], res[0]};
		}
	}
}
