package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.BallGrabber_Funnel;
import org.usfirst.frc.team4500.robot.commands.BallGrabber_Grab;
import org.usfirst.frc.team4500.robot.commands.Cannon_MoveLeft;
import org.usfirst.frc.team4500.robot.commands.Cannon_MoveRight;
import org.usfirst.frc.team4500.robot.commands.Climber_Climb;
import org.usfirst.frc.team4500.robot.commands.GearGrabber_GrabberToggle;
import org.usfirst.frc.team4500.robot.commands.GearGrabber_PanelToggle;
import org.usfirst.frc.team4500.robot.commands.Group_Fire;
import org.usfirst.frc.team4500.robot.commands.Group_Pickup_Drop;
import org.usfirst.frc.team4500.robot.commands.Group_Pickup_Drop_Stop;
import org.usfirst.frc.team4500.robot.commands.Group_Pickup_Lift;
import org.usfirst.frc.team4500.robot.commands.Group_Pickup_Pickup;

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
	Button grabBall, funnelBallForward, funnelBallBackward;
	Button grabGear, letGoGear;
	Button extendPanel, retractPanel;
	Button grabberToggle, panelToggle;
	Button climb;
	//Button pidMove, autoRun, gyroMove, gyroMove2;
	Button gearPickupPlace, gearPickupGrab;
	
	Button visionAlign, visionAlign2;
	
	public OI() {
		driveStick = new Joystick(0);
		shootStick = new Joystick(1);
		
		gearPickupPlace = new JoystickButton(driveStick, 11);
		gearPickupGrab = new JoystickButton(driveStick, 1);
		
		// Buttons for the Cannon subsystem
		moveCannonLeft = new JoystickButton(shootStick, 4);
		moveCannonLeft.whileHeld(new Cannon_MoveLeft(0.5));
		moveCannonLeft.whenReleased(new Cannon_MoveLeft(0));
		
		moveCannonRight = new JoystickButton(shootStick, 5);
		moveCannonRight.whileHeld(new Cannon_MoveRight(-0.5));
		moveCannonRight.whenReleased(new Cannon_MoveRight(0));
		
		/*feedBall = new JoystickButton(shootStick, 3);
		feedBall.whileHeld(new Cannon_Feed(1, true));
		feedBall.whenReleased(new Cannon_Feed(0, true));*/
		
		fireGroup = new JoystickButton(shootStick, 1);
		fireGroup.whileHeld(new Group_Fire(1, true));
		fireGroup.whenReleased(new Group_Fire(0, true));
		
		
		// Buttons for the BallGrabber subsystem
		grabBall = new JoystickButton(shootStick, 2);
		grabBall.whileHeld(new BallGrabber_Grab(-1));
		grabBall.whenReleased(new BallGrabber_Grab(0));
		
		
		funnelBallForward = new JoystickButton(shootStick, 6);
		funnelBallForward.whileHeld(new BallGrabber_Funnel(1, true));
		funnelBallForward.whenReleased(new BallGrabber_Funnel(0, true));
		
		funnelBallBackward = new JoystickButton(shootStick, 7);
		funnelBallBackward.whileHeld(new BallGrabber_Funnel(-1, true));
		funnelBallBackward.whenReleased(new BallGrabber_Funnel(0, true));
		
		
		// Buttons for the GearGrabber
		//grabGear = new JoystickButton(shootStick, 6);
		//grabGear.whenPressed(new GearGrabber_Grab());
		
		//letGoGear = new JoystickButton(shootStick, 7);
		//letGoGear.whenPressed(new GearGrabber_Letgo());
		
		//grabberToggle = new JoystickButton(shootStick, 10);
		//grabberToggle.whenPressed(new GearGrabber_GrabberToggle());
		
		panelToggle = new JoystickButton(shootStick, 11);
		panelToggle.whenPressed(new GearGrabber_PanelToggle());
		
		//extendPanel = new JoystickButton(shootStick, 11);
		//extendPanel.whenPressed(new GearGrabber_Extend());
	
		//retractPanel = new JoystickButton(shootStick, 10);
		//retractPanel.whenPressed(new GearGrabber_Retract()); 
		
		// Buttons for the Climber
		climb = new JoystickButton(shootStick, 3);
		climb.whileHeld(new Climber_Climb(1));
		climb.whenReleased(new Climber_Climb(0));
		
		// Buttons for the GearPickup
		gearPickupGrab.whileHeld(new Group_Pickup_Pickup());
		gearPickupGrab.whenReleased(new Group_Pickup_Lift());
		gearPickupPlace.whileHeld(new Group_Pickup_Drop());
		gearPickupPlace.whenReleased(new Group_Pickup_Drop_Stop());
		
		/*pidMove = new JoystickButton(driveStick, 5);
		pidMove.whenPressed(new DriveTrain_PIDMove(5, 15));
		
		autoRun = new JoystickButton(driveStick, 6);
		autoRun.whenPressed(new Auto_Test());
		
		gyroMove = new JoystickButton(driveStick, 3);
		gyroMove.whenPressed(new Group_MoveByAngle(90));
		
		gyroMove2 = new JoystickButton(driveStick, 4);
		gyroMove2.whenPressed(new Group_MoveByAngle(180));
		
		visionAlign = new JoystickButton(driveStick, 9);
		visionAlign.whenPressed(new Auto_VisionAdjustGear(Robot.visionServer.getData()));
		
		visionAlign2 = new JoystickButton(driveStick, 10);
		visionAlign2.whenPressed(new Auto_VisionAdjustGearPID(0, 10));
		gyroMove2.whenPressed(new Group_MoveByAngle(180));*/
	}
	
	/**
	 * The value of the x axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return x value from joystick (-1 to 1)
	 */
	public double getJoyX() {
		double xSquared = driveStick.getX();
		if(xSquared > 0) {
			// Ternary Operator:
			// condition ? if true : if false
			return (Math.abs(driveStick.getX()) < RobotMap.DEADZONE) ? 0 : Math.pow(driveStick.getX(), 2);
		} else {
			return (Math.abs(driveStick.getX()) < RobotMap.DEADZONE) ? 0 : xSquared*-xSquared;
		}
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
	}

	/**
	 * The value of the twist axis of the joystick, adjusted for deadzones and any necessary scaling
	 * @return the value from joystick (-1 to 1)
	 */
	public double getJoyTwist() {
		double twist = driveStick.getTwist();
		double finalTwist = (Math.abs(driveStick.getTwist()) < RobotMap.TWISTDEADZONE) ? 0 : Math.pow(Functions.cvtRange(0.6, 1, 0, 1, Math.abs(driveStick.getTwist())), 2);
		if( twist < 0) {
			return finalTwist *-1;
		} else {
			return finalTwist;
		}
	}
	
	/**
	 * Gets the joystick's scroll and converts it from the range -1-1 to 0-1
	 * @return The value of the scroll in the range 0-1
	 */
	public double getJoyScroll() {
		return Functions.cvtRange(-1, 1, 0, 1, shootStick.getZ());
	}
}
