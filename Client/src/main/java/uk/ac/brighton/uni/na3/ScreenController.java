package uk.ac.brighton.uni.na3;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ScreenController extends AnchorPane {

	private HashMap<String, Node> screens = new HashMap<String, Node>();
	
	
	/*
	 * Get a screen from map.
	 */
	public Node getScreen(String name){
		return screens.get(name);
	}
	
	/*
	 * add a screen to the map
	 */
	public void addScreen(String name, Node screen){
		screens.put(name, screen);
	}
	
	/*
	 *  Load a screen, place it in hashmap.
	 */
	public boolean loadScreen(String name, String res){
		try {
			FXMLLoader screenLoader = new FXMLLoader(getClass().getResource(res));
			Parent loadedScreen = (Parent) screenLoader.load();
			ControlledView controlledView = ( (ControlledView) screenLoader.getController() );
			controlledView.setParent(this);
			addScreen(name, loadedScreen);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Set the current screens scene
	 */
	public boolean setScreen(String name){
		if(screens.get(name) != null){			
			if(!getChildren().isEmpty()){
				getChildren().remove(0);
				getChildren().add(0, screens.get(name));
			} else {
				getChildren().add(screens.get(name));
			}
			return true;
		} else {
			System.out.println("Screen not loaded");
			return false;
		}
	}
	
	/*
	 * delete screen from hashmap
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
