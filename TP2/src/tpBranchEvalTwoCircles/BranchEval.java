
package tpBranchEvalTwoCircles;

import java.util.Stack;

import util.*;

public class BranchEval {

	Constraint[] ctrs;     // list of constraints
	Box[] solutions;       // list of solutions
	int numSolutions;      // number of solutions found

	public BranchEval(Constraint[] _ctrs) {
		this.ctrs = _ctrs;
		this.solutions = new Box[1000];
		this.numSolutions = 0;
	}

	private boolean allArentViolated(Box b) {
		for (Constraint s : ctrs) {
			if (s.violated(b)) {
				return false;
			}
		}
		return true;
	}

	public boolean solve(Box x0, double eps) {

		this.numSolutions = 0;
		Stack<Box> s =new Stack<Box>();

		s.push(x0);
		int count=0; // number of branchings

		while (!s.empty()) {
			Box x = s.pop();
			if (this.allArentViolated(x)) {
				if (x.maxDiam() < eps) {
					this.solutions[numSolutions++] = x;
				} else {
					Pair<Box, Box> p = x.bisect();
					s.push(p.fst);
					s.push(p.snd);
				}
			}
			count++;
		}

		System.out.println("Number of potential solutions = " + numSolutions + 
				" found with " + count + " branchings !");     
		System.out.print("List of solutions: ");
		for (int i=0; i < numSolutions; i++) {
			System.out.print(solutions[i] + " | ");
		}
		System.out.println("");
		return (numSolutions > 0);
	}
}
