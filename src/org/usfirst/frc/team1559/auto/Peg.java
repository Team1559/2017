package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.Constants;
import org.usfirst.frc.team1559.robot.DriveTrain;
import org.usfirst.frc.team1559.robot.GearGatherer;
import org.usfirst.frc.team1559.robot.Vision;

public class Peg extends AutoCommand {

	private static final double TOLERANCE = 170; // in encoder ticks
	private static final double ANGLE_TOLERANCE = 1;
	double distance;
	double startDist;

	public Peg() {
		this.distance = 59 * Constants.ENCODER_CODES_PER_REV / (4 * Math.PI) * (22.0/32.0);
	}

	@Override
	public void init() {
		DriveTrain.getInstance().drop(true);
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
		GearGatherer.getInstance().set(false);
	}

	@Override
	public void update() {
		//double distFromTarget = distance - (DriveTrain.getInstance().getAvgEncoderPos() - startDist);
		double distFromTarget = Vision.getInstance().getDistance();
		System.out.println(distFromTarget);
		double angleFromTarget = Vision.getInstance().getAngle();
		double kP_angle = 0.033;
		double kP_dist = 0.0000000003;
		DriveTrain.getInstance().driveMecanum(angleFromTarget * kP_angle, -distFromTarget * kP_dist, 0);
	}

	@Override
	public void done() {
		DriveTrain.getInstance().set(DriveTrain.FL, 0);
		DriveTrain.getInstance().set(DriveTrain.FR, 0);
		DriveTrain.getInstance().set(DriveTrain.RL, 0);
		DriveTrain.getInstance().set(DriveTrain.RR, 0);
		GearGatherer.getInstance().set(true);
	}
	
	@Override
	public boolean isFinished() {
		double currentEnc = DriveTrain.getInstance().getAvgEncoderPos();
		double desiredEnc = distance + startDist;
		double errAngle = Math.abs(Vision.getInstance().getAngle());
		return Math.abs(currentEnc - desiredEnc) < TOLERANCE && errAngle < ANGLE_TOLERANCE;
	}
}
