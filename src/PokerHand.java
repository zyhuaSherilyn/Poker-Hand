/**
 * Keeps track of a Poker Hand. Hand size could be 1 to 7 cards. Includes a
 * getType() method that finds the type (e.g. two pair, flush, straight) of this
 * hand. Note: In determining a hand's type you should consider up to the best 5
 * cards in the hand.
 * 
 * @author Sherilyn Hua
 * @version April 2013
 */

public class PokerHand extends Hand
{
	// Poker Hand types/categories
	// Use these constants in your getType method
	// e.g. return FULL_HOUSE;
	public final static int ROYAL_FLUSH = 9;
	public final static int STRAIGHT_FLUSH = 8;
	public final static int FOUR_OF_A_KIND = 7;
	public final static int FULL_HOUSE = 6;
	public final static int FLUSH = 5;
	public final static int STRAIGHT = 4;
	public final static int THREE_OF_A_KIND = 3;
	public final static int TWO_PAIR = 2;
	public final static int PAIR = 1;
	public final static int NOTHING = 0;

	public final static String[] TYPES = { "Nothing", "Pair", "Two Pair",
			"Three of a Kind ", "Straight", "Flush", "Full House",
			"Four of a Kind", "Straight Flush", "Royal Flush" };

	// Creates two arrays to store the rank and suit of the Cards in hand
	// Adds one more element spot to rank since Ace is tallied to both 1 and 14
	public int[] rank = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	public int[] suit = new int[] { 0, 0, 0, 0, 0 };

	/**
	 * Constructs an empty PokerHand
	 */
	public PokerHand()
	{
		super();
	}

	/**
	 * Returns the type of this hand e.g. Flush, Straight, Two Pair
	 * @return the poker hand type 0 - NOTHING to 9 - ROYAL_FLUSH
	 */
	public int getType()
	{
		// Tallies the rank and suit of each Card in hand into the rank and suit
		// arrays
		for (Card nextCard : hand)
		{
			rank[nextCard.getRank()]++;
			// If it's an Ace, tallies both 1 and 14
			if (nextCard.isAce())
			{
				rank[14]++;
			}
			suit[nextCard.getSuit()]++;
		}

		// Determines which type of poker is present in the hand, starting from
		// the type that gives the highest score
		// Returns nothing if none of the types is found
		if (isRoyalFlush())
			return ROYAL_FLUSH;
		if (isStraightFlush())
			return STRAIGHT_FLUSH;
		if (isFourOfAKind())
			return FOUR_OF_A_KIND;
		if (isFullHouse())
			return FULL_HOUSE;
		if (isFlush())
			return FLUSH;
		if (isStraight(rank))
			return STRAIGHT;
		if (isThreeOfAKind(1))
			return THREE_OF_A_KIND;
		if (isTwoPair())
			return TWO_PAIR;
		if (isPair(1))
			return PAIR;
		return NOTHING;
	}

	/**
	 * Determines if a straight (5 consecutive Cards) is present in the hand by
	 * examining a given array consists of the Card ranks
	 * @param ranks the array to be examined
	 * @return true, if straight is present in this array; false if otherwise
	 */
	public boolean isStraight(int[] ranks)
	{
		int count = 0;
		for (int n = 1; n < ranks.length; n++)
		{
			// Adds 1 to count if a rank is tallied one or more times
			// Returns true if 5 consecutive ranks are present
			if (ranks[n] >= 1)
			{
				count++;
				if (count == 5)
					return true;
			}
			else
				// Sets count back to 0 if there's a gap in between ranks
				count = 0;
		}
		return false;
	}

	/**
	 * Determines if a flush (5 Cards of the same suit) is present in the hand
	 * by examining the array of Card suits
	 * @return true, if flush is found in the hand; false if otherwise
	 */
	public boolean isFlush()
	{
		// Goes through every element in the suit array
		// If an element in this array is greater than or equal to 5, returns
		// true
		for (int n = 1; n < suit.length; n++)
			if (suit[n] >= 5)
				return true;

		return false;
	}

	/**
	 * Determines if a straight flush (5 consecutive Cards of the same suit) is
	 * present in the hand by examining the hand array and the suit array; a new
	 * rank array is created to keep track of the rank of the Cards with the
	 * same suit
	 * @return true, if a straight flush is found in the hand; false if
	 *         otherwise
	 */
	public boolean isStraightFlush()
	{
		int[] newRank = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };

