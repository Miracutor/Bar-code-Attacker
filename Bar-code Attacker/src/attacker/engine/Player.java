package attacker.engine;

import java.util.Random;

import attacker.Main;
import attacker.gui.BattleStage_Controller;

public class Player<Type extends Hero> {
	
	private Type inslot;
	private int damage_val;
	private boolean dead;
	private int ap;
	private String mode;//AUTO or MANUAL
	
	public Type getInslot() {
		return inslot;
	}
	public int getDamage_val() {
		return damage_val;
	}
	public boolean isDead() {
		return dead;
	}
	public int getAp() {
		return ap;
	}
	public String getMode() {
		return mode;
	}
	public void recieveDamage(int a)
	{
		damage_val=damage_val+a;
		int remain;
		remain=getPlayerHP();
		if(getPlayerHP()<=0)
		{
			dead=true;
			remain=0;
		}
		else
			dead=false;
		
		System.out.print(inslot.getName());
		System.out.print("'s HP is ");
		System.out.print(remain);
		System.out.println(".");
	}
	public int getPlayerHP()
	{
		int a=inslot.getHP()-damage_val;
		return a;
	}
	public int useSkill(int n,Player enemy)//Only MANUAL
	{
		if(mode!="MANUAL")
			return 0;
		
		if(ap<inslot.getSkill_list().get(n).getAp_cost())
		{
			System.out.println("Not enough AP.");
			return 0;
		}
		
		int d=0;
		System.out.print(inslot.getName());
		System.out.print(" ");
		System.out.print("used ");
		System.out.print(inslot.getSkill_list().get(n).getName());
		
		if(inslot.getSkill_list().get(n).getName()=="Fill AP")
		{
			ap=inslot.getAP();
			System.out.println(". Reached relaxed state.");
			
			if(enemy==Main.getP1())
				BattleStage_Controller.notifytext("P2","FULL AP");
			else
				BattleStage_Controller.notifytext("P1","FULL AP");
			
			d=inslot.useSkill(n, enemy);
			return d;
		}
		
		Random rnd=new Random();
		int r=rnd.nextInt(100)+1;
		if(r<=25 && r<=enemy.inslot.getEVA() && inslot.getSkill_list().get(n).getType()!="HOL")//Evasion only have 25% probability to activate in any situation
		{
			String status=String.join(" ", ".", enemy.inslot.getName(), "evaded the attack.");
			System.out.println(status);
			d=0;
			
			if(enemy==Main.getP1())
				BattleStage_Controller.notifytext("P1","EVADE");
			else
				BattleStage_Controller.notifytext("P2","EVADE");
			
			ap=ap-inslot.getSkill_list().get(n).getAp_cost();
			return d;
		}
			
		d=inslot.useSkill(n, enemy);
		String status;
		if(inslot.getSkill_list().get(n).getType()!="HOL")
		{
			status=String.join(" ", ".", "Deal", String.valueOf(d), "damage. ");
		}
		else
			status=String.join(" ", ".", "Heal", String.valueOf(d*-1), "HP. ");
		
		System.out.println(status);
		enemy.recieveDamage(d);
		
		String alt_d;
		if(inslot.getSkill_list().get(n).getType()!="HOL")
			alt_d=String.valueOf(d);
		else
		{
			alt_d=String.valueOf(d*-1);
			alt_d=String.join(" ", "HEAL", alt_d,"HP");
		}
		
		if(enemy==Main.getP1())
			BattleStage_Controller.notifytext("P1",alt_d);
		else
			BattleStage_Controller.notifytext("P2",alt_d);
		
		ap=ap-inslot.getSkill_list().get(n).getAp_cost();
		
		return d;
	}
	public int useSkill(Player enemy)//Only AUTO
	{
		if(mode!="AUTO")
			return 0;
		
		int d=0;
		int n=inslot.getHeroAI().autoSkill(inslot.getHP(), getPlayerHP(),ap);
		System.out.print(inslot.getName());
		System.out.print(" ");
		System.out.print("used ");
		System.out.print(inslot.getSkill_list().get(n).getName());
		
		if(inslot.getSkill_list().get(n).getName()=="Fill AP")
		{
			ap=inslot.getAP();
			System.out.println(". Reached relaxed state.");
			
			if(enemy==Main.getP1())
				BattleStage_Controller.notifytext("P2","FULL AP");
			else
				BattleStage_Controller.notifytext("P1","FULL AP");
			
			d=inslot.useSkill(n, enemy);
			return d;
		}
		
		Random rnd=new Random();
		int r=rnd.nextInt(100)+1;
		if(r<=25 && r<=enemy.inslot.getEVA() && inslot.getSkill_list().get(n).getType()!="HOL")//Evasion only have 25% probability to activate in any situation
		{
			String status=String.join(" ", ".", enemy.inslot.getName(), "evaded the attack.");
			System.out.println(status);
			d=0;
			
			if(enemy==Main.getP1())
				BattleStage_Controller.notifytext("P1","EVADE");
			else
				BattleStage_Controller.notifytext("P2","EVADE");
			
			ap=ap-inslot.getSkill_list().get(n).getAp_cost();
			return d;
		}
			
		d=inslot.useSkill(n, enemy);
		String status;
		if(inslot.getSkill_list().get(n).getType()!="HOL")
		{
			status=String.join(" ", ".", "Deal", String.valueOf(d), "damage. ");
		}
		else
			status=String.join(" ", ".", "Heal", String.valueOf(d*-1), "HP. ");
		
		System.out.println(status);
		enemy.recieveDamage(d);
		
		String alt_d;
		if(inslot.getSkill_list().get(n).getType()!="HOL")
			alt_d=String.valueOf(d);
		else
		{
			alt_d=String.valueOf(d*-1);
			alt_d=String.join(" ", "HEAL", alt_d,"HP");
		}
		
		if(enemy==Main.getP1())
			BattleStage_Controller.notifytext("P1",alt_d);
		else
			BattleStage_Controller.notifytext("P2",alt_d);
		
		ap=ap-inslot.getSkill_list().get(n).getAp_cost();
		
		return d;
	}
	public boolean check_skillcost(int n)
	{
		if(ap>=inslot.getSkill_list().get(n).getAp_cost())
			return true;
		
		return false;	
	}
	public Player()
	{
		inslot=null;
		damage_val=0;
		dead=false;
		ap=0;
		mode="NONE";
	}
	
	public Player(Type t,String m)
	{
		inslot=t;
		damage_val=0;
		dead=false;
		ap=inslot.getAP();
		mode=m;
		if(mode=="AUTO")
			inslot.setAI();
	}

}
