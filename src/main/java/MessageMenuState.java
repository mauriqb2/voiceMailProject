
public class MessageMenuState implements ConnectionState {
	
	Connection connection;
	
	public MessageMenuState(Connection connection) {
		this.connection = connection;
	}


	@Override
	public void dial(String key) {
		switch(Integer.parseInt(key)) {
	  	case  1:{
	  		String output = connection.getMessage();
	  		connection.speakAll(output);
	        break;
	  	}
	  	case 2:{
	  		connection.getCurrentMailbox().saveCurrentMessage();
	  		connection.speakAll(ConnectionText.MESSAGE_MENU_TEXT);
	        break;
	  	}
	  	case 3:{
	  		connection.getCurrentMailbox().removeCurrentMessage();
	  		connection.speakAll(ConnectionText.MESSAGE_MENU_TEXT);
	        break;
	  	}
	  	case 4:{
	  		connection.setConnectionState(new MailboxMenuState(connection));
	  		connection.speakAll(ConnectionText.MAILBOX_MENU_TEXT);
	        break;
	  	}
	  }
	}
}
