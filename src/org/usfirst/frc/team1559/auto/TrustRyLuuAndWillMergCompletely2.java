package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;
import org.usfirst.frc.team1559.robot.Vision;

// drive at full speed until < 24 in

public class TrustRyLuuAndWillMergCompletely2 extends AutoCommand {

	private static final double TOLERANCE = 240; // in encoder ticks

	private double currentAngle;
	private double startDist;

	public TrustRyLuuAndWillMergCompletely2() {
	}

	@Override
	public void init() {
		DriveTrain.getInstance().drop(true);
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
	}

	@Override
	public void update() {
		double currentDistance = Vision.getInstance().getDistance();
		currentAngle = Vision.getInstance().getAngle();
//		double distFromTarget = distance - (DriveTrain.getInstance().getAvgEncoderPos() - startDist);
//		double kPd = 0.033; // 0.033
		double kPa = 0.03;
		DriveTrain.getInstance().driveMecanum(kPa * currentAngle, -Math.min(Math.max(currentDistance - 30, 0) / 120, 1), 0, true);
		// DriveTrain.getInstance().set(DriveTrain.FL, -kPd * distFromTarget);
		// DriveTrain.getInstance().set(DriveTrain.FR, kPd * distFromTarget);
		// DriveTrain.getInstance().set(DriveTrain.RL, -kPd * distFromTarget);
		// DriveTrain.getInstance().set(DriveTrain.RR, kPd * distFromTarget);
	}

	@Override
	public void done() {
		DriveTrain.getInstance().stopDriving();
	}

	@Override
	public boolean isFinished() {
		return Vision.getInstance().getDistance() < 25;
//		double currentEnc = DriveTrain.getInstance().getAvgEncoderPos();
//		double desiredEnc = distance + startDist;
//		return Math.abs(currentEnc - desiredEnc) < TOLERANCE;
	}
}
