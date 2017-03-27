package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.DriveTrain_OmniDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	public Talon lOmni, rOmni;
	public Spark fsOmni, bsOmni;
	
	public ADXRS450_Gyro gyro;
	
	//public Encoder lEncoder, rEncoder;
	
	public Ultrasonic sonic;
	
	public DriveTrain() {
		lOmni = new Talon(RobotMap.LMOTOR);
    	rOmni = new Talon(RobotMap.RMOTOR);
    	
    	fsOmni = new Spark(RobotMap.FSMOTOR);
    	bsOmni = new Spark(RobotMap.BSMOTOR);
    	
    	/*lEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    	rEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
    	lEncoder.setDistancePerPulse(0.115); // 1 pulse is .115 inches 2in/0.115
    	rEncoder.setDistancePerPulse(0.115); 
    	lEncoder.setSamplesToAverage(7);
    	rEncoder.setSamplesToAverage(7);*/
    	
    	sonic = new Ultrasonic(RobotMap.SONAR_OUTPUT, RobotMap.SONAR_INPUT);
    	sonic.setAutomaticMode(true); 
    	
    	/*horizEncoder.setMaxPeriod(.1);
		horizEncoder.setDistancePerPulse(1);
		horizEncoder.setMinRate(10);
		horizEncoder.setSamplesToAverage(7);*/
    	
    	gyro = new ADXRS450_Gyro(); 	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveTrain_OmniDrive());
    }
   
    public double getGyroAngle() {
		return gyro.getAngle();
	}
    
    public void resetGyro() {
    	gyro.reset();
    }

    
    public void gyroCorrect() {
    	if(gyro.getAngle() > 0.20 || gyro.getAngle() < -0.20) { // 0.25
    		if(gyro.getAngle() > 0) {
    			//lOmni.set(0.1); // 0.1
    			//rOmni.set(0.1);
    			bsOmni.set(-0.325); //bs is fs
    			fsOmni.set(-0.250); // 292
    		} else {
    			//lOmni.set(-0.1);
    			//rOmni.set(-0.1);
    			bsOmni.set(0.325);
    			fsOmni.set(0.250);
    		}
    	}
    }
    
    public void autoMoveForward() {
    	lOmni.set(-1/2.5); // o/3
    	rOmni.set(1/2.5);
    }
    
    /**
     * Function for driving the robot. If there is no twist then move forward, backwards or strafe
     * If there is twist then rotate the robot.
     * @param joyX
     * @param joyY
     * @param joyTwist
     */
    public void omniDrive(double x, double y, double z) {
    	lOmni.set(y-z*.5);
    	rOmni.set(-y-z*.5);
    	fsOmni.set(-x+z );
    	bsOmni.set(x+z);
    }
}

