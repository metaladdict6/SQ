package hanze.nl.bussimulator;

public class Line {

	public Stop[] haltes;
	
	Line(Stop...haltes){
		this.haltes=haltes;
	}
	
	int getLengte() {
		return haltes.length;
	}
	
	Halte getHalte(int positie){
		return haltes[positie].halte;
	}
	
	int getRichting(int positie){
		return haltes[positie].richting;
	}
}
