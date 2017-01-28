package org.usfirst.frc.team1559.lib;

import java.util.HashMap;

public class SystemManager {

	private HashMap<Subsystem, State> systems;

	public SystemManager() {
		systems = new HashMap<Subsystem, State>();
	}

	public void add(Subsystem v) {
		systems.put(v, new State());
	}

	public void add(Subsystem... values) {
		for (Subsystem v : values) {
			systems.put(v, new State());
		}
	}

	public Subsystem get(String name) {
		for (Subsystem s : systems.keySet()) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		new NullPointerException("Could not find subsystem: " + name).printStackTrace();
		return null;
	}
	
	public State getState(String name) {
		Subsystem s = get(name);
		s.getState(systems.get(s));
		return systems.get(s);
	}
}
