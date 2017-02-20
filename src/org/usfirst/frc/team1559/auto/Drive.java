package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;

public class Drive extends AutoCommand {

	double distance, speed;
	double startDist;

	public Drive(double distance, double speed) {
		this.distance = distance;
		this.speed = speed;
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
	}

	@Override
	public void init() {
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
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
		// DriveTrain.getInstance().resetEncoders();
	}

	@Override
	public boolean isFinished() {
		System.out.println("" + (distance + startDist + " : " + DriveTrain.getInstance().getAvgEncoderPos()));
		return DriveTrain.getInstance().getAvgEncoderPos() >= ((distance * 0.95) + startDist);
	}
}
