package org.usfirst.frc.team1559.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class BallGatherer {

	private CANTalon belt;
	private Solenoid piston;
	private boolean gathering;

	private BallGatherer(int talonPort, int pistonPort) {
		belt = new CANTalon(talonPort);
		piston = new Solenoid(pistonPort);
	}

	public void gather(boolean b) {
		if (b) {
			gathering = true;
			belt.set(0.7);
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
