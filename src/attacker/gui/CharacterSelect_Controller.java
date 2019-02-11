package attacker.gui;

import java.io.IOException;

import attacker.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;

public class CharacterSelect_Controller {
	
	@FXML
	public ListView<String> listclass;
	@FXML
	public ToggleButton autotoggle;
	@FXML
	public Button gobutton;
	@FXML
	public Button savebutton;
	@FXML
	public ChoiceBox<Integer> pickPlayer;
	
	private String mode;
	
	
	CharacterSelect_Controller other;
	
	public void initialize()
	{
		listclass.getItems().add("Swordman\nKen");
		listclass.getItems().add("Archer\nYumi");
		listclass.getItems().add("Brawler\nHaku");
		listclass.getItems().add("Magician\nMaho");
		
		pickPlayer.getItems().add(1);
		pickPlayer.getItems().add(2);
		autotoggle.setDisable(true);
	}
	
	public int picked()
	{
		return listclass.getSelectionModel().getSelectedIndex();	
	}
	
	public String getmode()
	{
		if(autotoggle.isSelected()==true)
			return "AUTO";
		else
			return "MANUAL";
	}
	
	public void returnTitle() throws IOException
	{
		Main.loadTitle();
	}
	
	public void savePlayer()
	{
		int which=pickPlayer.getSelectionModel().getSelectedIndex();
		int character=picked();
		
		mode=getmode();
		
		Main.setCharacter(which, character, mode);
		
	}
	

}
