package alisher.mc.kz.poker.combinations;

import alisher.mc.kz.poker.Card;
import alisher.mc.kz.poker.Game;

import java.util.ArrayList;
import java.util.List;

public abstract class Combination {
    protected List<Card> checkdeck;
    private Game.Combinations combinationsName;
    public Combination (List<Card>checkdeck, Game.Combinations combinationsName){
        this.checkdeck = checkdeck;
        this.combinationsName = combinationsName;
    }

    public List<Card> getCheckdeck() {
        return checkdeck;
    }

    public int getScore(){
        return this.combinationsName.getScore();
    }
    public String getCombinationsName(){
        return this.combinationsName.name();
    }
    abstract public int compareTo(Combination combination);

}