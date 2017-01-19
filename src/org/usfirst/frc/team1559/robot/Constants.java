package org.usfirst.frc.team1559.robot;

public class Constants {
	// again, thou shan't use magical digits
	
	//Talon stuffs
	public static final double P = 0.3;
	public static final double I = 0;
	public static final double D = 0.05;
	public static final double F = 0.32;
	public static final float NOMIAL_OUTPUT_VOLTAGE = +0.0f;
	public static final float PEAK_OUTPUT_VOLTAGE = +12.0f;
	public static final float NEGATIVE_NOMIAL_OUTPUT_VOLTAGE = -0.0f;
	public static final float NEGATIVE_PEAK_OUTPUT_VOLTAGE = -0.0f;
	public static final double RPM_CONVERSION = (300)/(4096*.1029);
	
	//Shifting
	public static final double SHIFT_UP_SPEED = 4.6; // m/s
	public static final double SHIFT_DOWN_SPEED = 4.4; // m/s
	public static final double LOW_SPEED_MULTIPLIER = 1.0 ; //Reccommended 2.2, Chris didn't like it....
}
