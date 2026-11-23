package game;

public class NoteBook extends Item{
	
	private boolean read;
	
	public NoteBook() {
		super("notebook",false);
		read=false;
	}
	
	@Override
	public void read() {
		if(read==true) {
			Game.print("You read the same thing, there is nothing else new in the notebook");
		}
		else if(read==false) {
			read=true;
			Game.print("Written in the notebook is the password to the computer in the office");
			Item password = new Password();
			Game.inventory.add(password);
		}
		else {
			Game.print("You do not have a notebook");
		}
	}
}
