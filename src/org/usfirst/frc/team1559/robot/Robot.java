
package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.routines.CrossLineRoutine;
import org.usfirst.frc.team1559.auto.routines.PegRoutine;
import org.usfirst.frc.team1559.auto.routines.SquareRoutine;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Robot extends IterativeRobot {

	public static final int WIDTH = 36; // inches
	public static final int LENGTH = 40; // inches
	
	Compressor compressor;
	AutoRoutine routine;
	DriveTrain driveTrain; // Create the Drive Train
	Shooter shooter; // Create the Shooter
	GearPlacer gearGatherer; // Create the Gear Gatherer
	BallGatherer ballGatherer; // Create the Ball Gatherer
	Climber climber; // Creates the Climber
	// Diagnostics diagnostics; //Create the diagnostics tool
	OperatorInterface oi; // Create the OI
	PowerDistributionPanel pdp; //snek

	public void robotInit() {
		Vision.getInstance();
		routine = new PegRoutine(PegRoutine.CENTER, false);
		pdp = new PowerDistributionPanel();
		driveTrain = DriveTrain.getInstance(); // Instantiate the Drive Train
		shooter = Shooter.getInstance(); // Instantiate the Shooter
		gearGatherer = GearPlacer.getInstance(); // Instantiate the Gear
													// Gatherer
		ballGatherer = BallGatherer.getInstance(); // Instantiate the Ball
													// Gatherer
		oi = OperatorInterface.getInstance(); // Instantiate the OI
		// diagnostics = new Diagnostics();
		climber = Climber.getInstance();
		compressor = new Compressor();
		
		System.out.println("snekked");
	}
	//data 9 -el frutch
	public void autonomousInit() {
		driveTrain.drop(false); // Make sure we are in traction mode
		driveTrain.setOperatorControlled(false);
		routine.reset();
		shooter.shooterInit();
	}

	public void autonomousPeriodic() {
		Vision.getInstance().update();
		routine.run();
		driveTrain.update();
	}

	public void teleopInit() {
		driveTrain.drop(false); // Make sure we are in traction mode
		ballGatherer.setPiston(true);
		driveTrain.setOperatorControlled(true);
	}

	public void teleopPeriodic() {
		// Vision.getInstance().update();
		// JOHN, GRAB AN ANGLE FROM THE GYRO WITH THE getIMUAngle METHOD. THIS
		// WS WRITTEN IN TEN MINUTES SO I WOULD TEST THE METHOD OUT FIRST -NATE
		oi.updateButtons(); // update controller values
		// gearGatherer.update(oi.getDriverStick().getRawButton(Constants.GEAR_GATHERER));
		// //Update gear gatherer
		// driveTrain.drive(oi.getDriverStick());
		driveTrain.update(oi.getDriverStick()); // with this setup, mecanum
												// doesn't work, plz fix
		if (oi.drop.isPressed()) {
			driveTrain.drop(!driveTrain.getMecanumized());
		}
		// shooter.fire(oi.getDriverStick().getRawAxis(Constants.SHOOTER_AXIS) >
		// Constants.SHOOTER_TOLERANCE); //Shooter

		gearGatherer.set(oi.gear.isDown());
		ballGatherer.gather(oi.gather.isDown());

		if (oi.flip.isPressed()) {
			driveTrain.setFlipped(!driveTrain.isFlipped());
		}

		if (oi.climb.isDown() || oi.climb2.isDown()) {
			climber.climb();
		} else if (oi.unclimb.isDown()) {
			climber.unclimb();
		} else {
			climber.stop();
		}

		if (oi.openUp.isPressed()) {
			gearGatherer.openMouth(!gearGatherer.isMouthOpen());
		}

		shooter.fire(oi.shoot.isDown());
	}

	public void testInit() {
		compressor.start();
	}

	public void testPeriodic() {
		driveTrain.set(DriveTrain.FL, 0);
		driveTrain.set(DriveTrain.FR, 0);
		driveTrain.set(DriveTrain.RL, 0);
		driveTrain.set(DriveTrain.RR, 0);
		// shooter.fire(true);

		switch (oi.getDriverStick().getPOV()) {
		case 45:
			// driveTrain.set(Wiring.FR_SRX, -100);
			driveTrain.set(DriveTrain.FR, 300);
			break;
		case 135:
			// driveTrain.set(Wiring.RR_SRX, -100);
			driveTrain.set(DriveTrain.RR, 300);
			break;
		case 180 + 45:
			// driveTrain.set(Wiring.RL_SRX, 100);
			driveTrain.set(DriveTrain.RL, 300);
			break;
		case 360 - 45:
			// driveTrain.set(Wiring.FL_SRX, 100);
			driveTrain.set(DriveTrain.FL, 300);
			break;
		}
		System.out.println("Enc" + shooter.getShooter().getEncPosition());
	}

	public void disabledInit() {
	}

	public void disabledPeriodic() {
	}

}
