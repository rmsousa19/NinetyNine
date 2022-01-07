// Programmer: Rachel Sousa
// Date Created: 06/08/2018
// Date Revised: 06/18/2018
// Description: Main class that runs GUIs and gameplay

// imports the necessary packages for the program
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.lang.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

public class NinetyNine implements ActionListener
{
   // declares the JFrame variable necessary for the graphics
   JFrame frame;
   // declares the different JPanel components for the graphics
   JPanel main1, main2, main3;
   JPanel contentPane, compCardPane, compNamePane, userCardPane, userNamePane;
   JPanel totalPane, pilePane, deckPane, keyPane;
   JPanel pane1, pane2, pane3, pane4;
   // declares the different JButtons for the graphics
   JButton userCard1, userCard2, userCard3;
   JButton start, next, enter, next2, easy, hard, ace1, ace11, restart;
   // declares the different JLabels for the program
   JLabel compCard1, compCard2, compCard3;
   JLabel cardDeck, discard;
   JLabel welcome, rules, inst, border, border2, winner;
   JLabel tot, specialCards;
   JLabel namePrompt, compDiff;
   JLabel compName, usersName;
   JTextField userName;
   // declares the player variables
   User player;
   CPU comPlayer;
   // declares the timer used for basic animations
   Timer myTimer;
   // declares variables for some of the aesthetics for the program
   Color maroon = new Color(153, 0, 0);
   Color grey = new Color(153, 153, 153);
   Font headlineFont = new Font("Century Gothic", Font.BOLD, 36);
   Font smallFont = new Font("Century Gothic", Font.BOLD, 20);
   Font rulesFont = new Font("Century Gothic", Font.PLAIN, 15);
   // other variables used for the program
   String name;
   int[] userHand = new int[3];
   int[] compHand = new int[3];
   int counter;
   int total = 0;
   int lastPlay;
   int currentPlay, firstCard, compMove, turn;
   Deck deck = new Deck();
   
