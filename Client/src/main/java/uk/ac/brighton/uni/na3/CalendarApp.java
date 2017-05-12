package uk.ac.brighton.uni.na3;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CalendarApp extends Application {

    public static String loginScreenID = "login";
    public static String loginScreenFXML = "/Login.fxml";
    public static String createEventID = "createEvent";
    public static String createEventFXML = "/Create Event.fxml";
    public static String editEventID = "editEvent";
    public static String editEventFXML = "/Edit Event.fxml";
    public static String dayViewID = "dayView";
    public static String dayViewFXML = "/DayView.fxml";
    public static String calendarViewID = "calendarView";
    public static String calendarViewFXML = "/Calendar_View.fxml";

    private static Stage primaryStage, secondaryStage;

    private static ScreenController secondaryController;

    public static void main(String[] args) {
        launch(args);
    }

    /*
     * Resize the screen based on the current scene.
     */
    public static void resizeScreen() {
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

    public static void newSecondaryScene(String screenID, String stageName) {
        secondaryController.setScreen(screenID);
        secondaryStage.setTitle(stageName);
        secondaryStage.showAndWait();
    }

    public static void closeSecondaryScene() {
        secondaryStage.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public String writeValue(Object value) {
                try {
                    String map = mapper.writeValueAsString(value);
                    System.out.println(map);
                    return mapper.writeValueAsString(map);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Initialise primary screen
        ScreenController mainController = new ScreenController();
        mainController.loadScreen(loginScreenID, loginScreenFXML);
        mainController.loadScreen(calendarViewID, calendarViewFXML);
        

        mainController.setScreen(loginScreenID);

        Group root = new Group();
        root.getChildren().addAll(mainController);
        Scene scene = new Scene(root);

        primaryStage = stage;
        primaryStage.setScene(scene);

        secondaryStage = new Stage();
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryController = new ScreenController();
        secondaryController.loadScreen(createEventID, createEventFXML);
        secondaryController.loadScreen(createEventID, createEventFXML);

        root = new Group();
        root.getChildren().addAll(secondaryController);
        scene = new Scene(root);
        secondaryStage.setScene(scene);

        primaryStage.show();
    }
  
    //TODO: When logged in, check settings the user has specified i.e. default calendar view && color scheme. -> Would have to be done with CSS (Provide themes? or just allow color customization)
}
