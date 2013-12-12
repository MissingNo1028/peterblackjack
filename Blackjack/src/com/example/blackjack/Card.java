package com.example.blackjack;

public class Card {
	private int suit, value, number;
	public Card(int newSuit, int newNumber){
		suit = newSuit;
		number = newNumber;
		if(number > 10)
			value = 10;
		else if(number == 1)
			value = 11;
		else
			value = number;
			
	}
	
	public int getSuit(){
		return suit;
	}
	public int getValue(){
		return value;
	}
	public int getNumber(){
		return number;
	}
	public void setValue(int val){
		value = val;
	}
}
