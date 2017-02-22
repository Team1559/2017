package org.usfirst.frc.team1559.robot;

public class Constants {
	// again, thou shan't use magical digits

	// Robit size
	public static final int ROBOT_WIDTH = 40;
	public static final int ROBOT_LENGTH = 36;
	
	//Talon stuffs
	public static final double Pd = -200000; //(This may change) The proportional value for the PIDF loop (Mecanum)
	public static final double Id = 0; //(This may change) The integral value for the PIDF loop (Mecanum)
	public static final double Dd = 0; //(This may change) The derivative  value for the PIDF loop (Mecanum)
	public static final double Fd = 0.0682; //(This may change) The feed-forward value for the PIDF loop (Mecanum)
	// Fd CHASSIS BLEU: 0.0682
	// Fd CHASSIS OR: 
	public static final double Ps = -200000; //(This may change) The proportional value for the PIDF loop (shooter)
	public static final double Is = 0; //(This may change) The integral value for the PIDF loop (shooter)
	
	public static final int ENCODER_CODES_PER_REV = 4096;// The number of times an encoder will move in one wheel revolution
	public static final float NOMINAL_FWD_VOUT = +0.0f; //The forward nomial output voltage for the talons
	public static final float NOMINAL_REV_VOUT = -0.0f; //The forward peak output voltage for the talons
	public static final float PEAK_FWD_VOUT = +12.0f; //The reverse nomial output voltage for the talons
	public static final float PEAK_REV_VOUT = -12.0f; //The reverse peak output voltage for the talons
	public static final int PROFILE = 0; //The profile for the talons
	public static final double RPM_CONVERSION = (300)/(4096); //This will change
}
