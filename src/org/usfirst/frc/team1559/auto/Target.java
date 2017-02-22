package org.usfirst.frc.team1559.auto;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team1559.lib.BNO055;
import org.usfirst.frc.team1559.robot.DriveTrain;
import org.usfirst.frc.team1559.robot.Vision;

public class Target extends AutoCommand {

	double speed;
	double startAngle;
	double currentAngle;
	List<Double> angleBuffer;

	public Target() {
		this.speed = 100;
	}
	public Target(double speed) {
		this.speed = speed;
	}

	@Override
	public void init() {
		this.startAngle = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS, BNO055.vector_type_t.VECTOR_EULER)
				.getHeading();
		currentAngle = Vision.getInstance().getAngle();
		angleBuffer = new ArrayList<Double>();
	}

	@Override
	public void update() {
		currentAngle = Vision.getInstance().getAngle();
		angleBuffer.add(currentAngle);
		if (angleBuffer.size() > 5) {
			angleBuffer.remove(5);
		}
		System.out.println(angleBuffer);
		double distFromTarget = currentAngle;
		double kP = 0.120;
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
		if (angleBuffer.size() < 5) {
			return false;
		}
		double error = 0;
		for (double x : angleBuffer) {
			error += Math.abs(x);
		}
		return error <= 5;
	}
}
