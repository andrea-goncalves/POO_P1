import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class CSVReader 
{
    public static int[][] carregarMatriz(String caminhoArquivo) 
    {
        List<int[]> linhas = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) 
        {
            String linha;
            while ((linha = br.readLine()) != null) 
            {
                if (linha.trim().isEmpty()) continue;
                
                String[] valores = linha.split(",");
                int[] linhaInt = new int[valores.length];
                
                for (int i = 0; i < valores.length; i++) 
                {
                    linhaInt[i] = Integer.parseInt(valores[i].trim());
                }
                linhas.add(linhaInt);
            }
        } 
        catch (IOException e) 
        {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }

        return linhas.toArray(new int[0][]);
    }
}