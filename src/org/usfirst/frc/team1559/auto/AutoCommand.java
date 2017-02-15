package org.usfirst.frc.team1559.auto;

public abstract class AutoCommand {
	
	private boolean initialized;
	
	public AutoCommand() {
		initialized = false;
	}
	
	public void initialize() {
		init();
		initialized = true;
	}
	
	public abstract void init();
	public abstract void update();
	public abstract void done();
	public abstract boolean isFinished();
	
	public boolean isInitialized() {
		return initialized;
	}
	
	public void setInitialized(boolean b) {
		initialized = b;
	}
	
	public abstract double getDistance();
}
