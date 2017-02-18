package org.usfirst.frc.team1559.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class BallGatherer {
	
	boolean gathering;
	DoubleSolenoid piston;
	CANTalon belt;

	public BallGatherer() {
		belt = new CANTalon(Wiring.BGATHERER_TALON);
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