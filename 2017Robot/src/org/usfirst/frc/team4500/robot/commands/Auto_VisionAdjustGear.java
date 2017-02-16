package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;
import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto_VisionAdjustGear extends Command {
	
	double data = 0;

    public Auto_VisionAdjustGear(double data) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        this.data = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.vData > RobotMap.VISION_IMAGE_WIDTH/2) {
    		Robot.drivetrain.fsOmni.set(-0.2);
    		Robot.drivetrain.bsOmni.set(0.2);
    	} else {
    		Robot.drivetrain.fsOmni.set(0.2);
    		Robot.drivetrain.bsOmni.set(-0.2);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double distLeft = Math.abs((RobotMap.VISION_IMAGE_WIDTH/2) - RobotMap.vData);
        return distLeft < 2 && distLeft > -2 ? true : false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
