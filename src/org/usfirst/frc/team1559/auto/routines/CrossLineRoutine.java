package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.DriveForward;

public class CrossLineRoutine extends AutoRoutine {

	@Override
	public void init() {
		put(new DriveForward(93));
	}
}
