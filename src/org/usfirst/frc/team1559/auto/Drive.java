package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;

public class Drive extends AutoCommand {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

//	double distance, speedL, speedR;
//	double startDist;
//	
//	public Drive(double distance, double speedR, double speedL) {
//		this.distance = distance;
//		this.speedL= speedL;
//		this.speedR = speedR;
//		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
//	}
//	
//	@Override
//	public void init() {
//		DriveTrain.getInstance().resetEncoders();
//	}
//	
//	public double getDistance() {
//		return distance;
//	}
//
//	@Override
//	public void update() {
//		DriveTrain.getInstance().set(DriveTrain.FL, -speedL);
//		DriveTrain.getInstance().set(DriveTrain.FR, speedR);
//		DriveTrain.getInstance().set(DriveTrain.RL, -speedL);
//		DriveTrain.getInstance().set(DriveTrain.RR, speedR);
//	}
//	
//	@Override
//	public void done() {
//		DriveTrain.getInstance().set(DriveTrain.FL, 0);
//		DriveTrain.getInstance().set(DriveTrain.FR, 0);
//		DriveTrain.getInstance().set(DriveTrain.RL, 0);
//		DriveTrain.getInstance().set(DriveTrain.RR, 0);	
//		DriveTrain.getInstance().resetEncoders();
//	}
//
//	@Override
//	public boolean isFinished() {
//<<<<<<< HEAD
//<<<<<<< HEAD
//<<<<<<< HEAD
//		System.out.println("JUST STOP");
//		return DriveTrain.getInstance().getAvgEncoderPos() >= distance + startDist;
//=======
//=======
//>>>>>>> parent of 60c6b1c... is not good yet
//=======
//>>>>>>> parent of 60c6b1c... is not good yet
//		return DriveTrain.getInstance().getAvgEncoderPos() >= distance;
//>>>>>>> parent of 60c6b1c... is not good yet
//	}
}
