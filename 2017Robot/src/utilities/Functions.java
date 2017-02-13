package utilities;

public class Functions {
	
	public static double cvtRange(double currentMin, double currentMax, double newMin, double newMax, double target) {
		return (((target - currentMin)*(newMax - newMin)) /
				(currentMax - currentMin)) 
				+ newMin;
	}
	
	public static double encoderPulseToInches(double pulse) {
		return pulse*0.115;
	}
	
	public static boolean ultrasonicInRange(double min, double max, double value) {
		return value >= min && value <= max ? true : false;
	}
}
