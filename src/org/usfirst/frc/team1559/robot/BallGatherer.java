package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;

public class BallGatherer {

	private static final double GATHER_SPEED = 0.7;

	private Servo belt;
	private Solenoid piston;
	private boolean gathering;

	private BallGatherer(int talonPort, int pistonPort) {
		belt = new Servo(talonPort);
		piston = new Solenoid(pistonPort);
//		belt.setInverted(true);
	}

	public void gather(boolean b) {
		System.out.println("gather " + b);
		if (b) {
			gathering = true;
			belt.set(GATHER_SPEED);
		} else {
			gathering = false;
			belt.set(0);
		}
	}

	public void setPiston(boolean b) {
		piston.set(b);
	}

	public boolean isGathering() {
		return gathering;
	}

	private static BallGatherer instance;

	public static BallGatherer getInstance() {
		if (instance == null) {
			instance = new BallGatherer(Wiring.BGATHERER_SERVO, Wiring.BGATHERER_PISTON);
		}
		return instance;
	}
}
