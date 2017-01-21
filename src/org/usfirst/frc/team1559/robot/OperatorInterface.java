package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.BetterJoystick;

public class OperatorInterface {

	private static final int PORT_DRIVER = 0;
	private static final int PORT_COPILOT = 1;
	
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
