package project4;

public class Jon extends Critter {

	@Override
	public String toString() { return "J"; }
	
	@Override
	public void doTimeStep() {
		//Valvano walks around randomly
		walk(Critter.getRandomInt(7));
	}

	@Override
	public boolean fight(String opponent) {
		//Valvano likes to participate in casual friendly fights
		char c = opponent.charAt(0); 
		if (c % 2 == 0) { return true; }
		return false;
	}

}
