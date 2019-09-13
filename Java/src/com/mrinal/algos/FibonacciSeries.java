package com.mrinal.algos;
import java.util.HashMap;
import java.util.Map;

public class FibonacciSeries {
	// List<Integer> series = new ArrayList<>();
	private Map<Double, Double> fibNumberList = new HashMap<>();

	public FibonacciSeries() {
	}

	private double generateFibNumber(double i, boolean dynamic) {
		if (i <= 2) {
			return 1;
		}
		if (dynamic) {
			double fibNumber = fibNumberList.getOrDefault(i, 0.0);
			if (fibNumber != 0.0)
				return fibNumber;
		}
		double val = generateFibNumber(i - 1, dynamic) + generateFibNumber(i - 2, dynamic);
		if (dynamic) {
			fibNumberList.put(i, val);
		}
		return val;
	}

	public void printFibNumber(int n, boolean dynamic) {
		long startTime = System.nanoTime();
		System.out.println(n + "  " + generateFibNumber(n, dynamic) + " ");
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in milliseconds  : " + timeElapsed / 1000000);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FibonacciSeries series = new FibonacciSeries();
		System.out.println("Index" + "  " + "FibNumber" + " ");
		series.printFibNumber(200,true);
	}

}
