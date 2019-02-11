package attacker.engine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class BattleAI {
	
	private String mode;//Default value is NONE. SWORDMAN, MAGICIAN, BRAWLER, ARCHER.
	private int ap_now;
	private ArrayList<Integer> PHY_list;//the list of PHY skill this hero has
	private ArrayList<Integer> MAG_list;//the list of MAG skill this hero has
	private ArrayList<Integer> HOL_list;//the list of HOL skill this hero has
	//the list refer to index of the main Skill list.
	
	private boolean flag1;//If the player still have more HP but not full
	private boolean flag2;//If the player have lower than 30% of its HP 
	
	private boolean no_MAG;//If the player don't have any MAG skill
	private boolean no_HOL;//If the player don't have any HOL skill
	
	private LinkedList<Skill> list_skill;
	
	public String getMode() {
		return mode;
	}

	Random rnd=new Random();
	//Random Number Generator for AI
	//How to use... rnd.nextInt(100)+1
	//Generate number between 1-100
	
	//////-Most Important Rule-//////
	///-If the unit cannot use any skill even base skill OR its AP is zero, use skill "Fill AP".-///
	
	///---Reminder----///
	//0-Base   /// 
	//1-Fill AP///
	//2-3-4-5-6-Other skill///
	
	//1.Divide an AI into 3 states above.
	//2.In each state, generate random number generator from 1 to 100.
	//3. Using if-else statement, state the possibility the unit using PHY, MAG, HOL, Base Attack(0), Fill AP(1).
	//4. Refer to flag, no_MAG and no_HOL and filter.
	//5. Using generated number, COMP will pick skill type.
	//6. Use RNG again. This time picking from type list, for example,
	//   computer pick PHY. Then, another rng will used to pick PHY skill from PHY list.
	//7. Skill picked must lower or equal ap_cost will remaining character AP(ap_now).
	//8. If not, another rng generated, and pick again from PHY list not full skill list.
	//9. If full requirement just return like example: return PHY_list.get(i);
	//10. Do same for another two states and other 3 classes.
	
	
	private void checkFlags(int HP,int remain,int ap)
	{
		ap_now=ap;
		
		if(HP<remain && 0.2*HP<remain)
		{
			flag1=true;
			flag2=false;
		}
		else
		{
			flag1=false;
			flag2=true;
		}
	}
	
	private boolean check_cost(int n)
	{
		if(list_skill.get(n).getAp_cost()<=ap_now)
			return true;
		
		return false;
	}
	
	private boolean check_cost(ArrayList<Integer> data)
	{
		boolean status=false;
		
		if(data.isEmpty())
			return status;
		
		for(int i=0;i<data.size();i++)
		{
			if(list_skill.get(data.get(i)).getAp_cost()>ap_now)
				status=false;
			else
				status=true;
		}
		
		return status;
	}
	
	public void updateSkill(LinkedList<Skill> list)
	{	
		int a=0,b=0,c=0;
		no_MAG=true;
		no_HOL=true;
		PHY_list=new ArrayList<Integer>();
		MAG_list=new ArrayList<Integer>();
		HOL_list=new ArrayList<Integer>();
		list_skill=list;
		
		for(int i=2;i<=6;i++)//Check availability of MAG or HOL spells in list.
		{
			if(list.get(i).getType()=="PHY")
			{
				PHY_list.add(a, i);
				a++;
			}
			else if(list.get(i).getType()=="MAG")
			{
				no_MAG=false;
				MAG_list.add(b, i);
				b++;
			}
			else if(list.get(i).getType()=="HOL")
			{
				no_HOL=false;
				HOL_list.add(c, i);
				c++;
			}

		}
	}
	
	public int autoSkill(int HP,int remain,int ap)
	{
		checkFlags(HP,remain,ap);
		
		int rng=rnd.nextInt(100)+1;
		if(flag1==true)
		{
			if((rng<=100 && ap==0)||(check_cost(PHY_list)==false && check_cost(MAG_list)==false && check_cost(HOL_list)==false))
				return 1;
			else if(rng>=80 && rng<=100 && check_cost(0)==true)
				return 0;
			else if(rng>=50 && rng<80 && check_cost(PHY_list)==true)
			{
				rng=rnd.nextInt(PHY_list.size());
				while(check_cost(PHY_list.get(rng)))
				{
					rng=rnd.nextInt(PHY_list.size());
				}
				return PHY_list.get(rng);
			}
			else if(rng>=50 && rng<80 && check_cost(MAG_list)==true)
			{
				rng=rnd.nextInt(MAG_list.size());
				while(check_cost(MAG_list.get(rng)))
				{
					rng=rnd.nextInt(MAG_list.size());
				}
				return MAG_list.get(rng);
			}
			else if(rng>=50 && rng<80 && check_cost(HOL_list)==true)
			{
				rng=rnd.nextInt(HOL_list.size());
				while(check_cost(HOL_list.get(rng)))
				{
					rng=rnd.nextInt(HOL_list.size());
				}
				return HOL_list.get(rng);
			}
			else if(rng>=50 && rng<80 && check_cost(0)==true)
				return 0;
			else if(rng>=20 && rng<50 && check_cost(MAG_list)==true)
			{
				rng=rnd.nextInt(MAG_list.size());
				while(check_cost(MAG_list.get(rng)))
				{
					rng=rnd.nextInt(MAG_list.size());
				}
				return MAG_list.get(rng);
			}
			else if(rng>=20 && rng<50 && check_cost(HOL_list)==true)
			{
				rng=rnd.nextInt(HOL_list.size());
				while(check_cost(HOL_list.get(rng)))
				{
					rng=rnd.nextInt(HOL_list.size());
				}
				return HOL_list.get(rng);
			}
			else if(rng>=20 && rng<50 && check_cost(PHY_list)==true)
			{
				rng=rnd.nextInt(PHY_list.size());
				while(check_cost(PHY_list.get(rng)))
				{
					rng=rnd.nextInt(PHY_list.size());
				}
				return PHY_list.get(rng);
			}
			else if(rng>=20 && rng<50 && check_cost(0)==true)
				return 0;
			else if(rng>=0 && rng<20 && check_cost(HOL_list)==true)
			{
				rng=rnd.nextInt(HOL_list.size());
				while(check_cost(HOL_list.get(rng)))
				{
					rng=rnd.nextInt(HOL_list.size());
				}
				return HOL_list.get(rng);
			}
			else if(rng>=0 && rng<20 && check_cost(MAG_list)==true)
			{
				rng=rnd.nextInt(MAG_list.size());
				while(check_cost(MAG_list.get(rng)))
				{
					rng=rnd.nextInt(MAG_list.size());
				}
				return MAG_list.get(rng);
			}
			else if(rng>=0 && rng<20 && check_cost(PHY_list)==true)
			{
				rng=rnd.nextInt(PHY_list.size());
				while(check_cost(PHY_list.get(rng)))
				{
					rng=rnd.nextInt(PHY_list.size());
				}
				return PHY_list.get(rng);
			}
			else if(rng>=0 && rng<20 && check_cost(0)==true)
				return 0;
			else
				return 1;		
		}
		else
		{
			if((rng<=100 && ap==0)||(check_cost(PHY_list)==false && check_cost(MAG_list)==false && check_cost(HOL_list)==false))
				return 1;
			else if(rng>=75 && rng<=100 && check_cost(0)==true)
				return 0;
			else if(rng>=40 && rng<75 && check_cost(PHY_list)==true)
			{
				rng=rnd.nextInt(PHY_list.size());
				while(check_cost(PHY_list.get(rng)))
				{
					rng=rnd.nextInt(PHY_list.size());
				}
				return PHY_list.get(rng);
			}
			else if(rng>=40 && rng<75 && check_cost(MAG_list)==true)
			{
				rng=rnd.nextInt(MAG_list.size());
				while(check_cost(MAG_list.get(rng)))
				{
					rng=rnd.nextInt(MAG_list.size());
				}
				return MAG_list.get(rng);
			}
			else if(rng>=40 && rng<75 && check_cost(HOL_list)==true)
			{
				rng=rnd.nextInt(HOL_list.size());
				while(check_cost(HOL_list.get(rng)))
				{
					rng=rnd.nextInt(HOL_list.size());
				}
				return HOL_list.get(rng);
			}
			else if(rng>=40 && rng<75 && check_cost(0)==true)
				return 0;
			else if(rng>=20 && rng<40 && check_cost(MAG_list)==true)
			{
				rng=rnd.nextInt(MAG_list.size());
				while(check_cost(MAG_list.get(rng)))
				{
					rng=rnd.nextInt(MAG_list.size());
				}
				return MAG_list.get(rng);
			}
			else if(rng>=20 && rng<40 && check_cost(HOL_list)==true)
			{
				rng=rnd.nextInt(HOL_list.size());
				while(check_cost(HOL_list.get(rng)))
				{
					rng=rnd.nextInt(HOL_list.size());
				}
				return HOL_list.get(rng);
			}
			else if(rng>=20 && rng<40 && check_cost(PHY_list)==true)
			{
				rng=rnd.nextInt(PHY_list.size());
				while(check_cost(PHY_list.get(rng)))
				{
					rng=rnd.nextInt(PHY_list.size());
				}
				return PHY_list.get(rng);
			}
			else if(rng>=20 && rng<40 && check_cost(0)==true)
				return 0;
			else if(rng>=0 && rng<20 && check_cost(HOL_list)==true)
			{
				rng=rnd.nextInt(HOL_list.size());
				while(check_cost(HOL_list.get(rng)))
				{
					rng=rnd.nextInt(HOL_list.size());
				}
				return HOL_list.get(rng);
			}
			else if(rng>=0 && rng<20 && check_cost(MAG_list)==true)
			{
				rng=rnd.nextInt(MAG_list.size());
				while(check_cost(MAG_list.get(rng)))
				{
					rng=rnd.nextInt(MAG_list.size());
				}
				return MAG_list.get(rng);
			}
			else if(rng>=0 && rng<20 && check_cost(PHY_list)==true)
			{
				rng=rnd.nextInt(PHY_list.size());
				while(check_cost(PHY_list.get(rng)))
				{
					rng=rnd.nextInt(PHY_list.size());
				}
				return PHY_list.get(rng);
			}
			else if(rng>=0 && rng<20 && check_cost(0)==true)
				return 0;
			else
				return 1;		
		}
	}
	
	BattleAI()
	{
		mode="NONE";
		PHY_list=null;
		MAG_list=null;
		HOL_list=null;
		flag1=false;
		flag2=false;
		no_MAG=false;
		no_HOL=false;
		ap_now=200;
		list_skill=null;
	}
	
	BattleAI(String m,LinkedList<Skill> list)
	{
		mode=m;
		flag1=true;
		flag2=false;
		no_MAG=false;
		no_HOL=false;
		ap_now=200;
		list_skill=list;
		updateSkill(list);
	}

}
