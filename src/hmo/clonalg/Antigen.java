package hmo.clonalg;
import java.util.List;

public class Antigen {
	private ProblemInstance problem;

	public Antigen(ProblemInstance problem) {
		super();
		this.problem = problem;
	}
	
	public void evaluate(Antibody a) {
		double cost = 0.0;
		int school = 0;
		
		for (int bus = 0; bus < a.busRoutes.size(); bus++) {
			List<Integer> route = a.busRoutes.get(bus);
			int currentStop = route.get(0);
			cost += problem.getStopsDistance(school, currentStop);
			int lastStop = currentStop;
			for (int i = 1; i < route.size(); i++) {
				lastStop = currentStop;
				currentStop = route.get(i);
				cost += problem.getStopsDistance(lastStop, currentStop);
			}
			cost += problem.getStopsDistance(currentStop, school);
		}
		a.cost = cost;
	}
	
	public void evaluate(List<Antibody> population) {
		for (Antibody a : population) {
			evaluate(a);
		}
	}
}
