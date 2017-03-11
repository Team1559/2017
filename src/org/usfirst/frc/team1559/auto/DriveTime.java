package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;

public class DriveTime extends AutoCommand {

	int ticks;
	int timer;
	double speed;
	
	public DriveTime(double seconds, double speed) {
		this.ticks = (int) (seconds * 50);
		this.speed = speed;
	}
	
	@Override
	public void init() {
		this.timer = 0;
	}

	@Override
	public void update() {
		DriveTrain.getInstance().driveTraction(speed, 0);
		timer++;
	}

	@Override
	public void done() {
		DriveTrain.getInstance().stopDriving();
	}

	@Override
	public boolean isFinished() {
		return timer >= ticks;
	}

}
