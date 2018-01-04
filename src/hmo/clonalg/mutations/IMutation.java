package hmo.clonalg.mutations;

import java.util.List;

import hmo.clonalg.Antibody;

public abstract class IMutation {
	
	public abstract void mutate(Antibody a);
	
	public void mutate(List<Antibody> population) {
		for (Antibody a : population) {
			this.mutate(a);
		}
	}
}
