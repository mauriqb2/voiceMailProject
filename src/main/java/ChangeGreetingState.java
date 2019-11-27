
public class ChangeGreetingState implements ConnectionState {
	
    Connection connection;
	
	public ChangeGreetingState(Connection connection) {
		this.connection = connection;
	}


	@Override
	public void dial(String key) {
		if (key.equals("#"))
	         connection.getCurrentMailbox().setGreeting(connection.getCurrentRecording());
	         connection.setCurrentRecording("");
	         connection.setConnectionState(new MailboxMenuState(connection));
	         connection.speakAll(ConnectionText.MAILBOX_MENU_TEXT);
	}

}
