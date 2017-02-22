package org.usfirst.frc.team4500.robot.commands;

import org.usfirst.frc.team4500.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Group_Fire extends CommandGroup {

    public Group_Fire(double speed, boolean fromTrigger) {
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
    	requires(Robot.cannon);
    	requires(Robot.ballgrabber);
    	//addParallel(new Cannon_FireMax(speed));
    	//addSequential(new Wait(0.5));
    	addSequential(new Cannon_Feed(speed/2));
    	addSequential(new Wait(0.5));
    	addSequential(new BallGrabber_Funnel(speed));
    	//addSequential(new BallGrabber_Grab(-speed/2));
    }
}
