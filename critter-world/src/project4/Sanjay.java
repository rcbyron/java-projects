package project4;

public class Sanjay extends Critter {
	private int dir; 
	
	@Override
	public String toString() { return "S"; }
	
	@Override
	public void doTimeStep() {
		//Banerjee is always right 
		dir = Critter.getRandomInt(2) - 1; 
		run(dir); 
	}

	@Override
	public boolean fight(String oponent) {
		if (Critter.getRandomInt(150) < 40 ) { 
			return true; 
		}
		return false;
	}

}
