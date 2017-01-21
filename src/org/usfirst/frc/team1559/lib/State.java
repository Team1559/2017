package org.usfirst.frc.team1559.lib;

import java.util.HashMap;

public class State {

	private HashMap<String, Object> vals;
	
	public State() {
		vals = new HashMap<String, Object>();
	}
	
	public void put(String key, Object value) {
		vals.put(key, value);
	}
	
	public Object get(String key) {
		return vals.get(key);
	}
}
