package cryptography;

public class CaesarBreaker {

	String alph = "abcdefghijklmnopqrstuvwxyz";

	// count the frequency of letters
	public int[] countLetters(String message) {
		int[] counts = new int[26];
		for (int i = 0; i < message.length(); i++) {
			char ch = Character.toLowerCase(message.charAt(i));
			int idx = alph.indexOf(ch);
			if (idx != -1) {
				counts[idx]++;
			}
		}
		return counts;
	}

	// decrypt one key code
	public String decrypt(String encrypted) {
		CaesarCipher cc = new CaesarCipher();
		int[] freq = countLetters(encrypted);
		int maxDex = maxIndex(freq);
		int dkey = maxDex - 4;
		if (maxDex < 4) {
			dkey = 26 - (4 - maxDex);
		}
		return cc.encrypt(encrypted, 26 - dkey);
	}

	public int maxIndex(int[] counts) {
		int largestValue = 0;
		int idx = -1;
		for (int i = 0; i < counts.length; i++) {
			int currentValue = counts[i];
			if (currentValue > largestValue) {
				largestValue = currentValue;
				idx = i;
			}
		}
		return idx;
	}

	// return a new String that is every other character from message starting
	// with the start position
	public String halfOfString(String message, int start) {
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < message.length(); i += 2) {
			sb.append(message.charAt(i));
		}
		return sb.toString();
	}

	// get an array of the letter frequencies in String s and then use maxIndex
	// to calculate the index of the largest letter frequency
	public int getKey(String message) {
		int[] freq = countLetters(message);
		return maxIndex(freq);
	}
	
	public String decryptTwoKeys(String encrypted) {
		String s1 = halfOfString(encrypted, 0);
		String s2 = halfOfString(encrypted, 1);
		System.out.println("key 1 is " + getKey(s1));
		System.out.println("key 2 is " + getKey(s2));
		StringBuilder result = new StringBuilder(encrypted);
		String ds1 = decrypt(s1);
		String ds2 = decrypt(s2);
		for (int i = 0; i < ds1.length(); i++) {
			result.setCharAt(i * 2, ds1.charAt(i));
		}
		for (int i = 0; i < ds2.length(); i++) {
			result.setCharAt(i * 2 + 1, ds2.charAt(i));
		}
		return result.toString();
	}
}
