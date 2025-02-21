import java.util.*;
import java.util.Scanner;

public class BlackJack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);                   // creates scanner

        boolean continueGame = true;

        while (continueGame) {

            int finalPlayerNumber = playerThrow(scanner);               // Takes return value from method and runs method
            int finalMachineNumber = machineThrow(finalPlayerNumber);                    // Takes return value from method and runs method

            compareThrows(finalPlayerNumber, finalMachineNumber, scanner);// Runs compareThrows()

            System.out.println("\nDo you want to play again? (y/n)");
            String playAgain = scanner.nextLine();
            if (playAgain.equals("n")) {
                continueGame = false;
            }
        }
    }

    public static int newThrow() {
        // Summen af 2 random tal (så det fungerer statistisk ligesom terninger)
        Random random = new Random();
        int ranNum1 = random.nextInt(6 - 1 + 1) + 1;
        int ranNum2 = random.nextInt(6 - 1 + 1) + 1;
        return ranNum1 + ranNum2;
    }

    public static int playerThrow(Scanner scanner) {
        boolean y = true;                                                   // Bool for while loop
        int finalNumber = newThrow();                                       // Saves newThrow() in finalNumber

        while (y) {
            System.out.println("your throw: " + finalNumber);               // Prints your throw everytime loop runs
            if (finalNumber == 21) {
                System.out.println("WUHUU you totally won!!");
                y = false;
                System.exit(0);
                return finalNumber;

            } else if (finalNumber > 21) {
                System.out.println("Orgh too bad! You went over 21!!");
                y = false;
                return finalNumber;
            }

            System.out.println("Do you want to throw again? (y/n)");        // Asks to throw again
            String input = scanner.nextLine();                              // Scanner saves input in input variable

            if (input.equals("n")) {
                // Print om spiller vil slå igen (y/n)
                System.out.println("Your final number is " + finalNumber);
                y = false;                                                  // Stops loop if "n" is input
            } else {
                int x = newThrow();
                System.out.println("Your new throw is: " + finalNumber + " + " + x);
                finalNumber = x + finalNumber;                              // Adds x to finalNumber and saves the new value
            }
        }
        return finalNumber;
    }

    public static int machineThrow(int finalPlayerNumber) {
        int finalNumber = newThrow();                                       // Machines first throw

        while (finalNumber < 17) {
            int x = newThrow();                                             // Throw again if finalNumber < 17
            finalNumber = finalNumber + x;

            if (finalNumber > 21 && finalPlayerNumber <= 21) {
                System.out.println("You won! Machine throw is: " + finalNumber);

            } else if (finalNumber >= 17 && finalNumber < 21) {
                System.out.println("\nMachine Throw is: " + finalNumber);
                // Continues looop
            }
        }
        return finalNumber;
    }

    public static void compareThrows(int finalPlayerNumber, int finalMachineNumber, Scanner scanner) {
        if (finalPlayerNumber != 21 && finalMachineNumber == 21) {
            System.out.println("Too bad! The Machine won! with a great roll: " + finalMachineNumber);


        } else if (finalMachineNumber < finalPlayerNumber && finalPlayerNumber <= 21) {
            System.out.println("WUHUU you totally won!! with a great roll: " + finalPlayerNumber + "\nMachine throw was: " + finalMachineNumber);


        } else if (finalMachineNumber > finalPlayerNumber && finalMachineNumber < 21) {
            System.out.println("Too bad! The Machine won! with a great roll: " + finalMachineNumber);

        } else if (finalMachineNumber == finalPlayerNumber) {
            System.out.println("Its a DRAW!! You both rolled: " + finalMachineNumber);
        }
    }
}