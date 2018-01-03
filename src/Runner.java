import java.io.IOException;

import hmo.clonalg.ProblemInstance;

public class Runner {
	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.err.println("Expected 1 argument: path to the file with problem description");
			System.exit(1);
		}
		
		ProblemInstance.readFromFile(args[0]);
	}
}
