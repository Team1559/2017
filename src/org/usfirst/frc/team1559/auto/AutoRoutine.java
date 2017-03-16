package org.usfirst.frc.team1559.auto;

import java.util.ArrayList;
import java.util.List;

public abstract class AutoRoutine {

	private List<AutoCommand> commands;
	private int currentCommand;
	protected boolean visionEnabled;

	public AutoRoutine() {
		visionEnabled = true;
		commands = new ArrayList<AutoCommand>();
		currentCommand = 0;
		init();
	}

	public abstract void init();

	public void reset() {
		currentCommand = 0;
		for (AutoCommand c : commands) {
			c.reset();
		}
	}

	public void put(AutoCommand auto) {
		commands.add(auto);
	}

	public void run() {
		if (currentCommand >= commands.size()) {
			return;
		}
		if (!commands.get(currentCommand).isInitialized()) {
			commands.get(currentCommand).initialize();
			System.out.println("INIT " + currentCommand);
		}
		if (!commands.get(currentCommand).isFinished()) {
			commands.get(currentCommand).tick();
			System.out.println("UPDATE " + currentCommand + ": (" + commands.get(currentCommand).getClass().getName() + ")");
		} else {
			commands.get(currentCommand).done();
			System.out.println("DONE " + currentCommand);
			currentCommand++;
		}
	}

	public void setVisionEnabled(boolean b) {
		visionEnabled = b;
	}
}
