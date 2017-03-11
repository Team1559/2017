package org.usfirst.frc.team1559.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorInterface {

	private Joystick driverStick, copilotStick;

	public List<DTButton> buttons;
	public DTButton shoot, gather, drop, gear, flip, climb, unclimb, openUp, climb2;

	private static OperatorInterface instance;

	public static OperatorInterface getInstance() {
		if (instance == null) {
			instance = new OperatorInterface();
		}
		return instance;
	}

	public OperatorInterface() {
		assert Wiring.JOYSTICK0 != Wiring.JOYSTICK1;
		driverStick = new Joystick(Wiring.JOYSTICK0); // The drivers joystick
		copilotStick = new Joystick(Wiring.JOYSTICK1); // The copilots joystick
		buttons = new ArrayList<DTButton>();
		shoot = new DTButton(driverStick, Wiring.BTN_SHOOT);
		gather = new DTButton(driverStick, Wiring.BTN_GATHER);
		drop = new DTButton(driverStick, Wiring.BTN_DROP);
		gear = new DTButton(driverStick, Wiring.BTN_GEAR);
		flip = new DTButton(driverStick, Wiring.BTN_FLIP);
		climb = new DTButton(driverStick, Wiring.BTN_CLIMB);
		climb2 = new DTButton(copilotStick, Wiring.BTN_CLIMB_JOHN);
		unclimb = new DTButton(driverStick, Wiring.BTN_CLIMB_REV);
		openUp = new DTButton(driverStick, Wiring.BTN_MOUTH);
	}

	public Joystick getDriverStick() {
		return driverStick;
	}

	public Joystick getCopilotStick() {
		return copilotStick;
	}

	public void updateButtons() {
		shoot.update();
		gather.update();
		drop.update();
		gear.update();
		flip.update();
		climb.update();
		unclimb.update();
		openUp.update();
		climb2.update();
	}
}
