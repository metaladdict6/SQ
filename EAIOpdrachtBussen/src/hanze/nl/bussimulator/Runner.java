package hanze.nl.bussimulator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Runner {

	private static HashMap<Integer,ArrayList<Bus>> busStart = new HashMap<Integer,ArrayList<Bus>>();
	private static ArrayList<Bus> actieveBussen = new ArrayList<Bus>();
	private static int interval=1000;
	private static int syncInterval=5;    
	
	private static void addBus(int starttijd, Bus bus){
		ArrayList<Bus> bussen = new ArrayList<Bus>();
		if (busStart.containsKey(starttijd)) {
			bussen = busStart.get(starttijd);
		}
		bussen.add(bus);
		busStart.put(starttijd,bussen);
		bus.setbusID(starttijd);
	}
	
	private static int startBussen(int tijd){
		for (Bus bus : busStart.get(tijd)){
			actieveBussen.add(bus);
		}
		busStart.remove(tijd);
		return (!busStart.isEmpty()) ? Collections.min(busStart.keySet()) : -1;
	}
	
	public static void moveBussen(int nu){
		Iterator<Bus> itr = actieveBussen.iterator();
		while (itr.hasNext()) {
			Bus bus = itr.next();
			boolean eindpuntBereikt = bus.move();
			if (eindpuntBereikt) {
				bus.sendLastETA(nu);
				itr.remove();
			}
		}		
	}

	public static void sendETAs(int nu){
		Iterator<Bus> itr = actieveBussen.iterator();
		while (itr.hasNext()) {
			Bus bus = itr.next();
			bus.sendETAs(nu);
		}				
	}
	
	public static int initBussen(){
		initBussesInRichting(1);
		initBussesInRichting(-1);
		return Collections.min(busStart.keySet());
	}

	public static void initBussesInRichting(int richting) {
		addBus(3, new Bus(Line.LIJN1, Company.ARRIVA, richting));
		addBus(5, new Bus(Line.LIJN2, Company.ARRIVA, richting));
		addBus(4, new Bus(Line.LIJN3, Company.ARRIVA, richting));
		addBus(6, new Bus(Line.LIJN4, Company.ARRIVA, richting));
		addBus(3, new Bus(Line.LIJN5, Company.FLIXBUS, richting));
		addBus(5, new Bus(Line.LIJN6, Company.QBUZZ, richting));
		addBus(4, new Bus(Line.LIJN7, Company.QBUZZ, richting));
		addBus(6, new Bus(Line.LIJN1, Company.ARRIVA, richting));
		addBus(12, new Bus(Line.LIJN4, Company.ARRIVA, richting));
		addBus(10, new Bus(Line.LIJN5, Company.FLIXBUS, richting));
	}

	public static void main(String[] args) throws InterruptedException {
		int tijd=0;
		int volgende = initBussen();
		while ((volgende>=0) || !actieveBussen.isEmpty()) {
			System.out.println("De time is:" + tijd);
			volgende = (tijd==volgende) ? startBussen(tijd) : volgende;
			moveBussen(tijd);
			sendETAs(tijd);
			Thread.sleep(interval);
			tijd++;
		}
	}

	/* Om de tijdsynchronisatie te gebruiken moet de onderstaande main gebruikt worden
	 * 
	public static void main(String[] args) throws InterruptedException {
		int time=0;
		int counter=0;
		TijdFuncties tijdFuncties = new TijdFuncties();
		tijdFuncties.initSimulatorTijden(interval,syncInterval);
		int volgende = initBussen();
		while ((volgende>=0) || !actieveBussen.isEmpty()) {
			counter=tijdFuncties.getCounter();
			time=tijdFuncties.getTijdCounter();
			System.out.println("De time is:" + tijdFuncties.getSimulatorWeergaveTijd());
			volgende = (counter==volgende) ? startBussen(counter) : volgende;
			moveBussen(time);
			sendETAs(time);
			tijdFuncties.simulatorStep();
		}
	}
		 */

}
