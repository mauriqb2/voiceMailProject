
public class MailboxMenuState implements ConnectionState {
	
	Connection connection;
	
	public MailboxMenuState(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void dial(String key) {
		switch(Integer.parseInt(key)) {
		  case 1:{
			  connection.setConnectionState(new MessageMenuState(connection));
			  connection.speakAll(ConnectionText.MESSAGE_MENU_TEXT);
		      break;
		  } 
		  case 2:{
			  connection.setConnectionState(new ChangePasscodeState(connection));
			  connection.speakAll("Enter new passcode followed by the # key");
		      break;
		  }
		  case 3:{
			  connection.setConnectionState(new ChangeGreetingState(connection));
			  connection.speakAll("Record your greeting, then press the # key");
		      break;
		  }
	  }
	}
}
