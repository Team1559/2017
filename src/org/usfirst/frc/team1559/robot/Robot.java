
package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.JoystickButtonListener;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot {

	DriveTrain driveTrain; //Create the Drive Train
	Shooter shooter; //Create the Shooter
	GearGatherer gearGatherer; //Create the Gear Gatherer
	BallGatherer ballGatherer; //Create the Ball Gatherer
	OperatorInterface controllers; //Create the OI
	Diagnostics diagnostics; //Create
	Joystick driver;
	
	
	@Override
	public void robotInit() {
		driveTrain = DriveTrain.getInstance(); //Instantiate the Drive Train
		shooter = Shooter.getInstance(); //Instantiate the Shooter
		gearGatherer = GearGatherer.getInstance(); //Instantiate the Gear Gatherer
		ballGatherer = BallGatherer.getInstance(); //Instantiate the Ball Gatherer
		controllers = OperatorInterface.getInstance(); //Instantiate the OI
		diagnostics = new Diagnostics();
		driver = new Joystick(Wiring.JOYSTICK0);
		controllers.getDriverStick().addButtonListener(shooter);
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
		driveTrain.drive(driver.getX(), driver.getY(), driver.getX(), driver);
	}

	@Override
	public void testInit() {

	}

	@Override
	public void testPeriodic() {
		//diagnostics.encoderData(e); Place encoder "e" here
		diagnostics.jetsonData(420); //Outputs the jetson IMU data
		//diagnostics.servoData(s); Place servo "s" here
		//diagnostics.solenoidData(s); Place solenoid data here
		
		//sorry ryan but it just makes way more sense to me to do it like this:
		if(driver.getRawButton(1) && driver.getRawButton(3)) {
			diagnostics.testSolenoidData(driveTrain.getDrop());
		}
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		
	}

}
