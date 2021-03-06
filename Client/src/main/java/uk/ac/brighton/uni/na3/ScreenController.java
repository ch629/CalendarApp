package uk.ac.brighton.uni.na3;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;

public class ScreenController extends AnchorPane {
    private HashMap<String, Pair<Node,ControlledView>> screens = new HashMap<>();

    /**
     * Get a screen from map.
     * @param name The name of the screen
     * @return The screen from the map.
     */
    public Pair<Node,ControlledView> getScreen(String name) {
        return screens.get(name);
    }

    /**
     * Add a screen to the map
     * @param name The name of the screen
     * @param screen The screen
     */
    public void addScreen(String name, Pair<Node,ControlledView> pair ) {
        screens.put(name, pair);
    }

    /**
     * Load a screen, place it in hashmap
     * @param name The name of the screen
     * @param res The screen resource
     * @return Whether it was successfully loaded
     */
    public boolean loadScreen(String name, String res) {
        try {
            FXMLLoader screenLoader = new FXMLLoader(getClass().getResource(res));
            Parent loadedScreen = screenLoader.load();
            ControlledView controlledView = screenLoader.getController();
            controlledView.setParent(this);
            addScreen(name, new Pair<Node, ControlledView>(loadedScreen,controlledView));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Sets the current screens scene
     * @param name The screen
     * @return Whether it was loaded successfully
     */
    public boolean setScreen(String name) {
        if (screens.get(name) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, screens.get(name).getKey());
            } else {
                getChildren().add(screens.get(name).getKey());
            }
            return true;
        } else {
            System.out.println("Screen not loaded");
            return false;
        }
    }

    /**
     * Delete a screen from the map
     * @param name The name of the screen
     * @return Whether it was deleted
     */
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen was not in hashmap");
            return false;
        } else {
            return true;
        }
    }
}
