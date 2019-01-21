package hanze.nl.mockdatabaselogger;

public class EstimatedTimeOfArrival {
	String halteNaam;
	int richting;
	int aankomsttijd;
	
	EstimatedTimeOfArrival(String halteNaam, int richting, int aankomsttijd){
		this.halteNaam=halteNaam;
		this.richting=richting;
		this.aankomsttijd=aankomsttijd;
	}
}
