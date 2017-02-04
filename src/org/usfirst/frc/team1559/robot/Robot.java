
package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	DriveTrain driveTrain; //Create the Drive Train
	Shooter shooter; //Create the Shooter
	GearGatherer gearGatherer; //Create the Gear Gatherer
	BallGatherer ballGatherer; //Create the Ball Gatherer
	Climber climber; //Creates the Climber
	Diagnostics diagnostics; //Create the diagnostics tool
	OperatorInterface oi; //Create the OI
	
	
	@Override
	public void robotInit() {
		driveTrain = DriveTrain.getInstance(); //Instantiate the Drive Train
		shooter = Shooter.getInstance(); //Instantiate the Shooter
		gearGatherer = GearGatherer.getInstance(); //Instantiate the Gear Gatherer
		ballGatherer = BallGatherer.getInstance(); //Instantiate the Ball Gatherer
		oi = OperatorInterface.getInstance(); //Instantiate the OI
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
		driveTrain.drop(false); //Make sure we are in traction mode
	}

	@Override
	public void teleopPeriodic() {
		oi.updateButtons(); //update controller values
		gearGatherer.update(oi.getDriverStick().getRawButton(Constants.GEAR_GATHERER)); //Update gear gatherer
		
		if (driveTrain.getMecanumized()) { //Driving
			driveTrain.driveMecanum(oi.getDriverStick().getRawAxis(0), oi.getDriverStick().getRawAxis(1), oi.getDriverStick().getRawAxis(4));
		} else {
			driveTrain.driveTraction(oi.getDriverStick().getX(), oi.getDriverStick().getY());
		}
		
		
		if (oi.drop.isPressed()) {
			driveTrain.drop(!driveTrain.getMecanumized());
		}
		shooter.fire(oi.getDriverStick().getRawAxis(Constants.SHOOTER_AXIS) > Constants.SHOOTER_TOLERANCE); //Shooter
		
		ballGatherer.pickUpBall(); //Ball pickup
	
		climber.climbOnUp(); //climb up bois
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
