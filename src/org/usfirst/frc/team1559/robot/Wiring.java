package org.usfirst.frc.team1559.robot;

public class Wiring {
	
	// thou shan't use magical digits
	
	//Talon stuffs
<<<<<<< HEAD
	public static final int FL_SRX = 1; //The front left TalonSRX
	public static final int FR_SRX = 2; //The front right TalonSRX
	public static final int RL_SRX = 3; //The rear left TalonSRX
	public static final int RR_SRX = 4; //The rear right TalonSRX
	public static final int SHOOTER_TALON_PORT = 1; //The TalonSRX for shooting
=======
	public static final int FL_SRX = 1;
	public static final int FR_SRX = 2;
	public static final int RL_SRX = 3;
	public static final int RR_SRX = 4;
	public static final int SHOOTER_TALON_PORT = 1;
	public static final int SHOOTER_TALON_PROFILE = 0;
>>>>>>> origin/master
	
	//Gear Gatherer
	public static final int GEAR_IN = 1; //Port for the limit switch
	public static final int SOLENOID_UP = 1; //Solenoid port for pulling the door up
	public static final int SOLENOID_DOWN = 2; //Solenoid port for pushing the door down
	
	//Dropper
	public static final int DROPPER = 1; //Solenoid port for dropping the wheels/pulling them up
	
	//Joysticks
	public static final int JOYSTICK0 = 0;
	public static final int JOYSTICK1 = 1;
	public static final int JOYSTICK2 = 2;
	public static final int JOYSTICK3 = 3;
	
	//Gatherer/shooter controls
	public static final int BTN_GATHER = 1; //Button to control the gatherer
	public static final int BTN_SHOOT = 2; //Button to control the shooter
	public static final int SHOOTER_BALL_OPENER_PORT = 1; //
}
