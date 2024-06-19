import alisher.mc.kz.poker.Game;
import alisher.mc.kz.poker.Player;
import alisher.mc.kz.poker.Table;
import alisher.mc.kz.poker.combinations.DeckOfCards;
import alisher.mc.kz.poker.combinations.GameException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws GameException {
        Scanner scanner = new Scanner(System.in);
        /*DeckOfCards deck = new DeckOfCards();
        deck.shuffle();
        String check = "да";
        System.out.println(deck);
        Player player1 = new Player("mari", deck);
        Player player2 = new Player("olesherka", deck);
        Table table = new Table(deck);
        player1.setCardsOnHands(0, new Card(Card.Suit.ACE, Card.Face.CLUB));
        player2.setCardsOnHands(0, new Card(Card.Suit.ACE, Card.Face.PEAK));
        player1.setCardsOnHands(1, new Card(Card.Suit.FIVE, Card.Face.PEAK));
        player2.setCardsOnHands(1, new Card(Card.Suit.TWO, Card.Face.HEART));
        table.setCardsOnTable(table.getCardsOnTable(), 0, new Card(Card.Suit.FOUR, Card.Face.CLUB ));
        table.setCardsOnTable(table.getCardsOnTable(), 1, new Card(Card.Suit.FOUR, Card.Face.DIAMOND ));
        table.setCardsOnTable(table.getCardsOnTable(), 2, new Card(Card.Suit.FOUR, Card.Face.PEAK ));
        table.setCardsOnTable(table.getCardsOnTable(), 3, new Card(Card.Suit.TEN, Card.Face.CLUB ));
        table.setCardsOnTable(table.getCardsOnTable(), 4, new Card(Card.Suit.SIX, Card.Face.HEART ));
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(table);
        Game game = new Game (deck, player1, player2, table);
        game.computePlayersHighScore(table, player1);
        game.computePlayersHighScore(table, player2);
        System.out.println(game.findWinner());*/
        while (true){
            DeckOfCards deck = new DeckOfCards();
            deck.shuffle();
            String check = "да";
            System.out.println(deck);
            Player player1 = new Player("Ilya", deck);
            Player player2 = new Player("Never", deck);
            System.out.println(player1);
            System.out.println(player2);
            Table table = new Table(deck);

            System.out.println(table);
            Game game = new Game (deck, player1, player2, table);
            game.computePlayersHighScore(table, player1);
            game.computePlayersHighScore(table, player2);
            System.out.println(game.findWinner());
            System.out.println("Хотите еще?");
            String input = scanner.nextLine();
            while(!(input.equals("да")) && !(input.equals("нет"))){
                System.out.println("Неверная команда");
                input = scanner.nextLine();
            }
            if (input.equals("нет")){
                System.out.println("Спасибо за игру!");
                break;
            }
        }
    }
}