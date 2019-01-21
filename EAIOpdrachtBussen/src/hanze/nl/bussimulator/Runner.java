package hanze.nl.bussimulator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Runner {

	private static HashMap<Integer,ArrayList<BusManager>> busStart = new HashMap<Integer,ArrayList<BusManager>>();
	private static ArrayList<BusManager> activeBusses = new ArrayList<BusManager>();
	private static int interval=1000;
	private static int syncInterval=5;    
	
	private static void addBus(int starttijd, BusModel busModel){
		ArrayList<BusManager> bussen = new ArrayList<BusManager>();
		if (busStart.containsKey(starttijd)) {
			bussen = busStart.get(starttijd);
		}
		bussen.add(new BusManager(busModel));
		busStart.put(starttijd,bussen);
		busModel.setbusID(starttijd);
	}
	
	private static int startBussen(int tijd){
		for (BusManager busModel : busStart.get(tijd)){
			activeBusses.add(busModel);
		}
		busStart.remove(tijd);
		return (!busStart.isEmpty()) ? Collections.min(busStart.keySet()) : -1;
	}
	
	public static void moveBussen(int nu){
		Iterator<BusManager> itr = activeBusses.iterator();
		while (itr.hasNext()) {
			BusManager busModel = itr.next();
			boolean hasReachedLastStop = busModel.move();
			if (hasReachedLastStop) {
				MessageSender sender = new MessageSender(busModel.bus);
				sender.sendLastEstimatedTimeOfArrival(nu);
				itr.remove();
			}
		}		
	}

	public static void sendETAs(int nu){
		Iterator<BusManager> itr = activeBusses.iterator();
		while (itr.hasNext()) {
			BusManager busModel = itr.next();
			MessageSender sender = new MessageSender(busModel.bus);
			sender.sendLastEstimatedTimeOfArrival(nu);
		}				
	}
	
	public static int initBussen(){
		initBussesInDirection(1);
		initBussesInDirection(-1);
		return Collections.min(busStart.keySet());
	}

	public static void initBussesInDirection(int direction) {
		addBus(3, new BusModel(Line.LINE1, Company.ARRIVA, direction));
		addBus(5, new BusModel(Line.LINE2, Company.ARRIVA, direction));
		addBus(4, new BusModel(Line.LINE3, Company.ARRIVA, direction));
		addBus(6, new BusModel(Line.LINE4, Company.ARRIVA, direction));
		addBus(3, new BusModel(Line.LINE5, Company.FLIXBUS, direction));
		addBus(5, new BusModel(Line.LINE6, Company.QBUZZ, direction));
		addBus(4, new BusModel(Line.LINE7, Company.QBUZZ, direction));
		addBus(6, new BusModel(Line.LINE1, Company.ARRIVA, direction));
		addBus(12, new BusModel(Line.LINE4, Company.ARRIVA, direction));
		addBus(10, new BusModel(Line.LINE5, Company.FLIXBUS, direction));
	}

	public static void main(String[] args) throws InterruptedException {
		int tijd=0;
		int volgende = initBussen();
		while ((volgende>=0) || !activeBusses.isEmpty()) {
			System.out.println("De time is:" + tijd);
			volgende = (tijd==volgende) ? startBussen(tijd) : volgende;
			moveBussen(tijd);
			sendETAs(tijd);
			Thread.sleep(interval);
			tijd++;
		}
	}
}
