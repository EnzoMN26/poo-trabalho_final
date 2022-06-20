import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Handles the game lifecycle and behavior
 * 
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    private boolean gameOver;
    private int pontos;
    private LocalDate ld;
    private Fases faseAtual;

    private Game() {
        gameOver = false;
        pontos = 0;
        ld = LocalDate.now();
    }

    public LocalDate getDate() {
        return ld;
    }

    public void setGameOver() {
        gameOver = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getPontos() {
        return pontos;
    }

    public void incPontos() {
        pontos++;
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }
        return (game);
    }

    public void addChar(Character c) {
        activeChars.add(c);
        c.start();
    }

    public void eliminate(Character c) {
        activeChars.remove(c);
    }

    public void Start() {
        loadFase1();
        faseAtual = Fases.Fase1;
    }

    private void loadFase1() {
        // Repositório de personagens
        activeChars = new LinkedList<>();

        // Adiciona o canhao
        canhao = new Canhao(400, 700);
        activeChars.add(canhao);

        activeChars.add(new Shooting_Enemy(100, 50, -1, 1.2, 1));
        activeChars.add(new Normal_Enemy(200, 100, -1, 1));
        activeChars.add(new Normal_Enemy(300, 150, 1, 1));
        activeChars.add(new Shooting_Enemy(400, 200, -1, 1.9, 1));
        activeChars.add(new Normal_Enemy(500, 250, 1));
        activeChars.add(new Shooting_Enemy(600, 300, 1, 2.6, 1));
        activeChars.add(new Normal_Enemy(700, 350, -1, 1));
        activeChars.add(new Normal_Enemy(800, 400, -1, 1));
        activeChars.add(new Normal_Enemy(900, 450, 1));

        // activeChars.add(new Shooting_Enemy(400,400, -1, 1));

        for (Character c : activeChars) {
            c.start();
        }
    }

    private void loadFase2() {
        // Repositório de personagens
        activeChars = new LinkedList<>();

        // Adiciona o canhao
        canhao = new Canhao(400, 700);
        activeChars.add(canhao);

        activeChars.add(new Shooting_Enemy(100, 50, -1, 1.2, 1));
        activeChars.add(new Normal_Enemy(200, 100, -1, 1));
        activeChars.add(new Normal_Enemy(300, 150, 1, 1));
        activeChars.add(new Shooting_Enemy(400, 200, -1, 1.9, 1));
        activeChars.add(new Normal_Enemy(500, 250, 1));
        activeChars.add(new Shooting_Enemy(600, 300, 1, 2.6, 1));
        activeChars.add(new Normal_Enemy(700, 350, -1, 1));
        activeChars.add(new Normal_Enemy(800, 400, -1, 1));
        // activeChars.add(new Normal_Enemy(900, 450, 1));

        activeChars.add(new Asteroid(900, 450));

        // activeChars.add(new Shooting_Enemy(400,400, -1, 1));

        for (Character c : activeChars) {
            c.start();
        }
    }

    public void Update(long currentTime, long deltaTime) {
        if (gameOver) {
            return;
        }

        for (int i = 0; i < activeChars.size(); i++) {
            Character este = activeChars.get(i);
            este.Update(deltaTime);
            for (int j = 0; j < activeChars.size(); j++) {
                Character outro = activeChars.get(j);
                if (este != outro) {
                    este.testaColisao(outro);
                }
            }
        }

        if (activeChars.size() == 1) {
            switch (faseAtual) {
                case Fase1:
                    loadFase2();
                    faseAtual = Fases.Fase2;
                    break;
            
                default:
                    break;
            }
        }
    }

    public void OnInput(KeyCode keyCode, boolean isPressed) {

        if (isPressed) {
            switch (keyCode) {
                case DIGIT1:                
                    loadFase1();
                    faseAtual = Fases.Fase1;
                    break;
            
                case DIGIT2:                
                    loadFase2();
                    faseAtual = Fases.Fase2;
                    break;
            
                default:
                    break;
            }
        }
        canhao.OnInput(keyCode, isPressed);
    }

    public void Draw(GraphicsContext graphicsContext) {
        for (Character c : activeChars) {
            c.Draw(graphicsContext);
        }
    }
}
