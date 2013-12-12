package com.example.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class BlackjackGame extends Activity {
	
	
	ArrayList<Card> cardDeck = new ArrayList<Card>();
	ArrayList<Card> player = new ArrayList<Card>();
	ArrayList<Card> dealer = new ArrayList<Card>();
	int cardCounter = 0;
	int playerValue = 0;
	int dealerValue = 0;
	int playerHandCount = 0;
	int dealerHandCount = 0;
	int playerChips = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.play_game);
        Intent intent = getIntent();
        playerChips = getIntent().getIntExtra("chips",-1);
        if(playerChips == 0){
        	finish();
        }
        TextView chipView = (TextView) findViewById(R.id.current_chips);
        chipView.setText("$" + Integer.toString(playerChips));
		Button hit = (Button) findViewById(R.id.hit_button);
		View.OnClickListener hitClickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Add logic for button
				playerHit(v);
				
			}
		};
		
		hit.setOnClickListener(hitClickListener);
        
		Button stay = (Button) findViewById(R.id.stay_button);
		View.OnClickListener stayClickListener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Add logic for button
				playerStay(v);
				
			}
		};
		
		stay.setOnClickListener(stayClickListener);
		
    	ArrayList<Card> playerHand = new ArrayList<Card>();
    	ArrayList<Card> dealerHand = new ArrayList<Card>();
        //Create a New Deck
        for(int i = 1; i <= 4; i++){
        	for(int j = 1; j <= 13; j++){
			
        		Card newCard = new Card(i,j);
        		cardDeck.add(newCard);
        	}
        }
        
        //Shuffle Deck
        long seed = System.nanoTime();
        Collections.shuffle(cardDeck, new Random(seed));

        TextView playerValueArea = (TextView) findViewById(R.id.player_total);
        TextView dealerValueArea = (TextView) findViewById(R.id.dealer_total);
        
        //Deal Initial Cards
        playerHand = playerDrawCard(playerHand);
        playerHand = playerDrawCard(playerHand);
        dealerHand = dealerDrawCard(dealerHand);
        dealerHand = dealerDrawCard(dealerHand);
		Log.d("PWD",Integer.toString(playerValue));
        playerValueArea.setText(Integer.toString(playerValue));
        playerTurn(playerHand);
        int dealerShowValue = dealerHand.get(1).getValue();
        dealerValueArea.setText(Integer.toString(dealerShowValue));
        dealer = dealerHand;
        
        
	}
	
	
	//Draw a Card
	public ArrayList<Card> playerDrawCard(ArrayList<Card> hand){
		//Log.d("PWD",cardDeck.get(cardCounter));
		Card drawnCard = cardDeck.get(cardCounter);
		hand.add(drawnCard);
		playerValue = playerValue + drawnCard.getValue();
		cardCounter++;
		TextView playerValueArea = (TextView) findViewById(R.id.player_total);
		playerValueArea.setText(Integer.toString(playerValue));
		
		RelativeLayout deckLayout = (RelativeLayout)findViewById(R.id.card_area);
		TextView newCard = new TextView(this);
		final ImageView newDraw = new ImageView(this);
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 55, getResources().getDisplayMetrics());
		newCard.setLayoutParams(new RelativeLayout.LayoutParams(width,height));
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)newCard.getLayoutParams();
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
		newCard.setLayoutParams(layoutParams);
		newCard.setGravity(Gravity.CENTER);
		newCard.setText(drawnCard.getFace());
		newCard.setTextSize(15);
		int newSuit = drawnCard.getSuit();
		switch (newSuit) {
		case 1: newCard.setBackground(getResources().getDrawable(R.drawable.spadesdrawable));
				newCard.setTextColor(0xffff0000);
				break;
		case 2: newCard.setBackground(getResources().getDrawable(R.drawable.clubsdrawable));
				newCard.setTextColor(0xffff0000);
				break;
		case 3: newCard.setBackground(getResources().getDrawable(R.drawable.heartsdrawable));
				newCard.setTextColor(0xff000000);
				break;
		case 4: newCard.setBackground(getResources().getDrawable(R.drawable.diamonddrawable));
				newCard.setTextColor(0xff000000);
				break;
		}

		
	    TranslateAnimation anim = new TranslateAnimation( 0, -50 + (playerHandCount * 50), 0, 275 );
	    anim.setDuration(1000);
	    anim.setFillAfter( true );
	    deckLayout.addView(newCard);
	    newCard.startAnimation(anim);
		

	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		            // TODO              
		playerHandCount++;
		return hand;
	}
	
	
	public ArrayList<Card> dealerDrawCard(ArrayList<Card> hand){
		//Log.d("PWD",cardDeck.get(cardCounter));
		Card drawnCard = cardDeck.get(cardCounter);
		hand.add(drawnCard);
		
		dealerValue = dealerValue + drawnCard.getValue();
		cardCounter++;
		TextView dealerValueArea = (TextView) findViewById(R.id.dealer_total);
		dealerValueArea.setText(Integer.toString(dealerValue));
		
		RelativeLayout deckLayout = (RelativeLayout)findViewById(R.id.card_area);
		TextView newCard = new TextView(this);
		final ImageView newDraw = new ImageView(this);
		int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
		int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 55, getResources().getDisplayMetrics());
		newCard.setLayoutParams(new RelativeLayout.LayoutParams(width,height));
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)newCard.getLayoutParams();
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
		newDraw.setLayoutParams(layoutParams);
		newDraw.setImageDrawable(getResources().getDrawable(R.drawable.cardback));
		newCard.setLayoutParams(layoutParams);
		newCard.setGravity(Gravity.CENTER);
		newCard.setText(drawnCard.getFace());
		newCard.setTextSize(15);
		int newSuit = drawnCard.getSuit();
		switch (newSuit) {
		case 1: newCard.setBackground(getResources().getDrawable(R.drawable.spadesdrawable));
				newCard.setTextColor(0xffff0000);
				break;
		case 2: newCard.setBackground(getResources().getDrawable(R.drawable.clubsdrawable));
				newCard.setTextColor(0xffff0000);
				break;
		case 3: newCard.setBackground(getResources().getDrawable(R.drawable.heartsdrawable));
				newCard.setTextColor(0xff000000);
				break;
		case 4: newCard.setBackground(getResources().getDrawable(R.drawable.diamonddrawable));
				newCard.setTextColor(0xff000000);
				break;
		}

		
	    TranslateAnimation anim = new TranslateAnimation( 0, -50 + (dealerHandCount * 50), 0, -275 );
	    anim.setDuration(1000);
	    anim.setFillAfter( true );
	    if(dealerHandCount == 0){
	    	newDraw.setId(1);
	    	newCard.setId(2);
	    	deckLayout.addView(newCard);
	    	newCard.setVisibility(View.INVISIBLE);
	    	deckLayout.addView(newDraw);
	    	newCard.startAnimation(anim);
	    	newDraw.startAnimation(anim);
	    }
	    else{
	    	deckLayout.addView(newCard);
	    	newCard.startAnimation(anim);
	    }

	                   
	    try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		            // TODO              
		dealerHandCount++;
		return hand;
	}
	
	//Check for 21. Return 0 if still playing, 1 if win, -1 if loss.
	public int winCheck(ArrayList<Card> hand, int id){
		int totalValue=0;
		for(int i = 0; i < hand.size(); i++){
			totalValue= totalValue + hand.get(i).getValue();

		}
	
		if (totalValue == 21)
			return 1;
		else if (totalValue > 21){
			boolean aces = false;
			//Check for Aces
			for(int i = 0; i < hand.size(); i++){
				if(hand.get(i).getValue() == 11){
					Log.d("PWD","Ace Found");
					hand.get(i).setValue(1);
					aces = true;
				}
			}
			//Recalculate if aces found
			if(aces == true){
				totalValue = 0;
				for(int i = 0; i < hand.size(); i++){
					totalValue= totalValue + hand.get(i).getValue();
				}
				
				if(id == 1){
					playerValue = totalValue;
					TextView playerValueArea = (TextView) findViewById(R.id.player_total);
					playerValueArea.setText(Integer.toString(playerValue));
				}
				else if(id == 2){
					dealerValue = totalValue;
					TextView dealerValueArea = (TextView) findViewById(R.id.dealer_total);
					dealerValueArea.setText(Integer.toString(dealerValue));
				}
					
				
			}
			if(totalValue > 21)
				return -1;
			else 
				return 0;
		}
		else
			return 0;
	}
	
	//Player Turn
	public void playerTurn(ArrayList<Card> hand){
		Button stay = (Button) findViewById(R.id.stay_button);
		Button hit = (Button) findViewById(R.id.hit_button);
		stay.setVisibility(View.VISIBLE);
		hit.setVisibility(View.VISIBLE);
		
		//Check for 21
		int game = winCheck(hand,1);
		player = hand;
		Log.d("PWD","Wincheck Completed");
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setPositiveButton("Play again", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				//Restart activity
				Intent intent = getIntent();
				intent.putExtra("chips", playerChips);
				finish();
				startActivity(intent);
			}
		});
		builder.setNegativeButton("Quit", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				//Quit 
				finish();
			}
		});
		
		if(game == 1)
		{
			builder.setMessage("Congratulations! Blackjack!");
			builder.setTitle("Winner!");
			playerChips = playerChips + 50;
			AlertDialog dialog = builder.create();
			dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
			dialog.show();
			
			//Create alert saying they won, button to restart game
		}
		else if(game == -1)
		{
			builder.setMessage("Bust!");
			builder.setTitle("Loss!");
			playerChips = playerChips -50;
			AlertDialog dialog = builder.create();
			dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
			//Create alert saying they loss, button to restart game 
			dialog.show();
		}
			
			
			
