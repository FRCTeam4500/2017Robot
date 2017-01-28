package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.BallGrabber_Grab;
import org.usfirst.frc.team4500.robot.commands.Cannon_Feed;
import org.usfirst.frc.team4500.robot.commands.Cannon_MoveLeft;
import org.usfirst.frc.team4500.robot.commands.Cannon_MoveRight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick driveStick, shootStick;
	
	Button moveCannonLeft, moveCannonRight, feedBall;
	Button grabBall;
	
	
	
	public OI() {
		driveStick = new Joystick(0);
		shootStick = new Joystick(1);
		
		// Buttons for the Cannon subsystem
		moveCannonLeft = new JoystickButton(shootStick, 4);
		moveCannonLeft.whileHeld(new Cannon_MoveLeft(0.2));
		moveCannonLeft.whenReleased(new Cannon_MoveLeft(0));
		
		moveCannonRight = new JoystickButton(shootStick, 5);
		moveCannonRight.whileHeld(new Cannon_MoveRight(0.2));
		moveCannonRight.whenReleased(new Cannon_MoveRight(0));
		
		feedBall = new JoystickButton(shootStick, 6);
		feedBall.whileHeld(new Cannon_Feed(0.5));
		feedBall.whenReleased(new Cannon_Feed(0));
		
		
		// Buttons for the BallGrabber subsystem
		grabBall = new JoystickButton(shootStick, 0);
		grabBall.whileHeld(new BallGrabber_Grab(0.5));
		grabBall.whenReleased(new BallGrabber_Grab(0));
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
	 * @return the value from joystick (-1 to 1)
	 */
	public double getJoyTwist() {
		return ((Math.abs(driveStick.getTwist()) < RobotMap.DEADZONE) ? 0 : driveStick.getTwist());
	}
	
	/**
	 * Gets the joystick's scroll and converts it from the range -1-1 to 0-1
	 * @return The value of the scroll in the range 0-1
	 */
	public double getJoyScroll() {
		return ((shootStick.getZ()-(-1))*1)/2;
		//return shootStick.getZ();
	}
}
