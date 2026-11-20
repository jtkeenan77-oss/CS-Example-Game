package game;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class NPC {
	
	private String name;
	
	
	public NPC(String n) {
		name=n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void say(String dialouge) {
		Game.print("\n"+name.toUpperCase()+"\n"+dialouge);
	}
	
	public void talk() {
		Game.print("You can't talk to "+name);
	}
	
	public int getResponse(String[] responses) {
		String res="";
		for(int i=1; i<=responses.length;i++) {
			Game.print("\n"+i+". "+responses[i-1]);
			res+=("\n"+i+". "+responses[i-1]);
		}
		String choice= JOptionPane.showInputDialog(null,"Choose an option"+res);
		Game.print("\n> "+choice);
		int test=0;
			try {
				test=Integer.parseInt(choice);
			} catch (NumberFormatException e) {
				return 0;
			}
		if(test>3 || test<0)
			return 0;
		return test;
	}

	public void give(Item i) {
		Game.print(name+" does not want the "+i.getName());
	}
	
}
