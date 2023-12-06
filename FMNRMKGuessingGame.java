// Programmer: Farshid Miri, Nathan Roe, Medet Karl
// Class: CS &145
// Date: 9/21/2023
// Assignment: GuessingGame-Lab 4
// Purpose: This Java program generates while loops and pseudorandom numbers with a method that lets a user to play a guessing game.

import java.util.Scanner;

public class FMNRMKGuessingGame {
    // Constant for the maximum number used in the guessing game
    private static final int MAX_NUMBER = 100;

    // Main method to start the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        introduceGame();

        // Variables to store overall statistics
        int totalGames = 0;
        int totalGuesses = 0;
        int bestGuesses = Integer.MAX_VALUE;

        // Play multiple games until the user decides to quit
        String playAgain;
        do {
            playGame(MAX_NUMBER, scanner);
            totalGames++;
            totalGuesses += GuessingGameUtils.getGuessCount();

            // Update the best (fewest) number of guesses used in any single game
            if (GuessingGameUtils.getGuessCount() < bestGuesses) {
                bestGuesses = GuessingGameUtils.getGuessCount();
            }

            // Ask the user whether to play another game
            System.out.print("Do you want to play again? (Y/N): ");
            playAgain = scanner.next();
        } while (playAgain.equalsIgnoreCase("Y"));

        // Report overall results
        reportOverallResults(totalGames, totalGuesses, bestGuesses);
        scanner.close();
    }

    // Method to introduce the game to the user
    private static void introduceGame() {
        System.out.println("Welcome to the Guessing Game!");
        System.out.println("I am thinking of a number between 1 and " + MAX_NUMBER + ".");
        System.out.println("Try to guess the number.");
        System.out.println("I will tell you if your guess is too high or too low.");
        System.out.println("Let's begin!");
    }

    // Method to play one game with the user
    private static void playGame(int maxNumber, Scanner scanner) {
        int answer = (int) (Math.random() * maxNumber) + 1;
        int guess;
        int guessCount = 0;

        System.out.println("\nI have picked a number. Start guessing!");

        do {
            System.out.print("Your guess: ");
            guess = scanner.nextInt();
            guessCount++;

            if (guess == answer) {
                if (guessCount == 1) {
                    System.out.println("You got it right in 1 guess!");
                } else {
                    System.out.println("Congratulations! You got it right in " + guessCount + " guesses.");
                }
            } else if (guess < answer) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }
        } while (guess != answer);

        // Store the number of guesses made in the utility class
        GuessingGameUtils.setGuessCount(guessCount);
    }

    // Method to report overall results
    private static void reportOverallResults(int totalGames, int totalGuesses, int bestGuesses) {
        double averageGuesses = (double) totalGuesses / totalGames;
        System.out.println("\nOverall Results:");
        System.out.println("Total number of games played: " + totalGames);
        System.out.println("Total number of guesses made: " + totalGuesses);
        System.out.printf("Average number of guesses per game: %.1f\n", averageGuesses);
        System.out.println("Best (fewest) number of guesses used in a single game: " + bestGuesses);
    }
}

// Utility class to store the number of guesses made in a single game
class GuessingGameUtils {
    private static int guessCount;

    public static int getGuessCount() {
        return guessCount;
    }

    public static void setGuessCount(int count) {
        guessCount = count;
    }// end main method
}// end class