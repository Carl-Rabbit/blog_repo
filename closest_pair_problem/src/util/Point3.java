package util;

import java.util.Objects;

public class Point3 {
	public int idx;
	public long x, y, z;

	public Point3(long x, long y, long z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Check whether the first point is smaller in lexicographical order
	 */
	public static boolean smaller(Point3 p1, Point3 p2) {
		if (p1.x != p2.x) {
			return p1.x < p2.x;
		} else if (p1.y != p2.y) {
			return p1.y < p2.y;
		}
		return p1.z < p2.z;
	}

	/**
	 * Get distance of two points
	 */
	public static double getDis(Point3 p1, Point3 p2) {
		long tmp1 = p1.x - p2.x;
		long tmp2 = p1.y - p2.y;
		long tmp3 = p1.z - p2.z;
		return Math.sqrt(tmp1 * tmp1 + tmp2 * tmp2 + tmp3 * tmp3);
	}

	@Override
	public String toString() {
		return "Point{" +
				"idx=" + idx +
				", x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Point3 point3 = (Point3) o;
		return x == point3.x &&
				y == point3.y &&
				z == point3.z;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}
}