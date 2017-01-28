package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.JoystickButtonEvent;
import org.usfirst.frc.team1559.lib.JoystickButtonListener;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class GearGatherer implements JoystickButtonListener {

	private static GearGatherer instance;

	public static GearGatherer getInstance() {
		if (instance == null) {
			instance = new GearGatherer();
		}
		return instance;
	}

	DigitalInput limit; // The limit switch
	DoubleSolenoid piston; // The piston that releases the gear

	public GearGatherer() {
		limit = new DigitalInput(Wiring.GEAR_IN); // instantiation
		piston = new DoubleSolenoid(Wiring.SOLENOID_DOWN, Wiring.SOLENOID_UP);// forward
																				// is
																				// down
	}

	public boolean hasGear() {
		return limit.get(); // Gets the position of the limit switch (on or off)
	}

	public void update(boolean triggered) { // Triggered is a joystick button
		if (limit.get() && triggered) { // If the gear is there and the button
										// was pressed then
			piston.set(Value.kForward); // Sets the value to push
		} else {
			piston.set(Value.kReverse); // Sets the value to pull
		}
	}

	@Override
	public void buttonPressed(JoystickButtonEvent e) {
		if (e.getButtonID() == 4) // TODO: ack
		{

		}
	}

	@Override
	public void buttonReleased(JoystickButtonEvent e) {
		if (e.getButtonID() == 4) // TODO: ack
		{

		}
	}
}
