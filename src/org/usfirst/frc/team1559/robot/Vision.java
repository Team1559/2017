package org.usfirst.frc.team1559.robot;

public class Vision {

	private static final String HOST = "10.15.59.6";
	private static final int PORT = 22;

	UDPClient client;

	private Vision() {
		client = new UDPClient(HOST, PORT);
	}

	private static Vision instance;

	public static Vision getInstance() {
		if (instance == null) {
			instance = new Vision();
		}

		return instance;
	}
}
