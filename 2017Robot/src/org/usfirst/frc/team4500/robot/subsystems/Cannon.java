package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Cannon extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Talon horizMotor;
	private Talon fireMotor;
	private Talon feedMotor;
	
	public Cannon() {
		horizMotor = new Talon(RobotMap.HORIZMOTOR);
		fireMotor = new Talon(RobotMap.FIREMOTOR);
		feedMotor = new Talon(RobotMap.FEEDMOTOR);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveHorizontally(double speed) {
    	horizMotor.set(speed);
    }
    
    public void cannonSpinup(double speed) {
    	fireMotor.set(speed);
    }
}

