import hawkge.chat.model.OnLineUserModel;
import hawkge.game.Game;
import hawkge.game.GameSessionInfo;
import hawkge.game.models.GameModel;
import hawkge.game.gui.GameView;

/**
 * Klasse met gegevens voor 4 op een rij.
 * @author michaelkint
 */

public class VierOpEenRij extends Game {
    
    /** Constructor roept de super op, die respectievelijk de naam, het minimaal
     aantal spelers en het maximaal aantal spelers instelt. **/
    public VierOpEenRij(){
        super("VierOpEenRij",2,3);
     }

    /** De persoon die het spel wint krijgt 10 punten, de rest 0. 
     @param place De plaats waarvoor men de score wil weten
     @return Het aantal punten voor de bijhorende plaats. **/
    @Override
    public int getScore(int place) {
        if(place == 1) return 10;
        else return 0;
    }

    /** Geef de spelregels voor het huidige spel terug. 
     Wordt in een onediteerbare textarea geplaatst, en past zich automatisch aan aan
     de inhoud. Het is echter aangeraden gebruik te maken van newlines. 
     @return De spelregels voor het gemaakte spel. **/
    @Override
    public String getRules() {
        String rules;
        rules = " English \n"
                + "\n"
                + "\n 4 in a row is usually played with 2 players. In this version"
                + "\n however, it can be played with 2 or 3 players. "
                + "\n \n The main goal of this game is that players, each making a move one "
                + "\n at a time, fill a place on the gameboard (in their own specific color). "
                + "\n The first player to reach 4 filled places in a row wins the game."
                + "\n One can achieve 4 in a row either horizontal, vertical or diagonal. "
                + "\n \n When the whole gameboard is filled and none of the players has "
                + "\n filled 4 places in a row, the game ends in a draw and noone wins."
                + "\n \n \n "
                + "Nederlands \n"
                + "\n"
                + "\n 4 op een rij wordt normaalgezien gespeeld met 2 spelers." 
                + "\n In deze versie kan het echter gespeeld worden met 2 of 3 spelers."
                + "\n \n De bedoeling is dat de spelers elk op toer een vakje op het spelbord"
                + "\n bezetten (dit in een voor elke gebruiker specifieke kleur). "
                + "\n De speler die het eerst 4 vakjes naast elkaar heeft ingenomen wint."
                + "\n 4 vakjes op een rij kunnen zowel horizontaal, verticaal als diagonaal "
                + "\n behaald worden."
                + "\n \n Indien het spelbord vol is en geen enkele speler heeft 4 vakjes"
                + "\n op een rij ingenomen, eindigt het in gelijkspel en wint niemand het spel. ";                
                
        return rules;
    }
    
    /** Geef het paneel met spelbord terug waarop het spel zal plaatsvinden. 
     @param model De gameview wordt aangemaakt op basis van het GameModel. **/
    @Override
    public GameView getGameView(GameModel model){
        return new VierOpEenRijGameView(model);
    }
    
    /** Geef een nieuw VierOpEenRijGameModel terug. **/
    @Override
    public GameModel getGameModel(OnLineUserModel onlineusermodel, int numberofplayers) {
        return new VierOpEenRijGameModel(onlineusermodel,numberofplayers,this);
    }

    /** Geef een VierOpEenRijGameModel terug, ingesteld met gekregen instellingen. **/
    @Override
    public GameModel getGameModel(OnLineUserModel onlineusermodel, GameSessionInfo info) {
        return new VierOpEenRijGameModel(onlineusermodel,this, info);
    }
}
