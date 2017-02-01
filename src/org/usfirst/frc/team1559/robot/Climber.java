package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Climber {
	Talon spin; // motor that will spin the climber bar
	Joystick joyboy; // JOYSTICK
	
	public Climber(){
		joyboy = new Joystick(Wiring.JOYSTICK0); // DA JOYSTICK
	}
    
	public void climbOnUp(){
		if(joyboy.getRawButton(1)){ // if button pressed motor will activate
			spin.setSpeed(0.7); // speed of motor
		} else { // if button not pressed motor no activate
			spin.set(0); // no speed of motor because motor lazy
		}
	}
	
}

                  