import javafx.animation.PauseTransition;
import javafx.application.Application;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;

public class BlackJack extends Application {

	static final String black = "#2D2D2D";
	static final String lightBlack = "#424242";
	static final String gold = "#82600B";

	static double totalFunds = 0;

//	static double betAmount;
	static String startingAmountString;

	static String betAmountString;
	private HashMap<String,Scene> sceneMap;
	BlackjackDealer blackjackDealer;
	BlackjackGameLogic blackjackGameLogic;
	static BlackjackGame blackjackGame;
	GameScene gameScene;
	StartScene startScene;
	PlaceBetScene placeBetScene;
	RoundOverScene roundOverScene;
	LoseScene loseScene;



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	private static boolean startButtonListener(String startingAmountString, StartScene startScene){
		if(startingAmountString.isEmpty()){
			startScene.getStartingAmount().setPromptText("Enter a valid number");
			return false;
		}
		try{
			totalFunds =  Double.parseDouble(startingAmountString);

		}catch (NumberFormatException e){
			startScene.getStartingAmount().clear();
			startScene.getStartingAmount().setPromptText("Enter a valid number");
			return false;
		}

		if (totalFunds <= 0) {
			startScene.getStartingAmount().clear();
			startScene.getStartingAmount().setPromptText("Enter a valid number");
			return false;
		}

		return true;


	}

	private static boolean startBet(String betAmountString, PlaceBetScene placeBetScene){
		if (betAmountString.isEmpty()){
			placeBetScene.getBetAmountField().setPromptText("Enter a valid number");
			return false;
		}


		try {
			blackjackGame.currentBet = Double.parseDouble(betAmountString);
		} catch (NumberFormatException e) {
			placeBetScene.getBetAmountField().clear();
			placeBetScene.getBetAmountField().setPromptText("Enter a valid number");
			return false;
		}

		if (blackjackGame.currentBet <= 0){
			placeBetScene.getBetAmountField().clear();
			placeBetScene.getBetAmountField().setPromptText("Enter a valid number");
			return false;
		}
		else if(totalFunds < 0 || totalFunds < blackjackGame.currentBet) {
			placeBetScene.getBetAmountField().clear();
			placeBetScene.getBetAmountField().setPromptText("Not enough funds");
			return false;
		}

		return true;
	}


	public void startGame(ArrayList<Card> playerDeck, ArrayList<Card> dealerDeck){

		for(Card c : playerDeck){
			gameScene.getPlayerCardHbox().getChildren().add(c.getFrontCardContainer());
		}

		Card card;
		card = blackjackDealer.drawOne();
		dealerDeck.add(card);
		card.setShow(false);
		gameScene.getCpuCardHbox().getChildren().add(card.getBackCardContainer());

		card = blackjackDealer.drawOne();
		dealerDeck.add(card);
		gameScene.getCpuCardHbox().getChildren().add(card.getFrontCardContainer());

		gameScene.setPlayerCount(Integer.toString(blackjackGameLogic.handTotal(playerDeck)));
		gameScene.setDealerCount(Integer.toString(blackjackGameLogic.handTotal(dealerDeck)));
	}

	public void dealerTurn() {
		ArrayList<Card> bankerHand = blackjackGame.getBankerHand();
		gameScene.setDealerCount(Integer.toString(blackjackGameLogic.handTotal(bankerHand)));

		while(blackjackGameLogic.evaluateBankerDraw(bankerHand)) {
			Card card = blackjackDealer.drawOne();
			blackjackGame.addToBankerHand(card);
			gameScene.getCpuCardHbox().getChildren().add(card.getFrontCardContainer());
			gameScene.setDealerCount(Integer.toString(blackjackGameLogic.handTotal(bankerHand)));
		}
	}

