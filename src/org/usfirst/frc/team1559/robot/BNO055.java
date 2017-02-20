package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class BNO055 {

	private double xAngle, yAngle, zAngle;
	private I2C imu;

	private BNO055() {
		imu = new I2C(Port.kOnboard, 0x28);
		imu.write(0x3D, 0x0C);
	}

	public void update() {
		byte[] buffer = new byte[6];
		imu.read(0x1A, 6, buffer);

		int x = buffer[0] | (buffer[1] << 8);
		int y = buffer[2] | (buffer[3] << 8);
		int z = buffer[4] | (buffer[5] << 8);
		xAngle = (double) x / 16.0;
		yAngle = (double) y / 16.0;
		zAngle = (double) z / 16.0;
		// byte[] gyroZLSB = new byte[1];
		// byte[] gyroZMSB = new byte[1];
		//
		// imu.read(0x18, 1, gyroZLSB);
		// imu.read(0x19, 1, gyroZMSB);
		// currentAngle = ((double) ((gyroZMSB[0] << 8) | gyroZLSB[0])) / 16;
	}

	public double getZ() {
		return zAngle;
	}
	
	public double getX() {
		return xAngle;
	}
	
	public double getY() {
		return yAngle;
	}

	private static BNO055 instance;

	public static BNO055 getInstance() {
		if (instance == null) {
			instance = new BNO055();
		}
		return instance;
	}
}
