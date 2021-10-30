import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
 * program name: WordSearch.java
 * author: Xin Li
 * email: xinli2@email.arizona.edu
 */

public class WordSearch {
    /*
     * This program is to read in a file representing a grid of
     * characters and find the word that have in a dictionary of
     * valid words. This program will start from left-to-right,
     * right-to-left then top-to-bottom and bottom to top. After
     * that, print them out.
     */
    private static final int MIN_WORD_LENGTH = 3;

    public static List<String> readDictionary(String filename) {
        Scanner dictionary = null;
        try {
            dictionary = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> dict = new ArrayList<String>();
        while (dictionary.hasNext()) {
            String temp = dictionary.nextLine();
            if (temp.length() >= MIN_WORD_LENGTH) {
                temp = temp.toLowerCase();
                dict.add(temp);
            }
        }
        return dict;
    }

    public static String[][] readGrid(String filename) {
        Scanner grid = null;
        try {
            grid = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int x = Integer.valueOf(grid.nextLine());
        int y = Integer.valueOf(grid.nextLine());
        String[][] gridFile = new String[x][y];
        for (int i = 0; i < x; i++) {
            String temp = grid.nextLine();
            temp = temp.toLowerCase();
            String[] each = temp.split(" ");
            gridFile[i] = each.clone();
        }
        return gridFile;
    }

    public static void wordSearch(List<String> dictionary, String[][] grid) {
        String result = "";
        int position = 0;
        int current = 0;
        for (int i = 0; i < grid.length; i++) {
            while (position < grid[0].length) {
                while (current < grid[0].length) {
                    result += grid[i][current];
                    if (dictionary.contains(result)) {
                        System.out.println(result);
                    }
                    current++;
                }
                position++;
                current = position;
                result = "";
            }
            position = 0;
            current = 0;
        }
    }

    public static void reverseWordSearch(List<String> dictionary,
            String[][] grid) {
        String result = "";
        int position = grid[0].length - 1;
        int current = grid[0].length - 1;
        for (int i = 0; i < grid.length; i++) {
            while (position >= 0) {
                while (current >= 0) {
                    result += grid[i][current];
                    if (dictionary.contains(result)) {
                        System.out.println(result);
                    }
                    current--;
                }
                position--;
                current = position;
                result = "";
            }
            position = grid[0].length - 1;
            current = grid[0].length - 1;
        }
    }

    public static String[][] verticalGrid(String[][] grid) {
        String[][] newGrid = new String[grid[0].length][grid.length];
        int k = 0;
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                newGrid[i][j] = grid[j][k];
            }
            k++;
        }

        return newGrid;
    }

    public static void main(String[] args) {
        // Remember, to access command-line arguments, you use args[0],
        // args[1],...
        // See CommandLine.java and Stdin.java in the Class Examples github for examples.
        List<String> dictionary = readDictionary(args[0]);
        String[][] grid = readGrid(args[1]);
        wordSearch(dictionary, grid);
        reverseWordSearch(dictionary, grid);
        grid = verticalGrid(grid);
        wordSearch(dictionary, grid);
        reverseWordSearch(dictionary, grid);
    }

}
