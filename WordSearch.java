import java.util.Random;
import java.util.Scanner;
    
public class WordSearch {

    private String title;
    private String[] words;
    private int row;
    private int column;
    private char[][] grid;
    private static final int max = 10; // max input words-- may change when size changes

    /*
     *  Constructor for WordSearch object of default size.
     */

    public WordSearch(String title, String[] words) {

        this.title = title;
        this.words = words;
        this.row = 10;
        this.column = 10;
    }

    /*
     *  Constructor for WordSearch object of custom size.
     */

    public WordSearch(String title, String[] words, int row, int column) {

        this(title, words);
        this.row = row;
        this.column = column;
    }

    /*
     *  Prints title of puzzle and creates blank grid of specified size.
     */

    public void createPuzzle() {

        if (!title.equals("")) {
            String pad = "";
            int b = (19 - title.length()) / 2;
            for (int i = 0; i < b; i++) {
                pad += " ";
            }
            System.out.printf("%s%s%s \n", pad, title, pad);
        }
        grid = new char[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                grid[i][j] = '*';
            }
        }
    }

    /*
     *  Randomly orients and places words with respect to overlap.
     */

    public void placeWords() {

        for (int i = 0; i < words.length; i++) {
            char[][] temp;
            int startRow = 0;  // starting point
            int startCol = 0; // starting point

            boolean invalid = false;

            do {
                String word = words[i];
                temp = grid;
                invalid = false;
                Random rand = new Random();
                int orient = rand.nextInt(4); // Change this back to 8 after testing

                /*
                 *  Where orient signifies:
                 *  0 -- right
                 *  1 -- left
                 *  2 -- down
                 *  3 -- up
                 *  4 -- down right
                 *  5 -- up left
                 *  6 -- up right
                 *  7 -- down left
                 */

                if (orient % 2 == 1)
                    word = reverse(word);

                /* Horizontal */

                if (orient < 2) {
                    startRow = rand.nextInt(row); // random row
                    startCol = rand.nextInt(column - word.length() + 1);
                    for (int j = 0; j < word.length() && !invalid; j++) {
                        if (temp[startRow][startCol + j] != '*' && temp[startRow][startCol + j] != word.charAt(j)) {
                            invalid = true;
                        }
                        temp[startRow][startCol + j] = word.charAt(j);
                    }
                }

                /* Vertical */

                else if (orient < 4) {
                    startRow= rand.nextInt(row - word.length() + 1); // random row
                    startCol = rand.nextInt(column);
                    for (int j = 0; j < word.length(); j++) {
                        if (temp[startRow + j][startCol] != '*' && temp[startRow + j][startCol] != word.charAt(j)) {
                            invalid = true;
                        }
                        temp[startRow + j][startCol] = word.charAt(j);
                    }
                }
//
//                switch (orient)
//                {
//                    case 0: // right
//
//                        break;
//                    case 1: // left
//                        startRow= rand.nextInt(row); // random row
//                        col = rand.nextInt(column - word.length() + 1);
//                        for (int j = 0; j < word.length(); j++) {
//                            temp[startRow][col + j] = word.charAt(j);
//                        }
//                        break;
//                    case 2: // down
//
//                        break;
//                    case 3: // up
//                        startRow= rand.nextInt(row - word.length() + 1); // random row
//                        col = rand.nextInt(column);
//                        for (int j = 0; j < word.length(); j++)
//                        {
//                            temp[startRow + j][col] = word.charAt(j);
//                        }
//                        break;
//                    case 4: // down right
//                        startRow= rand.nextInt(row - word.length() + 1);
//                        col = rand.nextInt(column - word.length() + 1);
//                        for (int j = 0; j  < word.length(); j++)
//                        {
//                            temp[startRow + j][col + j] = word.charAt(j);
//                        }
//                        break;
//                    case 5: // up left
//                        startRow= rand.nextInt(row - word.length() + 1);
//                        col = rand.nextInt(column - word.length() + 1);
//                        for (int j = 0; j < word.length(); j++)
//                        {
//                            temp[startRow + j][col + j] = word.charAt(j);
//                        }
//                        break;
//                    case 6: // up right
//                        startRow= rand.nextInt(row - word.length() + 1) + word.length() - 1;
//                        col = rand.nextInt(column - word.length() + 1);
//                        for (int j = 0; j < word.length(); j++)
//                        {
//                            temp[startRow - j][col + j] = word.charAt(j);
//                        }
//                        break;
//                    case 7: // down left
//                        startRow= rand.nextInt(row - word.length() + 1) + word.length() - 1;
//                        col = rand.nextInt(column - word.length() + 1);
//                        for (int j = 0; j < word.length(); j++)
//                        {
//                            temp[startRow - j][col + j] = word.charAt(j);
//                        }
//                        break;
//                }
                System.out.println();
                System.out.println(word);
                printPuzzle();
                System.out.println();
            } while(invalid);

            grid = temp;
        }
    }

    /*
     *  Reverses words for use method placeWords().
     */

    public String reverse(String forward) {
        int len = forward.length();
        char[] back = new char[len];
        String backward = "";
        for (int i = 0; i < len; i++) {
            back[i] = forward.charAt(len - 1 - i);
            backward += back[i];
        }
        return backward;
    }

    /*
     *  Fills puzzle with random letters after words are placed.
     */

    public char[][] fillRandom() {
        Random rand = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '*')
                {
                    int a = rand.nextInt(26) + 65;
                    char r = (char)a;
                    grid[i][j] = r;
                }
            }
        }
        return grid;
    }

    /*
     *  Prints puzzle...
     */

    public void printPuzzle() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.printf("%c ", grid[i][j]);
            }
            System.out.printf("\n");
        }
    }

    /*
     *  Copies contents of array into new array.
     */

    public String[] copyArray(String[] array, int len) {

        String[] newArray = new String[len];
        for (int i = 0; i < len; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public char[][] copyArray(char[][] array, int size) {

        char[][] newArray = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    /*
     *  Main!!
     */

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String[] words = new String[max];
        String title = "";

        System.out.println("Welcome to Charlene's Word Search Generator!");
        System.out.println("Title of word search (optional) : ");
        title = s.nextLine();

        System.out.printf("Enter a maximum of %d words: \n", max); // Change maximum based on size probs
        int count = 0; // counts true length of filled words array
        for (int i = 0; i < max; i++) {
            String next = s.nextLine();
            if (next.equals("")) {
                break;
            }
            words[i] = next.toUpperCase().replaceAll("\\s+","");
            count++;
        }
        WordSearch puzzle = new WordSearch(title, words);
        puzzle.words = puzzle.copyArray(puzzle.words, count);
        System.out.println(puzzle.words.length);
        puzzle.createPuzzle();
        puzzle.placeWords();
    //    puzzle.grid = puzzle.fillRandom();
    //    puzzle.printPuzzle();
    }
}