package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;
import org.usfirst.frc.team1559.robot.Vision;

public class TurnToVision extends AutoCommand {

	private static final int TOLERANCE = 1;
	private double errAngle;
	
	@Override
	public void init() {
		errAngle = Vision.getInstance().getAngle();
	}

	@Override
	public void update() {
		errAngle = Vision.getInstance().getAngle();
		double kP = 8.5;
		DriveTrain.getInstance().set(DriveTrain.FL, kP * errAngle);
		DriveTrain.getInstance().set(DriveTrain.FR, kP * errAngle);
		DriveTrain.getInstance().set(DriveTrain.RL, kP * errAngle);
		DriveTrain.getInstance().set(DriveTrain.RR, kP * errAngle);
	}

	@Override
	public void done() {
		DriveTrain.getInstance().stopDriving();
	}

	@Override
	public boolean isFinished() {
		return Math.abs(errAngle) < TOLERANCE;
	}

}
