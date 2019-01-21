package hanze.nl.bussimulator;

import hanze.nl.bussimulator.BusStop.Position;

public class BusModel {

	Company company;
	Line lijn;
	int halteNummer;
	int toNextStop;
	int direction;
	boolean atStop;
	String busID;
	
	BusModel(Line lijn, Company company, int direction){
		this.lijn=lijn;
		this.company = company;
		this.direction = direction;
		this.halteNummer = -1;
		this.toNextStop = 0;
		this.atStop = false;
		this.busID = "Niet gestart";
	}
	
	void setbusID(int starttijd){
		this.busID=starttijd+lijn.name()+ direction;
	}
	

	

	

}
