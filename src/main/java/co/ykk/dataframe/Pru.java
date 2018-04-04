package co.ykk.dataframe;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import co.ykk.dataframe.entities.DataFrame;

public class Pru {

	public static void main(String[] args) {

//		BufferedReader reader = new BufferedReader(
//				new InputStreamReader(Pru.class.getClassLoader().getResourceAsStream("example-files_ex1.csv")));
//
//		reader.lines().forEach(System.out::println);

		if (args.length == 1 && args[0].equals("+")) {
			Scanner in = new Scanner(System.in);
			String line = null;
			List<String> str = new LinkedList<String>();
			List<DataFrame> frames = new LinkedList<DataFrame>(); 
			while(in.hasNextLine()) {
				String s = in.nextLine();
				if (s.trim().isEmpty()) {
					frames.add(DataFrame.parseFromList(str));
					str = new LinkedList<String>();
					System.out.println(frames);
					continue;
				}
				str.add(s);				
			}
			frames.add(DataFrame.parseFromList(str));
			DataFrame all = DataFrame.addAll(frames);
			
			System.out.println(all);
		}
	}

}
