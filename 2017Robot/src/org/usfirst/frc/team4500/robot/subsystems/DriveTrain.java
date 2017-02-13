package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.OmniDrive;

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
	
	private Talon lOmni, rOmni;
	private Spark fsOmni, bsOmni;
	
	public ADXRS450_Gyro gyro;
	
	public Encoder lEncoder, rEncoder;
	
	//public Ultrasonic sonic;
	public AnalogInput ultrasonic;
	
	public DriveTrain() {
		lOmni = new Talon(RobotMap.LMOTOR);
    	rOmni = new Talon(RobotMap.RMOTOR);
    	
    	fsOmni = new Spark(RobotMap.FSMOTOR);
    	bsOmni = new Spark(RobotMap.BSMOTOR);
    	
    	lEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    	rEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
    	lEncoder.setDistancePerPulse(0.115); // 1 pulse is .115 inches 2in/0.115
    	rEncoder.setDistancePerPulse(0.115); 
    	lEncoder.setSamplesToAverage(7);
    	rEncoder.setSamplesToAverage(7);
    	
    	//sonic = new Ultrasonic(5, 5);
    	//sonic.setAutomaticMode(true);
    	ultrasonic = new AnalogInput(0); 
    	
    	/*horizEncoder.setMaxPeriod(.1);
		horizEncoder.setDistancePerPulse(1);
		horizEncoder.setMinRate(10);
		horizEncoder.setSamplesToAverage(7);*/
    	
    	gyro = new ADXRS450_Gyro(); 	
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new OmniDrive());
    }
   
    public double getGyroAngle() {
		return gyro.getAngle();
	}
    
    /**
     * Function for driving the robot. If there is no twist then move forward, backwards or strafe
     * If there is twist then rotate the robot.
     * @param joyX
     * @param joyY
     * @param joyTwist
     */
    public void omniDrive(double x, double y, double z) {
    	
    	lOmni.set(y);
    	rOmni.set(-y);
    	fsOmni.set(-x+z);
    	bsOmni.set(x+z);
    	
    	/*if(x == 0 && y == 0 && (z > 0.8|| z < -0.8)) {
    		lOmni.set(-z);
    		rOmni.set(-z);
    	} else {
    		lOmni.set(y+z*.5);
        	rOmni.set(-y-z*.5);
        	fsOmni.set(-x+z*.9);
        	bsOmni.set(x+z*.9);*/
    	
    	
    	/*if(joyTwist > 0.3 || joyTwist < -0.3) {
    		fsOmni.set(-joyX);
    		bsOmni.set(joyX);
    		lOmni.set(-joyTwist);
    		rOmni.set(-joyTwist);
    	} else {
    		fsOmni.set(-joyX);
    		bsOmni.set(joyX);
    		lOmni.set(joyY);
    		rOmni.set(-joyY);
    	}*/
    }
    
    public void resetLeftEncoder() {
    	lEncoder.reset();
    }
    
    public void resetRightEncoder() {
    	rEncoder.reset();
    }
}

