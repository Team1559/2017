package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.lib.BNO055;
import org.usfirst.frc.team1559.robot.DriveTrain;

public class Turn extends AutoCommand {

	double angle;
	double startAngle;

	public Turn(double angle) {
		this.angle = angle;
	}

	@Override
	public void init() {
		this.startAngle = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading();
	}

	@Override
	public void update() {
		double distFromTarget = angle - (BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading() - startAngle);
		double kP = 8.5;
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
		return Math.abs(currentAngle - desiredAngle) <= 2;
	}
}
