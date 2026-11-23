package game;

public class Brother extends NPC{
	private int convoNumber=0;
	
	public Brother() {
		super("brother");
	}
	
	
	
	public void talk() {
		switch(convoNumber) {
		case 0:
			say("What do you want");
			String[] responses =
				{
						"Whats the combonation to the safe",
						"Can I have 20 bucks",
						"Whats the wifi password"
				};
			int option = getResponse(responses);
			switch(option) {
				case 1:
					say("Do you got 20 bucks?");
					convoNumber=1;
					break;
				case 2:
					say("Are you crazy?");
					break;
				case 3:
					say("1234");
					break;
				default:
					say("What are you talking about?");
				}
			break;
			
		case 1:
			convoOne();
			break;

		case 2:
			say("I already gave you the password");
			break;
		}
		

	}
	
	
	
	public void convoOne() {
		String[] responses= {"No","yeah","not yet"};
		
		int option = getResponse(responses);
		switch(option) {
			case 1:
				say("Then why are you here");
				convoNumber=0;
				break;
			case 2:
				say("then hand it over");
				convoNumber=0;
				break;
			case 3:
				say("Then go away");
				convoNumber=0;
				break;
			default:
				say("What are you talking about?");
		}
		
		
		
		
		
		
	}
	
	@Override
	public void give(Item i) {
		if(i.getName().equals("money")) {
			Game.print("BROTHER\nThe password is 1234");
			Game.inventory.add(new Item("combonation",false));
			Game.inventory.remove(i);
			convoNumber=2;
		}
		else {
			Game.print("BROTHER\nThats not what I want");
		}
	}
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
