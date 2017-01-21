package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class GearGatherer {
	DigitalInput limit;
	DoubleSolenoid wow;
	
	public GearGatherer(){
		limit = new DigitalInput(Wiring.GEAR_IN);
		wow = new DoubleSolenoid(Wiring.SOLENOID_DOWN,Wiring.SOLENOID_UP);//forward is down
	}
	
	public boolean hasGear(){
		return limit.get();
	}
	
	public void update(boolean triggered){
		if(limit.get() && triggered){
			wow.set(Value.kForward);
		}
		else{
			wow.set(Value.kReverse);
		}
	}
}
