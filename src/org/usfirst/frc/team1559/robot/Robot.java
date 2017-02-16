
package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.routines.TestRoutine;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	AutoRoutine routine;
	DriveTrain driveTrain; //Create the Drive Train
	Shooter shooter; //Create the Shooter
	GearGatherer gearGatherer; //Create the Gear Gatherer
	//BallGatherer ballGatherer; //Create the Ball Gatherer
	//Climber climber; //Creates the Climber
	//Diagnostics diagnostics; //Create the diagnostics tool
	OperatorInterface oi; //Create the OI
	
	public void robotInit() {
		routine = new TestRoutine();
		driveTrain = DriveTrain.getInstance(); //Instantiate the Drive Train
		shooter = Shooter.getInstance(); //Instantiate the Shooter
		gearGatherer = GearGatherer.getInstance(); //Instantiate the Gear Gatherer
		//ballGatherer = BallGatherer.getInstance(); //Instantiate the Ball Gatherer
		oi = OperatorInterface.getInstance(); //Instantiate the OI
		//diagnostics = new Diagnostics();
		//climber = Climber.getInstance();
		
	}

	public void autonomousInit() {
		driveTrain.drop(false); //Make sure we are in traction mode
	}

	public void autonomousPeriodic() {
		routine.run();
	}

	public void teleopInit() {
		driveTrain.drop(false); //Make sure we are in traction mode
	}
	
	public void teleopPeriodic() {
		//JOHN, GRAB AN ANGLE FROM THE GYRO WITH THE getIMUAngle METHOD. THIS WS WRITTEN IN TEN MINUTES SO I WOULD TEST THE METHOD OUT FIRST -NATE
		oi.updateButtons(); //update controller values
		//gearGatherer.update(oi.getDriverStick().getRawButton(Constants.GEAR_GATHERER)); //Update gear gatherer
//		driveTrain.drive(oi.getDriverStick());
		driveTrain.drive(oi.getDriverStick()); // with this setup, mecanum doesn't work, plz fix
		if (oi.drop.isPressed()) {
			driveTrain.drop(!driveTrain.getMecanumized());
		}
		shooter.fire(oi.getDriverStick().getRawAxis(Constants.SHOOTER_AXIS) > Constants.SHOOTER_TOLERANCE); //Shooter
		
		gearGatherer.set(oi.gear.isDown());
		//ballGatherer.pickUpBall(); //Ball pickup
	
		//climber.climbOnUp(); //climb up bois
		SmartDashboard.putNumber("JoyX", oi.getDriverStick().getX() * Math.abs(oi.getDriverStick().getX()));
		SmartDashboard.putNumber("JoyY", oi.getDriverStick().getY() * Math.abs(oi.getDriverStick().getY()));
		SmartDashboard.putNumber("JoyR", oi.getDriverStick().getRawAxis(4));
	}


	public void testInit() {

	}


	public void testPeriodic() {
		driveTrain.set(DriveTrain.FL, 0);
		driveTrain.set(DriveTrain.FR, 0);
		driveTrain.set(DriveTrain.RL, 0);
		driveTrain.set(DriveTrain.RR, 0);
		shooter.fire(true);
		
		switch (oi.getDriverStick().getPOV()) {
		case 45:
//			driveTrain.set(Wiring.FR_SRX, -100);
			driveTrain.set(DriveTrain.FR, -1);
			break;
		case 135:
			//driveTrain.set(Wiring.RR_SRX, -100);
			driveTrain.set(DriveTrain.RR, -1);
			break;
		case 180 + 45:
			//driveTrain.set(Wiring.RL_SRX, 100);
			driveTrain.set(DriveTrain.RL, -1);
			break;
		case 360 - 45:
			//driveTrain.set(Wiring.FL_SRX, 100);
			driveTrain.set(DriveTrain.FL, -1);
			break;
		}
	}


	public void disabledInit() {

	}


	public void disabledPeriodic() {
		
	}

}
