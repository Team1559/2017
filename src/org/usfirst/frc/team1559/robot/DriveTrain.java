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

	private static DriveTrain instance;

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	private CANTalon fl, fr, rl, rr; // The CANTalons for driving
	private Solenoid drop; // Solenoid for shifting
	private RobotDrive drive; // The robot's drive
	private double maxSpeed; // The max speed of the chassis
	private boolean mecanumized; // Bool to check if the chassis is in mecanum
	double gyroAngle; // TODO: Delete

	public DriveTrain() {
		super("drive-train");
		fl = new CANTalon(Wiring.FL_SRX); // Front left talon
		fr = new CANTalon(Wiring.FR_SRX); // Front right talon
		rl = new CANTalon(Wiring.RL_SRX); // Rear left talon
		rr = new CANTalon(Wiring.RR_SRX); // Rear right talon
		drop = new Solenoid(Wiring.DROPPER); // The solenoid that drops the
												// mecanum wheels or brings them
												// up
		maxSpeed = Constants.MAX_DRIVE_SPEED;
		drive = new RobotDrive(fl, rl, fr, rr);
		mecanumized = false;

		initVelocityControl(fl);
		initVelocityControl(fr);
		initVelocityControl(rl);
		initVelocityControl(rr);

		initTraction();
		// Enables the PIDF loop
		fl.enable();
		fr.enable();
		rl.enable();
		rr.enable();
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
		fl.setInverted(false);
		fr.setInverted(false);
		rl.setInverted(false);
		rr.setInverted(false);
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
		SmartDashboard.putNumber("FR Encoder", fr.getEncPosition());
		SmartDashboard.putNumber("FL Encoder", fl.getEncPosition());
		SmartDashboard.putNumber("RR Encoder", rr.getEncPosition());
		SmartDashboard.putNumber("RL Encoder", rl.getEncPosition());
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
		double flSpeed = xIn + yIn + rotation;
		double frSpeed = -xIn + yIn - rotation;
		double rlSpeed = -xIn + yIn + rotation;
		double rrSpeed = xIn + yIn - rotation;
		double[] speeds = { flSpeed, frSpeed, rlSpeed, rrSpeed };
		normalize(speeds);

		// Set speeds
		fl.set(-speeds[0] * maxSpeed);
		fr.set(speeds[1] * maxSpeed);
		rl.set(-speeds[2] * maxSpeed);
		rr.set(speeds[3] * maxSpeed);

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

	public void set(int port, double speed) {
		if (fr.getDeviceID() == port) {
			fr.set(speed);
		} else if (fl.getDeviceID() == port) {
			fl.set(speed);
		} else if (rr.getDeviceID() == port) {
			rr.set(speed);
		} else if (rl.getDeviceID() == port) {
			rl.set(speed);
		}
	}

	public CANTalon getCANTalon(int port) {
		if (fr.getDeviceID() == port) {
			return fr;
		} else if (fl.getDeviceID() == port) {
			return fl;
		} else if (rr.getDeviceID() == port) {
			return rr;
		} else {
			return rl;
		}
	}
}
