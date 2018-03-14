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

    }
    public void fillRandom()
    {
        Random rand = new Random();
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                if (grid[i][j] == '*')
                {
                    int a = rand.nextInt(26) + 65;
                    char r = (char)a;
                    grid[i][j] = r;
                }
                System.out.printf("%c ", grid[i][j]);
            }
            System.out.printf("\n");
        }
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
        for(int i = 0; i < max; i++)
        {
            String next = s.nextLine();
            if (next.equals(""))
            {
                break;
            }
            words[i] = next;
        }

        WordSearch puzzle = new WordSearch(title, words);
        puzzle.createPuzzle(); // places title and initializes grid w/ *'s
        puzzle.placeWords();
        puzzle.fillRandom(); // fills remaining stars and prints puzzle
    }
}
