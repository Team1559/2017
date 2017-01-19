package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TalonSRX;

public class DriveTrain {

	private static DriveTrain instance;
	
	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}
	
	private TalonSRX fl, fr, rl, rr;
	private Solenoid drop;

	public DriveTrain() {
		fl = new TalonSRX(Wiring.FL_SRX);
		fr = new TalonSRX(Wiring.FR_SRX);
		rl = new TalonSRX(Wiring.RL_SRX);
		rr = new TalonSRX(Wiring.RR_SRX);
		drop = new Solenoid(Wiring.DROPPER);
	}
	
}
