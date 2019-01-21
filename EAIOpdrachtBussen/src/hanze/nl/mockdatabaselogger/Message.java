package hanze.nl.mockdatabaselogger;

import java.util.ArrayList;

public class Message {
	String lijnNaam;
	String eindpunt;
	String bedrijf;
	String busID;
	int tijd;
	public ArrayList<EstimatedTimeOfArrival> EstimatedTimeOfArrivals;
	
	Message(String lijnNaam, String bedrijf, String busID, int tijd){
		this.lijnNaam=lijnNaam;
		this.bedrijf=bedrijf;
		this.eindpunt="";
		this.busID=busID;
		this.tijd=tijd;
		this.EstimatedTimeOfArrivals =new ArrayList<EstimatedTimeOfArrival>();
	}

}
