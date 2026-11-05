package game;

import java.io.Serializable;

public class Item implements Serializable {

	private String name;
	private boolean heavy;
	
	public Item(String n, boolean h) {
		name=n;
		heavy=h;
	}
	
	public void setName(String n) {
		name=n;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isHeavy() {
		return heavy;
	}
	
	public void open() {
		Game.print("You cannot open this Item");
	}
	
	public void close() {
		Game.print("You cannot close this Item");
	}
	
	public void read() {
		Game.print("There is nothing to read");
	}
	
	public void use() {
		Game.print("You cannot use this Item");
	}
	
	public String toString() {
		return name;
	}
}
