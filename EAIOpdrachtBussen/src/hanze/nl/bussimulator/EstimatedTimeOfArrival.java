package hanze.nl.bussimulator;

public class EstimatedTimeOfArrival {
	String stopName;
	int direction;
	int arrivalTime;
	
	EstimatedTimeOfArrival(String stopName, int direction, int arrivalTime){
		this.stopName = stopName;
		this.direction = direction;
		this.arrivalTime = arrivalTime;
	}
}
