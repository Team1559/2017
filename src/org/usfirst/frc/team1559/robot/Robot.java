
package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	DriveTrain driveTrain; //Create the Drive Train
	Shooter shooter; //Create the Shooter
	GearGatherer gearGatherer; //Create the Gear Gatherer
	BallGatherer ballGatherer; //Create the Ball Gatherer
	OperatorInterface controllers; //Create the OI
	Diagnostics diagnostics; //Create
	
	
	@Override
	public void robotInit() {
		driveTrain = DriveTrain.getInstance(); //Instantiate the Drive Train
		shooter = Shooter.getInstance(); //Instantiate the Shooter
		gearGatherer = GearGatherer.getInstance(); //Instantiate the Gear Gatherer
		ballGatherer = BallGatherer.getInstance(); //Instantiate the Ball Gatherer
		controllers = OperatorInterface.getInstance(); //Instantiate the OI
		diagnostics = new Diagnostics();
	}

	@Override
	public void autonomousInit() {
		driveTrain.drop(false); //Make sure we are in traction mode
	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopInit() {
		controllers.getDriverStick().addButtonListener(gearGatherer);
		driveTrain.drop(false); //Make sure we are in traction mode
	}

	@Override
	public void teleopPeriodic() {
		controllers.update();
		driveTrain.driveTraction(controllers.getDriverStick().getStick()); //Drive in traction mode
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
