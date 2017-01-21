package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TalonSRX;

public class DriveTrain {
	
	private static DriveTrain instance;

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	private TalonSRX fl, fr, rl, rr;
	private Solenoid drop;
	private RobotDrive drive;
	private AnalogGyro g;
	private boolean mecanumized;
	private double maxSpeed;
	double gyroAngle;

	public DriveTrain() {
		fl = new TalonSRX(Wiring.FL_SRX);
		fr = new TalonSRX(Wiring.FR_SRX);
		rl = new TalonSRX(Wiring.RL_SRX);
		rr = new TalonSRX(Wiring.RR_SRX);
		drop = new Solenoid(Wiring.DROPPER);
		maxSpeed = Constants.MAX_DRIVE_SPEED;
		g = new AnalogGyro(1); // WE WILL NOT HAVE A GYRO ON THE ROBORIO JOHN
	}

	public void drop() {
		mecanumized = !mecanumized;
		drop.set(mecanumized);
	}

	public void driveTraction(double move, double rot) {
		drive.arcadeDrive(move, rot);
	}

	public void driveMecanum(double x, double y, double rotation) {
		//
		//gAngle = g.getAngle();

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

		gyroAngle = g.getAngle(); // TODO: Replace with IMU data from TX1

		double xIn = x;
		double yIn = y;
		// Negate y for the joystick.
		yIn = -yIn;
		// Compenstate for gyro angle.
		double rotated[] = rotateVector(xIn, yIn, gyroAngle);
		xIn = rotated[0];
		yIn = rotated[1];

		double flSpeed = xIn + yIn + rotation;
		double frSpeed = -xIn + yIn - rotation;
		double rlSpeed = -xIn + yIn + rotation;
		double rrSpeed = xIn + yIn - rotation;
		double[] speeds = { flSpeed, frSpeed, rlSpeed, rrSpeed };
		normalize(speeds);

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
	
}
