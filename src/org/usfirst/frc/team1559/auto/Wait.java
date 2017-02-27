package org.usfirst.frc.team1559.auto;

public class Wait extends AutoCommand {

	int ticks;
	int timer;
	
	public Wait(double seconds) {
		this.ticks = (int) (seconds * 50);
	}
	
	@Override
	public void init() {
		this.timer = 0;
	}

	@Override
	public void update() {
		timer++;
	}

	@Override
	public void done() {
		
	}

	@Override
	public boolean isFinished() {
		return timer >= ticks;
	}

}
