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
	public String getFace(){

		String face = "";
	
		if(number == 1)
			face = "A";
		else if(number == 11)
			face = "J";
		else if(number == 12)
			face = "Q";
		else if (number == 13)
			face = "K";
		else
			face = Integer.toString(number);
		
		return face;
	}
}
