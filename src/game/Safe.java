package game;

public class Safe extends Item{
	
	
	private boolean firstOpen;
	private boolean opened;
	
	public Safe() {
		super("safe",true);
		firstOpen=false;
		opened=false;
	}
	
	@Override
	public void open() {
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
