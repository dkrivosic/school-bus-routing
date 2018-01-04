package hmo.clonalg.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hmo.clonalg.Antibody;
import hmo.clonalg.Antigen;
import hmo.clonalg.CloneOperator;
import hmo.clonalg.ProblemInstance;
import hmo.clonalg.mutations.IMutation;
import hmo.clonalg.utils.RandomNumberGenerator;

public class Clonalg {
	private int populationSize;
	private int maxIterations;
	private int generateNew;
	private ProblemInstance problem;
	private RandomNumberGenerator random;
	private Antigen antigen;
	private CloneOperator cloneOperator;
	private IMutation hypermutation;

	public Clonalg(int populationSize, int maxIterations, int generateNew, ProblemInstance problem,
			RandomNumberGenerator random, Antigen antigen, CloneOperator cloneOperator, IMutation hypermutation) {
		super();
		this.populationSize = populationSize;
		this.maxIterations = maxIterations;
		this.generateNew = generateNew;
		this.problem = problem;
		this.random = random;
		this.antigen = antigen;
		this.cloneOperator = cloneOperator;
		this.hypermutation = hypermutation;
	}

	public Antibody run() {
		// Initialize population
		List<Antibody> population = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			population.add(new Antibody(problem, random));
		}

		Antibody best = null;

		for (int iteration = 1; iteration <= maxIterations; iteration++) {
			antigen.evaluate(population);
			List<Antibody> clones = cloneOperator.clone(population);
			hypermutation.mutate(clones);
			antigen.evaluate(clones);

			// Generate new antibodies
			List<Antibody> newPopulation = new ArrayList<>();
			for (int i = 0; i < generateNew; i++) {
				newPopulation.add(new Antibody(problem, random));
			}

			// Merge current population with clones
			population.addAll(clones);
			Collections.sort(population);

			// Fill the rest of the new population with the best antibodies from
			// current population
			for (int i = 0; i < populationSize - generateNew; i++) {
				newPopulation.add(population.get(i));
			}

			best = population.get(0);
			population = newPopulation;
		}

		return best;
	}

}
