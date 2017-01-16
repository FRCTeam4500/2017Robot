package org.usfirst.frc.team4500.robot;

import utilities.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static final int LMOTOR = 0, RMOTOR = 1, FMOTOR = 2, BMOTOR = 3;
	
	/**
	 * The deadzone on the joystick
	 */
	public static final double DEADZONE = .05;
	
	/**
	 * The position of each wheel with relation to the center of the robot (in inches but that doesn't matter)
	 */
	public static final Vector lOmniPosition = new Vector(-11.75, -10.592, 0),
							   rOmniPosition = new Vector(-11.75, 10.592, 0), 
							   fOmniPosition = new Vector(0, 12.915, 0), 
							   bOmniPosition = new Vector(0, -12.915, 0);
	
	/**
	 * The direction of each wheel in relation the robot's coordinate system - may need to negativise some values
	 */
	public static final Vector  lOmniDirection = new Vector(0, 1, 0),
								rOmniDirection = new Vector(0, 1, 0),
								fOmniDirection = new Vector(1, 0, 0),
								bOmniDirection = new Vector(1, 0, 0);
	
	/**
	 * These values relate how different motors need to move according to their gear ratio. Set at dummy values now
	 */
	public static final double  lOmniRatio = 1, 
								rOmniRatio = 1, 
								fOmniRatio = 1, 
								bOmniRatio = 1;
	
	public static double visionData = 0;
	
}
