package flip;

import javafx.animation.Animation;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
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

public class FlipAndGo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Node card = createCard();

        primaryStage.setScene(createScene(card));
        primaryStage.show();

        RotateTransition rotationY = new RotateTransition();
        rotationY.setAxis( Rotate.Y_AXIS );
        rotationY.setDuration( Duration.seconds( 5 ) );
        rotationY.setByAngle( 360 );
        rotationY.setNode( card );
        rotationY.setAutoReverse( true );
        rotationY.setCycleCount( Animation.INDEFINITE );


        TranslateTransition transition= new TranslateTransition();
        transition.setToX(350);
        transition.setToX(375);
        transition.setDuration(Duration.seconds( 5 ));
        transition.setNode(card);

        ParallelTransition ptransition = new ParallelTransition( card,
                rotationY, transition );

//        rotationY.play();
//        transition.play();

        ptransition.play();

    }

    private Scene createScene(Node card) {
        StackPane root = new StackPane();
        root.getChildren().addAll(card);
        Scene scene = new Scene(root, 600, 700, true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());
        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Node createCard() {
        return new ImageView(
                new Image(
                        "http://www.ohmz.net/wp-content/uploads/2012/05/Game-of-Throne-Magic-trading-cards-3.jpg"
                )
        );
    }
}
