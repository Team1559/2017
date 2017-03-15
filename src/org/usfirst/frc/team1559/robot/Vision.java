package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class Vision {

	private boolean connected;

	UDPClient client;
	private double angle;
	private double distance;

	private Vision() {
		client = new UDPClient();
		connected = true;
	}

	public void update() {
		try {
			String in = client.get();
			if (in.equals("c" + DriverStation.getInstance().getAlliance().toString())) {
				connected = true;
			} else {
				if (in.indexOf("d") >= 0) {
					double temp0 = Double.parseDouble(in.substring(0, in.indexOf("d")));
					if (temp0 != -1000) {
						angle = temp0;
					}
					double temp1 = Double.parseDouble(in.substring(in.indexOf("d") + 1));
					if (temp1 != -1000) {
						distance = temp1;
					}
				} else {
					double temp = Double.parseDouble(in);
					if (temp != -1000) {
						angle = temp;
					}
				}
			}
		} catch (NumberFormatException | NullPointerException e) {
			System.err.println("nothing happening here");
		}
	}

	public void checkConnection() {
		client.send(DriverStation.getInstance().getAlliance().toString());
	}

	public double getAngle() {
		return angle;
	}

	public double getDistance() {
		return distance;
	}

	public String getRaw() {
		return client.get();
	}

	public boolean isConnected() {
		return connected;
	}

	private static Vision instance;

	public static Vision getInstance() {
		if (instance == null) {
			instance = new Vision();
		}

		return instance;
	}
}
