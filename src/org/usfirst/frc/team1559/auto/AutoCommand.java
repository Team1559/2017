package org.usfirst.frc.team1559.auto;

public abstract class AutoCommand {

	public abstract void init();
	public abstract void update();
	public abstract void done();
	public abstract boolean isFinished();
}
