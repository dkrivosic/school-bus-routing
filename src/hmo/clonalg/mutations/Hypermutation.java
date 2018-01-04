package hmo.clonalg.mutations;

import java.util.List;

import hmo.clonalg.Antibody;

public class Hypermutation extends IMutation {
	private List<IMutation> mutations;

	public Hypermutation(List<IMutation> mutations) {
		super();
		this.mutations = mutations;
	}

	@Override
	public void mutate(Antibody a) {
		for (IMutation mutation : mutations) {
			mutation.mutate(a);
		}

	}

}
