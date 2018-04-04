package co.ykk.dataframe;

import java.util.*;

public class Tester {

	public static void main(String[] args) {

		int testNum;
		int[] testCases;

		Scanner in = new Scanner(System.in);

		System.out.println("Enter test number");
		testNum = in.nextInt();

		testCases = new int[testNum];

		int i = 0;

		while (in.hasNextInt()) {
			testCases[i] = in.nextInt();
			i++;
		}

		for (Integer t : testCases) {
			if (t != null)
				System.out.println(t.toString());
		}

	}

}