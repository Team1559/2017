package org.usfirst.frc.team1559.robot;

import org.usfirst.frc.team1559.lib.State;
import org.usfirst.frc.team1559.lib.Subsystem;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class Lights extends Subsystem {
	private I2C port;
	private byte[] data;

	private Lights() {
		super("Lights");
		port = new I2C(Port.kMXP, Constants.ARDUINO_ADDRESS);
		data = new byte[3];
	}

	public void setSequence(byte sequence) {
		data[0] = sequence;
	}

	public void setError(byte error) {
		data[1] = error;
	}

	public void setShooter(byte shooter) {
		data[2] = shooter;
	}

	public void write() {
		port.writeBulk(data);
	}

	@Override
	public void getState(State s) {
		// TODO Auto-generated method stub

	}
}
