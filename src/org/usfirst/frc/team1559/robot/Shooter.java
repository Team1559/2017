package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.JoystickButtonEvent;
import org.usfirst.frc.team1559.lib.JoystickButtonListener;
import org.usfirst.frc.team1559.lib.State;
import org.usfirst.frc.team1559.lib.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Servo;

/**
 * Fly wheel shooter with servo gate.
 * @author God
 *
 */
public class Shooter extends Subsystem implements JoystickButtonListener {

	private static Shooter instance;

	public static Shooter getInstance() {
		if (instance == null) {
			instance = new Shooter();
		}
		return instance;
	}

	Servo ballOpener; // The instantiation of the servo. Acts as the gate.
	CANTalon shooterTalon; // The motor that is used to fire them balls.

	public Shooter() {
		super("shooter");

		// All the variables are defined here.
		ballOpener = new Servo(Wiring.SHOOTER_BALL_OPENER_PORT);
		shooterTalon = new CANTalon(Wiring.SHOOTER_TALON_PORT);

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
	
	public void fire(boolean b) {
		if (b) {
			set(420); // TODO: math, and not blazing it
			ballOpener.setAngle(Constants.SHOOTER_OPEN);
		} else {
			set(0);
			ballOpener.setAngle(Constants.SHOOTER_CLOSE);
		}
	}

	public void set(int rpm) { // Starts up the motor so that them balls can be
								// fired.
		shooterTalon.set(rpm * Constants.RPM_CONVERSION); // Motor is started
															// up.
	}

	public void getState(State s) { // Something, I'm sure.

	}

	@Override
	public void buttonPressed(JoystickButtonEvent e) {
		if (e.getID() == Wiring.BTN_SHOOT) {
			fire(true);
		}
	}

	@Override
	public void buttonReleased(JoystickButtonEvent e) {
		fire(false);
	}
}
