package hanze.nl.bussimulator;

public class MessageSender {

    private BusModel busModel;

    public MessageSender(BusModel busModel) {
        this.busModel = busModel;
    }

    public void sendEstimatedTimeOfArrivals(int now){
        try{
            int i=0;
            Message message = new Message(busModel.lijn.name(), busModel.company.name(), busModel.busID,now);
            checkIfAtStopAndAdToTimeOfArrivals(message);
            setNextFollowingBusAndAddEstimatedTimeOfArrival(now, message);
            message.endPoint = busModel.lijn.getHalte(i- busModel.direction).name();
            sendBericht(message);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void checkIfAtStopAndAdToTimeOfArrivals(Message message) throws Exception {
        if (busModel.atStop) {
            EstimatedTimeOfArrival estimatedTimeOfArrival = new EstimatedTimeOfArrival(busModel.lijn.getHalte(busModel.halteNummer).name(), busModel.lijn.getRichting(busModel.halteNummer),0);
            message.EstimatedTimeOfArrivals.add(estimatedTimeOfArrival);
        }
    }

    private void setNextFollowingBusAndAddEstimatedTimeOfArrival(int now, Message message) throws Exception{
        BusStop.Position eerstVolgende= busModel.lijn.getHalte(busModel.halteNummer+ busModel.direction).getPosition();
        int tijdNaarHalte= busModel.toNextStop +now;
        for (int i = busModel.halteNummer+ busModel.direction; !(i>= busModel.lijn.getLengte()) && !(i < 0); i=i+ busModel.direction){
            tijdNaarHalte+= busModel.lijn.getHalte(i).distance(eerstVolgende);
            EstimatedTimeOfArrival estimatedTimeOfArrival = new EstimatedTimeOfArrival(busModel.lijn.getHalte(i).name(), busModel.lijn.getRichting(i),tijdNaarHalte);
            message.EstimatedTimeOfArrivals.add(estimatedTimeOfArrival);
            eerstVolgende= busModel.lijn.getHalte(i).getPosition();
        }
    }

    public void sendLastEstimatedTimeOfArrival(int now){
        try {
            Message message = new Message(busModel.lijn.name(), busModel.company.name(), busModel.busID,now);
            String eindpunt = busModel.lijn.getHalte(busModel.halteNummer).name();
            EstimatedTimeOfArrival estimatedTimeOfArrival = new EstimatedTimeOfArrival(eindpunt, busModel.lijn.getRichting(busModel.halteNummer),0);
            message.EstimatedTimeOfArrivals.add(estimatedTimeOfArrival);
            message.endPoint = eindpunt;
            sendBericht(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendBericht(Message message) throws Exception{
        //TODO verstuur een XML message naar de messagebroker.
    }
}
