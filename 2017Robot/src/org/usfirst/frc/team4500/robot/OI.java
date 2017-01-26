package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.Grabber_Grab;
import org.usfirst.frc.team4500.robot.commands.Grabber_LetGo;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick driveStick, stickTwo;
	
	Button grab, letGo;
	
	public OI() {
		driveStick = new Joystick(0);
		stickTwo = new Joystick(1);
		
		grab = new JoystickButton(driveStick, 0);
		grab.whenPressed(new Grabber_Grab());
		letGo = new JoystickButton(driveStick, 0);
		letGo.whenPressed(new Grabber_LetGo());
	}
	
	/**
	 * The value of the x axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return x value from joystick (-1 to 1)
	 */
	public double getJoyX() {
		return (Math.abs(driveStick.getX()) < RobotMap.DEADZONE) ? 0 : driveStick.getX();
	}

	/**
	 * The value of the y axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return y value from joystick (-1 to 1)
	 */
	public double getJoyY() {
		return (Math.abs(driveStick.getY()) < RobotMap.DEADZONE) ? 0 : driveStick.getY();
	}

	/**
	 * The value of the twist axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return twist value from joystick (-1 to 1)
	 */
	public double getJoyTwist() {
		return ((Math.abs(driveStick.getTwist()) < RobotMap.DEADZONE) ? 0 : driveStick.getTwist());
	}
}
