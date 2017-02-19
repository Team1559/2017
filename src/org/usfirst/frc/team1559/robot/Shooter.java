package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.State;
import org.usfirst.frc.team1559.lib.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Talon;

public class Shooter extends Subsystem {

	private static Shooter instance;

	public static Shooter getInstance() { // Gets the instance of the Shooter.
		if (instance == null) {
			instance = new Shooter();
		}
		return instance;
	}
	
	// The Shooter is a Fly Wheel Shooter.
	// This is where most of the variables are instantiated.
	CANTalon shooterTalon;
	Talon shooterFeeder; // The motor that is used to fire them balls & The motor used to feed the balls.

	public Shooter() { // Used to define the CANTalon and other variables.
		super("shooter");
		// All the variables are defined here.
		shooterTalon = new CANTalon(Wiring.SHOOTER_TALON);// Will change for the actual Robot.
		shooterFeeder = new Talon(Wiring.FEEDER_TALON);
		// Initiation for the CANTalon
		shooterTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder); // Sets the feedback device to a Quad Encoder.
		shooterTalon.configEncoderCodesPerRev(Constants.ENCODER_CODES_PER_REV); // Encoder codes per Revolution.
		shooterTalon.changeControlMode(TalonControlMode.Speed); // Sets the control mode to Speed.
		shooterTalon.configNominalOutputVoltage(Constants.NOMIAL_OUTPUT_VOLTAGE,Constants.NEGATIVE_PEAK_OUTPUT_VOLTAGE); // Sets the output voltages.
		shooterTalon.configPeakOutputVoltage(Constants.PEAK_OUTPUT_VOLTAGE, Constants.NOMIAL_OUTPUT_VOLTAGE); // Sets the peak voltage and the minimum voltage output.
		shooterTalon.setProfile(Constants.PROFILE); // Sets the profile of the CANTalon.
		shooterTalon.setP(Constants.Ps); // Ps = 0.2
		shooterTalon.setI(Constants.Is); // Is = 0
		shooterTalon.setD(Constants.Ds); // Ds = 0
		shooterTalon.setF(Constants.Fs); // Fs = 0.0422
	}

	public void setShooter(double rpm) { // Starts up the motor so that them balls can be fired.
		shooterTalon.set(rpm); // Motor is started up.
	}

	public void setFeeder(double feedSpeed) {
		shooterFeeder.set(feedSpeed);
	}

	public void fire(boolean fire) { // Used to control the fire rate of the
										// balls
		if (fire) {
			setShooter(Constants.SHOOTER_SPEED); // TODO: Set to the right speed
			setFeeder(Constants.FEEDER_SPEED); // TODO: Find the perfect speed.
		} else {
			setShooter(0); // Sets the fire rate to 0, stopping the shooter.
			setFeeder(0); // Sets the feed rate to 0, stopping it.
		}
	}

	public void getState(State s) { // The method that will set the state of s
		s.put("shooter-velocity", shooterTalon.getEncVelocity() * Constants.RPM_CONVERSION); // TODO: Fix this.
	}
}
