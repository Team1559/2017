package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;

public class Drive extends AutoCommand {

	double distance, speedL, speedR;
	
	public Drive(double distance, double speedR, double speedL) {
		this.distance = distance;
		this.speedL= speedL;
		this.speedR = speedR;
	}
	
	@Override
	public void init() {
		DriveTrain.getInstance().resetEncoders();
	}

	@Override
	public void update() {
		DriveTrain.getInstance().set(DriveTrain.FL, -speedL);
		DriveTrain.getInstance().set(DriveTrain.FR, speedR);
		DriveTrain.getInstance().set(DriveTrain.RL, -speedL);
		DriveTrain.getInstance().set(DriveTrain.RR, speedR);
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
		return DriveTrain.getInstance().getAvgEncoderPos() >= distance;
	}
}
