package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class IMU {

	private double currentAngle;
	private I2C imu;

	private IMU() {
		imu = new I2C(Port.kOnboard, 0x28);
	}

	public void update() {
		byte[] gyroZLSB = new byte[1];
		byte[] gyroZMSB = new byte[1];

		imu.read(0x18, 1, gyroZLSB);
		imu.read(0x19, 1, gyroZMSB);
		currentAngle = ((double) ((gyroZMSB[0] << 8) | gyroZLSB[0])) / 16;
	}

	public double getAngle() {
		return currentAngle;
	}

	private static IMU instance;

	public static IMU getInstance() {
		if (instance == null) {
			instance = new IMU();
		}
		return instance;
	}
}
