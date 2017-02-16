package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class GearGatherer {

	private static GearGatherer instance;

	public static GearGatherer getInstance() {
		if (instance == null) {
			instance = new GearGatherer();
		}
		return instance;
	}

	DigitalInput limit; // The limit switch
	Solenoid piston; // The piston that releases the gear

	public GearGatherer() {
		limit = new DigitalInput(Wiring.GEAR_IN); // instantiation
		piston = new Solenoid(Wiring.GEAR_GATHERER);// forward
	}

	public void set(boolean b) { // Triggered is a joystick button
		piston.set(b);
	}
}
