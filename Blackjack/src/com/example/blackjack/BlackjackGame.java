package com.example.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;

public class BlackjackGame extends Activity {
	
	
	ArrayList<Card> cardDeck = new ArrayList<Card>();
	int cardCounter = 0;
	
	
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
	
	//Check for 21
	public void winCheck(ArrayList<Card> hand){
		
	
	}
	
	//Player Turn
	public void playerTurn(ArrayList<Card> hand){
		
		
	
	}
	
	//Dealer Turn 
	public void dealerTurn(ArrayList<Card> hand){
		
	}
}
