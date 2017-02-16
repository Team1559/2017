package org.usfirst.frc.team1559.auto;

import java.util.Vector;

import org.usfirst.frc.team1559.robot.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
=======

	public void reset() {
		currentCommand = 0;
		for (AutoCommand c : commands) {
			c.setInitialized(false);
		}
>>>>>>> origin/master
	}

	public void put(AutoCommand auto) {
		commands.add(auto);
	}

	public void run() {
<<<<<<< HEAD
		if (currentCommand >= actions.size())
			return;
		if (!actions.get(currentCommand).isInitialized()) {
			actions.get(currentCommand).initialize();
		}
		if (!actions.get(currentCommand).isFinished()) {
			actions.get(currentCommand).update();
=======
		SmartDashboard.putNumber("Command Num: ", currentCommand);
		SmartDashboard.putNumber("AVG Encoder Pos: ", DriveTrain.getInstance().getAvgEncoderPos(false));
		SmartDashboard.putNumber("Distance: ", commands.get(currentCommand).getDistance());
		if (currentCommand >= commands.size()) {
			return;
		}
		if (!commands.get(currentCommand).isInitialized()) {
			commands.get(currentCommand).initialize();
			System.out.println("Init");
		}
		if (!commands.get(currentCommand).isFinished()) {
			commands.get(currentCommand).update();
			System.out.println("Updating");
			
>>>>>>> origin/master
		} else {
			commands.get(currentCommand).done();
			DriveTrain.getInstance().resetEncoders();
			System.out.println("Finished");
			currentCommand++;
		}
	}
<<<<<<< HEAD

=======
>>>>>>> origin/master
}
