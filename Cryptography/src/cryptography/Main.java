package cryptography;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CaesarCipher cc = new CaesarCipher();
		CaesarBreaker cb = new CaesarBreaker();
		System.out.println(cb.decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
		System.out.println(cc.encryptTwoKeys("Just a test string with lots of eeeeeeeeeeeeeeeees ", 23, 2));
	}

}
