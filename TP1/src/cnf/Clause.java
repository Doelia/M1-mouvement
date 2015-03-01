package cnf;
import java.util.ArrayList;
import java.util.List;

import solver.Configuration;


public class Clause implements Configuration {

	int maxValues = 20;
	List<Atome> atomes;
	
	public Clause(int maxValues) {
		this.atomes = new ArrayList<Atome>();
		this.maxValues = maxValues;
	}
	
	// S dlf 4 5 1 0
	public Clause(String list, int maxValues) {
		this(maxValues);
		for (String s : list.split(" ")) {
			if (!s.isEmpty()) {
				try {
					int val = Integer.parseInt(s);
					if (val < this.maxValues && val != 0) {
						Atome a = new Atome(val);
						this.addAtome(a);
					}
				} catch (Exception e) {
					System.err.println("Erreur lors du parsing du la clause '"+list+"' sur l'atome "+s);
					e.printStackTrace();
				}
			}
		}
	}
	
	public void addAtome(Atome a) {
		this.atomes.add(a);
	}
	
	@Override
	public String toString() {
		String s = "Clause : ";
		for (Atome a : atomes) s += a.toString()+ " ";
		s+= ", cost "+this.getCost();
		return s;
	}

	@Override
	public boolean isBetter(Configuration c) {
		if (c == null) return true;
		return (this.getCost() > c.getCost());
	}

	@Override
	public int getCost() {
		int s = 0;
		for (Atome a : atomes) s += a.getVal();
		return s;
	}
	
}
