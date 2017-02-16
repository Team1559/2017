package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;

public class Drive extends AutoCommand {

<<<<<<< HEAD
	double distance, speedL, speedR;
	double startDist;
=======
	double distance, speedL, speedR, origin;
>>>>>>> origin/master
	
	public Drive(double distance, double speedR, double speedL, double origin) {
		this.distance = distance;
		this.speedL= speedL;
		this.speedR = speedR;
<<<<<<< HEAD
		this.startDist = DriveTrain.getInstance().getAvgEncoderPos();
=======
		this.origin = origin;
>>>>>>> origin/master
	}
	
	@Override
	public void init() {
<<<<<<< HEAD

=======
		DriveTrain.getInstance().resetEncoders();
		origin = 0;
	}
	
	public double getDistance() {
		return distance;
>>>>>>> origin/master
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
		System.out.println("STOP NOW PLS");
		DriveTrain.getInstance().set(DriveTrain.FL, 0);
		DriveTrain.getInstance().set(DriveTrain.FR, 0);
		DriveTrain.getInstance().set(DriveTrain.RL, 0);
		DriveTrain.getInstance().set(DriveTrain.RR, 0);	
		DriveTrain.getInstance().resetEncoders();
	}

	@Override
	public boolean isFinished() {
<<<<<<< HEAD
		System.out.println("JUST STOP");
		return DriveTrain.getInstance().getAvgEncoderPos() >= distance + startDist;
=======
		return DriveTrain.getInstance().getEncoderPos(DriveTrain.FL) >= (distance + origin);
		
>>>>>>> origin/master
	}
}
