package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.BNO055;
import org.usfirst.frc.team1559.lib.Ramp;
import org.usfirst.frc.team1559.lib.State;
import org.usfirst.frc.team1559.lib.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

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
	private boolean rampedControls = true;
	private boolean fieldCentric = false;

	private WPI_TalonSRX[] talons;
	private Solenoid drop; // Solenoid for shifting
	private RobotDrive drive;
	private boolean mecanumized;
	private boolean flipped; // controls which side is the front

	RobotPosition currentPos;
	RobotPosition desiredPos;

	BNO055 imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER);

	private DriveTrain(boolean mecanumized) {
		super("drive-train");
		talons = new WPI_TalonSRX[4];
		talons[FR] = new WPI_TalonSRX(Wiring.FR_SRX);
		talons[RR] = new WPI_TalonSRX(Wiring.RR_SRX);
		talons[RL] = new WPI_TalonSRX(Wiring.RL_SRX);
		talons[FL] = new WPI_TalonSRX(Wiring.FL_SRX);
		drop = new Solenoid(Wiring.DROPPER);
		drive = new RobotDrive(talons[FL], talons[RL], talons[FR], talons[RR]);
		this.mecanumized = mecanumized;
		currentPos = new RobotPosition();
		desiredPos = new RobotPosition();

		if (velocityControl) {
			for (WPI_TalonSRX t : talons) {
				initVelocityControl(t);
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

	public void updatePosition() {
		currentPos.enc = getAvgEncoderPos();
		currentPos.angle = imu.getVector()[0];
	}

	public void update() {
		updatePosition();
		operatorControlled = false;
	}

	public void update(Joystick stick) {
		if (operatorControlled) {
			updatePosition();
			drive(stick);
		} else {
			update();
		}
	}

	public void driveTo(RobotPosition pos, double speed) {
		// only traction for now
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
		double xs = Math.signum(stick.getX());
		double ys = Math.signum(stick.getY());
		double xIn = Math.pow(stick.getX(), 2) * xs;
		double yIn = Math.pow(stick.getY(), 2) * ys;
		double rotIn = Math.pow(stick.getRawAxis(2), 3); // with an xbox controller the raw axis is 4

		if (rampedControls && !mecanumized) { // ramping breaks mecanum
												// apparently...
			xIn = rampX.rampMotorValue(xIn);
			yIn = rampY.rampMotorValue(yIn);
			rotIn = rampRot.rampMotorValue(rotIn);
		}

		if (flipped) {
			rotIn = -rotIn;
		}

		if (mecanumized) {
			driveMecanum(xIn, yIn, rotIn, fieldCentric);
		} else {
			driveTraction(yIn, rotIn);
		}
	}

	public void initTraction() {
		talons[FL].setInverted(false);
		talons[FR].setInverted(false);
		talons[RL].setInverted(false);
		talons[RR].setInverted(false);
		if (velocityControl) {
			drive.setMaxOutput(MAX_SPEED_TRACTION);
		}
	}

	public void initMecanum() {
		if (velocityControl) {
			drive.setMaxOutput(MAX_SPEED_MECANUM);
		}
	}

	public void driveTraction(double move, double rot) {
		drive.arcadeDrive(move, rot);
	}

	public void driveMecanum(double x, double y, double rotation, boolean fieldCentric) {

		double xIn = x;
		double yIn = y;
		// Negate y for the joystick.
		yIn = -yIn;
		// Compenstate for gyro angle.
		double[] rotated = rotateVector(xIn, yIn, fieldCentric ? currentPos.angle : 0);
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

	public void initVelocityControl(WPI_TalonSRX talon) {
		talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		talon.configClosedloopRamp(0.0, 0); // 0.08
		talon.configOpenloopRamp(0.15, 0);

		talon.configNominalOutputForward(0, 0);
		talon.configNominalOutputReverse(0, 0);
		talon.configPeakOutputForward(+1, 0);
		talon.configPeakOutputReverse(-1, 0);

		talon.setNeutralMode(NeutralMode.Coast);

		talon.configNeutralDeadband(.04, 0);

		// TODO this is new
		talon.set(ControlMode.Velocity, 0);
	}

	public void resetEncoders() {
		for (int i = 0; i < talons.length; i++) {
			talons[i].getSensorCollection().setQuadraturePosition(0, 0);
		}
	}

	public double getAvgEncoderPos() {
		return (talons[0].getSensorCollection().getQuadraturePosition()- //- for robot 1
				talons[1].getSensorCollection().getQuadraturePosition()+
				talons[2].getSensorCollection().getQuadraturePosition()-
				talons[3].getSensorCollection().getQuadraturePosition())/4.0;
	}
	

	public double getEncoderPos(int wheel) {
		return Math.abs(talons[wheel].getSensorCollection().getQuadraturePosition());
	}

	/**
	 * Sets a particular {@link WPI_TalonSRX} to the specified speed.
	 * 
	 * @param wheel
	 *            The position of the wheel on the chassis. Use {@link DriveTrain} constants, such as {@link DriveTrain#FR}.
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

	public void stopDriving() {
		set(FL, 0);
		set(FR, 0);
		set(RL, 0);
		set(RR, 0);
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
