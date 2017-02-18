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
		shooterTalon = new CANTalon(Wiring.SHOOTER_TALON_PORT);// Will change for the actual Robot.
		shooterTalon.setInverted(true);

		// Initiation for the CANTalon
		shooterTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder); // Sets the feedback device to a Quad Encoder.
		shooterTalon.configEncoderCodesPerRev(Constants.ENCODER_CODES_PER_REV_SHOOTER); // Encoder codes per Revolution.
		shooterTalon.reverseSensor(true);
		shooterTalon.changeControlMode(TalonControlMode.Speed); // Sets the control mode to Speed.
		//shooterTalon.changeControlMode(TalonControlMode.PercentVbus);
		shooterTalon.configNominalOutputVoltage(Constants.NOMIAL_OUTPUT_VOLTAGE, Constants.NEGATIVE_NOMIAL_OUTPUT_VOLTAGE); // Sets the output voltages.
		shooterTalon.configPeakOutputVoltage(Constants.PEAK_OUTPUT_VOLTAGE, Constants.NEGATIVE_PEAK_OUTPUT_VOLTAGE); // Sets the peak voltage and the minimum voltage output.
		shooterTalon.setProfile(Constants.PROFILE); // Sets the profile of the CANTalon.
		shooterTalon.setP(Constants.Ps); // Ps = 0.2
		shooterTalon.setI(Constants.Is); // Is = 0
		shooterTalon.setD(Constants.Ds); // Ds = 0
		shooterTalon.setF(Constants.Fs); // Fs = 0.0422
		
	}

	public void set(int rpm) { // Starts up the motor so that them balls can be fired.
		shooterTalon.set(rpm); // Motor is started up.
		//shooterTalon.set(rpm*0.1);
	}

	public void fire(boolean fire) { // Used to control the fire rate of the balls
		if (fire) {
			//set(Constants.SHOOTER_RPM*0.01); //TODO: math and don't blaze
			set(Constants.SHOOTER_RPM); //RPM is 4x this amount.
			System.out.println(shooterTalon.getEncVelocity());
		} else {
			set(0); //Sets the fire rate to 0, stopping the shooter.
		}
	}


	public void getState(State s) { // The method that will set the state of s
		s.put("shooter-velocity", shooterTalon.getEncVelocity()*Constants.RPM_CONVERSION); // TODO: fix
	}
}
