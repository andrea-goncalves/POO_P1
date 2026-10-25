import java.io.*;
import java.util.*;
// Lê ficheiros CSV com os mapas dos níveis e devolve-os como matrizes de números
public class CSVReader
{
    //Lê o ficheiro e devolve uma matriz com os valores de cada linha e coluna
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