   public NinetyNine()
   {
      // creates the JFrame to hold the graphics
      frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      
      // creates the main content pane
      contentPane = new JPanel();
      contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
      
      // creates the three main JPanels
      main1 = new JPanel();
      main1.setBackground(maroon);
      main1.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
      main2 = new JPanel();
      main2.setBackground(maroon);
      main3 = new JPanel();
      main3.setBackground(maroon);
      main3.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
      
      // creates the separate JPanels for holding the different graphic components in
      pane1 = new JPanel(); //empty panel
      pane1.setBackground(maroon);
      pane1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      compCardPane = new JPanel(); //panel conatining the computer players cards
      compCardPane.setBackground(maroon);
      compCardPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      compNamePane = new JPanel(); //contains the comp players name
      compNamePane.setBackground(maroon);
      compNamePane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      pane2 = new JPanel(); //empty panel
      pane2.setBackground(maroon);
      pane2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      totalPane = new JPanel(); //contains the total of the pile
      totalPane.setBackground(maroon);
      totalPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      pilePane = new JPanel(); //middle pane containing the deck and the pile
      pilePane.setBackground(grey);
      pilePane.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
      pilePane.setPreferredSize(new Dimension(675,375));
      keyPane = new JPanel(); //contains the list of special cards
      keyPane.setBackground(maroon);
      keyPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      pane3 = new JPanel(); //empty panel
      pane3.setBackground(maroon);
      pane3.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      userCardPane = new JPanel(); //contains the users hand
      userCardPane.setBackground(maroon);
      userCardPane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      userNamePane = new JPanel(); //contains the users name
      userNamePane.setBackground(maroon);
      userNamePane.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      pane4 = new JPanel(); //empty pane
      pane4.setBackground(maroon);
      pane4.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
      
      
      // creates different JButtons and JLabels for the graphics of the game
      userCard1 = new JButton(new ImageIcon("Card Pictures//2.png"));
      userCard1.setActionCommand("1");
      userCard1.addActionListener(this);
      userCard1.setContentAreaFilled(false);
      userCard1.setBorderPainted(false);
      userCard1.setVisible(false);
      userCard2 = new JButton(new ImageIcon("Card Pictures//3.png"));
      userCard2.setActionCommand("2");
      userCard2.addActionListener(this);
      userCard2.setContentAreaFilled(false);
      userCard2.setBorderPainted(false);
      userCard2.setVisible(false);
      userCard3 = new JButton(new ImageIcon("Card Pictures//4.png"));
      userCard3.setActionCommand("3");
      userCard3.addActionListener(this);
      userCard3.setContentAreaFilled(false);
      userCard3.setBorderPainted(false);
      userCard3.setVisible(false);
      compCard1 = new JLabel(new ImageIcon("Card Pictures//back.jpg"));
      compCard1.setVisible(false);
      compCard2 = new JLabel(new ImageIcon("Card Pictures//back.jpg"));
      compCard2.setVisible(false);
      compCard3 = new JLabel(new ImageIcon("Card Pictures//back.jpg"));
      compCard3.setVisible(false);
      cardDeck = new JLabel(new ImageIcon("Card Pictures//back.jpg"));
      cardDeck.setVisible(false);
      discard = new JLabel(new ImageIcon("Card Pictures//back.jpg"));
      discard.setVisible(false);
      
      // adds the JButtons and JLabels to their respective panes
      pilePane.add(cardDeck);
      pilePane.add(discard);
      userCardPane.add(userCard1);
      userCardPane.add(userCard2);
      userCardPane.add(userCard3);
      compCardPane.add(compCard1);
      compCardPane.add(compCard2);
      compCardPane.add(compCard3);
      
      // creates more JLabels, JButtons and a JTextField for the program
      welcome = new JLabel("Ninety-Nine");
      welcome.setFont(headlineFont);
      welcome.setBorder(BorderFactory.createEmptyBorder(75,300,75,300));
      welcome.setAlignmentX(JLabel.CENTER_ALIGNMENT);
      rules = new JLabel("Rules");
      rules.setFont(headlineFont);
      rules.setBorder(BorderFactory.createEmptyBorder(10,300,10,300));
      rules.setAlignmentX(JLabel.CENTER_ALIGNMENT);
      rules.setVisible(false);
      inst = new JLabel("<html>The goal of the game Ninety-Nine is simple, don't let the total <br/>pass ninety-nine. Each player will take turns adding one of the three <br/>cards from their hand to the pile to add to the total. Players will use <br/>strategy to make sure they are not the one to bring the pile past <br/>ninety-nine. There are some special cards that have values that aren't their face value. <br/>Royal cards are equal to 10 unless specified otherwise.</html>");
      inst.setFont(rulesFont);
      inst.setAlignmentX(JLabel.CENTER_ALIGNMENT);
      inst.setVisible(false);
      namePrompt = new JLabel("Enter your Name");
      namePrompt.setFont(smallFont);
      namePrompt.setAlignmentX(JLabel.CENTER_ALIGNMENT);
      namePrompt.setVisible(false);
      userName = new JTextField(10);
      userName.setActionCommand("start game");
      userName.addActionListener(this);
      userName.setVisible(false);
      compName = new JLabel("James");
      compName.setFont(smallFont);
      compName.setForeground(Color.white);
      compName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
      compName.setVisible(false);
      usersName = new JLabel("");
      usersName.setFont(smallFont);
      usersName.setForeground(Color.white);
      usersName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
      usersName.setVisible(false);
      tot = new JLabel("Pile Total: " + total);
      tot.setFont(smallFont);
      tot.setForeground(Color.white);
      tot.setVisible(false);
      specialCards = new JLabel("<html>Special Cards<br/>A = 1<br/>K -> 99<br/>J = -10<br/>9 = 0</html>");
      specialCards.setFont(smallFont);
      specialCards.setForeground(Color.white);
      specialCards.setVisible(false);
      start = new JButton("Start");
      start.setActionCommand("Start");
      start.addActionListener(this);
      start.setFont(headlineFont);
      start.setBorder(BorderFactory.createEmptyBorder(20,30,20,30));
      start.setBackground(Color.gray);
      start.setForeground(Color.white);
      next = new JButton("Next");
      next.setActionCommand("Next");
      next.addActionListener(this);
      next.setFont(smallFont);
      next.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
      next.setBackground(Color.gray);
      next.setForeground(Color.white);
      next.setVisible(false);
      compDiff = new JLabel("Choose Computer Difficulty");
      compDiff.setFont(smallFont);
      compDiff.setForeground(Color.white);
      compDiff.setVisible(false);
      easy = new JButton("Easy");
      easy.setActionCommand("Easy");
      easy.addActionListener(this);
      easy.setFont(smallFont);
      easy.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
      easy.setBackground(Color.gray);
      easy.setForeground(Color.white);
      easy.setVisible(false);
      border = new JLabel(new ImageIcon("Card Pictures//border.png"));
      border.setVisible(true);
      border2 = new JLabel(new ImageIcon("Card Pictures//border.png"));
      border2.setAlignmentX(Component.BOTTOM_ALIGNMENT);
      border2.setVisible(true);
      winner = new JLabel("");
      winner.setFont(headlineFont);
      winner.setBorder(BorderFactory.createEmptyBorder(75,300,75,300));
      winner.setAlignmentX(JLabel.CENTER_ALIGNMENT);
      winner.setVisible(false);
      hard = new JButton("Hard");
      hard.setActionCommand("Hard");
      hard.addActionListener(this);
      hard.setFont(smallFont);
      hard.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
      hard.setBackground(Color.gray);
      hard.setForeground(Color.white);
      hard.setVisible(false);
      ace1 = new JButton("1");
      ace1.setActionCommand("Ace1");
      ace1.addActionListener(this);
      ace1.setFont(smallFont);
      ace1.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
      ace1.setBackground(Color.gray);
      ace1.setForeground(Color.white);
      ace1.setVisible(false);
      ace11 = new JButton("11");
      ace11.setActionCommand("Ace11");
      ace11.addActionListener(this);
      ace11.setFont(smallFont);
      ace11.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
      ace11.setBackground(Color.gray);
      ace11.setForeground(Color.white);
      ace11.setVisible(false);
      restart = new JButton("New Game");
      restart.setActionCommand("restart");
      restart.addActionListener(this);
      restart.setFont(smallFont);
      restart.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
      restart.setBackground(Color.gray);
      restart.setForeground(Color.white);
      restart.setVisible(false);
      
      //adds all the components to their respective panes.
      pilePane.add(welcome);
      pilePane.add(rules);
      pilePane.add(inst);
      pilePane.add(namePrompt);
      pilePane.add(userName);
      pane2.add(compName);
      pane4.add(usersName);
      totalPane.add(tot);
      keyPane.add(specialCards);
      pilePane.add(start);
      pilePane.add(next);
      pilePane.add(compDiff);
      pilePane.add(easy);
      pilePane.add(hard);
      pilePane.add(winner);
      pilePane.add(restart);
      pane4.add(ace1);
      pane4.add(ace11);
      main1.add(pane1);
      main1.add(compCardPane);
      main1.add(compNamePane);
      main1.add(pane2);
      main1.add(border);
      main2.add(totalPane);
      main2.add(pilePane);
      main2.add(keyPane);
      main3.add(pane3);
      main3.add(userCardPane);
      main3.add(userNamePane);
      main3.add(pane4);
      main3.add(border2);
      
      // add all the components to the three main frames.
      contentPane.add(main1);
      contentPane.add(main2);
      contentPane.add(main3);
      
      // adds the content pane to the JFrame
      frame.setContentPane(contentPane);
      
      frame.pack();
      frame.setVisible(true);
   }
   
