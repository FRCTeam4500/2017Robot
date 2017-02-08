package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class DriveTrainPID extends PIDSubsystem {

	private Talon lOmni, rOmni;
	private Spark fsOmni, bsOmni;
	
	private ADXRS450_Gyro gyro;
	
	private Encoder lEncoder, rEncoder;
	
    public DriveTrainPID() {
    	// Name, P, I, D
    	super("DriveTrainPID", 1.0, 0.0, 0.0);
    	lOmni = new Talon(RobotMap.LMOTOR);
    	rOmni = new Talon(RobotMap.RMOTOR);
    	
    	fsOmni = new Spark(RobotMap.FSMOTOR);
    	bsOmni = new Spark(RobotMap.BSMOTOR);
    	
    	lEncoder = new Encoder(9, 8);
    	rEncoder = new Encoder(7, 6);
    	lEncoder.setDistancePerPulse(0.115); // 1 pulse is .115 inches 2in/0.115
    	rEncoder.setDistancePerPulse(0.115); 
    	
    	gyro = new ADXRS450_Gyro(); 	
        // Use these to get going:
        setSetpoint(20); //-  Sets where the PID controller should move the system
        //                  to
        enable(); //- Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return lEncoder.get(); // pidGet?
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	lOmni.set(output);
    	rOmni.set(output);
    }
}
