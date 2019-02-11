package attacker.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

import attacker.Main;
import attacker.engine.Skill;

public class SkillPad_Controller_UI_P2 {
	
	
	//SkillPad
	@FXML
	private ProgressBar hp_bar;
	@FXML
	private ProgressBar ap_bar;
	@FXML
	private Text remain_hp;
	@FXML
	private Text total_hp;
	@FXML
	public Text remain_ap;
	@FXML
	private Text total_ap;
	@FXML
	private Label chra_name;
	@FXML
	public ListView<String> skills;
	@FXML
	private Button usebutton;
	@FXML
	ScrollPane scroll_list;
	//End of SkillPad Items
	
	private static int fullHP;
	private int currentSelect;
	public static SkillPad_Controller_UI_P2 other;
	
	
	public void update_status(int reHP,int reAP)
	{
		if(reHP<0)
			reHP=0;
		
		remain_hp.setText(String.valueOf(reHP));
		remain_ap.setText(String.valueOf(reAP));
		hp_bar.setProgress((double)reHP/fullHP);
		ap_bar.setProgress((double)reAP/200);
		BattleStage_Controller.other.checkDead();
	}
	
	public boolean PlayerisDead()
	{
		return remain_hp.getText()==String.valueOf(0);
	}
	
	public void pause_skill()
	{
		skills.setDisable(true);
		usebutton.setDisable(true);
	}
	
	public void play_skill()
	{
		skills.setDisable(false);
		usebutton.setDisable(false);
	}
	
	@FXML
	public void initialize() throws IOException
	{	
		fullHP=Main.getP2().getInslot().getHP();
		int HP=fullHP;
		int AP=200;
		
		total_hp.setText(String.valueOf(HP));
		total_ap.setText(String.valueOf(200));
		remain_hp.setText(String.valueOf(HP));
		remain_ap.setText(String.valueOf(AP));
		hp_bar.setProgress(1);
	    ap_bar.setProgress(1);
		chra_name.setText(Main.getP2().getInslot().getName());
		other=getthis();
		
		if(Main.getP2().getMode()=="AUTO")
		{
			pause_skill();
		}
		else
		{
			insert_skills();
		    skills.getSelectionModel().selectedIndexProperty().addListener(SelectCheck);
		    pause_skill();
		}
	}
	
	public SkillPad_Controller_UI_P2 getthis() {
		return this;
	}
	
	private final ChangeListener<Number> SelectCheck=new ChangeListener<Number>()
	{//Listener to check box
		public void changed(ObservableValue ov,Number value, Number new_value)
		{
			currentSelect=new_value.intValue();
		}

	};
	

	public void useSelectSkill() throws IOException
	{
		if(Main.getP2().check_skillcost(currentSelect)==true)
		{
			if(Main.getP2().getInslot().getSkill_list().get(currentSelect).getType()!="HOL")
				Main.getP2().useSkill(currentSelect, Main.getP1());
			else
				Main.getP2().useSkill(currentSelect, Main.getP2());
			
		    update_status(Main.getP2().getPlayerHP(),Main.getP2().getAp());
		    SkillPad_Controller_UI_P1.other.update_status(Main.getP1().getPlayerHP(),Main.getP1().getAp());
		    if(Main.getP1().isDead()==false && Main.getP1().isDead()==false)
		    {
		    	pause_skill();
		        SkillPad_Controller_UI_P1.other.play_skill();
		    }
		    else
		    {
		    	pause_skill();
		        SkillPad_Controller_UI_P2.other.play_skill();
		    }
		    
		}
		else
			warn_notenoughAP();
		
	}
	
	public void warn_notenoughAP() throws IOException
	{
		String status=String.join(" ",Main.getP2().getInslot().getName(), "don't have enough AP.\nRelax a little bit, Player 2.");
		Text warn=new Text(status);
		BorderPane window=new BorderPane();
		window.setCenter(warn);
		window.setBottom(Main.loadWClose());
		
		Main.LittleStage.setScene(new Scene(window));
		Main.LittleStage.setTitle("Warning!");
		Main.LittleStage.setWidth(300);
		Main.LittleStage.setHeight(200);
		Main.LittleStage.show();
	}
	
	@FXML
	public void insert_skills()
	{
		LinkedList<Skill> data_org=Main.getP2().getInslot().getSkill_list();
		ObservableList<String> data=FXCollections.observableArrayList();
		for(int i=0; i<7; i++)
		{
			String skill_now=String.join("\n", data_org.get(i).getName(),data_org.get(i).getDescription());
			data.add(i, skill_now);
		}
		skills.setItems(data);
	}

}
