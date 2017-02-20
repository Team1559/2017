package org.usfirst.frc.team1559.auto;

import java.util.Vector;

public class AutoRoutine {

	Vector<AutoCommand> commands;

	int currentCommand;

	public AutoRoutine() {
		commands = new Vector<AutoCommand>();
		currentCommand = 0;
	}

	public void reset() {
		currentCommand = 0;
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
			commands.get(currentCommand).update();
			System.out.println("UPDATE " + currentCommand);
		} else {
			commands.get(currentCommand).done();
			System.out.println("DONE " + currentCommand);
			currentCommand++;
		}
	}
}
