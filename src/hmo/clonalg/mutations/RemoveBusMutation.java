package hmo.clonalg.mutations;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import hmo.clonalg.Antibody;
import hmo.clonalg.ProblemInstance;
import hmo.clonalg.utils.RandomNumberGenerator;

public class RemoveBusMutation extends IMutation {
	private double probability;
	private RandomNumberGenerator random;
	private ProblemInstance problem;
	
	public RemoveBusMutation(double probability, RandomNumberGenerator random, ProblemInstance problem) {
		super();
		this.probability = probability;
		this.random = random;
		this.problem = problem;
	}

	@Override
	public void mutate(Antibody a) {
		if (random.nextDouble() > probability) {
			return;
		}
		
		int numberOfBuses = a.busRoutes.size();
		int bus = random.nextInt(numberOfBuses);
		List<Integer> route = a.busRoutes.get(bus);
		a.busRoutes.remove(bus);
		numberOfBuses--;
		
		Stack<Integer> stops = new Stack<>();
		stops.addAll(route);
		while (!stops.isEmpty()) {
			boolean stopAssigned = false;
			for (bus = 0; bus < numberOfBuses; bus++) {
				int busPassengers = 0;
				for (int stop : a.busRoutes.get(bus)) {
					busPassengers += a.stops.get(stop).size();
				}
				int studentsOnStop = a.stops.get(stops.peek()).size(); 
				if (busPassengers + studentsOnStop <= problem.getBusCapacity()) {
					busPassengers += studentsOnStop;
					a.busRoutes.get(bus).add(stops.pop());
					stopAssigned = true;
					break;
				}
			}
			if (!stopAssigned) {
				break;
			}
			
		}
		
		if (stops.isEmpty()) {
			return;
		}
		
		List<Integer> newRoute = new ArrayList<Integer>();
		while (!stops.isEmpty()) {
			newRoute.add(stops.pop());
		}
		a.busRoutes.add(newRoute);
	}
	
	
}
