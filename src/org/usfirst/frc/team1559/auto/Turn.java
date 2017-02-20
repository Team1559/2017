package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;

public class Turn extends AutoCommand {

	double angle, speed;
	double startAngle;

	public Turn(double angle, double speed) {
		this.angle = angle;
		this.speed = speed;
//		this.startAngle = BNO055.getInstance().getZ();
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		
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
		if (angle > 0) {
//			return BNO055.getInstance().getZ() >= startAngle + angle;
		} else {
//			return BNO055.getInstance().getZ() <= startAngle + angle;
		}
		return false;
	}
}
