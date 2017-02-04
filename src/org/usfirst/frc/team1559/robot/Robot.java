
package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	DriveTrain driveTrain; //Create the Drive Train
	Shooter shooter; //Create the Shooter
	GearGatherer gearGatherer; //Create the Gear Gatherer
	BallGatherer ballGatherer; //Create the Ball Gatherer
	OperatorInterface controllers; //Create the OI
	Climber climber; //Creates the Climber
	Diagnostics diagnostics; //Create the diagnostics tool
	
	
	@Override
	public void robotInit() {
		driveTrain = DriveTrain.getInstance(); //Instantiate the Drive Train
		shooter = Shooter.getInstance(); //Instantiate the Shooter
		gearGatherer = GearGatherer.getInstance(); //Instantiate the Gear Gatherer
		ballGatherer = BallGatherer.getInstance(); //Instantiate the Ball Gatherer
		controllers = OperatorInterface.getInstance(); //Instantiate the OI
		diagnostics = new Diagnostics();
		climber = Climber.getInstance();
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
		controllers.update(); //update controller values
		gearGatherer.update(controllers.getDriverStick().getStick().getRawButton(Constants.GEAR_GATHERER)); //Update gear gatherer
		
		if (driveTrain.getMecanumized()) { //Driving
			driveTrain.driveMecanum(controllers.getDriverStick().getStick().getRawAxis(0), controllers.getDriverStick().getStick().getRawAxis(1), controllers.getDriverStick().getStick().getRawAxis(4));
		} else {
			driveTrain.driveTraction(controllers.getDriverStick().getStick());
		}
		
		if (controllers.getDriverStick().getStick().getRawButton(Constants.SHIFTER)) { //Shifting
			driveTrain.drop(!driveTrain.getMecanumized());
		}
		
		shooter.fire(controllers.getDriverStick().getStick().getRawAxis(Constants.SHOOTER_AXIS) > Constants.SHOOTER_TOLERANCE); //Shooter
		
		ballGatherer.pickUpBall(); //Ball pickup
		
		climber.climbOnUp();
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
