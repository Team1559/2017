package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.CloseGear;
import org.usfirst.frc.team1559.auto.DriveForward;
import org.usfirst.frc.team1559.auto.DriveMecanum;
import org.usfirst.frc.team1559.auto.DriveTime;
import org.usfirst.frc.team1559.auto.OpenGear;
import org.usfirst.frc.team1559.auto.TargetMecanum;
import org.usfirst.frc.team1559.auto.TrustRyLuuAndWillMergCompletely2;
import org.usfirst.frc.team1559.auto.Turn;
import org.usfirst.frc.team1559.auto.Wait;
import org.usfirst.frc.team1559.robot.Robot;

public class PegRoutine extends AutoRoutine {

	public static enum Side {
		LEFT, CENTER, RIGHT
	}

	private Side peg;

	public PegRoutine(Side peg) {
		this.peg = peg;
	}

	@Override
	public void init() {
		if (peg == Side.LEFT || peg == Side.RIGHT) {
			put(new DriveForward(93 - Robot.WIDTH / 2));
			put(new Turn(peg == Side.LEFT ? 60 : -60, false));
			put(new TargetMecanum());
			//put(new TrustRyLuuAndWillMergCompletely2());
			//put(new DriveForward(20));
			put(new DriveTime(1.8, -0.3));
			//put(new Wait(.2));
			put(new OpenGear());
			put(new DriveForward(-30));
			put(new CloseGear());
		} else {
			if (visionEnabled) {
				put(new DriveForward(24));
				put(new TrustRyLuuAndWillMergCompletely2());
				//put(new Wait(.1));
				//put(new DriveForward(20));
				put(new DriveMecanum(0,6,0));
			} else {
				put(new DriveForward(111 - Robot.WIDTH));
			}
			put(new Wait(0.2));
			//System.out.println("gear me");
			put(new OpenGear());
			put(new Wait(0.5));
			//put(new DriveForward(-24));
			put(new DriveMecanum(0,-24,0));
			put(new CloseGear());
		}
	}
}