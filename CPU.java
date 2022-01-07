// Programmer: Rachel Sousa
// Date Created: 06/08/2018
// Date Revised: 06/18/2018
// Description: Abstract class for the CPU difficulties.

abstract class CPU extends Player
{
   // constructor
   // pre: string of player's name
   // post: name set
   public CPU(String n)
   {
      super(n);
   }
   
   // abstract method for takeTurn which will be used by the CPUs
   abstract int takeTurn(int t);
}