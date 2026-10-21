import java.io.*;

public class SaveGame
{
    private static final String FILE = "savegame.txt";   
    private static final int LEVEL_LINE = 0; 
    private static final int SCORE_LINE = 1; 

    public static void save(int levelNumber, int score)
    {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE))) {
            out.println(levelNumber);
            out.println(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int load()
    {
        return readNumber(LEVEL_LINE);
    }

    public static int loadScore()
    {
        return readNumber(SCORE_LINE);
    }

    public static boolean hasSave()
    {
        return load() > 0;
    }

    public static void clear()
    {
        new File(FILE).delete();
    }

    public static Level createLevel(int levelNumber)
    {
        switch (levelNumber) {
            case 1: return new Level1();
            case 2: return new Level2();
            case 3: return new Level3();
            case 4: return new BossLevel();
            default: return null;
        }
    }

    private static int readNumber(int lineIndex)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line = null;
            for (int i = 0; i <= lineIndex; i++) {
                line = reader.readLine();
            }
            return Integer.parseInt(line.trim());
        } catch (IOException | NumberFormatException | NullPointerException e) {
            return 0;
        }
    }
}