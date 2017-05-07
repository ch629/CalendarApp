package uk.ac.brighton.uni.na3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalendarApp extends Application {
	
	public static String loginScreenID    = "login";
    public static String loginScreenFXML  = "/Login.fxml";
    public static String createEventID    = "createEvent";
    public static String createEventFXML  = "/Create Event.fxml";
    public static String editEventID      = "editEvent";
    public static String editEventFXML    = "/Edit Event.fxml";
    public static String calendarViewID   = "calendarView";
    public static String calendarViewFXML = "/Calendar_View.fxml";
    
    private static Stage primaryStage;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {	
    	ScreenController mainController = new ScreenController();
    	mainController.loadScreen(loginScreenID , loginScreenFXML );
    	mainController.loadScreen(createEventID , createEventFXML );
    	mainController.loadScreen(editEventID   , editEventFXML   );
    	mainController.loadScreen(calendarViewID, calendarViewFXML);
    	
    	mainController.setScreen(loginScreenID);
    	
    	Group root = new Group();
    	root.getChildren().addAll(mainController);
    	Scene scene = new Scene(root);
    	primaryStage = stage;
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    
    /*
     * Resize the screen based on the current scene.
     */
    public static void resizeScreen(){
    	primaryStage.sizeToScene();
    	primaryStage.centerOnScreen();
    }

    //TODO: When logged in, check settings the user has specified i.e. default calendar view && color scheme. -> Would have to be done with CSS (Provide themes? or just allow color customization)
}
