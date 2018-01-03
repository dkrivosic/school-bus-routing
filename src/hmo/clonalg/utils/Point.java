package hmo.clonalg.utils;

public class Point {
	private double x;
	private double y;
	
	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			return false;
		}
		
		double e = 1e-6;
		Point p = (Point) obj;
		
		return Math.abs(p.x - x) < e && Math.abs(p.y - y) < e;
	}

}
