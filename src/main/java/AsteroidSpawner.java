import javafx.scene.canvas.GraphicsContext;
import java.util.Random;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public class AsteroidSpawner extends Enemy {
    Random random = new Random();
    private int RELOAD_TIME = 2100000000; // Time is in nanoseconds
    private int shot_timer = 0;
    private int timer = 50;

    public AsteroidSpawner() {
        super(0, 0);
    }

    @Override
    public void start() {
        setSpeed(3);

    }

    @Override
    public void testaColisao(Character outro) {  
        if (outro instanceof Enemy) {
            return;
        }

        super.testaColisao(outro);

        // Monta pontos
        int p2x = this.getX()+this.getLargura();

        // Verifica se sai da tela
        if (p2x < 0){
            setColidiu();
        }
  
    }

    @Override
    public void Update(long deltaTime){
        super.Update(deltaTime);
        if (shot_timer > 0) shot_timer -= deltaTime;
        if (timer > 0) timer--;
        shot();
    }


    @Override
    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(getImage(), getX(),getY());
    }

    public void shot(){
        int a = random.nextInt(1000);
        double k = random.nextInt(2);
        double c = Math.pow(-1.0, k);
        Asteroid shot = new Asteroid(a, 0, (int)c);
        if (shot_timer <= 0 && timer <= 0) {
            Game.getInstance().addChar(shot);
            shot_timer = RELOAD_TIME;
        }
    }
}
