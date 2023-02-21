import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.*;
public class MiniCrossword
{
    private char[][] board;
    private char[][] solution;
    private String[][] clues;
    public MiniCrossword(String key, String cluesName, int x, int y) throws
            FileNotFoundException
    {
        board = new char[x][y];
        solution = new char[x][y];
        int clueLength = Math.max(x, y);
        clues = new String[2][clueLength];
        Scanner keyScan = new Scanner(new File(key));
        for(int i = 0; i < x; i++)
        {
            String toRead = keyScan.nextLine();
            for(int j = 0; j < y; j++)
            {
                solution[i][j] = toRead.charAt(j);
            }
        }
        Scanner clueScan = new Scanner(new File(cluesName));
        int AorD = 4000;
        int count = 3500;
        while(clueScan.hasNextLine())
        {
            String read = clueScan.nextLine();
            if(read.equalsIgnoreCase("ACROSS"))
            {
                AorD = 0;
                count = 0;
            }
            else if(read.equalsIgnoreCase("DOWN"))
            {
                AorD = 1;
                count = 0;
            }
            else
            {
                clues[AorD][count] = read;
                count++;
            }
        }
    }
    public void displaySolution()
    {
        for(int i = 0; i < solution.length; i++)
        {
            if(i == 0)
            {
                System.out.print("___");
                for(int x = 0; x < solution.length; x++)
                {
                    System.out.print("_" + (x+1) + "_");
                }
                System.out.println("_");
            }
            for(int j = 0; j < solution[0].length; j++)
            {
                if(j == 0)
                {
                    System.out.print((i+1) + " |");
                }
                System.out.print(" " + solution[i][j] + " ");
                if(j == solution[0].length - 1)
                {
                    System.out.print("|");
                }
            }
            System.out.println();
            if(i == solution.length - 1)
            {
                System.out.println("___________________");
            }
        }
    }
    public void displayBoard()
    {
        for(int i = 0; i < board.length; i++)
        {
            if(i == 0)
            {
                System.out.print("___");
                for(int x = 0; x < board.length; x++)
                {
                    System.out.print("_" + (x+1) + "_");
                }
                System.out.println("_");
            }
            for(int j = 0; j < board[0].length; j++)
            {
                if(j == 0)
                {
                    System.out.print((i+1) + " |");
                }
                System.out.print(" " + board[i][j] + " ");
                if(j == board[0].length - 1)
                {
                    System.out.print("|");
                }
            }
            System.out.println();
            if(i == board.length - 1)
            {
                System.out.println("___________________");
            }
        }
    }
    public void displayClues()
    {
        for(int i = 0; i < clues.length; i++)
        {
            if(i == 0)
            {
                System.out.println();
                System.out.println("ACROSS: ");
                System.out.println("___________________");
            }
            else if(i == 1)
            {
                System.out.println();
                System.out.println("DOWN: ");
                System.out.println("___________________");
            }
            for(int j = 0; j < clues[i].length; j++)
            {
                System.out.println(clues[i][j]);
            }
        }
    }

    public boolean checkWord(String direction, int location) {
        boolean correct = false;
        if (direction.equalsIgnoreCase("down")) {
            for (int i = 0; i < board[i].length; i++) {
                if (board[i][location] == solution[i][location]) {
                    correct = true;
                } else {
                    correct = false;
                    break;
                }
            }

        } else if (direction.equalsIgnoreCase("across")) {
            for (int i = 0; i < board[i].length; i++) {
                if (board[i][location] == solution[i][location]) {
                    correct = true;
                } else {
                    correct = false;
                    break;
                }
            }

        }
        return correct;
    }

    public boolean checkPuzzle() {
        boolean z = true;
        for(int i = 0; i < solution.length; i++) {
            for(int j = 0; j < solution[i].length; j++) {
                if(!(solution[i][j] == board[i][j])) {
                    z = false;
                    break;
                }
                else {
                    continue;
                }
            }
        }
        return z;
    }

    public void clearPuzzle() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void addWord(String word, String direction, int location) {
        String input = word.toUpperCase();
        if (direction.equalsIgnoreCase("down") && input.length() == 5) {
            for (int i = 0; i < board[0].length; i++) {
                board[i][location] = input.charAt(i);
            }

        } else if (direction.equalsIgnoreCase("across") && input.length() == 5) {
            for (int i = 0; i < board[location].length; i++) {
                board[location][i] = input.charAt(i); }
        }
    }



    public static void main(String[] args) throws FileNotFoundException
    {
        MiniCrossword todays = new MiniCrossword("puzzle1key.txt",
                "puzzle1clues.txt", 5, 5);
        Scanner userInput = new Scanner(System.in);
        boolean ayush = true;
        while(ayush)
        {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            todays.displayBoard();
            todays.displayClues();
            // Give users options for checking word, checking board, inputting word, etc.
            System.out.println();
            System.out.println();
            System.out.println();

            System.out.println("-----------MENU-----------");
            System.out.println("      1 - Check Word      ");
            System.out.println("      2 - Check Board      ");
            System.out.println("     3 - Enter a Word      ");
            System.out.println("      4 - Clear Board      ");
            System.out.println();
            System.out.println("What menu choice would you like?");
            int choice = userInput.nextInt();


            switch(choice) {
                case 1:
                    System.out.println("Down or Across?");
                    userInput.nextLine();
                    String d = userInput.nextLine();
                    int l = 0;
                    if(d.equalsIgnoreCase("down")) {
                        System.out.println("Enter column number");
                    }
                     else if(d.equalsIgnoreCase("across")) {
                    System.out.println("Enter row number");
                }
                     l = userInput.nextInt();
                    boolean coolio = todays.checkWord(d,l-1);
                    if(coolio) {
                        System.out.println("Your word is correct!");
                    }
                    else {
                        System.out.println("The word you guessed is incorrect!");
                    }
                    break;
                case 2:
                    boolean checker = todays.checkPuzzle();
                    if(checker) {
                        System.out.println("Your puzzle is correct!");
                    }
                    else {
                        System.out.println("Your puzzle is incorrect!");
                    }
                    break;
                case 3:
                    String field;
                    System.out.println("Enter a word to guess");
                    userInput.nextLine();
                    field = userInput.nextLine();
                    System.out.println("Down or Across?");
                    String di = userInput.nextLine();
                    int x = 0;
                    if(di.equalsIgnoreCase("down")) {
                        System.out.println("Enter column number");
                    }
                    else if (di.equalsIgnoreCase("across")) {
                    System.out.println("Enter row number");

                }

                    x = userInput.nextInt();
                    todays.addWord(field, di, x-1);
                    break;
                case 4:
                    todays.clearPuzzle();
                    break;
                    }
                    if(todays.checkPuzzle()) {
                        ayush = false;
                        System.out.println("You've solved the puzzle!");
                    }
            }

            // Perform the user's task
            // Check if the board is solved, if so print success message and break from loop
     }
    }
