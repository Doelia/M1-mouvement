package solver;

public class Solver {

	int maxTries; // number of times a local search is performed
	int maxMoves; // maximum number of visited neighbors in every trial
	int maxNeightbors; // maximum number of neighbors which are visited for	any move
	int minNeightbors; // minimum number of neighbors which are visited for	any move
	
	StateNeighbors noAcceptation = StateNeighbors.BEST_NEIGHBOR; // value taken into account when no neighbor has
	// been accepted (among Max-Neighbors ones).
	
	private static int INF = Integer.MAX_VALUE;
	
	SetConfiguration configurations;
	
	public Solver(int maxTries, int maxMoves, int maxNeightbors,
			int minNeightbors, SetConfiguration configurations) {
		super();
		this.maxTries = maxTries;
		this.maxMoves = maxMoves;
		this.maxNeightbors = maxNeightbors;
		this.minNeightbors = minNeightbors;
		this.configurations = configurations;
	}

	public Configuration genericMetaheuristic() {
		Configuration best = null;
		for (int i = 0; i < maxTries; i++) {
			Configuration x = configurations.initialConfiguration();
			Configuration bestWalk = x;
			for (int j = 0; j < maxMoves; j++) {
				x = genericMove(x);
				//System.out.println("Move to "+x);
				if (x.isBetter(bestWalk)) {
					bestWalk = x;
					//System.out.println("Best walk devient "+bestWalk);
				}
			}
			if (bestWalk != null && bestWalk.isBetter(best)) {
				best =  bestWalk;
				//System.out.println("Best devient "+best);
			}
		}
		return best;
	}
	
	private boolean accepted(Configuration x, Configuration x2) {
		return x2.isBetter(x);
	}
	
	private Configuration genericMove(Configuration x) {
		boolean isBest = (minNeightbors > 1) || (noAcceptation == StateNeighbors.BEST_NEIGHBOR);
		int bestCost = INF;
		Configuration xBest = x;
		Configuration x2 = null;
		boolean isAccepted = false;
		for (int i = 0; i < minNeightbors || (i < maxNeightbors && !isAccepted); i++) {
			x2 = configurations.generateNeighbor(x);
			if (accepted(x, x2)) {
				isAccepted = true;
			}
			if (isBest && x2.getCost() < bestCost) {
				xBest = x2;
				bestCost = x2.getCost();
			}
		}
		if (isAccepted) {
			return isBest ? xBest : x2;
		}
		switch (noAcceptation) {
		case BEST_NEIGHBOR:
			return xBest;
		case ONE_NEIGHBOR:
			return x2;
		case NO_MOVE:
			return x;
		}
		return null;
	}
	
}
