package griffith;
import java.util.*;
import java.io.*;
import java.lang.Thread;
//Student Name: Chukwuemeka Wisdom Arinze
//Student Number: 2970177

public class BlackJack {
	// Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE
    public static final String RESET = "\u001B[0m";		//RESET
    
    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE
    
    //Background
    public static final String RED_TEXT = "\u001B[31m";
    public static final String GREEN_TEXT = "\u001B[32m";
    public static final String YELLOW_TEXT = "\u001B[33m";
    public static final String BLACK_BG = "\u001B[40m";
    public static final String WHITE_BG = "\u001B[47m";
    
    
    public void rulesBlackJack() { //this is displayed when the user wants to see the game rules.
    	System.out.println(BLACK_BOLD + "---------------------------------Welcome to the Black Jack Game!!---------------------------------------");
    	System.out.println("------------------------------------------------------RULES------------------------------------------------------" + RESET);
    	System.out.println(RED_BOLD + "- To start a new game, the user must enter the alphabet 'N', this will ask the user's how many people");
    	System.out.println("would like to play the game.");
    	System.out.println("- Remember the dealer will be the first person to get a card. So for example, if the user enters the number");
    	System.out.println("3, the game will automatically assign the dealer as one of the players. So it will be dealer + 2 other players");
    	System.out.println("- The users can then enter their name and the game will use the names given to assign each player their ");
    	System.out.println("cards. ");
    	
    	System.out.println(RESET);
    }
    
    public void quit() { //this is displayed when the user quits the game
    	System.out.println(BLACK_BG + WHITE_BOLD + "****************************************************************************************************");
    	System.out.println("*    Thank you for playing this version of the Black Jack Game.                                    *");
    	System.out.println("*    Hope you enjoyed playing the game and we hope to see you next time.                           *");
    	System.out.println("****************************************************************************************************" + RESET);
    }
	
