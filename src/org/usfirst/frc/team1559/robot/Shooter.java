package org.usfirst.frc.team1559.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Servo;

public class Shooter {
	// The Shooter is a Fly Wheel.
	// This is where all the variables are instantiated.
	boolean SERVO_BOOLEAN, GATE_STATUS;
	// SERVO_BOOLEAN - Sets the angle of the Servo, based on speed.
	// GATE_STATUS - If the gate is closed, no balls are allowed in, and if open, vice versa.
	Servo BALL_OPENER;
	// The instantiation of the servo.
	CANTalon SRX;
	// The Encoder checks the rate of fire for the talon.
	// The talon allows the Robot to control the rate of fire.
	// The Joystick allows the pilots to control the fire of the balls. (Will change)
	int i;
	// For all for loops.
	public void ShooterInit() {
		// All the variables are defined here.
		BALL_OPENER = new Servo(1); // Will change for actual Robot.
		SRX = new CANTalon(1);// Will change for the actual Robot.
		SERVO_BOOLEAN = true; // Starts out defined as true, the servo is open.
		GATE_STATUS = true; // The gate starts out as open.
		i = 0; // To be used with the for loop to limit the ball rate.
		SRX.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		SRX.configEncoderCodesPerRev(4096);
		SRX.changeControlMode(TalonControlMode.Speed);
		SRX.configNominalOutputVoltage(+0.0f, -0.0f);
		SRX.configPeakOutputVoltage(+12.0f, 0.0f);
		SRX.setProfile(0);
		SRX.setP(0);
		SRX.setI(0);
		SRX.setD(0);
		SRX.setF(0);
		
	}
	public void ShooterGate() {
		// Controls the functions of the gate.
		if(GATE_STATUS) {
			SERVO_BOOLEAN = true; // The rate of fire is not limited.
			BALL_OPENER.set(0.0); // The angle will most definitely change. Also, closes the way for balls to get in.
		}
		else if(GATE_STATUS) {
			for(i = 0; i <= 101; i++) { // 1 integer value is equal to about 20ms.
				SERVO_BOOLEAN = false; // The rate of fire is limited
				if(i <= 50) // tick, tick.
					BALL_OPENER.setAngle(0.0); // Opens the way for balls to get in.
				else if(i > 50 && i <= 100) // tick, tick.
					BALL_OPENER.setAngle(90.00); // Closes the way for balls to get in.
				else if(i == 101) // default scenario
					i = 0; // the for loop resets.
			}
		}
		else if(!GATE_STATUS) {
			for(i = 0; i < 200; i++) { // 1 integer value is equal to about 20ms.
				SERVO_BOOLEAN = false; // The rate of fire is limited
			}
		}
		else {
			SERVO_BOOLEAN = false; // The rate of fire is limited
			BALL_OPENER.setAngle(90.0); // Closes the way for balls to get in.
		}
	}
	
	public void ShooterMotor(int rpm){
		SRX.set(rpm*300/4096);
	}
}