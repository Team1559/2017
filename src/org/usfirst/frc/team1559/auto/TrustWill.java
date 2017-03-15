package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;
import org.usfirst.frc.team1559.robot.Vision;

public class TrustWill extends AutoCommand {

	private static final double TOLERANCE = 210; // in encoder ticks

	private double distance;
	private double startDist;
	double currentAngle;

	public TrustWill() {
	}
	
	@Override
	public void init() {
		this.distance = Vision.getInstance().getDistance();
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
		DriveTrain.getInstance().drop(true);
		currentAngle = Vision.getInstance().getAngle();
	}

	@Override
	public void update() {
		double distFromTarget = currentAngle;
		double kP = 0.03; // .220
		DriveTrain.getInstance().driveMecanum(kP * distFromTarget, 0, 0, false);
	}

	@Override
	public void done() {
		DriveTrain.getInstance().stopDriving();
	}

	@Override
	public boolean isFinished() {
		double currentEnc = DriveTrain.getInstance().getAvgEncoderPos();
		double desiredEnc = distance + startDist;
		return Math.abs(currentEnc - desiredEnc) < TOLERANCE;
	}

}
