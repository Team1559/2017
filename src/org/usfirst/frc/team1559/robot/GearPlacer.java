package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

public class GearPlacer {

	private static final double CLOSED_POSITION = 0.15;
	private static final double OPEN_POSITION = 0.45;
	private Servo mouth; // blocks balls
	private Solenoid piston; // releases gear
	private boolean open;

	private GearPlacer() {

		mouth = new Servo(Wiring.GEAR_GATHERER_SERVO); // TODO: find correct port and add to Wiring
		mouth.setBounds(2, .005, 1.5, .005, 1);
		piston = new Solenoid(Wiring.GEAR_GATHERER);
	}

	public void openMouth(boolean b) {
		System.out.println("Mouth pos: " + mouth.getPosition());
		mouth.set(b ? OPEN_POSITION : CLOSED_POSITION);
		open = b;
	}

	public void set(boolean b) {
		piston.set(b);
	}

	public boolean isMouthOpen() {
		return open;
	}
	
	public boolean isPlacerOpen() {
		return piston.get();
	}

	private static GearPlacer instance;

	public static GearPlacer getInstance() {
		if (instance == null) {
			instance = new GearPlacer();
		}
		return instance;
	}
}
