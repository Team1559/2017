
package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;

public class Robot extends IterativeRobot {

	DriveTrain driveTrain; //Create the Drive Train
	//Shooter shooter; //Create the Shooter
	//GearGatherer gearGatherer; //Create the Gear Gatherer
	//BallGatherer ballGatherer; //Create the Ball Gatherer
	//Climber climber; //Creates the Climber
	//Diagnostics diagnostics; //Create the diagnostics tool
	OperatorInterface oi; //Create the OI
	I2C imu;//QUICK AND DIRTY -NATE
	
	
	@Override
	public void robotInit() {
		driveTrain = DriveTrain.getInstance(); //Instantiate the Drive Train
		//shooter = Shooter.getInstance(); //Instantiate the Shooter
		//gearGatherer = GearGatherer.getInstance(); //Instantiate the Gear Gatherer
		//ballGatherer = BallGatherer.getInstance(); //Instantiate the Ball Gatherer
		oi = OperatorInterface.getInstance(); //Instantiate the OI
		//diagnostics = new Diagnostics();
		//climber = Climber.getInstance();
		imu = new I2C(Port.kOnboard, 0x28);//QUICK AND DIRTY -NATE
		setIMU();//QUICK AND DIRTY -NATE
		
	}
	
	public void setIMU(){//QUICK AND DIRTY -NATE
		imu.write(0x3D, 0x03);
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

	public double getIMUAngle(){//QUICK AND DIRTY -NATE
		double angle = 0.0;
		byte[] gyroZLSB = new byte[1];
		byte[] gyroZMSB = new byte[1];
		imu.read(0x18, 1, gyroZLSB);
		imu.read(0x19, 1, gyroZMSB);
		angle = (double)((gyroZMSB[0] << 8) | gyroZLSB[0]);
		return angle;
	}
	@Override
	public void teleopPeriodic() {
		//JOHN, GRAB AN ANGLE FROM THE GYRO WITH THE getIMUAngle METHOD. THIS WS WRITTEN IN TEN MINUTES SO I WOULD TEST THE METHOD OUT FIRST -NATE
		oi.updateButtons(); //update controller values
		//gearGatherer.update(oi.getDriverStick().getRawButton(Constants.GEAR_GATHERER)); //Update gear gatherer
		driveTrain.drive(oi.getDriverStick());
		
		if (oi.drop.isPressed()) {
			driveTrain.drop(!driveTrain.getMecanumized());
		}
		//shooter.fire(oi.getDriverStick().getRawAxis(Constants.SHOOTER_AXIS) > Constants.SHOOTER_TOLERANCE); //Shooter
		
		//ballGatherer.pickUpBall(); //Ball pickup
	
		//climber.climbOnUp(); //climb up bois
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
