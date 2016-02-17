package cryptography;

import edu.duke.FileResource;

//figure out the most common word length in a file
public class WordLengths {

	public int[] countWordLengths(FileResource resource, int[] counts) {
		int countsLength = counts.length;
		for (String word : resource.words()) {
			int len = singleWordLength(word);
			if (len < countsLength) {
				counts[len]++;
			} else {
				counts[countsLength - 1]++;
			}
		}
		return counts;
	}

	public void testCountWordLengths() {
		FileResource fr = new FileResource();
		int[] counts = new int[31];
		counts = countWordLengths(fr, counts);
		System.out.println(indexOfMax(counts));
		
	}

	//return the actual length of a single word
	public int singleWordLength(String word) {
		int len = word.length();
		if (!Character.isLetter(word.charAt(0))) {
			len--;
		}
		if (!Character.isLetter(word.charAt(word.length() - 1))) {
			len--;
		}
		if (len <= 0) {
			return 0;
		} else {
			return len;
		}
		
	}

	// return the index position of the largest element in values
	public int indexOfMax(int[] values) {
		int largestValue = 0;
		int idx= -1;
		for (int i = 0; i < values.length; i++) {
			int currentValue = values[i];
			if (currentValue > largestValue) {
				largestValue = currentValue;
				idx = i;
			}
		}
		return idx;
	}
}
