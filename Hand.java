
public class Hand 
{
	private Card head;
	private Card first;
	private Card current;
	private Card tail;
	private Card previous;
	private Card next;
	private int numberOfCards;
	
	Hand()
	{
		head = null;
		first = null;
		current = null;
		tail = null;
		previous = null;
		numberOfCards = 0;
	}
	
	public void addCard(Card cardToAdd)
	{
		if (head == null)
		{
			head = new Card(cardToAdd);
			head.setNextCard(null);
			tail = head;
		}
		else
		{
			tail.setNextCard(new Card(cardToAdd));
			tail = tail.getNextCard();
		}
		numberOfCards++;
	}
	
	public String toString()
	{
		String data = "";
		if (head != null)
		{
			for (int i = 0; i < numberOfCards; i++)
			{
				data += current.toString() + "/n";
				current = current.getNextCard();
			}
		}
		else
			data = "The hand is empty.";
		
		current = head;
		return data;
	}
	
	public void removeCard(int position)
	{
		if(position >= numberOfCards)
		{
			System.out.println("Position out of range.");
		}
		else if(head != null && position > 1)
		{
			for (int i = 0; i < position; i++)
			{
				previous = current;
				current = current.getNextCard();
				next = current.getNextCard();
			}
			previous.setNextCard(current.getNextCard());
		}
		else if(position == 0)
		{
			head = head.getNextCard();
		}
		current = head;
		next = current.getNextCard();
		previous = null;
	}

}
