import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public class Main extends Application {
    private Image image;
    private List<Integer> listaPontuacao = new ArrayList<Integer>(11);

    @Override
    public void start(Stage stage) throws Exception {
        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene( root );
        stage.setScene( scene );

        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT );

        root.getChildren().add( canvas );

        // Setup Game object
        Game.getInstance().Start();

        try {
            URL url =  getClass().getResource("pontuacao.txt");
            File arquivoPontos = new File(url.getPath());
            Scanner scannner = new Scanner(arquivoPontos);

            while (scannner.hasNextLine()) {
                String pontuacao = scannner.nextLine();
                listaPontuacao.add(Integer.parseInt(pontuacao));
            }
            listaPontuacao.sort((a, b) -> b-a);
        } catch (Exception e) {
            System.out.println("----------ERRO---------------");
            System.out.println(e);
        }

        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });

        // Register Game Loop
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;

                Game.getInstance().Update(currentNanoTime, deltaTime);
                try{
                    // Carrega a imagem ajustando a altura para 40 pixels
                    // mantendo a proporção em ambas dimensões
                    image =  new Image("Background.png"); //se usar jpg fica MUITO lento
                    gc.drawImage(image,0 ,0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    System.out.println("BACKGROUND");
                    System.exit(1);
                }

                gc.fillText("Pontos: "+Game.getInstance().getPontos(), 10, 10);
                gc.fillText("data: "+Game.getInstance().getDate().getDayOfMonth()+"/"+Game.getInstance().getDate().getDayOfYear(),10,30);
                Game.getInstance().Draw(gc);
                if (Game.getInstance().isGameOver()){
                    int pontuacaoFinal = Game.getInstance().getPontos();
                    if (listaPontuacao.size() < 10 || pontuacaoFinal > listaPontuacao.get(9)) {
                        listaPontuacao.add(pontuacaoFinal);
                        listaPontuacao = listaPontuacao.stream().sorted((a, b) -> b-a).limit(10).collect(Collectors.toList());
                    }

                    String conteudoArquivo = "";

                    for (Integer pontuacao : listaPontuacao) {
                        conteudoArquivo+= pontuacao+"\n";
                    }

                    try {
                        URL url =  getClass().getResource("pontuacao.txt");
                        FileWriter arquivo = new FileWriter(url.getPath());
                        arquivo.write(conteudoArquivo);
                        arquivo.close();

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    stop();
                }

                lastNanoTime = currentNanoTime;
            }

        }.start();

        // Show window
        stage.show();
    }

    public static void main(String args[]) {
        launch();
    }
}
