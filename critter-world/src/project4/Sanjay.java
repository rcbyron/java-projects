package project4;

public class Sanjay extends Critter {
	
	
	@Override
	public String toString() { return "S"; }
	
	@Override
	public void doTimeStep() {
		//Banerjee is always right
		run(Critter.getRandomInt(2) - 1); 
	}

	@Override
	public boolean fight(String opponent) {
		//Patt thinks CompArch > Solid State Electronics 
		if (opponent.equals("Y")) { return true; }
		
		return false;
	}

}
