package project4;

public class Yale extends Critter {
	
	@Override
	public String toString() { return "Y"; }
	
	@Override
	public void doTimeStep() {
		//Patt only moves up 
		walk(Critter.getRandomInt(2) + 1); 
		Yale child = new Yale();
		//Patt's children start by moving down 
		reproduce(child, Critter.getRandomInt(2) + 5);
	}

	@Override
	public boolean fight(String opponent) {
		//Patt is jealous of Banerjee's salary 
		if (opponent.equals("S")) { return true; }
		return false;
	}

}