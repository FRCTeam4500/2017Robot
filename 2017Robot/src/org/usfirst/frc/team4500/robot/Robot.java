 
package org.usfirst.frc.team4500.robot;

import java.io.IOException;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4500.robot.commands.Auto_Main;
import org.usfirst.frc.team4500.robot.subsystems.BallGrabber;
import org.usfirst.frc.team4500.robot.subsystems.Cannon;
import org.usfirst.frc.team4500.robot.subsystems.Climber;
import org.usfirst.frc.team4500.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4500.robot.subsystems.DriveTrainPID;
import org.usfirst.frc.team4500.robot.subsystems.GearGrabber;
import org.usfirst.frc.team4500.robot.subsystems.PneumaticsCompressor;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	
	public static PneumaticsCompressor compressor;
	
	//public static DriveTrainPID drivetrain;
	public static Cannon cannon;
	public static BallGrabber ballgrabber;
	public static GearGrabber geargrabber;
	public static Climber climber;
	
	//public static VisionClient visionServer;
	//public static SerialPort arduino;
	public static OI oi;

	Command mainCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		drivetrain = new DriveTrain();
		//drivetrain = new DriveTrainPID();
		cannon = new Cannon();
		ballgrabber = new BallGrabber();
		geargrabber = new GearGrabber();
		climber = new Climber();
		compressor = new PneumaticsCompressor();
		//arduino = new SerialPort(19200, SerialPort.Port.kUSB);
		
		/* Trys to enable the vision server and start a separate thread for it
		 * If it encounters an error then it will through an IOException
		 */

		/*try {
			visionServer = new VisionClient((short) 3141);
			Thread t = new Thread(visionServer);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
  
		CameraServer.getInstance().startAutomaticCapture(0);
		CameraServer.getInstance().startAutomaticCapture(1);
		/*new Thread(() -> {
			UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture(0);
			UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(1);
			cam0.setResolution(RobotMap.CV_IMAGE_WIDTH, RobotMap.CV_IMAGE_HEIGHT);
			cam1.setResolution(160, 120);
			
			CvSink cvSink = CameraServer.getInstance().getVideo(cam0);
			
			CvSource outputStream = CameraServer.getInstance().putVideo("blur", RobotMap.CV_IMAGE_WIDTH, RobotMap.CV_IMAGE_HEIGHT);
			
			Mat source = new Mat();
			Mat output = new Mat();
			
			while(!Thread.interrupted()) {
				cvSink.grabFrame(source);
				Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2RGB);
				Imgproc.line(output, new Point(RobotMap.CV_IMAGE_WIDTH/2-10, RobotMap.CV_IMAGE_HEIGHT), new Point(RobotMap.CV_IMAGE_WIDTH/2-10, 0), new Scalar(255, 0, 0), 3);
				outputStream.putFrame(output);
			}
		}).start();*/
		
		oi = new OI();
		
		autoChooser.addDefault("Default", new Auto_Main());
		mainCommand = new Auto_Main();
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
		//autonomousCommand = autoChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example) 
		if (mainCommand != null)
			mainCommand.start();
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
		if (mainCommand != null)
			mainCommand.cancel();
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
		//RobotMap.vData = visionServer.getData();
        //SmartDashboard.putNumber("Server data", visionServer.getData()); 
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
