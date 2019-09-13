package com.mrinal.algos;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author msharma
 *
 */
public class BagOfWords {

	HashMap<String, Integer> bag = new HashMap<>();

	File file = null;

	/**
	 * 
	 */
	public BagOfWords() {
		file = new File(getClass().getClassLoader().getResource("Resource/BagOfWords.txt").getFile());
	}

	public void createBag() {
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String word = scanner.next();
				int count = bag.getOrDefault(word.toLowerCase(), 0);
				bag.put(word, ++count);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BagOfWords bagOfWords = new BagOfWords();
		bagOfWords.createBag();

	}

}
