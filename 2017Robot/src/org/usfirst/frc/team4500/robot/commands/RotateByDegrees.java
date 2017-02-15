package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateByDegrees extends Command {
	
	double deg = 0;
	double setGoal = 0;

    public RotateByDegrees(double deg) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        this.deg = deg;
        setGoal = Robot.drivetrain.getGyroAngle() + deg;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.drivetrain.getGyroAngle() > setGoal) {
    		
    	} else {
    		
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double degLeft = setGoal - Robot.drivetrain.getGyroAngle();
    	if(degLeft < 2 && degLeft > -2) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
