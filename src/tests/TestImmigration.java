package tests;
import java.awt.Color;

import cells.*;
import gui.*;
import simulator.*;

public class TestImmigration {
    public static void main(String [] args) {
        GUISimulator gui = new GUISimulator(800, 800, Color.BLACK);
        ImmigrationTab tab = new ImmigrationTab(50, 50, 3, 10);

        gui.setSimulable(new CellsSimulator(tab, gui));
    }
}
