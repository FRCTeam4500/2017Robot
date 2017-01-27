package org.usfirst.frc.team4500.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallGrabber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private Talon grabberMotor;

	public BallGrabber() {
		grabberMotor = new Talon(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

