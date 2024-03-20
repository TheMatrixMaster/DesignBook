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

/**
 * Stub for a GameModel class that is an application
 * of the Singleton design pattern.
 */
public class GameModel {
	
	private static final GameModel INSTANCE = new GameModel();
	private final Deck aDeck = new Deck();
	
	// private constructor so the client cannot create more GameModel instances
	private GameModel() {}
	
	public static GameModel instance() { 
		return INSTANCE;
	}

	public void newGame() {
		aDeck.shuffle();
	}
}
