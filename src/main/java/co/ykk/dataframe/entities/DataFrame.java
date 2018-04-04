package co.ykk.dataframe.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DataFrame {
	
	private Map<String,List<String>> data;
	
	public DataFrame(Map<String, List<String>> data) {
		super();
		this.data = data;
	}


	public static DataFrame parseFromList(List<String> str) {
		 Map<String,List<String>> data = new HashMap<String,List<String>>();  
		String[] columnNames = str.get(0).split(",");
		for (String name : columnNames) {
			data.put(name,new LinkedList<>());
		}
		for (int i = 1; i < str.size(); i++) {
			String[] line = str.get(i).split(",");
			for (int j = 0; j < line.length; j++) {
				data.get(columnNames[j]).add(line[j]);
			}
		}
		return new DataFrame(data);
	}
	
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (Entry<String, List<String>> entry : data.entrySet()) {
			
		}
	}


	public static DataFrame addAll(List<DataFrame> frames) {
		Map<String, List<String>> data = new HashMap<String, List<String>>();
		for (DataFrame dataFrame : frames) {
			data.putAll(dataFrame.getData());
		}
		return new DataFrame(data);
	}


	private Map<String, List<String>> getData() {
		return data;
	}
	
	

}
