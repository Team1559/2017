package org.usfirst.frc.team1559.robot;

public class Constants {
	// again, thou shan't use magical digits

	// Drive Train
	public static final double MAX_DRIVE_SPEED = .685; //The maximum speed the mecanum wheels can go
	public static final int DROP_BUTTON = 3; //Button that drops/picks up the mecanum wheels
	public static final int PRIMARY_BUFFER_LENGTH = 8;
	public static final int SECONDARY_BUFFER_LENGTH = 4;
	
	//Talon stuffs
	public static final double Pd = -200000; //(This may change) The proportional value for the PIDF loop (Mecanum)
	public static final double Id = 0; //(This may change) The integral value for the PIDF loop (Mecanum)
	public static final double Dd = 0; //(This may change) The derivative  value for the PIDF loop (Mecanum)
	public static final double Fd = 0.0628; //(This may change) The feed-forward value for the PIDF loop (Mecanum)
	// Fd CHASSIS BLEU: 0.0682
	// Fd CHASSIS OR: 
	public static final double Ps = 0.3; //(This may change) The proportional value for the PIDF loop (shooter)
	public static final double Is = 0; //(This may change) The integral value for the PIDF loop (shooter)
	public static final double Ds = 0.0; //(This may change) The derivative  value for the PIDF loop (shooter)
	public static final double Fs = 0.32; //(This may change) The feed-forward value for the PIDF loop (shooter)
	
	public static final int ENCODER_CODES_PER_REV = 4096;// The number of times an encoder will move in one wheel revolution
	
	public static final float NOMIAL_OUTPUT_VOLTAGE = +0.0f; //The forward nomial output voltage for the talons
	public static final float PEAK_OUTPUT_VOLTAGE = +12.0f; //The reverse nomial output voltage for the talons
	public static final float NEGATIVE_NOMIAL_OUTPUT_VOLTAGE = -0.0f; //The forward peak output voltage for the talons
	public static final float NEGATIVE_PEAK_OUTPUT_VOLTAGE = -12.0f; //The reverse peak output voltage for the talons
	public static final int PROFILE = 0; //The profile for the talons
	public static final int SHOOTER_TALON_PROFILE = 0; //The TalonSRX profile for shooting
	public static final int SHOOTER_RPM = 420;//Need to check, also could change based on distance
	public static final double RPM_CONVERSION = (300)/(4096); //This will change
	public static final double FIRE_DELAY = 1.0/3;
	public static final double CLOSE_DELAY = 1.0/8;
	public static final int OPEN_VAL = 90;
	public static final int CLOSE_VAL = 0;
	
	//Shifting
	public static final double SHIFT_UP_SPEED = 4.6; // m/s
	public static final double SHIFT_DOWN_SPEED = 4.4; // m/s
	public static final double LOW_SPEED_MULTIPLIER = 1.0 ; //Recommended 2.2, According to Cody...

	//Driver Controls
	public static final int SHIFTER = 4;
	public static final double SHOOTER_TOLERANCE = 0.2;
	public static final int SHOOTER_AXIS = 3;
	public static final int GEAR_GATHERER = 1;
	public static final int BALL_GATHERER = 6;
	public static final int CLIMBER = 2;
}
