/* CRITTERS Jon.java
 * EE422C Project 4 submission by
 * Robert (Connor) Byron
 * rcb2746
 * 76550
 * Joel Guo
 * jg55475
 * 76550
 * Slip days used: 1
 * Spring 2016
 */
package project4;

public class Jon extends Critter {

	@Override
	public String toString() { return "J"; }
	
	@Override
	public void doTimeStep() {
		int dir = Critter.getRandomInt(7);
		for (int i = 0; i < 150; i++) {
			if (look(dir) == null || !look(dir).equals("J"))
				break;
			dir = Critter.getRandomInt(7); 
		}
		// Valvano walks around randomly but avoids other Valvanos
		walk(dir);
		Jon child = new Jon();
		// like father like son
		reproduce(child, Critter.getRandomInt(7));
	}

	@Override
	public boolean fight(String opponent) {
		// Valvano likes to participate in casual friendly fights
		char c = opponent.charAt(0); 
		if (c % 2 == 0) { return true; }
		return false;
	}

}