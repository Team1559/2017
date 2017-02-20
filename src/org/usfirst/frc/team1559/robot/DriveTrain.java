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

public class DriveTrain extends Subsystem {

	private static final int MAX_SPEED_TRACTION = 2000;
	private static final int MAX_SPEED_MECANUM = 1000;

	public static final int FR = 0, RR = 1, RL = 2, FL = 3;

	Ramp rampX, rampY, rampRot;

	private boolean operatorControlled = true;
	private boolean velocityControl = true;
	private boolean flipped; // controls which side is the front
	private boolean rampedControls = true;

	private CANTalon[] talons;
	private Solenoid drop; // Solenoid for shifting
	private RobotDrive drive;
	private boolean mecanumized;
	
	RobotPosition currentPos;
	RobotPosition desiredPos;

	private DriveTrain(boolean mecanumized) {
		super("drive-train");
		talons = new CANTalon[4];
		talons[FR] = new CANTalon(Wiring.FR_SRX);
		talons[RR] = new CANTalon(Wiring.RR_SRX);
		talons[RL] = new CANTalon(Wiring.RL_SRX);
		talons[FL] = new CANTalon(Wiring.FL_SRX);
		drop = new Solenoid(Wiring.DROPPER);
		drive = new RobotDrive(talons[FL], talons[RL], talons[FR], talons[RR]);
		this.mecanumized = mecanumized;
		currentPos = new RobotPosition();
		desiredPos = new RobotPosition();

		if (velocityControl) {
			for (CANTalon t : talons) {
				initVelocityControl(t);
				t.enable();
			}
		}

		rampX = new Ramp(3, 2);
		rampY = new Ramp(3, 2);
		rampRot = new Ramp(3, 2);

		if (mecanumized) {
			initMecanum();
		} else {
			initTraction();
		}
		setFlipped(true);
	}

	public void drop(boolean mecanumized) { // Control the versadrop
		this.mecanumized = mecanumized;
		drop.set(mecanumized);
	}

	public void update(Joystick stick) {
		if (operatorControlled) {
			drive(stick);
		} else {
			driveTo(desiredPos);
		}
	}
	
	public void driveTo(RobotPosition pos) {
		// only traction for now
		currentPos.enc = getAvgEncoderPos();
		currentPos.angle = BNO055.getInstance().getZ();
		double dist = pos.enc - currentPos.enc;
		double dAngle = pos.angle - currentPos.angle;
		if (!mecanumized) {
			double outLeft = -dAngle + dist;
			double outRight = dAngle + dist;
			talons[FL].set(outLeft);
			talons[RL].set(outLeft);
			talons[FR].set(outRight);
			talons[RR].set(outRight);
		} else {
			
		}
	}

	public void drive(Joystick stick) {
		double xIn = Math.pow(stick.getX(), 3);
		double yIn = Math.pow(stick.getY(), 3);
		double rotIn = Math.pow(stick.getRawAxis(4), 3);

		if (rampedControls && !mecanumized) { // ramping breaks mecanum apparently...
			xIn = rampX.rampMotorValue(xIn);
			yIn = rampY.rampMotorValue(yIn);
			rotIn = rampRot.rampMotorValue(rotIn);
		}

		if (flipped) {
			rotIn = -rotIn;
		}

		if (mecanumized) {
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
	}

	public void driveMecanum(double x, double y, double rotation) {
		double angle = 0;
		// double angle = IMU.getInstance().getZ();
		double xIn = x;
		double yIn = y;
		// Negate y for the joystick.
		yIn = -yIn;
		// Compenstate for gyro angle.
		double rotated[] = rotateVector(xIn, yIn, angle);
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
		talon.configNominalOutputVoltage(Constants.NOMINAL_FWD_VOUT, Constants.NOMINAL_REV_VOUT);
		talon.configPeakOutputVoltage(Constants.PEAK_FWD_VOUT, Constants.PEAK_REV_VOUT);
		talon.setProfile(Constants.PROFILE);
		talon.setP(Constants.Pd);
		talon.setI(Constants.Id);
		talon.setD(Constants.Dd);
		talon.setF(Constants.Fd);
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

	public void setFlipped(boolean b) {
		flipped = b;
		talons[FL].setInverted(!talons[FL].getInverted());
		talons[FR].setInverted(!talons[FR].getInverted());
		talons[RL].setInverted(!talons[RL].getInverted());
		talons[RR].setInverted(!talons[RR].getInverted());
	}

	public boolean isFlipped() {
		return flipped;
	}
	
	public boolean isOperatorControlled() {
		return operatorControlled;
	}
	
	public void setOperatorControlled(boolean b) {
		operatorControlled = b;
	}

	private static DriveTrain instance;

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain(false);
		}
		return instance;
	}
}
