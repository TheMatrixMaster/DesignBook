/*******************************************************************************
 * Companion code for the book "Introduction to Software Design with Java",
 * 2nd edition by Martin P. Robillard.
 *
 * Copyright (C) 2022 by Martin P. Robillard
 *
 * This code is licensed under a Creative Commons 
 * Attribution-NonCommercial-NoDerivatives 4.0 International License.
 * 
 * See http://creativecommons.org/licenses/by-nc-nd/4.0/
 * 
 *******************************************************************************/
package e2.chapter4;
import java.util.Optional;
import java.lang.Class;

import java.lang.Comparable;
import java.lang.reflect.Field;

/**
 * Implementation of a playing card. This class yields immutable objects.
 * This version of the class shows an application of the Flyweight design
 * pattern where the flyweight store is pre-initialized.
 */
public class Card implements Comparable<Card> {

	public static void main(String[] args)
	{
		Card card1 = new Card(Rank.ACE, Suit.CLUBS);
		Class<?> class1 = card1.getClass();

		try {
			Field rankOfCard = class1.getDeclaredField("aRank");
			rankOfCard.setAccessible(true);
			rankOfCard.set(card1, Optional.of(Rank.JACK));
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Implements the flyweight store as a bidimensional array. The 
	 * first dimension indexes the suits by the ordinal value of their enumerated type, 
	 * and the second dimension, the ranks. For example, to retrieve the two of clubs,
	 * we access CARDS[Suit.CLUBS.ordinal()][Rank.TWO.ordinal()]. 
	 */
	private static final Card[][] CARDS = new Card[Suit.values().length][Rank.values().length];
	private static final Card[] JOKERS = new Card[2];

	private enum Joker { WHITE, BLACK };
	
	private final Optional<Rank> aRank;
	private final Optional<Suit> aSuit;
	private final Optional<Joker> aJoker;
	
	// Initialization of the flyweight store
	static {
		for( Suit suit : Suit.values() ) {
			for( Rank rank : Rank.values() ) {
				CARDS[suit.ordinal()][rank.ordinal()] = new Card(rank, suit);
			}
		}
		JOKERS[0] = new Card(true);
		JOKERS[1] = new Card(false);
	}
	
	// Private constructor
	public Card( Rank pRank, Suit pSuit) {
		aRank = Optional.of(pRank);
		aSuit = Optional.of(pSuit);
		aJoker = Optional.empty();
	}

	public Card( boolean pIsWhiteJoker ) {
		aRank = Optional.empty();
		aSuit = Optional.empty();
		if (pIsWhiteJoker) {
			aJoker = Optional.of(Joker.WHITE);
		} else {
			aJoker = Optional.of(Joker.BLACK);
		}
	}
	
	/**
	 * @param pRank The rank of the requested card.
	 * @param pSuit The suit of the requested card.
	 * @return The unique Card instance with pRank and pSuit
	 * @pre pRank != null && pSuit != null
	 */
	public static Card get(Rank pRank, Suit pSuit) {
		assert pRank != null && pSuit != null;
		return CARDS[pSuit.ordinal()][pRank.ordinal()];
	}

	public static Card get(boolean pIsWhiteJoker) {
		if (pIsWhiteJoker) {
			return JOKERS[0];
		}
		else {
			return JOKERS[1];
		}
	}

	public boolean isJoker() {
		return aJoker.isPresent();
	}

	public boolean isWhiteJoker() {
		return isJoker() && aJoker.get() == Joker.WHITE;
	}

	public boolean isBlackJoker() {
		return isJoker() && aJoker.get() == Joker.BLACK;
	}
	
	/**
	 * @return The rank of the card.
	 */
	public Optional<Rank> getRank() {
		return aRank;
	}
	
	/**
	 * @return The suit of the card.
	 */
	public Optional<Suit> getSuit() {
		return aSuit;
	}
	
	@Override
	public String toString() {
		return String.format("%s of %s", aRank, aSuit);
	}

	@Override
	public int compareTo(Card pCard)
	{
		if (isJoker() && pCard.isJoker())
		{
			return aJoker.get().compareTo(pCard.aJoker.get());
		}
		else if (isJoker() && !pCard.isJoker())
		{
			return 1;
		}
		else if (!isJoker() && pCard.isJoker())
		{
			return -1;
		}
		else
		{
			return aRank.get().compareTo(pCard.aRank.get());
		}
	}

	@Override
	public boolean equals(Object pObject) {
		if (this == pObject)
		{
			return true;
		}
		if (pObject == null)
		{
			return false;
		}
		if (getClass() != pObject.getClass())
		{
			return false;
		}

		Card other = (Card) pObject;
		
		return (
			aSuit.get() == other.aSuit.get() && 
			aRank.get() == other.aRank.get()
		);
	}

	@Override
	public int hashCode() {
		return aSuit.hashCode() + aRank.hashCode();
	}
}
