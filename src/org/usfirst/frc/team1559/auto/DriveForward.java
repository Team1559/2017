package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.Constants;
import org.usfirst.frc.team1559.robot.DriveTrain;

public class DriveForward extends AutoCommand {

	private static final double TOLERANCE = 240; // in encoder ticks
	
	private double distance;
	private double startDist;

	public DriveForward(double inches) {
		this.distance = inches * Constants.ENCODER_CODES_PER_REV / (4 * Math.PI);
	}
	
	@Override
	public void init() {
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
	}

	@Override
	public void update() {
		double distFromTarget = distance - (DriveTrain.getInstance().getAvgEncoderPos() - startDist);
		double kP = 0.029; //0.033
		DriveTrain.getInstance().set(DriveTrain.FL, -kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.FR, kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RL, -kP * distFromTarget);
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
		double currentEnc = DriveTrain.getInstance().getAvgEncoderPos();
		double desiredEnc = distance + startDist;
		return Math.abs(currentEnc - desiredEnc) < TOLERANCE;
	}
}
