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

	public void reset() {
		currentCommand = 0;
		for (AutoCommand c : commands) {
			c.setInitialized(false);
		}
	}

	public void put(AutoCommand auto) {
		commands.add(auto);
	}

	public void run() {
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
			
		} else {
			commands.get(currentCommand).done();
			DriveTrain.getInstance().resetEncoders();
			System.out.println("Finished");
			currentCommand++;
		}
	}
}
