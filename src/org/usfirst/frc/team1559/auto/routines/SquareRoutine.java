package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.DriveForward;
import org.usfirst.frc.team1559.auto.Turn;

public class SquareRoutine extends AutoRoutine {

	@Override
	public void init() {
		for (int x = 0; x < 4; x++) {
			put(new DriveForward(48));
			put(new Turn(90));
		}
	}
}