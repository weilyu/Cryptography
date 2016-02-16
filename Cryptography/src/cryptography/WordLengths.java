package cryptography;

import edu.duke.FileResource;

public class WordLengths {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void countWordLengths(FileResource resource, int counts) {
		int countWords = 0;
		for (String word : resource.words()) {
			int len = word.length();
			if (!Character.isAlphabetic(word.charAt(0))) {
				len--;
			}
			if (!Character.isAlphabetic(word.charAt(word.length() - 1))) {
				len--;
			}
			if (len == counts) {
				countWords++;
			}
		}
	}
}
