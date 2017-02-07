package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.BallGrabber_Funnel;
import org.usfirst.frc.team4500.robot.commands.BallGrabber_Grab;
import org.usfirst.frc.team4500.robot.commands.Cannon_Feed;
import org.usfirst.frc.team4500.robot.commands.Cannon_MoveLeft;
import org.usfirst.frc.team4500.robot.commands.Cannon_MoveRight;
import org.usfirst.frc.team4500.robot.commands.Climber_Climb;
import org.usfirst.frc.team4500.robot.commands.GearGrabber_Extend;
import org.usfirst.frc.team4500.robot.commands.GearGrabber_Grab;
import org.usfirst.frc.team4500.robot.commands.GearGrabber_Letgo;
import org.usfirst.frc.team4500.robot.commands.GearGrabber_Retract;
import org.usfirst.frc.team4500.robot.commands.Group_Fire;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import utilities.Functions;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	Joystick driveStick, shootStick;
	
	Button moveCannonLeft, moveCannonRight, feedBall, fireGroup;
	Button grabBall, funnelBall;
	Button grabGear, letGoGear;
	Button extendPanel, retractPanel;
	Button climb;
	
	
	
	public OI() {
		driveStick = new Joystick(0);
		shootStick = new Joystick(1);
		
		// Buttons for the Cannon subsystem
		moveCannonLeft = new JoystickButton(shootStick, 4);
		moveCannonLeft.whileHeld(new Cannon_MoveLeft(0.3));
		moveCannonLeft.whenReleased(new Cannon_MoveLeft(0));
		
		moveCannonRight = new JoystickButton(shootStick, 5);
		moveCannonRight.whileHeld(new Cannon_MoveRight(-0.3));
		moveCannonRight.whenReleased(new Cannon_MoveRight(0));
		
		feedBall = new JoystickButton(shootStick, 3);
		feedBall.whileHeld(new Cannon_Feed(0.5));
		feedBall.whenReleased(new Cannon_Feed(0));
		
		fireGroup = new JoystickButton(shootStick, 1);
		fireGroup.whileHeld(new Group_Fire(1));
		fireGroup.whenReleased(new Group_Fire(0));
		
		
		// Buttons for the BallGrabber subsystem
		grabBall = new JoystickButton(shootStick, 2);
		grabBall.whileHeld(new BallGrabber_Grab(-0.5));
		grabBall.whenReleased(new BallGrabber_Grab(0));
		funnelBall = new JoystickButton(shootStick, 12);
		funnelBall.whileHeld(new BallGrabber_Funnel(0.5));
		funnelBall.whenReleased(new BallGrabber_Funnel(0));
		
		
		// Buttons for the GearGrabber
		grabGear = new JoystickButton(shootStick, 5);
		grabGear.whenPressed(new GearGrabber_Grab());
		
		letGoGear = new JoystickButton(shootStick, 6);
		letGoGear.whenPressed(new GearGrabber_Letgo());
		
		extendPanel = new JoystickButton(shootStick, 7);
		extendPanel.whenPressed(new GearGrabber_Extend());
		
		retractPanel = new JoystickButton(shootStick, 8);
		retractPanel.whenPressed(new GearGrabber_Retract());
		
		// Buttons for the Climber
		climb = new JoystickButton(shootStick, 0);
		climb.whileHeld(new Climber_Climb(0.5));
		climb.whenReleased(new Climber_Climb(0));
	}
	
	/**
	 * The value of the x axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return x value from joystick (-1 to 1)
	 */
	public double getJoyX() {
		double xSquared = driveStick.getX();
		if(xSquared > 0) {
			return (Math.abs(driveStick.getX()) < RobotMap.DEADZONE) ? 0 : Math.pow(driveStick.getX(), 2);
		} else {
			return (Math.abs(driveStick.getX()) < RobotMap.DEADZONE) ? 0 : xSquared*-xSquared;
		}
		//return (Math.abs(driveStick.getX()) < RobotMap.DEADZONE) ? 0 : driveStick.getX();
		//return (Math.abs(driveStick.getX()) < RobotMap.DEADZONE) ? 0 : Math.pow(driveStick.getX(), 2);
	}

	/**
	 * The value of the y axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return y value from joystick (-1 to 1)
	 */
	public double getJoyY() {
		double ySquared = driveStick.getY();
		if(ySquared > 0) {
			return (Math.abs(driveStick.getY()) < RobotMap.DEADZONE) ? 0 : Math.pow(driveStick.getY(), 2);
		} else {
			return (Math.abs(driveStick.getY()) < RobotMap.DEADZONE) ? 0 : ySquared*-ySquared;
		}
		//return (Math.abs(driveStick.getY()) < RobotMap.DEADZONE) ? 0 : driveStick.getY();
	}

	/**
	 * The value of the twist axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return the value from joystick (-1 to 1)
	 */
	public double getJoyTwist() {
		double twist = driveStick.getTwist();
		if(twist > 0){
			return (Math.abs(driveStick.getTwist()) < RobotMap.TWISTDEADZONE) ? 0 : Math.pow(Functions.cvtRange(0.6, 1, 0, 1, driveStick.getTwist()), 2);
		} else {
			twist = Functions.cvtRange(-0.6, -1, 0, -1, driveStick.getTwist());
			return (Math.abs(driveStick.getTwist()) < RobotMap.TWISTDEADZONE) ? 0 : twist * -twist;
		}
		//return ((Math.abs(driveStick.getTwist()) < RobotMap.DEADZONE) ? 0 : driveStick.getTwist());
		//return (Math.abs(driveStick.getTwist()) < RobotMap.TWISTDEADZONE) ? 0 : Math.pow(Functions.cvtRange(0.6, 1, 0, 1, driveStick.getTwist()), 2);
	}
	
	/**
	 * Gets the joystick's scroll and converts it from the range -1-1 to 0-1
	 * @return The value of the scroll in the range 0-1
	 */
	public double getJoyScroll() {
		//return ((shootStick.getZ()-(-1))*1)/2;
		//return shootStick.getZ();
		return Functions.cvtRange(-1, 1, 0, 1, shootStick.getZ());
	}
}
