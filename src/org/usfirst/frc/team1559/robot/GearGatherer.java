package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

public class GearGatherer {

	private static final double CLOSED_POSITION = 0.25;
	private static final double OPEN_POSITION = 0.85;
	
	
	private Servo mouth; // blocks balls
	private Solenoid piston; // releases gear
	private boolean open;

	private GearGatherer() {
		
		mouth = new Servo(Wiring.GEAR_GATHERER_SERVO); // TODO: find correct port and add to Wiring
		mouth.setBounds(2, .005, 1.5, .005, 1);
		piston = new Solenoid(Wiring.GEAR_GATHERER);
	}

	public void open(boolean b) {
		mouth.set(b ? OPEN_POSITION : CLOSED_POSITION);
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
