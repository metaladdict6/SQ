package hanze.nl.infobord;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import org.codehaus.jackson.map.ObjectMapper;
//import hanze.nl.tijdtools.InfobordTijdFuncties;

public class InfoBord {

	private static HashMap<String,Integer> laatsteBericht = new HashMap<String,Integer>();
	private static HashMap<String,JSONBericht> infoBordRegels = new HashMap<String,JSONBericht>();
	private static InfoBord infobord;
	private static int hashValue;
	private JFrame scherm;
	private JLabel tijdregel1;
	private JLabel tijdregel2;

	private ArrayList<JLabel> rules = new ArrayList<>();
	
	private InfoBord(){
		this.scherm = new JFrame("InfoBord");
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		panel.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
		this.tijdregel1=new JLabel("Scherm voor de laatste keer bijgewerkt op:");
		this.tijdregel2=new JLabel("00:00:00");
//		this.regel1=new JLabel("-regel1-");
//		this.regel2=new JLabel("-regel2-");
//		this.regel3=new JLabel("-regel3-");
//		this.regel4=new JLabel("-regel4-");
		this.rules.add(new JLabel("-regel1-"));
		this.rules.add(new JLabel("-regel2-"));
		this.rules.add(new JLabel("-regel3-"));
		this.rules.add(new JLabel("-regel4-"));
		panel.add(tijdregel1);
		panel.add(tijdregel2);
//		panel.add(regel1);
//		panel.add(regel2);
//		panel.add(regel3);
//		panel.add(regel4);
		hashValue=0;
		scherm.add(panel);
		scherm.pack();
		scherm.setVisible(true);
	}
	
	public static InfoBord getInfoBord(){
		if(infobord==null){
			infobord=new InfoBord();
		}
		return infobord;
	}
	
	public void setRulesAndTotalTime(){
		String[] infoTekst={"--1--","--2--","--3--","--4--","leeg"};
		int[] aankomsttijden=new int[5];
		int aantalRegels = 0;
		if(!infoBordRegels.isEmpty()){
			CheckArrivalTimes(aantalRegels, aankomsttijden, infoTekst);
		}
		if(checkRepaint(aantalRegels, aankomsttijden)){
			hashValue= getTotalTime(aantalRegels, aankomsttijden);
			repaintInfoBord(infoTekst);
		}
	}

	private void CheckArrivalTimes(int amountOfLines, int[] arrivalTimes, String[] infoTexts){
		for(String busID: infoBordRegels.keySet()){
			JSONBericht regel = infoBordRegels.get(busID);
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
		if(hashValue!=totaalTijden){
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
		for(int i = 0; i < this.rules.size(); i++) {
			JLabel label = this.rules.get(i);
			setJlabel(label, i, infoTekst[i]);
		}
		scherm.repaint();		
	}

	private void setJlabel(JLabel label, int labelNumber, String text){
		label.setText(text);
	}

	public static void verwerktBericht(String incoming){
        try {
			JSONBericht message = new ObjectMapper().readValue(incoming, JSONBericht.class);
			String busID = message.getBusID();
			Integer tijd = message.getTijd();
			if (!laatsteBericht.containsKey(busID) || laatsteBericht.get(busID)<=tijd){
				laatsteBericht.put(busID, tijd);
				if (message.getAankomsttijd()==0){
					infoBordRegels.remove(busID);
				} else {
					infoBordRegels.put(busID, message);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
