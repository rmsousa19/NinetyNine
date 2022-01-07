// Programmer: Rachel Sousa
// Date Created: 06/08/2018
// Date Revised: 06/18/2018
// Description: Class that extends the deck and controls the players' hands

public class Hand extends Deck
{
   // declares variable for the class
   int[] hand = new int[3];
   
   // construtor
   // pre: int array containing players hand
   // post: sets hand
   public Hand(int[] h)
   {
      // sets hand
      hand = h;
   }
   
   // gets the players hand
   // pre: none
   // post: returns the player's hand
   public int[] getHand()
   {
      return(hand);
   }
   
   // sets the hand
   // pre: int array containing players hand
   // post: sets the hand equal to the imported array
   public void setHand(int[] h)
   {
      hand = h;
   }
   
   // gets the card in the player's hand asked for
   // pre: int for what position is wanted
   // post: returns card value
   public int getCard(int i)
   {
      return(hand[i]);
   }
   
   // draws a card
   // pre: element of array that will be replaced
   // post: new card is drawn and returned
   public int drawCard(int i)
   {
      hand[i] = draw();
      return(hand[i]);
   }
}