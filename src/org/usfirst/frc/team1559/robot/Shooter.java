package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.State;
import org.usfirst.frc.team1559.lib.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Servo;

public class Shooter extends Subsystem {

	private static Shooter instance;
	public static Shooter getInstance() {
		if (instance == null) {
			instance = new Shooter();
		}
		return instance;
	}
	
	// The Shooter is a Fly Wheel Shooter.
	// This is where most of the variables are instantiated.

	Servo ballOpener; // The instantiation of the servo. Acts as the gate.
	CANTalon shooterTalon; // The motor that is used to fire them balls.
	int i; // Used as a counter.
	int switchCaseVar; // Used with the switch case to initiate a delay in the fire rate.
	int velocity;
	
	public Shooter() {
		super("shooter");
		
		// All the variables are defined here.
		ballOpener = new Servo(Wiring.SHOOTER_BALL_OPENER_PORT); // Will change for actual Robot.
		shooterTalon = new CANTalon(Wiring.SHOOTER_TALON_PORT);// Will change for the actual Robot.
		i = 0; // To be used with the for loop to limit the ball rate.
		switchCaseVar = 0;

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

	public void fire() { // Used to control the fire rate of the balls
		if (OperatorInterface.getInstance().getDriverStick().getStick().getRawButton(Wiring.BTN_SHOOT)) {
			set(420); //TODO: math and dont blaze
		} else {
			set(0);
		}
	}

	public void clearCounter() { // Used to reset the counter 'i'.

		i = 0; // counter 'i' is reset

	}

	public void getState(State s) { // Something, I'm sure.
		s.put("shooter-velocity", 420); // TODO: fix
	}
}
