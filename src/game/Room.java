package game;

import java.io.Serializable;
import java.util.*;

public class Room implements Serializable{
	
	private String name;
	private boolean locked;
	private Room[] arr;
	private HashMap<String,Item> items=new HashMap<>();
	private NPC npc;
	
	public Room(String n) {
		locked=false;
		name=n;
		arr= new Room[6];
		World.rooms.add(this);
	}
	
	public void addNPC(NPC n) {
		npc=n;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void setLock(boolean t) {
		locked=t;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescriptInameion() {
		return Game.descriptions.get(name);
	}
	
	public void setName(String n) {
		name=n;
	}
	
	public void setDescription(String d) {
		Game.descriptions.put(name,d);
	}
	
	public void addExit(Room r, int n) {
		arr[n]=r;
	}
	
	public Room getExit(int n) {
		return arr[n];
	}
	
	public Item getItem(String name) {
		return items.get(name);
	}
	
	public void addItem(Item i) {
		items.put(i.getName(), i);
	}
	
	public void removeItem(String name) {
		items.remove(name);
	}
	
	public NPC getNPC() {
		return npc;
	}
	
	public String toString() {
		return name+":\n\t"+Game.descriptions.get(name);
	}

}
