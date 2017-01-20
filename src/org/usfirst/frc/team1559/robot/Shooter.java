package org.usfirst.frc.team1559.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Servo;

public class Shooter {
	// The Shooter is a Fly Wheel.
	// This is where all the variables are instantiated.
	boolean servoBoolean, gateStatus, flowLimit;
	// servoBoolean - Sets the angle of the Servo, based on speed.
	// gateStatus - If the gate is closed, no balls are allowed in, and if open, vice versa.
	Servo ballOpener;
	// The instantiation of the servo.
	CANTalon shooterTalon;
	// The Encoder checks the rate of fire for the talon.
	// The talon allows the Robot to control the rate of fire.
	int i;
	int switchCaseVar;
	// For all for loops.
	public void ShooterInit() {
		// All the variables are defined here.
		ballOpener = new Servo(1); // Will change for actual Robot.
		shooterTalon = new CANTalon(1);// Will change for the actual Robot.
		servoBoolean = true; // Starts out defined as true, the servo is open.
		gateStatus = true; // The gate starts out as open.
		flowLimit = false; // Starts out defined as false, ball rate not limited.
		i = 0; // To be used with the for loop to limit the ball rate.
		switchCaseVar = 0;
		
		// Initiation for the CANTalon
		shooterTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		shooterTalon.configEncoderCodesPerRev(4096);
		shooterTalon.changeControlMode(TalonControlMode.Speed);
		shooterTalon.configNominalOutputVoltage(Constants.NOMIAL_OUTPUT_VOLTAGE, Constants.NEGATIVE_PEAK_OUTPUT_VOLTAGE);
		shooterTalon.configPeakOutputVoltage(Constants.PEAK_OUTPUT_VOLTAGE, Constants.NOMIAL_OUTPUT_VOLTAGE);
		shooterTalon.setProfile(0);
		shooterTalon.setP(Constants.P);
		shooterTalon.setI(Constants.I);
		shooterTalon.setD(Constants.D);
		shooterTalon.setF(Constants.F);
		
	}
	public void ShooterMotor(int rpm){
		shooterTalon.set(rpm*Constants.RPM_CONVERSION); // Motor is started up.
	}
	public void Fire() {
		switch(switchCaseVar){
		case 0:
			ballOpener.setAngle(Constants.OPEN_VAL);
			i++;
			if(i >= Constants.CLOSE_DELAY*50){
				switchCaseVar++;
				i = 0;
			}
		break;
		case 1:
			ballOpener.setAngle(Constants.CLOSE_VAL);
			i++;
			if(i >= Constants.FIRE_DELAY*50){
				switchCaseVar++;
				i = 0;
			}
		break;
		default:
		switchCaseVar = 0;//we screwed up real bad bois
		break;
		}
	}
	
	public void clearCounter(){
		i = 0;
	}
	
	
}