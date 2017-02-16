package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorInterface {
	
	private Joystick driverStick, copilotStick; //Creates BetterJoysticks
	
	public DTButton shoot, gather, drop, gear;
	
	private static OperatorInterface instance;
	public static OperatorInterface getInstance() {
		if (instance == null) {
			instance = new OperatorInterface();
		}
		return instance;
	}
	
	public OperatorInterface() {
		assert Wiring.JOYSTICK0 != Wiring.JOYSTICK1;
		driverStick = new Joystick(Wiring.JOYSTICK0); //The drivers joystick
		copilotStick = new Joystick(Wiring.JOYSTICK1); //The copilots joystick
		shoot = new DTButton(driverStick, Wiring.BTN_SHOOT);
		gather = new DTButton(driverStick, Wiring.BTN_GATHER);
		drop = new DTButton(driverStick, Wiring.BTN_DROP);
		gear = new DTButton(driverStick, Wiring.BTN_GEAR);
	}
	
	public Joystick getDriverStick() { //Getter method to get the drivers joystick
		return driverStick;
	}
	
	public Joystick getCopilotStick() { //Getter method to get the copilots joystick
		return copilotStick;
	}
	
	public void updateButtons() {
		shoot.update();
		gather.update();
		drop.update();
	}
}
