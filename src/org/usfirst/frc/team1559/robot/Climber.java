package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Talon;

public class Climber {

	Talon talon;
	
	private Climber(int port) {
		talon = new Talon(port);
	}

	public void climb() {
		talon.set(-0.85);
	}

	public void unclimb() {
		talon.set(0.85);
	}
	
	public void stop() {
		talon.set(0);
	}

	private static Climber instance;

	public static Climber getInstance() {
		if (instance == null) {
			instance = new Climber(Wiring.CLIMBER_TALON);
		}
		return instance;
	}
}
