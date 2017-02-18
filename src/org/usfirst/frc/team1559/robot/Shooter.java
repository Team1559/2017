package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.State;
import org.usfirst.frc.team1559.lib.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

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

	CANTalon shooterTalon; // The motor that is used to fire them balls.
	int velocity; // The velocity variable.
	
	public Shooter() { // Used to define the CANTalon and other variables.
		super("shooter");
		// All the variables are defined here.
		shooterTalon = new CANTalon(Wiring.SHOOTER_TALON);// Will change for the actual Robot.

		// Initiation for the CANTalon
		shooterTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder); // Sets the feedback device to a Quad Encoder.
		shooterTalon.configEncoderCodesPerRev(Constants.ENCODER_CODES_PER_REV); // Encoder codes per Revolution.
		shooterTalon.changeControlMode(TalonControlMode.Speed); // Sets the control mode to Speed.
		shooterTalon.configNominalOutputVoltage(Constants.NOMIAL_OUTPUT_VOLTAGE, Constants.NEGATIVE_PEAK_OUTPUT_VOLTAGE); // Sets the output voltages.
		shooterTalon.configPeakOutputVoltage(Constants.PEAK_OUTPUT_VOLTAGE, Constants.NOMIAL_OUTPUT_VOLTAGE); // Sets the peak voltage and the minimum voltage output.
		shooterTalon.setProfile(Constants.PROFILE); // Sets the profile of the CANTalon.
		shooterTalon.setP(Constants.Ps); // Ps = 0.3
		shooterTalon.setI(Constants.Is); // Is = 0
		shooterTalon.setD(Constants.Ds); // Ds = 0.05
		shooterTalon.setF(Constants.Fs); // Fs = 0.32
	}

	public void set(int rpm) { // Starts up the motor so that them balls can be fired.
		shooterTalon.set(rpm * Constants.RPM_CONVERSION); // Motor is started up.
	}

	public void fire(boolean fire) { // Used to control the fire rate of the balls
		if (fire) {
			set(Constants.SHOOTER_RPM); //TODO: math and dont blaze
			System.out.println(shooterTalon.getEncVelocity());
		} else {
			set(0); //Sets the fire rate to 0, stopping the shooter.
		}
	}


	public void getState(State s) { // The method that will set the state of s
		s.put("shooter-velocity", shooterTalon.getEncVelocity()*Constants.RPM_CONVERSION); // TODO: fix
	}
}
