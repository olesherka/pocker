package alisher.mc.kz.poker.combinations;


import alisher.mc.kz.poker.Card;

import java.util.List;

public class NullCombination extends Combination{
    public NullCombination() {
        super(null, null);
    }
    @Override
    public int getScore(){
        return 0;
    }
    @Override
    public int compareTo(Combination combination) {
        return 0;
    }
}