   //Sets the users cards to the correct image
   //pre: array containing the users cards
   //post: cards are set to the correct images
   public void setCardImage(int[] u)
   {
      // changes the button icons to the correct cards
      userCard1.setIcon(new ImageIcon("Card Pictures//" + u[0] + ".png"));
      userCard2.setIcon(new ImageIcon("Card Pictures//" + u[1] + ".png"));
      userCard3.setIcon(new ImageIcon("Card Pictures//" + u[2] + ".png"));
   }
   
   //reacts to an event
   //pre: user caused event must have occured
   //post: GUI, player stats, and game progression are updated
   public void actionPerformed(ActionEvent event)
   {
      // String variable containing event name of action listener
      String eventName = event.getActionCommand();
      
      // new action listener for timer (nested class)
      ActionListener myLis = new ActionListener()
      {
         
         //timer that reacts to an event, continues to be called until gameplay is ready for the next user action
         //pre: user event must have occured
         //post: GUI, player stats and game progression are updated
         public void actionPerformed(ActionEvent e)
         {
               // String variable containing event name of action listener
               String ev = e.getActionCommand();
               
               // runs if event name is set to deal
               if(ev.equals("Deal"))
               {  
                  // pauses for half a second
                  try{ Thread.sleep(500); } catch (Exception ex){}
                  
                  // uses counter to run through and "deal" cards
                  switch(counter)
                  {
                     case 0: userCard1.setVisible(true); break;
                     case 1: compCard1.setVisible(true); break;
                     case 2: userCard2.setVisible(true); break;
                     case 3: compCard2.setVisible(true); break;
                     case 4: userCard3.setVisible(true); break;
                     case 5: compCard3.setVisible(true); break;
                     default: discard.setIcon(new ImageIcon("Card Pictures//" + firstCard + ".png"));
                  }            
                  counter += 1;
                  
                  //stops the timer once all cards are dealt
                  if (counter  > 6)
                  {
                     myTimer.stop();
                  }
               }
               
               //runs is the event name is equal to "UserTurn"
               else if(ev.equals("UserTurn"))
               {
                  // pauses for half a second
                  try{ Thread.sleep(500); } catch (Exception ex) {}
                  
                  // runs if user chooses the first card in their hand
                  if(eventName.equals("1"))
                  {
                     // gets the value of the current card
                     currentPlay = (userHand[0] + 2) % 13;
                     
                     // runs if the play keeps the total under 100
                     if((currentPlay + total) < 100 || currentPlay == 9 || currentPlay == 11 || currentPlay == 0)
                     {
                        // sets the discard pile image to the card just played
                        discard.setIcon(new ImageIcon("Card Pictures//" + userHand[0] + ".png"));
                        
                        // runs if the card played is one of the special cards
                        if (currentPlay == 9 || currentPlay == 11 || currentPlay == 0 || currentPlay == 1  || currentPlay == 12)
                        {
                           specialCards(currentPlay);
                        }
                        // runs if the card is not a special card
                        else
                        {
                           // adds the card to the total
                           total += currentPlay;
                        }
                        //draws a new card for the players hand
                        player.cardDraw(0);
                        userHand = player.getHand();
                        // sets the users card to the appropriate image
                        setCardImage(userHand);
                        // sets the pile total label to the appropriate total
                        tot.setText("Pile Total: " + total);
                     }
                     //runs if the total passes 99
                     else
                     {
                        // changes the text of the winner pane
                        winner.setText("You Lose");
                        // ends games
                        endGame();
                     }
                  }
                  // runs if user chooses the second card in their hand
                  else if(eventName.equals("2"))
                  {
                     // gets the value of the current card
                     currentPlay = (userHand[1] + 2) % 13;
                     
                     // runs if the play keeps the total under 100
                     if((currentPlay + total) < 100 || currentPlay == 9 || currentPlay == 11 || currentPlay == 0)
                     {
                        // sets the discard pile image to the card just played
                        discard.setIcon(new ImageIcon("Card Pictures//" + userHand[1] + ".png"));
                        
                        // runs if the card played is one of the special cards
                        if (currentPlay == 9 || currentPlay == 11 || currentPlay == 0 || currentPlay == 1  || currentPlay == 12)
                        {
                           specialCards(currentPlay);
                        }
                        // runs is the card played isn't a special card
                        else
                        {
                           total += currentPlay;
                        }
                        //draws a new card for the players hand
                        player.cardDraw(1);
                        userHand = player.getHand();
                        // sets the users card to the appropriate image
                        setCardImage(userHand);
                        // sets the pile total label to the appropriate total
                        tot.setText("Pile Total: " + total);
                     }
                     //runs if the total passes 99
                     else
                     {
                        // changes the text of the winner pane
                        winner.setText("You Lose");
                        // ends games
                        endGame();
                     }
                  }
                  // runs if user chooses the third card in their hand
                  else
                  {
                     // gets the value of the current card
                     currentPlay = (userHand[2] + 2) % 13;
                     
                     // runs if the play keeps the total under 100
                     if((currentPlay + total) < 100 || currentPlay == 9 || currentPlay == 11 || currentPlay == 0)
                     {
                        // sets the discard pile image to the card just played
                        discard.setIcon(new ImageIcon("Card Pictures//" + userHand[2] + ".png"));
                        
                        // runs if the card played is one of the special cards
                        if (currentPlay == 9 || currentPlay == 11 || currentPlay == 0 || currentPlay == 1  || currentPlay == 12)
                        {
                           specialCards(currentPlay);
                        }
                        // runs is the card played isn't a special card
                        else
                        {
                           total += currentPlay;
                        }
                        //draws a new card for the players hand
                        player.cardDraw(2);
                        userHand = player.getHand();
                        // sets the users card to the appropriate image
                        setCardImage(userHand);
                        // sets the pile total label to the appropriate total
                        tot.setText("Pile Total: " + total);
                     }
                     // runs if the total passes 99
                     else
                     {
                        // changes the text of the winner pane
                        winner.setText("You Lose");
                        // ends games
                        endGame();
                     }
                  }
                  // sets the timer to play the computer players turn after the user takes a turn
                  ((Timer)e.getSource()).setActionCommand("CompTurn"); 
               }
               // runs if the event name equals "CompTurn"
               else if(ev.equals("CompTurn"))
               {  
                  // pauses for two seconds
                  try{ Thread.sleep(2000); } catch (Exception ex) {}
                  
                  //gets the computer players move
                  compMove = comPlayer.takeTurn(total);
                  
                  // gets the value of the computers move
                  currentPlay = (compMove + 2) % 13;
                  
                  // runs if the play keeps the total under 100
                  if((currentPlay + total) < 100)
                  {
                     // runs if the card played is one of the special cards
                     if (currentPlay == 9 || currentPlay == 11 || currentPlay == 0 || currentPlay == 1 || currentPlay == 12)
                     {
                        specialCards(currentPlay);
                     }
                     // runs is the card played isn't a special card
                     else
                     {
                        total += currentPlay;
                     }
                     compHand = comPlayer.getHand();
                     tot.setText("Pile Total: " + total);
                     discard.setIcon(new ImageIcon("Card Pictures//" + compMove + ".png"));
                  }
                  // runs if the total passes 99
                  else
                  {
                     // changes the text of the winner pane
                     winner.setText("You Win!");
                     // ends game
                     endGame();
                  }
                  // stops the timer
                  myTimer.stop();
               }
            }     
         }; 
         
         //arguments are the default time between timer iterations and the actionlistener
         myTimer = new Timer(0, myLis);
         
         // runs if the event name equals "Start"
         if(eventName.equals("Start"))
         {
            // hides these variables
            welcome.setVisible(false);
            start.setVisible(false);
            // sets these variables as visible
            rules.setVisible(true);
            inst.setVisible(true);
            next.setVisible(true);
         }
         
         // runs if the event name equals "Next"
         else if(eventName.equals("Next"))
         {
            // hides these variables
            rules.setVisible(false);
            next.setVisible(false);
            inst.setVisible(false);
            // sets these variables as visibles
            easy.setVisible(true);
            hard.setVisible(true);
         }
         
         // runs if the event name equals "Easy"
         else if (eventName.equals("Easy"))
         {
            // hides these variables
            hard.setVisible(false);
            easy.setVisible(false);
            // creates the easy computer player variable
            comPlayer = (CPU)Player.getPlayer(1);
            compHand = comPlayer.getHand();
            // sets these variables as visible
            namePrompt.setVisible(true);
            userName.setVisible(true);
         }
         
         // runs if the event name equals "Hard"
         else if (eventName.equals("Hard"))
         {
            // hides these variables
            hard.setVisible(false);
            easy.setVisible(false);
            // creates the hard computer player variable
            comPlayer = (CPU)Player.getPlayer(0);
            compHand = comPlayer.getHand();
            // sets these variables as visible
            namePrompt.setVisible(true);
            userName.setVisible(true);
         }
         
         // runs when event name equals "start game"
         else if(eventName.equals("start game"))
         {
            // creates the user variable
            name = userName.getText();
            player = new User(name);
            //hides these variables
            easy.setVisible(false);
            hard.setVisible(false);
            userName.setVisible(false);
            namePrompt.setVisible(false);
            border.setVisible(false);
            border2.setVisible(false);
            // sets the users hand
            userHand = player.getHand();
            // calls on the setCardImage method
            setCardImage(userHand);
            // sets the text of the usersName and userLives panel
            usersName.setText(name);
            // sets the text of the compName and compLives panel
            compName.setText(comPlayer.getName());
            // sets these variables as visible
            compName.setVisible(true);
            usersName.setVisible(true);
            cardDeck.setVisible(true);
            discard.setVisible(true);
            tot.setVisible(true);
            specialCards.setVisible(true);
            // draws the first card
            firstCard = deck.draw();
            // gets the value of the first card
            currentPlay = (firstCard + 2) % 13;
            // calls on the specialCards method if the first card is a special card
            if (currentPlay == 9 || currentPlay == 11 || currentPlay == 0 || currentPlay == 1  || currentPlay == 12)
            {
               specialCards(currentPlay);
            }
            // changes the total if the card isn't a special card
            else
            {
               total += currentPlay;
            }
            // changes the text for the total pane
            tot.setText("Pile Total: " + total);
            // starts the timer to deal
            myTimer.setActionCommand("Deal");
            myTimer.start();
         }
         // runs if the user chooses the first card in their hand
         else if (eventName.equals("1"))
         {
            // sets the timer to UserTurn
            myTimer.setActionCommand("UserTurn");
            // starts the timer
            myTimer.start();
         }
         // runs if the user chooses the second card in their hand
         else if (eventName.equals("2"))
         {
            // sets the timer to UserTurn
            myTimer.setActionCommand("UserTurn");
            // starts the timer
            myTimer.start();
         }
         // runs if the user chooses the third card in their hand
         else if (eventName.equals("3"))
         {
            // sets the timer to UserTurn
            myTimer.setActionCommand("UserTurn");
            // starts the timer
            myTimer.start();
         }
         // runs when the user pressed the "New Game" button
         else if(eventName.equals("restart"))
         {
            newGame();
         }
   }     
   
