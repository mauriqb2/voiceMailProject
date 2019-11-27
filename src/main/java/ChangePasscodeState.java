
public class ChangePasscodeState implements ConnectionState{
	
	Connection connection;
	
	public ChangePasscodeState(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void dial(String key) {
		if (key.equals("#"))
	      {
	         connection.getCurrentMailbox().setPasscode(connection.getAccumulatedKeys());
	         connection.setConnectionState(new MailboxMenuState(connection));
	         connection.speakAll(ConnectionText.MAILBOX_MENU_TEXT);
	         connection.setAccumulatedKeys("");
	      }
	      else
	         connection.setAccumulatedKeys(key);
	}
	

}
