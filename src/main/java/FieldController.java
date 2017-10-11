import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FieldController {

    @FXML
    Canvas canvas;
    @FXML
    Canvas curPlayer;

    @FXML
    TextField p1buttons;
    @FXML
    TextField p1points;

    private GameField gf;

    public void btnStart(ActionEvent actionEvent) {
        gf = new GameField(10, 2, canvas.getGraphicsContext2D());
        curPlayer.getGraphicsContext2D().setFill(gf.getCurrentPlayer().getColor());
        curPlayer.getGraphicsContext2D().fillRect(0,0, curPlayer.getWidth(), curPlayer.getHeight());
        gf.draw();
    }

    public void canvasMouseClick(MouseEvent mouseEvent) {

        int col = (int)(mouseEvent.getX() / (canvas.getWidth() / gf.getSize()));
        int row = (int)(mouseEvent.getY() / (canvas.getWidth() / gf.getSize()));

        gf.tap(row, col);
        gf.draw();

        //p1buttons.setText(row + " " + col);
        curPlayer.getGraphicsContext2D().setFill(gf.getCurrentPlayer().getColor());
        curPlayer.getGraphicsContext2D().fillRect(0,0, curPlayer.getWidth(), curPlayer.getHeight());


        Stat st = gf.getStat(gf.getCurrentPlayer());
        p1buttons.setText(String.valueOf(st.buttons));
        p1points.setText(String.valueOf(st.points));
    }
}
