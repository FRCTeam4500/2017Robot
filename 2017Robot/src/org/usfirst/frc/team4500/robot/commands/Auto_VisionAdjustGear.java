package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;
import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Auto_VisionAdjustGear extends Command {
	
	double data = 0;
	boolean firstRun = true;

    public Auto_VisionAdjustGear(double data) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        //this.data = data;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(firstRun == true) {
    		data = Robot.visionServer.getData();
    		firstRun = false;
    	}
    	SmartDashboard.putNumber("cmd data", data);
    	if(data > 320) {
    		Robot.drivetrain.fsOmni.set(-0.4);
    		Robot.drivetrain.bsOmni.set(0.4);
    	} else {
    		Robot.drivetrain.fsOmni.set(0.4);
    		Robot.drivetrain.bsOmni.set(-0.4);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double distLeft = 0;
    	if(Robot.visionServer.getData() > 320) {
    		distLeft = Robot.visionServer.getData() - 320;
    	} else {
    		distLeft = 320 - Robot.visionServer.getData();
    	}
    	SmartDashboard.putNumber("cmd stop", distLeft);
    	if(distLeft < 10 && distLeft > -10) {
    		firstRun = true;
    		return true;
    	} else {
    		return false;
    	}
        //return distLeft < 10 && distLeft > -10 ? true : false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
