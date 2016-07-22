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
		//Patt is jealous of Banerjee's salary 
		if (opponent.equals("S")) { return true; }
		return false;
	}

}
