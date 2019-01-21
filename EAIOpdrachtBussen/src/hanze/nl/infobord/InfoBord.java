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

	static HashMap<String,Integer> laatsteBericht = new HashMap<String,Integer>();
	static HashMap<String,JSONBericht> infoBordRegels = new HashMap<String,JSONBericht>();
	private static InfoBord infobord;
	static int hashValue;
	JFrame scherm;
	private JLabel tijdregel1;
	private JLabel tijdregel2;

	ArrayList<JLabel> rules = new ArrayList<>();
	
	private InfoBord(){
		this.scherm = new JFrame("InfoBord");
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		panel.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));
		this.tijdregel1=new JLabel("Scherm voor de laatste keer bijgewerkt op:");
		this.tijdregel2=new JLabel("00:00:00");
		this.rules.add(new JLabel("-regel1-"));
		this.rules.add(new JLabel("-regel2-"));
		this.rules.add(new JLabel("-regel3-"));
		this.rules.add(new JLabel("-regel4-"));
		panel.add(tijdregel1);
		panel.add(tijdregel2);
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
	

}
