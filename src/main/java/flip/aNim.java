package flip;

import javafx.application.Application;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class aNim extends Application {
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 400, 400, true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());

        primaryStage.setScene(scene);

        AnimationFlip af = new AnimationFlip(4,3, Color.BEIGE, 40, root);
        primaryStage.show();
        af.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
