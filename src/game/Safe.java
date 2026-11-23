package game;

public class Safe extends Item{
	
	private boolean locked=true;
	private boolean firstOpen;
	private boolean opened;
	
	public Safe() {
		super("safe",true);
		firstOpen=false;
		opened=false;
	}
	
	@Override
	public void open() {
		if(locked == true) {
			for(Item i: Game.inventory) {
				if(i.getName().equals("combonation")) {
					locked=false;
					Game.print("You unlock the safe");
					return;
				}
				
			}
			Game.print("The safe is locked");
			return;
		}
		if(opened==false) {
			Game.print("You open the safe\n");
			opened=true;
			if(firstOpen==false) {
				Item notebook = new NoteBook();
				Game.currentRoom.addItem(notebook);
				firstOpen=true;
			}
			if(Game.currentRoom.getItem("notebook")!=null) {
				Game.print("there is a notebook sitting inside");
			}
			else{
				Game.print("The safe is empty");
			}
		}
		else if(opened==true) {
			Game.print("The safe is already open");
		}
	}
	
	@Override
	public void close() {
		if(opened==true) {
			if(Game.currentRoom.getItem("notebook")!=null) {
				Game.currentRoom.removeItem("notebook");
				firstOpen=false;
			}
			Game.print("You close the safe");
			opened=false;
		}
		else if(opened==false) {
			Game.print("The safe is already closed");
		}
	}

}
