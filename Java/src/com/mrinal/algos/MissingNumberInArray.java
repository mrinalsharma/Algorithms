package com.mrinal.algos;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MissingNumberInArray {
	public static void main(String[] args) {
		int N = 20;
		Integer[] array = new Integer[N];
		Random random = new Random();
		int skip = random.nextInt(N);

		for (int i = 0; i < N; i++) {
			if (i != skip)
				array[i] = i+1;
			else
				array[i] = 0;
		}
		List<Integer> randomArray = Arrays.asList(array);
		Collections.shuffle(randomArray);
		System.out.println("Print Original Array: " + randomArray);
		System.out.println("Skip : " + (skip+1));
		int sumOfAllTheElements = (N * (N + 1)) / 2;

		// find missing number
		for (Integer val : randomArray) {
			sumOfAllTheElements = sumOfAllTheElements - val;
		}
		System.out.println("Missing Value: " + sumOfAllTheElements);

	}
}
