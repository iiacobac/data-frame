package co.ykk.dataframe.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DataFrame {
	
		
	private Map<String,List<Item>> rows;
	private Map<String,List<Item>> cols;
	
	/**
	 * DataFrame private constructor.
	 * 
	 * @param cols
	 * @param rows
	 */
	private DataFrame(Map<String, List<Item>> cols, Map<String, List<Item>> rows) {
		this.cols = cols;
		this.rows = rows;
	}

	public static DataFrame parseFromList(List<String> str) {
		Map<String,List<Item>> cols = new HashMap<String,List<Item>>();
		Map<String,List<Item>> rows = new HashMap<String,List<Item>>();

		String[] columnNames = str.get(0).split(",");
		for (int i = 1; i < columnNames.length; i++) {
			cols.put(columnNames[i], new LinkedList<Item>());
		}
		for (int i = 1; i < str.size(); i++) {
			List<Item> row = new LinkedList<Item>();
			String[] line = str.get(i).split(",");
			for (int j = 1; j < line.length; j++) {
				 
				Item item = new Item(line[0], columnNames[j], line[j].trim().isEmpty()?0.0:Double.parseDouble(line[j]));
				row.add(item);
				cols.get(columnNames[j]).add(item);
			}
			rows.put(line[0], row);
		}		
		return new DataFrame(cols, rows); 
	}
	
	public Map<String, List<Item>> getRows() {
		return rows;
	}

	public Map<String, List<Item>> getCols() {
		return cols;
	}

	public static DataFrame sumAll(List<DataFrame> frames) {
		Map<String,List<Item>> rows = new HashMap<String,List<Item>>();
		Map<String,List<Item>> cols = new HashMap<String,List<Item>>();
		
		for (DataFrame dataFrame : frames) {
			addEntriesToMap(dataFrame.getCols(), cols);
			addEntriesToMap(dataFrame.getRows(), rows);
		}
		System.out.println(cols);
		System.out.println(rows);
		return new DataFrame(cols, rows);
	}

	private static void addEntriesToMap(Map<String,List<Item>> single, Map<String, List<Item>> composite) {
		for (Entry<String, List<Item>> entry: single.entrySet()) {
			if (composite.containsKey(entry.getKey())) { 
				List<Item> items = composite.get(entry.getKey());
				for (Item item : entry.getValue()) {
					if (items.contains(item)) {
						for (Item item2: items) { 
							if (item.equals(item2)) {
								items.remove(item2);
								items.add(Item.add(item,item2));
								break;
							}					
						}
					} else {
						items.add(item);	
					}
				}
			} else {
				composite.put(entry.getKey(), new LinkedList<Item>(entry.getValue()));
			}					
		}
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		for (String column : cols.keySet()) {
			buffer.append(",\"" + column + "\"");
		}
		buffer.append("\n");
		for (Entry<String, List<Item>> entry : rows.entrySet()) {
			buffer.append("\"" + entry.getKey() + "\"");			
			for (String column : cols.keySet()) {
				List<Item> rows = entry.getValue();
				buffer.append("," + getValueOrZero(rows, entry.getKey(), column));
			}
			buffer.append("\n");
		}
		return buffer.toString();
	
	}
	
	private double getValueOrZero(List<Item> list, String row, String column) {
		for (Item item : list) {
			if (row.equals(item.getRow()) && column.equals(item.getColumn())) 
				return item.getValue();
			
		} 
		return 0.0;
	}

	private static class Item {
		
		private String row;
		private String column;
		private double value;

		public Item(String row, String column, double value) {
			this.row = row;
			this.column = column;
			this.value = value;
		}

		public static Item add(Item item, Item item2) {
			return new Item(item.getRow(), item.getColumn(), item.getValue() + item2.getValue());
		}

		public String getRow() {
			return row;
		}

		public String getColumn() {
			return column;
		}

		public double getValue() {
			return value;
		}		
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Item) {
				Item o = ((Item)obj);
				return o.getRow().equals(this.getRow()) && o.getColumn().equals(this.getColumn());
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return (this.getRow() + "_" + this.getColumn()).hashCode();
		}
		
		@Override
		public String toString() {
			return "(" + row + ", " + column + ") ==> " + value;
		}
	
	}
	
}

















