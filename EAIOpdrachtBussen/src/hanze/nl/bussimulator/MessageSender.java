package hanze.nl.bussimulator;

public class MessageSender {

    private Bus bus;

    public MessageSender(Bus bus) {
        this.bus = bus;
    }

    public void sendETAs(int nu){
        int i=0;
        try{
            Message message = new Message(bus.lijn.name(),bus.company.name(),bus.busID,nu);
            if (bus.bijHalte) {
                EstimatedTimeOfArrival estimatedTimeOfArrival = new EstimatedTimeOfArrival(bus.lijn.getHalte(bus.halteNummer).name(),bus.lijn.getRichting(bus.halteNummer),0);
                message.EstimatedTimeOfArrivals.add(estimatedTimeOfArrival);
            }
            BusStop.Positie eerstVolgende=bus.lijn.getHalte(bus.halteNummer+ bus.richting).getPositie();
            int tijdNaarHalte=bus.totVolgendeHalte+nu;
            for (i = bus.halteNummer+ bus.richting ; !(i>= bus.lijn.getLengte()) && !(i < 0); i=i+ bus.richting ){
                tijdNaarHalte+= bus.lijn.getHalte(i).distance(eerstVolgende);
                EstimatedTimeOfArrival estimatedTimeOfArrival = new EstimatedTimeOfArrival(bus.lijn.getHalte(i).name(), bus.lijn.getRichting(i),tijdNaarHalte);
                message.EstimatedTimeOfArrivals.add(estimatedTimeOfArrival);
                eerstVolgende=bus.lijn.getHalte(i).getPositie();
            }
            message.endPoint =bus.lijn.getHalte(i-bus.richting).name();
            sendBericht(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

    }


    public void sendLastETA(int nu){
        Message message = new Message(bus.lijn.name(),bus.company.name(),bus.busID,nu);
        String eindpunt = bus.lijn.getHalte(bus.halteNummer).name();
        EstimatedTimeOfArrival estimatedTimeOfArrival = new EstimatedTimeOfArrival(eindpunt,bus.lijn.getRichting(bus.halteNummer),0);
        message.EstimatedTimeOfArrivals.add(estimatedTimeOfArrival);
        message.endPoint = eindpunt;
        sendBericht(message);
    }

    public void sendBericht(Message message){
        //TODO verstuur een XML message naar de messagebroker.
    }
}
