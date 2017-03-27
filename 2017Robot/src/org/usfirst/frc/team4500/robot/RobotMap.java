package org.usfirst.frc.team4500.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// PWM Motor ports
	public static final int LMOTOR = 0, RMOTOR = 1;
	
	public static final int FSMOTOR = 3, BSMOTOR = 2;

	public static final int HORIZMOTOR = 5, FIREMOTOR = 4, FEEDMOTOR = 6;
	
	public static final int CLIMBMOTOR = 7;
	
	public static final int BALLGRABMOTOR = 8;
	
	public static final int FUNNELMOTOR = 9;
	
	// Pneumatic ports
	public static final int PANELSOL_1 = 0, PANELSOL_2 = 1;
	
	public static final int GRABBERSOL_1 = 2, GRABBERSOL_2 = 3;
	
	public static final int PICKUPSOL_1 = 4, PICKUPSOL_2 = 5;
	
	public static final int COMPRESSOR = 0;
	
	// Sensor ports
	public static final int SONAR_OUTPUT = 8, SONAR_INPUT = 9; 
	
	// Deadzones
	public static final double DEADZONE = 0.4;
	public static final double TWISTDEADZONE = 0.6;
	
	// Stores the info from the Jetson
	public static double vData = 0.0;
	
	// The width and height of the image that is processed on the Jetson (resolution)
	public static final double VISION_IMAGE_WIDTH = 640.0, VISION_IMAGE_HEIGHT = 480.0;
	
	public static final int CV_IMAGE_WIDTH = 160, CV_IMAGE_HEIGHT = 120;
	
}
