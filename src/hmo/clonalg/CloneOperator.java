package hmo.clonalg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CloneOperator {
	private double beta;

	public CloneOperator(double beta) {
		super();
		this.beta = beta;
	}
	
	public List<Antibody> clone(List<Antibody> population) {
		Collections.sort(population);
		int populationSize = population.size();
		
		List<Antibody> clones = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			int numberOfClones = (int) Math.round(beta * populationSize / (i + 1.0));
			Antibody a = population.get(i);
			for (int j = 0; j < numberOfClones; j++) {
				clones.add(a.clone());
			}
		}
		
		return clones;
	}

}
