package hmo.clonalg.mutations;

import java.util.List;

import hmo.clonalg.Antibody;
import hmo.clonalg.utils.RandomNumberGenerator;

public class SwapStationsMutation extends IMutation {
	private double probability;
	private RandomNumberGenerator random;

	public SwapStationsMutation(double probability, RandomNumberGenerator random) {
		super();
		this.probability = probability;
		this.random = random;
	}

	@Override
	public void mutate(Antibody a) {
		int numberOfBuses = a.busRoutes.size();
		for (int i = 0; i < numberOfBuses; i++) {
			double p = random.nextDouble();
			if (p < probability) {
				List<Integer> route = a.busRoutes.get(i);
				if (route.size() < 2) {
					continue;
				}
				int n = route.size();
				int firstStationIndex = random.nextInt(n - 1);
				int secondStationIndex = random.nextInt(firstStationIndex + 1, n);
				int tmp = route.get(firstStationIndex);
				route.set(firstStationIndex, route.get(secondStationIndex));
				route.set(secondStationIndex, tmp);
			}
		}
	}

}
