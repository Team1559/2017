package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.lib.BNO055;
import org.usfirst.frc.team1559.robot.Constants;
import org.usfirst.frc.team1559.robot.DriveTrain;

public class DriveMecanum extends AutoCommand {

	private static final double DIST_TOLERANCE = 170; // +/- in encoder ticks
	private static final double ROT_TOLERANCE = 1; // +/- in degrees
	
	double startDist;
	double x, y, rot;
	double xErr, yErr, rotErr;
	double speed;
	
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
		yErr = y - (DriveTrain.getInstance().getAvgEncoderPos() - startDist);
		rotErr = rot - BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getVector()[0];
		double kP_rot = 0.033;
		double kP_dist = 0.3; //0.000003
		DriveTrain.getInstance().driveMecanum(0, yErr * kP_dist, rotErr * kP_rot);
		System.out.println(yErr);
		//QUICK AND DIRTY -John
//		if(yErr >= 8000) {
//			DriveTrain.getInstance().driveMecanum(0, 4096, rotErr * kP_rot);
//		} else if (yErr >= 4000) {
//			DriveTrain.getInstance().driveMecanum(0, 2048, rotErr * kP_rot);
//		} else if (yErr >= 1000) {
//			DriveTrain.getInstance().driveMecanum(0, 1024, rotErr * kP_rot);
//		}else {
//			DriveTrain.getInstance().driveMecanum(0, yErr * kP_dist, rotErr * kP_rot);
//		}
	}

	@Override
	public void done() {
		DriveTrain.getInstance().stopDriving();
	}

	@Override
	public boolean isFinished() {
		yErr = y - (DriveTrain.getInstance().getAvgEncoderPos() - startDist);
		rotErr = rot - BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getVector()[0];
		return Math.abs(yErr) < DIST_TOLERANCE && Math.abs(rotErr) < ROT_TOLERANCE;
	}

}
