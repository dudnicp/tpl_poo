package tests;
import gui.*;
import simulator.BallsSimulator;
import balls.*;

import java.awt.Point;
import java.awt.Color;

public class TestBallsSimulator {
        public static void main(String [] args) {
              GUISimulator gui = new GUISimulator(700, 500, Color.BLACK);

              Point[] pos = new Point[3];

              for (int i = 0; i < 3; i++) {
                    pos[i] = new Point(250, 100*(i+1));
              }
              
              Balls balls = new Balls(pos);
              gui.setSimulable(new BallsSimulator(balls, gui));
        }
}
