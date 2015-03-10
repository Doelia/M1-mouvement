package cnf;
import java.util.ArrayList;
import java.util.List;


public class Clause  {

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
		return s;
	}
	
	public boolean satisfiable(Interpretation i) {
		for (Atome a : this.atomes) {
			if (i.isTrue(a.getVal())) {
				return true;
			}
		}
		return false;
	}

}
