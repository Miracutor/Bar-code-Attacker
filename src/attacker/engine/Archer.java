package attacker.engine;

public class Archer extends Hero {

	private static Skill base_skill=new Skill("Hit",0.6,"PHY","A desprate hit from archer with his bow when he out of arrows.",2);
	public Archer() {
	}

	public Archer(String nam,long bar,int a, int c, int d, int e, int f, int g) {
		super(nam,bar,a, c, d, e, f, g,base_skill,"ARCHER");
	}
}
