package co.ykk.dataframe;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import co.ykk.dataframe.entities.DataFrame;

public class QS {

	public static void main(String[] args) {
		if (args.length == 1 && args[0].equals("+")) {
			Scanner in = new Scanner(System.in);
			String line = null;
			List<String> str = new LinkedList<String>();
			List<DataFrame> frames = new LinkedList<DataFrame>();
			while (in.hasNextLine()) {
				String s = in.nextLine();
				if (s.startsWith(",")) {
					if (!str.isEmpty()) {
						frames.add(DataFrame.parseFromList(str));
						str = new LinkedList<String>();
					}
				}
				if (s.trim().isEmpty())
					continue;
				str.add(s);
			}
			frames.add(DataFrame.parseFromList(str));

			DataFrame composite = DataFrame.sumAll(frames);

			System.out.println(composite.toString());

		}
	}

}
