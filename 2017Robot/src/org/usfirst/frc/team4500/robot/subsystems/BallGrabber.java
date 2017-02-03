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
	
	private Talon grabberMotor;
	private Talon funnelMotor;

	public BallGrabber() {
		grabberMotor = new Talon(RobotMap.BALLGRABMOTOR);
		funnelMotor = new Talon(RobotMap.FUNNELMOTOR);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /*
     * Turns on the motor to bring balls into the storage
     */
    public void grabBall(double speed) {
    	grabberMotor.set(speed);
    }
    
    public void funnelBall(double speed) {
    	funnelMotor.set(speed);
    }
}

