package attacker.engine;

import java.util.LinkedList;

public class Hero {
	
	private String name;
	private long code;
	private int HP;//Health point Max: 10,000
	private int AP=200;//Action point Max: 200, Same to all character
	private int ATK;//Attack stat Max: 800
	private int MAG;//Magic stat Max: 800
	private int PDEF;//Physical defense Max: 600
	private int MDEF;//Magical defense Max: 600
	private int EVA;//Evade stat Max: 25
	private LinkedList<Skill> skill_list; //Skill list: base skill,fill ap, other 5 skill
	private int skill_num;
	private Skill fill_ap=new Skill("Fill AP",1.0,"AP","Relax a little.\nTake a turn to fill AP.",0);
	private String class_name;
	private BattleAI HeroAI;
	
	public String getName() {
		return name;
	}
	public long getCode() {
		return code;
	}
	public int getHP() {
		return HP;
	}
	public int getAP() {
		return AP;
	}
	public int getATK() {
		return ATK;
	}
	public int getMAG() {
		return MAG;
	}
	public int getPDEF() {
		return PDEF;
	}
	public int getMDEF() {
		return MDEF;
	}
	public int getEVA() {
		return EVA;
	}
	public LinkedList<Skill> getSkill_list() {
		return skill_list;
	}
	public int getSkill_num() {
		return skill_num;
	}
	public BattleAI getHeroAI() {
		return HeroAI;
	}
	public void setStats(String nam,long bar,int a,int c,int d,int e,int f,int g,Skill s)
	{
		name=nam;
		code=bar;
		HP=a;
		ATK=c;
		MAG=d;
		PDEF=e;
		MDEF=f;
		EVA=g;
		skill_list.add(skill_num, s);
		
		if(a>10000 || c>800 || d>800 || e>600 || f>600 || g>25)
		{
			System.out.print("WARNING! There are one or more status values of this character ");
			System.out.print("(");
			System.out.print(name);
			System.out.println(") exceed the limit.");
		}
	}
	public void addSkill(Skill s)
	{
		if(skill_num==0 && skill_list.get(0)==null)
			skill_num=0;
		else
			skill_num++;
		
		skill_list.add(skill_num,s);
		if(HeroAI!=null)
			HeroAI.updateSkill(skill_list);
	}
	public int useSkill(int n,Player<?> enemy)
	{
		String type=skill_list.get(n).getType();
		if(type=="PHY")
			return skill_list.get(n).damage_calc(ATK-enemy.getInslot().getPDEF());
		else if(type=="MAG")
			return skill_list.get(n).damage_calc(MAG-enemy.getInslot().getMDEF());
		else if(type=="HOL")
			return skill_list.get(n).damage_calc((PDEF+MDEF)/2);
		else
			return 0;
	}
	public void setAI()
	{
		HeroAI=new BattleAI(class_name,skill_list);
	}
	
	public void changeClass(String change)
	{
		class_name=change;//Only for last boss
	}
	Hero()
	{
		skill_list=new LinkedList<Skill>();
		skill_num=0;
		setStats(" ",0,0,0,0,0,0,0,null);
		class_name=" ";
	}
	Hero(String nam,long bar,int a,int c,int d,int e,int f,int g,Skill s,String cla)
	{
		skill_list=new LinkedList<Skill>();
		skill_num=0;
		setStats(nam,bar,a,c,d,e,f,g,s);
		class_name=cla;
		addSkill(fill_ap);
		HeroAI=null;
	}
	
}
