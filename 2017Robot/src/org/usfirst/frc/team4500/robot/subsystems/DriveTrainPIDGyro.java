package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.DriveTrain_OmniDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainPIDGyro extends PIDSubsystem {
	
	public Talon lOmni, rOmni;
	public Spark fsOmni, bsOmni;
	
	public ADXRS450_Gyro gyro;
	
	public Ultrasonic sonic;

    // Initialize your subsystem here
    public DriveTrainPIDGyro() {
    	super("DriveTrainPIDGyro", 0.1, 0, 0);
    	lOmni = new Talon(RobotMap.LMOTOR);
    	rOmni = new Talon(RobotMap.RMOTOR);
    	
    	fsOmni = new Spark(RobotMap.FSMOTOR);
    	bsOmni = new Spark(RobotMap.BSMOTOR);
    	
    	gyro = new ADXRS450_Gyro(); 	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void pidMove(double dLow, double dHigh) {
    	SmartDashboard.putNumber("onTarget", getPIDController().getError());
    	enable();
    	setInputRange(-0.5, 0.5);
    }
    
    public boolean getError() {
    	SmartDashboard.putNumber("Error", getPIDController().getError());
    	if(getPIDController().getError() < 6 && getPIDController().getError() > -6) { // 2, -2
    		disable();
    		return true;
    	} else {
    		return false;
    	}
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveTrain_OmniDrive());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return gyro.getAngle();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	bsOmni.set(output/3);
    }
    
    public void omniDrive(double x, double y, double z) {
    	lOmni.set(y);
    	rOmni.set(-y);
    	fsOmni.set(-x+z);
    	bsOmni.set(x+z);
    }
}
