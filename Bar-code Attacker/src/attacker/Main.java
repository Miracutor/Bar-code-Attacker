package attacker;

import java.io.File;
import java.io.IOException;



import attacker.engine.Archer;
import attacker.engine.Boss;
import attacker.engine.Brawler;
import attacker.engine.Magician;
import attacker.engine.Player;
import attacker.engine.Skill;
import attacker.engine.Swordman;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage primaryStage;
	public static Stage LittleStage;
	private static BorderPane mainLayout;
	public static BorderPane BattleLayout;
	private static Player P1;
	private static Player P2;
	public static boolean arcade;
	public static boolean vs;
	//Test Character
	private static Swordman c1=new Swordman("Ken",993399,5000,450,100,200,50,18);
	private static Archer c2=new Archer("Yumi",993366,4400,425,200,150,180,22);
	private static Brawler c3=new Brawler("Haku",993366,5500,480,40,120,90,16);
	private static Magician c4=new Magician("Maho",993366,4200,250,400,140,250,20);
	
	//Enemy
	private static Swordman e1=new Swordman("General of Calm Storm",000000000000,9000,350,50,220,150,10);
	private static Archer e2=new Archer("General of Faceless Fanatical",000000000000,7000,300,100,180,120,15);
	private static Magician e3=new Magician("General of Manical Murderer",000000000000,6000,50,400,80,250,8);
	private static Brawler e4=new Brawler("General of Dark Madness",000000000000,11000,400,0,30,10,5);
	private static Boss lastboss=new Boss("QR Code of Ruthless Ruler",666,10000,350,300,150,150,10);
	//Enemy end
	
	@Override
	public void start(Stage primaryStage) throws IOException{
		P1=new Player();
		P2=new Player();
		Main.primaryStage = primaryStage;
		Main.primaryStage.setTitle("Bar-code Attacker");
		primaryStage.getIcons().add(new Image("icon.png"));
		primaryStage.setResizable(false);
		loadTitle();
		LittleStage=new Stage();
		LittleStage.getIcons().add(new Image("icon.png"));
		Main.LittleStage.initModality(Modality.WINDOW_MODAL);
		LittleStage.initOwner(primaryStage);
		LittleStage.setResizable(false);
		enter_skill();
		
		P1=new Player(c1,"MANUAL");
	    P2=new Player(c2,"MANUAL");
		
		arcade=false;
		vs=false;
	}
	
	public void enter_skill()
	{
		c1.addSkill(new Skill("Slant",2.2,"PHY","A single hit\nsword skill.",12));
		c1.addSkill(new Skill("Bigger Slant",3.0,"PHY","Basicially, Slant\nbut harder.",30));
		c1.addSkill(new Skill("Superior Slant",3.8,"PHY","Basically, Bigger Slant\nbut more superior.",40));
		c1.addSkill(new Skill("Rampage Slant",5.2,"PHY","Slant that executed\nin rampage mode.",100));
		c1.addSkill(new Skill("Calm",-3.2,"HOL","Calm yourself, bro...",70));
		
		c2.addSkill(new Skill("Multi-hit",2.8,"PHY","A multi-hit archery skill.\nHow Impressive!",25));
		c2.addSkill(new Skill("Straight Pierce",3.0,"PHY","An attack that filled\nwith calmness and honesty.",30));
		c2.addSkill(new Skill("Eleventh Arrow",5.3,"PHY","A 11-hit archery skill.\nThis even impressed the last boss.",100));
		c2.addSkill(new Skill("Blast Arrows",3.2,"MAG","Attack with arrows that\nsimmered with magical explosives.",40));
		c2.addSkill(new Skill("Zeroth Stance",-4.0,"HOL","The state of selfless\nthat shown in archery.",60));
		
		c3.addSkill(new Skill("Punchey",2.5,"PHY","A single hit\nbrawler skill.",18));
		c3.addSkill(new Skill("Bigger Punch",3.2,"PHY","Basicially, Punchey\nbut harder.",38));
		c3.addSkill(new Skill("Superior Punch",3.4,"PHY","Basically, Bigger Punch\nbut more superior.",45));
		c3.addSkill(new Skill("Justice Punch",5.1,"PHY","Punch that well...\njustice?",100));
		c3.addSkill(new Skill("No Fear",-2.2,"HOL","Even I die today...",80));
		
		c4.addSkill(new Skill("Big Flare",3.4,"MAG","Create a ball of fire from magic.",50));
		c4.addSkill(new Skill("Breeze Wind",2.2,"MAG","Summon a gentle_wind\nthat make you wonder if\nthese is really an attack.",25));
		c4.addSkill(new Skill("Maniacal Flare",5.0,"MAG","A fanatic version of Big Flare.",100));
		c4.addSkill(new Skill("Yggdrasil",3.0,"MAG","Summmon trees from\nthe Code's ground.",40));
		c4.addSkill(new Skill("Full Restore",-4.0,"HOL","A better version of Restore.\nIs this practically\nUndefeatable?",80));
		
	
	}
	
	public static Player getP1() {
		return P1;
	}

	public static Player getP2() {
		return P2;
	}

	
	public static void loadTitle() throws IOException
	{
		primaryStage.setWidth(600);
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("gui/TitleScreen.fxml"));
		mainLayout=loader.load();
		Scene scene=new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
		//music_title();
	}
	
	public static BorderPane loadWClose() throws IOException
	{
		FXMLLoader loader=new FXMLLoader();
	    loader.setLocation(Main.class.getResource("gui/CloseWindow.fxml"));
	    BorderPane WindowClose=loader.load();
	    return WindowClose;
	}
	
	
	private static void loadPad_P1() throws IOException
	{
		primaryStage.setWidth(910);
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("gui/SkillPad-P1.fxml"));
		BorderPane PadP1=loader.load();
		BattleLayout.setLeft(PadP1);
	}
	
	private static void loadPad_P2() throws IOException
	{
		primaryStage.setWidth(910);
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("gui/SkillPad-P2.fxml"));
		BorderPane PadP2=loader.load();
		BattleLayout.setRight(PadP2);
	}
	
	private static void load_battleStage() throws IOException
	{
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("gui/BattleStage.fxml")); 
		BattleLayout=loader.load();
		BattleLayout.setPrefWidth(100);
		Scene scene=new Scene(BattleLayout);
		primaryStage.setScene(scene);
	}
	
	private static void load_Story() throws IOException
	{
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("gui/StoryScreen.fxml")); 
		mainLayout=loader.load();
		Scene scene=new Scene(mainLayout);
		primaryStage.setScene(scene);
	}
	
	public static void pickmode(int pick) throws IOException
	{
		if(pick==0)//Arcade
		{
			load_Story();
			arcade=true;
			//mode_Arcade();
		}
		else if(pick==1)//VSMode
		{
			vs=true;
			mode_VS();
		}
		else if(pick==2)//Character List
		{
			pickCharacter();
		}
		else if(pick==3)
			Add_Charcter();
		else
			primaryStage.close();
	}
	
	public static void music_title()
	{
		File mu=new File("src\\back.mp3");
		Media back_music=new Media(mu.toURI().toString());
		MediaPlayer mp=new MediaPlayer(back_music);
		Runnable playMusic=()-> mp.play();  
		//mp.setOnReady(playMusic);  
		mp.setAutoPlay(true);
		//mp.setOnEndOfMedia(playMusic);
	}
	
	public static void mode_VS() throws IOException
	{
		load_battleStage();
		loadPad_P1();
		loadPad_P2();
	}

	
	public static void Add_Charcter() throws IOException
	{	
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("gui/CreateCharacter.fxml"));
		mainLayout=loader.load();
		Scene scene=new Scene(mainLayout);
		primaryStage.setScene(scene);
	}
	
	public static void pickCharacter() throws IOException
	{
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("gui/CharacterSelect.fxml"));
		mainLayout=loader.load();
		Scene scene=new Scene(mainLayout);
		primaryStage.setScene(scene);
	}
	
	public static void pauseGame() throws IOException
	{
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("gui/PauseWindow.fxml"));
		Scene scene=new Scene(loader.load());
		LittleStage.setTitle("Pause Game?");
		LittleStage.setScene(scene);
		LittleStage.show();
	}

	public static boolean isArcade() {
		return arcade;
	}

	public static boolean isVs() {
		return vs;
	}
	
	public static void setCharacter(int which,int character,String mode)
	{
		if(which==0)
		{
			if(character==0)
				P1=new Player(c1,mode);
			else if(character==1)
				P1=new Player(c2,mode);
			else if(character==2)
				P1=new Player(c3,mode);
			else
				P1=new Player(c4,mode);
		}
		else
		{
			if(character==0)
				P2=new Player(c1,mode);
			else if(character==1)
				P2=new Player(c2,mode);
			else if(character==2)
				P2=new Player(c3,mode);
			else
				P2=new Player(c4,mode);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
