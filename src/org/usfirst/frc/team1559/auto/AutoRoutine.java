package org.usfirst.frc.team1559.auto;

import java.util.Vector;

public class AutoRoutine {

	Vector<AutoCommand> commands;

	int currentCommand;
	
	public AutoRoutine() {
		commands = new Vector<AutoCommand>();
		currentCommand = 0;
	}
<<<<<<< HEAD

	public void reset() {
		currentCommand = 0;
	}

=======
	
>>>>>>> parent of 60c6b1c... is not good yet
	public void put(AutoCommand auto) {
		commands.add(auto);
	}
	
	public void run() {
<<<<<<< HEAD
		if (currentCommand >= commands.size())
			return;
		if (!commands.get(currentCommand).isInitialized()) {
			commands.get(currentCommand).initialize();
			System.out.println("INIT " + currentCommand);
		}
		if (!commands.get(currentCommand).isFinished()) {
			commands.get(currentCommand).update();
			System.out.println("UPDATE " + currentCommand);
=======
		actions.get(currentCommand).init();
		if (!actions.get(currentCommand).isFinished()) {
			actions.get(currentCommand).update();
>>>>>>> parent of 60c6b1c... is not good yet
		} else {
			commands.get(currentCommand).done();
			System.out.println("DONE " + currentCommand);
			currentCommand++;
		}
		
	}
<<<<<<< HEAD
=======
	
	
>>>>>>> parent of 60c6b1c... is not good yet
}
