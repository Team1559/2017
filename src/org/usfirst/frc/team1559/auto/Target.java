package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;
import org.usfirst.frc.team1559.robot.Vision;

public class Target extends AutoCommand {

	private static final double TOLERANCE = 2.5;
	
	double speed;
	double currentAngle;
	double[] angleBuffer;
	
	public Target() {
		this.speed = 80;
	}
	public Target(double speed) {
		this.speed = speed;
	}

	@Override
	public void init() {
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
		double kP = 0.15; //.220
		DriveTrain.getInstance().set(DriveTrain.FL, speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.FR, speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RL, speed * kP * distFromTarget);
		DriveTrain.getInstance().set(DriveTrain.RR, speed * kP * distFromTarget);
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
		double error = 0;
		for (double x : angleBuffer) {
			error += Math.abs(x);
		}
		return error <= TOLERANCE * angleBuffer.length;
	}
}
