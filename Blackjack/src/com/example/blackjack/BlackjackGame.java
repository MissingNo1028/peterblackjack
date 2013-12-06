package com.example.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class BlackjackGame extends Activity {
	
	
	ArrayList<Card> cardDeck = new ArrayList<Card>();
	int cardCounter = 0;
	int playerValue = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);
        
    	ArrayList<Card> playerHand = new ArrayList<Card>();
    	ArrayList<Card> dealerHand = new ArrayList<Card>();
        //Create a New Deck
        for(int i = 1; i < 4; i++){
        	for(int j = 1; j < 13; j++){
			
        		Card newCard = new Card(i,j);
        		cardDeck.add(newCard);
        	}
        }
        
        //Shuffle Deck
        long seed = System.nanoTime();
        Collections.shuffle(cardDeck, new Random(seed));

        //Deal Initial Cards
        playerHand = drawCard(playerHand);
        playerHand = drawCard(playerHand);
        dealerHand = drawCard(dealerHand);
        dealerHand = drawCard(dealerHand);
        
	}
	
	
	//Draw a Card
	public ArrayList<Card> drawCard(ArrayList<Card> hand){
		
		hand.add(cardDeck.get(cardCounter));
		return hand;
	}
	
	//Reveal Top Card
	public void revealCard(ArrayList<Card> hand){
		
		
	}
	
	//Check for 21. Return 0 if still playing, 1 if win, -1 if loss.
	public int winCheck(ArrayList<Card> hand){
		int totalValue=0;
		for(int i = 0; i < hand.size(); i++){
			totalValue= totalValue + hand.get(i).getValue();
		}
	
		if (totalValue == 21)
			return 1;
		else if (totalValue > 21)
			return -1;
		else
			return 0;
	}
	
	//Player Turn
	public void playerTurn(ArrayList<Card> hand){
		
		int totalValue=0;
		for(int i = 0; i < hand.size(); i++){
			totalValue= totalValue + hand.get(i).getValue();
		}
		playerValue = totalValue;
		//Check for 21
		int game = winCheck(hand);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setPositiveButton("Play again", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				//Restart activity
			}
		});
		builder.setNegativeButton("Quit", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				//Restart activity
			}
		});
		
		if(game == 1)
		{
			builder.setMessage("Congratulations! Blackjack!");
			builder.setTitle("Winner!");
			AlertDialog dialog = builder.create();
			
			//Create alert saying they won, button to restart game
		}
		else if(game == -1)
		{
			builder.setMessage("Bust!");
			builder.setTitle("Loss!");
			AlertDialog dialog = builder.create();
			//Create alert saying they loss, button to restart game 
		}
		else
		{
			
			AlertDialog.Builder gameBuilder = new AlertDialog.Builder(this);
			gameBuilder.setPositiveButton("Hit", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id) {
					//Draw another card -- FIGURE OUT HOW TO GET HAND TO WORK IN A CLICK LISTENER
					//drawCard(hand);
				}
			});
			gameBuilder.setNegativeButton("Stay", new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int id) {
					//Don't draw another card
				}
			});
			gameBuilder.setMessage("Your current total is " + totalValue + ", do you wish to hit or stay?");
			gameBuilder.setTitle("Your Turn");
			AlertDialog dialog = gameBuilder.create();
			//Create alert asking if they want to draw or stay. Go to Dealer's turn.
		}
		
	
	}
	
	//Dealer Turn 
	public void dealerTurn(ArrayList<Card> hand){
		int cpu = winCheck(hand);
		int totalValue=0;
		for(int i = 0; i < hand.size(); i++){
			totalValue= totalValue + hand.get(i).getValue();
		}
		boolean over = false;
		while(over == false){
		if(cpu == -1){
			//Win Alert
		}
		else if(cpu == 1){
			//Loss Alert
		}
		else{
			//Draw a card if risk is acceptable
			if(totalValue < playerValue){
				drawCard(hand);
			}
			else{
				//Display loss message
				over = true;
			}

		}
		}
	
	}
}
