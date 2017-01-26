
package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.BetterJoystick;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {

	DriveTrain driveTrain;
	Shooter shooter;
	GearGatherer gearGatherer;
	BallGatherer ballGatherer;
	OperatorInterface controllers;
	
	
	@Override
	public void robotInit() {
		driveTrain = DriveTrain.getInstance();
		shooter = Shooter.getInstance();
		gearGatherer = GearGatherer.getInstance();
		ballGatherer = BallGatherer.getInstance();
		controllers = new OperatorInterface();
	}

	@Override
	public void autonomousInit() {
		driveTrain.drop(false);
	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopInit() {
		driveTrain.drop(false);
	}

	@Override
	public void teleopPeriodic() {
		driveTrain.driveTraction(controllers.getDriverStick().getStick());
	}

	@Override
	public void testInit() {

	}

	@Override
	public void testPeriodic() {

	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {

	}

}
