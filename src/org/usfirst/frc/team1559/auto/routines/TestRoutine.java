package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.Drive;
import org.usfirst.frc.team1559.robot.DriveTrain;

public class TestRoutine extends AutoRoutine {

	public TestRoutine() {
		put(new Drive(10000, 200, 200, DriveTrain.getInstance().getEncoderPos(DriveTrain.FL)));
		put(new Drive(5000, 600, 600, DriveTrain.getInstance().getEncoderPos(DriveTrain.FL)));
	}
}