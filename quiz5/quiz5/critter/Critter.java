package quiz5.critter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Jo, VN
 */
public abstract class Critter {
	/**
	 * the x coordinate of my critter in the world
	 */
	public int x_coord;
	public int y_coord;
	public int energy;
	public static List<Critter> population = new ArrayList<>();
	public static List<Critter> babies = new ArrayList<Critter>();
	public boolean dead = false; // Critters are live by default
	private static Random rand = new Random();
	public static long seed;
	static int timestep = 0;
	
	/**
	 * Constructor creates live Critter.
	 */
	public Critter()
	{
		x_coord = 0;
		y_coord = 0;
		energy = Params.start_energy;
		dead = false;
		seed = 0;
	}
	
	/**
	 * Returns a random integer between 0 and max-1 (incl).
	 * @param max The random int generated goes from 0 to max - 1.
	 * @return Returns a random int.
	 */
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	/**
	 * This method can be called at any time to reset the random int generator.
	 * @param new_seed Seed for the random generator getRandomInt.
	 */
	public static void setSeed(long new_seed) {
		rand = new Random(new_seed);
	}

	
	/**
	 * Makes the critter move one space in a direction
	 * @param dir what direction to move in
	 */
	protected final void walk(int dir){
		energy-=Params.walk_energy_cost;
		if (energy < 0) dead = true;
		switch (dir) {
        case 0: // Right
            x_coord++;
            break;
        case 1: // Right, Up
            x_coord++;
            y_coord--;
            break;
        case 2: // Up
            y_coord--;
            break;
        case 3: // Left, Up
            x_coord--;
            y_coord--;
            break;
        case 4: // Left
            x_coord--;
            break;
        case 5: // Left, Down
            x_coord--;
            y_coord++;
            break;
        case 6: // Down
            y_coord++;
            break;
        case 7: // Right, Down
            x_coord++;
            y_coord++;
            break;
        }
		//Wrap around        
		x_coord = Math.floorMod(x_coord, Params.world_width);
        y_coord = Math.floorMod(y_coord, Params.world_height);
        
	}
	
	public int getX_coord() {
		return x_coord;
	}
	public void setX_coord(int x_coord) {
		this.x_coord = x_coord;
	}
	public int getY_coord() {
		return y_coord;
	}
	public void setY_coord(int y_coord) {
		this.y_coord = y_coord;
	}

	public int getEnergy() {
		return energy;
	}

	/**
	 * @param energy Sets this Critter's energy.  For debug.
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	

	/**
	 * 
	 * @param offspring A Critter type to reproduce.
	 * @param direction Direction of location in which the new Critter is created, 1 square away.
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if (dead) return;
		if (energy >= Params.min_reproduce_energy) {
			offspring.energy = energy/2;
			offspring.dead = offspring.energy <= 0;
			energy = energy - offspring.getEnergy();	
			if (!offspring.dead) {
				offspring.x_coord = x_coord;
				offspring.y_coord = y_coord;
				offspring.walk(direction);
				babies.add(offspring);
			}
			if (this.energy <= 0) 
				dead = true;
		}
	}

	/**
	 * 
	 * @param name String should be Algae or FriendlyCritter
	 * @param x	X location
	 * @param y	Y location
	 * @throws IllegalArgumentException If String not one of these two.
	 */
	public static void addCritter (String name, int x, int y) {
		Critter c = null;
		if (name.equals("Algae")) {
			c = new Algae();
			c.dead = false;
			c.x_coord = x;
			c.y_coord = y;
			population.add(c);
		} else if (name.equals("FriendlyCritter")) {
			c = new FriendlyCritter();
			c.dead = false;
			c.x_coord = x;
			c.y_coord = y;
		} else {
			throw new IllegalArgumentException("Wrong Critter.");
		}
		population.add(c);
	}
	
	/**
	 * 
	 * @param c Adds this supplied critter.
	 */
	
	public static void addCritter(Critter c) {
		if (c != null && !c.dead) {
			c.dead = false;
			population.add(c);
		}
	}
	
	/**
	 * Takes 1 time step.
	 */
	public static void worldTimeStep () {
		timestep++;
		population.addAll(babies);
		babies.clear();
		for (Critter c: population) {
			c.doTimeStep();
		}
	}
	
	/**
	 * Each Critter has to override this method.
	 */
	public abstract void doTimeStep();


	/**
	 * Looks in given direction 1 step away.
	 * @param direction Direction to look, starts from 0 (right), goes counter-clockwise to 7.
	 * @return toString of Critter in location, or null.
	 */
	protected String look (int direction) {
		if (dead) return null;
		int [] lookLocation = getNewCoords(direction);
		
		Critter c = null;
		for (Critter cr: population) {
			if (cr.x_coord == lookLocation[0] && cr.y_coord == lookLocation[1]) {
				c = cr;
				break;
			}			
		}
		if (c == null) {
			return null;
		}
		energy -= Params.look_energy_cost;
		if (energy <= 0) dead = true;
		return c.toString();			
	}
	
	// Returns the new co-ordinates after n steps in the given direction.
	private int[] getNewCoords(int direction) {
		int w = Params.world_width; int h = Params.world_height;
		int newX = x_coord + w; int newY = y_coord + h;
		
		switch (direction) {
		case 0: newX = (newX += 1); break;
		case 1: newX = (newX += 1);
				newY = (newY -= 1); break;
		case 2: newY = (newY -= 1); break;
		case 3: newX = (newX -= 1);
				newY = (newY -= 1); break;
		case 4: newX = (newX -= 1); break;
		case 5: newX = (newX -= 1);
				newY = (newY += 1); break;
		case 6: newY = (newY += 1); break;
		case 7: newX = (newX += 1); 
				newY = (newY += 1); break;
		}
		return new int[]{newX%w, newY%h};
	}
	
	/**
	 * Displays the world.
	 */
	public static void displayWorld() {
		String [][] world = new String[Params.world_width][Params.world_height];
		for (String[] row : world) {
			Arrays.fill(row, ".");
		}
		for (Critter c: population) {
			if (c != null && !c.dead) {
				world[c.y_coord][c.x_coord] = c.toString();
			}		
		}
		System.out.println("\nWorld at time = " + timestep);
		for (int row = 0; row < world.length; row++ ) {
			for (int col = 0; col < world[0].length; col++) {
				System.out.print(world[row][col]);
			}
			System.out.print("\n");
		}	
	}
	
	/**
	 * Removes all Critters from world.
	 */
	public static void clearWorld() {
		System.out.println("\nTime = " + timestep + ". Clearing world.");
		population.clear();
	}
}
