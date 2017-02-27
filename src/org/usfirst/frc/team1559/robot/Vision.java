package org.usfirst.frc.team1559.robot;

public class Vision {

	UDPClient client;
	private double angle;

	private Vision() {
		client = new UDPClient();
	}

	public void update() {
		try {
			double temp = Double.parseDouble(client.get());
			if (temp != -1000) {
				angle = temp;
			}
		} catch (NumberFormatException | NullPointerException e) {
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
