package attacker.gui;

import java.io.IOException;

import attacker.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PauseWindow_Controller {
	
	@FXML
	private Button resumeButton;
	@FXML
	private Button returnButton;
	
	public void resume()
	{
		Main.LittleStage.close();
	}
	
	public void return_title() throws IOException
	{
		Main.LittleStage.close();
		Main.vs=false;
		Main.arcade=false;
		Main.loadTitle();
	}
}
