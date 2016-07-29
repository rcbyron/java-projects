package quiz5.critter;


public class Algae extends Critter {

	private String name = "@";
	
	public Algae() {
	}
	
	public String toString() {return name; }
	
	public boolean fight(String opponent) {
		return false; 
	}
	
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
	}
	
}
