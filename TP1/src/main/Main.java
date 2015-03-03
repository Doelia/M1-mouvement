package main;
import java.util.Random;

import cnf.Solve;


public class Main {

	static Random rand = new Random();
	public static int random(int i1, int i2) {
		return (rand.nextInt((i2 - i1) + 1)) + i1;
	}

	public static void main(String[] args) {
		Solve.solveCnf("test.cnf");
		FctSolver.solveFcf();
	}

}
