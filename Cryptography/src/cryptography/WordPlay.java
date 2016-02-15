package cryptography;

public class WordPlay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
	}

	public static boolean isVowel(char ch) {
		StringBuilder vowel = new StringBuilder("AEIOUaeiou");
		for (int i = 0; i < vowel.length(); i++) {
			if (ch == vowel.charAt(i)) {
				return true;
			}
		}
		return false;
	}

	public static String replaceVowels(String phrase, char ch) {
		StringBuilder sb = new StringBuilder(phrase);
		StringBuilder newSb = new StringBuilder();
		for (int i = 0; i < sb.length(); i++) {
			if (isVowel(sb.charAt(i))) {
				newSb.append(ch);
			} else {
				newSb.append(sb.charAt(i));
			}
		}
		return newSb.toString();
	}

	public static String emphasize(String phrase, char ch) {
		StringBuilder sb = new StringBuilder(phrase);
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == ch) {
				if (i % 2 == 0) {
					sb.setCharAt(i, '*');
				} else {
					sb.setCharAt(i, '+');
				}
			}
		}
		return sb.toString();

	}
}
