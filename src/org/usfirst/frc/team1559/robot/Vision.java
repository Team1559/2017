package org.usfirst.frc.team1559.robot;

public class Vision {

	UDPClient client;
	private double angle;
	private double distance;

	private Vision() {
		client = new UDPClient();
	}

	public void update() {
		try {
			double temp = Double.parseDouble(client.getAngle());
			if (temp != -1000) {
				angle = temp;
			}
		} catch (NumberFormatException | NullPointerException e) {
			System.err.println("nothing happening here");
		}
		try {
			double tempd = Double.parseDouble(client.getDistance());
			if (tempd != -1000) {
				distance = tempd;
			}
		} catch (NumberFormatException | NullPointerException e) {
			System.err.println("rats for lunch");
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
	
	public double getDistance(){
		return distance;
	}
}
