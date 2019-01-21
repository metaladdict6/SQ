package hanze.nl.infobord;

import org.codehaus.jackson.map.ObjectMapper;

import javax.swing.*;
import java.io.IOException;

public class InfoBordManager {

    private InfoBord infoBord;

    public InfoBordManager(){
        this.infoBord = InfoBord.getInfoBord();
    }

    public void setRulesAndTotalTime(){
        String[] infoTekst={"--1--","--2--","--3--","--4--","leeg"};
        int[] aankomsttijden=new int[5];
        int aantalRegels = 0;
        if(!infoBord.infoBordRegels.isEmpty()){
            CheckArrivalTimes(aantalRegels, aankomsttijden, infoTekst);
        }
        if(checkRepaint(aantalRegels, aankomsttijden)){
            infoBord.hashValue= getTotalTime(aantalRegels, aankomsttijden);
            repaintInfoBord(infoTekst);
        }
    }

    private void CheckArrivalTimes(int amountOfLines, int[] arrivalTimes, String[] infoTexts){
        for(String busID: infoBord.infoBordRegels.keySet()){
            JSONBericht regel = infoBord.infoBordRegels.get(busID);
            int dezeTijd=regel.getAankomsttijd();
            String dezeTekst=regel.getInfoRegel();
            int plaats=amountOfLines;
            changeArrivalTime(amountOfLines, dezeTijd, arrivalTimes, infoTexts, plaats, dezeTekst);
            if(amountOfLines<4){
                amountOfLines++;
            }
        }
    }

    private void changeArrivalTime(int aantalRegels, int thisTime, int[] arrivalTimes, String[] infoTekst, int place,
                                   String thisText){
        for(int i=aantalRegels;i>0;i--){
            if(thisTime<arrivalTimes[i-1]){
                arrivalTimes[i]=arrivalTimes[i-1];
                infoTekst[i]=infoTekst[i-1];
                place=i-1;
            }
        }
        arrivalTimes[place]=thisTime;
        infoTekst[place]=thisText;
    }

    private boolean checkRepaint(int aantalRegels, int[] aankomsttijden){
        int totaalTijden= getTotalTime(aantalRegels, aankomsttijden);
        if(infoBord.hashValue!=totaalTijden){
            return true;
        }
        return false;
    }

    private int getTotalTime(int aantalRegels, int[] aankomsttijden){
        int totaalTijden=0;
        for(int i=0; i<aantalRegels;i++){
            totaalTijden+=aankomsttijden[i];
        }
        return totaalTijden;
    }

    private void repaintInfoBord(String[] infoTekst){
        for(int i = 0; i < infoBord.rules.size(); i++) {
            JLabel label = infoBord.rules.get(i);
            setJlabel(label, i, infoTekst[i]);
        }
        infoBord.scherm.repaint();
    }

    private void setJlabel(JLabel label, int labelNumber, String text){
        label.setText(text);
    }

    public static void verwerktBericht(String incoming){
        try {
            JSONBericht message = new ObjectMapper().readValue(incoming, JSONBericht.class);
            String busID = message.getBusID();
            Integer tijd = message.getTijd();
            if (!InfoBord.getInfoBord().laatsteBericht.containsKey(busID) || InfoBord.getInfoBord().laatsteBericht.get(busID)<=tijd){
                InfoBord.getInfoBord().laatsteBericht.put(busID, tijd);
                if (message.getAankomsttijd()==0){
                    InfoBord.getInfoBord().infoBordRegels.remove(busID);
                } else {
                    InfoBord.getInfoBord().infoBordRegels.put(busID, message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
