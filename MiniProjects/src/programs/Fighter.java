package programs;

import java.util.Random;

public class Fighter {
	public String name;
	public int hitpoints, maxDamage;
	private Random random;
	
	public Fighter(String name, int hitpoints, int maxDamage) {
		this.name = name;
		this.hitpoints = hitpoints;
		this.maxDamage = maxDamage;
		
		this.random = new Random();
	}
	
	public Fighter() {
		this.name = "Default Fighter";
		this.hitpoints = 100;
		this.maxDamage = 35;
		
		this.random = new Random();
	}
	
	public int attackOtherFighter(Fighter opponent) {
		if (random.nextInt(100) < 80) {
			int damage = maxDamage - random.nextInt(maxDamage);
			opponent.hitpoints -= damage;
			return damage;
		}
		else {
			return -1;
		}
			
	}
}