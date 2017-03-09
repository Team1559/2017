package org.usfirst.frc.team1559.auto;

import org.usfirst.frc.team1559.robot.DriveTrain;
import org.usfirst.frc.team1559.robot.Vision;

/**
 * Use for verifying working vision.
 */
public class Follow extends AutoCommand {

	// untested !!

	private double errAngle;
	
	@Override
	public void init() {
		
	}

	@Override
	public void update() {

		errAngle = Vision.getInstance().getAngle();
		
		double kP = 8.5;
		DriveTrain.getInstance().driveMecanum(kP * errAngle, 0, 0);
	}

	@Override
	public void done() {
		// lol nope
	}

	@Override
	public boolean isFinished() {
		return false; // yes, this will run forever.
	}
}
