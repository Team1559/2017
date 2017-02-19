package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class BallGatherer {

	private static final double GATHER_SPEED = 1;
	
	private Talon belt;
	private Solenoid piston;
	private boolean gathering;

	private BallGatherer(int talonPort, int pistonPort) {
		belt = new Talon(talonPort);
		piston = new Solenoid(pistonPort);
		belt.setInverted(true);
	}

	public void gather(boolean b) {
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
			instance = new BallGatherer(Wiring.BGATHERER_TALON, Wiring.BGATHERER_PISTON);
		}
		return instance;
	}
}
