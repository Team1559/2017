package org.usfirst.frc.team1559.auto;

import java.util.Vector;

public class AutoRoutine {

	Vector<AutoCommand> actions;

	int currentCommand;
	
	public AutoRoutine() {
		actions = new Vector<AutoCommand>();
		currentCommand = 0;
	}
	
	public void put(AutoCommand auto) {
		actions.add(auto);
	}
	
	public void run() {
		actions.get(currentCommand).init();
		if (!actions.get(currentCommand).isFinished()) {
			actions.get(currentCommand).update();
		} else {
			actions.get(currentCommand).done();
			currentCommand++;
		}
		
	}
	
	
}
