import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hmo.clonalg.Antibody;
import hmo.clonalg.Antigen;
import hmo.clonalg.CloneOperator;
import hmo.clonalg.ProblemInstance;
import hmo.clonalg.algorithm.Clonalg;
import hmo.clonalg.mutations.ChangeStudentStationMutation;
import hmo.clonalg.mutations.Hypermutation;
import hmo.clonalg.mutations.IMutation;
import hmo.clonalg.mutations.RemoveBusMutation;
import hmo.clonalg.mutations.SwapStationsMutation;
import hmo.clonalg.utils.RandomNumberGenerator;
import hmo.clonalg.utils.ResultFileWriter;

public class Runner {
	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.err.println("At least 1 argument expected: path to the file with problem description");
			System.exit(1);
		}
		
		for (String filename : args) {
			System.out.println("Problem " + filename);
			ProblemInstance problem = ProblemInstance.readFromFile(filename);
			ResultFileWriter writer = new ResultFileWriter(filename);
			RandomNumberGenerator random = new RandomNumberGenerator();
			Antigen antigen = new Antigen(problem);
			CloneOperator cloneOperator = new CloneOperator(0.5);
			int populationSize = 100;
			int maxIterations = 150000;
			int generateNew = 5;
	
			IMutation hypermutation = new Hypermutation(Stream.of(new ChangeStudentStationMutation(0.05, random, problem),
					new RemoveBusMutation(0.05, random, problem),
					new SwapStationsMutation(0.1, random)).collect(Collectors.toList()));
	
			Clonalg algorithm = new Clonalg(populationSize, maxIterations, generateNew, problem, random, antigen,
					cloneOperator, hypermutation, writer);
	
			Antibody result = algorithm.run();
			System.out.println("Cost: " + result.cost);
			writer.write(result, "ne");
			System.out.println("Total number of evaluations: " + antigen.getNumberOfEvaluations());
			System.out.println();
		}
	}
}
