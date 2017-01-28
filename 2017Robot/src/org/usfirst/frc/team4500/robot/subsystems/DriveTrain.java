package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.OmniDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	private Talon lOmni, rOmni;
	private Spark fsOmni, bsOmni;
	
	//private ADXRS450_Gyro gyro;
	
	public DriveTrain() {
		lOmni = new Talon(RobotMap.LMOTOR);
    	rOmni = new Talon(RobotMap.RMOTOR);
    	
    	fsOmni = new Spark(RobotMap.FSMOTOR);
    	bsOmni = new Spark(RobotMap.BSMOTOR);
    	
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
    	if (joyTwist == 0) {
    		fsOmni.set(joyX);
    		bsOmni.set(joyX);
    		lOmni.set(joyY);
    		rOmni.set(joyY);
    	} else if(joyTwist > 0.3 || joyTwist < -0.3) {
    		fsOmni.set(joyX);
    		bsOmni.set(joyX);
    		lOmni.set(joyTwist);
    		rOmni.set(-joyTwist);
    	} 
//    	if(joyTwist == 0) {
//    		lOmni.set(0);
//    		rOmni.set(0);
//    	}
//    	if(joyY > 0.1) {
//    		SmartDashboard.putString("In Y+", "Yes");
//    		lOmni.set(joyY);
//    		rOmni.set(joyY);
//    	} else if(joyY < -0.1) {
//    		SmartDashboard.putString("In Y-", "Yes");
//    		lOmni.set(joyY);
//    		rOmni.set(joyY);
//    	} else {
//    		lOmni.set(0);
//    		rOmni.set(0);
//    	}
//    	
//    	if(joyX > 0.1) {
//    		SmartDashboard.putString("In X+", "Yes");
//    		fsOmni.set(joyX);
//    		bsOmni.set(joyX);
//    	} else if(joyX < -0.1) {
//    		SmartDashboard.putString("In X-", "Yes");
//    		fsOmni.set(joyX);
//    		bsOmni.set(joyX);
//    	} else {
//    		fsOmni.set(0);
//    		bsOmni.set(0);
//    	}
//    	
//    	if(joyTwist > 0.1) {
//    		lOmni.set(joyTwist);
//    		rOmni.set(joyTwist);
//    	} else if(joyTwist < -0.1) {
//    		lOmni.set(joyTwist);
//    		rOmni.set(joyTwist);
//    	} else {
//    		lOmni.set(joyTwist);
//    		rOmni.set(joyTwist);
//    	}
    	
    	
    	
    }
}

