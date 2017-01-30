package org.usfirst.frc.team4500.robot.subsystems;

public class Functions {
	
	public static double cvtRange(double currentMin, double currentMax, double newMin, double newMax, double target) {
		return (((target - currentMin)*(newMax - newMin)) /
				(currentMax - currentMin)) 
				+ newMin;
	}

}
