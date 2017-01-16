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
	
	//private Talon lOmni, rOmni;
	//private Talon fStraif, bStraif;
	
	private Wheel[] omniWheels;
	
	//private RobotDrive omniDrive;
	
	private Talon lOmni, rOmni;
	private Talon fOmni, bOmni;
	private Talon lTank, rTank;
	
	private ADXRS450_Gyro gyro;
	
	private RobotDrive strafeDrive;
	
	public DriveTrain() {
		lOmni = new Talon(RobotMap.LMOTOR);
    	rOmni = new Talon(RobotMap.RMOTOR);
    	fOmni = new Talon(RobotMap.FMOTOR);
    	bOmni = new Talon(RobotMap.BMOTOR);
    	lTank = lOmni;
    	rTank = rOmni;
    	
    	gyro = new ADXRS450_Gyro();
    	
    	strafeDrive = new RobotDrive(fOmni, bOmni);
    	omniWheels = new Wheel[4];
    	omniWheels[0] = new Wheel(RobotMap.lOmniPosition, RobotMap.lOmniDirection, RobotMap.lOmniRatio, lOmni);
    	omniWheels[1] = new Wheel(RobotMap.rOmniPosition, RobotMap.rOmniDirection, RobotMap.rOmniRatio, rOmni);
    	omniWheels[2] = new Wheel(RobotMap.fOmniPosition, RobotMap.fOmniDirection, RobotMap.fOmniRatio, fOmni);
    	omniWheels[3] = new Wheel(RobotMap.bOmniPosition, RobotMap.bOmniDirection, RobotMap.bOmniRatio, bOmni);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new OmniDrive());
    }
    
    /*public void OmniDrive(double joyY, double joyTwist) {
    	omniDrive.arcadeDrive(joyY, joyTwist);
    }*/
    
    public double getGyroAngle() {
		return gyro.getAngle();
	}
    
    /**
     * Six wheel omni drive given joystick input and a gyroscope reading
     * @param joyX x-axis of joystick input
     * @param joyY y-axis of joystick input
     * @param joyTwist twist of joystick input
     * @param gyro gyroscope reading (relative, degrees)
     */
    public void omniDrive(double joyX, double joyY, double joyTwist, double gyro) {
    	gyro /= ((360 * (Math.ceil(gyro/360))) * 2*Math.PI);	//Reduces angle to a value between -1 and 1, then converts it to radians
    	Vector linear = new Vector(joyX, joyY, 0); 					//The non rotational component of the motion
    	Vector rotation = new Vector(0, 0, joyTwist);// - gyro); XXX I think this was the problem, the gyro angle should be taken into account later
    																//The rotational component of the motion


    	double speeds[] = new double[4]; 							//Array to store each wheel speed
    	double maxSpeed = 0.0; 										//Stores the value of the maximum speed of any of the wheels during the current iteration
    	
    	for(int i = 0; i<omniWheels.length; i++){    		
    		speeds[i] = omniWheels[i].getSpeed(linear, rotation, gyro); 	//Gets the speed of each wheel relative to the robot's current position.
    																//i.e. the actual speed of the wheel 
    		
    		
    		if(Math.abs(speeds[i]) > maxSpeed){
    			maxSpeed = Math.abs(speeds[i]); 					//Sets the value of the max speed
    		}
    	}
    	
    	if(maxSpeed > 1){											//In case the max speed is too high,
    																//reduces all speeds using the max speed as a scale factor
    		for(int i = 0; i<omniWheels.length; i++){
    			speeds[i] /= maxSpeed;
    		}
    	}
    																//Sets the wheels to the proper speed
    	for(int i = 0; i<omniWheels.length; i++){
    		omniWheels[i].setSpeed(speeds[i]);
    	}
    }
}

