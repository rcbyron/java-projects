package quiz5.critter;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyCritterTester {

    @Test
    public void testMovement() {
    	Critter.population.clear();
    	Critter.babies.clear();
    	
    	// Set up world and add FriendlyCritter with Algae obstacles
		Critter.addCritter ("Algae", 0, 0);
		Critter.addCritter ("Algae", 0, 1);
		Critter.addCritter ("Algae", 0, 2);
		Critter.addCritter ("Algae", 1, 1);
		Critter.addCritter ("Algae", 1, 2);
		Critter.addCritter ("Algae", 2, 0);
		//Critter.displayWorld();
		
		Critter c = new FriendlyCritter();
		c.setX_coord(1);
		c.setY_coord(0);
		Critter.addCritter(c);
		
		// Test for movement wrapping around (like a torus)
		//Critter.displayWorld();
		assertEquals(c.getX_coord(), 1);
		assertEquals(c.getY_coord(), 0);
		Critter.worldTimeStep();
		//Critter.displayWorld();
		assertEquals(c.getX_coord(), 2);
		assertEquals(c.getY_coord(), 2);
		Critter.worldTimeStep();
		//Critter.displayWorld();
		assertEquals(c.getX_coord(), 2);
		assertEquals(c.getY_coord(), 1);
    }
    
    // TEST FAILS BECAUSE 2 BABIES ARE PRODUCED	
    @Test
    public void testReproduction() {
    	Critter.population.clear();
    	Critter.babies.clear();
    	
		Critter c1 = new FriendlyCritter();
		Critter c2 = new FriendlyCritter();
		c1.setX_coord(1);
		c1.setY_coord(0);
		c2.setX_coord(0);
		c2.setY_coord(0);
		Critter.addCritter(c1);
		Critter.addCritter(c2);
		//Critter.displayWorld();
		
		assertEquals(Critter.babies.size(), 0);
		Critter.worldTimeStep();
		assertEquals(Critter.babies.size(), 1);
		//Critter.displayWorld();
    }
	
    // TEST FAILS BECAUSE HE PUTS IN ALGAE TWICE
	@Test
	public void testPopulation() {
		Critter.addCritter ("Algae", 0, 0);
		Critter.addCritter ("Algae", 0, 1);
		assertEquals(2, Critter.population.size());
		Critter.addCritter ("Algae", 0, 2);
		assertEquals(3, Critter.population.size());
	}
}
