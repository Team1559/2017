package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.State;
import org.usfirst.frc.team1559.lib.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

public class Shooter extends Subsystem {

	// The Shooter is a Fly Wheel Shooter.
	// This is where most of the variables are instantiated.

	private CANTalon shooterTalon;

	public Shooter() {
		super("shooter");
		shooterTalon = new CANTalon(Wiring.SHOOTER_TALON);
		shooterTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterTalon.configEncoderCodesPerRev(Constants.ENCODER_CODES_PER_REV);
		shooterTalon.changeControlMode(TalonControlMode.Speed);
		shooterTalon.configNominalOutputVoltage(Constants.NOMIAL_FWD_VOUT,
				Constants.PEAK_REV_VOUT);
		shooterTalon.configPeakOutputVoltage(Constants.PEAK_FWD_VOUT, Constants.NOMIAL_FWD_VOUT);
		shooterTalon.setProfile(Constants.PROFILE);
		shooterTalon.setP(Constants.Ps);
		shooterTalon.setI(Constants.Is);
		shooterTalon.setD(Constants.Ds);
		shooterTalon.setF(Constants.Fs);
	}

	public void set(double rpm) {
		shooterTalon.set(rpm);
	}

	public void fire(boolean fire) {
		if (fire) {
			set(Constants.SHOOTER_SPEED); // TODO: math and dont blaze
			// System.out.println(shooterTalon.getEncVelocity());
			// System.out.println(shooterTalon.getOutputVoltage());
			// System.out.println(shooterTalon.getOutputCurrent());
		} else {
			set(0); // stop
		}
	}

	public void getState(State s) {
		s.put("shooter-velocity", shooterTalon.getEncVelocity() * Constants.RPM_CONVERSION);
	}

	private static Shooter instance;

	public static Shooter getInstance() {
		if (instance == null) {
			instance = new Shooter();
		}
		return instance;
	}
}
