package uk.ac.brighton.uni.na3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CalendarApp extends Application {
    public static String loginScreenID = "login";
    public static String loginScreenFXML = "/Login.fxml";
    public static String createAccountID = "createAccount";
    public static String createAccountFXML = "/CreateAccount.fxml";
    public static String createEventID = "createEvent";
    public static String createEventFXML = "/Create Event.fxml";
    public static String editEventID = "editEvent";
    public static String editEventFXML = "/EditEventV2.fxml";
    public static String dayViewID = "dayView";
    public static String dayViewFXML = "/DayView.fxml";
    
    public static String monthViewID = "monthView";
    public static String monthViewFXML = "/MonthView.fxml";

    private static Stage primaryStage, secondaryStage;

    private static ScreenController mainController, secondaryController;

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

    public static void setupUnirest() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();

            @Override
            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (IOException ignored) {
                }
                return null;
            }

            @Override
            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException ignored) {
                }
                return null;
            }
        });
    }

    public static void postLoginLoad() {
        //mainController.loadScreen(CalendarApp.dayViewID, CalendarApp.dayViewFXML);
        mainController.loadScreen(CalendarApp.monthViewID, CalendarApp.monthViewFXML);
        secondaryController.loadScreen(createEventID, createEventFXML);
        secondaryController.loadScreen(editEventID, editEventFXML);
    }

    @Override
    public void start(Stage stage) throws Exception {
        setupUnirest();
        //Initialise primary screen
        mainController = new ScreenController();
        mainController.loadScreen(loginScreenID, loginScreenFXML);
        mainController.loadScreen(createAccountID, createAccountFXML);
        mainController.setScreen(loginScreenID);


        Group root = new Group();
        root.getChildren().addAll(mainController);
        Scene scene = new Scene(root);

        primaryStage = stage;
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        secondaryStage = new Stage();
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.setResizable(false);
        secondaryController = new ScreenController();

        root = new Group();
        root.getChildren().addAll(secondaryController);
        scene = new Scene(root);
        secondaryStage.setScene(scene);

        primaryStage.show();
    }
    
    public static ScreenController getMainController(){
    	return mainController;
    }
}