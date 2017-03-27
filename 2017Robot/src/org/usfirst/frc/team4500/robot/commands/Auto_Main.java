package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;
import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_Main extends CommandGroup {

    public Auto_Main() {
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
    	requires(Robot.cannon);
    	
    	//addSequential(new Auto_VisionAdjustGear(RobotMap.vData));
    	
    	// ^
    	addSequential(new DriveTrain_ResetGyro());
    	//addSequential(new DriveTrain_PIDMove(3, 4));
    	addSequential(new Auto_DriveWithGyro());
    	addSequential(new Wait(0.8));
    	
    	// Drop gear
    	addSequential(new Group_Pickup_Drop());
    	addSequential(new Wait(0.2));
    	addSequential(new Pickup_MoveBySpeed(0));
    	addSequential(new Wait(0.8));
    	
    	//addSequential(new DriveTrain_PIDMove(40, 42));
    	//addSequential(new Wait(0.5));
    	//addSequential(new Group_Fire(1, false));
    	
    	// V
    	addParallel(new DriveTrain_AutoMoveDelay(0, 0.6, 0));
    	addSequential(new Wait(0.6)); // 1
    	
    	//Lift the gear grabber back up and stop
    	addSequential(new Pickup_Retract());
    	addSequential(new DriveTrain_AutoMoveDelay(0, 0, 0));
    	addSequential(new Wait(2));
    	
    	/*// ^
    	addSequential(new Auto_DriveWithGyro());
    	
    	// Move the cannon right
    	addSequential(new Cannon_MoveRight(0.5));
    	addSequential(new Wait(1));
    	addSequential(new Cannon_MoveRight(0));*/
    	
    	// Fire the cannon
    	//addSequential(new Group_Fire(1, false));
    	
    	// ->
    	/*SaddParallel(new DriveTrain_AutoMoveDelay(0.8, 0, 0));
    	addSequential(new Wait(2.5)); // 1
    	// ^
    	addParallel(new DriveTrain_AutoMoveDelay(0, -1, 0));
    	addSequential(new Wait(5)); // 2
    	addSequential(new Pickup_Retract());
    	addSequential(new DriveTrain_AutoMoveDelay(0, 0, 0));*/
    	
    	
    	
    	
    	//addSequential(new DriveTrain_RotateByDegrees(180));
    }
}


/*
		requires(Robot.drivetrain);
    	requires(Robot.geargrabber);
    	requires(Robot.cannon);
    	
    	//addSequential(new Auto_VisionAdjustGear(RobotMap.vData));
    	
    	// ^
    	addSequential(new DriveTrain_ResetGyro());
    	//addSequential(new DriveTrain_PIDMove(3, 4));
    	addSequential(new Auto_DriveWithGyro());
    	addSequential(new Wait(0.8));
    	
    	// Drop gear
    	addSequential(new Group_Pickup_Drop());
    	addSequential(new Wait(0.2));
    	addSequential(new Pickup_MoveBySpeed(0));
    	addSequential(new Wait(0.8));
    	
    	//addSequential(new DriveTrain_PIDMove(40, 42));
    	//addSequential(new Wait(0.5));
    	//addSequential(new Group_Fire(1, false));
    	
    	// V
    	addParallel(new DriveTrain_AutoMoveDelay(0, 0.6, 0));
    	addSequential(new Wait(1)); // 1
    	
    	//Lift the gear grabber back up and stop
    	addSequential(new Pickup_Retract());
    	addSequential(new DriveTrain_AutoMoveDelay(0, 0, 0));
    	addSequential(new Wait(2));
    	
    	// ^
    	addSequential(new Auto_DriveWithGyro());
    	
    	// Move the cannon right
    	addSequential(new Cannon_MoveRight(0.5));
    	addSequential(new Wait(1));
    	addSequential(new Cannon_MoveRight(0));
    	
    	// Fire the cannon
    	//addSequential(new Group_Fire(1, false));
    	
    	// ->
    	/*SaddParallel(new DriveTrain_AutoMoveDelay(0.8, 0, 0));
    	addSequential(new Wait(2.5)); // 1
    	// ^
    	addParallel(new DriveTrain_AutoMoveDelay(0, -1, 0));
    	addSequential(new Wait(5)); // 2
    	addSequential(new Pickup_Retract());
    	addSequential(new DriveTrain_AutoMoveDelay(0, 0, 0));*/
    	
    	
    	
    	
    	//addSequential(new DriveTrain_RotateByDegrees(180));



