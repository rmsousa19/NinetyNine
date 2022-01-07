// Programmer: Rachel Sousa
// Date Created: 05/28/2018
// Date Revised: 06/18/2018
// Description: Class that controls the deck for the game

import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
   // declares variables for class
   int[] cards;
   public static ArrayList<Integer> cardDeck = new ArrayList<Integer>();
   int numCards = 52;
   
   // constructor
   // pre: none
   // post: puts together a deck of 52 cards
   public Deck()
   {
      // adds cards to an arrayList and shuffles them
      for(int i = 0; i < 52; i++)
      {
         cardDeck.add(i);
      }
      shuffle();
   }
   
   // shuffles the deck
   // pre: none
   // post: cards are put in a random order
   public void shuffle()
   {
      Collections.shuffle(cardDeck);
   }
   
   // resets the deck
   // pre: none
   // post: current deck is cleared and cards are added back into the deck and shuffled.
   public void restart()
   {
      // deck is clears
      cardDeck.clear();
      numCards = 52;
      
      // deck is created and shuffled
      for(int i = 0; i < 52; i++)
      {
         cardDeck.add(i);
      }
      
      shuffle();
   }
   
   // draws a card
   // pre: none
   // post: a card is drawn
   public int draw()
   {
      // card is drawn and removed from the deck
      Integer card = cardDeck.get(numCards - 1);
      cardDeck.remove(numCards - 1);
      numCards -= 1;
      // card is returned
      return(card);
   }
   
   // deals hands
   // pre: none
   // post: int array is returned containing the player's hand
   public int[] deal()
   {
      // variable created
      int[] hand = new int[3];
      
      // hand created
      for(int i = 0; i < 3; i++)
      {
         hand[i] = draw();
      }
      
      // hand returned
      return(hand);
   }
}