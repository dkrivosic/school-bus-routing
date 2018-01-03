package hmo.clonalg.utils;
import java.util.Random;

public class RandomNumberGenerator {
	private Random random;
	
	public RandomNumberGenerator() {
		this.random = new Random();
	}
	
	public double nextDouble() {
		return random.nextDouble();
	}
	
	public int nextInt(int upperBound) {
		return random.nextInt(upperBound);
	}
	
	public int nextInt(int lowerBound, int upperBound) {
		return random.nextInt(upperBound - lowerBound) + lowerBound;
	}
	

}
