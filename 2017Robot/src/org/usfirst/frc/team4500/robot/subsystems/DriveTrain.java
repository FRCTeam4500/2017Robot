package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.OmniDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	private Talon lOmni, rOmni;
	private Talon fsOmni, bsOmni;
	
	//private ADXRS450_Gyro gyro;
	
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
    
//    public double getGyroAngle() {
//		return gyro.getAngle();
//	}
    
    public void omniDrive(double joyX, double joyY, double joyTwist) {
    	if(joyY > 0.05) {
    		SmartDashboard.putString("In Y+", "Yes");
    		lOmni.set(1);
    		rOmni.set(1);
    	} else if(joyY < -0.05) {
    		SmartDashboard.putString("In Y-", "Yes");
    		lOmni.set(-1);
    		rOmni.set(-1);
    	} else {
    		lOmni.set(0);
    		rOmni.set(0);
    	}
    	
    	if(joyX > 0.05) {
    		SmartDashboard.putString("In X+", "Yes");
    		fsOmni.set(1);
    		bsOmni.set(1);
    	} else if(joyX < -0.05) {
    		SmartDashboard.putString("In X-", "Yes");
    		fsOmni.set(-1);
    		bsOmni.set(-1);
    	} else {
    		fsOmni.set(0);
    		bsOmni.set(0);
    	}
    	
    	if(joyTwist > 0.05) {
    		lOmni.set(-1);
    		rOmni.set(1);
    	} else if(joyTwist < -0.05) {
    		lOmni.set(1);
    		rOmni.set(-1);
    	} else {
    		lOmni.set(0);
    		rOmni.set(0);
    	} 	
    }
}

