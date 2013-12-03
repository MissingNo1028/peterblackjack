package com.example.blackjack;

public class Card {
	private int suit, value;
	public Card(int newSuit, int newValue){
		suit = newSuit;
		value = newValue;
	}
	
	public int getSuit(){
		return suit;
	}
	public int getValue(){
		return value;
	}
}
