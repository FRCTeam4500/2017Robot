package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateByDegrees extends Command {
	
	//double dLow = 0;
	//double dHigh = 0;
	double deg = 0;

    public RotateByDegrees(double deg) {//double dLow, double dHigh) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        //this.dLow = dLow;
        //this.dHigh = dHigh;
        this.deg = deg;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*if(deg < Robot.drivetrain.getGyroAngle()) {
    		Robot.drivetrain.lOmni.set(0.5);
    		Robot.drivetrain.rOmni.set(0.5);
    	} else {
    		Robot.drivetrain.lOmni.set(-0.5);
    		Robot.drivetrain.rOmni.set(-0.5);
    	}*/
    	Robot.drivetrain.useSonarInput = false;
    	//Robot.drivetrain.pidMove(dLow, dHigh);
    	Robot.drivetrain.moveByDeg(deg);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	/*double degLeft = Robot.drivetrain.getGyroAngle() - deg;
    	if(degLeft < 2 && degLeft > -2) {
    		return true;
    	} else {
    		return false;
    	}*/
    	return Robot.drivetrain.getError();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.useSonarInput = true;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
