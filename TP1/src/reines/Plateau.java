package reines;

import solver.Configuration;

public class Plateau implements Configuration {

	boolean[][] positions;

	@Override
	public boolean isBetter(Configuration c) {
		return this.getCost() < c.getCost();
	}

	@Override
	public int getCost() {
		return 0;
	}
}
