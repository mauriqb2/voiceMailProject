public class Connection
{
	   private Mailbox currentMailbox;
	   private MailSystem system;
	   private String currentRecording;
	   private String accumulatedKeys;
	   private ConnectionObservable observable;
	   private int state;

	   private static final int DISCONNECTED = 0;
	   private static final int CONNECTED = 1;
	   private static final int RECORDING = 2;
	   private static final int MAILBOX_MENU = 3;
	   private static final int MESSAGE_MENU = 4;
	   private static final int CHANGE_PASSCODE = 5;
	   private static final int CHANGE_GREETING = 6;

	   private static final String INITIAL_PROMPT = 
	         "Enter mailbox number followed by #";      
	   private static final String MAILBOX_MENU_TEXT = 
	         "Enter 1 to listen to your messages\n"
	         + "Enter 2 to change your passcode\n"
	         + "Enter 3 to change your greeting";
	   private static final String MESSAGE_MENU_TEXT = 
	         "Enter 1 to listen to the current message\n"
	         + "Enter 2 to save the current message\n"
	         + "Enter 3 to delete the current message\n"
	         + "Enter 4 to return to the main menu";
	   
   public Connection(MailSystem s, ConnectionObservable observable){
      system = s;
      this.observable = observable;
      resetConnection(); 
   }

   public void dial(String key){
      if (state == CONNECTED)
         connect(key);
      else if (state == RECORDING)
         login(key);
      else if (state == CHANGE_PASSCODE)
         changePasscode(key);
      else if (state == CHANGE_GREETING)
         changeGreeting(key);
      else if (state == MAILBOX_MENU)
         mailboxMenu(key);
      else if (state == MESSAGE_MENU)
         messageMenu(key);
   }

   public void record(String voice){
      if (state == RECORDING || state == CHANGE_GREETING)
         currentRecording += voice;
   }

   public void hangup(){
      if (state == RECORDING)
         currentMailbox.addMessage(new Message(currentRecording));
      resetConnection();
   }

   private void resetConnection(){
      currentRecording = "";
      accumulatedKeys = "";
      state = CONNECTED;
      observable.speakAll(INITIAL_PROMPT);
   }

   private void connect(String key){
      if (key.equals("#"))
      {
         currentMailbox = system.findMailbox(accumulatedKeys);
         if (currentMailbox != null)
         {
            state = RECORDING;
            observable.speakAll(currentMailbox.getGreeting());
         }
         else
        	 observable.speakAll("Incorrect mailbox number. Try again!");
         accumulatedKeys = "";
      }
      else
         accumulatedKeys += key;
   }

   private void login(String key){
      if (key.equals("#"))
      {
         if (currentMailbox.checkPasscode(accumulatedKeys))
         {
            state = MAILBOX_MENU;
            observable.speakAll(MAILBOX_MENU_TEXT);
         }
         else
        	 observable.speakAll("Incorrect passcode. Try again!");
         accumulatedKeys = "";
      }
      else
         accumulatedKeys += key; 
   }

   private void changePasscode(String key){
      if (key.equals("#"))
      {
         currentMailbox.setPasscode(accumulatedKeys);
         state = MAILBOX_MENU;
         observable.speakAll(MAILBOX_MENU_TEXT);
         accumulatedKeys = "";
      }
      else
         accumulatedKeys += key;
   }

   private void changeGreeting(String key){
      if (key.equals("#"))
         currentMailbox.setGreeting(currentRecording);
         currentRecording = "";
         state = MAILBOX_MENU;
         observable.speakAll(MAILBOX_MENU_TEXT);
   }

   private void mailboxMenu(String key){
	  switch(Integer.parseInt(key)) {
		  case 1:{
			  state = MESSAGE_MENU;
			  observable.speakAll(MESSAGE_MENU_TEXT);
		      break;
		  } 
		  case 2:{
			  state = CHANGE_PASSCODE;
			  observable.speakAll("Enter new passcode followed by the # key");
		      break;
		  }
		  case 3:{
			  state = CHANGE_GREETING;
			  observable.speakAll("Record your greeting, then press the # key");
		      break;
		  }
	  }
   }

   private void messageMenu(String key){
	  switch(Integer.parseInt(key)) {
	  	case  1:{
	  		String output = getMessage();
	  		observable.speakAll(output);
	        break;
	  	}
	  	case 2:{
	  		currentMailbox.saveCurrentMessage();
	  		observable.speakAll(MESSAGE_MENU_TEXT);
	        break;
	  	}
	  	case 3:{
	  		currentMailbox.removeCurrentMessage();
	  		observable.speakAll(MESSAGE_MENU_TEXT);
	        break;
	  	}
	  	case 4:{
	  		state = MAILBOX_MENU;
	  		observable.speakAll(MAILBOX_MENU_TEXT);
	        break;
	  	}
	  }
   }

	private String getMessage() {
		String output = "";
		 Message m = currentMailbox.getCurrentMessage();
		 if (m == null) output += "No messages." + "\n";
		 else output += m.getText() + "\n";
		 output += MESSAGE_MENU_TEXT;
		return output;
	}
}










