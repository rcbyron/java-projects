/* CRITTERS Critter.java
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

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

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
		new Point(0, -1),
		new Point(1, -1)
	};
	
	private static Map<Point, ArrayList<Critter>> world = new HashMap<Point, ArrayList<Critter>>();
	
	public static List<Critter> getPopulation() {
		return population;
	}
	
	public Color getColor() { return Color.BLACK; }
	
	public int getX() { return x_coord; }
	public int getY() { return y_coord; }
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return "?"; }
	
	private int energy = Params.start_energy;
	protected int getEnergy() { return energy; }
	
	private boolean hasMoved;
	private int x_coord;
	private int y_coord;
	
    private void move(int direction) {
    	x_coord = (x_coord + dirs[direction].x) % Params.world_width;
    	if (x_coord < 0) x_coord += Params.world_width;
    	y_coord = (y_coord + dirs[direction].y) % Params.world_height;
    	if (y_coord < 0) y_coord += Params.world_height;
    	hasMoved = true;
    }
    
    protected Point lookHelper(int direction, int distance) {
    	int x = (x_coord + distance * dirs[direction].x) % Params.world_width;
    	if (x < 0) x += Params.world_width;
    	int y = (y_coord + distance * dirs[direction].y) % Params.world_height;
    	if (y < 0) y += Params.world_height;
    	return new Point(x, y);
    }
    
    protected String look(int direction) {
    	energy -= Params.look_energy_cost;

    	Point p = lookHelper(direction, 1);
    	if(world.containsKey(p) && world.get(p).size() > 0)
    		return world.get(p).get(0).toString();
    	return null;
    }
    
    protected String look2(int direction) {
    	energy -= Params.look_energy_cost;

    	Point p = lookHelper(direction, 2);
    	if(world.containsKey(p) && world.get(p).size() > 0)
    		return world.get(p).get(0).toString();
    	return null;
    }
	
	protected final void walk(int direction) {
		energy -= Params.walk_energy_cost;
		if (energy >= 0)
			move(direction);
	}
	
	protected final void run(int direction) {
		energy -= Params.run_energy_cost;
		if (energy >= 0) {
			move(direction);
			move(direction);
		}
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if (energy < Params.min_reproduce_energy) { return; }
		
		offspring.energy = energy / 2; 
		energy = energy % 2 == 0 ? energy / 2 : energy / 2 + 1; 
		
		Point p = lookHelper(direction, 1);
		offspring.x_coord = p.x;
		offspring.y_coord = p.y;
		babies.add(offspring);
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
		    
		    population.add(me);
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
				if (bug.getClass().getTypeName().equals(critter_class_name))
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
	
	private static void doEncounters() {
		for (ArrayList<Critter> spot : world.values()) {
			//if coordinate occupied by more than one critter
			while (spot.size() > 1) { 
				Critter critA = spot.get(0);
				Critter critB = spot.get(1);
				Point fightPoint = new Point(critA.x_coord, critA.y_coord); 
				boolean fightA = critA.fight(critB.toString());
				boolean fightB = critB.fight(critA.toString());
				
				if (!fightA) { critA.tryToEscape(); }
				if (!fightB) { critB.tryToEscape(); }
				if (critA.energy <= 0 || critB.energy <= 0) {
					if (critB.energy <= 0) { spot.remove(critB); population.remove(critB); }
					if (critA.energy <= 0) { spot.remove(critA); population.remove(critA); }
				} else if (critA.x_coord == fightPoint.x && critA.y_coord == fightPoint.y 
					&& critB.x_coord == fightPoint.x && critB.y_coord == fightPoint.y) {
					//actually fight
					int rollA = getRandomInt(critA.energy);
					int rollB = getRandomInt(critB.energy);
					if (rollA > rollB) { 
						critA.energy += .5 * critB.energy;
						spot.remove(critB);
						population.remove(critB);
					} else {
						critB.energy += .5 * critA.energy;
						spot.remove(critA);
						population.remove(critA);
					}
				} else {
					if (!(critB.x_coord == fightPoint.x && critB.y_coord == fightPoint.y)) {
						spot.remove(critB);
					}
					if (!(critA.x_coord == fightPoint.x && critA.y_coord == fightPoint.y)) {
						spot.remove(critA);
					}
				}
			}
		}
	}
	
	/*
	 * if Critter has already moved or cannot find open space, deduct walking energy
	 * else walk or run to open spot 
	 */
	private void tryToEscape() {
		int escapeDir = openAdjacentPoint(new Point(x_coord, y_coord));
		if (hasMoved || escapeDir == -1) { energy -= Params.walk_energy_cost; } 
		else if (escapeDir < 8) { walk(escapeDir); } 
		else if(escapeDir < 16) { run(escapeDir - 8); }
	}
	
	/*
	 * return 0-7 for direction of immediate open space
	 * return 8-15 for direction of open space two spaces away
	 */
	private static int openAdjacentPoint(Point p){
		for (int dir = 0; dir < 16; dir++) {
	    	int temp_x = (p.x + ((dir+8) / 8)*dirs[dir].x) % Params.world_width;
	    	if (temp_x < 0) temp_x += Params.world_width;
	    	int temp_y = (p.y + ((dir+8) / 8)*dirs[dir].y) % Params.world_height;
	    	if (temp_y < 0) temp_y += Params.world_height;
			Point temp_p = new Point(temp_x, temp_y);
			if (!world.containsKey(temp_p) || world.get(temp_p).size() == 0) { return dir; }
		}
		return -1;
	}
	
	public static int timestep = 0;
	public static void worldTimeStep() {
		timestep++;
		// System.out.println("step: "+timestep);
		
//		loop through all critters in collection, call doTimeStep for each
//		i. walk/run 
//		ii. energy deduction
//		iii. reproduce but babies still in crib
//		iv. cheat
		for (ArrayList<Critter> spot : world.values()) {
			for (Critter bug : spot) {
				bug.hasMoved = false;
				bug.doTimeStep();
			}
		}

		doEncounters();
		
		// Update rest energy
		for (ArrayList<Critter> spot : world.values())
			for (Critter bug : spot)
				bug.energy -= Params.rest_energy_cost;
		
		// Add algae
		try {
			for (int i = 0; i < Params.refresh_algae_count; i++)
				makeCritter("project4.Algae");
		} catch (InvalidCritterException e) {
			e.printStackTrace();
		}
		
		// Remove dead critters
		Iterator<Critter> iter = population.iterator();
		while (iter.hasNext()) {
			Critter bug = iter.next();
			if (bug.energy <= 0) {
				iter.remove();
			}
		}
				
		// Add babies to population
		population.addAll(babies);
		
		// Clear and update critter map
		for (ArrayList<Critter> spot : world.values())
			spot.clear();
		for (Critter bug : population) {
			Point p = new Point(bug.x_coord, bug.y_coord);
			if (!world.containsKey(p)) world.put(p, new ArrayList<Critter>());
			world.get(p).add(bug);
		}
	}
	
	public static void displayWorld() {
		Main.launch(Main.class);
	}
}
