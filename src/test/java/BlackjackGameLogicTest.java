import static org.junit.jupiter.api.Assertions.*;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class BlackjackGameLogicTest {
    private final BlackjackGameLogic gameLogic = new BlackjackGameLogic();

    @Test
    void testWhoWonBasicPlayer(){


        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("clubs", 11));
        playerHand.add(new Card("clubs", 10));
        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("clubs", 1));
        dealerHand.add(new Card("clubs", 10));
        assertEquals("player", gameLogic.whoWon(playerHand, dealerHand), "who won must return 'player' when they have 21");

    }
    @Test
    void testWhoWonBasicDealer(){


        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("clubs", 1));
        dealerHand.add(new Card("clubs", 10));
        dealerHand.add(new Card("clubs", 10));
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("clubs", 1));
        playerHand.add(new Card("clubs", 10));
        assertEquals("dealer", gameLogic.whoWon(playerHand, dealerHand), "who won must return 'dealer' when they have 21");


    }
    @Test
    void testWhoWonBlackJackPlayer(){


        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("clubs", 11));
        playerHand.add(new Card("clubs", 10));
        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("clubs", 1));
        dealerHand.add(new Card("clubs", 10));
        dealerHand.add(new Card("clubs", 10));
        assertEquals("player", gameLogic.whoWon(playerHand, dealerHand), "who won must return 'player' when they both have 21 but the player has blackjack");


    }
    @Test
    void testWhoWonBlackJackDealer(){

        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("clubs", 11));
        dealerHand.add(new Card("clubs", 10));
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("clubs", 1));
        playerHand.add(new Card("clubs", 10));
        playerHand.add(new Card("clubs", 10));
        assertEquals("dealer", gameLogic.whoWon(playerHand, dealerHand), "who won must return 'dealer' when they both have 21 but the dealer has blackjack");

    }
    @Test
    void testWhoWonClosestPlayer(){

        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("clubs", 11));
        dealerHand.add(new Card("clubs", 7));
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("clubs", 1));
        playerHand.add(new Card("clubs", 11));
        playerHand.add(new Card("clubs", 7));
        assertEquals("player", gameLogic.whoWon(playerHand, dealerHand), "who won must return 'player' when the player wins by being closest to 21");

    }

    @Test
    void testWhoWonClosestDealer(){

        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("clubs", 11));
        dealerHand.add(new Card("clubs", 7));
        dealerHand.add(new Card("clubs", 1));
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("clubs", 11));
        playerHand.add(new Card("clubs", 7));
        assertEquals("dealer", gameLogic.whoWon(playerHand, dealerHand), "who won must return 'dealer' when the dealer wins by being closest to 21");

    }

    @Test
    void testDrawBasic(){

        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("clubs", 11));
        dealerHand.add(new Card("clubs", 7));
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("clubs", 11));
        playerHand.add(new Card("clubs", 7));
        assertEquals("push", gameLogic.whoWon(playerHand, dealerHand), "who won must return 'push' when they have the same value of cards");

    }
    @Test
    void testDrawBlackJack(){

        ArrayList<Card> dealerHand = new ArrayList<>();
        dealerHand.add(new Card("clubs", 11));
        dealerHand.add(new Card("clubs", 10));
        ArrayList<Card> playerHand = new ArrayList<>();
        playerHand.add(new Card("clubs", 11));
        playerHand.add(new Card("clubs", 10));
        assertEquals("push", gameLogic.whoWon(playerHand, dealerHand), "who won must return 'push' when they both have black jack");

    }

    @Test
    void testHandTotalEmpty(){
        ArrayList<Card> hand = new ArrayList<>();
        assertEquals(0, gameLogic.handTotal(hand), "Hand total must return 0 when empty");

    }
    @Test
    void testHandTotalBasic(){
        ArrayList<Card> hand = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            hand.add(new Card("clubs", i));
        }
        int sum = 55;

        assertEquals(sum, gameLogic.handTotal(hand),"hand total must return sum of all cards");


    }

    @Test
    void testHandTotalAce(){
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("clubs", 11));
        hand.add(new Card("clubs", 11));

        assertEquals(12, gameLogic.handTotal(hand),"hand total must return appropriate sum when ace changes value");


    }

    @Test
    void testEvaluateBankerDraw(){
        ArrayList<Card> hand = new ArrayList<>();
        hand.add(new Card("clubs", 11));
        hand.add(new Card("clubs", 7));
        assertFalse(gameLogic.evaluateBankerDraw(hand));
        hand.clear();
        hand.add(new Card("clubs", 11));
        hand.add(new Card("clubs", 3));
        assertTrue(gameLogic.evaluateBankerDraw(hand));

    }

}
