package org.usfirst.frc.team1559.auto;

import java.util.ArrayList;
import java.util.List;

public class AutoRoutine {

	List<AutoCommand> commands;

	int currentCommand;

	public AutoRoutine() {
		commands = new ArrayList<AutoCommand>();
		currentCommand = 0;
	}

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
			System.out.println("UPDATE " + currentCommand);
		} else {
			commands.get(currentCommand).done();
			System.out.println("DONE " + currentCommand);
			currentCommand++;
		}
	}
}
