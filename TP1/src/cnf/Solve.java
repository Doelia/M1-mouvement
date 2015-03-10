package cnf;

import java.util.List;

import solver.Configuration;
import solver.SetConfiguration;
import solver.Solver;

public class Solve {

	public static void solveCnf(String file) {
		final List<Clause> list = ParsingFile.parseFile(file);
		System.out.println("Input: "+list);

		int n = 5;

		Solver s = new Solver(n, n, n, n, new SetConfiguration() {

			Interpretation i = new Interpretation(list);

			@Override
			public Configuration initialConfiguration() {
				i.randomize();
				return i;
			}

			@Override
			public Configuration generateNeighbor(Configuration x) {
				i.nextStep();
				return i;
			}
		});

		Interpretation best = (Interpretation) s.genericMetaheuristic();
		System.out.println("Best = "+best);
	}
	
}
