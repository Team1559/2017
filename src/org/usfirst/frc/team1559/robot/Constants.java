package org.usfirst.frc.team1559.robot;

public interface Constants {
	// again, thou shan't use magical digits

	// Robit size
	final int ROBOT_WIDTH = 40;//These are in inches
	final int ROBOT_LENGTH = 36;//This one too
	
	//Talon stuffs
	final double Pd = -200000; //(This may change) The proportional value for the PIDF loop (Mecanum)
	final double Id = 0; //(This may change) The integral value for the PIDF loop (Mecanum)
	final double Dd = 0; //(This may change) The derivative  value for the PIDF loop (Mecanum)
	final double Fd = 0.0682; //(This may change) The feed-forward value for the PIDF loop (Mecanum)
	// Fd CHASSIS BLEU: 0.0682
	// Fd CHASSIS OR: 
	final double Ps = -0.9; //(This may change) The proportional value for the PIDF loop (shooter)
	final double Is = -0; //(This may change) The integral value for the PIDF loop (shooter)
	final double Ds = -0; //(This may change) The derivative  value for the PIDF loop (shooter)
	final double Fs = 0.042; //(This may change) The feed-forward value for the PIDF loop (shooter)
	final int ENCODER_CODES_PER_REV = 4096;// The number of times an encoder will move in one wheel revolution

	final float NOMINAL_FWD_VOUT = +0.0f; //The forward nomial output voltage for the talons
	final float NOMINAL_REV_VOUT = -0.0f; //The forward peak output voltage for the talons
	final float PEAK_FWD_VOUT = +12.0f; //The reverse nomial output voltage for the talons
	final float PEAK_REV_VOUT = -12.0f; //The reverse peak output voltage for the talons
	final int PROFILE = 0; //The profile for the talons
	final double SHOOTER_SPEED = 550;//The minimum to make it over the lip is 685, the maximum is 720, average of about 700.
	final double FEEDER_SPEED = 0.25; // The speed for the feeder of the balls. (Will change)
	final double RPM_CONVERSION = (300)/(4096); //This will change
	final double SHOOTER_TOLERANCE = 0.5; //needs to be found
	final int ARDUINO_ADDRESS = 4;
}
