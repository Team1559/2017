package org.usfirst.frc.team1559.auto.routines;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.CloseGear;
import org.usfirst.frc.team1559.auto.DriveForward;
import org.usfirst.frc.team1559.auto.OpenGear;
import org.usfirst.frc.team1559.auto.TrustWill;
import org.usfirst.frc.team1559.auto.Turn;
import org.usfirst.frc.team1559.robot.Robot;

public class TrustWillRightRoutine extends AutoRoutine {

	@Override
	public void init() {
		put(new DriveForward(93 - Robot.WIDTH / 2));
		put(new Turn(-60, false));
		put(new TrustWill());
		put(new OpenGear());
		put(new DriveForward(-30));
		put(new CloseGear());
	}

}
