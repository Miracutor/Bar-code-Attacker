package attacker.gui;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import attacker.Main;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class StoryController {
	
	@FXML
	private Text storytext;
	
	private int click;
	
	public void initialize() throws InterruptedException
	{
		storytext.setText("Once upon a time,\nthere are a Planet called Codes.");
		click=1;
	}
	
	public void story_process() throws IOException
	{
		click++;
		if(click==2)
			storytext.setText("In this planet,\nBar Codes are living happily\nin harmony.");
		else if(click==3)
			storytext.setText("Until, someone appear...");
		else if(click==4)
			storytext.setText("He is QR Code.\nProclaiming himself\nas a higher being\nthan other Bar Codes,");
		else if(click==5)
			storytext.setText("he and his army invaded the planet\nand ruled the planet\nwith his tyranny.");
		else if(click==6)
			storytext.setText("Since hundred year ago,\nBar Codes suffering hopelessly\nuntil among them\nappear a group of chosen heroes...");
		else if(click==7)
			storytext.setText("They called \"Bar-codes Attacker\"!");
		else if(click==8)
			storytext.setText("Thus,\ncan they defeat QR Code\nand achieve the best ending!?");
		else
		{
			System.out.println("The end.");
			Main.loadTitle();
		}
	}

}
