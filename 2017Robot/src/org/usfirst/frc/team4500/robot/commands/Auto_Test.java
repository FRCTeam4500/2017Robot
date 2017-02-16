package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;
import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_Test extends CommandGroup {

    public Auto_Test() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	requires(Robot.drivetrain);
    	requires(Robot.geargrabber);
    
    	//addSequential(new Auto_VisionAdjustGear(RobotMap.vData));
    	addSequential(new DriveTrain_ResetGyro());
    	addSequential(new DriveTrain_PIDMove(1, 6));
    	addSequential(new Wait(0.8));
    	addSequential(new GearGrabber_Grab());
    	addSequential(new Wait(0.8));
    	addSequential(new DriveTrain_PIDMove(35, 45));
    	addSequential(new GearGrabber_Letgo());
    	
    	addSequential(new DriveTrain_RotateByDegrees(180));
    }
}
