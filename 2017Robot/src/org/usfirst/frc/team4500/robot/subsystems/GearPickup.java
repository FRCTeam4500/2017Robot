package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearPickup extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//private DigitalOutput pickupMotor;
	private DoubleSolenoid liftSol;
	private Talon pickupMotor;
	
	public GearPickup() {
		//pickupMotor = new DigitalOutput(RobotMap.PICKUPMOTOR);
		pickupMotor = new Talon(RobotMap.BALLGRABMOTOR);
		liftSol = new DoubleSolenoid(RobotMap.PICKUPSOL_1, RobotMap.PICKUPSOL_2);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void solExtend() {
    	liftSol.set(Value.kForward);
    }
    
    public void solRetract() {
    	liftSol.set(Value.kReverse);
    }
    
    public void enableMotor() {
    	//pickupMotor.set(true);
    	pickupMotor.set(1);
    }
    
    public void disableMotor() {
    	//pickupMotor.set(false);
    	pickupMotor.set(0);
    }
    
    public void moveMotor(double speed) {
    	pickupMotor.set(speed);
    }
 
}

