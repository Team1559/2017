package org.usfirst.frc.team1559.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;

public class IMU {

	int port;
	I2C imu;

	public IMU() {
		imu = new I2C(Port.kOnboard, 0x28);
	}

	public double getAngle() {// QUICK AND DIRTY -NATE
		double angle = 0.0;
		byte[] gyroZLSB = new byte[1];
		byte[] gyroZMSB = new byte[1];

		imu.read(0x18, 1, gyroZLSB);
		imu.read(0x19, 1, gyroZMSB);
		angle = ((double) ((gyroZMSB[0] << 8) | gyroZLSB[0])) / 16;
		return angle;
	}

}
