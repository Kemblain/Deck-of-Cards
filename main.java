/****************************************************************************************
* Program Name:   Deck of Cards
* Author:         Kem
* Date:           October 23, 2017
* Course/Section: CSC 112 001W
* Program Description:
*    This program allows manipulation of a Deck object during runtime.
*    The Deck object functions as a linked list of Card objects.
*    Manipulation includes 
*    	creation
*    	listing the cards in the deck to console
*    	counting the number of cards in the deck
*    	removing a card, both by random or by user selection
*    	shuffling the deck
*    
*    In addition to main(), there are two small helper functions inside this file. 
*    displayMenu() prints the menu to the screen, and removeCard() takes a deck object,
*    clarifies if the user wants to remove a specific card, a random card, or cancel,
*    and then removes a card from the deck.
****************************************************************************************/

/************************************ Refined Algorithm *********************************
 * 	Establish variables and objects
 * 	Display the menu, and get user input
 * 	WHILE the user does not want to quit
 * 		IF the user wants to create a new deck
 * 			create a new deck
 * 		ELSE IF the user wants to print the current deck
 * 			print the current deck
 * 		ELSE IF the user wants to know the number of cards in the current deck
 * 			print the number of cards in the current deck
 * 		ELSE IF the user wants to remove a card from the deck
 * 			find out if the user wants to remove a specific card or a random card
 * 			IF the user wants to remove a specific card
 * 				get their input, and remove that card
 * 			ELSE
 * 				pick a random card and remove it
 * 			END REMOVAL IF/ELSE
 * 		ELSE
 * 			shuffle the deck
 * 		END MENU IF/ELSE
 * 
 * 		Display the menu
 * 		Get the users input
 * 	END WHILE
 * 		
 * 	Output an exiting message and close the scanner
****************************************************************************************/

/*********************************** Compiler Directives *******************************/



/* Imports */
import java.util.Scanner;
import java.util.Random;

public class main {

	public static void main(String[] args) 
	{
		/* Establish variables and objects */
		Deck theOneDeckToRuleThemAll = new Deck();	// The Deck object, to be manipulated
		Scanner sc = new Scanner(System.in);		// A Scanner object, to get user input
		int input = -1;								// A variable for storing user input
		final int QUITTER = 6;						// The option to exit the program
		
		
		/* Display the menu, and get user input */
		displayMenu();
		input = sc.nextInt();
		
		
		/* WHILE the user does not want to quit */
		while(input != QUITTER)
		{
			
			/* IF the user wants to create a new deck */
			if (input == 1)
			{
				/* create a new deck */
				theOneDeckToRuleThemAll.createDeck();
			}
			
			/* ELSE IF the user wants to print the current deck */
			else if (input == 2)
			{
				/* print the current deck */
				System.out.println(theOneDeckToRuleThemAll);
			}
			
			/* ELSE IF the user wants to know the number of cards in the current deck */
			else if (input == 3)
			{
				/* print the number of cards in the current deck */
				System.out.println("There are " + theOneDeckToRuleThemAll.countCards() + " cards remaining.");
			}
			
			/* ELSE IF the user wants to remove a card from the deck */
			else if (input == 4)
			{
				
				/* 	find out if the user wants to remove a specific card or a random card
				 * 	IF the user wants to remove a specific card
				 * 		get their input, and remove that card
				 * 	ELSE
				 * 		pick a random card and remove it 
				 * 	END REMOVAL IF/ELSE */
				removeCard(theOneDeckToRuleThemAll); // All of that happens inside this function
			}
			
			/* ELSE */
			else
			{
				/* shuffle the deck */
				System.out.println("Shuffling deck.");
				theOneDeckToRuleThemAll = theOneDeckToRuleThemAll.shuffle();
			}//END MENU IF/ELSE
				
			/* Display the menu */	
			displayMenu();
			
			/* Get the users input */
			input = sc.nextInt();		
		}//END WHILE
		
		/* Output an exiting message and close the scanner */
		System.out.println("Exiting.");
		sc.close();
	}
	
/****************************************************************************************
 * 	displayMenu() just prints the menu to the console. Really simple.
 ***************************************************************************************/
	
	private static void displayMenu()
	{
		System.out.println("1. Create a new deck.");
		System.out.println("2. Print entirety of current deck.");
		System.out.println("3. Display number of cards in current deck.");
		System.out.println("4. Remove card from current deck.");
		System.out.println("5. Shuffle deck.");
		System.out.println("6. Exit.");
		System.out.print("Please make a selection: ");
	}
	
/****************************************************************************************
* 	removeCard() takes a Deck object, clarifies if the user wants to remove a specific 
* 	card, a random card, or cancel, and then removes a card from the deck via the 
* 	requested method.
* ***************************************************************************************/
	
	private static void removeCard(Deck theDeck)
	{
		/* Variables and Objects */
		int input = -1;								//Holder for user input
		Scanner sc = new Scanner(System.in);		//Scanner object
		
		/* Print out options for user */
		System.out.println("1. Remove a specific card (by position).");
		System.out.println("2. Remove a random card.");
		System.out.println("3. Cancel");
		System.out.print("Please make a selection: ");
		
		/* Get user input */
		input = sc.nextInt();
		
		/* IF the user wants to remove a specific card */
		if (input == 1)
		{
			/* Get the card the user wants to remove, and remove it */
			System.out.println("Enter the position of the card to be removed: ");
			input = sc.nextInt();
			theDeck.removeCard(input);
		}
		
		/* ELSE IF the user wants to remove a random card */
		else if (input == 2)
		{
			/* generate a random position, and remove that card */
			Random rnd = new Random();
			input = rnd.nextInt(theDeck.countCards()+1);
			theDeck.removeCard(input);
		}
		/*ELSE the user doesnt want to remove a card*/
		else
		{
			/*output a cancellation message */
			System.out.println("Cancelling...");
		}//END IF/ELSE
	}

	/* End of the file */
}
