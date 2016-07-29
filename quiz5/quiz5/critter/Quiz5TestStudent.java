package quiz5.critter;

public class Quiz5TestStudent {

	public static void main(String[] args) {
		
		// Add Algae and 1 F
		Critter.addCritter ("Algae", 0, 0);
		Critter.addCritter ("Algae", 0, 1);
		Critter.addCritter ("Algae", 0, 2);
		Critter.addCritter ("Algae", 1, 0);
		Critter.addCritter ("Algae", 2, 0);
		Critter.addCritter ("Algae", 2, 1);
		Critter.displayWorld();
		Critter c = new FriendlyCritter();
		c.setX_coord(1);
		c.setY_coord(1);
		Critter.addCritter(c);
		Critter.displayWorld();		
		
		// Make the single F walk
		Critter.worldTimeStep();
		Critter.displayWorld();	

	}
}
