
package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	DriveTrain driveTrain; //Create the Drive Train
	//Shooter shooter; //Create the Shooter
	//GearGatherer gearGatherer; //Create the Gear Gatherer
	//BallGatherer ballGatherer; //Create the Ball Gatherer
	//Climber climber; //Creates the Climber
	//Diagnostics diagnostics; //Create the diagnostics tool
	OperatorInterface oi; //Create the OI
	I2C imu;//QUICK AND DIRTY -NATE
	
	Ramp rx;
	Ramp ry;
	
	
	public void robotInit() {
		driveTrain = DriveTrain.getInstance(); //Instantiate the Drive Train
		//shooter = Shooter.getInstance(); //Instantiate the Shooter
		//gearGatherer = GearGatherer.getInstance(); //Instantiate the Gear Gatherer
		//ballGatherer = BallGatherer.getInstance(); //Instantiate the Ball Gatherer
		oi = OperatorInterface.getInstance(); //Instantiate the OI
		rx = new Ramp();
		ry = new Ramp();
		//diagnostics = new Diagnostics();
		//climber = Climber.getInstance();
		imu = new I2C(Port.kOnboard, 0x28);//QUICK AND DIRTY -NATE
		setIMU();//QUICK AND DIRTY -NATE
		
	}
	
	public void setIMU(){//QUICK AND DIRTY -NATE
		imu.write(0x3D, 0x0C);
	}

	public void autonomousInit() {
		driveTrain.drop(false); //Make sure we are in traction mode
	}

	public void autonomousPeriodic() {

	}

	public void teleopInit() {
		driveTrain.drop(false); //Make sure we are in traction mode
		
		setIMU();
	}

	public double getIMUAngle(){//QUICK AND DIRTY -NATE
		double angle = 0.0;
		byte[] gyroZLSB = new byte[1];
		byte[] gyroZMSB = new byte[1];
		
		imu.read(0x18, 1, gyroZLSB);
		imu.read(0x19, 1, gyroZMSB);
		SmartDashboard.putNumber("LSB", gyroZLSB[0]);
		SmartDashboard.putNumber("MSB", gyroZMSB[0]);
		angle = ((double)((gyroZMSB[0] << 8) | gyroZLSB[0]))/16;
		return angle;
	}
	
	public void teleopPeriodic() {
		//JOHN, GRAB AN ANGLE FROM THE GYRO WITH THE getIMUAngle METHOD. THIS WS WRITTEN IN TEN MINUTES SO I WOULD TEST THE METHOD OUT FIRST -NATE
		oi.updateButtons(); //update controller values
		//gearGatherer.update(oi.getDriverStick().getRawButton(Constants.GEAR_GATHERER)); //Update gear gatherer
//		driveTrain.drive(oi.getDriverStick());
		driveTrain.driveTraction(oi.getDriverStick(), rx, ry); // with this setup, mecanum doesn't work, plz fix
		if (oi.drop.isPressed()) {
			driveTrain.drop(!driveTrain.getMecanumized());
		}
		//shooter.fire(oi.getDriverStick().getRawAxis(Constants.SHOOTER_AXIS) > Constants.SHOOTER_TOLERANCE); //Shooter
		
		//ballGatherer.pickUpBall(); //Ball pickup
	
		//climber.climbOnUp(); //climb up bois
		SmartDashboard.putNumber("IMU", getIMUAngle());
	}


	public void testInit() {

	}


	public void testPeriodic() {
		driveTrain.set(Wiring.FL_SRX, 0);
		driveTrain.set(Wiring.FR_SRX, 0);
		driveTrain.set(Wiring.RR_SRX, 0);
		driveTrain.set(Wiring.RL_SRX, 0);
		
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
