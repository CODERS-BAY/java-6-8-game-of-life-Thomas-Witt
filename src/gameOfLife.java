import java.util.Scanner;

public class gameOfLife {
    public static void main(String[] args) {
        int rowspan = 11;
        int colspan = 50;
        int[][] primary = new int[rowspan][colspan];
        int[][] secondary = new int[rowspan][colspan];
        //Füge hier die ersten Felder ein, die am Leben sein sollen:

        primary[3][23] = 1;
        primary[3][25] = 1;
        primary[3][27] = 1;
        primary[4][23] = 1;
        primary[4][27] = 1;
        primary[5][23] = 1;
        primary[5][27] = 1;
        primary[6][23] = 1;
        primary[6][27] = 1;
        primary[7][23] = 1;
        primary[7][25] = 1;
        primary[7][27] = 1;

        for (int i = 0; i < rowspan; i++) {
            for (int j = 0; j < colspan; j++) {
                secondary[i][j] = primary[i][j];
            }
        }

        int k = 0;
        while (k < 20) {

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
            for (int i = 0; i < rowspan; i++) {
                for (int j = 0; j < colspan; j++) {
                    if(primary[i][j]==0)
                    System.out.print(".");
                    else{
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
}
