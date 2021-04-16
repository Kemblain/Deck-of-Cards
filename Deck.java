import java.util.Random;

/**
 * Functions as a linked list of card objects.
 * @author Kem
 * @version 1.0
 * @since 1.0
 * @Due October 23, 2017
 * @Class CSC 112 001W
 * @see Card
 */

public class Deck {
	/**
	 * The top card of the deck
	 */
	private Card head;
	
	/**
	 * A holding object so it can be manipulated without adjusting anything else in the deck
	 */
	private Card first;		
	
	/**
	 * The current card to be operated on
	 */
	private Card current;
	
	/**
	 * The card directly before the current card.
	 */
	private Card previous;				
	
	/**
	 * The card directly after the current card.
	 */
	private Card next;
	
	/**
	 * The very last card in the deck.
	 */
	private Card tail;	
	
	/**
	 * The number of cards in the deck.
	 */
	private int numberOfCards;		
	
	/**
	 * A boolean for whether the deck is in the process of shuffling.
	 */
	private boolean shuffleInProgress;
	
	/**
	 * Construction of an empty deck.
	 * 
	 * Constructs an empty deck by setting all of the object pointers
	 * to null, numberOfCards to 0, and shuffleInProgress to false.
	 */
	Deck()
	{
		head = null;
		first = null;
		current = null;
		previous = null;
		next = null;
		tail = null;
		numberOfCards = 0;
		shuffleInProgress = false;
	}
	
	/**
	 * Creates each card and adds them to the deck.
	 * 
	 * As long as the deck isn't full already, this goes through the process of generating
	 * each card and adding them to the deck, so that the deck is sorted by suit and then by value.
	 */
	public void createDeck()
	{
		
		if (numberOfCards == 52)
		{
			System.out.println("Deck still full. Don't waste my memory making another one.");
		}
		else
		{
			
			head = new Card();
			first = head;
			current = head;
		
			
			for(int i = 0; i < 52; i++)
			{
				tail = current;
				numberOfCards++;
				current.setID(i+1);
				current.setSuitValue((i/13)+ 1);
				current.setFaceValue((i%13)+ 1);
				
				setSuitName(current);
				setFaceName(current);
				
				first = new Card();
				
				current.setNextCard(first);
				current = current.getNextCard();
			}
			
			
			System.out.println("Deck creation complete.");
			System.out.println();
		}
		
		resetPointers();
	}
	
	/**
	 * Checks if the deck is empty.
	 * 
	 * This checks if the head card is actually null to determine whether the deck is empty.
	 */
	public boolean isEmpty()
	{
		if(head != null)
			return false;
		return true;
	}
	
	/**
	 * Provides functionality for converting a deck object to a string.
	 * 
	 * If the deck isn't empty, this function gets the information for each card in the deck
	 * and stores it in a string and then returns the string. If the deck is empty, the string simply
	 * contains that information.
	 * 
	 * @return A String containing information on each card in the deck separated by new line characters
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		
		String deckString = "";
		if(!isEmpty())
		{
			resetPointers();
			System.out.println("Beginning reading cards...");
			int i = 1;
			while(current != tail)
			{
				if((i/10) == 0 && current.getID()/10 == 0)
				{
					deckString += "Card  " + current.getID() + ": " + current + " ( " + i + "/" + this.countCards() + ")" + "\n";
				}
				else if((i/10) == 0)
				{
					deckString += "Card " + current.getID() + ": " + current + " ( " + i + "/" + this.countCards() + ")" + "\n";
				}
				else if (current.getID()/10 == 0)
				{
					deckString += "Card  " + current.getID() + ": " + current + " (" + i + "/" + this.countCards() + ")" + "\n";
				}
				else
				{
					deckString += "Card " + current.getID() + ": " + current + " (" + i + "/" + this.countCards() + ")" + "\n";
				}
				
				i++;
				moveForward();
			}
			deckString += "Card " + tail.getID() + ": " + tail + " (" + i + "/" + this.countCards() + ")" + "\n";
			resetPointers();
			System.out.println("Reading complete. Cards are as follows: ");
		}
		else
			deckString = "The deck is empty. Create a deck first!";
		return deckString;
	}
	
	/**
	 * Returns numberOfCards.
	 * 
	 * @see Deck.numberOfCards
	 * @return An integer that represents the number of cards in the deck
	 */
	public int countCards()
	{
		return numberOfCards;
	}
	
	/**
	 * Removes a card from the current deck
	 * 
	 * As long as the deck isn't empty, and the input is in range and positive, 
	 * this function removes the card from the position specified.
	 * 
	 * @param number An integer representing the position of the card to be removed
	 */
	public void removeCard(int number)
	{
		if (isEmpty())
		{
			System.out.println("The deck is empty. You cannot discard from an empty deck.");
		}
		else if(number > this.countCards())
		{
			System.out.println("You have attempted to discard a card from a position not inside this deck.");
			System.out.println("There are only " + this.countCards() + " cards in this deck");
		}
		else if(number < 0)
		{
			System.out.println("Negative absolute positions don't exist. What are you trying to do here?");
		}
		else
		{
			for (int i = 1; i < number; i++)
			{
				moveForward();
			}
		
			numberOfCards--;
			if(!isShuffling())
			{
				System.out.println("Removing " + current + " from deck.");
			}
			if(current != head && current != tail)
			{
				previous.setNextCard(next);
				current = previous;
			}
			else if(current == tail)
			{
				tail = previous;
				current = tail;
			}
			else
			{
				head = current.getNextCard();
				current = head;
			}
		
			resetPointers();
		}
	}
	
