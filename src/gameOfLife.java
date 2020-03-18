import java.util.Scanner;

public class gameOfLife {
    public static void main(String[] args) {
        //Choose the size of the display:
        int rowspan = 11;
        int colspan = 50;
        int[][] primary = new int[rowspan][colspan];
        int[][] secondary = new int[rowspan][colspan];
        int chooseFigure;
        System.out.println("Choose your figure:\n1 for the Exploder\n2 for the Row\n3 for the Spaceship");
        Scanner scanner = new Scanner(System.in);
        chooseFigure = scanner.nextInt();
        while (chooseFigure != 1 && chooseFigure != 2 && chooseFigure != 3) {
            System.out.println("There are only 3 figures available. Please choose 1, 2 or 3!");
            chooseFigure = scanner.nextInt();
        }
        if(chooseFigure==1){
            startTheExploder(primary);
        }
        else if(chooseFigure==2){
            startTheRow(primary);
        }
        else {
            startTheSpaceship(primary);
        }
        for (int i = 0; i < rowspan; i++) {
            for (int j = 0; j < colspan; j++) { //IntelliJ schlägt mir vor "arraycopy" zu verwenden, und obwohl es funktionieren würde,
                                                //mach ich das lieber mit Schleifen, weil mir der Befehl "arraycopy" unbekannt ist,
                                                //und ich nicht weiß, welche Parameter ich da eingeben muss, und warum.
                secondary[i][j] = primary[i][j];
            }
        }

        int k = 0;
        int cycles;
        System.out.println("How many cycles should the programme run?");
        Scanner scanner2 = new Scanner(System.in);
        cycles = scanner2.nextInt();

        while (k < cycles) {

            changingTheArray(rowspan, colspan, primary, secondary);

            for (int i = 0; i < rowspan; i++) {
                for (int j = 0; j < colspan; j++) {
                    if (primary[i][j] == 0)
                        System.out.print(".");
                    else {
                        System.out.print("#");
                    }
                }
                System.out.println();
            }
            System.out.println();
            for (int i = 0; i < rowspan; i++) {
                for (int j = 0; j < colspan; j++) {
                    primary[i][j] = secondary[i][j];
                }
            }
            k++;
        }

    }

    private static void changingTheArray(int rowspan, int colspan, int[][] primary, int[][] secondary) {
        for (int i = 1; i < rowspan - 1; i++) {             //der Rand des Arrays wird nicht verändert.
            for (int j = 1; j < colspan - 1; j++) {
                int sum = primary[i - 1][j - 1] + primary[i - 1][j] + primary[i - 1][j + 1] + primary[i][j - 1]
                        + primary[i][j + 1] + primary[i + 1][j - 1] + primary[i + 1][j] + primary[i + 1][j + 1];
                if (primary[i][j] == 0 && sum == 3) {
                    secondary[i][j] = 1;
                }
                if (primary[i][j] == 1 && sum < 2) {
                    secondary[i][j] = 0;
                }
                if (primary[i][j] == 1 && sum > 3) {
                    secondary[i][j] = 0;
                }
            }
        }
    }

    private static void startTheExploder(int[][] primary) {
        //Füge hier die ersten Felder ein, die am Leben sein sollen:
        primary[primary.length / 2 - 2][primary[1].length / 2 - 2] = 1;
        primary[primary.length / 2 - 2][primary[1].length / 2] = 1;
        primary[primary.length / 2 - 2][primary[1].length / 2 + 2] = 1;
        primary[primary.length / 2 - 1][primary[1].length / 2 - 2] = 1;
        primary[primary.length / 2 - 1][primary[1].length / 2 + 2] = 1;
        primary[primary.length / 2][primary[1].length / 2 - 2] = 1;
        primary[primary.length / 2][primary[1].length / 2 + 2] = 1;
        primary[primary.length / 2 + 1][primary[1].length / 2 - 2] = 1;
        primary[primary.length / 2 + 1][primary[1].length / 2 + 2] = 1;
        primary[primary.length / 2 + 2][primary[1].length / 2 - 2] = 1;
        primary[primary.length / 2 + 2][primary[1].length / 2] = 1;
        primary[primary.length / 2 + 2][primary[1].length / 2 + 2] = 1;
    }

    private static void startTheRow(int[][] primary) {
        //Füge hier die ersten Felder ein, die am Leben sein sollen:
        primary[primary.length / 2][primary[1].length / 2 - 4] = 1;
        primary[primary.length / 2][primary[1].length / 2 - 3] = 1;
        primary[primary.length / 2][primary[1].length / 2 - 2] = 1;
        primary[primary.length / 2][primary[1].length / 2 - 1] = 1;
        primary[primary.length / 2][primary[1].length / 2] = 1;
        primary[primary.length / 2][primary[1].length / 2 + 1] = 1;
        primary[primary.length / 2][primary[1].length / 2 + 2] = 1;
        primary[primary.length / 2][primary[1].length / 2 + 3] = 1;
        primary[primary.length / 2][primary[1].length / 2 + 4] = 1;
        primary[primary.length / 2][primary[1].length / 2 + 5] = 1;
    }

    private static void startTheSpaceship(int[][] primary) {
        //Füge hier die ersten Felder ein, die am Leben sein sollen:
        primary[primary.length / 2 - 2][3] = 1;
        primary[primary.length / 2 - 2][6] = 1;
        primary[primary.length / 2 - 1][7] = 1;
        primary[primary.length / 2][3] = 1;
        primary[primary.length / 2][7] = 1;
        primary[primary.length / 2 + 1][4] = 1;
        primary[primary.length / 2 + 1][5] = 1;
        primary[primary.length / 2 + 1][6] = 1;
        primary[primary.length / 2 + 1][7] = 1;
    }
}
