import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public class Game {
    private static Game game = null;
    private Cannon cannon;
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

    public void incPontos(int incremento) {
        pontos+= incremento;
    }

    public int getVidasCannon() {
        return cannon.getVida();
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
        cannon = new Cannon1(400, 700, 3);
        activeChars.add(cannon);

        activeChars.add(new Shooting_Enemy(100, 50, -1, 1.2, 1, 800000000));
        activeChars.add(new Normal_Enemy(200, 100, -1, 1));
        activeChars.add(new Normal_Enemy(300, 150, 1, 1));
        activeChars.add(new Shooting_Enemy(400, 200, -1, 1.9, 1, 800000000));
        activeChars.add(new Normal_Enemy(500, 250, 1));
        activeChars.add(new Shooting_Enemy(600, 300, 1, 2.6, 1, 800000000));
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
        cannon = new Cannon2(400, 700, 3);
        activeChars.add(cannon);

        activeChars.add(new Shooting_Enemy(100, 50, -1, 1.2, 1, 1500000000));
        activeChars.add(new Normal_Enemy(200, 100, -1, 4));
        activeChars.add(new Shooting_Enemy(300, 200, -1, 1.9, 1, 1500000000));
        activeChars.add(new Normal_Enemy(400, 150, 1, 4));
        activeChars.add(new Shooting_Enemy(500, 200, -1, 1.9, 1, 1500000000));
        activeChars.add(new Normal_Enemy(600, 250, 4));
        activeChars.add(new Shooting_Enemy(700, 300, 1, 2.6, 1, 1500000000));
        activeChars.add(new Normal_Enemy(800, 350, -1, 4));
        activeChars.add(new Normal_Enemy(900, 400, -1, 4));
        activeChars.add(new Shooting_Enemy(1000, 200, -1, 1.9, 1, 1500000000));

        // activeChars.add(new Shooting_Enemy(400,400, -1, 1));
        // activeChars.add(new Normal_Enemy(900, 450, 4));

        activeChars.add(new AsteroidSpawner());

        for (Character c : activeChars) {
            c.start();
        }
    }

    private void loadFase3() {
        // Repositório de personagens
        activeChars = new LinkedList<>();

        // Adiciona o canhao
        cannon = new Cannon3(400, 700, 5);
        activeChars.add(cannon);

        // Adiciona o boss
        activeChars.add(new Boss(400, 50));

        for (Character c : activeChars) {
            c.start();
        }
    }

    private void loadGameOver() {
        // Tela que aparece quando o jogo termina
        activeChars = new LinkedList<>();
        setGameOver();
    }

    public void Update(long currentTime, long deltaTime) {
        if (gameOver) {
            return;
        }

        int totalInimigos = 0; // total de inimigos que não são asteróides

        for (int i = 0; i < activeChars.size(); i++) {
            Character este = activeChars.get(i);
            este.Update(deltaTime);
            if (este instanceof Enemy) {
                Enemy aux = (Enemy) este;
                if (aux.isSaiuDaTela()) {
                    cannon.reduzVida();
                }
            }

            for (int j = 0; j < activeChars.size(); j++) {
                Character outro = activeChars.get(j);
                if (este != outro) {
                    este.testaColisao(outro);
                }
            }

            if (!(este instanceof Asteroid) && !(este instanceof AsteroidSpawner) && !(este instanceof Shot)) {
                totalInimigos++;
            }

        }

        // if (activeChars.size() == 1) {
        if (totalInimigos == 1) {
            switch (faseAtual) {
                case Fase1:
                    loadFase2();
                    faseAtual = Fases.Fase2;
                    break;

                case Fase2:
                    loadFase3();
                    faseAtual = Fases.Fase3;
                    break;

                case Fase3:
                    loadGameOver();
                    faseAtual = Fases.GameOver;
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

                case DIGIT3:
                    loadFase3();
                    faseAtual = Fases.Fase3;
                    break;

                default:
                    break;
            }
        }
        cannon.OnInput(keyCode, isPressed);
    }

    public void Draw(GraphicsContext graphicsContext) {
        for (Character c : activeChars) {
            c.Draw(graphicsContext);
        }
    }

}
