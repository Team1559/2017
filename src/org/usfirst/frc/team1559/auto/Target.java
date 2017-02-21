package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.lib.BNO055;
import org.usfirst.frc.team1559.robot.DriveTrain;
import org.usfirst.frc.team1559.robot.Vision;

public class Target extends AutoCommand {

	double speed;
	double startAngle;
	double currentAngle;

	public Target(double speed) {
		this.speed = speed;
	}

	@Override
	public void init() {
		this.startAngle = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading();
		currentAngle = Vision.getInstance().getAngle();
	}

	@Override
	public void update() {
		currentAngle = Vision.getInstance().getAngle();
		double distFromTarget = currentAngle;
		double kP = 0.23;
		DriveTrain.getInstance().set(DriveTrain.FL, speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.FR, speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RL, speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RR, speed * kP * distFromTarget);
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
		return Math.abs(currentAngle) <= 1;
	}
}
