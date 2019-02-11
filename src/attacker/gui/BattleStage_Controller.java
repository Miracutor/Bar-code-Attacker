package attacker.gui;

import java.io.IOException;
import java.util.Random;

import attacker.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BattleStage_Controller {
	
	public ImageView youwin;
	public ImageView youlose;
	public static ImageView p1win;
	public ImageView p2win;
	@FXML
	public AnchorPane battle_stick;
	@FXML
	public ImageView battle_back;
	
	public static BattleStage_Controller other; 
	
	public void initialize()
	{
		other=getstage();
		battle_back.setFitHeight(350);
		battle_back.setFitWidth(494);
		battle_back.setPickOnBounds(true);
	}
	
	@FXML
	private void pauseGame() throws IOException
	{
		Main.pauseGame();
	}
	
	public static void notifytext(String position,String text)
	{
		Text notifytext;
		notifytext=new Text();
		notifytext.setText(text);
		notifytext.setFill(Color.WHITE);
		notifytext.setFont(new Font("AR JULIAN", 24));
		
		Random rng=new Random();
		int rd=rng.nextInt(3)+1;
		int pc=0;
		
		
		if(position=="P1")
		{
			if(rd==1)
				pc=17;
			else if(rd==2)
				pc=19;
			else
				pc=15;
		}
		else
		{
			if(rd==1)
				pc=302;
			else if(rd==2)
				pc=298;
			else
				pc=300;
		}
		notifytext.setLayoutX(pc);
		movetext(notifytext);
		other.getpane().getChildren().add(notifytext);
		
		notifytext.setLayoutY(150);
		
		
		
		//other.getpane().getChildren().remove(other.getpane().getChildren().indexOf(notifytext));
	}
	
	private static void movetext(Text notify)
	{
        int i=10;    
	    DoubleProperty move = new SimpleDoubleProperty(i);
        notify.yProperty().bind(move);
        
        Timeline moveout = new Timeline(
                new KeyFrame(Duration.seconds(0.005),         event -> move.setValue(move.getValue()-1)),
                new KeyFrame(Duration.seconds(0.01), event -> move.setValue(move.getValue()-1))
            );
        
        moveout.setAutoReverse(true);
        moveout.setCycleCount(300);
        moveout.play();
}
	
	public void checkDead()
	{
		if(Main.getP1().isDead()==true && Main.isVs()==true)
		{
	        p2win=new ImageView("p2-win.png");
			p2win.setLayoutX(25);
			p2win.setLayoutY(75);
			other.getpane().setOnMouseClicked(handler);
			other.getpane().getChildren().add(p2win);
		}
		
		if(Main.getP1().isDead()==true && Main.isArcade()==true)
		{
			youlose=new ImageView("youlose.png");
			youlose.setLayoutX(25);
			youlose.setLayoutY(75);
			other.getpane().setOnMouseClicked(handler);
			other.getpane().getChildren().add(youlose);
		}
		
		if(Main.getP2().isDead()==true && Main.isVs()==true)
		{
			p1win=new ImageView("p1-win.png");
			p1win.setLayoutX(25);
			p1win.setLayoutY(75);
			other.getpane().setOnMouseClicked(handler);
			other.getpane().getChildren().add(p1win);
		}
		
		if(Main.getP2().isDead()==true && Main.isArcade()==true)
		{
			youwin=new ImageView("youwin.png");
			youwin.setLayoutX(25);
			youwin.setLayoutY(75);
			other.getpane().setOnMouseClicked(handler);
			other.getpane().getChildren().add(youwin);
		}
	}
	
	EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
 
        public void handle(MouseEvent event)
        {
        	try {
				other.getpane().getChildren().remove(4);
				other.getpane().setOnMouseClicked(null);
        		Main.loadTitle();
				Main.arcade=false;
				Main.vs=false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }  
          
};
	
	private BattleStage_Controller getstage()
	{
		return this;
	}
	
	public AnchorPane getpane()
	{
		return battle_stick;
	}


}
