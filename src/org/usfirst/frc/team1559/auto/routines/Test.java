package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.DriveForward;
import org.usfirst.frc.team1559.auto.DriveMecanum;
import org.usfirst.frc.team1559.auto.Turn;

public class Test extends AutoRoutine {

	@Override
	public void init() {
		put(new DriveMecanum(0,10,0));
	}
}