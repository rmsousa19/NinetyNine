// Programmer: Rachel Sousa
// Date Created: 05/28/2018
// Date Revised: 06/18/2018
// Description: Player class inherited by CPUs and User

import java.util.Random;

public class Player
{
   // declares variables used for class
   public Deck cardDeck = new Deck();
   public Hand playerHand;
   public int deckTotal;
   public String name;
   
   //Constructor
   //Pre: String including player name
   //Post: name saved and hand variable created
   public Player(String n)
   {
      name = n;
      // creates a new hand variable
      playerHand = new Hand(cardDeck.deal());
   }
   
   // get the player's hand
   // pre: none
   // post: int array with players cards
   public int[] getHand()
   {
      int[] hand = playerHand.getHand();
      return(hand);
   }
   
   // sets the players hand
   // pre: none
   // post: sets the players hand
   public void setHand()
   {
      playerHand = new Hand(cardDeck.deal());
   }
   
   // gets the players name
   // pre: none
   // post: returns a string of the player's name
   public String getName()
   {
      return(name);
   }
   
   // draws a new card for the player
   // pre: number for what position in the array to draw for
   // post; card drawn
   public int cardDraw(int n)
   {
      return(playerHand.drawCard(n));
   }
   
   // sets a new CPU
   // pre: number for choice of diffuculty
   // post: new CPU created.
   public static Player getPlayer(int i)
   {
      // if input is 1, returns easy CPU
      if(i == 1)
      {
         return(new Easy("Megan"));
      }
      // else, returns hard CPU
      else
      {
         return(new Hard("Aidan"));
      }
   }
}