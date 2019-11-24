package tests;
import java.awt.Color;

import simulator.*;
import boids.*;
import gui.*;

public class TestBoidSimulator {

	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(800, 800, Color.BLACK);
		
		Flock fishes = new Flock();
		for (int i = 0; i < 200; i++) {
			fishes.addBoid(new Fish());
		}
		
		Flock sharks = new Flock();
		for (int i = 0; i < 5; i++) {
			sharks.addBoid(new Shark());
		}
		
		BoidsSimulator simulator = new BoidsSimulator(gui, fishes, sharks);
		gui.setSimulable(simulator);
		
	}
}
