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
	
	/**
	 * The deadzone on the joystick
	 */
	public static final double DEADZONE = .05;
	
}
