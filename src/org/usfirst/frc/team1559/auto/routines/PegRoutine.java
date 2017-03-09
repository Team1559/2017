package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.CloseGear;
import org.usfirst.frc.team1559.auto.DriveForward;
import org.usfirst.frc.team1559.auto.OpenGear;
import org.usfirst.frc.team1559.auto.Peg;
import org.usfirst.frc.team1559.auto.Target;
import org.usfirst.frc.team1559.auto.Turn;
import org.usfirst.frc.team1559.auto.Wait;
import org.usfirst.frc.team1559.robot.Robot;

public class PegRoutine extends AutoRoutine {

	public static final int LEFT = 0;
	public static final int CENTER = 1;
	public static final int RIGHT = 2;

	public PegRoutine(int pegId, boolean vision) {
		if (pegId == LEFT || pegId == RIGHT) {
			put(new DriveForward(93 - Robot.WIDTH / 2));
			put(new Turn(pegId == LEFT ? 60 : -60, true));
			put(new DriveForward(60 - Robot.WIDTH / 2)); // TODO: figure out drive distances
			put(new Target());
			put(new Peg());
			put(new DriveForward(-30));
		} else {
			put(new DriveForward(104 - Robot.WIDTH));
			if (vision) {
				put(new Target());
			} else {
				put(new Wait(0.5));
			}
			put(new OpenGear());
			put(new Wait(0.5));
			put(new DriveForward(-24));
			put(new CloseGear());
		}
	}
}