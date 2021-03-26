package presentationLayer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	private List<Observer> observers = new ArrayList<Observer>();
	private String state;
	
	public void notifyAllObservers() {
		for(Observer observer: observers) {
			observer.update();
		}
	}
	
	public String getState() {
		return this.state;
	}
	
	public void setState(String newState) {
		this.state = newState;
		notifyAllObservers();
	}
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
}
