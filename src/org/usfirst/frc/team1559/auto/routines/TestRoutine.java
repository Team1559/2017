package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.Drive;

public class TestRoutine extends AutoRoutine {

	public TestRoutine() {
		put(new Drive(10000, 2000, 2000));
	}
}
