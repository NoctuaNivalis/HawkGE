import hawkge.chat.model.OnLineUserModel;
import hawkge.game.Game;
import hawkge.game.GameSessionInfo;
import hawkge.game.models.GameModel;
import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

/**
 *
 * @author michaelkint
 */
public class VierOpEenRijGameModel extends GameModel {

    private int[][] spelbord;
    private int total;

    public VierOpEenRijGameModel(OnLineUserModel model, int minusers, Game g) {
        super(model, g, minusers);
        spelbord = new int[8][8];
        total = 0;
    }
    
    public VierOpEenRijGameModel(OnLineUserModel model, Game g, GameSessionInfo info){
        super(info, g, model);  
        spelbord = new int[8][8];
        total = 0;
    }

    @Override
    public void receive(Serializable data) {
        Point p = (Point) data;
        fill((int) p.getX(), (int) p.getY());
    }
    
    public void fill(Point p){ 
        fill((int)p.getX(),(int)p.getY());
    }

    public void fill(int i, int j) {
        spelbord[i][j] = getActiveUserIndex() + 1; // Zodat user 0 nr 1 krijgt ( 0 = leeg)
        
        fireStateChanged(); // herteken
        total++;
        if (checkIfWon(spelbord[i][j])){ 
            stopFinishedGame();
        } else if(total == getWidth() * getHeight()){
            stopDrawedGame();
        } else {
            nextUsersTurn();
        }
        fireStateChanged();
    }

    public boolean checkIfWon(int checknumber) {
        return checkHorizontal(checknumber) || checkVertical(checknumber) || checkDiagonal(checknumber);
    }

    public boolean checkHorizontal(int checknumber) {
        for (int i = 0; i < getHeight(); i++) {
            int k = 0;
            for (int j = 0; j < getWidth(); j++) {
                if (spelbord[i][j] == checknumber) {
                    k++;
                } else {
                    k = 0;
                }
                if (k == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkVertical(int checknumber) {
        for (int i = 0; i < getWidth(); i++) {
            int k = 0;
            for (int j = 0; j < getHeight(); j++) {
                if (spelbord[j][i] == checknumber) {
                    k++;
                } else {
                    k = 0;
                }
                if (k == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDiagonal(int checknumber) {
        int i = 4;
        while (i >= 0) {
            int k = 0;
            int l = 0;
            for (int m = 0; m < getWidth() - i; m++) {
                if (spelbord[i + m][l + m] == checknumber) {
                    k++;
                } else {
                    k = 0;
                }
                if (k == 4) {
                    return true;
                }
            }
            i--;
        }

        i = 0;
        while (i <= 5) {
            int k = 0;
            int l = 0;
            for (int m = 0; m < getHeight() - i; m++) {
                if (spelbord[l + m][i + m] == checknumber) {
                    k++;
                } else {
                    k = 0;
                }
                if (k == 4) {
                    return true;
                }
            }
            i++;
        }

        i = 3;
        while (i <= 7) {
            int k = 0;
            int l = 0;
            for (int m = 0; m <= i; m++) {
                if (spelbord[i - m][l + m] == checknumber) {
                    k++;
                } else {
                    k = 0;
                }
                if (k == 4) {
                    return true;
                }
            }
            i++;
        }

        i = 4;
        while (i >= 1) {
            int k = 0;
            int l = getHeight() - 1;
            for (int m = 0; m < getWidth() - i; m++) {
                if (spelbord[l - m][i + m] == checknumber) {
                    k++;
                } else {
                    k = 0;
                }
                if (k == 4) {
                    return true;
                }
            }
            i--;
        }
        
        return false;
    }

    public boolean getPos(int i, int j) {
        return spelbord[i][j] != 0;
    }

    public int getPosNumber(int i, int j) {
        return spelbord[i][j];
    }

    public boolean getPos(Point p) {
        return getPos((int) p.getX(), (int) p.getY());
    }

    public int getWidth() {
        return 8;
    }

    public int getHeight() {
        return 8;
    }

    public Color getPlayerColor(int number) {
        Color c = null;
        switch (number) {
            case 1:
                c = Color.RED;
                break;
            case 2:
                c = Color.BLUE;
                break;
            case 3:
                c = Color.GREEN;
                break;
        }
        return c;
    }
}
