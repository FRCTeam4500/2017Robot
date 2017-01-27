package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearGrabber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private DoubleSolenoid grabber;
	
	public GearGrabber() {
		grabber = new DoubleSolenoid(RobotMap.GRABBERSOL_1, RobotMap.GRABBERSOL_2);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void grabberForward() {
    	grabber.set(Value.kForward);
    }
    
    public void grabberReverse() {
    	grabber.set(Value.kReverse);
    }
}

