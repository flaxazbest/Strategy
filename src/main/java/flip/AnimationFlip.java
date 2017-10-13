package flip;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.util.LinkedList;

public class AnimationFlip {

    final Duration duration = Duration.millis(1000);

    private int row;
    private int col;
    private Color color;
    private double size;
    private Pane pane;

    LinkedList<ParallelTransition> flips;
    LinkedList<Node> chips;


    public AnimationFlip(int row, int col, Color color, double size, Pane pane) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.size = size;
        this.pane = pane;

        flips = new LinkedList<ParallelTransition>();
        chips = new LinkedList<Node>();
        for (int i=0; i<4; i++) {
            chips.add(createCard());
        }
        for (Node node: chips) {
            node.setLayoutX(col*size);
            node.setLayoutY(row*size);
        }
        pane.getChildren().addAll(chips);

        //Right move
        ParallelTransition right = new ParallelTransition();

        RotateTransition rotateRight = new RotateTransition(duration, chips.get(0));
        rotateRight.setAxis(Rotate.Y_AXIS);
        rotateRight.setFromAngle(0);
        rotateRight.setToAngle(180);
        rotateRight.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition translateRight = new TranslateTransition(duration, chips.get(0));
        translateRight.setFromX(0);
        translateRight.setToX(-size);
        translateRight.setAutoReverse(true);
        right.getChildren().addAll(rotateRight, translateRight);

        //Left move
        ParallelTransition left = new ParallelTransition();

        RotateTransition rotateLeft = new RotateTransition(duration, chips.get(1));
        rotateLeft.setAxis(Rotate.Y_AXIS);
        rotateLeft.setFromAngle(-180);
        rotateLeft.setToAngle(0);
        rotateLeft.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition translateLeft = new TranslateTransition(duration, chips.get(1));
        translateLeft.setFromX(0);
        translateLeft.setToX(size);
        translateLeft.setAutoReverse(true);
        left.getChildren().addAll(rotateLeft, translateLeft);

        //Up move
        ParallelTransition up = new ParallelTransition();

        RotateTransition rotateUp = new RotateTransition(duration, chips.get(2));
        rotateUp.setAxis(Rotate.X_AXIS);
        rotateUp.setFromAngle(0);
        rotateUp.setToAngle(-180);
        rotateUp.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition translateUp = new TranslateTransition(duration, chips.get(2));
        translateUp.setFromY(0);
        translateUp.setToY(-size);
        translateUp.setAutoReverse(true);
        up.getChildren().addAll(rotateUp, translateUp);

        //Down move
        ParallelTransition down = new ParallelTransition();

        RotateTransition rotateDown = new RotateTransition(duration, chips.get(3));
        rotateDown.setAxis(Rotate.X_AXIS);
        rotateDown.setFromAngle(0);
        rotateDown.setToAngle(180);
        rotateDown.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition translateDown = new TranslateTransition(duration, chips.get(3));
        translateDown.setFromY(0);
        translateDown.setToY(size);
        translateDown.setAutoReverse(true);
        down.getChildren().addAll(rotateDown, translateDown);

        left.setCycleCount(10);
        right.setCycleCount(10);
        up.setCycleCount(10);
        down.setCycleCount(10);

        flips.add(right);
        flips.add(left);
        flips.add(up);
        flips.add(down);
    }

    public void play() {
        for (ParallelTransition pt: flips)
            pt.play();
    }

    private Node createCard() {

        Canvas c = new Canvas(size, size);
        GraphicsContext gc = c.getGraphicsContext2D();


        Rectangle r = new Rectangle(size, size);
        //r.get




        //iv.getImage().get
        iv.setFitHeight(size);
        iv.setFitWidth(size);
        return iv;
    }

}
