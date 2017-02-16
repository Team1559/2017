package org.usfirst.frc.team1559.auto;

public abstract class AutoCommand {

	private boolean initialized;
	
	public AutoCommand() {
		initialized = false;
	}
	
	public void initialize() {
		initialized = true;
		init();
	}
	public abstract void init();
	public abstract void update();
	public abstract void done();
	public abstract boolean isFinished();
	
	public boolean isInitialized() {
		return initialized;
	}
}
