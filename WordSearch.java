import javax.swing.*;
import java.util.Random;

public class WordSearch
{
    private String title;
    private String[] words;
    private int row;
    private int column;
    private char[][] grid;

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
    public void inputWindow()
    {

    }
    public void createPuzzle()
    {
        grid = new char[row][column];
        Random rand = new Random();
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                int a = rand.nextInt(26) + 65;
                char r = (char)a;
                grid[i][j] = r;
                System.out.printf("%c ", grid[i][j]);
            }
            System.out.printf("\n");
        }
    }
    public static void main(String[] args)
    {
        String[] yay = new String[1]; // Tests
        yay[0] = "Hello"; // More tests
        WordSearch puzzle = new WordSearch("RIP", yay); // Even more tests
        puzzle.createPuzzle();
    }
}
