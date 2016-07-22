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
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	private static Map<Point, ArrayList<Critter>> world = new HashMap<Point, ArrayList<Critter>>();
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return "?"; }
	
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
		if (energy >= 0)
			move(direction);
		else
			energy = 0;
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if (energy < Params.min_reproduce_energy) { return; }
		
		offspring.energy = energy / 2; 
		energy = energy % 2 == 0 ? energy / 2 : energy / 2 + 1; 
		
		//DIRECTION CONNOR PLS THX 
		offspring.x_coord = (x_coord + dirs[direction].x) % Params.world_width;
		offspring.y_coord = (x_coord + dirs[direction].x) % Params.world_width;
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String opponent);
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		Class<?> myCritter = null;
		Constructor<?> constructor = null;
		Object instanceOfMyCritter = null;

	    try {
			myCritter = Class.forName(critter_class_name);
		    constructor = myCritter.getConstructor(); // get null parameter constructor
		    instanceOfMyCritter = constructor.newInstance(); // create instance
		    Critter me = (Critter) instanceOfMyCritter; // cast to Critter
		    me.x_coord = getRandomInt(Params.world_width);
		    me.y_coord = getRandomInt(Params.world_height);
		    Point pos = new Point(me.x_coord, me.y_coord);
		    
		    ArrayList<Critter> bucket = world.get(pos);
		    if (bucket == null) bucket = new ArrayList<Critter>();
		    bucket.add(me);
		    
		    world.put(pos, bucket);
		} catch (Exception e) {
			if (e instanceof ClassNotFoundException)
				throw new InvalidCritterException(critter_class_name);
			else
				e.printStackTrace();
		}
	}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
	
		for (ArrayList<Critter> bugs : world.values()) {
			for (Critter bug : bugs) {
				if (bug.getClass().getTypeName() == critter_class_name);
					result.add(bug);
			}
		}
		
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
//		for (ArrayList<Critter> spot : world.values()) {
//			for (Critter bug : spot) {
//				bug.doTimeStep();
//				// Update world manually
//			}
//		}
//      0. timestep++;
//		1. loop through all critters in collection, call doTimeStep for each
//			i. walk/run 
//			ii. energy deduction
//			iii. reproduce but babies still in crib
//			iv. cheat
//		2. doEncounters()
//			i. fight, square by square
//		3. update rest energy
//		4. add algae
//		5. remove dead critters
//		6. add babies to populations 
	}
	
	public static void displayWorld() {
		for (int i = -1; i <= Params.world_height; i++) {
			for (int j = -1; j <= Params.world_width; j++) {
				if (i == -1 || i == Params.world_height)
					System.out.print((j == -1 || j == Params.world_width) ? '+' : '-');
				else {
					Point currPos = new Point(j, i);
					if (j == -1 || j == Params.world_width)
						System.out.print('|');
					else if (world.containsKey(currPos) && world.get(currPos).size() > 0)
						System.out.print(world.get(currPos).get(0));
					else
						System.out.print(' ');
				}
			}
			System.out.println();
		}
	}
}
