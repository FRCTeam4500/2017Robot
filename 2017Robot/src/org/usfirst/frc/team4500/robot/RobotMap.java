package org.usfirst.frc.team4500.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Motor ports
	public static final int LMOTOR = 0, RMOTOR = 1;
	
	public static final int FSMOTOR = 3, BSMOTOR = 2;

	public static final int HORIZMOTOR = 5, FIREMOTOR = 4, FEEDMOTOR = 6;
	
	public static final int BALLGRABMOTOR = 8;
	
	public static final int CLIMBMOTOR = 7;
	
	public static final int FUNNELMOTOR = 9;
	
	// Pneumatic ports
	public static final int GRABBERSOL_1 = 0, GRABBERSOL_2 = 1;
	
	public static final int PANELSOL_1 = 2, PANELSOL_2 = 3;
	
	public static final int COMPRESSOR = 0;
	
	// Others
	public static final double DEADZONE = 0.4;
	public static final double TWISTDEADZONE = 0.6;
	
}
