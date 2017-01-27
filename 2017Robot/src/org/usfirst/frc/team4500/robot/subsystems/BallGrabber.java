package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallGrabber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static Talon grabberMotor;

	public BallGrabber() {
		grabberMotor = new Talon(RobotMap.BALLGRABMOTOR);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void grabBall(double speed) {
    	grabberMotor.set(speed);
    }
}

