package org.usfirst.frc.team1559.auto;

import java.util.Vector;

public class AutoRoutine {

	Vector<AutoAction> actions;

	int count = 0;
	
	public AutoRoutine() {
		actions = new Vector<AutoAction>();
		count = 0;
	}
	
	public void put(AutoAction auto) {
		actions.add(auto);
	}
	
	public void run() {
		if (actions.get(count).init()) {
			if (!actions.get(count).update()) {
				actions.get(count).update();
			} else {
				actions.get(count).stop();
			}
		}
		
		count++;
	}
}
