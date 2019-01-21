package hanze.nl.bussimulator;

public enum Line {
	LINE1 (new Stop(BusStop.A,1),
			new Stop(BusStop.B,1),
			new Stop(BusStop.C,1),
			new Stop(BusStop.D,1),
			new Stop(BusStop.E,1),
			new Stop(BusStop.F,1),
			new Stop(BusStop.G,1)),

	LINE2 (new Stop(BusStop.H,1)
			,new Stop(BusStop.E,1),
			new Stop(BusStop.I,1),
			new Stop(BusStop.K,1),
			new Stop(BusStop.L,1),
			new Stop(BusStop.M,1)),

	LINE3 (new Stop(BusStop.N,1),
			new Stop(BusStop.O,1),
			new Stop(BusStop.P,1),
			new Stop(BusStop.K,-1),
			new Stop(BusStop.Q,1),
			new Stop(BusStop.R,1)),

	LINE4 (new Stop(BusStop.S,1),
			new Stop(BusStop.T,1),
			new Stop(BusStop.U,1),
			new Stop(BusStop.V,1),
			new Stop(BusStop.I,1),
			new Stop(BusStop.K,1),
			new Stop(BusStop.L,1),
			new Stop(BusStop.M,1)),

	LINE5 (new Stop(BusStop.W,1),
			new Stop(BusStop.X,1),
			new Stop(BusStop.Y,1),
			new Stop(BusStop.G,1),
			new Stop(BusStop.Z,1),
			new Stop(BusStop.N,1)),

	LINE6 (new Stop(BusStop.A,1),
			new Stop(BusStop.B,1),
			new Stop(BusStop.H,1),
			new Stop(BusStop.T,-1),
			new Stop(BusStop.X,-1),new Stop(BusStop.W,-1)),

	LINE7 (new Stop(BusStop.A,1),
			new Stop(BusStop.R,-1),
			new Stop(BusStop.Q,-1),
			new Stop(BusStop.L,1),
			new Stop(BusStop.M,1),
			new Stop(BusStop.O,-1),
			new Stop(BusStop.N,-1),
			new Stop(BusStop.Z,-1),
			new Stop(BusStop.G,-1),
			new Stop(BusStop.Y,-1),
			new Stop(BusStop.X,-1),
			new Stop(BusStop.W,-1)),

	LINE8 (new Stop(BusStop.M,-1),
			new Stop(BusStop.P,1),
			new Stop(BusStop.J,1),
			new Stop(BusStop.F,-1),
			new Stop(BusStop.V,-1),
			new Stop(BusStop.E,-1),
			new Stop(BusStop.H,-1));

	public Stop[] haltes;
	
	Line(Stop...haltes){
		this.haltes=haltes;
	}
	
	int getLengte() {
		return haltes.length;
	}
	
	BusStop getHalte(int positie){
		return haltes[positie].busStop;
	}
	
	int getRichting(int positie){
		return haltes[positie].richting;
	}

	static class Stop{
		BusStop busStop;
		int richting;

		public Stop(BusStop busStop, int richting){
			this.busStop = busStop;
			this.richting=richting;
		}
	}
}