   // changes the total for special cards
   // pre: card value
   // post: changes the total
   public void specialCards(int n)
   {
      // sets total for the 9
      if(n == 9)
      {
         total += 0;
      }
      // sets total for the Jack
      else if(n == 11)
      {
         total -= 10;
      }
      // sets total for the Queen
      else if(n == 12)
      {
         total += 10;
      }
      // sets total for the King
      else if(n == 0)
      {
         total = 99;
      }
      // sets total for the Ace
      else if(n == 1)
      {
         total += 1;
      }
   }
   
   // ends the game
   // pre: none
   // post: hides all variables and shows the winner panel and the restart button
   private void endGame()
   {
      // stops the timer
      myTimer.stop();
      // hides all the cards
      userCard1.setVisible(false);
      userCard2.setVisible(false);
      userCard3.setVisible(false);
      compCard1.setVisible(false);
      compCard2.setVisible(false);
      compCard3.setVisible(false);
      usersName.setVisible(false);
      compName.setVisible(false);
      discard.setVisible(false);
      cardDeck.setVisible(false);
      // hides the key and the total
      specialCards.setVisible(false);
      tot.setVisible(false);
      discard.setIcon(new ImageIcon("Card Pictures//back.png"));
      // shows the winner pane and restart button
      winner.setVisible(true);
      restart.setVisible(true);
   }
   
   // restarts the game
   // pre: none
   // post: restarts the game by dealing cards and setting properties visible
   private void newGame()
   {
      // hides the restart button and the winner panel
      restart.setVisible(false);
      winner.setVisible(false);
      // resets the total and the counter
      total = 0;
      counter = 0;
      // resets the deck
      deck.restart();
      // sets buttons visible
      easy.setVisible(true);
      hard.setVisible(true);
   }
   
   //Runs game
   //pre: none
   //post: game of ninety-nine is run
   private static void runGUI()
   {
      JFrame.setDefaultLookAndFeelDecorated(false); //sets look and feel
      NinetyNine game = new NinetyNine(); //creates a new ninety-nine game
   }
   
   public static void main(String[] args)
   {// main method
      javax.swing.SwingUtilities.invokeLater(new Runnable()
      {// event dispatching thread
         public void run()
         {
            runGUI();
         }
      });
   }
}