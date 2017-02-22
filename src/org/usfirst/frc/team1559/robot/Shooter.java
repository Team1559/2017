package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.State;
import org.usfirst.frc.team1559.lib.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {

	private CANTalon shooter;
	private Talon feeder;

	public Shooter() {
		super("shooter");
		feeder = new Talon(Wiring.FEEDER_TALON);
		shooter = new CANTalon(Wiring.SHOOTER_TALON);
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooter.configEncoderCodesPerRev(Constants.ENCODER_CODES_PER_REV);
		shooter.configNominalOutputVoltage(Constants.NOMINAL_FWD_VOUT, Constants.NOMINAL_REV_VOUT);
		shooter.configPeakOutputVoltage(Constants.PEAK_FWD_VOUT, Constants.PEAK_REV_VOUT);
		shooter.setProfile(Constants.PROFILE);
		shooter.setP(Constants.Ps);
		shooter.setI(Constants.Is);
		shooter.setD(Constants.Ds);
		shooter.setF(Constants.Fs);
		shooter.enable();
		shooter.setInverted(true);
	}

	public void shooterInit() {
		shooter.enable();
	}

	public void setShooter(double shootSpeed) {
		shooter.set(shootSpeed);
	}

	public void setFeeder(double feedSpeed) {
		feeder.set(feedSpeed);
	}

	public CANTalon getShooter() {
		return shooter;
	}

	public void fire(boolean fire) {
		SmartDashboard.putNumber("SHOOTER: ", shooter.getEncVelocity());
		if (fire) {
			setShooter(Constants.SHOOTER_SPEED); // TODO: Find the right speed.
//			if (Math.abs(shooter.getClosedLoopError()) < Constants.SHOOTER_TOLERANCE) {
//				 setFeeder(Constants.FEEDER_SPEED); // TODO: Find the exact speed
//			}
//			else{
//				setFeeder(0);
//			}
		} else {
			setShooter(0);
			setFeeder(0);
		}
	}

	public void getState(State s) {
		s.put("shooter-velocity", shooter.getEncVelocity() * Constants.RPM_CONVERSION);
	}

	private static Shooter instance;

	public static Shooter getInstance() {
		if (instance == null) {
			instance = new Shooter();
		}
		return instance;
	}
}
