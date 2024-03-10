import java.util.ArrayList;

public class BlackjackGame {
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;
    BlackjackDealer theDealer;
    BlackjackGameLogic gameLogic;
    double currentBet;
    double totalMoney;
    public BlackjackGame(){
        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();
    }
    public double evaluteWinnings() {
        if (gameLogic.whoWon(playerHand, bankerHand).compareTo("player")== 0) {
            boolean playerNatural = gameLogic.handTotal(playerHand) == 21 && playerHand.size() == 2;

            if (playerNatural) {
                totalMoney += currentBet * 1.5;
                return currentBet * 1.5;
            }
            totalMoney += currentBet;
            return currentBet;
        } else if (gameLogic.whoWon(playerHand, bankerHand).compareTo("banker") == 0) {
            totalMoney -= currentBet;
            return -currentBet;
        } else {
            return 0;
        }
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }
    public ArrayList<Card> getBankerHand() {
        return bankerHand;
    }
    public void addToPlayerHand(Card card){
        playerHand.add(card);
    }
    public void addToBankerHand(Card card){
         bankerHand.add(card);
    }
}
