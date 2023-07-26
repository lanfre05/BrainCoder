package jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {

            // Loading the GUI application attributes from the FXML
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("gui.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Retrieving and initialazing the controller with the stage instance
            Controller controller = loader.getController();
            controller.init(primaryStage);

            // Setting window attributes and let the "show" begin !
            primaryStage.setWidth(1000);
            primaryStage.setHeight(800);
            primaryStage.setTitle("BrainCoder");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}