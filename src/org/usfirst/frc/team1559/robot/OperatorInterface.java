package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.BetterJoystick;

public class OperatorInterface {
	
	private BetterJoystick driverStick, copilotStick; //Creates BetterJoysticks
	
	private static OperatorInterface instance;
	public static OperatorInterface getInstance() {
		if (instance == null) {
			instance = new OperatorInterface();
		}
		return instance;
	}
	
	public OperatorInterface() {
		assert Wiring.JOYSTICK0 != Wiring.JOYSTICK1;
		driverStick = new BetterJoystick(Wiring.JOYSTICK0); //The drivers joystick
		copilotStick = new BetterJoystick(Wiring.JOYSTICK1); //The copilots joystick
	}
	
	public BetterJoystick getDriverStick() { //Getter method to get the drivers joystick
		return driverStick;
	}
	
	public BetterJoystick getCopilotStick() { //Getter method to get the copilots joystick
		return copilotStick;
	}
	
	public void update() {
		driverStick.update();
		copilotStick.update();
	}
}
