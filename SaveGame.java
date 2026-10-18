import java.io.*;


public class SaveGame
{
    private static final String FILE = "savegame.txt";   
    
    public static void save(int levelNumber)
    {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE))) {
            out.println(levelNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
    public static int load()
    {
        File file = new File(FILE);
        if (!file.exists()) {
            return 0;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return Integer.parseInt(reader.readLine().trim());
        } catch (IOException | NumberFormatException | NullPointerException e) {
            return 0;
        }
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
}