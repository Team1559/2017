package org.usfirst.frc.team1559.robot;

public class Wiring {//kristin was here :3

	// thou shan't use magical digits

	// Talon stuffs
	public static final int FL_SRX = 10; // The front left TalonSRX
	public static final int FR_SRX = 11; // The front right TalonSRX
	public static final int RL_SRX = 12; // The rear left TalonSRX
	public static final int RR_SRX = 13; // The rear right TalonSRX
	public static final int SHOOTER_TALON_PORT = 9; // (Will change) The Port
													// that the CANTalon will
													// use.

	// Gear Gatherer
	public static final int GEAR_IN = 1; // Port for the limit switch
	public static final int GEAR_GATHERER = 2;

	// Dropper
	public static final int DROPPER = 0; // Solenoid port for dropping the
											// wheels/pulling them up

	// Joysticks
	public static final int JOYSTICK0 = 0;
	public static final int JOYSTICK1 = 1;
	public static final int JOYSTICK2 = 2;
	public static final int JOYSTICK3 = 3;

	// Gatherer/shooter controls
	public static final int BTN_GATHER = 1; // Button to control the gatherer
	public static final int BTN_SHOOT = 10; // Button to control the shooter
	public static final int BTN_FLIP = 2;
	public static final int BTN_DROP = 9;
	public static final int BTN_GEAR = 3;
	
}
