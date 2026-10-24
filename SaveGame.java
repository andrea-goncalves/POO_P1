import java.io.*;

public class SaveGame
{
    public static void save(int levelNumber, int score)
    {
        try (PrintWriter out = new PrintWriter(new FileWriter(Settings.SAVE_FILE))) {
            out.println(levelNumber);
            out.println(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int load()
    {
        return readNumber(Settings.SAVE_LEVEL_LINE);
    }

    public static int loadScore()
    {
        return readNumber(Settings.SAVE_SCORE_LINE);
    }

    public static boolean hasSave()
    {
        return load() > 0;
    }

    public static void clear()
    {
        new File(Settings.SAVE_FILE).delete();
    }

    public static Level createLevel(int levelNumber)
    {
        switch (levelNumber) {
            case Settings.LEVEL_1: return new Level1();
            case Settings.LEVEL_2: return new Level2();
            case Settings.LEVEL_3: return new Level3();
            case Settings.BOSS_LEVEL: return new BossLevel();
            default: return null;
        }
    }

    private static int readNumber(int lineIndex)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(Settings.SAVE_FILE))) {
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