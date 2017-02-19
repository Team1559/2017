package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

public class GearGatherer {

	private Servo mouth; // blocks balls
	private Solenoid piston; // releases gear
	private boolean open;

	private GearGatherer() {
		mouth = new Servo(4); // TODO: find correct port and add to Wiring
		piston = new Solenoid(Wiring.GEAR_GATHERER);
	}

	public void open(boolean b) {
		mouth.set(b ? 1 : 0);
		open = b;
	}
	
	public void set(boolean b) {
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
