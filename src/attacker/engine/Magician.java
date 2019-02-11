package attacker.engine;

public class Magician extends Hero {

	private static Skill base_skill=new Skill("Hit",0.8,"PHY","A desprate hit from magician with her wand when she out of mana.",5);
	public Magician() {
	}

	public Magician(String nam,long bar,int a, int c, int d, int e, int f, int g) {
		super(nam,bar,a, c, d, e, f, g,base_skill,"MAGICIAN");
	}

}
