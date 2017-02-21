package org.usfirst.frc.team1559.robot;

public class Vision {

//	private static final String HOST = "10.15.59.6";
//	private static final int PORT = 23;

	UDPClient client;
	private double angle;

	private Vision() {
		client = new UDPClient();
	}

	public void update() {
		try {
		angle = Double.parseDouble(client.receive());
		} catch(NumberFormatException e) {
			System.err.println("nothing happening here");
		}
	}

	private static Vision instance;

	public static Vision getInstance() {
		if (instance == null) {
			instance = new Vision();
		}

		return instance;
	}

	public double getAngle() {
		return angle;
	}
}
