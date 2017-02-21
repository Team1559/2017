package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.Constants;
import org.usfirst.frc.team1559.robot.DriveTrain;

public class Drive extends AutoCommand {

	double distance, speed;
	double startDist;

	public Drive(double inches, double speed) {
		this.distance = inches * Constants.ENCODER_CODES_PER_REV / (4 * Math.PI);
		this.speed = speed;
	}

	@Override
	public void init() {
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
	}

	@Override
	public void update() {
		double distFromTarget = distance - (DriveTrain.getInstance().getAvgEncoderPos() - startDist);
		double kP = 0.0005;
		DriveTrain.getInstance().set(DriveTrain.FL, -speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.FR, speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RL, -speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RR, speed * kP * distFromTarget);
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
		double currentEnc = DriveTrain.getInstance().getAvgEncoderPos();
		double desiredEnc = distance + startDist;
		return Math.abs(currentEnc - desiredEnc) < 150;
	}
}
