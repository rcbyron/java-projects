/* CRITTERS Yale.java
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

public class Yale extends Critter {
	
	@Override
	public Color viewColor() { return Color.DARKORCHID; }
	
	@Override
	public String toString() { return "Y"; }
	
	@Override
	public void doTimeStep() {
		// Patt only moves up 
		walk(Critter.getRandomInt(2) + 1); 
		Yale child = new Yale();
		// Patt's children start by moving down 
		reproduce(child, Critter.getRandomInt(2) + 5);
	}

	@Override
	public boolean fight(String opponent) {
		//Patt fights if Nandakumar is watching 
		for (int i = 0; i < 8; i++) {
			if (look(i) != null && look2(i) != null && (look(i).equals("V") || look2(i).equals("V"))) {
				return true; 
			}
		}
		// Patt is jealous of Banerjee's salary 
		if (opponent.equals("S")) { return true; }
		return false;
	}

}