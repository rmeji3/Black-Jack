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
        return 0;
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
