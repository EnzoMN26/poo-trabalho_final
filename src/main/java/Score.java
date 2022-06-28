import java.io.IOException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public class Score{
    /*private static final int MAXSCORE = 1000;
    private ArrayList<Integer> pontos;

    private static final String fName = "score.txt";

    public Score(){
        pontos = new ArrayList<>(MAXSCORE);
    }

    public void carregaScore(){
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir+"\\"+fName;
        Path path = Paths.get(nameComplete);
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, StandardCharsets.UTF_8))){
        while (sc.hasNext()){
            String linha = sc.nextLine();
            String dados[] = linha.split("\n");
            int score = Integer.parseInt(dados[0]);
            pontos.add(score);
        }
        }catch (IOException x){
            System.err.format("Erro de E/S: %s%n", x);
        }
    }

    public void gravaScore(){
        try {
            FileWriter fw = new FileWriter(fName);
            for(int i=0;i<pontos.size();i++){
                int linha = pontos.get(i);
                fw.write(linha+"\n");
            }
            fw.close();
        }catch (IOException x){
            System.err.format("Erro de E/S: %s%n", x);
        }
    }

    public void update(int i){
        carregaScore();
        pontos.add(i);
        ordena();
        gravaScore();
    }

    public String imprime(){
        String linha = "Ranking \n";
        for(int i=0;i<10;i++){
            linha += (i+1) +"º: " + pontos.get(i) + "\n";
        }
        return linha;
    }

   public void ordena(){
        Collections.sort(pontos, Collections.reverseOrder());
   }*/
}