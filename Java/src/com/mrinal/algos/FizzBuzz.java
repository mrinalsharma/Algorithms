package com.mrinal.algos;

public class FizzBuzz {
	public static void main(String[] args) {
		for (int i = 1; i < 101; i++) {
			if (i % 3 == 0 && i % 5 == 0)
				System.out.println("FizzBuzz");
			if (i % 3 == 0)
				System.out.println("Fizz");
			if (i % 5 == 0)
				System.out.println("Buzz");
			else
				System.out.println(i);
		}
	}
}
