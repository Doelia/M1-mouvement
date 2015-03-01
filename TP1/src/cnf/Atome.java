package cnf;

public class Atome {

	private int val;
	
	public Atome(int valeur) {
		this.val = valeur;
	}
	
	public int getVal() {
		return val;
	}
	
	@Override
	public String toString() {
		return ""+val;
	}
	
}
