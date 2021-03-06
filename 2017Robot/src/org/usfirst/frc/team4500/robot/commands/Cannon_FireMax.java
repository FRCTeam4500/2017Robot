package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Cannon_FireMax extends Command {
	
	double speed = 0;

    public Cannon_FireMax(double speed) {
        // Use requires() here to declare subsystem dependencies
    	this.speed = speed;
        requires(Robot.cannon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(speed != 0) {
    		Robot.cannon.fireSpinup(-Robot.oi.getJoyScroll());	
    	} else {
    		Robot.cannon.fireSpinup(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
