package attacker.engine;

public class Boss extends Hero {
	
	private static Skill base_skill=new Skill("Darkness Attack",1.1,"PHY","A normal attack from QR Code.\nIt has darkness element.",10);
	private static Skill special_skill=new Skill("Darkness Blast",2.2,"PHY","An attack that QR Code filled a part of his enermous power",100);
	
	public Boss(String nam,long bar,int a, int c, int d, int e, int f, int g) {
		super(nam,bar,a, c, d, e, f, g,base_skill,"ARCHER");
	}
	
	public void updateState(int remain)
	{
		double perHP=(remain/getHP())*100;
		
		if(perHP==100)
			changeClass("ARCHER");
		else if(perHP<100 && perHP>75)
			changeClass("MAGICIAN");
		else if(perHP<75 && perHP>35)
			changeClass("SWORDMAN");
		else
			changeClass("BRAWLER");
		setAI();
	}
	
}
