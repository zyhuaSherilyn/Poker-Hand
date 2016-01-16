import java.util.Random;


public class Deck 
{
	protected Card [] deck;
	private int topCard=0;
	//create multiple decks of cards
	Deck(int noOfDeck)
	{
		deck=new Card[noOfDeck*52];
		for(int Decks=1; Decks<=noOfDeck;Decks++)
		{
			for(int suits=1;suits<=4;suits++)
			{
				for(int ranks=1;ranks<=13;ranks++)
				{
					Card newCard=new Card(ranks,suits);
					deck[topCard]=newCard;
					topCard++;
				}
			}
		}
	}
	
	//Create only one deck of cards
	Deck()
	{
		deck=new Card[52];
		for(int suits=1;suits<=4;suits++)
		{
			for(int ranks=1;ranks<=13;ranks++)
			{
				Card newCard=new Card(ranks,suits);
				deck[topCard]=newCard;
				topCard++;
			}
		}
	}
	
	public Card deal()
	{
		if(topCard!=0)
		{
			topCard--;
			return deck[topCard];
		}
		return null;
	}
	
	//Randomly shuffle all the cards on the desk by randomly switching the position of the two cards
	public void shuffle ()
	{
	    topCard=deck.length;
		Random rnd= new Random();
		for(int index=deck.length-1;index>0;index--)
		{
			int switchCard=rnd.nextInt(index+1);
			//Switch cards around
			Card swichCard= deck[switchCard];
			deck[switchCard]=deck[index];
			deck[index]=swichCard;
		}
	}
	
	public String toString()
	{
		String returnString="";
		for (int index = 0; index < topCard; index ++)
			returnString+= deck[index].toString()+" ";
		return String.format("%s", returnString);
	}
	public int cardsLeft()
	{
		return topCard;
	}
}
