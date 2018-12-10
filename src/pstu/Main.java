package pstu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("encryption.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Controller controller = fxmlLoader.getController();
        Scene scene = new Scene(root, 844,220);
        controller.setScene(scene);
        primaryStage.setTitle("Encryption Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
