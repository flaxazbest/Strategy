import javafx.scene.paint.Color;

public class Cell {

    final static double arcRound = 10.0;
    private GameField gameField;
    private Player owner;
    private int price;
    private int row;
    private int col;

    public Cell(GameField gameField, Player owner, int price, int row, int col) {
        this.gameField = gameField;
        this.owner = owner;
        this.price = price;
        this.row = row;
        this.col = col;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void tap(Player p) {
        price++;
        if (price > 3) {
            price = 0;
            if (row > 0) {
                gameField.getCell(row-1,col).tap(p);
                gameField.getCell(row-1,col).setOwner(p);
            }
            if (row < gameField.getSize()-1) {
                gameField.getCell(row+1,col).tap(p);
                gameField.getCell(row+1,col).setOwner(p);
            }
            if (col > 0) {
                gameField.getCell(row,col-1).tap(p);
                gameField.getCell(row,col-1).setOwner(p);
            }
            if (col < gameField.getSize()-1) {
                gameField.getCell(row,col+1).tap(p);
                gameField.getCell(row,col+1).setOwner(p);
            }
            owner = null;
        }
    }

    public void draw(int size) {
        if (owner == null || price == 0) {
            gameField.getGc().setFill(Color.GRAY);
            gameField.getGc().fillRoundRect(col * size, row * size, size-2, size-2, arcRound, arcRound);
        }
        else {
            gameField.getGc().setFill(owner.getColor());
            gameField.getGc().fillRoundRect(col * size, row * size, size-2, size-2, arcRound, arcRound);
            gameField.getGc().setStroke(Color.WHITE);
            gameField.getGc().strokeText(String.valueOf(price), col*size+15, row*size+25);
        }
    }
}
