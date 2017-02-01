package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsCompressor extends Subsystem {

	Compressor compress;
	
	public PneumaticsCompressor() {
		compress = new Compressor(RobotMap.COMPRESSOR);
		startCompressor();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void startCompressor() {
    	compress.start();
    }
}

