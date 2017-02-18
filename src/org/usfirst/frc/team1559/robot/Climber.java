package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Talon;

public class Climber {

	Talon spin; // motor that will spin the climber bar

	public Climber() {
		spin = new Talon(Wiring.CLIMBER_TALON);
	}

	public void climb() {
		spin.set(-0.7);
	}

	public void unclimb() {
		spin.set(0.7);
	}
	
	public void stop() {
		spin.set(0);
	}

	private static Climber instance;

	public static Climber getInstance() {
		if (instance == null) {
			instance = new Climber();
		}
		return instance;
	}
}
