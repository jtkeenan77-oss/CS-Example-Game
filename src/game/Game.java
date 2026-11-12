
package game;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.SwingUtilities;

public class Game {

    public static Room currentRoom = World.buildWorld();
    public static ArrayList<Item> inventory = new ArrayList<>();
    public static HashMap<String, String> descriptions = new HashMap<>();
    private static StringBuilder response = new StringBuilder();

    private static TextAdventureGUI gui;
    
    public static void saveGame() {

    	try {
    	File saveFile = new File("save0.sav"); 
    	saveFile.createNewFile();
    	ObjectOutputStream outStream = new ObjectOutputStream(new
    			FileOutputStream(saveFile));
    	outStream.writeObject(currentRoom);
    	outStream.writeObject(inventory);
    	outStream.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public static void loadGame() {

    	
    	try {
    	File saveFile = new File("save0.sav");
    	ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(saveFile));
    	currentRoom=(Room) inStream.readObject();
    	inventory=(ArrayList<Item>) inStream.readObject();
    	inStream.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public static void print(String s) {
    	response.append(s);
    }
    
    public static String processCommand(String input) {
    	response.delete(0, response.length());


        if (input == null || input.trim().isEmpty()) {
            return "Enter a command.";
        }

        String[] thing = input.toLowerCase().trim().split("\\s+");
        String command = thing[0];

        if (command.equals("go")) {
            if (thing.length < 2) {
                return "Go where?";
            }
            command = thing[1];
        }

        switch (command) {
            case "up":
            case "down":
            case "east":
            case "west":
            case "north":
            case "south":
                response.append(move(command));
                break;

            case "take":
                if (thing.length < 2) {
                    response.append("Take what?");
                } else if (currentRoom.getItem(thing[1]) != null) {
                    response.append(takeItem(thing[1]));
                } else {
                    response.append("There is no ").append(thing[1]);
                }
                break;

            case "i":
                response.append("You are carrying ").append(inventory);
                break;
                
            case "save":
            	saveGame();
            	response.append("Game saved");
            	break;
            
            case "load":
            	loadGame();
            	response.append("Game loaded");
            	break;
            	
            case "use":
            	boolean looped=false;
            	command=thing[1];
            	for(Item i: inventory) {
            		if(command.equals(i.getName())) {
            			looped=true;
            			i.use();
            			break;
            		}
            	}
            	if(looped=true)
            		break;
            	response.append("You do not have a "+command);
            	break;
            	
            case "read":
            	boolean loooped=false;
            	if(thing.length<2) {
            		Game.print("read what? ");
            	}
            	else {
            		for(int i=0; i<inventory.size();i++) {
            			if(inventory.get(i).getName().equals(thing[1])) {
            				inventory.get(i).read();
            				looped=true;
            			}
            		}
            		if(loooped==false) {
            			Game.print("There is no "+thing[1]);
            		}
            		
            	}
            	break;
            	
            case "open":
            	if(thing.length<2)
            		Game.print("open what? ");
            	else {
            		if(currentRoom.getItem(thing[1])!=null) {
            			currentRoom.getItem(thing[1]).open();
            		}
            	}
            	break;
            	
            case "close":
            	if(thing.length<2)
            		Game.print("close what? ");
            	else {
            		if(currentRoom.getItem(thing[1])!=null) {
            			currentRoom.getItem(thing[1]).close();
            		}
            	}
            	break;
            	
            case "talk":
            	if(thing.length<2)
            		Game.print("talk to who? ");
            	else if(thing.length==2) {
            		Game.print("Talk to what?");
            	}
            	else if(currentRoom.getNPC()==null) {
            		Game.print("There are no people here");
            	}
            	else {
            		if(currentRoom.getNPC()!=null) {
            			if(currentRoom.getNPC().getName().equals(thing[2])) {
            				currentRoom.getNPC().talk();
            			}
            			else {
            				Game.print("There is nobody here named that");
            			}
            		}
            	}
            	break;
            

            case "exit":
                response.append("Farewell");
                break;

            default:
                response.append("Error: Unknown command.");
        }

        return response.toString();
    }

    public static String move(String s) {
        if (s.equals("east") && currentRoom.getExit(0) != null) {
        	if(currentRoom.getExit(0).isLocked()) {
        		return "That door is locked";
        	}
            currentRoom = currentRoom.getExit(0);
            return "You go east.";
        } else if (s.equals("west") && currentRoom.getExit(1) != null) {
        	if(currentRoom.getExit(1).isLocked()) {
        		return "That door is locked";
        	}
            currentRoom = currentRoom.getExit(1);
            return "You go west.";
        } else if (s.equals("north") && currentRoom.getExit(2) != null) {
        	if(currentRoom.getExit(2).isLocked()) {
        		return "That door is locked";
        	}
            currentRoom = currentRoom.getExit(2);
            return "You go north.";
        } else if (s.equals("south") && currentRoom.getExit(3) != null) {
        	if(currentRoom.getExit(3).isLocked()) {
        		return "That door is locked";
        	}
            currentRoom = currentRoom.getExit(3);
            return "You go south.";
        } else if (s.equals("up") && currentRoom.getExit(4) != null) {
        	if(currentRoom.getExit(4).isLocked()) {
        		return "That door is locked";
        	}
            currentRoom = currentRoom.getExit(4);
            return "You go up.";
        } else if (s.equals("down") && currentRoom.getExit(5) != null) {
        	if(currentRoom.getExit(5).isLocked()) {
        		return "That door is locked";
        	}
            currentRoom = currentRoom.getExit(5);
            return "You go down.";
        } else {
            return "You cannot go that way.";
        }
    }

    public static String takeItem(String name) {
        Item item = currentRoom.getItem(name);

        if (item.isHeavy()) {
            return "You can't lift the: " + name;
        }

        inventory.add(item);
        currentRoom.removeItem(name);
        return "You take the " + item + " with you.";
    }

    public static String getRoomDescription() {
       
        return currentRoom.toString();
    }

    public static void loadDescriptions() {
        try {
            Scanner fileScan = new Scanner(new File("Descriptions"));

            while (fileScan.hasNextLine()) {
                String name = fileScan.nextLine();
                StringBuilder desc = new StringBuilder();

                while (fileScan.hasNextLine()) {
                    String line = fileScan.nextLine();
                    if (line.equals("#")) {
                        break;
                    }
                    desc.append("\n").append(line);
                }

                descriptions.put(name, desc.toString());
            }

            fileScan.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadDescriptions();

        SwingUtilities.invokeLater(() -> {
            gui = new TextAdventureGUI();
            gui.createGUI(
                getRoomDescription(),
                "Welcome to the adventure!",
                e -> {
                    String command = e.getActionCommand();
                    String response = processCommand(command);

                    gui.appendResponse("> " + command);
                    gui.appendResponse(response);
                    gui.setRoomDescription(getRoomDescription());

                    if (command.equalsIgnoreCase("exit")) {
                        gui.dispose();
                    }
                }
            );
        });
    }
}
