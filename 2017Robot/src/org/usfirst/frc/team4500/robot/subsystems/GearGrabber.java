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
	private DoubleSolenoid panel;
	
	public GearGrabber() {
		grabber = new DoubleSolenoid(RobotMap.GRABBERSOL_1, RobotMap.GRABBERSOL_2);
		panel = new DoubleSolenoid(RobotMap.PANELSOL_1, RobotMap.PANELSOL_2);
		
		//grabber.set(Value.kForward);
		//panel.set(Value.kForward);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Extends the pistons forward to grab a gear
     */
    public void grabberForward() {
    	grabber.set(Value.kForward);
    }
    
    /**
     * Retracts the pistons to let go of a gear
     */
    public void grabberReverse() {
    	grabber.set(Value.kReverse);
    }
    
    public void panelExtend() {
    	panel.set(Value.kForward);
    }
    
    public void panelRetract() {
    	panel.set(Value.kReverse);
    }
    
    public void grabberToggle(boolean mode) {
    	if(mode == true) {
    		grabber.set(Value.kForward);
    	} else {
    		grabber.set(Value.kReverse);
    	}
    }
    
    public void panelToggle(boolean mode) {
    	if(mode == true) {
    		panel.set(Value.kForward);
    	} else {
    		panel.set(Value.kReverse);
    	}
    }
}