//Original Idea
//			AlertDialog.Builder gameBuilder = new AlertDialog.Builder(this);
//			gameBuilder.setPositiveButton("Hit", new DialogInterface.OnClickListener(){
//				public void onClick(DialogInterface dialog, int id) {
//					//Draw another card -- FIGURE OUT HOW TO GET HAND TO WORK IN A CLICK LISTENER
//					//drawCard(hand);
//				}
//			});
//			gameBuilder.setNegativeButton("Stay", new DialogInterface.OnClickListener(){
//				public void onClick(DialogInterface dialog, int id) {
//					//Don't draw another card
//				}
//			});
//			gameBuilder.setMessage("Your current total is " + totalValue + ", do you wish to hit or stay?");
//			gameBuilder.setTitle("Your Turn");
//			AlertDialog dialog = gameBuilder.create();
//			dialog.show();
//			//Create alert asking if they want to draw or stay. Go to Dealer's turn.
		
	
	}
	
	//Dealer Turn 
	public void dealerTurn(ArrayList<Card> hand){
		int cpu = winCheck(hand,2);
		boolean over = false;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setPositiveButton("Play again", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				//Restart activity
				Intent intent = getIntent();
				intent.putExtra("chips", playerChips);
				finish();
				startActivity(intent);
			}
		});
		builder.setNegativeButton("Quit", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				//Quit 
				finish();
			}
		});
		while(over == false){
		if(cpu == -1){
			//Win Alert
			playerChips = playerChips + 50;
			over = true;
			builder.setMessage("The Dealer Loses!");
			builder.setTitle("Winner!");
			AlertDialog dialog = builder.create();
			dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
			dialog.show();
		}
		else if(cpu == 1){
			over = true;
			playerChips = playerChips -50;
			//Loss Alert
			builder.setMessage("The Dealer Wins!");
			builder.setTitle("Loser!");
			AlertDialog dialog = builder.create();
			dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
			dialog.show();
		}
		else{
			//Draw a card following
			if(dealerValue < 17){
				hand = dealerDrawCard(hand);
				dealer = hand;
				Log.d("PWD","DealerValue = " + Integer.toString(dealerValue));
				if(dealerValue > 21)
					cpu = -1;
//				try {
//				    Thread.sleep(2000);
//				} catch(InterruptedException ex) {
//				    Thread.currentThread().interrupt();
//				}
			}
			else{
				Log.d("PWD","Dealer Value = " + Integer.toString(dealerValue) + "Player Value = " + Integer.toString(playerValue));
				if(playerValue > dealerValue)
					cpu = -1;
				else
					cpu = 1;
			}

		}
		}
	
	}
	public void playerHit(View view){
		player = playerDrawCard(player);
		int game = 0;
		game = winCheck(player,1);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setPositiveButton("Play again", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				//Restart activity
				Intent intent = getIntent();
				intent.putExtra("chips", playerChips);
				finish();
				startActivity(intent);
			}
		});
		builder.setNegativeButton("Quit", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id) {
				//Quit 
				finish();
			}
		});
		if(game == -1)
		{
			builder.setMessage("Bust!");
			builder.setTitle("Loss!");
			playerChips = playerChips -50;
			AlertDialog dialog = builder.create();
			dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
			//Create alert saying they loss, button to restart game 
			dialog.show();
		}
	}
	
	public void playerStay(View view){
		Button stay = (Button) findViewById(R.id.stay_button);
		Button hit = (Button) findViewById(R.id.hit_button);
		stay.setVisibility(View.INVISIBLE);
		hit.setVisibility(View.INVISIBLE);
		TextView dealerValueArea = (TextView) findViewById(R.id.dealer_total);
        dealerValueArea.setText(Integer.toString(dealerValue));
        ImageView faceDown = (ImageView) findViewById(1);
        TextView faceUp = (TextView) findViewById(2);
        faceDown.setVisibility(View.INVISIBLE);
        faceUp.setVisibility(View.VISIBLE);
		dealerTurn(dealer);

	}
}
