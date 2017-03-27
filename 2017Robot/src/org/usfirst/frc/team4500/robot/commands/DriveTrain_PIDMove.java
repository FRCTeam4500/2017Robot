package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrain_PIDMove extends Command {
	
	double dLow = 0;
	double dHigh = 0;

    public DriveTrain_PIDMove(double dLow, double dHigh) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        this.dLow = dLow;
        this.dHigh = dHigh;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drivetrain.pidMove(dLow, dHigh);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Robot.drivetrain.getError();
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
