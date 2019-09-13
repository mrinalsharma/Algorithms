package com.mrinal.algos;
import java.util.Arrays;

public class SubarrayWithGivenSum {
	public void findSubarrayWithGIvenSum(Integer[] array, int sum) {
		int i = 0;
		int j = 1;
		while (i < array.length && j <= array.length) {
			int incrementalSum = Arrays.stream(Arrays.copyOfRange(array, i, j)).mapToInt(Integer::intValue).sum();
			if (incrementalSum < sum) {
				if (j != array.length)
					j++;
			} else if (incrementalSum > sum) {
				i++;
			} else {
				System.out.println(Arrays.toString(Arrays.copyOfRange(array, i, j)));
				j++;
			}
		}
	}

	public static void main(String[] args) {
		Integer[] array = { 1, 3, 4, 5, 1, 2, 3, 1, 2, 2, 3,1,1,4 };
		SubarrayWithGivenSum subarrayWithGivenSum = new SubarrayWithGivenSum();
		subarrayWithGivenSum.findSubarrayWithGIvenSum(array, 5);
	}

}
