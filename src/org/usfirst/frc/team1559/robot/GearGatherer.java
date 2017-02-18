package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

public class GearGatherer {

	boolean open;
	Servo mouth;
	Solenoid piston; // The piston that releases the gear

	public GearGatherer() {
		mouth = new Servo(4);
		piston = new Solenoid(Wiring.GEAR_GATHERER);// forward
	}

	public void open(boolean b) {
		mouth.set(b ? 1 : 0);
		open = b;
	}
	
	public void set(boolean b) { // Triggered is a joystick button
		piston.set(b);
	}

	public boolean isOpen() {
		return open;
	}

	private static GearGatherer instance;

	public static GearGatherer getInstance() {
		if (instance == null) {
			instance = new GearGatherer();
		}
		return instance;
	}
}
