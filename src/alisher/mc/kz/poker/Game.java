package alisher.mc.kz.poker;

import alisher.mc.kz.poker.combinations.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    DeckOfCards deck;
    Player player1;
    Player player2;
    Table table;
    public enum Combinations{
        ROYAL_FLUSH(11),
        STRAIGHT_FLUSH(10),
        FOUR_OF_A_KIND(9),
        FULL_HOUSE(8),
        FLUSH(7),
        STRAIGHT(6),
        THREE_OF_A_KIND(5),
        TWO_PAIR(4),
        PAIR(3),
        HIGH_CARD(2);
        int score;
        Combinations(int score){
            this.score = score;
        }
        public int getScore(){
            return this.score;
        }
        public static Combinations getCombinationsScore(int score) throws GameException{
            for(Combinations value : values()){
                if(value.score == score){
                    return value;
                }
            }
            throw new GameException("данной комбинации не сущетсвует");
        }
    }
    public Game(DeckOfCards deck, Player player1, Player player2, Table table) {
        this.deck=deck;
        this.player1=player1;
        this.player2=player2;
        this.table=table;
    }

    public void computePlayersHighScore(Table table, Player player) {
        List<Card> allcards = new ArrayList<>(player.getCardsOnHands());
        allcards.addAll(table.getCardsOnTable());
        for (int bitMask = 0; bitMask < (1 << 7); bitMask++) {         // 0000001 - 00100000
            int cnt = 0;
            for (int number = 6; number >= 0; number--) {           //  0011010 - 000001
                cnt += ((bitMask >> number) & 1);
            }
            if (cnt == 5) {
                //01111
                List<Card> checkDeck = new ArrayList<>();
                for (int number = 6; number >= 0; number--) {
                    if (((bitMask >> number) & 1) == 1) {
                        checkDeck.add(allcards.get((number)));
                    }
                }
                Combination combination = findCombination(checkDeck);
                if (combination.getScore() > player.getCombination().getScore()) {
                    player.setCombination(combination);
                }
            }

        }
    }


    private Combination checkRoyalFlush(List<Card> checkDeck){
        Collections.sort(checkDeck);
        for (int i = 0; i < 5; i++) {       //флеш-рояль - 11
            if (!(checkDeck.get(i).getSuit().getScore() == i + 10 &&
                    checkDeck.get(i).getFace() == checkDeck.get(0).getFace())) {
                return new NullCombination();
            }
        }
        return new RoyalFlush(checkDeck);
    }

    private Combination checkStraightFlush(List<Card> checkDeck){
        Collections.sort(checkDeck);
        for (int i = 0; i < 4; i++) {       //стрит-флеш - 10
            if (!((checkDeck.get(i + 1).getSuit().getScore() - checkDeck.get(i).getSuit().getScore()) == 1 &&
                    checkDeck.get(i).getFace() == checkDeck.get(0).getFace())) {
                return new NullCombination();
            }
        }
        return new StraightFlush(checkDeck, checkDeck.get(4).getSuit());
    }

    private Combination checkFourOfAKind(List<Card> checkDeck){
        Collections.sort(checkDeck);
        if (!(checkDeck.get(0).getSuit().getScore() == checkDeck.get(3).getSuit().getScore() || //карэ - 9
                checkDeck.get(1).getSuit().getScore() == checkDeck.get(4).getSuit().getScore() )) {
            return new NullCombination();
        }
        return new FourOfAKind(checkDeck, checkDeck.get(2).getSuit());
    }

    private Combination checkFullHouse(List<Card> checkDeck){
        Collections.sort(checkDeck);
        if (checkDeck.get(0).getSuit().getScore() == checkDeck.get(1).getSuit().getScore() && //фулл-хаус - 8
                checkDeck.get(2).getSuit().getScore() == checkDeck.get(4).getSuit().getScore()){
            return new FullHouse(checkDeck, checkDeck.get(0).getSuit(), checkDeck.get(2).getSuit());
        }
        if(checkDeck.get(0).getSuit().getScore() == checkDeck.get(2).getSuit().getScore() &&
                checkDeck.get(3).getSuit().getScore() == checkDeck.get(4).getSuit().getScore()){
            return new FullHouse(checkDeck, checkDeck.get(3).getSuit(), checkDeck.get(0).getSuit());
        }
        return new NullCombination();
    }

    private Combination checkFlush(List<Card> checkDeck){
        Collections.sort(checkDeck);
        for (int i = 0; i < 5; i++) {       //Флеш - 7
            if (checkDeck.get(0).getFace() != checkDeck.get(i).getFace()) {
                return new NullCombination();
            }
        }
        return new Flush(checkDeck, checkDeck.get(4).getSuit());
    }

    private Combination checkStraight(List<Card> checkDeck){
        Collections.sort(checkDeck);
        if(checkDeck.get(4).getSuit().getScore() == Card.Suit.ACE.getScore() && checkDeck.get(3).getSuit().getScore() == Card.Suit.FIVE.getScore() &&
                checkDeck.get(2).getSuit().getScore() == Card.Suit.FOUR.getScore() && checkDeck.get(1).getSuit().getScore() == Card.Suit.THREE.getScore() &&
                checkDeck.get(0).getSuit().getScore() == Card.Suit.TWO.getScore() ) {
            return new Straight(checkDeck, checkDeck.get(3).getSuit());
        }
        for (int i = 0; i < 4; i++) {       //стрит - 6
            if ((checkDeck.get(i+1).getSuit().getScore() - checkDeck.get(i).getSuit().getScore())!=1) {
                return new NullCombination();
            }
        }
        return new Straight(checkDeck, checkDeck.get(4).getSuit());

    }

    private Combination checkThreeOfAKind(List<Card> checkDeck){
        Collections.sort(checkDeck);
        for (int i = 2; i < checkDeck.size(); i++){
            if ((checkDeck.get(i).getSuit().getScore() == checkDeck.get(i-1).getSuit().getScore())&&
                    checkDeck.get(i).getSuit().getScore() == checkDeck.get(i-2).getSuit().getScore()){
                return new ThreeOfAKind(checkDeck, checkDeck.get(i).getSuit());
            }
        }
        return new NullCombination();
    }

    private Combination checkTwoPair(List<Card> checkDeck){
        Collections.sort(checkDeck);
        int cntScore = 0;
        int index1 = -1;
        int index2 = -1;
        for (int i = 1; i < checkDeck.size(); i++){
            if (checkDeck.get(i).getSuit().getScore() == checkDeck.get(i-1).getSuit().getScore()){
                cntScore+=1;
                if (index1==-1){
                    index1 = i;
                }
                else{
                    index2 = i;
                }
            }
        }
        if (cntScore == 2){
            return new TwoPairs(checkDeck, checkDeck.get(index1).getSuit(), checkDeck.get(index2).getSuit());
        }
        return new NullCombination();
    }

    private Combination checkPair(List<Card> checkDeck){
        Collections.sort(checkDeck);
        for (int i = 1; i < 5; i++) {       //пара - 3
            if (checkDeck.get(i).getSuit().getScore() == checkDeck.get(i-1).getSuit().getScore()){
                return new PairCard(checkDeck, checkDeck.get(i).getSuit());
            }
        }
        return new NullCombination();
    }
    private Combination checkHighCard(List<Card> checkDeck){
        Collections.sort(checkDeck);
        return new HighCard(checkDeck);
    }

    private Combination findCombination(List<Card> checkDeck) {
        Collections.sort(checkDeck);
        if ((checkRoyalFlush(checkDeck).getScore())!= 0){
            return checkRoyalFlush(checkDeck);
        }
        if ((checkStraightFlush(checkDeck).getScore())!= 0){
            return checkStraightFlush(checkDeck);
        }
        if ((checkFourOfAKind(checkDeck).getScore())!= 0){
            return checkFourOfAKind(checkDeck);
        }
        if ((checkFullHouse(checkDeck).getScore())!= 0){
            return checkFullHouse(checkDeck);
        }
        if ((checkFlush(checkDeck).getScore())!= 0){
            return checkFlush(checkDeck);
        }
        if ((checkStraight(checkDeck).getScore())!= 0){
            return checkStraight(checkDeck);
        }
        if ((checkThreeOfAKind(checkDeck).getScore())!= 0){
            return checkThreeOfAKind(checkDeck);
        }
        if ((checkTwoPair(checkDeck)).getScore() != 0){
            return checkTwoPair(checkDeck);
        }
        if (checkPair(checkDeck).getScore()!= 0){
            return checkPair(checkDeck);
        }
        return checkHighCard(checkDeck);
    }

    public String findWinner(){
        System.out.println(player1.getPlayerName() + " " + player1.combination.getCombinationsName());
        System.out.println(player1.combination.getCheckdeck());
        System.out.println(player2.getPlayerName() + " " + player2.combination.getCombinationsName());
        System.out.println(player2.combination.getCheckdeck());
        System.out.println("Итоги партии: ");
        if(player1.getCombination().getScore() > player2.getCombination().getScore()){
            return player1.getPlayerName() + " " + player1.combination.getCombinationsName();
        }
        if(player1.getCombination().getScore() < player2.getCombination().getScore()){
            return player2.getPlayerName() + " " + player2.combination.getCombinationsName();
        }
        if(player1.getCombination().getScore() == player2.getCombination().getScore()){
            int resultAfterCompare = player1.combination.compareTo(player2.getCombination());
            if(resultAfterCompare == 1){
                return player1.getPlayerName() + " " + player1.combination.getCombinationsName() ;
            }
            if(resultAfterCompare == 2){
                return player2.getPlayerName() + " " + player2.combination.getCombinationsName();
            }
            if(resultAfterCompare == 0){
                return "Draw";
            }
        }
        return "______________________________";
    }
}


