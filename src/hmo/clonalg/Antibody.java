package hmo.clonalg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hmo.clonalg.utils.RandomNumberGenerator;

public class Antibody implements Comparable<Antibody> {
	public List<List<Integer>> busRoutes;
	public List<List<Integer>> stops;
	public double cost;
	
	public Antibody(ProblemInstance problem, RandomNumberGenerator random) {
		// Initialize stops
		stops = new ArrayList<>();
		for (int i = 0; i < problem.getStopsCount(); i++) {
			stops.add(new ArrayList<>());
		}
		
		// Assign students to stops
		for (int student = 0; student < problem.getStudentsCount(); student++) {
			List<Integer> availableStops = new ArrayList<>(problem.getAvailableStops(student));
			
			while (!availableStops.isEmpty()) {
				int n = availableStops.size();
				if (n == 0) {
					System.err.println("Wrong problem definition! Student " + student + " is too far away from all the stops.");
					System.exit(1);
				}
				
				int i = random.nextInt(n);
				int stop = availableStops.get(i);
				
				if (stops.get(stop).size() < problem.getBusCapacity())
				{
					stops.get(stop).add(student);
					break;
				} else {
					availableStops.remove(i);
				}
			}
		}
		
		// Create a random permutation of stops
		List<Integer> stopsShuffled = new ArrayList<>();
		for (int i = 0; i < problem.getStopsCount(); i++) {
			stopsShuffled.add(i);
		}
		Collections.shuffle(stopsShuffled);
		
		// Assign stops to busses
		int busCapacity = problem.getBusCapacity();
		List<Integer> route = new ArrayList<>();
		int passengers = 0;
		
		for (int i = 0; i < problem.getStopsCount(); i++) {
			int stop = stopsShuffled.get(i);
			int studentsOnStop = stops.get(stop).size();
			if (passengers + studentsOnStop < busCapacity) {
				passengers += studentsOnStop;
				route.add(stop);
			} else {
				busRoutes.add(route);
				route = new ArrayList<>();
				route.add(stop);
				passengers = studentsOnStop;
			}
		}
		busRoutes.add(route);
	}
	
	private Antibody() {
		super();
	}
	
	public Antibody clone() {
		Antibody a = new Antibody();
		
		List<List<Integer>> busRoutesCopy = new ArrayList<>();
		for (List<Integer> route : busRoutes) {
			busRoutesCopy.add(new ArrayList<>(route));
		}
		
		List<List<Integer>> stopsCopy = new ArrayList<>();
		for (List<Integer> students : stops) {
			stopsCopy.add(new ArrayList<>(students));
		}
		
		a.busRoutes = busRoutesCopy;
		a.stops = stopsCopy;
		a.cost = cost;
		
		return a;
	}

	@Override
	public int compareTo(Antibody o) {
		if (this.cost < o.cost) {
			return -1;
		} else if (this.cost > o.cost) {
			return 1;
		}
		return 0;
	}
}
