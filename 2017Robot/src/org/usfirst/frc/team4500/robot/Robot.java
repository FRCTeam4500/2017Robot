 
package org.usfirst.frc.team4500.robot;

import java.io.IOException;

import org.usfirst.frc.team4500.robot.commands.TestCommand;
import org.usfirst.frc.team4500.robot.subsystems.BallGrabber;
import org.usfirst.frc.team4500.robot.subsystems.Cannon;
import org.usfirst.frc.team4500.robot.subsystems.Climber;
import org.usfirst.frc.team4500.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4500.robot.subsystems.GearGrabber;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import utilities.Functions;
import utilities.VisionClient;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static DriveTrain drivetrain;
	public static Cannon cannon;
	public static BallGrabber ballgrabber;
	public static GearGrabber geargrabber;
	public static Climber climber;
	
	public static VisionClient visionServer;
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drivetrain = new DriveTrain();
		cannon = new Cannon();
		ballgrabber = new BallGrabber();
		geargrabber = new GearGrabber();
		climber = new Climber();
		
		/* Trys to enable the vision server and start a separate thread for it
		 * If it encounters an error then it will through an IOException
		 */
		/*try {
			visionServer = new VisionClient((short) 1234);
			Thread t = new Thread(visionServer);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		oi = new OI();
		
		autoChooser.addDefault("Default", new TestCommand(2));
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = autoChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Robot.drivetrain.lEncoder.reset();
		Robot.drivetrain.rEncoder.reset();
		Robot.drivetrain.gyro.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("Scroll", oi.getJoyScroll());
		SmartDashboard.putNumber("JoyX", Robot.oi.getJoyX());
		SmartDashboard.putNumber("JoyY", Robot.oi.getJoyY());
		SmartDashboard.putNumber("JoyTwist", Robot.oi.getJoyTwist());
		SmartDashboard.putNumber("Gyro", Robot.drivetrain.getGyroAngle());
		SmartDashboard.putNumber("UltasonicVal", Robot.drivetrain.sonic.getRangeInches());
		/*double data = visionServer.getData();
        SmartDashboard.putNumber("Server data", data);*/
        SmartDashboard.putNumber("lEncoder.get", Robot.drivetrain.lEncoder.get());
        SmartDashboard.putNumber("lEncoder.getInches", Functions.encoderPulseToInches(Robot.drivetrain.lEncoder.get()));
        SmartDashboard.putNumber("rEncoder.get", Robot.drivetrain.rEncoder.get()); 
        SmartDashboard.putNumber("rEncoder.getInches", Functions.encoderPulseToInches(Robot.drivetrain.rEncoder.get())); 
        double ultrasonic = Robot.drivetrain.sonic.getRangeInches();
        SmartDashboard.putBoolean("ultrasonic 20-25", Functions.ultrasonicInRange(20, 25, ultrasonic));
        SmartDashboard.putBoolean("ultrasonic 10-19", Functions.ultrasonicInRange(10, 19, ultrasonic));
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
