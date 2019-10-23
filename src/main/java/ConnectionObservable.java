import java.util.ArrayList;

public class ConnectionObservable{
	
	private ArrayList<ConnectionObserver> observers = new ArrayList<ConnectionObserver>();

	public void speakAll(String message) {
		observers.forEach(observer -> {
			observer.speak(message);
		});
	}

	public void addObserver(ConnectionObserver connectionObserver) {
		observers.add(connectionObserver);
	}

	public void removeObserver(ConnectionObserver connectionObserver) {
		observers.remove(connectionObserver);
	}

}