	public void resetGame(){
		blackjackDealer = new BlackjackDealer();
		blackjackDealer.shuffleDeck();
		blackjackGame = new BlackjackGame();
		gameScene.getPlayerCardHbox().getChildren().clear();
		gameScene.getCpuCardHbox().getChildren().clear();
		gameScene.getStandButton().setDisable(false);
		gameScene.getHitButton().setDisable(false);

	}
	public Scene checkResult(){

		double earnings = blackjackGame.evaluateWinnings();
		if(earnings > 0) {
			totalFunds += earnings + blackjackGame.currentBet;
			roundOverScene.setEarningsLabel(earnings);
			roundOverScene.setTotalFundsLabel(totalFunds);
			resetGame();
			return roundOverScene.getScene();
		}
		else if(earnings < 0) {
			roundOverScene.setEarningsLabel(earnings);
			roundOverScene.setTotalFundsLabel(totalFunds);
			if(totalFunds <= 0) {
				resetGame();
				return loseScene.getScene();

			}
			resetGame();
			return roundOverScene.getScene();
		}

		roundOverScene.setEarningsLabel(0);
		totalFunds += blackjackGame.currentBet;
		roundOverScene.setTotalFundsLabel(totalFunds);
		resetGame();
		return roundOverScene.getScene();

	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {

		blackjackDealer = new BlackjackDealer();
		blackjackGameLogic = new BlackjackGameLogic();
		blackjackGame = new BlackjackGame();
		blackjackDealer.shuffleDeck();

		primaryStage.setTitle("Black Jack");

		startScene = new StartScene();

		placeBetScene = new PlaceBetScene();

		gameScene = new GameScene();

		roundOverScene = new RoundOverScene();


		loseScene = new LoseScene();




		startingAmountString = "";
		startScene.getStartButton().setOnAction(e->{
			startingAmountString = startScene.getStartingAmount().getText();
			if(startButtonListener(startingAmountString, startScene))
			{
				placeBetScene.setTotalFundsLabel(totalFunds);
				primaryStage.setScene(placeBetScene.getScene());
			}
		});
		startScene.getStartingAmount().setOnKeyPressed(e->{
			if (e.getCode() == KeyCode.ENTER) {
				String startingAmountString = startScene.getStartingAmount().getText();
				if(startButtonListener(startingAmountString, startScene))
				{
					placeBetScene.setTotalFundsLabel(totalFunds);
					primaryStage.setScene(placeBetScene.getScene());
				}

			}
		});


		placeBetScene.getBetButton().setOnAction(e->{
			betAmountString = placeBetScene.getBetAmountField().getText();
			if(startBet(betAmountString, placeBetScene)) {
				primaryStage.setScene(gameScene.getScene());
				blackjackGame.playerHand = blackjackDealer.dealHand();
				startGame(blackjackGame.getPlayerHand(),blackjackGame.getBankerHand());
				totalFunds -= blackjackGame.currentBet;
				gameScene.setTotalFundsLabel(totalFunds);
				gameScene.setBetLabel(blackjackGame.currentBet);
			}
		});
		placeBetScene.getBetAmountField().setOnKeyPressed(e->{
			if (e.getCode() == KeyCode.ENTER) {
				betAmountString = placeBetScene.getBetAmountField().getText();
				if(startBet(betAmountString, placeBetScene)) {
					primaryStage.setScene(gameScene.getScene());
					blackjackGame.playerHand = blackjackDealer.dealHand();
					startGame(blackjackGame.getPlayerHand(),blackjackGame.getBankerHand());
					gameScene.setTotalFundsLabel(totalFunds);
					gameScene.setBetLabel(blackjackGame.currentBet);
				}
			}
		});

		gameScene.getHitButton().setOnAction(e->{
			Card card = blackjackDealer.drawOne();
			gameScene.getPlayerCardHbox().getChildren().add(card.getFrontCardContainer());
			blackjackGame.addToPlayerHand(card);
			gameScene.setPlayerCount(Integer.toString(blackjackGameLogic.handTotal(blackjackGame.getPlayerHand())));
			if (blackjackGameLogic.handTotal(blackjackGame.getPlayerHand()) > 21) {
				ArrayList<Card> p2 = blackjackGame.getBankerHand();
				Card hiddenCard = p2.get(0);
				hiddenCard.setShow(true);
				gameScene.getCpuCardHbox().getChildren().set(0, hiddenCard.getFrontCardContainer());
				gameScene.setDealerCount(Integer.toString(blackjackGameLogic.handTotal(p2)));
				gameScene.getStandButton().setDisable(true);
				gameScene.getHitButton().setDisable(true);
				PauseTransition pause = new PauseTransition(Duration.seconds(3));
				pause.setOnFinished(event -> {
					// Code to execute after the 2-second delay
					primaryStage.setScene(checkResult());
				});
				pause.play(); // Start the pause

			}
//			blackjackGameLogic.whoWon(p1,blackjackGame.getBankerHand());
		});
		gameScene.getStandButton().setOnAction(e->{
			ArrayList<Card> p2 = blackjackGame.getBankerHand();
			ArrayList<Card> p1 = blackjackGame.getPlayerHand();
			Card hiddenCard = p2.get(0);
			hiddenCard.setShow(true);
			gameScene.getCpuCardHbox().getChildren().set(0, hiddenCard.getFrontCardContainer());

			dealerTurn();
			gameScene.getStandButton().setDisable(true);
			gameScene.getHitButton().setDisable(true);
			String result = blackjackGameLogic.whoWon(p1,p2);
			PauseTransition pause = new PauseTransition(Duration.seconds(3));
			pause.setOnFinished(event -> {
				// Code to execute after the 2-second delay
				primaryStage.setScene(checkResult());
			});
			pause.play(); // Start the pause
		});

		roundOverScene.getQuitButton().setOnAction(e->{
			primaryStage.setScene(startScene.getScene());
		});
		roundOverScene.getContinueButton().setOnAction(e->{
			placeBetScene.setTotalFundsLabel(totalFunds);
			primaryStage.setScene(placeBetScene.getScene());
		});
		loseScene.getQuitButton().setOnAction(e->{
			System.exit(1);
		});
		loseScene.getPlayAgainButton().setOnAction(e->{
			primaryStage.setScene(startScene.getScene());
		});

		primaryStage.setScene(startScene.getScene());

		primaryStage.getIcons().add(new Image("/icon.png"));
		primaryStage.show();
	}
}