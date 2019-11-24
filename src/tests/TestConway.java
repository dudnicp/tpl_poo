package tests;
import java.awt.Color;

import cells.*;
import gui.*;
import simulator.*;

public class TestConway {
    public static void main(String [] args) {
        GUISimulator gui = new GUISimulator(800, 800, Color.BLACK);
        ConwayTab tab = new ConwayTab(50, 50, 10);

        gui.setSimulable(new CellsSimulator(tab, gui));
    }
}
