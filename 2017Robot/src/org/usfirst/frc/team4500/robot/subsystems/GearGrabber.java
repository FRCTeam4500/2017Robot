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
	
	public static DoubleSolenoid grabSol;
	
	public GearGrabber() {
		grabSol = new DoubleSolenoid(RobotMap.GRAB_SOL_1,RobotMap.GRAB_SOL_2);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void grab() {
    	grabSol.set(Value.kForward);
    	
    }
    
    public void letGo() {
    	grabSol.set(Value.kReverse);
    	
    }
}

