// Programmer: Rachel Sousa
// Date Created: 06/08/2018
// Date Revised: 06/18/2018
// Description: Class for the user variable

public class User extends Player
{
   // declates variables
   String name;
   
   // constructor
   // pre: string including player's name
   // post: name sent to player class
   public User(String n)
   {
      super(n);
      name = n;
   }
   
   // sets the players name
   // pre: string with name
   // post: name is set
   public void setName(String n)
   {
      name = n;
   }
   
   // gets the players name
   // pre: none
   // post: returns a string with the players name
   public String getName()
   {
      return(name);
   }
}