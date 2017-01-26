
package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {

	private boolean drive;
	
	DriveTrain driveTrain;
	Shooter shooter;
	GearGatherer gearGatherer;
	BallGatherer ballGatherer;
	Joystick driver;
	
	
	@Override
	public void robotInit() {
		drive = false;
		driveTrain = new DriveTrain();
		shooter = new Shooter();
		gearGatherer = new GearGatherer();
		ballGatherer = new BallGatherer();
		driver = new Joystick(Wiring.JOYSTICK0);
	}

	@Override
	public void autonomousInit() {
		driveTrain.drop(drive);
	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopInit() {
		driveTrain.drop(drive);
	}

	@Override
	public void teleopPeriodic() {
		driveTrain.driveTraction(driver);
		
		if(driver.getRawButton(3)) {//Change to constant
			driveTrain.drop(!drive);
		}
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
