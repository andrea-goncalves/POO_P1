import java.io.*;
import java.util.*;

public class CSVReader
{
    public static int[][] matrix(String path)
    {
        List<int[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(Settings.CSV_SEPARATOR);
                int[] values = new int[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    values[i] = Integer.parseInt(parts[i].trim());
                }
                rows.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows.toArray(new int[0][]);
    }
}