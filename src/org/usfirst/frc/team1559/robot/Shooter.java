package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.State;
import org.usfirst.frc.team1559.lib.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Talon;

public class Shooter extends Subsystem {

	private CANTalon shooterTalon; private Talon shooterFeeder;

	public Shooter() {
		super("shooter");
		shooterFeeder = new Talon(Wiring.FEEDER_TALON);
		shooterTalon = new CANTalon(Wiring.SHOOTER_TALON);
		shooterTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterTalon.configEncoderCodesPerRev(Constants.ENCODER_CODES_PER_REV);
		shooterTalon.changeControlMode(TalonControlMode.Speed);
		shooterTalon.configNominalOutputVoltage(Constants.NOMINAL_FWD_VOUT, Constants.PEAK_REV_VOUT);
		shooterTalon.configPeakOutputVoltage(Constants.PEAK_FWD_VOUT, Constants.NOMINAL_FWD_VOUT);
		shooterTalon.setProfile(Constants.PROFILE);
		shooterTalon.setP(Constants.Ps);
		shooterTalon.setI(Constants.Is);
		shooterTalon.setD(Constants.Ds);
		shooterTalon.setF(Constants.Fs);
	}
	public void shooterInit() {
		shooterTalon.enable();
	}

	public void setShooter(double shootSpeed) {
		shooterTalon.set(shootSpeed);
	}
	public void setFeeder(double feedSpeed) {
		shooterFeeder.set(feedSpeed);
	}

	public void fire(boolean fire) {
		if (fire) {
			setShooter(Constants.SHOOTER_SPEED); // TODO: Find the right speed. (Max is 720, Min is 685)
			setFeeder(Constants.FEEDER_SPEED); // TODO: Find the exact speed that works.
		} else {
			setShooter(0);
			setFeeder(0);
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
