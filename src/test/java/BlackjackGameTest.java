
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
public class BlackjackGameTest {

    @Test
    void testPositiveEarnings(){

        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.currentBet = 10;
        blackjackGame.totalMoney = 100;
        blackjackGame.addToPlayerHand(new Card("clubs", 10));
        blackjackGame.addToPlayerHand(new Card("clubs", 7));
        blackjackGame.addToBankerHand(new Card("clubs", 1));
        blackjackGame.addToBankerHand(new Card("clubs", 5));

        assertEquals(10, blackjackGame.evaluateWinnings(), "evaluate earnings must return double the bet");
    }

    @Test
    void testNegativeEarnings(){
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.addToBankerHand(new Card("clubs", 10));
        blackjackGame.addToBankerHand(new Card("clubs", 11));
        blackjackGame.addToPlayerHand(new Card("clubs", 11));
        blackjackGame.addToPlayerHand(new Card("clubs", 5));
        blackjackGame.currentBet = 100;
        double winnings = blackjackGame.evaluateWinnings();
        System.out.println(winnings);
        assertEquals(-100, winnings);
    }

    @Test
    void testNeutralEarnings(){
        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.addToBankerHand(new Card("clubs", 10));
        blackjackGame.addToBankerHand(new Card("clubs", 11));
        blackjackGame.addToPlayerHand(new Card("clubs", 10));
        blackjackGame.addToPlayerHand(new Card("clubs", 11));
        blackjackGame.currentBet = 100;
        double winnings = blackjackGame.evaluateWinnings();
        assertEquals(0, winnings);
    }

    @Test
    void testBlackJackEarnings(){

        BlackjackGame blackjackGame = new BlackjackGame();
        blackjackGame.currentBet = 10;
        blackjackGame.totalMoney = 100;
        blackjackGame.addToPlayerHand(new Card("clubs", 10));
        blackjackGame.addToPlayerHand(new Card("clubs", 11));
        blackjackGame.addToBankerHand(new Card("clubs", 1));
        blackjackGame.addToBankerHand(new Card("clubs", 5));

        assertEquals(15, blackjackGame.evaluateWinnings(), "evaluate earnings must return double the bet");
    }
}
