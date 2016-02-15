package cryptography;

import edu.duke.*;

public class CaesarCipher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(encryptTwoKeys(
				"At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
	}

	public static String encrypt(String input, int key) {
		StringBuilder encrypted = new StringBuilder(input);
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
		for (int i = 0; i < encrypted.length(); i++) {
			char currChar = encrypted.charAt(i);
			if (Character.isLowerCase(currChar)) {
				int idx = alphabet.indexOf(Character.toUpperCase(currChar));
				char newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
				encrypted.setCharAt(i, newChar);
			}
			if (Character.isUpperCase(currChar)) {
				int idx = alphabet.indexOf(currChar);
				char newChar = shiftedAlphabet.charAt(idx);
				encrypted.setCharAt(i, newChar);
			}
		}
		return encrypted.toString();
	}

	public static String decrypt(String input, int key) {
		return encrypt(input, 26-key);
	}
	
	public static void testCaesar() {
		FileResource fr = new FileResource();
		String message = fr.asString();
		int key = 1;
		String encrypted = encrypt(message, key);
		System.out.println("key is " + key + "\n" + encrypted);
	}

	public static String encryptTwoKeys(String input, int key1, int key2) {
		StringBuilder result = new StringBuilder(input);
		for (int i = 0; i < input.length(); i++) {
			String thisChar = String.valueOf(input.charAt(i));
			if (i % 2 == 0) {
				char newChar = encrypt(thisChar, key1).charAt(0);
				result.setCharAt(i, newChar);
			} else {
				char newChar = encrypt(thisChar, key2).charAt(0);
				result.setCharAt(i, newChar);
			}
		}
		return result.toString();
	}
}
