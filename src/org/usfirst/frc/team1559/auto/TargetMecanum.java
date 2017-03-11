package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.lib.BNO055;
import org.usfirst.frc.team1559.robot.DriveTrain;
import org.usfirst.frc.team1559.robot.Vision;

public class TargetMecanum extends AutoCommand {

	private static final double TOLERANCE = 2.35;

	double startAngle;
	double currentAngle;
	double[] angleBuffer;

	public TargetMecanum() {
	}

	public TargetMecanum(double speed) {
	}

	@Override
	public void init() {
		DriveTrain.getInstance().drop(true);
		this.startAngle = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER).getHeading();
		currentAngle = Vision.getInstance().getAngle();
		angleBuffer = new double[25];
		for (int i = 0; i < angleBuffer.length; i++) {
			angleBuffer[i] = -1000;
		}
	}

	@Override
	public void update() {
		currentAngle = Vision.getInstance().getAngle();
		for (int i = angleBuffer.length - 2; i >= 0; i--) {
			angleBuffer[i + 1] = angleBuffer[i];
		}
		angleBuffer[0] = currentAngle;
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
		double error = 0;
		for (double x : angleBuffer) {
			error += Math.abs(x);
		}
		return error <= TOLERANCE * angleBuffer.length;
	}
}
