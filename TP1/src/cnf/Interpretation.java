package cnf;

import java.util.Arrays;
import java.util.List;

import main.Main;

import solver.Configuration;

public class Interpretation implements Configuration {

	private boolean[] affectations;
	private List<Clause> list;
	
	public Interpretation(List<Clause> clauses) {
		this.list = clauses;
		int n = clauses.get(0).maxValues + 1;
		this.affectations = new boolean[n];
		for (int i = 0; i < n; i++) {
			this.set(i, false);
		}
	}
	
	public void randomize() {
		for (int i = 0; i < this.affectations.length; i++) {
			this.set(i, Main.random(0, 1) == 1);
		}
	}
	
	public void set(int variable, boolean value) {
		this.affectations[this.getVariable(variable)] = value;
	}
	
	public int getVariable(int value) {
		return Math.abs(value);
	}
	
	public boolean isTrue(int variable) {
		return this.affectations[this.getVariable(variable)];
	}

	@Override
	public boolean isBetter(Configuration c) {
		return (this.getCost() > c.getCost());
	}

	@Override
	public int getCost() {
		int nbrOk = 0;
		for (Clause c : list) {
			if (c.satisfiable(this)) {
				nbrOk++;
			}
		}
		return nbrOk;
	}
	
	public void nextStep() {
		this.randomize(); // TODO
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.affectations);
	}
	
}
