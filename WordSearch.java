import java.util.Random;
import java.util.Scanner;

public class WordSearch
{
    private String title;
    private String[] words;
    private int row;
    private int column;
    private char[][] grid;
    private static final int max = 10; // max input words-- may change when size changes

    public WordSearch(String title, String[] words)
    {
        this.title = title;
        this.words = words;
        this.row = 10;
        this.column = 10;
    }
    public WordSearch(String title, String[] words, int row, int column)
    {
        this(title, words);
        this.row = row;
        this.column = column;
    }
    public void inputWindow() // From swing stuff
    {

    }
    public void createPuzzle()
    {
        if (!title.equals(""))
        {
            String pad = "";
            int b = (19 - title.length()) / 2;
            for (int i = 0; i < b; i++)
            {
                pad += " ";
            }
            System.out.printf("%s%s%s \n", pad, title, pad);
        }
        grid = new char[row][column];
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                grid[i][j] = '*';
            }
        }
    }
    public void placeWords()
    {
        Random rand = new Random();
        for (int i = 0; i < words.length; i++)
        {
            char[][] temp;
            temp = grid;
            String wor = words[i];
            int len = wor.length();
            int ro = 0;  // starting point
            int col = 0; // starting point
        do
        {
            int a = rand.nextInt(8);
//              a = 7; // for testing
            switch (a)
            {
                case 0: // right
                    ro = rand.nextInt(row); // random row
                    col = rand.nextInt(column - len + 1);
                    for (int j = 0; j < len; j++)
                    {
                        temp[ro][col + j] = wor.charAt(j);
                    }
                    break;
                case 1: // left
                    wor = reverse(wor);
                    ro = rand.nextInt(row); // random row
                    col = rand.nextInt(column - len + 1);
                    for (int j = 0; j < len; j++)
                    {
                        temp[ro][col + j] = wor.charAt(j);
                    }
                    break;
                case 2: // down
                    ro = rand.nextInt(row - len + 1); // random row
                    col = rand.nextInt(column);
                    for (int j = 0; j < len; j++)
                    {
                        temp[ro + j][col] = wor.charAt(j);
                    }
                    break;
                case 3: // up
                    wor = reverse(wor);
                    ro = rand.nextInt(row - len + 1); // random row
                    col = rand.nextInt(column);
                    for (int j = 0; j < len; j++)
                    {
                        temp[ro + j][col] = wor.charAt(j);
                    }
                    break;
                case 4: // down right
                    ro = rand.nextInt(row - len + 1);
                    col = rand.nextInt(column - len + 1);
                    for (int j = 0; j  < len; j++)
                    {
                        temp[ro + j][col + j] = wor.charAt(j);
                    }
                    break;
                case 5: // up left
                    wor = reverse(wor);
                    ro = rand.nextInt(row - len + 1);
                    col = rand.nextInt(column - len + 1);
                    for (int j = 0; j < len; j++)
                    {
                        temp[ro + j][col + j] = wor.charAt(j);
                    }
                    break;
                case 6: // up right
                    ro = rand.nextInt(row - len + 1) + len - 1;
                    col = rand.nextInt(column - len + 1);
                    for (int j = 0; j < len; j++)
                    {
                        temp[ro - j][col + j] = wor.charAt(j);
                    }
                    break;
                case 7: // down left
                    wor = reverse(wor);
                    ro = rand.nextInt(row - len + 1) + len - 1;
                    col = rand.nextInt(column - len + 1);
                    for (int j = 0; j < len; j++)
                    {
                        temp[ro - j][col + j] = wor.charAt(j);
                    }
                    break;
            }
        }while(checkOverlap(wor, grid, temp, ro, col));
        grid = temp;
        }
    }
    public boolean checkOverlap(String word, char[][] grid, char[][] temp, int rstart, int cstart) // method to check whether overlap is okay
    {
        int count;
        if (count > 1)
        {
            return true;
        }
        else if (count == 1)
        {
            return false;
        }
        return false;
    }
    public String reverse(String forward) // reverses words for use in cases 1, 3, 5, 6, 7
    {
        int len = forward.length();
        char[] back = new char[len];
        String backward = "";
        for (int i = 0; i < len; i++)
        {
            back[i] = forward.charAt(len - 1 - i);
            backward += back[i];
        }
        return backward;
    }
    public void fillRandom()
    {
        Random rand = new Random();
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
//                if (grid[i][j] == '*')
//                {
//                    int a = rand.nextInt(26) + 65;
//                    char r = (char)a;
//                    grid[i][j] = r;
//                }
                System.out.printf("%c ", grid[i][j]);
            }
            System.out.printf("\n");
        }
    }
    public String[] copyArray(String[] array, int newlen)
    {
        String[] newarray = new String[newlen];
        for (int i = 0; i < newlen; i++)
        {
            newarray[i] = array[i];
        }
        return newarray;
    }
    public char[][] copyArray(char[][] array, int r, int c) // change this because it's wrong
    {
        char[][] newarray = new char[r][c];
        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                newarray[i][j] = array[i][j];
            }
        }
        return newarray;
    }
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        String[] words = new String[max];
        String title;

        System.out.printf("Welcome to Charlene's Word Search Generator!\n");
        System.out.printf("Title of word search (optional) :\n");
        title = s.nextLine();

        System.out.printf("Enter a maximum of %d words: \n", max); // Change maximum based on size probs
        int count = 0; // counts true length of filled words array
        for(int i = 0; i < max; i++)
        {
            String next = s.nextLine();
            if (next.equals(""))
            {
                break;
            }
            words[i] = next.toUpperCase().replaceAll("\\s+","");
            count++;
        }
        WordSearch puzzle = new WordSearch(title, words);
        puzzle.words = puzzle.copyArray(puzzle.words, count);
        System.out.println(puzzle.words.length);
        puzzle.createPuzzle(); // places title and initializes grid w/ *'s
        puzzle.placeWords();
        puzzle.fillRandom(); // fills remaining stars and prints puzzle
    }
}
