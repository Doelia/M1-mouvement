package cnf;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ParsingFile {

	public static List<Clause> parseFile(String nameFile) {
		ArrayList<Clause> list = new ArrayList<Clause>();
		
		try {
			InputStream in = new FileInputStream(nameFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			int maxValues = 20;
			
			String line;
			while ((line = br.readLine()) != null && !line.equals("%")) {
				if (line.length() > 0 && line.charAt(0) != 'c') {
					if (line.charAt(0) != 'p') {
						Clause c = new Clause(line, maxValues);
						list.add(c);
					}
				}
			}
			
			br.close();
			
		} catch (Exception e) {
			System.err.println("Erreur lors de l'ouverture du fichier "+nameFile);
			e.printStackTrace();
			return null;
		}
		
		return list;
	}
}
