/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Summer 2016
 */
package project4;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */
public abstract class Critter {
	
	private static final Point[] dirs = {
			new Point(1, 0),
			new Point(1, 1),
			new Point(0, 1),
			new Point(-1, 1),
			new Point(-1, 0),
			new Point(-1, -1),
			new Point(0, -1)
		};
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
    private void move(int direction) {
    	int temp_x = (x_coord + dirs[direction].x) % Params.world_width;
    	int temp_y = (y_coord + dirs[direction].y) % Params.world_height;
    }
	
	protected final void walk(int direction) {
		energy -= Params.walk_energy_cost;
		if (energy >= 0)
			move(direction);
		else
			energy = 0;
	}
	
	protected final void run(int direction) {
		energy -= Params.run_energy_cost;
		if (energy >= 0) {
			move(direction);
			move(direction);
		}
		else
			energy = 0;
	}	
	
	private boolean moved; 
	
	private final boolean hasMoved() {
		return moved; 
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if (energy < Params.min_reproduce_energy) { return; }
		
		offspring.energy = energy / 2; 
		energy = energy % 2 == 0 ? energy / 2 : energy / 2 + 1; 
		
		//DIRECTION CONNOR PLS THX 
	}
	private static Map<Point, ArrayList<Critter>> world = new HashMap<Point, ArrayList<Critter>>();
	
	private static void doEncounters() {
		for (ArrayList<Critter> spot : world.values()) {
			//if coordinate occupied by more than one critter
			while (spot.size() > 1) { 
				Critter critA = spot.get(0);
				Critter critB = spot.get(1);
				Point fightPoint = new Point(critA.x_coord, critA.y_coord);
				boolean fightA = critA.fight(critB.toString());
				boolean fightB = critB.fight(critA.toString());
				
				if (!fightA) { critA.tryToMove(); }
				if (!fightB) { critB.tryToMove(); }
				if (critA.energy <= 0 || critB.energy <= 0) {
					if (critB.energy <= 0) { spot.remove(critB); }
					if (critA.energy <= 0) { spot.remove(critA); }
				} else if (critA.x_coord == fightPoint.x && critA.y_coord == fightPoint.y 
					&& critB.x_coord == fightPoint.x && critB.y_coord == fightPoint.y) {
					int rollA = getRandomInt(critA.energy);
					int rollB = getRandomInt(critB.energy);
					if (rollA > rollB) { 
						critA.energy += .5 * critB.energy;
						spot.remove(critB);
					} else {
						critB.energy += .5 * critA.energy;
						spot.remove(critA);
					}
				}
			}
		}
	}
	
	private void tryToMove() {
		if (!hasMoved()) {
			int escapeDir = openAdjacentPoint(new Point(x_coord, y_coord));
			if (escapeDir > -1 && escapeDir < 8) {
				walk(escapeDir);
			} else if(escapeDir > 8 && escapeDir < 16) {
				run(escapeDir);
			} else {
				energy -= Params.walk_energy_cost;
			}
		} else {
			energy -= Params.walk_energy_cost;
		}
	}
	
	/*
	 * return 0-7 for direction of immediate open space
	 * return 8-15 for direction of open space two spaces away
	 */
	private static int openAdjacentPoint(Point p){
		for (int dir = 0; dir < 8; dir++) {
			int temp_x = (p.x + dirs[dir].x) % Params.world_width;
			int temp_y = (p.y + dirs[dir].y) % Params.world_height;
			Point temp_p = new Point(temp_x, temp_y);
			if (world.get(temp_p).size() == 0) {
				return dir; 
			}
		}
		for (int dir = 8; dir < 16; dir++) {
			int temp_x = (p.x + 2 * dirs[dir].x) % Params.world_width;
			int temp_y = (p.y + 2 * dirs[dir].y) % Params.world_height;
			Point temp_p = new Point(temp_x, temp_y);
			if (world.get(temp_p).size() == 0) {
				return dir; 
			}
		}
		return -1;
	}
	
	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
	}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
	
		return result;
	}
	
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setXCoord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setYCoord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
	}
	
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
		
	public static void worldTimeStep() {
	}
	
	public static void displayWorld() {}
}
