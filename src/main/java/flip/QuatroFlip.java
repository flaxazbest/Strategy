package flip;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.LinkedList;

public class QuatroFlip extends Application{

    final static Duration duration = Duration.millis(2000);

    LinkedList<Node> flip;

    public void start(Stage primaryStage) throws Exception {

        flip = new LinkedList<Node>();
        for (int i=0; i<4; i++)
            flip.add(createCard());

        StackPane root = new StackPane();

        Scene scene = new Scene(root, 400, 400, true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());

        root.getChildren().addAll(flip);

        primaryStage.setScene(scene);
        primaryStage.show();

        ParallelTransition ptLeft = left(flip.get(0));
        ParallelTransition ptRight = right(flip.get(1));
        ParallelTransition ptUp = up(flip.get(2));
        ParallelTransition ptDown = down(flip.get(3));
        ptLeft.play();
        ptRight.play();
        ptUp.play();
        ptDown.play();
    }

    private ParallelTransition right(Node object) {
        ParallelTransition animation = new ParallelTransition(object);

        RotateTransition rotateRight = new RotateTransition(duration, object);
        rotateRight.setAxis(Rotate.Y_AXIS);
        rotateRight.setFromAngle(0);
        rotateRight.setToAngle(180);
        rotateRight.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition translateRight = new TranslateTransition(duration, object);
        translateRight.setFromX(0);
        translateRight.setToX(-100);
        translateRight.setAutoReverse(true);

        animation.getChildren().addAll(rotateRight, translateRight);
        return animation;
    }

    private ParallelTransition left(Node object) {
        ParallelTransition animation = new ParallelTransition(object);

        RotateTransition rotateLeft = new RotateTransition(duration, object);
        rotateLeft.setAxis(Rotate.Y_AXIS);
        rotateLeft.setFromAngle(0);
        rotateLeft.setToAngle(-180);
        rotateLeft.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition translateLeft = new TranslateTransition(duration, object);
        translateLeft.setFromX(0);
        translateLeft.setToX(100);
        translateLeft.setAutoReverse(true);

        animation.getChildren().addAll(rotateLeft, translateLeft);
        return animation;
    }

    private ParallelTransition up(Node object) {
        ParallelTransition animation = new ParallelTransition(object);

        RotateTransition rotateUp = new RotateTransition(duration, object);
        rotateUp.setAxis(Rotate.X_AXIS);
        rotateUp.setFromAngle(0);
        rotateUp.setToAngle(-180);
        rotateUp.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition translateUp = new TranslateTransition(duration, object);
        translateUp.setFromY(0);
        translateUp.setToY(-100);
        translateUp.setAutoReverse(true);

        animation.getChildren().addAll(rotateUp, translateUp);
        return animation;
    }

    private ParallelTransition down(Node object) {
        ParallelTransition animation = new ParallelTransition(object);

        RotateTransition rotateDown = new RotateTransition(duration, object);
        rotateDown.setAxis(Rotate.X_AXIS);
        rotateDown.setFromAngle(0);
        rotateDown.setToAngle(180);
        rotateDown.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition translateDown = new TranslateTransition(duration, object);
        translateDown.setFromY(0);
        translateDown.setToY(100);
        translateDown.setAutoReverse(true);

        animation.getChildren().addAll(rotateDown, translateDown);
        return animation;
    }

    private Node createCard() {
        ImageView iv = new ImageView(
                new Image(
                        //"http://www.ohmz.net/wp-content/uploads/2012/05/Game-of-Throne-Magic-trading-cards-2.jpg"
                        "https://st2.depositphotos.com/1178962/6202/v/950/depositphotos_62020599-stock-illustration-casino-poker-chip-vector-design.jpg"
                ));
        iv.setFitHeight(100);
        iv.setFitWidth(100);
        return iv;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
