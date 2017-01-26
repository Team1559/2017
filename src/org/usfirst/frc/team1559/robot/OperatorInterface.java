package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.BetterJoystick;

public class OperatorInterface {

	public static final int PORT_DRIVER = 0;
	public static final int PORT_COPILOT = 1;
	
	public static final int DROP_BUTTON = 3;
	
	private BetterJoystick driverStick, copilotStick;
	
	public OperatorInterface() {
		assert PORT_DRIVER != PORT_COPILOT;
		driverStick = new BetterJoystick(PORT_DRIVER);
		copilotStick = new BetterJoystick(PORT_COPILOT);
	}
	
	public BetterJoystick getDriverStick() {
		return driverStick;
	}
	
	public BetterJoystick getCopilotStick() {
		return copilotStick;
	}
}
