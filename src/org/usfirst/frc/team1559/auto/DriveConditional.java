package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;

public class DriveConditional extends AutoCommand {

	Condition condition;
	double speed;

	public DriveConditional(Condition condition, double speed) {
		this.condition = condition;
		this.speed = speed;
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		DriveTrain.getInstance().set(DriveTrain.FL, -speed);
		DriveTrain.getInstance().set(DriveTrain.FR, speed);
		DriveTrain.getInstance().set(DriveTrain.RL, -speed);
		DriveTrain.getInstance().set(DriveTrain.RR, speed);
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
		return condition.check();
	}
}
