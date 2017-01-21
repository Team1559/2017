package org.usfirst.frc.team1559.lib;

public class JoystickAxisEvent {

	private int port;
	private int id;
	private double pos;
	
	public JoystickAxisEvent(int port, int id, double pos) {
		this.port = port;
		this.id = id;
		this.pos = pos;
	}
	
	public int getPort() {
		return port;
	}
	
	public int getID() {
		return id;
	}
	
	public double getPosition() {
		return pos;
	}
	
	public double getPositionPower(double pow) {
		return Math.pow(pos, pow);
	}
}
