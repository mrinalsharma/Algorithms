package com.mrinal.algos;

public class StringReverse {

	public StringReverse() {
	}

	public String reverse(char[] sequence) {
		int i = 0;
		int j = sequence.length - 1;
		while (j > i) {
			char temp = sequence[i];
			sequence[i] = sequence[j];
			sequence[j] = temp;
			i++;
			j--;
		}
		return new String(sequence);
	}

	public boolean isPalindrome(char[] sequence) {
		int i = 0;
		int j = sequence.length - 1;
		while (j > i) {
			if (sequence[i] == sequence[j]) {
				i++;
				j--;
			} else
				return false;
		}

		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "Hello World";
		System.out.println(str);
		StringReverse reverseString = new StringReverse();
		System.out.println(reverseString.reverse(str.toCharArray()));
		String palindromeStr = "madam";
		System.out.println("IsPalindrome: " + reverseString.isPalindrome(palindromeStr.toCharArray()));
	}

}
