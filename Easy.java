// Programmer: Rachel Sousa
// Date Created: 06/08/2018
// Date Revised: 06/18/2018
// Description: Class for Easy CPU

public class Easy extends CPU
{
   String name;
   int[] hand;
   
   // constructor
   // pre: string containing player's name
   // post: name sent to player class
   public Easy(String n)
   {
      super(n);
   }
   
   // player takes their turn
   // pre: int containing total
   // post: computer player's turn is taken by returning int for their play
   public int takeTurn(int t)
   {
      // declares variables for methods
      int total = t;
      int[] hand = getHand();
      int play;
      
      // plays the first card if it keeps the total under 99
      if(total + hand[0] < 99)
      {
         play = hand[0];
         hand[0] = cardDraw(0);
         return(play);
      }
      // plays the second card if it keeps the total under 99
      else if(total + hand[1] < 99)
      {
         play = hand[1];
         hand[1] = cardDraw(1);
         return(play);
      }
      // plays the third card
      else
      {
         play = hand[2];
         hand[2] = cardDraw(2);
         return(play);
      }
   }
}