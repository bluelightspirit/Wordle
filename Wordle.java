import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Wordle
 * <p>
 *
 * @author Gary Young
 * @version 01/14/2023
 * Wordle directly runs the main method that runs other methods,
 * so that the user plays Wordle & tries to guess the randomly chosen word, given hints, or by guessing their first try.
 */

// the Wordle class,
// that can handle all the instance variables that are the global reference variables the methods can access
// and can handle many methods created inside of it
// purpose: to handle the main method (which is also connected w/ many other methods & variables within the class) where the user plays the game Wordle,
// in main, the user will play Wordle by comparing their guessed word to the randomly chosen word up to 6 times;
// if they run out of tries, they lose.
public class Wordle {
    // a general ArrayList for storing all the words in the words.txt file
    private final ArrayList<String> words = new ArrayList<>();
    // an ArrayList for telling the user if they guessed a valid word already (which would mean you don't lose a try)
    private final ArrayList<String> validGuessedWords = new ArrayList<>();
    // the array of letters that start with "an" before than rather than "a"
    private final char[] anLetters = new char[12];
    // the file name the program is searching for (built in-case it ever has to change)
    private final String inFileName = "words.txt";
    // the randomized chosen word by the program
    private String chosenWord = "";
    // the word guessed by the user
    private String userGuess = "";
    // a boolean for the purpose of telling the computer the word's valid for validGuessChkr()
    private boolean validWord = true;
    // user tries remaining to play the game
    private int tries = 6;
    // user tries left, for the purpose of winning the game's message
    private int realTries = 0;
    // add to ArrayList of potential words (assignment gives 4.5k words, this program can work with any amount now)
    public void addArrayList(String line) {
        words.add(line);
    }
    // reads the words.txt file to see if it meets the Wordle requirements
    public boolean readFile() {
        System.out.printf("Attempting to open file %s for output...\n", inFileName);
        // search the words.txt file and add in words into the ArrayList
        try {
            // input file being searched
            Scanner fIn = new Scanner(new FileInputStream("words.txt"));
            System.out.println("File opened successfully!");
            while (fIn.hasNext()) {
                userGuess = fIn.nextLine();
                if (userGuess.length() == 5) {
                    addArrayList(userGuess);
                }
                System.out.println(userGuess);
            }
            // if words.txt file doesn't contain enough text in the file itself, tell user a certain error
            if (words.size() == 0) {
                // if there's no text
                if (userGuess.equals("")) {
                    // if file does not have any text, give user an error and peace out
                    System.err.printf("Error: The file %s does not contain any text!%n", inFileName);
                    // else if there's some text, but no 5 character words in any line
                } else {
                    // if file does not have any 5 character words, give user an error and peace out
                    System.err.printf("Error: The file %s contains no words with a valid length!%n", inFileName);
                }
                return false;
            }
            System.out.println("Words put into program successfully!");
        }
        // if the file doesn't exist, give user an error and peace out
        catch (FileNotFoundException e) {
            System.err.printf("Error: the file %s does not exist!%n", inFileName);
            return false;
        }
        // if readFile gets by without any catches, move on to randomization by setting return to true
        return true;
    }
    // randomize a number based upon the amount of words there are in the ArrayList
    // then chose the index of that word in the ArrayList and make that the randomly chosen word
    public void wordRandomizer() {
        System.out.println("Attempting to get a randomized word from the list...");
        int randomNum = (int) (Math.random() * words.size());
        chosenWord = words.get(randomNum);
        System.out.println("Successfully retrieved a randomized word from the list!");
        // cheat code to tell user what the word is before they guess
        // System.out.printf("Randomly Chosen Word: %s%n", chosenWord);
    }
    // scan the user's input,
    // then check if the input matches a valid word,
    // and determines if user is winner or not,
    // if not, will give user hints about what characters are correct
    // will keep going on until tries are less than 1, dictated from main method while loop
    public void userScanner() {
        // makes a scanner class
        Scanner userGuesser = new Scanner(System.in);
        // gives user option to input their userGuess
        userGuess = userGuesser.nextLine();
        // set user's word guess to LowerCase (since program is designed to check startsWith more now than equals)
        userGuess = userGuess.toLowerCase();
        // if user gives a word too short, return to exit userScanner method
        if (userGuess.length() < 5) {
            // if user input userGuess length is less than 5, give user an error and let them try again
            System.err.println("Error: Invalid length (too short)!");
            return;
        }
        // if user gives a word that does not contain letters, return to exit userScanner method
        for (int i = 0; i <= 4; i++) {
            if (!(userGuess.charAt(i) >= 'a' && userGuess.charAt(i) <= 'z')) {
                // if user input does not contain only letters, give user an error and let them try again
                System.err.println("Error: Input does not contain only letters!");
                return;
            }
        }
        // check if input is a previously guessed word
        if (prevGuessedWordChkr()) {
            // if user input is a guessed word already, give user an error and let them try again
            System.err.println("Error: Input is a previously guessed word!");
            return;
        }
        // check if input matches a valid word from words.txt & if user gives a word too big
        if (!validGuessChkr()) {
            // this will only print error if user inserts an invalid word not matching any word from words.txt
            // otherwise will print nothing if user inserts a valid word with a length too big
            if (!validWord) {
                // print error too big
                if (userGuess.length() > 5) {
                    System.err.println("Error: Invalid length (too big)!");
                    // print error doesn't match words.txt
                } else {
                    System.err.printf("Error: Input does not match a valid word when referring to %s!%n", inFileName);
                }
            }
            // either way, the user will try again from this return statement
            return;
        }
        // compare if the word wins directly (all 5 characters match & is correct)
        if (compareToWin()) {
            return;
        }
        // compare the userGuess from the user to the chosenWord randomized by the program
        // this is used to give the user hints on what characters are correct,
        // if user gets specific characters correct, tell them exactly where their character is correct
        compareToGeneral();
    }
    // print how many tries the user has left in general
    public void printTries(int tries) {
        // try count message
        if (tries > 1) {
            System.out.printf("You have %d tries remaining to guess the correct 5-letter word.%n%n", tries);
        }
        // one try left grammar fix
        else {
            System.out.printf("You have %d try remaining to guess the correct 5-letter word.%n%n", tries);
        }
        // ask user for guess
        System.out.print("What is your guess?\n» ");
    }
    // print how many tries the user has left designed for user's first try
    public void printTriesFirst(int tries) {
        // prints user's tries remaining & their guess
        System.out.printf("You have %d tries to guess the correct 5-letter word.%nGood luck!%n%n", tries);
        System.out.print("What is your guess?\n» ");
    }
    // see if user's userGuess wins the game
    // this method sets the tries to -1 if there's a winner,
    // which makes checkWinner() return true
    public boolean compareToWin() {
        // user wins
        if (userGuess.equalsIgnoreCase(chosenWord)) {
            // set realTries to actual user tries for checkWinner() method
            realTries = tries;
            // tries remaining for user after the guess consideration
            realTries--;
            // tries is set to -1 to exit while loop & not tell user they lost (which is what setting tries to 0 causes)
            tries = -1;
            return true;
        }
        // user has not won yet (user keeps playing)
        return false;
    }
    // gives user hints on what characters are correct and their location
    // but does not tell user where correctly guessed characters are unless they are guessed in the correct location of the chosenWord
    public void compareToGeneral() {
        // subtract global tries count
        tries--;
        // loops through chars 1-5 of both userGuess from the user and chosenWord from the program both at once
        for (int i = 0; i <= 4; i++) {
            // compares word guess respective to randomly chosen word
            if (userGuess.charAt(i) == (chosenWord.charAt(i))) {
                System.out.printf("The %c is right! (Character #%d)%n", chosenWord.charAt(i), i+1);
            }
        }
        // nested for loop purpose: finding other characters that the user guessed that are found in the randomly chosen word
        // first loop is to get characters from the user itself (for their word guess)
        for (int userIn = 0; userIn <= 4; userIn++) {
            // skip the character being compared to the randomly chosen word if correct
            if (userGuess.charAt(userIn) != chosenWord.charAt(userIn)) {
                // second loop is to get characters from the program itself (for the randomly chosen word)
                for (int chosen = 0; chosen <= 4; chosen++) {
                    // compares word guess to all randomly chosen word letters regardless of location
                    if (userGuess.charAt(userIn) == (chosenWord.charAt(chosen))) {
                        // converts proper grammar usage with "a" or "an" before the found out chosen character from the user
                        // print "an"
                        for (int a = 0; a <= 11; a++) {
                            if (chosenWord.charAt(chosen) == anLetters[a]) {
                                System.out.printf("There is an %c, but not at character #%d%n", chosenWord.charAt(chosen), userIn + 1);
                                break;
                                // print "a"
                            } else if (a == 11) {
                                System.out.printf("There is a %c, but not at character #%d%n", chosenWord.charAt(chosen), userIn + 1);
                                break;
                            }
                        }
                        // break for purpose of escaping character being compared to chosen word character's for loop
                        break;
                    }
                }
            }
        }
    }
    // checks what to do if userGuess length is too big
    public boolean userGuessHasBigLengthChkr() {
        // if user gives a word too big, return to exit userScanner method if user wishes to
        if (userGuess.length() > 5) {
            // if user input userGuess length is greater than 5, give user an option to use the 5-letter word part of their guess
            System.out.printf("Your input was \"%s\" which has a length greater than 5, but still has a valid word in the first 5 characters.%n", userGuess);
            userGuess = userGuess.substring(0, 5);
            System.out.printf("Would you like to guess the 5-letter word portion of your guess, \"%s\" toward the game? %nY or Yes | N or No%n» ", userGuess);
            // makes a scanner class
            Scanner validUserChoiceScanner = new Scanner(System.in);
            // infinite while loop until user inputs valid answer
            while (true) {
                // gives user option to input their answer (yes or no)
                String userChoice = validUserChoiceScanner.nextLine();
                // if yes, return true to exit while loop to guess that word and move on
                if (userChoice.equalsIgnoreCase("Y") || userChoice.equalsIgnoreCase("Yes")) {
                    return true;
                    // if no, return false to exit userScanner() method and input a whole new word per user's desire
                } else if (userChoice.equalsIgnoreCase("N") || userChoice.equalsIgnoreCase("No")) {
                    return false;
                    // if neither yes/no, prompt user to give a valid answer and continue while loop
                } else {
                    System.err.printf("Error: Answer \"%s\" is not \"Y\", \"Yes\", \"N\", or \"No\"%n", userChoice);
                    // sleep so println can be after error message properly
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException("Uncaught", e);
                    }
                    System.out.print("Please input a valid answer!\n» ");
                }
            }
        }
        return true;
    }
    // check if the guessed word matches a word from the ArrayList for words.txt
    public boolean validGuessChkr() {
        // if user length is too big and user does not want to move on,
        if (words.contains(userGuess.substring(0, 5))) {
            // if word is valid, return true
            System.out.println("Valid word guess detected!");
            // give user option to guess again if input length is too big, but guess is valid
            if (!userGuessHasBigLengthChkr()) {
                // returns false if user DOESN'T wish to use their valid word they found from userGuessHasBigLengthChkr()
                return false;
            }
            else {
                // returns true if user DOES wish to use their valid word they found from userGuessHasBigLengthChkr()
                validGuessedWords.add(userGuess);
                return true;
            }
            // else if words ArrayList<> doesn't contain userGuess
        } else {
            // if user input does not match the word at all
            // expect to print an error message (thus validWord = false) to user but still return false to use up no tries
            validWord = false;
            return false;
        }
    }
    // check if the guessed word is a previously guessed word
    public boolean prevGuessedWordChkr() {
        return validGuessedWords.contains(userGuess.substring(0, 5));
    }
    // set letters for the anLetters[] Array
    public void setAnLetters() {
        // the letters set here are the letters that are meant to have "an" before them rather than "a" (grammatically)
        anLetters[0] = 'a';
        anLetters[1] = 'e';
        anLetters[2] = 'f';
        anLetters[3] = 'h';
        anLetters[4] = 'i';
        anLetters[5] = 'l';
        anLetters[6] = 'm';
        anLetters[7] = 'n';
        anLetters[8] = 'o';
        anLetters[9] = 'r';
        anLetters[10] = 's';
        anLetters[11] = 'x';
    }
    // check if the user won or lost (only checks if tries is less than 1 intentionally)
    // this method searches through tries
    public void checkWinner() {
        // if tries == -1,
        if (tries == -1) {
            // win
            System.out.println("\n+++++=====»»»»»»»»»» VICTORY ««««««««««=====+++++");
            if (realTries != 1) {
                System.out.printf("Congratulations. You won with %d extra tries remaining!", realTries);
                // grammar fix if realTries = 1
            } else {
                System.out.printf("Congratulations. You won with %d extra try remaining!", realTries);
            }
        }
        // if tries == 0,
        if (tries == 0) {
            // loss
            System.out.println("\n««««««««««=====----- DEFEAT -----=====»»»»»»»»»»");
            System.out.printf("Sorry, the word is %s", chosenWord);
        }
    }
    // the main method to run all other methods
    // purpose: to read the file, randomize a word from words.txt, then have the user play Wordle
    // user will play Wordle by comparing their guessed word to the randomly chosen word up to 6 times;
    // if they run out of tries, they lose.
    public static void main (String[]args) {
        // an object created to access all the methods
        // shortened from wordleObject
        Wordle wordleObj = new Wordle();
        // read the words.txt file
        // if the words.txt has problems in any way,
        if (!wordleObj.readFile()) {
            // stop the game from going on
            return;
        }
        // set the letters that have an "an" before them rather than an "a"
        wordleObj.setAnLetters();
        // pick a random number based upon the amount of valid 5-character words in words.txt,
        // then use that number to index the ArrayList of the words.txt file and chose that specific word of that randomized index
        wordleObj.wordRandomizer();
        // designed for user's first try, giving good luck when the game first starts
        wordleObj.printTriesFirst(wordleObj.tries);
        // attempt to play the game asking user for input once
        wordleObj.userScanner();
        // designed for after user's first try, no more good luck given
        // while user has greater than 0 tries,
        while (wordleObj.tries > 0) {
            // prints user's tries remaining
            wordleObj.printTries(wordleObj.tries);
            // keep playing the game by asking the user for input
            wordleObj.userScanner();
        }
        // check for a winner after user tries is not > 0 anymore
        wordleObj.checkWinner();
    }
}
