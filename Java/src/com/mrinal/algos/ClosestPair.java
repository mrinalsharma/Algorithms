package com.mrinal.algos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author msharma
 *
 */
public class ClosestPair {
	private double delta = 0;

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public float getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public float getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Float.floatToIntBits(x);
			result = prime * result + Float.floatToIntBits(y);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
				return false;
			if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}

	public Map<Integer, Point> findClosestPair(List<Point> points) {
		// sort the array by X axis and Y axis and store in separate arrays
		List<Point> xSortedPoints = points;
		List<Point> ySortedPoints = new ArrayList<Point>(points);
		xSortedPoints.sort((p1, p2) -> (int) (p1.getX() - p2.getX()));
		ySortedPoints.sort((p1, p2) -> (int) (p1.getY() - p2.getY()));
		return closestPair(xSortedPoints, ySortedPoints);
	}

	public Map<Integer, Point> bruteForceClosestPair(List<Point> points) {
		double shortestDistance = -1;
		Map<Integer, Point> closestPoints = new HashMap<>();
		if (points.size() < 2)
			return closestPoints;
		for (int i = 0; i < points.size(); i++) {
			// System.out.println("Point I:" + points.get(i).x);
			for (int j = i + 1; j < points.size(); j++) {
				// System.out.println("Point J:" + points.get(j).x);
				double dist = Math.sqrt(Math.pow((points.get(i).x - points.get(j).x), 2)
						+ Math.pow((points.get(j).y - points.get(j).y), 2));
				if (shortestDistance == -1 || dist < shortestDistance) {
					shortestDistance = dist;
					closestPoints.clear();
					closestPoints.put(new Integer(1), points.get(i));
					closestPoints.put(new Integer(2), points.get(j));
				}
			}
		}
		// System.out.println("Closest Points" + closestPoints.toString());
		return closestPoints;
	}

	public double distance(Point a, Point b) {
		return Math.sqrt(Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2));
	}

	private Map<Integer, Point> closestPair(List<Point> rx, List<Point> ry) {
		if (rx.size() <= 4) {
			return bruteForceClosestPair(rx);
		}
		List<Point> leftArrayXSorted = rx.subList(0, ((rx.size() / 2)));
		double xDivMarker = leftArrayXSorted.get(leftArrayXSorted.size() - 1).getX();
		List<Point> leftArrayYSorted = ry.stream().filter(point -> point.getX() <= xDivMarker)
				.collect(Collectors.toList());
		Map<Integer, Point> leftClosestPoint = closestPair(leftArrayXSorted, leftArrayYSorted);
		List<Point> rightArrayXSorted = rx.subList(((rx.size() / 2)), rx.size());
		List<Point> rightArrayYSorted = ry.stream().filter(point -> point.getX() > xDivMarker)
				.collect(Collectors.toList());
		Map<Integer, Point> rightClosestPoint = closestPair(rightArrayXSorted, rightArrayYSorted);
		delta = Math.min(distance(leftClosestPoint.get(1), leftClosestPoint.get(2)),
				distance(rightClosestPoint.get(1), rightClosestPoint.get(2)));
		Map<Integer, Point> splitClosestPoint = closestPairSplit(delta, xDivMarker, rx, ry);
		Map<Integer, Point> smaller = null;
		if (leftClosestPoint.size() == 0 && rightClosestPoint.size() != 0)
			smaller = rightClosestPoint;
		else if (leftClosestPoint.size() != 0 && rightClosestPoint.size() == 0)
			smaller = leftClosestPoint;
		else if (distance(leftClosestPoint.get(1), leftClosestPoint.get(2)) < distance(rightClosestPoint.get(1),
				rightClosestPoint.get(2))) {
			smaller = leftClosestPoint;
		} else
			smaller = rightClosestPoint;
		if (splitClosestPoint.size() != 0) {
			if (distance(smaller.get(1), smaller.get(2)) < distance(splitClosestPoint.get(1),
					splitClosestPoint.get(2))) {
				return smaller;
			} else
				return splitClosestPoint;
		} else
			return smaller;

	}

	private Map<Integer, Point> closestPairSplit(double delta, double xDivMarker, List<Point> rx, List<Point> ry) {
		Map<Integer, Point> shortestDistancePoints = new HashMap<>();
		List<Point> S = ry.stream()
				.filter(point -> (point.getX() >= xDivMarker - delta) && (point.getX() <= xDivMarker + delta))
				.collect(Collectors.toList());
		if (S.size() < 2)
			return shortestDistancePoints;
		double shortestDistance = Double.MAX_VALUE;
		for (int i = 0; i < S.size(); i++) {
			for (int j = Math.max(0, i - 8); j < Math.min(S.size()-1, i + 8); j++) {
				// distance = Math.min(distance(S.get(i), S.get(j)),distance);
				if ((i!=j) && distance(S.get(i), S.get(j)) < shortestDistance) {
					shortestDistance = distance(S.get(i), S.get(j));
					shortestDistancePoints.clear();
					shortestDistancePoints.put(new Integer(1), S.get(i));
					shortestDistancePoints.put(new Integer(2), S.get(j));
				}
			}
		}
		return shortestDistancePoints;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Point> points = new ArrayList<>();
		/*
		 * int P[][] = { { 2, 3 }, { 12, 30 }, { 40, 50 }, { 5, 1 }, { 12, 10 }, { 3, 4
		 * } }; for (int i = 0; i < P.length; i++) { int val[] = P[i]; points.add(new
		 * Point(val[0], val[1])); }
		 */
		Random r = new Random();
	    for (int i = 0; i < 1000000; i++)
	      points.add(new Point(r.nextInt(), r.nextInt()));
		ClosestPair closestPair = new ClosestPair();
		long startTime = System.nanoTime();
		Map<Integer, Point> closestPoints = closestPair.findClosestPair(points);
		long endTime = System.nanoTime();
		System.out.println("Closest points: " + closestPair.findClosestPair(points) + "Distance = "
				+ closestPair.distance(closestPoints.get(1), closestPoints.get(2)));
		// get difference of two nanoTime values
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in milliseconds  : " + timeElapsed / 1000000);
	}

}
