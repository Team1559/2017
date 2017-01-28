package org.usfirst.frc.team1559.lib;

import edu.wpi.first.wpilibj.Joystick;

public class GlobalJoystick {
	
	private Joystick driverStick;
	private Joystick copilotStick;
	private boolean isPressed = false;
	private int button;
	private int count = 0;
	
	public GlobalJoystick(int driverPort, int copilotPort, int button) {
		driverStick = new Joystick(driverPort);
		copilotStick = new Joystick(copilotPort);
		this.button = button;
	}
	
	public Joystick getDriverStick() {
		return driverStick;
	}
	
	public Joystick getCopilotStick() {
		return copilotStick;
	}
	
	public boolean getPress(Joystick stick, int button) {
		if (stick.getRawButton(button)) {
			if (!isPressed) {
				isPressed = true;
				return true;
			}
		} else {
			isPressed = false;
		}
		return false;
	}
	
	public boolean getRelease(Joystick stick, int button) {
		if (!stick.getRawButton(button)) {
			if (!isPressed) {
				isPressed = true;
				return true;
			}
		} else {
			isPressed = false;
		}
		return false;
	}
}



















































































//dad no not the spatch
