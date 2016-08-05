/* CRITTERS Vallath.java
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

import javafx.scene.paint.Color;

public class Vallath extends Critter {

	@Override
	public Color getColor() { return Color.CORNFLOWERBLUE; }
	
	@Override
	public String toString() { return "V";	}
	
	@Override
	public void doTimeStep() {
		// Nandakumar only walks in cardinal directions
		walk(Critter.getRandomInt(3) * 2); 
		Vallath child = new Vallath();
		// Nandakumar's children are rebellious
		reproduce(child, Critter.getRandomInt(3) * 2 + 1);
	}

	@Override
	public boolean fight(String opponent) {
		// Nandakumar doesn't like how Valvano looked at him
		if (opponent.equals("J")) { return true; }
		// otherwise he is mostly a pacifist
		if (Critter.getRandomInt(100) < 5) { return true; }
		return false;
	}

}