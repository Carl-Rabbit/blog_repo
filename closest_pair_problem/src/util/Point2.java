package util;

import java.util.Objects;

public class Point2 {
	public int idx;
	public long x, y;

	public Point2(long x, long y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Check whether the first point is smaller in lexicographical order
	 */
	public static boolean smaller(Point2 p1, Point2 p2) {
		if (p1.x == p2.x) {
			return p1.y < p2.y;
		}
		return p1.x < p2.x;
	}

	/**
	 * Get distance of two points
	 */
	public static double getDis(Point2 p1, Point2 p2) {
		long tmp1 = p1.x - p2.x;
		long tmp2 = p1.y - p2.y;
		return Math.sqrt(tmp1 * tmp1 + tmp2 * tmp2);
	}

	@Override
	public String toString() {
		return "Point{" +
				"idx=" + idx +
				", x=" + x +
				", y=" + y +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point2 point2 = (Point2) o;
		return x == point2.x &&
				y == point2.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}