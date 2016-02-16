package cryptographyOO;

import edu.duke.FileResource;

public class TestCaesarCipher {
	private String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private int[] countLetters(String message) {
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

	private int maxIndex(int[] counts) {
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

	public void simpleTests() {
		FileResource fr = new FileResource();
		String input = fr.asString();
		CaesarCipher cc = new CaesarCipher(18);
		String encrypted = cc.encrypt(input);
		System.out.println(encrypted);
		String decrypted = cc.decrypt(input);
		System.out.println(decrypted);
		String broken = breakCaesarCiper(encrypted);
		System.out.println(broken);
	}

	public String breakCaesarCiper(String input) {
		int[] freq = countLetters(input);
		int maxDex = maxIndex(freq);
		int dkey = maxDex - 4;
		if (maxDex < 4) {
			dkey = 26 - (4 - maxDex);
		}
		CaesarCipher cc = new CaesarCipher(dkey);
		return cc.decrypt(input);
	}


}
