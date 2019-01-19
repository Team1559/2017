
package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.auto.AutoRoutine;
import org.usfirst.frc.team1559.auto.routines.PegRoutine;
import org.usfirst.frc.team1559.auto.routines.Test;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Robot extends IterativeRobot {

	public static final int WIDTH = 36; // inches
	public static final int LENGTH = 40; // inches

	private Compressor compressor;
	private AutoRoutine routine;
	private DriveTrain driveTrain;
	//private Shooter shooter;
	private GearPlacer gearGatherer;
	private BallGatherer ballGatherer;
	private Climber climber;
	private OperatorInterface oi;
	private PowerDistributionPanel pdp;

	public void robotInit() {
		Vision.getInstance();
		routine = new Test();
		pdp = new PowerDistributionPanel();
		driveTrain = DriveTrain.getInstance();
		//shooter = Shooter.getInstance();
		gearGatherer = GearPlacer.getInstance();
		ballGatherer = BallGatherer.getInstance();
		oi = OperatorInterface.getInstance();
		climber = Climber.getInstance();
		compressor = new Compressor();
	}

	// data 9 -el frutch
	public void autonomousInit() {
		driveTrain.drop(false);
		routine.reset();
		//shooter.shooterInit();
		// Vision.getInstance().checkConnection();
	}

	public void autonomousPeriodic() {
		Vision.getInstance().update();
		routine.run();
		// if (!Vision.getInstance().isConnected()) {
		// Vision.getInstance().checkConnection();
		// }
		// routine.setVisionEnabled(Vision.getInstance().isConnected());
	}

	public void teleopInit() {
		driveTrain.drop(false); // Make sure we are in traction mode
		ballGatherer.setPiston(true);
		driveTrain.setOperatorControlled(true);
		System.out.println("hello World");
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

		//shooter.fire(oi.shoot.isDown());
	}

	public void testInit() {
		compressor.start();
	}

	public void testPeriodic() {
		driveTrain.stopDriving();
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
	}

	public void disabledInit() {
	}

	public void disabledPeriodic() {
	}

}