		// If a flush is present in the hand
		if (isFlush())
		{
			// Finds the suit that contains 5 or more Cards
			int greatestSuit = 0;
			for (int n = 1; n < suit.length; n++)
			{
				if (suit[n] >= 5)
					greatestSuit = n;
			}

			// Looks for Cards in the hand that have the same suit as the
			// greatestSuit and tallies them in the new rank array
			for (int m = 0; m < hand.size(); m++)
			{
				if (hand.get(m).getSuit() == greatestSuit)
				{
					newRank[hand.get(m).getRank()]++;
					// If the Card is an Ace, tallies 14 element as well
					if (hand.get(m).getRank() == 1)
						newRank[14]++;
				}
			}
		}

		// If the resulting rank array contains a straight type, a straight
		// flush is present
		if (isStraight(newRank))
			return true;

		return false;
	}

	/**
	 * Determines if a royal flush (straight flush that starts from 10) is
	 * present in the hand by examining the hand array and the suit array; a new
	 * rank array is created to keep track of the rank of the Cards with the
	 * same suit
	 * @return true if a royal flush is found in the hand; false if otherwise
	 */
	public boolean isRoyalFlush()
	{
		int[] newRank = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };

		// If a flush is present in the hand
		if (isFlush())
		{
			// Finds the suit that contains 5 or more Cards
			int greatestSuit = 0;
			for (int n = 1; n < suit.length; n++)
			{
				if (suit[n] >= 5)
					greatestSuit = n;
			}

			// Looks for Cards in the hand that have the same suit as the
			// greatestSuit and tallies them in the new rank array
			for (int m = 0; m < hand.size(); m++)
			{
				if (hand.get(m).getSuit() == greatestSuit)
				{
					newRank[hand.get(m).getRank()]++;
					// If the Card is an Ace, tallies 14 element as well
					if (hand.get(m).getRank() == 1)
						newRank[14]++;
				}
			}
		}

		// Checks from rank 10 to see if there are 5 consecutive Cards
		int count = 0;
		for (int n = 10; n < newRank.length; n++)
		{
			if (newRank[n] >= 1)
			{
				count++;
				// If there are 5 consecutive Cards, a royal flush is present
				if (count >= 5)
					return true;
			}
			else
				// Sets count back to 0 if there's a gap in between ranks
				count = 0;
		}
		return false;
	}

	/**
	 * Determines if a pair (2 Cards with the same rank) is present in the hand
	 * by examining the rank array starting from the given index
	 * @param index the index in the rank array at which the method starts to
	 *            look for pair
	 * @return true, if a pair is found in the hand; false if otherwise
	 */
	public boolean isPair(int index)
	{
		for (int n = index; n < rank.length; n++)
		{
			// Returns true if any element in the rank array is found to be
			// greater than or equal to 2
			if (rank[n] == 2)
				return true;
		}
		return false;
	}

	/**
	 * Determines if a two pair (2 different pairs) is present in the hand by
	 * examining the rank array
	 * @return true, if a two pair is found in the hand; false if otherwise
	 */
	public boolean isTwoPair()
	{
		for (int n = 2; n < rank.length; n++)
			// If one pair is found at index n, start searching for another pair
			// starting from n+1
			if (rank[n] == 2)
				if (isPair(n + 1))
					return true;
		return false;
	}

	/**
	 * Determines if a three-of-a-kind (3 Cards of the same rank) is present in
	 * the hand by examining the rank array starting from the given index
	 * @param start the index in the rank array at which the method starts to
	 *            look for three of a kind
	 * @return true, if a three-of-a-kind is found in the hand; false if
	 *         otherwise
	 */
	public boolean isThreeOfAKind(int start)
	{
		for (int n = start; n < rank.length - 1; n++)
			// If an element in the rank array is equal to 3, a three-of-a-kind
			// is found
			if (rank[n] == 3)
				return true;
		return false;
	}

	/**
	 * Determines if a four-of-a-kind is present in the hand by examining the
	 * rank array
	 * @return true, if a four-of-a-kind is found in the hand; false if
	 *         otherwise
	 */
	public boolean isFourOfAKind()
	{
		for (int n = 1; n < rank.length; n++)
			// If an element in the rank array is equal to 3, a four-of-a-kind
			// is found
			if (rank[n] == 4)
				return true;
		return false;
	}

	/**
	 * Determines if a full house is present in the hand by examining the rank
	 * array
	 * @return true, if 2 three-of-a-kind or 1 three-of-a-kind with a pair is
	 *         found in the hand; false if otherwise
	 */
	public boolean isFullHouse()
	{
		for (int n = 1; n < rank.length - 1; n++)
		{
			// If a three-of-a-kind is found
			if (rank[n] == 3)
			{
				// If another three-of-a-kind is found
				if (isThreeOfAKind(n + 1))
					return true;
				// If a pair is found
				if (isPair(0))
					return true;
			}
		}
		return false;
	}
}
