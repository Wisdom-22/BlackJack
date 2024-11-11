package griffith;
import java.util.*;
import java.io.*;
import java.lang.Thread;
//Student Name: Chukwuemeka Wisdom Arinze
//Student Number: 2970177

public class BlackJackMain {

	//Background
    public static final String RED_TEXT = "\u001B[31m";
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_TEXT = "\u001B[33m";
    public static final String BLACK_BG = "\u001B[40m";
    public static final String WHITE_BG = "\u001B[47m";
    public static final String RESET = "\u001B[0m";		//RESET
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		BlackJack blackJack = new BlackJack();
		char character = 'A';
		
		while(character != 'Q') {
			System.out.println(BLACK_BG + YELLOW_TEXT + "|---------------------------------------------------------------------------------------------------------------------------------------------------|");
			System.out.println("|---------------------------------------------WELCOME TO THE CONSOLE VERSION OF THE BLACK JACK GAME.-------------------------------------------------|");
			System.out.println("|                                                      To Play a New Game, Press N.                                                                  |");
			System.out.println("|                                                      To see the Rules Press R.                                                                     |");
			System.out.println("|                                                      To Quit the game press Q.                                                                     |");
			System.out.println("|---------------------------------------------------------------------------------------------------------------------------------------------------|" + RESET);
			character = scan.next().charAt(0);
			
			if(character >= 65 && character <= 90) {
				character = character;
			}
			else if(character >= 97 && character <= 122) {
				character = (char) (character - 32);
			}
			else {
				System.out.println("You entered the wrong option, please try again");
			}
			
			if(character == 'N') {
				System.out.println("How many people will like to play BlackJack!!!");
				int numPlayers = scan.nextInt();
				scan.nextLine();
				System.out.println("");
				
				String [] playerNames = new String[numPlayers];
				playerNames[0] = "Dealer";
				System.out.println(GREEN_BOLD + "Player's 1 Name is Dealer. " + RESET);
				System.out.println("");
				
				//then the loop starts from 1 because the first player will be dealer.
				try {
					//this loop collects the players name
					for(int a = 1; a < playerNames.length; a++) {
						System.out.println("Please enter player's " + (a + 1) + " Name: " );
						playerNames[a] = scan.nextLine();			
							
						System.out.println(GREEN_BOLD + playerNames[a] + " is player " + (a+1) + RESET);
						System.out.println();
					}
					
					Thread.sleep(1000); //waits for 1 second
				}catch(Exception exception) {
					System.out.println(exception);
				}
				
				blackJack.playBlackJack(playerNames);//this is the method that allows the user to play the game
			}
			else if(character == 'R') { //this if statement is if the user presses the R key to see the rules
				blackJack.rulesBlackJack();
				System.out.println();
			}
			
			else if(character == 'Q') { //this if statement is if the user presses the Q key to quit the game
				blackJack.quit();
			}
		}
		

		
	}

}
