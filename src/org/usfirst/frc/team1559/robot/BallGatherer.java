package org.usfirst.frc.team1559.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class BallGatherer {

	private CANTalon belt;
	private Solenoid piston;
	private boolean gathering;

	private BallGatherer() {
		belt = new CANTalon(Wiring.BGATHERER_TALON);
		piston = new Solenoid(Wiring.BGATHERER_PISTON);
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
			instance = new BallGatherer();
		}
		return instance;
	}
}
