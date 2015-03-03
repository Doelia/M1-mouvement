package cnf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.Main;
import solver.Configuration;
import solver.SetConfiguration;
import solver.Solver;

public class Solve {

	public static void solveCnf(String file) {
		final List<Clause> list = ParsingFile.parseFile(file);
		System.out.println("Input: "+list);

		final HashMap<Clause, Integer> hashT = new HashMap<Clause, Integer>();
		for (int i = 0; i < list.size(); i++) {
			hashT.put(list.get(i), i);
		}

		int n = 100;

		Solver s = new Solver(n, n, n, n, new SetConfiguration() {

			ArrayList<Clause> configs = (ArrayList<Clause>) list;
			HashMap<Clause, Integer> hash = hashT;

			@Override
			public Configuration initialConfiguration() {
				int random = Main.random(0, this.configs.size()-1);
				return configs.get(random);
			}

			@Override
			public Configuration generateNeighbor(Configuration x) {
				int i = hash.get(x);
				int next = (i == configs.size() - 1) ? 0 : i + 1;
				return configs.get(next);
			}
		});

		Clause best = (Clause) s.genericMetaheuristic();
		System.out.println("Best = "+best);
	}
	
}
