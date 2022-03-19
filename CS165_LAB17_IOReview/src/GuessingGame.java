
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    private int number;
    int guesses;
    Scanner input;
    /**TODO - Complete this Method
     * The constructor should initialize the number to a random value between 1 and 100
     * inclusive.(Hint: look up the Random class and the nextInt(int bound) method)
     *
     * The constructor should also initialize guesses to 0.
     *
     * The construct should also instantiate a new scanner that reads the System.in and assign it
     * to the class variable 'input'
     */
    public GuessingGame(){
        guesses = 0;
        input = new Scanner(System.in);
        Random r = new Random();
        number = r.nextInt(100) + 1;
    }
    /**
     * This method should use the classes scanner 'input' to read the next value from the command line
     * and return it's value as an Integer.(The InputMismatch exception should help you if you are using
     * the nextInt() method.
     *
     * Make sure to check that the input is an Integer before reading it. If it's not then
     * print 'Make sure your input is an Integer!' and call getUserInput() again.
     *
     * Hint: look at the javadoc here(https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html)
     * to see a full list of the methods a scanner has and the datatypes they return
     * @return the user's input
     */
    public int getUserInput(){
        //YOUR CODE HERE
        try{
            int r = input.nextInt();
            return r;
        }catch(InputMismatchException err){
            System.out.println("Make sure your input is an Integer!");
            input.next();
            return getUserInput();
        }
    }

    /**TODO - Complete this method
     * This method should return a message to the user telling them how close they are to
     * the correct number. A radius means within number + 2 and number - 2 inclusive of both
     * bounds. Below are the hints you should print:
     *
     * If your guess is within a radius of 2, the game should print 'on fire!'
     * If your guess is within a radius of 5, the game should print 'very hot!'
     * If your guess is within a radius of 10, the game should print 'warm'
     * If your guess is within a radius of 50 the game will print 'cold'
     * If your guess is more than 50 away then the game will print 'freezing'
     *
     *
     * @param guess - the user's guess
     * @return A string representing how close the user is to the right number
     */
    public String getHint(int guess){
        int diff = Math.abs(guess - number);
        if (diff <= 2) {
            return "on fire!";
        } else if (diff <= 5) {
            return "very hot!";
        } else if (diff <= 10) {
            return "warm";
        } else if (diff <= 50) {
            return "cold";
        } else {
            return "freezing";
        }
    }
    /**TODO - Complete this Method
     * This method should CLOSE THE SCANNER and
     * if the player won(didWin is true) then print the number of guesses it took them to get the right
     * answer like 'You Won!!!\n You took 5 guesses'
     * and if not, it should print a message that says 'Thanks for playing!'
     * @param didWin
     */
    private void endGame(boolean didWin){
        input.close();
        if(didWin) {
            System.out.printf("You Won!!!\nYou took %d guesses", guesses);
        } else {
            System.out.println("Thanks for playing!");
        }
    }

    public void play(){
        printPrompt();
        //comment in this line for testing
        System.out.println("the secret number is " + number);
        while(true){
            System.out.println("What's your guess?");
            int in = getUserInput();
            guesses++;
            if (in == -1) {
                endGame(false);
                break;
            }
            if (in == number) {
                endGame(true);
                break;
            }
            System.out.println("You are " + getHint(in));
        }
    }
    public void printPrompt(){
        String prompt = "Hello and Welcome to the guessing game.\n" +
                "In this game, a random number between 1 and 100 will be generated\n" +
                "and it is your job to guess that number\n" +
                "If your guess is within a radius of 10 around the number(answer + 10 or answer - 10)\n" +
                "then the system will print 'warm!'\n" +
                "If your guess is within a radius of 5, the game should print 'very hot!'\n" +
                "If your guess is within a radius of 2, the game should print 'on fire!'" +
                "If your guess is within a radius of 50 the game will print 'cold'\n" +
                "If your guess is more than 50 away then the game will print 'freezing'\n" +
                "\nEnter '-1' if you would like to quit.\n" +
                "***********************************************************************************";
        System.out.println(prompt);
    }
    public static void main(String[] args){
        GuessingGame myGame = new GuessingGame();
        myGame.play();
    }
}

