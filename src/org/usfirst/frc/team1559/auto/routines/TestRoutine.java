package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.Drive;
import org.usfirst.frc.team1559.auto.Target;

public class TestRoutine extends AutoRoutine {
	
	public TestRoutine() {
		put(new Drive(48, 100));
		put(new Target(50));
	}
}