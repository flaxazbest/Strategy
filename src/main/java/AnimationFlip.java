import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import java.util.LinkedList;

public class AnimationFlip {

    final Duration duration = Duration.millis(500);

    private int row;
    private int col;
    private Color color;
    private double size;
    private Pane pane;
    private GameField gameField;

    LinkedList<ParallelTransition> flips;
    LinkedList<Node> chips;


    public AnimationFlip(int row, int col, Color color, double size, GameField gameField) {
        this.row = row;
        this.col = col;
        this.color = color;
        this.size = size;
        this.pane = gameField.getPane();
        this.gameField = gameField;

        int countFlips = 0;

        flips = new LinkedList<ParallelTransition>();
        chips = new LinkedList<Node>();

        //Right move
        if (col < gameField.getSize()-1) {
            chips.add(createCard(color));
            chips.get(countFlips).setLayoutX(col*size);
            chips.get(countFlips).setLayoutY(row*size);

            ParallelTransition right = new ParallelTransition();

            RotateTransition rotateRight = new RotateTransition(duration, chips.get(countFlips));
            rotateRight.setAxis(Rotate.Y_AXIS);
            rotateRight.setFromAngle(-180);
            rotateRight.setToAngle(0);
            rotateRight.setInterpolator(Interpolator.EASE_OUT);

            TranslateTransition translateRight = new TranslateTransition(duration, chips.get(countFlips));
            translateRight.setFromX(0);
            translateRight.setToX(size);
            translateRight.setAutoReverse(true);
            right.getChildren().addAll(rotateRight, translateRight);
            final int finalCountFlipsRight = countFlips;
            right.setOnFinished(e -> {
                chips.get(finalCountFlipsRight).setVisible(false);
                gameField.getCell(row, col + 1).draw(40);
                gameField.getCell(row, col).draw(40);
            });
            countFlips++;
            flips.add(right);
        }

        //Left move
        if (col > 0) {
            chips.add(createCard(color));
            chips.get(countFlips).setLayoutX(col*size);
            chips.get(countFlips).setLayoutY(row*size);
            ParallelTransition left = new ParallelTransition();

            RotateTransition rotateLeft = new RotateTransition(duration, chips.get(countFlips));
            rotateLeft.setAxis(Rotate.Y_AXIS);
            rotateLeft.setFromAngle(0);
            rotateLeft.setToAngle(180);
            rotateLeft.setInterpolator(Interpolator.EASE_OUT);

            TranslateTransition translateLeft = new TranslateTransition(duration, chips.get(countFlips));
            translateLeft.setFromX(0);
            translateLeft.setToX(-size);
            translateLeft.setAutoReverse(true);
            left.getChildren().addAll(rotateLeft, translateLeft);
            final int finalCountFlipsLeft = countFlips;
            left.setOnFinished(e -> {
                chips.get(finalCountFlipsLeft).setVisible(false);
                gameField.getCell(row, col-1).draw(40);
                gameField.getCell(row, col).draw(40);
            });
            countFlips++;
            flips.add(left);
        }

        //Up move
        if (row > 0) {
            chips.add(createCard(color));
            chips.get(countFlips).setLayoutX(col*size);
            chips.get(countFlips).setLayoutY(row*size);
            ParallelTransition up = new ParallelTransition();

            RotateTransition rotateUp = new RotateTransition(duration, chips.get(countFlips));
            rotateUp.setAxis(Rotate.X_AXIS);
            rotateUp.setFromAngle(0);
            rotateUp.setToAngle(-180);
            rotateUp.setInterpolator(Interpolator.EASE_OUT);

            TranslateTransition translateUp = new TranslateTransition(duration, chips.get(countFlips));
            translateUp.setFromY(0);
            translateUp.setToY(-size);
            translateUp.setAutoReverse(true);
            up.getChildren().addAll(rotateUp, translateUp);
            final int finalCountFlipsUp = countFlips;
            up.setOnFinished(e -> {
                chips.get(finalCountFlipsUp).setVisible(false);
                gameField.getCell(row-1, col).draw(40);
                gameField.getCell(row, col).draw(40);
            });
            countFlips++;
            flips.add(up);
        }

        //Down move
        if (row < gameField.getSize()-1) {
            chips.add(createCard(color));
            chips.get(countFlips).setLayoutX(col*size);
            chips.get(countFlips).setLayoutY(row*size);
            ParallelTransition down = new ParallelTransition();

            RotateTransition rotateDown = new RotateTransition(duration, chips.get(countFlips));
            rotateDown.setAxis(Rotate.X_AXIS);
            rotateDown.setFromAngle(0);
            rotateDown.setToAngle(180);
            rotateDown.setInterpolator(Interpolator.EASE_OUT);

            TranslateTransition translateDown = new TranslateTransition(duration, chips.get(countFlips));
            translateDown.setFromY(0);
            translateDown.setToY(size);
            translateDown.setAutoReverse(true);
            down.getChildren().addAll(rotateDown, translateDown);
            final int finalCountFlipsDown = countFlips;
            down.setOnFinished(e -> {
                chips.get(finalCountFlipsDown).setVisible(false);
                gameField.getCell(row + 1, col).draw(40);
                gameField.getCell(row, col).draw(40);
            });
            countFlips++;
            flips.add(down);
        }

        pane.getChildren().addAll(chips);

/*
        left.setCycleCount(10);
        right.setCycleCount(10);
        up.setCycleCount(10);
        down.setCycleCount(10);
*/
    }

    public void play() {
        for (ParallelTransition pt: flips)
            pt.play();
//        for (Node node: chips)
//            node.setVisible(false);
    }

    private Node createCard(Color color) {

        Canvas c = new Canvas(size, size);
        //c.setEffect(new DropShadow());
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRoundRect(0, 0, size-2, size-2, 10, 10);
        gc.setFill(Color.BLACK);
        gc.fillRoundRect(15, 15, 10, 10, 10, 10);
        c.setHeight(size);
        c.setWidth(size);
        return c;
    }

}
