package org.usfirst.frc.team1559.lib;

public class JoystickButtonEvent {

	private int port;
	private int id;
	
	public JoystickButtonEvent(int port, int id) {
		this.port = port;
		this.id = id;
	}
	
	public int getPort() {
		return port;
	}
	
	public int getID() {
		return id;
	}
	
}
