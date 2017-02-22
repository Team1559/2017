package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.DriveDistance;
import org.usfirst.frc.team1559.auto.Turn;

public class SquareRoutine extends AutoRoutine {

	public SquareRoutine() {
		for (int x = 0; x < 4; x++) {
			put(new DriveDistance(48, 100));
			put(new Turn(90, 100));
		}
	}
}