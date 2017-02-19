package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Talon;

public class Climber {

	Talon talon;
	
	private Climber() {
		talon = new Talon(Wiring.CLIMBER_TALON);
	}

	public void climb() {
		talon.set(-0.7);
	}

	public void unclimb() {
		talon.set(0.7);
	}
	
	public void stop() {
		talon.set(0);
	}

	private static Climber instance;

	public static Climber getInstance() {
		if (instance == null) {
			instance = new Climber();
		}
		return instance;
	}
}
