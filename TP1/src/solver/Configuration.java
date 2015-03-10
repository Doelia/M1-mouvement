package solver;

public interface Configuration {

	// Retruen vrai si this et meilleur que C
	public boolean isBetter(Configuration c);
	public int getCost();
}

