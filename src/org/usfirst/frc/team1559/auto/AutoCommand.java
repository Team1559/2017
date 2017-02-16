package org.usfirst.frc.team1559.auto;

public abstract class AutoCommand {
<<<<<<< HEAD

=======
	
>>>>>>> origin/master
	private boolean initialized;
	
	public AutoCommand() {
		initialized = false;
	}
	
	public void initialize() {
<<<<<<< HEAD
		initialized = true;
		init();
	}
=======
		init();
		initialized = true;
	}
	
>>>>>>> origin/master
	public abstract void init();
	public abstract void update();
	public abstract void done();
	public abstract boolean isFinished();
	
	public boolean isInitialized() {
		return initialized;
	}
<<<<<<< HEAD
=======
	
	public void setInitialized(boolean b) {
		initialized = b;
	}
	
	public abstract double getDistance();
>>>>>>> origin/master
}
