package org.usfirst.frc.team1559.robot;
//                        :-)

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Diagnostics {

	public SmartDashboard x;
	
	public Diagnostics() //the constructor
	{
		x = new SmartDashboard();
	}
//------------------------------------------------------------------------------------------------------------------------------------
//       --Call these methods to test various things--
	
	//Encoders
	public void encoderData(Encoder e) //call this to output encoder data (distance)
	{
		String key = "Encoder Distance: ";
		SmartDashboard.putString(key, (e.getDistance() + ""));				
	}
	//-----------------------------------------------------------------------------------------
	//Servos
	public void servoData(Servo s) //call to output servo data (angle)
	{
		String key = "Servo Angle: ";
		SmartDashboard.putString(key, (s.getAngle() + ""));				
	}
	
	public void testServoData(Servo s) //will tell you if the servo is working
	{
			double num1 = s.getAngle(); //moves the servo and sees if it actually moves
			double num2;
			if(num1 == 0)
			{
				s.setAngle(5);
				num2 = s.getAngle();
			}
			else
			{
				s.setAngle(0);
				num2 = s.getAngle();
			}
			boolean check = (num1 != num2);
			String key = "Servo is working: ";
			SmartDashboard.putString(key, (check + ""));	
	}
	//-----------------------------------------------------------------------------------------
	//Gyros
	public void gyroData(Gyro g) //outputs gyro data (angle)
	{
		String key = "Gyro Angle: ";
		SmartDashboard.putString(key, (g.getAngle() + ""));				
	}
	//-----------------------------------------------------------------------------------------
	//Solenoids
	public void solenoidData(Solenoid s) //outputs solenoid data (on or off)
	{
		String key = "Solenoid Status: ";
		SmartDashboard.putString(key, (s.get() + ""));				
	}
	
	public void testSolenoidData(Solenoid s) //tests the solenoid
	{
		boolean state1 = s.get(); //turns on the solenoid and sees if it actually turned on
		s.set(!state1);
		boolean state2 = s.get();
		s.set(state1);
		String key = "Solenoid is working: ";
		SmartDashboard.putString(key, ((state1 != state2) + ""));	
	}
	//-----------------------------------------------------------------------------------------
	//Digital inputs
	public void digitalInputData(DigitalInput d)  //outputs the value from the digital input channel (true or false)
	{
		String key = "DigitalInput Status: ";
		SmartDashboard.putString(key, (d.get() + ""));				
	}
	
}
