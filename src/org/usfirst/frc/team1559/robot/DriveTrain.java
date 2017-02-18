package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.Ramp;
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

	private static final int MAX_SPEED_TRACTION = 2000;
	private static final int MAX_SPEED_MECANUM = 1000;

	public static final int FR = 0;
	public static final int RR = 1;
	public static final int RL = 2;
	public static final int FL = 3;

	private static DriveTrain instance;

	Ramp rampX, rampY, rampRot;

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	private boolean velocityControl = true;
	private boolean flipped = false;

	private CANTalon[] talons;
	private Solenoid drop; // Solenoid for shifting
	private RobotDrive drive; // The robot's drive
	private boolean mecanumized;
	double gyroAngle; // TODO: Delete

	public DriveTrain() {
		super("drive-train");
		talons = new CANTalon[4];
		talons[FR] = new CANTalon(Wiring.FR_SRX);
		talons[RR] = new CANTalon(Wiring.RR_SRX);
		talons[RL] = new CANTalon(Wiring.RL_SRX);
		talons[FL] = new CANTalon(Wiring.FL_SRX);
		drop = new Solenoid(Wiring.DROPPER);
		drive = new RobotDrive(talons[FL], talons[RL], talons[FR], talons[RR]);
		mecanumized = false;

		for (CANTalon t : talons) {
			if (velocityControl) {
				initVelocityControl(t);
			}
			t.enable();
		}

		rampX = new Ramp(3, 2);
		rampY = new Ramp(3, 2);
		rampRot = new Ramp(3, 2);

		initTraction();
		// Enables the PIDF loop
	}

	public void drop(boolean mecanumized) { // Control the versadrop
		this.mecanumized = mecanumized;
		drop.set(mecanumized); // Set the pistons to the new value
	}

	public void drive(Joystick stick, boolean ramp) {
		double xIn = Math.pow(stick.getX(), 3);
		double yIn = Math.pow(stick.getY(), 3);
		double rotIn = Math.pow(stick.getRawAxis(4), 3);

		if (ramp && !mecanumized) {
			xIn = rampX.rampMotorValue(xIn);
			yIn = rampY.rampMotorValue(yIn);
			rotIn = rampRot.rampMotorValue(rotIn);
		}
		if (flipped) {
			rotIn = -rotIn;
		}

		if (mecanumized /* && stick.getRawAxis(4) >= 0.1 */) {
			driveMecanum(xIn, yIn, rotIn);
		} else {
			driveTraction(yIn, rotIn);
		}
	}

	public void initTraction() {
		talons[FL].setInverted(false);
		talons[FR].setInverted(false);
		talons[RL].setInverted(false);
		talons[RR].setInverted(false);
		drive.setMaxOutput(MAX_SPEED_TRACTION);
	}

	public void initMecanum() {
		drive.setMaxOutput(MAX_SPEED_MECANUM);
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
		SmartDashboard.putNumber("FR Encoder", talons[FR].getEncVelocity());
		SmartDashboard.putNumber("FL Encoder", talons[FL].getEncVelocity());
		SmartDashboard.putNumber("RR Encoder", talons[RR].getEncVelocity());
		SmartDashboard.putNumber("RL Encoder", talons[RL].getEncVelocity());
		SmartDashboard.putNumber("FL Voltage", talons[FL].getOutputVoltage());
	}

	// public void driveMecanum(double x, double y, double rotation) {
	// drive.mecanumDrive_Cartesian(x, y, rotation, 0);
	// }

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

		System.out.println(gyroAngle);

		// Calculate the speeds
		double[] speeds = new double[4];
		speeds[FL] = xIn + yIn + rotation;
		speeds[FR] = -xIn + yIn - rotation;
		speeds[RL] = -xIn + yIn + rotation;
		speeds[RR] = xIn + yIn - rotation;
		normalize(speeds);

		// Set speeds
		talons[FL].set(-speeds[FL] * MAX_SPEED_MECANUM);
		talons[FR].set(speeds[FR] * MAX_SPEED_MECANUM);
		talons[RL].set(-speeds[RL] * MAX_SPEED_MECANUM);
		talons[RR].set(speeds[RR] * MAX_SPEED_MECANUM);

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

	public void resetEncoders() {
		for (int i = 0; i < talons.length; i++) {
			talons[i].setEncPosition(0);
		}
	}

	public double getAvgEncoderPos() {
		double ret = 0;
		for (int i = 0; i < talons.length; i++) {
			ret += Math.abs(talons[i].getEncPosition());
		}
		ret /= talons.length;
		return ret;
	}

	public double getEncoderPos(int wheel) {
		return Math.abs(talons[wheel].getEncPosition());
	}

	/**
	 * Sets a particular {@link CANTalon} to the specified speed.
	 * 
	 * @param wheel
	 *            The position of the wheel on the chassis. Use
	 *            {@link DriveTrain} constants, such as {@link DriveTrain#FR}.
	 * @param speed
	 *            Speed of the wheel.
	 */
	public void set(int wheel, double speed) {
		talons[wheel].set(speed);
	}

	public void flipFront() {
		flipped = !flipped;
		talons[FL].setInverted(!talons[FL].getInverted());
		talons[FR].setInverted(!talons[FR].getInverted());
		talons[RL].setInverted(!talons[RL].getInverted());
		talons[RR].setInverted(!talons[RR].getInverted());
	}
}
