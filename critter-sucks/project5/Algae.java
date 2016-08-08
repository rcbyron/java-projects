package project5;

import javafx.scene.paint.Color;
import project5.Critter.TestCritter;

public class Algae extends TestCritter {

	@Override
	public CritterShape viewShape() {
		return CritterShape.CIRCLE;
	}
	
	@Override
	public Color viewColor() { return Color.GREEN; }
	
	@Override
	public String toString() { return "@"; }
	
	public boolean fight(String not_used) { return false; }
	
	public void doTimeStep() {
		setEnergy(getEnergy() + Params.photosynthesis_energy_amount);
	}
}
