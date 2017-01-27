package org.usfirst.frc.team1559.lib;

import java.util.Vector;

import edu.wpi.first.wpilibj.Joystick;

public class BetterJoystick {

	private int port;
	private Joystick stick;
	private Vector<JoystickButtonListener> buttonListeners = new Vector<JoystickButtonListener>();
	private Vector<JoystickAxisListener> axisListeners = new Vector<JoystickAxisListener>();
	private boolean[] buttons;
	private double[] axes;
	
	public BetterJoystick(int port) {
		this.port = port;
		stick = new Joystick(port);
		buttons = new boolean[stick.getButtonCount()];
		axes = new double[stick.getAxisCount()];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = stick.getRawButton(i);
		}
		for (int i = 0; i < axes.length; i++) {
			axes[i] = stick.getRawAxis(i);
		}
	}
	
	public void update() {
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i] != stick.getRawButton(i)) {
				if (stick.getRawButton(i)) {
					for (JoystickButtonListener l : buttonListeners) {
						l.buttonPressed(new JoystickButtonEvent(port, i));
					}
				} else {
					for (JoystickButtonListener l : buttonListeners) {
						l.buttonReleased(new JoystickButtonEvent(port, i));
					}
				}
				buttons[i] = stick.getRawButton(i);
			}
		}
		
		for (int i = 0; i < axes.length; i++) {
			if (axes[i] != stick.getRawAxis(i)) {
				for (JoystickAxisListener l : axisListeners) {
					l.axisMoved(new JoystickAxisEvent(port, i, stick.getRawAxis(i)));
				}
				axes[i] = stick.getRawAxis(i);
			}
		}
	}
	
	public void addButtonListener(JoystickButtonListener jl) {
		buttonListeners.add(jl);
	}
	
	public void addAxisListener(JoystickAxisListener al) {
		axisListeners.add(al);
	}
	
	public Joystick getStick() {
		return stick;
	}
}
