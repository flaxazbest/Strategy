import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GameField {

    private int size;
    private int playerCount;
    private Player[] players = null;
    private int currentPlayer = 0;
    private Cell[][] field = null;
    private GraphicsContext gc;
    private Pane pane;

    public GameField(int size, int playerCount, GraphicsContext graphicsContext, Pane pane) {
        this.gc = graphicsContext;
        this.playerCount = playerCount;
        this.size = size;
        this.pane = pane;
        players = new Player[playerCount];
        field = new Cell[size][size];
        for (int i=0; i<size; i++)
            for (int j=0; j<size; j++) {
                field[i][j] = new Cell(this, null, 0, i, j);
        }

        players[0] = new Player(Color.BLUEVIOLET);
        players[1] = new Player(Color.GREENYELLOW);

        field[1][size-2].setOwner(players[0]);
        field[1][size-2].setPrice(3);

        field[size-2][1].setOwner(players[1]);
        field[size-2][1].setPrice(3);

        draw();
    }

    public Cell getCell(int row, int col) {
        return field[row][col];
    }

    public int getSize() {
        return size;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void draw() {
        for (int i=0; i<size; i++)
            for (int j=0; j<size; j++) {
                field[i][j].draw(40);
            }
    }

    public void tap(int row, int col) {
        if (field[row][col].getOwner() != null && field[row][col].getOwner().equals(players[currentPlayer])) {
            field[row][col].tap(players[currentPlayer]);
            currentPlayer = (currentPlayer+1) % playerCount;
        }
    }

    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public Stat getStat(Player p) {
        Stat stat = new Stat();
        for (int i=0; i<size; i++)
            for (int j=0; j<size; j++)
                if (field[i][j].getOwner() != null && field[i][j].getOwner().equals(p)) {
                    stat.buttons++;
                    stat.points += field[i][j].getPrice();
                }
        return stat;
    }

    public Pane getPane() {
        return pane;
    }
}
