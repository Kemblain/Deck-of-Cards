
public class Card {
	
	private int faceValue;
	private String faceName;
	private int suitValue;
	private String suitName;
	private int ID;
	private Card nextCard;
	
	Card()
	{
		nextCard = null;
	}
	
	Card(Card oldCard)
	{
		faceValue = oldCard.getFaceValue();
		faceName = oldCard.getFaceName();
		suitValue = oldCard.getSuitValue();
		suitName = oldCard.getSuitName();
		ID = oldCard.getID();
		nextCard = null;
	}
	
	public void setFaceValue(int newFaceValue)
	{
		faceValue = newFaceValue;
	}
	
	public void setFaceName(String newFaceName)
	{
		faceName = newFaceName;
	}
	
	public void setSuitValue(int newSuitValue)
	{
		suitValue = newSuitValue;
	}
	
	public void setSuitName(String newSuitName)
	{
		suitName = newSuitName;
	}
	
	public void setID(int newID)
	{
		ID = newID;
	}
	
	public void setNextCard(Card newNextCard)
	{
		nextCard = newNextCard;
	}
	
	public int getFaceValue()
	{
		return faceValue;
	}
	
	public String getFaceName()
	{
		return faceName;
	}
	
	public int getSuitValue()
	{
		return suitValue;
	}
	
	public String getSuitName()
	{
		return suitName;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public Card getNextCard()
	{
		return nextCard;
	}
	
	public String toString()
	{
		return(faceName + " of " + suitName);
	}

}
