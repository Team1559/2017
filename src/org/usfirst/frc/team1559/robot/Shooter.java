package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
	// The Shooter is a Fly Wheel.
	// This is where all the variables are instantiated.
	boolean LOOP_STATUS, SERVO_BOOLEAN, GATE_STATUS;
	// LOOP_SATUS - Boolean for the speed of the speed controller.
	// SERVO_BOOLEAN - Sets the angle of the Servo, based on speed.
	// GATE_STATUS - If the gate is closed, no balls are allowed in, and if open, vice versa.
	Servo BALL_OPENER;
	// The instantiation of the servo.
	Talon SRX;
	Encoder SRX_Checker;
	// The Encoder checks the rate of fire for the talon.
	// The talon allows the Robot to control the rate of fire.
	// The ERROR_LIGHT alerts the Team if the code malfunctions.
	int i;
	// For all for loops.
	public void ShooterInit() {
		// All the variables are defined here.
		BALL_OPENER = new Servo(1); // Will change for actual Robot.
		SRX = new Talon(1); // Will change for the actual Robot.
		LOOP_STATUS = false; // Starts out defined as false, ball rate not limited.
		SERVO_BOOLEAN = true; // Starts out defined as true, the servo is open.
		GATE_STATUS = true; // The gate starts out as open.
		i = 0; // To be used with the for loop to limit the ball rate.
	}
	public void ShooterGate() {
		// Controls the functions of the gate.
		if(!LOOP_STATUS && GATE_STATUS) {
			SERVO_BOOLEAN = true; // The rate of fire is not limited.
			BALL_OPENER.set(0.0); // The angle will most definitely change. Also, closes the way for balls to get in.
		}
		else if(LOOP_STATUS && GATE_STATUS) {
			for(i = 0; i <= 101; i++) { // 1 integer value is equal to about 20ms.
				SERVO_BOOLEAN = false; // The rate of fire is limited
				if(i <= 50) // tick, tick.
					BALL_OPENER.set(0.0); // Opens the way for balls to get in.
				else if(i > 50 && i <= 100) // tick, tick.
					BALL_OPENER.set(90.00); // Closes the way for balls to get in.
				else if(i == 101) // default scenario
					i = 0; // the for loop resets.
			}
		}
		else if(LOOP_STATUS && !GATE_STATUS) {
			for(i = 0; i < 200; i++) { // 1 integer value is equal to about 20ms.
				SERVO_BOOLEAN = false; // The rate of fire is limited
			}
		}
		else {
			SERVO_BOOLEAN = false; // The rate of fire is limited
			BALL_OPENER.set(90.0); // Closes the way for balls to get in.
		}
	}
	public void ShooterLaunch() {
		// Controls functions of the balls at launch.
	}
	public void SpeedControl() {
		
		// Allows the rate of balls to be controlled, so the Robot is not overloaded and the balls are not continually.
	}
}