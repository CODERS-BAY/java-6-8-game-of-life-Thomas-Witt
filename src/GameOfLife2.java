import java.util.Random;
import java.util.Scanner;

public class GameOfLife2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("How tall should the field be?");
        int height = scanner.nextInt();
        System.out.println("And how broad?");
        int width = scanner.nextInt();
        System.out.println("How many cycles should the program run?");
        int cycles = scanner.nextInt();

        int[][] gamefield = new int[height][width];
        int[][] changedField = new int[height][width];
        System.out.println("Do you want to see the line (press 1) or the lightweight spaceship (press 2) or start with a randomized field (press 3)?");

        int choice = scanner.nextInt();
        if (choice == 1) {
            StartWithTheShortRow(height, width, gamefield);
        } else if (choice == 2) {
            StartWithSpaceShip(height, gamefield);
        } else if (choice == 3) {
            StartWithRandom(gamefield);
        }

        for (int i = 0; i < height; i++) {                  //This copies the original gamefield into a second array for check-manipulation
            for (int j = 0; j < width; j++) {
                changedField[i][j] = gamefield[i][j];
            }
        }

        printTheField(height, width, gamefield);            //Shows the original setting of the field

        for (int i = 0; i < cycles; i++) {
            changeTheField(gamefield, changedField);

            printTheField(height, width, changedField);
        }
    }

    private static void StartWithRandom(int[][] gamefield) {
        for (int i = 1; i < gamefield.length - 1; i++) {
            for (int j = 1; j < gamefield[0].length - 1; j++) {
                Random rand = new Random();
                gamefield[i][j] = rand.nextInt(15) % 2;
            }
        }
    }

    private static void StartWithTheShortRow(int height, int width, int[][] gamefield) {
        gamefield[height / 2][width / 2 - 1] = 1;
        gamefield[height / 2][width / 2] = 1;
        gamefield[height / 2][width / 2 + 1] = 1;
    }

    private static void StartWithSpaceShip(int height, int[][] gamefield) {
        gamefield[height / 2][1] = 1;
        gamefield[height / 2 + 2][1] = 1;
        gamefield[height / 2 - 1][2] = 1;
        gamefield[height / 2 - 1][3] = 1;
        gamefield[height / 2 - 1][4] = 1;
        gamefield[height / 2 - 1][5] = 1;
        gamefield[height / 2][5] = 1;
        gamefield[height / 2 + 1][5] = 1;
        gamefield[height / 2 + 2][4] = 1;
    }


    private static void printTheField(int height, int width, int[][] gamefield) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (gamefield[i][j] == 0)
                    System.out.print(".");
                else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void changeTheField(int[][] gamefield, int[][] changedField) {

        for (int i = 1; i < gamefield.length - 1; i++) {             // The edge of the field will not be changed. Think of it as the border of the playfield.
            for (int j = 1; j < gamefield[0].length - 1; j++) {      // Otherwise you would need multiple checks if the "sum" wont try to access outside of the array -> error
                if (gamefield[i][j] == 0 && (gamefield[i - 1][j - 1] + gamefield[i - 1][j] + gamefield[i - 1][j + 1] +
                        gamefield[i][j - 1] + gamefield[i][j + 1] + gamefield[i + 1][j - 1] + gamefield[i + 1][j] + gamefield[i + 1][j + 1]) == 3) {
                    changedField[i][j] = 1;
                } else if (gamefield[i][j] == 1 && (gamefield[i - 1][j - 1] + gamefield[i - 1][j] + gamefield[i - 1][j + 1] +
                        gamefield[i][j - 1] + gamefield[i][j + 1] + gamefield[i + 1][j - 1] + gamefield[i + 1][j] + gamefield[i + 1][j + 1]) < 2) {
                    changedField[i][j] = 0;
                } else if (gamefield[i][j] == 1 && (gamefield[i - 1][j - 1] + gamefield[i - 1][j] + gamefield[i - 1][j + 1] +
                        gamefield[i][j - 1] + gamefield[i][j + 1] + gamefield[i + 1][j - 1] + gamefield[i + 1][j] + gamefield[i + 1][j + 1]) > 3) {
                    changedField[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < gamefield.length; i++) {                // This transfers the changes made in the second array back to the original gamefield
            for (int j = 0; j < gamefield[0].length; j++) {
                gamefield[i][j] = changedField[i][j];
            }
        }
    }
}
