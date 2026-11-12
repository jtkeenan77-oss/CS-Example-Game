package game;

public class Gunther extends NPC {

	
		public Gunther() {
			super("gunther");
		}

		@Override
		public void talk() {
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
					say("Do I looko rich to you?");
							break;
				case 2:
					say("I hate grapes");
					break;
				case 3:
					say("I don't like jokes");
					break;
				default:
					say("Uhhh…what?");
			}
		}
}

