import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Hand
{
	protected ArrayList<Card> hand;

	// static boolean isBusted=false;
	// constructor for read in a file when hand of cards is given
	public Hand(File file) throws FileNotFoundException
	{
		Scanner readFile = new Scanner(file);

		readFile.useDelimiter(" ");
		this.hand = new ArrayList<Card>();
		while (readFile.hasNext())
		{
			Card newCard = new Card(readFile.next());
			this.hand.add(newCard);
		}
		readFile.close();
	}

	// constructor for an empty hand
	public Hand(String cards)
	{
		Scanner readString = new Scanner(cards);
		readString.useDelimiter(" ");
		this.hand = new ArrayList<Card>();
		while (readString.hasNext())
		{
			Card newCard = new Card(readString.next());
			this.hand.add(newCard);
		}
		readString.close();
	}

	public Hand()
	{
		this.hand = new ArrayList<Card>();
	}

	public void addCard(Card card)
	{
		hand.add(card);
	}

	// Return the total value of the cards in hand
	public int getValue()
	{
		int score = 0;
		int numberOfAce = 0;
		for (Card currentCard : hand)
		{
			score += currentCard.getValue();
			if (currentCard.isAce())
				numberOfAce++;
		}
		while (numberOfAce != 0)
		{
			score = score - 10;
			numberOfAce--;
		}
		return score;
	}

	// return the five cards in hand to a string
	public String toString()
	{
		String returnString = "";
		for (Card currentCard : this.hand)
		{
			returnString += currentCard.toString() + " ";
		}
		return String.format("%s", returnString);
	}

	// if there are only two cards in the hand, and the two card's value add up
	// to 21 point return true
	public boolean isBlackJack()
	{
		if (getValue() == 21 && hand.size() == 2)
			return true;
		return false;
	}

	// clear the current hand of cards
	public void clear()
	{
		hand.clear();
	}

	// sort the five cards in hand by their rank
	public void sortByRank()
	{
		Collections.sort(hand);
	}

	// sort the five cards in hand by their suit
	public void sortBySuit()
	{
		Collections.sort(hand, Card.SUIT_ORDER);
	}

}
