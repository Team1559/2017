package org.usfirst.frc.team1559.robot;

public class Wiring {// kristin was here :3

	// thou shan't use magical digits

	// Drive Train
	public static final int FL_SRX = 10; // The front left TalonSRX
	public static final int FR_SRX = 11; // The front right TalonSRX
	public static final int RL_SRX = 12; // The rear left TalonSRX
	public static final int RR_SRX = 13; // The rear right TalonSRX

	// Climber
	public static final int CLIMBER_TALON = 9;

	// Ball Gatherer
	public static final int BGATHERER_TALON = 0;
	public static final int BGATHERER_PISTON = 1;

	// Shooter
	public static final int SHOOTER_TALON = 9; // Will change (The port it is on)
	public static final int FEEDER_TALON = 5; // Will change (The port it is on)

	// Gear Gatherer
	public static final int GEAR_GATHERER = 2;
	public static final int GEAR_GATHERER_SERVO = 3;

	// Dropper
	public static final int DROPPER = 0; // Solenoid port for dropping the wheels/pulling them up

	// Joysticks
	public static final int JOYSTICK0 = 0;
	public static final int JOYSTICK1 = 1;
	public static final int JOYSTICK2 = 2;
	public static final int JOYSTICK3 = 3;

	// Gatherer/shooter controls
	public static final int BTN_GATHER = 5; // Button to control the gatherer
	public static final int BTN_SHOOT = 6; // Button to control the shooter &
											// feeder
	public static final int BTN_FLIP = 2;
	public static final int BTN_DROP = 9;
	public static final int BTN_GEAR = 3;
	public static final int BTN_CLIMB = 8;
	public static final int BTN_CLIMB_REV = 7;
	public static final int BTN_MOUTH = 4;
}
