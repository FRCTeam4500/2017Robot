package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Cannon_Feed extends Command {
	
	double speed = 0;
	boolean fromTrigger;

    public Cannon_Feed(double speed, boolean fromTrigger) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.cannon);
        this.speed = speed;
        this.fromTrigger = fromTrigger;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.cannon.feederStartup(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return fromTrigger;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
