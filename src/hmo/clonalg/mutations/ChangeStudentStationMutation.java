package hmo.clonalg.mutations;

import java.util.List;

import hmo.clonalg.Antibody;
import hmo.clonalg.ProblemInstance;
import hmo.clonalg.utils.RandomNumberGenerator;

public class ChangeStudentStationMutation extends IMutation {
	private double probability;
	private RandomNumberGenerator random;
	private ProblemInstance problem;
	
	public ChangeStudentStationMutation(double probability, RandomNumberGenerator random, ProblemInstance problem) {
		super();
		this.probability = probability;
		this.random = random;
		this.problem = problem;
	}

	@Override
	public void mutate(Antibody a) {
		for (int stop = 1; stop < a.stops.size(); stop++) {
			for (int i = 0; i < a.stops.get(stop).size(); i++) {
				if (random.nextDouble() < probability) {
					int student = a.stops.get(stop).get(i);
					List<Integer> availableStops = problem.getAvailableStops(student);
					int j = random.nextInt(availableStops.size());
					int newStop = availableStops.get(j);
					
					a.stops.get(stop).remove(i);
					a.stops.get(newStop).add(student);
				}
			}
		}
		
	}
	
}
