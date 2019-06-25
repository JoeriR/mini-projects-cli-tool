package programs;

public class BattleSimulator {
	
	public void simulateBattle(Fighter fighter1, Fighter fighter2) {
		
		System.out.println("Starting a fight between " + fighter1.name + " and " + fighter2.name);
		
		int roundCounter = 1;
		
		while (fighter1.hitpoints > 0 && fighter2.hitpoints > 0) {
			
			System.out.println(String.format("\nRound %d: %s %dhp, %s %dhp", roundCounter, fighter1.name, fighter1.hitpoints, fighter2.name, fighter2.hitpoints));
			
			int damage1 = fighter1.attackOtherFighter(fighter2);
			
			if (damage1 < 0)
				System.out.println("" + fighter1.name + " missed!");
			else
				System.out.println(String.format("%s hits %s for %d damage", fighter1.name, fighter2.name, damage1));
			
			int damage2 = fighter2.attackOtherFighter(fighter1);
			
			if (damage2 < 0)
				System.out.println("" + fighter2.name + " missed!");
			else
				System.out.println(String.format("%s hits %s for %d damage", fighter2.name, fighter1.name, damage2));
			
			++roundCounter;
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				
			}
			
		}
		
		if (fighter1.hitpoints <= 0 && fighter2.hitpoints <= 0) {
			System.out.println("\nDouble knockout!");
			System.out.println("It's a tie!");
		}
		else if (fighter1.hitpoints <= 0) {
			System.out.println("\nKnockout!");
			System.out.println("" + fighter2.name + " wins!");
		}
		else if (fighter2.hitpoints <= 0) {
			System.out.println("\nKnockout!");
			System.out.println("" + fighter1.name + " wins!");
		}
		
		System.out.println(String.format("\nResult: %d: %s %dhp, %s %dhp", roundCounter, fighter1.name, fighter1.hitpoints, fighter2.name, fighter2.hitpoints));
		
	}
}
