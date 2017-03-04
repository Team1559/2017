package org.usfirst.frc.team1559.auto;

import edu.wpi.first.wpilibj.Timer;

public abstract class AutoCommand {
	
	private boolean initialized;
	private double t, tStart;
	
	public AutoCommand() {
		initialized = false;
	}

	public void initialize() {
		initialized = true;
		tStart = Timer.getFPGATimestamp();
		init();
	}

	public abstract void init();

	public void tick() {
		t = Timer.getFPGATimestamp() - tStart;
		update();
	}
	
	public abstract void update();

	public abstract void done();

	public abstract boolean isFinished();

	public boolean isInitialized() {
		return initialized;
	}

	public void reset() {
		initialized = false;
	}
	
	public double getTime() {
		return t;
	}
}
