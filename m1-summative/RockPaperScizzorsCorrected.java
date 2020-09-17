package sg.am.lesson7;


import java.util.Random;
import java.util.Scanner;

/*
 * Milestone 1
 */

/**
 *
 * @author afsanamiji
 * last revised 10.12.2019
 */
public class RockPaperScizzors {
    
        
        
        public void rPS(){
           
      
        Scanner userInput = new Scanner(System.in);
        Random sc = new Random();
        int rounds;    // how many times the user wants to play
        int ties = 0;  // how many times the use and comp pick the same thing
        int userWins = 0;  // keeps track how many times user wins.
        int compWins = 0;  // keeps track how many times the comp wins
        int compPick;   //comps random pick
        int userPick;   //user's scanner input
        int roundsCounter= 0;  // counts how many rounds played in total
        String playAgain = "yes";  //condition needed to trigger do while loop
       
        do {
        System.out.print("How many rounds of Rock, Paper Scizzors would you like to play? 1-10 ");
        rounds = userInput.nextInt();
        //how do i make it accept only a number? 
        
        /*while (rounds > 10 || rounds < 1 ){
        System.out.println("Error: Please enter a correct value.");
        rounds = userInput.nextInt();
        //repeats till you enter valid number of rounds allowed
        }*/
           
        if (rounds <= 10 && rounds > 0 ){
            //if rounds is within ten
            System.out.println("Great! You wish to play: " + rounds + " Rounds.");
        }else{
            System.out.println("You did not enter the correct number of rounds");
            break;
        }
            
           for (int i = 0; i < rounds; i++){
               // for the humber of rounds...please do this....
               roundsCounter++;
               //counts how many rounds played. never resets even when game restarts
               System.out.println("This is round : " + roundsCounter);  
               
               System.out.println("Please type 1 for Rock, 2 for Paper and 3 for Scizzors. ");
               userPick = userInput.nextInt();
               System.out.println("You picked : " + userPick);
               
                     while (userPick > 3 || userPick < 1){
                     System.out.println("Error. Please type  a valid entry. "); 
                     userPick = userInput.nextInt();
                    //will repeat till user types 1, 2 or 3. error message. more user friendly
                    }
                
            compPick = sc.nextInt(3) + 1;
                // assigning the computers turn/pick. random out of 3. 1-3
           System.out.println("The computer picked : " + compPick);
           
            if (userPick == compPick){
                ties++;
                System.out.println("Tie!");
                // if the same thing is picked. tie.
                
                }else if(userPick == 1 && compPick == 2){
                compWins++;
                System.out.println("Computer wins this round!");
                //if user picks rock and comp picks paper, comp wins.
                
                 }else if(userPick == 1 && compPick == 3){
                userWins++;
                System.out.println("You win this round!");
                // if user picks rock and comp picks scizzors, user wins.
                
                 }else if(userPick == 2 && compPick == 1){
                userWins++;
                System.out.println("You win this round!");
                //user picks paper and comp picks rock, user wins.
                
                }else if(userPick == 2 && compPick == 3){
                compWins++;
                System.out.println("Computer wins this round!"); 
                // user paper, comp scizzors, comp wins.
                
                 }else if(userPick == 3 && compPick == 1){
                compWins++;
                System.out.println("Computer wins this round!");
                // user scizzors, comp rock, comp wins.
                
                 }else if(userPick == 3 && compPick == 2){
                userWins++;
                System.out.println("You win this round!");
                //user scizzors, comp paper, user wins.
                }   
           }   
        System.out.println("Number of ties: " + ties);
        System.out.println("Number of times you won: " + userWins);
        System.out.println("Number of times the computer won: " + compWins);
        
        if (userWins > compWins){
            System.out.println("YOU ARE THE WINNER! GO GET SOME ICE CREAM!!!");
            }else if(compWins > userWins ){
            System.out.println("THE COMPUTER WON. IT WILL PROCEED TO TAKE OVER THE WOLRDDD");
            }else if (userWins == compWins){
            System.out.println("WAT-TA GAME WAT-TA TIE!");
         }
        
        System.out.println("Would you like to play again? yes/no");
        //the answer will trigger if the loop runs again or not. 
        playAgain = userInput.nextLine();
        
           while(((playAgain.compareTo("yes") > 0) || (playAgain.compareTo("yes") < 0))  && ((playAgain.compareTo("no") > 0) || (playAgain.compareTo("no") < 0))){
               //this loops is to make it user friendly, so if the user has a typo it asks them again. maybe there is a better way to do this. 
               System.out.println("Please enter a valid entry");
               playAgain = userInput.nextLine();
               
               //this runs no matter what. a bug. how do i fix.
           }
        /*do {
        System.out.println("Would you like to play again? yes/no");
        playAgain = userInput.nextLine();
        
        //this is so if you enter something other than yes or no, it lets you pick again.
        }while (((playAgain.compareTo("yes") > 0) || (playAgain.compareTo("yes") < 0))  && ((playAgain.compareTo("no") > 0) || (playAgain.compareTo("no") < 0)));
        */
        if (playAgain.equals("no")){
            System.out.println("Thanks for playing!");
            //if no, it breaks out of the loop.
            } 
        }  while (playAgain.equals("yes"));
     }
        // if yes, go back to the top and run the entire code again.
}


    
