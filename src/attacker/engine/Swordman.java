package attacker.engine;

public class Swordman extends Hero {

	private static Skill base_skill=new Skill("Slash",1.0,"PHY","A firm attack from swordman.\nIt looks cool.",8);
	public Swordman() {
	}

	public Swordman(String nam,long bar,int a, int c, int d, int e, int f, int g) {
		super(nam,bar,a, c, d, e, f, g, base_skill,"SWORDMAN");
	}

}
