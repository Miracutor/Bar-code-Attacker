package attacker.gui;

import attacker.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CloseWindow_Controller {
	
	@FXML
	private Button closeButton;
	
	public void onClose()
	{
		Main.LittleStage.close();
	}
	
	

}
