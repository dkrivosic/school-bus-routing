package hmo.clonalg;
import java.util.List;

public class Antigen {
	private ProblemInstance problem;
	private int numberOfEvaluations;

	public Antigen(ProblemInstance problem) {
		super();
		this.problem = problem;
		this.numberOfEvaluations = 0; 
	}
	
	public int getNumberOfEvaluations() {
		return numberOfEvaluations;
	}
	
	public void evaluate(Antibody a) {
		// Check bus capacity
		int numberOfBuses = a.busRoutes.size();
		int busCapacity = problem.getBusCapacity();
		for (int bus = 0; bus < numberOfBuses; bus++) {
			int passengers = 0;
			List<Integer> route = a.busRoutes.get(bus);
			for (int stop : route) {
				passengers += a.stops.get(stop).size();
			}
			if (passengers > busCapacity) {
				a.cost = Double.POSITIVE_INFINITY;
				return;
			}
		}
		
		// Calculate cost
		double cost = 0.0;
		int school = 0;
		
		for (int bus = 0; bus < a.busRoutes.size(); bus++) {
			List<Integer> route = a.busRoutes.get(bus);
			if (route.isEmpty())
			{
				continue;
			}
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
		
		this.numberOfEvaluations += 1;
	}
	
	public void evaluate(List<Antibody> population) {
		for (Antibody a : population) {
			evaluate(a);
		}
	}
}
