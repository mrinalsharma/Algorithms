package com.mrinal.algos;

import java.util.Arrays;

public class CountTheTriplets {
	public static void main(String[] args) {
		int[] arr = new int[] { 1};
		Arrays.sort(arr);
		int tripletCount = 0;
		int n = arr.length;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				int sumIndex = Arrays.binarySearch(arr, arr[i]+arr[j]);
				if(sumIndex > 0)
				{
					tripletCount++;
				}

			}
		}
		System.out.println("Triplet Count : "+  tripletCount);
	}
}
