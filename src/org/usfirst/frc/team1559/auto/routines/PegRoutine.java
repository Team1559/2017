package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.DriveDistance;
import org.usfirst.frc.team1559.auto.Peg;
import org.usfirst.frc.team1559.auto.Target;

public class PegRoutine extends AutoRoutine {

	public PegRoutine() {
		//put(new DriveDistance(48));
		put(new Target());
		put(new Peg());
		put(new DriveDistance(-48));
	}
}