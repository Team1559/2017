package org.usfirst.frc.team1559.lib;

public abstract class Subsystem {

	private String m_name;
	
	public Subsystem(String name) {
		m_name = name;
	}
	
	public String getName() {
		return m_name;
	}
	
	public abstract void getState(State s);
}