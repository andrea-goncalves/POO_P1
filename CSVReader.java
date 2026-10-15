import java.io.*;
import java.util.*;

public class CSVReader
{
    public static int[][] carregarMatriz(String caminho)
    {
        List<int[]> linhas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                int[] valores = new int[partes.length];
                for (int i = 0; i < partes.length; i++) {
                    valores[i] = Integer.parseInt(partes[i].trim());
                }
                linhas.add(valores);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linhas.toArray(new int[0][]);
    }
}