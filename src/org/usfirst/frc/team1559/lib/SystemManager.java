package org.usfirst.frc.team1559.lib;

import java.util.Collection;
import java.util.Vector;

public class SystemManager {

	private Vector<Subsystem> systems = new Vector<Subsystem>();

	public SystemManager() {

	}

	public void add(Subsystem v) {
		systems.add(v);
	}

	public void add(Collection<Subsystem> values) {
		for (Subsystem v : values) {
			systems.add(v);
		}
	}

	public Subsystem get(String name) {
		for (Subsystem s : systems) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		new NullPointerException("Could not find subsystem: " + name).printStackTrace();
		return null;
	}
}
