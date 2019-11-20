import java.util.ArrayList;

public class Observable{
	
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	public void speakAll(String message) {
		observers.forEach(observer -> {
			observer.speak(message);
		});
	}

	public void addObserver(Observer connectionObserver) {
		observers.add(connectionObserver);
	}

	public void removeObserver(Observer connectionObserver) {
		observers.remove(connectionObserver);
	}

}
