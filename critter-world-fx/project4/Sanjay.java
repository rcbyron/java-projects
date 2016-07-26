/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Robert (Connor) Byron
 * rcb2746
 * 76550
 * Joel Guo
 * jg55475
 * 76550
 * Slip days used: 0 (+1 for this project)
 * Fall 2015
 */
package project4;

public class Sanjay extends Critter {
	
	
	@Override
	public String toString() { return "S"; }
	
	@Override
	public void doTimeStep() {
		// Banerjee is always right
		run(Critter.getRandomInt(2)); 
		Sanjay child = new Sanjay(); 
		// Banerjee's kids love Beyonce (they move to the left) 
		reproduce(child, Critter.getRandomInt(2) + 3);
	}

	@Override
	public boolean fight(String opponent) {
		// Patt thinks CompArch > Solid State Electronics 
		if (opponent.equals("Y")) { return true; }
		
		return false;
	}

}