	/**
	 * A private function used in assigning suit values to cards during deck creation.
	 * 
	 * Assigns a value to the passed card's suitName based on its suitValue.
	 * 
	 * @param cardToChange The card needing values assigned.
	 */
	private void setSuitName(Card cardToChange)
	{
		if (cardToChange.getSuitValue()==1)
			cardToChange.setSuitName("Spades  ");
		else if (cardToChange.getSuitValue()==2)
			cardToChange.setSuitName("Clubs   ");
		else if (cardToChange.getSuitValue()==3)
			cardToChange.setSuitName("Diamonds");
		else
			cardToChange.setSuitName("Hearts  ");
	}

	/**
	 * A private function used in assigning face values to cards during deck creation.
	 * 
	 * Assigns a value to the passed card's faceName based on its faceValue.
	 * 
	 * @param cardToChange The card needing values assigned.
	 */
	private void setFaceName(Card cardToChange)
	{
		if (cardToChange.getFaceValue()==1)
			cardToChange.setFaceName("Ace  ");
		else if (cardToChange.getFaceValue()==2)
			cardToChange.setFaceName("Two  ");
		else if (cardToChange.getFaceValue()==3)
			cardToChange.setFaceName("Three");
		else if (cardToChange.getFaceValue()==4)
			cardToChange.setFaceName("Four ");
		else if (cardToChange.getFaceValue()==5)
			cardToChange.setFaceName("Five ");
		else if (cardToChange.getFaceValue()==6)
			cardToChange.setFaceName("Six  ");
		else if (cardToChange.getFaceValue()==7)
			cardToChange.setFaceName("Seven");
		else if (cardToChange.getFaceValue()==8)
			cardToChange.setFaceName("Eight");
		else if (cardToChange.getFaceValue()==9)
			cardToChange.setFaceName("Nine ");
		else if (cardToChange.getFaceValue()==10)
			cardToChange.setFaceName("Ten  ");
		else if (cardToChange.getFaceValue()==11)
			cardToChange.setFaceName("Jack ");
		else if (cardToChange.getFaceValue()==12)
			cardToChange.setFaceName("Queen");
		else
			cardToChange.setFaceName("King ");
	}
	
	/**
	 * A private function for resetting the current pointer
	 * @see Deck.current
	 */
	private void resetPointers()
	{
		previous = null;
		current = head;
		next = head.getNextCard();
	}
	
	/**
	 * A private function for traversing the deck
	 */
	private void moveForward()
	{
		previous = current;
		current = current.getNextCard();
		if(current != null)
			next = current.getNextCard();
		else
			next = null;
	}
	
	/**
	 * A private function that returns the state of shuffleInProgress.
	 * 
	 * For use in the removeCard() function, as while shuffling printing out every
	 * card removed is not a desired action.
	 * @return boolean shuffleInProgress
	 */
	private boolean isShuffling()
	{
		return shuffleInProgress;
	}
	
	/**
	 * Shuffles the deck.
	 * 
	 * Shuffles the deck by taking a random card from the deck object, placing it a new
	 * deck, and repeating until no cards remain in the original. It then returns
	 * the newly created deck.
	 * @return A new deck with the positions of each card randomly assigned.
	 */
	public Deck shuffle()
	{
		if(!this.isEmpty())
		{
			this.shuffleInProgress = true;
			Deck shuffledDeck = new Deck();
			Random rnd = new Random();
			int firstCountCards = this.countCards();
			int randomHolder = -1;
		
			for(int i = 0; i < firstCountCards; i++)
			{
				randomHolder = rnd.nextInt(this.countCards() + 1);
				for(int n = 1; n < randomHolder; n++)
				{
					this.moveForward();
				}
				shuffledDeck.addCard(this.current);
				shuffledDeck.numberOfCards++;
				this.resetPointers();
				this.removeCard(randomHolder);
			}
			this.shuffleInProgress = false;
			return shuffledDeck;
		}
		else
		{
			System.out.print("Cannot shuffle an empty deck. Create a new one.");
			return this;
		}
		
	}
	/**
	 * Clones a card and adds it to the end of the deck.
	 * @param oldCard the card to be cloned.
	 */
	private void addCard(Card oldCard)
	{
		Card newCard = new Card(oldCard);
		if(this.head != null)
		{
			tail.setNextCard(newCard);
			tail = tail.getNextCard();
		}
		else
		{
			head = newCard;
			tail = newCard;
		}
		
	}
	
}