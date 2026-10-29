package game;

public class OfficeKey extends Item {
	private boolean usedTool;
		public OfficeKey() {
			super("officekey",false);
			usedTool = false;
		}
		@Override
		public void use() {
			if (Game.currentRoom.getName().equals("Living Room")) {
				if (usedTool) {
					Game.print("You already opened the office door");
				} else {
					Game.currentRoom.getExit(4).setLock(false);
					usedTool = true;
					Game.print("Using the key, you are able to open the door");
				}
			} else
				Game.print("You don't see any way to use the key…");
		}
}
