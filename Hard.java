// Programmer: Rachel Sousa
// Date Created: 06/08/2018
// Date Revised: 06/18/2018
// Description: Class for the Hard CPU

public class Hard extends CPU
{
   // declares variables for class
   String name;
   int hand[] = new int[3];
   
   // constructor
   // pre: string containing players name
   // post: name sent to player class
   public Hard(String n)
   {
      super(n);
   }
   
   // players takes their turn
   // pre: int containing total
   // post: Computer player's turn is taken by returning their play
   public int takeTurn(int t)
   {
      // declares variables needed for method
      hand = getHand();
      int play1 = (hand[0] + 2) % 13;
      int play2 = (hand[1] + 2) % 13;
      int play3 = (hand[2] + 2) % 13;
      int turn;
      
      // if the total is less than 70, the player plays their highest card that isn't a special card
      if(t < 70)
      {
         // checks if the card is the highest card and if it's not a special card
         if(play1 > play2 && play1 > play3 && play1 != 9 && play1 != 0 && play1 != 11 && play1 != 1 && (t + play1) < 100)
         {
            turn = hand[0];
            hand[0] = cardDraw(0);
            return(turn);
         }
         else if(play2 > play1 && play2 > play3 && play2 != 9 && play2 != 0 && play2 != 11 && play2 != 1 && (t + play2) < 100)
         {
            turn = hand[1];
            hand[1] = cardDraw(1);
            return(turn);
         }
         else
         {
            turn = hand[2];
            hand[2] = cardDraw(2);
            return(turn);
         }
      }
      // if the total is greater than 70 but less than 90, the player plays the lowest card that's not a special card
      else if(t < 90)
      {
         // checks if the card is the highest card and if it's not a special card
         if(play1 < play2 && play1 < play3 && play1 != 9 && play1 != 0 && play1 != 11 && play1 != 1 && (t + play1) < 100)
         {
            turn = hand[0];
            hand[0] = cardDraw(0);
            return(turn);
         }
         else if(play2 < play1 && play2 < play3 && play2 != 9 && play2 != 0 && play2 != 11 && play2 != 1 && (t + play2) < 100)
         {
            turn = hand[1];
            hand[1] = cardDraw(1);
            return(turn);
         }
         else
         {
            turn = hand[2];
            hand[2] = cardDraw(2);
            return(turn);
         }
      }
      // if the total is over 90 and under 100 the player plays a card that won't bring the total over 90 or a special card
      else if(t < 100)
      {
         // checks if the card doesn't bring the total over 99 and if it's not a special card
         if((t + play1) < 100 && play1 != 9 && play1 != 0 && play1 != 11 && play1 != 1)
         {
            turn = hand[0];
            hand[0] = cardDraw(0);
            return(turn);
         }
         else if((t + play2) < 100 && play2 != 9 && play2 != 0 && play2 != 11 && play2 != 1)
         {
            turn = hand[1];
            hand[1] = cardDraw(1);
            return(turn);
         }
         else if((t + play3) < 100 && play3 != 9 && play3 != 0 && play3 != 11 && play3 != 1)
         {
            turn = hand[2];
            hand[2] = cardDraw(2);
            return(turn);
         }
         
         // checks if the card is a special card
         else if(play1 == 9 || play1 == 0 || play1 == 11 || play1 == 1 && (t + play1) < 100)
         {
            turn = hand[0];
            hand[0] = cardDraw(0);
            return(turn);
         }
         else if(play2 == 9 || play2 == 0 || play2 == 11 || play2 == 1 && (t + play2) < 100)
         {
            turn = hand[1];
            hand[1] = cardDraw(1);
            return(turn);
         }
         else
         {
            turn = hand[2];
            hand[2] = cardDraw(2);
            return(turn);
         }
      }
      else
      {
         return(100);
      }
   }
}