package attacker.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

import attacker.Main;


public class Title_Controller_UI {
	
	//Title
	@FXML
	public ChoiceBox<String> battle_mode;
	private int select_mode;
	
	public void initialize()
	{
		set_title();
		battle_mode.getSelectionModel().select(0);//default
	}
	
	private final ChangeListener<Number> SelectCheck=new ChangeListener<Number>()
	{//Listener to check box
		public void changed(ObservableValue ov,Number value, Number new_value)
		{
			select_mode=new_value.intValue();
			String status=String.join(" ", "User picked mode",String.valueOf(select_mode));
			status=String.join("", status, ".");
			System.out.println(status);
		}
	};
	
	@FXML
	public void set_title()
	{
		ObservableList<String> data=FXCollections.observableArrayList();
		data.add(0,"Game Background");
		data.add(1,"VS");
		data.add(2,"Character Select");
		data.add(3,"Add New Chracter");
		data.add(4,"Exit Game");
		
		battle_mode.setItems(data);
		battle_mode.getSelectionModel().selectedIndexProperty().addListener(SelectCheck);
	}
	
	@FXML
	public void start_mode() throws IOException
	{
		String status;
		if(select_mode!=4)
		{
			status=String.join(" ", "User start the game with mode",String.valueOf(select_mode));
		    status=String.join("", status, ".");
		}
		else
		{
			status="User closed the game. Thank you.";
		}
			
		System.out.println(status);
		Main.pickmode(select_mode);
	}
	
	public int get_mode()
	{
		return select_mode;
	}
	

}
