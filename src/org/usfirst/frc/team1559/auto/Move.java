package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;

public class Move extends AutoAction {

	double distance, speedL, speedR;
	
	public Move(double distance, double speedR, double speedL) {
		this.distance = distance;
		this.speedL= speedL;
		this.speedR = speedR;
	}
	
	public boolean init() {
		DriveTrain.getInstance().resetEncoders();
		return true;
	}

	public boolean update() {
		DriveTrain.getInstance().set(DriveTrain.FL, -speedL);
		DriveTrain.getInstance().set(DriveTrain.FR, speedR);
		DriveTrain.getInstance().set(DriveTrain.RL, -speedL);
		DriveTrain.getInstance().set(DriveTrain.RR, speedR);
		return DriveTrain.getInstance().getAvgEncoderPos() >= distance;
	}
	
	public boolean stop() {
		DriveTrain.getInstance().set(DriveTrain.FL, 0);
		DriveTrain.getInstance().set(DriveTrain.FR, 0);
		DriveTrain.getInstance().set(DriveTrain.RL, 0);
		DriveTrain.getInstance().set(DriveTrain.RR, 0);		
		return true;
	}
}
