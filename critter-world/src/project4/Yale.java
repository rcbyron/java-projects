package project4;

public class Yale extends Critter {
	private int dir = 0; 
	
	@Override
	public String toString() { return "Y"; }
	
	@Override
	public void doTimeStep() {
		//Patt only moves up 
		dir = Critter.getRandomInt(2) + 1;  
		walk(dir); 

	}

	@Override
	public boolean fight(String opponent) {
		if ( Critter.getRandomInt(100) > 80 ) {
			return true; 
		}
		return false;
	}

}
