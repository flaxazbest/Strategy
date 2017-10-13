import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.LinkedList;

public class AnimationFlip {

    final Duration duration = Duration.millis(1000);

    private int row;
    private int col;
    private Color color;
    private double size;
    GraphicsContext gc;

    LinkedList<ParallelTransition> flips;

    public AnimationFlip(int row, int col, Color color, double size, GraphicsContext gc) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.size = size;
        this.gc = gc;

        flips = new LinkedList<ParallelTransition>();
    }

    public void play() {

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

}
