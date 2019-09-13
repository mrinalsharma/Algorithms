package com.mrinal.algos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Anagrams {
	private Map<String, List<String>> anagramBags = new HashMap<>();
	private List<String> words = new ArrayList<>();
	private List<String> sortedWords = new ArrayList<>();

	public Anagrams(List<String> words) {
		this.words = words;
	}

	public boolean isAnagram(String a, String b) {
		Arrays.sort(a.toLowerCase().toCharArray());
		Arrays.sort(b.toLowerCase().toCharArray());
		return a.equalsIgnoreCase(b);
	}

	public void printAnagramsTogether() {
		// Sort the list of Strings
		for (String word : words) {
			char[] caWord = word.toCharArray();
			Arrays.sort(caWord);
			List<String> bag = anagramBags.getOrDefault(new String(caWord), null);
			if (bag == null)
				bag = new ArrayList<>();
			bag.add(word);
			anagramBags.put(new String(caWord), bag);
		}
		for(Entry<String, List<String>>  bag: anagramBags.entrySet())
		{
			System.out.println(bag.getValue());
		}
	}
	
	public static void main(String[] args) {
		String[] wordsArray = {"cat", "dog", "tac", "god", "act"};
		Anagrams anagrams = new Anagrams(Arrays.asList(wordsArray));
		anagrams.printAnagramsTogether();

	}
}
