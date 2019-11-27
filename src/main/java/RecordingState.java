
public class RecordingState implements ConnectionState {
	
	private Connection connection;
	
	public RecordingState(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void dial(String key) {
		if (key.equals("#"))
	      {
	         if (connection.getCurrentMailbox().checkPasscode(connection.getAccumulatedKeys()))
	         {
	            connection.setConnectionState(new MailboxMenuState(connection));
	            connection.speakAll(ConnectionText.MAILBOX_MENU_TEXT);
	         }
	         else
	            connection.speakAll("Incorrect passcode. Try again!");
	         connection.setAccumulatedKeys("");
	      }
	      else
	         connection.setAccumulatedKeys(key);
	}

}
