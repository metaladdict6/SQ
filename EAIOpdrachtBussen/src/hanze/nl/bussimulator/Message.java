package hanze.nl.bussimulator;

import java.util.ArrayList;

public class Message {
	String lineName;
	String endPoint;
	String company;
	String busID;
	int time;
	ArrayList<EstimatedTimeOfArrival> EstimatedTimeOfArrivals;
	
	Message(String lineName, String company, String busID, int time){
		this.lineName = lineName;
		this.company = company;
		this.endPoint ="";
		this.busID=busID;
		this.time = time;
		this.EstimatedTimeOfArrivals =new ArrayList<EstimatedTimeOfArrival>();
	}
}
