package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.Drive;
import org.usfirst.frc.team1559.robot.DriveTrain;

public class TestRoutine extends AutoRoutine {

	public TestRoutine() {
		put(new Drive(10000, 2000, 2000));
		put(new Drive(5000, 200, 200));
	}
}