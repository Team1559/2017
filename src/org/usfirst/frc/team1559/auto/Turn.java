package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.lib.BNO055;
import org.usfirst.frc.team1559.robot.DriveTrain;

/**
 * This command rotates the robot until it is within {@link Turn#TOLERANCE} of the given angle using the {@link BNO055}.
 */
public class Turn extends AutoCommand {

	private static final double TOLERANCE = 2.0;

	private double angle;
	private double startAngle;
	private boolean absolute;

	public Turn(double angle) {
		this(angle, false);
	}

	/**
	 * Constructor.
	 * 
	 * @param angle
	 *            The angle (in degrees) for the robot to turn by.
	 */
	public Turn(double angle, boolean absolute) {
		this.angle = angle;
		this.absolute = absolute;
	}

	@Override
	public void init() {
		if (absolute) {
			this.startAngle = 0;
		} else {
			this.startAngle = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading();
		}
	}

	@Override
	public void update() {
		double currentAngle = 0;
		if (absolute) {
			currentAngle = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getVector()[0];
			if (currentAngle >= 180) {
				currentAngle -= 360;
			}
		} else {
			currentAngle = (BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading() - startAngle);
		}
		double distFromTarget = angle - currentAngle;
		double kP = 9;
		DriveTrain.getInstance().set(DriveTrain.FL, kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.FR, kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RL, kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RR, kP * distFromTarget);
	}

	@Override
	public void done() {
		DriveTrain.getInstance().set(DriveTrain.FL, 0);
		DriveTrain.getInstance().set(DriveTrain.FR, 0);
		DriveTrain.getInstance().set(DriveTrain.RL, 0);
		DriveTrain.getInstance().set(DriveTrain.RR, 0);
	}

	@Override
	public boolean isFinished() {
		double currentAngle = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading();
		double desiredAngle = startAngle + angle;
		return Math.abs(currentAngle - desiredAngle) <= TOLERANCE;
	}
}
