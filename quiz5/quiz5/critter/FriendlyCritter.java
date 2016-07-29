package quiz5.critter;

public class FriendlyCritter extends Critter {
	
	/**
	 * @return Returns "F".
	 */
	public String toString () {
		return "F";
	}
	
	/**
	 * Looks for mate in neighboring squares, if has enough energy.
	 * If there is a mate, reproduces, and throws baby in direction with empty square, if enough energy.
	 * Only 1 reproduction per time step. If no neighboring empty squares, chooses direction 0 for baby.
	 * If no mate found, then walks, if possible, to neighboring empty square.
	 * In all cases, checks for energy reserve before performing action.  
	 */
	
	public void doTimeStep() {
		Critter c = new FriendlyCritter();
		int mateDir = lookForMate();
		int emptyDir = lookForEmpty();
		if (mateDir >= 0) {
			int babyDirection = emptyDir < 0 ? 0: emptyDir;
			if (getEnergy() - Params.min_reproduce_energy > 0)
				reproduce(c, babyDirection);
		} else if (emptyDir >= 0) {
			if (getEnergy() - Params.walk_energy_cost > 0)
				walk(emptyDir);
		}
	}

	private int lookForMate() {
		int direction = -1;
		for (int dir = 0; dir <= 7; dir++) {
			String neighbor = null;
			if (getEnergy() - Params.look_energy_cost > 0) 
				neighbor = look(dir);
			if (neighbor != null && neighbor.equals(this.toString()) ) {
				direction = dir;
				break;
			}
		}
		return direction;
	}
	
	private int lookForEmpty() {
		int direction = -1;
		for (int dir = 0; dir <= 7; dir++) {
			if (getEnergy() - Params.look_energy_cost > 0) 
				if (look(dir) == null) {
					direction = dir;
					break;
				}
		}
		return direction;
	}
}
