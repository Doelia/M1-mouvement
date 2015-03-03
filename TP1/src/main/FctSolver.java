package main;
import solver.Configuration;
import solver.SetConfiguration;
import solver.Solver;


public class FctSolver {

	public static void solveFcf() {

		class C implements Configuration {
			int x;

			@Override
			public boolean isBetter(Configuration c) {
				return this.getCost() < c.getCost();
			}

			@Override
			public int getCost() {
				return (x-3)*(x-3) - 10;
			}
			
			@Override
			public String toString() {
				return ""+x;
			}
		};

		int n = 100;

		Solver s = new Solver(n, n, n, n, new SetConfiguration() {

			@Override
			public Configuration initialConfiguration() {
				C c = new C();
				c.x = Main.random(-100,100);
				return c;
			}

			@Override
			public Configuration generateNeighbor(Configuration x) {
				C c = new C();
				c.x = ((C) x).x + 1;
				return c;
			}
			
		});

		C best = (C) s.genericMetaheuristic();
		System.out.println("Best = "+best.x);
	}
	
}
