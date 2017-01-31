package org.usfirst.frc.team4500.robot;

import utilities.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int LMOTOR = 0, RMOTOR = 1;
	
	public static final int FSMOTOR = 2, BSMOTOR = 3;
	
	public static final int HORIZMOTOR = 4, FIREMOTOR = 5, ELEVATORMOTOR = 6;

	public static final int GRABBERSOL_1 = 1, GRABBERSOL_2 = 2;
	
	public static final int BALLGRABMOTOR = 7;
	
	/**
	 * The deadzone on the joystick
	 */
	public static final double DEADZONE = 0.1;
	
}
