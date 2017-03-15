package org.usfirst.frc.team1559.robot;

public interface Wiring {// kristin was here :3

	// thou shan't use magical digits

	// Drive Train
	final int FL_SRX = 10; // The front left TalonSRX
	final int FR_SRX = 11; // The front right TalonSRX
	final int RL_SRX = 12; // The rear left TalonSRX
	final int RR_SRX = 13; // The rear right TalonSRX

	// Climber
	final int CLIMBER_TALON = 9;

	// Ball Gatherer
	final int BGATHERER_TALON = 8;
	final int BGATHERER_PISTON = 1;

	// Shooter
	final int SHOOTER_TALON = 9; // Will change (The port it is on)
	final int FEEDER_TALON = 5; // Will change (The port it is on)

	// Gear Gatherer
	final int GEAR_GATHERER = 2;
	final int GEAR_GATHERER_SERVO = 3;

	// Dropper
	final int DROPPER = 0; // Solenoid port for dropping the wheels/pulling them up

	// Joysticks
	final int JOYSTICK0 = 0;
	final int JOYSTICK1 = 1;
	final int JOYSTICK2 = 2;
	final int JOYSTICK3 = 3;

	// Gatherer/shooter controls
	final int BTN_GATHER = 5; // Button to control the gatherer
	final int BTN_SHOOT = 6; // Button to control the shooter &
											// feeder
	final int BTN_FLIP = 2;
	final int BTN_DROP = 9;
	final int BTN_GEAR = 3;
	final int BTN_CLIMB = 8;
	final int BTN_CLIMB_JOHN = 1;
	final int BTN_CLIMB_REV = 7;
	final int BTN_MOUTH = 4;
}
