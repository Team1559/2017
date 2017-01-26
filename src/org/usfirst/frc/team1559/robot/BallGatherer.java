package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;


public class BallGatherer {
	
	private static BallGatherer instance;
	public static BallGatherer getInstance() {
		if (instance == null) {
			instance = new BallGatherer();
		}
		return instance;
	}

	DoubleSolenoid piston;
	Talon belt;
	Joystick joy;
	
	public BallGatherer(){
		piston = new DoubleSolenoid(Wiring.SOLENOID_DOWN,Wiring.SOLENOID_UP);//forward is down
		joy = new Joystick(Wiring.JOYSTICK0);
	}
    
	public void pickUpBall(){
		if(joy.getRawButton(1)){
			belt.setSpeed(0.7);
		}
		else if(joy.getRawButton(2)){
			belt.setSpeed(-0.7);
		} else {
			belt.set(0);
		}
	}
}
