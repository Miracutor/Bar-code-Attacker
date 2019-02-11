package attacker.engine;

public class Skill {
	
	private String name;
	private double multiplier;
	private String type;
	//Only 'PHY' for physical attack or 
	//'MAG' for magical attack or 
	//'HOL' for healing skill or AP for AP-related.
	private String description;
	private int ap_cost;
	public String getName() {
		return name;
	}
	public double getMultiplier() {
		return multiplier;
	}
	public String getType() {
		return type;
	}
	public String getDescription() {
		return description;
	}
	public int getAp_cost() {
		return ap_cost;
	}
	public void setSkill(String n,double m,String t,String d,int ap)
	{
		name=n;
		multiplier=m;
		type=t;
		description=d;
		ap_cost=ap;
	}
	public int damage_calc(int base)//base is value of status used for this skill
	{
		int damage=0;
		damage=(int) (base*multiplier);
		if(damage<=0 && type!="HOL")
			damage=1;
		return damage;
	}
	Skill()
	{
		setSkill(" ",0.0," "," ",0);
	}
	public Skill(String n,double m,String t,String d,int ap)
	{
		setSkill(n,m,t,d,ap);
	}

}