	public void playBlackJack(String [] players) {
		Random rand = new Random();//the class helps us generate random numbers from 0 to 9
		Scanner scan = new Scanner(System.in); //this class helps us read data from the users
		
		boolean isWinner = false; //the boolean variable to check if we have found a winner or not
		int counter= 0; //this counter helps us to go round each player
		int round = 0;
		boolean cardChecker[][] = new boolean[4][13]; //this two dimensional array will help us keep track of each
													 //slot of our cards
		int [] scoreBoard = new int[players.length]; //this array will help to record each player's score
		
		String [] removedPlayers = new String[players.length]; //this array holds all the players that have been removed/skipped
		int removedCounter = 0;							//because their score exceeds 21.
		boolean removedPlayerFound = false;
		String winnerName = "";
		boolean canScore = false; //this operator tells us when to begin scoring
		
		//1 = Ace. The user decides if it is a 1 or 11
		//10 = Jack, 10 = Queen, King = 10
		
		String [] suits = {"spades", "diamond", "hearts", "clubs"};
		int selectedSuit = 0;
		int selectedNumber = 0;
		
		while(isWinner == false) {			
			try {
				
				//generate a random suit and random number only if they are true
				while(cardChecker[selectedSuit][selectedNumber] == true) {
					selectedSuit = rand.nextInt(3);
					selectedNumber = rand.nextInt(12);
				}
				
				String card = "";
				
				
				//this if statement is for changing the amount of round or knowing which round we are currently in.
				if((counter+removedCounter) % players.length == 0) { //adds the removedCounter to the counter so that as we are 
					round++;										//calculating the round number we will take into account the number  
				}													//of removed players.
				
				//this if statement asks the user if they ant to hit or stay. This if statement is triggered when it after the first round
				//To escape from the if statement, the user has to input one of the two numbers. If the user inputs 1, that will represent 
				//a hit, implying that the user would like to get a random card. If the user inputs 2, it implies that they would like to
				//skip their turn and pass to the next user.
				if(round > 1) {
					int hitOrStay = 0; 
					
						while(hitOrStay != 1 || hitOrStay != 2) {
						System.out.println(GREEN_BOLD + "-------------------------"+ players[counter] + ", Do you want to hit or stay----------------------" + RESET);
						System.out.println(GREEN_BOLD + "" + players[counter] + ", Enter 1 to hit and 2 to stay!!" + RESET);
						hitOrStay = scan.nextInt();
						
							if(hitOrStay == 1) { //hit 
								System.out.println(BLUE + "You have decided to HIT" + RESET);
								System.out.println();
								break;
							}
							if(hitOrStay == 2) { //stay
								if(counter == players.length-1) { //if the counter is equal to players.length -1, then the next player should
									counter = 0;				//the first player in the list hence the counter been equal to 0
									
								}else {
									counter++;
								}
								System.out.println(BLUE + "You have decided to STAY" + RESET);
								System.out.println();
							}
						}
					
				}
				
				
				//the first if statement makes sure that if a random number generated is 0, it will be an ace
				if(cardChecker[selectedSuit][selectedNumber] == false && selectedNumber == 0) {
					card = "ace of " + suits[selectedSuit];
					int aceNumber = 0;
					boolean aceChecker = false;
					
					System.out.println(GREEN_BOLD + players[counter] + ", you drew an ace. So you have a choice to make.");
					System.out.println("An ace is a 1 or an 11. So, input which of the two digits you want your "
							+ "ace to represent." + RESET);
					aceNumber = scan.nextInt(); //gets the user's response to either 1 or 11 for ace card
					
					//in case the user inputs the wrong number, they have a chance to input the correct answer.
					if(aceNumber == 1 || aceNumber == 11) {
						aceChecker = true;
					}else {
						while(aceChecker == false) {
							System.out.println("Please input which value you want your ace to represent. 1 or 11");
							aceNumber = scan.nextInt();
							if(aceNumber == 1 || aceNumber == 11) {
								aceChecker = true;
							}
						}
					}
					
					selectedNumber = aceNumber - 1; //this is subtracted because down the code we are adding to all
					cardChecker[selectedSuit][selectedNumber] = true;
					//scan.nextLine();
					
					
				}//this if statement makes sure that if the random number generated is 10, it will be a jack
				else if(cardChecker[selectedSuit][selectedNumber] == false && selectedNumber == 10) {
					card = "jack of " + suits[selectedSuit];
					cardChecker[selectedSuit][selectedNumber] = true;
					selectedNumber = 9;
					
				}//this if statement makes sure that if the random number generated is 11, it will be a queen
				else if(cardChecker[selectedSuit][selectedNumber] == false && selectedNumber == 11) {
					card = "queen of " + suits[selectedSuit];
					cardChecker[selectedSuit][selectedNumber] = true;
					selectedNumber = 9;
					
				}//this if statement makes sue that if the random number generated is 12, it will be a king
				else if(cardChecker[selectedSuit][selectedNumber] == false && selectedNumber == 12) {
					card = "king of " + suits[selectedSuit];
					cardChecker[selectedSuit][selectedNumber] = true;
					selectedNumber = 9;
					
				}else {
					//this if statement is for the rest of the numbers generated
					card = (selectedNumber + 1)+ " of " + suits[selectedSuit]; //number
					cardChecker[selectedSuit][selectedNumber] = true; //boolean value
				}
				
				//calculates the players score by adding its last score to it's current score
				//For each selectedNumber, 1 will be added to it because index starts from 0.
				scoreBoard[counter] = scoreBoard[counter] + (selectedNumber+1); 
				
				System.out.println(PURPLE_BOLD + "---------------------------------Round " + round + "----------------------------------------" + RESET);
				System.out.println("Player Name : " + players[counter]); //displays the player's name
				System.out.println("Card : " + card); //displays the player's current card
				System.out.println("Player Score : " + scoreBoard[counter]); //displays the player's current score
				
				if(scoreBoard[counter] >= 15) {
					canScore = true;
				}
				
				System.out.println();
				System.out.println();
				Thread.sleep(2000); // allows a 2 second sleep so that the game slows down and 
				//System.out.println("Suit: " + selectedSuit + " Number: " + selectedNumber);
				
				if(scoreBoard[counter] > 21) {
					System.out.println(players[counter] + " has be eliminated because their score is above 21. ");
					System.out.println("");
					
					for(int a  = 0 ; a<removedPlayers.length; a++) {
						if(players[counter] == removedPlayers[a]) {//if the name is already in the removedPlayers list break
							break;
						}else { //if the player name is not in the list, add it to the list.
							removedPlayers[removedCounter] = players[counter];
							removedCounter++;
						}
					}
				}
				
				//if statement that changes players each round
				if(counter == players.length - 1) {
					counter = 0;
				}else {
					counter++;
				}
				
				//this if statement makes sure that the current player is not in the removedPlayer array. if the player is found in 
				//this array, then it will be skipped.
				boolean isEligiblePlayer = false; 
				if(counter < players.length) {
					
					while(isEligiblePlayer == false) {
						for(int a = 0; a < removedPlayers.length; a++) {
							if(players[counter] == removedPlayers[a]) {
								isEligiblePlayer = false;
								counter++;
							}else {
								isEligiblePlayer = true;
								break;
							}
						}
					}
				}
				
				//Scoring section
				int highestScore = 0;
				int scoreIndex = 0;
				
				//the scoring starts from the second round.
				if(canScore == true && round >=2 && (counter + removedCounter) % players.length == 0) {
					boolean finished = false;
					boolean isAce = false;
					for(int a = 0; a < scoreBoard.length; a++) {//iterating through the score board so we can find the highest score
						if(scoreBoard[a] == 21) {//if the score is 21 that means its an ace.
							highestScore = scoreBoard[a];
							scoreIndex = a;
							isAce = true;
							
							winnerDisplay(players[a]); //call the winner method for players with a score of 21
							isWinner = true; //sets the variable to true so that the loop will break
							aceWinner();
							//break;
						}
						
						//this if statement is for finding out the player with the highest score.
						if(scoreBoard[a] > highestScore && scoreBoard[a] <= 21) {
							highestScore = scoreBoard[a];
							scoreIndex = a;
							finished = true;
							
						}
						
					}
					
					if(finished == true && isAce == false) {
						winnerDisplay(players[scoreIndex]); //call the winner method
						isWinner = true; //sets the variable to true so that the loop will break
						//break;
					}
				}
			
			}catch(Exception exception) {
				System.out.println(exception);
			}
		}
		
	}
	
	public static void aceWinner() { //this method gets displayed if a user gets exactly 21 which is an ace.
		System.out.println(BLACK_BG + CYAN_BOLD + "________________________________________________________________________________________");
		System.out.println("|         ***       *********  **********  * * *                        |");
		System.out.println("|        *   *      *          *           * * *                        |");
		System.out.println("|       * *** *     *          *********   * * *                        |");
		System.out.println("|      *       *    *          *           * * *                        |");
		System.out.println("|     *         *   *          *                                        |");
		System.out.println("|    *___________*__**********_**********__* *_*_____________________________________________________________|" + RESET);
		System.out.println();
	}
	
	public static void winnerDisplay(String winnerName) { //this method will be displayed when the player with the highest point is found
		System.out.println(BLACK_BG + CYAN_BOLD + "________________________________________________________________________________________");
		System.out.println("|    THE                                                                               |");
		System.out.println("|       WINNER                                                                         |");
		System.out.println("|             IS                                                                       |");
		System.out.println("|               " + winnerName + "                                                     |");
		System.out.println("|                                                                                      |");
		System.out.println("|______________________________________________________________________________________|" + RESET);
		System.out.println();
	}
}
