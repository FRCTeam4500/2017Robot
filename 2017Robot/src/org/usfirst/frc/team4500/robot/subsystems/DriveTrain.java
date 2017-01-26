package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.OmniDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import utilities.Vector;
import utilities.Wheel;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	private Talon lOmni, rOmni;
	private Talon fsOmni, bsOmni;
	
	private ADXRS450_Gyro gyro;
	
	public DriveTrain() {
		lOmni = new Talon(RobotMap.LMOTOR);
    	rOmni = new Talon(RobotMap.RMOTOR);
    	
    	fsOmni = new Talon(RobotMap.FSMOTOR);
    	bsOmni = new Talon(RobotMap.BSMOTOR);
    	
    	//gyro = new ADXRS450_Gyro(); 	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new OmniDrive());
    }
    
    public double getGyroAngle() {
		return gyro.getAngle();
	}
    
    public void omniDrive2(double joyX, double joyY, double joyTwist) {
    	
    	double deadzone = RobotMap.DEADZONE;
    	//Deadzone defined in Robot Map
		
    	
    	//Forward / Backward functions
    	if(joyY > deadzone) {
    		lOmni.set(joyY);
    		rOmni.set(joyY);
    	} else if(joyY < -deadzone) {
    		lOmni.set(-joyY);
    		rOmni.set(-joyY);
    	} else {
    		lOmni.set(0);
    		rOmni.set(0);
    	}
    	
    	//Strafe functions
    	if(joyX > deadzone) {
    		fsOmni.set(joyX);
    		bsOmni.set(joyX);
    	} else if(joyX < -deadzone) {
    		fsOmni.set(-joyX);
    		bsOmni.set(-joyX);
    	} else {
    		fsOmni.set(0);
    		bsOmni.set(0);
    	}
    	
    	//Turn functions
    	if(joyTwist > deadzone) {
    		lOmni.set(joyTwist);
    		rOmni.set(-joyTwist);
    	} else if(joyTwist < -deadzone) {
    		lOmni.set(joyTwist);
    		rOmni.set(-joyTwist);
    	} else {
    		lOmni.set(0);
    		rOmni.set(0);
    	}
    }
}

