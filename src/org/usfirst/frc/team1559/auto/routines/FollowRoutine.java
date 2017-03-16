package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.Follow;

public class FollowRoutine extends AutoRoutine {

	@Override
	public void init() {
		put(new Follow());
	}
}
