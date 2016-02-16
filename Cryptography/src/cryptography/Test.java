package cryptography;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		countNum(100);
	}

	public static void countNum(int times) {
		Random rand = new Random();
		int[] count = new int[13];
		for (int i = 0; i < times; i++) {
			int d1 = rand.nextInt(6) + 1;
			int d2 = rand.nextInt(6) + 1;
			count[d1 + d2] += 1;
		}
		for (int i = 2; i <= 12; i++) {
			System.out.println(i + "'s number is " + count[i]);
		}
	}

}
