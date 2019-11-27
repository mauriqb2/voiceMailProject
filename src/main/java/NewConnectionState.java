
public class NewConnectionState implements ConnectionState {
	
	private Connection connection;
	
	public NewConnectionState(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void dial(String key) {
		if (key.equals("#"))
	      {
	         Mailbox currentMailbox = connection.getCurrentMailbox(connection.getAccumulatedKeys());
	         if (currentMailbox != null)
	         {
	        	connection.setConnectionState(new RecordingState(connection));
	            connection.speakAll(currentMailbox.getGreeting());
	         }
	         else
	        	 connection.speakAll("Incorrect mailbox number. Try again!");
	         connection.setAccumulatedKeys("");
	      }
	      else
	         connection.setAccumulatedKeys(key);
	}

}
