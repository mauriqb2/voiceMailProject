public class Connection extends Observable
{
	private Mailbox currentMailbox;
	private MailSystem system;
	private String currentRecording;
	private String accumulatedKeys;
	private ConnectionState connectionState;
	 
	public String getCurrentRecording() {
		return currentRecording;
	}

	public void setCurrentRecording(String currentRecording) {
		this.currentRecording = currentRecording;
	}
	
	public String getAccumulatedKeys() {
		return accumulatedKeys;
	}

	public void setAccumulatedKeys(String accumulatedKeys) {
		this.accumulatedKeys = accumulatedKeys;
	}
	      
	public ConnectionState getConnectionState() {
		return connectionState;
	}

	public void setConnectionState(ConnectionState connectionState) {
		this.connectionState = connectionState;
	}
	
	public void setCurrentMailbox(Mailbox currentMailbox) {
		this.currentMailbox = currentMailbox;
	}

	public Mailbox getCurrentMailbox(String accumulatedKeys) {
		currentMailbox = system.findMailbox(accumulatedKeys);
		return currentMailbox;
	}
	
	public Mailbox getCurrentMailbox() {
		return currentMailbox;
	}
	
	public Connection(MailSystem system){
		this.system = system;
		resetConnection(); 
	}
	
	public void dial(String key){
		connectionState.dial(key);
	}

	public void record(String voice){
	    if (connectionState instanceof RecordingState || connectionState instanceof ChangeGreetingState)
	    	currentRecording += voice;
	}

	public void hangup(){
	    if (connectionState instanceof RecordingState)
	        currentMailbox.addMessage(new Message(currentRecording));
	    resetConnection();
	}

	private void resetConnection(){
	    currentRecording = "";
	    accumulatedKeys = "";
	    connectionState = new NewConnectionState(this);
	    this.speakAll(ConnectionText.INITIAL_PROMPT);
	}
	
	public String getMessage() {
		String output = "";
		 Message m = currentMailbox.getCurrentMessage();
		 if (m == null) output += "No messages." + "\n";
		 else output += m.getText() + "\n";
		 output += ConnectionText.MESSAGE_MENU_TEXT;
		return output;
	}
}










