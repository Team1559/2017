package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.DriveMecanum;

public class PegRoutine extends AutoRoutine {

	public PegRoutine() {
		//put(new DriveDistance(48));
		//putSeries(new Peg());
		//putSeries(new Wait(3));
		put(new DriveMecanum(0, 48, 0));
//		put(new DriveDistance(48)); // subtract 11 thousand
	}
}