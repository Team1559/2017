package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.DriveDistance;
import org.usfirst.frc.team1559.auto.Peg;
import org.usfirst.frc.team1559.auto.Wait;

public class PegRoutine extends AutoRoutine {

	public PegRoutine() {
		//put(new DriveDistance(48));
		putSeries(new Peg());
		putSeries(new Wait(3));
		putSeries(new DriveDistance(-48)); // subtract 11 thousand
	}
}