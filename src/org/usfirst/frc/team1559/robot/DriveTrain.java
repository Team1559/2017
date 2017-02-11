package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.State;
import org.usfirst.frc.team1559.lib.Subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	public static final int FR = 0;
	public static final int RR = 1;
	public static final int RL = 2;
	public static final int FL = 3;

	private static DriveTrain instance;

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	private boolean velocityControl = true;

	private CANTalon[] talons;
	private Solenoid drop; // Solenoid for shifting
	private RobotDrive drive; // The robot's drive
	private double maxSpeed; // The max speed of the chassis
	private boolean mecanumized;
	double gyroAngle; // TODO: Delete

	public DriveTrain() {
		super("drive-train");
		talons[FR] = new CANTalon(Wiring.FR_SRX);
		talons[RR] = new CANTalon(Wiring.RR_SRX);
		talons[RL] = new CANTalon(Wiring.RL_SRX);
		talons[FL] = new CANTalon(Wiring.FL_SRX);
		drop = new Solenoid(Wiring.DROPPER);
		maxSpeed = Constants.MAX_DRIVE_SPEED;
		drive = new RobotDrive(talons[FL], talons[RL], talons[FR], talons[RR]);
		mecanumized = false;

		for (CANTalon t : talons) {
			if (velocityControl) {
				initVelocityControl(t);
			}
			t.enable();
		}

		initTraction();
		// Enables the PIDF loop
	}

	public void drop(boolean mecanumized) { // Control the versadrop
		this.mecanumized = mecanumized;
		drop.set(mecanumized); // Set the pistons to the new value
	}

	public void drive(Joystick stick) {
		if (mecanumized) {
			driveMecanum(stick.getX(), stick.getY(), stick.getRawAxis(4));
		} else {
			driveTraction(stick.getRawAxis(1), stick.getRawAxis(4));
		}
	}

	public void driveTraction(Joystick stick, Ramp rampX, Ramp rampY) {
		driveTraction(rampY.rampMotorValue(stick.getRawAxis(1)), rampX.rampMotorValue(stick.getRawAxis(4)));
	}

	public void initTraction() {
		talons[FL].setInverted(false);
		talons[FR].setInverted(false);
		talons[RL].setInverted(false);
		talons[RR].setInverted(false);
		drive.setMaxOutput(200);
	}

	public void initMecanum() {

	}

	public void driveTraction(double move, double rot) {
		drive.arcadeDrive(move, rot);
		// double leftMotorSpeed;
		// double rightMotorSpeed;
		// boolean squaredInputs = false;
		// if (squaredInputs) {
		// if (move >= 0.0) {
		// move = move * move;
		// } else {
		// move = -(move * move);
		// }
		// if (rot >= 0.0) {
		// rot = rot * rot;
		// } else {
		// rot = -(rot * rot);
		// }
		// }
		//
		// if (move > 0.0)
		//
		// {
		// if (rot > 0.0) {
		// leftMotorSpeed = move - rot;
		// rightMotorSpeed = Math.max(move, rot);
		// } else {
		// leftMotorSpeed = Math.max(move, -rot);
		// rightMotorSpeed = move + rot;
		// }
		// } else {
		// if (rot > 0.0) {
		// leftMotorSpeed = -Math.max(-move, rot);
		// rightMotorSpeed = move + rot;
		// } else {
		// leftMotorSpeed = move - rot;
		// rightMotorSpeed = -Math.max(-move, -rot);
		// }
		// }
		// fr.set(-rightMotorSpeed * 640);
		// rr.set(-rightMotorSpeed * 640);
		// fl.set(leftMotorSpeed * 640);
		// rl.set(leftMotorSpeed * 640);
		SmartDashboard.putNumber("FR Encoder", talons[FR].getEncPosition());
		SmartDashboard.putNumber("FL Encoder", talons[FL].getEncPosition());
		SmartDashboard.putNumber("RR Encoder", talons[RR].getEncPosition());
		SmartDashboard.putNumber("RL Encoder", talons[RL].getEncPosition());
	}

	public void driveMecanum(double x, double y, double rotation) { // Mecanum
																	// drive
																	// method
		//
		// gAngle = g.getAngle();

		// desiredAngle += rotation;
		//
		// if(desiredAngle > gyroAngle+1){
		// correctionAngle-=0.01;
		// } else if(desiredAngle < gyroAngle-1){
		// correctionAngle+=0.01;
		// } else {
		// correctionAngle = 0.0;
		// }
		//
		// rotation += correctionAngle;

		// gyroAngle = g.getAngle(); // TODO: Replace with IMU data from TX1

		double xIn = x;
		double yIn = y;
		// Negate y for the joystick.
		yIn = -yIn;
		// Compenstate for gyro angle.
		double rotated[] = rotateVector(xIn, yIn, gyroAngle);
		xIn = rotated[0];
		yIn = rotated[1];

		// Calculate the speeds
		double[] speeds = new double[4];
		speeds[FL] = xIn + yIn + rotation;
		speeds[FR] = -xIn + yIn - rotation;
		speeds[RL] = -xIn + yIn + rotation;
		speeds[RR] = xIn + yIn - rotation;
		normalize(speeds);

		// Set speeds
		talons[FL].set(-speeds[FL] * maxSpeed);
		talons[FR].set(speeds[FR] * maxSpeed);
		talons[RL].set(-speeds[RL] * maxSpeed);
		talons[RR].set(speeds[RR] * maxSpeed);

	}

	private static double[] rotateVector(double x, double y, double angle) {
		double cosA = Math.cos(angle * (3.14159 / 180.0));
		double sinA = Math.sin(angle * (3.14159 / 180.0));
		double out[] = new double[2];
		out[0] = x * cosA - y * sinA;
		out[1] = x * sinA + y * cosA;
		return out;
	}

	private static void normalize(double[] speeds) {
		double maxMagnitude = Math.abs(speeds[0]);
		for (int i = 1; i < speeds.length; i++) {
			double temp = Math.abs(speeds[i]);
			if (maxMagnitude < temp)
				maxMagnitude = temp;
		}
		if (maxMagnitude > 1.0) {
			for (int i = 0; i < speeds.length; i++) {
				speeds[i] = speeds[i] / maxMagnitude;
			}
		}
	}

	@Override
	public void getState(State s) {
		// TODO Auto-generated method stub

	}

	public boolean getMecanumized() {
		return mecanumized;
	}

	public void initVelocityControl(CANTalon talon) {
		talon.changeControlMode(TalonControlMode.Speed);
		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		talon.configEncoderCodesPerRev(Constants.ENCODER_CODES_PER_REV);
		talon.configNominalOutputVoltage(Constants.NOMIAL_OUTPUT_VOLTAGE, Constants.NEGATIVE_NOMIAL_OUTPUT_VOLTAGE);
		talon.configPeakOutputVoltage(Constants.PEAK_OUTPUT_VOLTAGE, Constants.NEGATIVE_PEAK_OUTPUT_VOLTAGE);
		talon.setProfile(Constants.PROFILE);
		talon.setP(Constants.Pd);
		talon.setI(Constants.Id);
		talon.setD(Constants.Dd);
		talon.setF(Constants.Fd);
		talon.setCloseLoopRampRate(6);
	}

	/**
	 * Sets a particular {@link CANTalon} to the specified speed.
	 * @param wheel The position of the wheel on the chassis. Use {@link DriveTrain} constants, such as {@link DriveTrain#FR}.
	 * @param speed Speed of the wheel.
	 */
	public void set(int wheel, double speed) {
		talons[wheel].set(speed);
	}
}
