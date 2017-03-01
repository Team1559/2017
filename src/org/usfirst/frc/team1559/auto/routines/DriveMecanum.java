package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoCommand;
import org.usfirst.frc.team1559.lib.BNO055;
import org.usfirst.frc.team1559.robot.Constants;
import org.usfirst.frc.team1559.robot.DriveTrain;

public class DriveMecanum extends AutoCommand {

	private static final double DIST_TOLERANCE = 170; // +/- in encoder ticks
	private static final double ROT_TOLERANCE = 1; // +/- in degrees
	
	double startDist;
	double x, y, rot;
	double xErr, yErr, rotErr;
	
	public DriveMecanum(double xInches, double yInches, double rotDegrees) {
		this.x = xInches * Constants.ENCODER_CODES_PER_REV / (4 * Math.PI) * (22.0/32.0);
		this.y = yInches * Constants.ENCODER_CODES_PER_REV / (4 * Math.PI) * (22.0/32.0);
		this.rot = rotDegrees;
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
	}
	
	@Override
	public void init() {
		DriveTrain.getInstance().drop(true);
	}

	@Override
	public void update() {
		double kP_rot = 0.033;
		double kP_dist = 0.000003;
		DriveTrain.getInstance().driveMecanum(0, yErr * kP_dist, rotErr * kP_rot);
		yErr = y - (DriveTrain.getInstance().getAvgEncoderPos() - startDist);
		rotErr = rot - BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getVector()[0];
	}

	@Override
	public void done() {
		DriveTrain.getInstance().stopDriving();
	}

	@Override
	public boolean isFinished() {
		return Math.abs(yErr) < DIST_TOLERANCE && Math.abs(rotErr) < ROT_TOLERANCE;
	}

}
