import java.util.Comparator;

/**
 * Constructs and keeps track of information of a Card object. Contains various
 * methods that can alter the instance variables of a Card object
 * @author Sherilyn Hua
 * @version April 18, 2015
 */
public class Card implements Comparable<Card>
{
	// Use number to represent the suit of cards. Let 1 represent Clubs, 2
	// represent Diamonds, 3 represent Hearts, and 4 represent Spades.
	private int suit;
	// Use number to represent the rank of cards. Let 1 represent Ace, 2 to 10
	// represent actual ranking, 11 represent Jack, 12 represent Queen and 13
	// represent King.
	private int rank;
	// Keep tracking if the card is facing up or down.
	private boolean isFaceUp = false;
	// The order of suits from rank by it's alphabetical order
	private static final String SUITS = " CDHS";
	// The ranking of cards. The position of each card's rank in the string is
	// equal to the number represent the rank of cards.
	private static final String RANKS = " A23456789TJQK";
	// Constant Comparator object for comparing Card by their suit.
	public static Comparator<Card> SUIT_ORDER = new SuitOrder();

	public Card(int rank, int suit)
	{
		this.rank = rank;
		this.suit = suit;
	}

	public Card(String cardStr)
	{
		this(RANKS.indexOf(cardStr.charAt(0)), SUITS.indexOf(cardStr.charAt(1)));
	}

	// return the score of each card between 2-11, according to the card's
	// ranking. Return 11 when it's an Ace
	public int getValue()
	{
		// A ,2,3,4,5,6,7,8,9,10, J, Q, K
		int[] scoring = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };
		return scoring[rank - 1];
	}

	public int getRank()
	{
		return this.rank;
	}

	public int getSuit()
	{
		return this.suit;
	}

	public int compareTo(Card othercard)
	{
		if (this.rank != othercard.rank)
			return this.rank - othercard.rank;
		else if (this.suit != othercard.suit)
			return this.suit - othercard.suit;
		else
			return 0;
	}

	public void flip()
	{
		isFaceUp = !isFaceUp;
	}

	public String toString()
	{
		return String.format("%c%c", RANKS.charAt(rank), SUITS.charAt(suit));
	}

	// Return the direction of the card
	public boolean isFaceUp()
	{
		return isFaceUp;
	}

	// Return if the current card is an Ace card
	public boolean isAce()
	{
		if (rank == 1)
			return true;
		return false;
	}

	private static class SuitOrder implements Comparator<Card>
	{
		public int compare(Card firstCard, Card secondCard)
		{
			if (firstCard.suit < secondCard.suit)
				return -1;
			else if (firstCard.suit > secondCard.suit)
				return 1;
			else
			{
				if (firstCard.rank < secondCard.rank)
					return -1;
				else if (firstCard.rank > secondCard.rank)
					return 1;
				else
					return 0;
			}
		}
	}
}
