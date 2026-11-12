package game;

import java.util.ArrayList;

public class World {
	
	public static ArrayList<Room> rooms = new ArrayList<>();
	
	public static Room buildWorld() {
		Room livingRoom = new Room("Living Room");
		Room office = new Room("Office");
		Room kitchen= new Room("Kitchen");
		Room bedroom= new Room("Bedroom");
		Room attic= new Room("Attic");
		Room garage= new Room("Garage");
		
		Item officeKey= new OfficeKey();
		Item tool= new Tool();
		Item musicBox = new Item("musicbox",false);
		Item flashLight = new Item("flashlight",false);
		Item orb = new Item("mysteriousorb",false);
		Item Knife = new Item("knife",false);
		Item couch = new Item("couch",true);
		Item bed=new Item("bed",true);
		Item safe=new Safe();
		
		bedroom.addExit(attic, 4);
		bedroom.addExit(livingRoom, 3); 
		livingRoom.addExit(kitchen, 0);
		livingRoom.addExit(office, 1);
		livingRoom.addExit(bedroom, 2);
		office.addExit(livingRoom, 0);
		attic.addExit(bedroom, 3);
		kitchen.addExit(livingRoom, 1);
		kitchen.addExit(garage,3);
		garage.addExit(kitchen, 2);
		
		bedroom.addItem(officeKey);
		bedroom.addItem(bed);
		livingRoom.addItem(tool);
		livingRoom.addItem(musicBox);
		livingRoom.addItem(couch);
		kitchen.addItem(Knife);
		kitchen.addItem(flashLight);
		kitchen.addItem(orb);
		attic.addItem(safe);
		
		NPC gunther = new Gunther();
		
		livingRoom.addNPC(gunther);
		
		office.setLock(true);
		attic.setLock(true);
		garage.setLock(true);
		
		
		
		return livingRoom;
	}
	

}
