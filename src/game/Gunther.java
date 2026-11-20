package game;

public class Gunther extends NPC {
	private int convoNumber=0;

	
		public Gunther() {
			super("gunther");
		}

		@Override
		public void talk() {
			switch(convoNumber) {
				case 0:
					say("Yo, whats up");
					String[] responses =
						{
								"Can you give me 20 bucks",
								"got any grapes",
								"Knock Knock"
						};
					int option = getResponse(responses);
					switch(option) {
						case 1:
							say("Do I look rich to you?");
							convoNumber=1;
							break;
						case 2:
							say("I hate grapes");
							convoNumber=2;
							break;
						case 3:
							say("Who's there");
							convoNumber=3;
							break;
						default:
							say("Uhhh…what?");
						}
					break;
					
				case 1:
					convoOne();
					break;
					
				case 2:
					convoTwo();
					break;
				case 3:
					convoThree();
					break;
				case 4:
					say("Just because I am wearing a fancy suit and shoes that cost $300 does not mean I am rich");
					break;
				case 5:
					say("Don't ask me for money");
					break;
				case 6:
					say("You have my sandwich yet?");
					break;
				case 7:
					say("I don't wanna talk to you");
					break;
				case 8:
					say("There are no grapes here");
					break;
				case 9:
					say("These are not the grapes you are looking for");
					break;
				case 10:
					say("No more jokes");
					break;
				case 11:
					say("Go away johnny");
					break;
				case 12:
					say("Gunther got arrested for tax evasion");
					break;
				}
			
			
			
		}
		
		public void convoOne() {
			String[] responses =
				{
						"You do look rich",
						"I really need the money",
						"I want get chick fil a"
				};
			int option = getResponse(responses);
			switch(option) {
				case 1:
					say("Just because I am wearing a fancy suit and shoes that cost $300 does not mean I am rich");
					convoNumber = 4;
					break;
				case 2:
					convoNumber =5;
					say("So do I");
					break;
				case 3:
				convoNumber =6;
					say("Get me chicken sandwich");
					break;
				default:
					say("Uhhh…what?");
			}
		}
		
		
		public void convoTwo() {
			String[] responses =
				{
						"How could you hate grapes?",
						"Yeah they are nasty",
						"but are there grapes here?"
				};
			int option = getResponse(responses);
			switch(option) {
				case 1:
					say("Cause they're healthy");
					convoNumber = 7;
					break;
				case 2:
					convoNumber =8;
					say("Then why were you asking");
					break;
				case 3:
				convoNumber =9;
					say("I ran out");
					break;
				default:
					say("Uhhh…what?");
			}
		}
		
		public void convoThree() {
			String[] responses =
				{
						"boo",
						"John",
						"The IRS and were here for your taxes"
				};
			int option = getResponse(responses);
			switch(option) {
				case 1:
					say("I aint fallin for that");
					convoNumber = 10;
					break;
				case 2:
					convoNumber = 11;
					say("Who is John?");
					break;
				case 3:
				convoNumber = 12;
					say("NOOOOOOOOOOOOOOOOOOOOOOOOOO");
					break;
				default:
					say("Uhhh…what?");
			}
		}
		
		@Override
		public void give(Item i) {
			Game.print("I don't want that");
		}
		
		
}

