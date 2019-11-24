package tests;

import java.awt.Color;

import cells.*;
import simulator.*;
import gui.*;

public class TestSchelling {

    public static void main(String [] args) {
        GUISimulator gui = new GUISimulator(800, 800, Color.BLACK);
        SchellingTab tab = new SchellingTab(20,20, 3, 5, 20, 20);

        gui.setSimulable(new CellsSimulator(tab, gui));
    }

}
