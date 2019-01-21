package hanze.nl.bussimulator;

public class BusManager {

    public BusModel bus;

    public BusManager(BusModel bus) {
        this.bus = bus;
    }

    private void toNextStop(){
        BusStop.Position volgendeHalte = bus.lijn.getHalte(bus.halteNummer+ bus.direction).getPosition();
        bus.toNextStop = bus.lijn.getHalte(bus.halteNummer).distance(volgendeHalte);
    }

    private void hasReachedStopAndSetBusStop() {
        if(!hasReachedStop()){
            toNextStop();
        }
    }

    private boolean hasReachedStop(){
        bus.halteNummer+= bus.direction;
        bus.atStop =true;
        if ((bus.halteNummer>=bus.lijn.getLengte()-1) || (bus.halteNummer == 0)) {
            printCurrentEstimatedTimeOfArrival();
            return true;
        }
        else {
            printCurrentEstimatedTimeOfArrival();
        }
        return false;
    }

    private void printCurrentEstimatedTimeOfArrival(){
        System.out.printf("Bus %s heeft busStop %s, direction %d bereikt.%n",
                bus.lijn.name(), bus.lijn.getHalte(bus.halteNummer), bus.lijn.getRichting(bus.halteNummer));
    }

    private void start() {
        bus.halteNummer = (bus.direction ==1) ? 0 : bus.lijn.getLengte()-1;
        printCurrentEstimatedTimeOfArrival();
        toNextStop();
    }

    boolean move(){
        boolean eindpuntBereikt = false;
        bus.atStop =false;
        if (bus.halteNummer == -1) {
            start();
        }
        else {
            bus.toNextStop--;
            if (bus.toNextStop ==0){
                eindpuntBereikt= hasReachedStop();
            }
        }
        return eindpuntBereikt;
    }
}
