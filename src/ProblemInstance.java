import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ProblemInstance {
	private int stopsCount;
    private int studentsCount;
    private int busCapacity;
    
    private List<Point> stops;
    private List<Point> students;
    private double[][] stopDistances;
    private List<List<Integer>> availableStops;
    
    private ProblemInstance(int stopsCount, int studentsCount, int busCapacity, double maximumWalk, List<Point> stops, List<Point> students) {
		super();
		this.stopsCount = stopsCount;
		this.studentsCount = studentsCount;
		this.busCapacity = busCapacity;
		this.stops = stops;
		this.students = students;
		this.stopDistances = calculateStopDistances(stops);
		this.availableStops = calculateAvailableStops(stops, students, maximumWalk);
	}

	public static ProblemInstance readFromFile(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		String line = reader.readLine();
		
		String[] header = line.split(",");
		int stopsCount = Integer.parseInt(header[0].trim().split(" ")[0]);
		int studentsCount = Integer.parseInt(header[1].trim().split(" ")[0]);
		double maximumWalk = Double.parseDouble(header[2].trim().split(" ")[0]);
		int busCapacity = Integer.parseInt(header[3].trim().split(" ")[0]);
		
		reader.readLine(); // Skip an empty line
		
		List<Point> stops = new ArrayList<>();
		for (int i = 0; i < stopsCount; i++) {
			String[] stop = reader.readLine().split("\t");
			double x = Double.parseDouble(stop[1]);
			double y = Double.parseDouble(stop[2]);
			stops.add(new Point(x, y));
		}
		
		reader.readLine(); // Skip an empty line
		reader.readLine(); // Skip an empty line
		
		List<Point> students = new ArrayList<>();
		for (int i = 0; i < studentsCount; i++) {
			String[] student = reader.readLine().split("\t");
			double x = Double.parseDouble(student[1]);
			double y = Double.parseDouble(student[2]);
			stops.add(new Point(x, y));
		}
		
		reader.close();
    	return new ProblemInstance(stopsCount, studentsCount, busCapacity, maximumWalk, stops, students);
    }
	
	private double[][] calculateStopDistances(List<Point> stops) {
		int stopsCount = stops.size();
		double[][] stopDistances = new double[stopsCount][stopsCount];
		
		for (int i = 0; i < stopsCount; i++) {
			for (int j = 0; j < i; j++) {
				double x1 = stops.get(i).getX();
				double y1 = stops.get(i).getY();
				double x2 = stops.get(j).getX();
				double y2 = stops.get(j).getY();
				
				double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1- y2, 2));
				stopDistances[i][j] = distance;
				stopDistances[j][i] = distance;
			}
		}
		
		return stopDistances;
	}
	
	private List<List<Integer>> calculateAvailableStops(List<Point> stops, List<Point> students, double maximumWalk) {
		List<List<Integer>> availableStops = new ArrayList<>();
		int stopsCount = stops.size();
		int studentsCount = students.size();
		
		for (int student = 0; student < studentsCount; student++) {
			List<Integer> availableForStudent = new ArrayList<>();
			for (int stop = 1; stop < stopsCount; stop++) {
				double x1 = students.get(student).getX();
				double y1 = students.get(student).getY();
				double x2 = stops.get(stop).getX();
				double y2 = stops.get(stop).getY();
				
				double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1- y2, 2));
				if (distance <= maximumWalk) {
					availableForStudent.add(stop);
				}
			}
			availableStops.add(availableForStudent);
		}
		
		return availableStops;
	}
}

