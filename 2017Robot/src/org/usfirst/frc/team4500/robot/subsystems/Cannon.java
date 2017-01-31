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
		feedMotor = new Talon(RobotMap.ELEVATORMOTOR);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Moves the robot's turret horizontally. 
     * If the value is positive then move right. If it's negative then move left
     * @param speed
     */
    public void moveHorizontally(double speed) {
    	horizMotor.set(speed);
    }
    
    /**
     * Starts spinning the turret's fire motor.
     * @param speed
     */
    public void fireSpinup(double speed) {
    	fireMotor.set(speed);
    }
    
    /**
     * Enables the motor for feeding balls up to the turret
     * @param speed
     */
    public void feederStartup(double speed) {
    	feedMotor.set(speed);
    }
}

