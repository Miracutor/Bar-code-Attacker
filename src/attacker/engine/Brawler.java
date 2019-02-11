package attacker.engine;

public class Brawler extends Hero {

	private static Skill base_skill=new Skill("Punch",1.2,"PHY","A passionate punch from brawler.\nThis attack is used normally.",10);
	public Brawler() {
	}

	public Brawler(String nam,long bar,int a, int c, int d, int e, int f, int g) {
		super(nam,bar,a, c, d, e, f, g,base_skill,"BRAWLER");
	}

}
