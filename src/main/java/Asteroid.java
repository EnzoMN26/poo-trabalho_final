import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public class Asteroid extends Enemy {
    // Random random = new Random();
    // private int RELOAD_TIME = 800000000; // Time is in nanoseconds
    // private int shot_timer = 0;
    // private int timer = 50;

    public Asteroid(int startX, int startY, int x) {
        super(startX, startY);
        setDirH(x);
        setDirV(1);

        try{
            setImage(new Image("asteroid.png",0,50,true,true ));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("ASTEROID");
            System.exit(1);
        }
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
        // super.Update(deltaTime);
        if (jaColidiu()){
            Game.getInstance().incPontos();
            deactivate();
        }else{
            setPosX(getX() + getDirH() * getSpeed());
            setPosY(getY() + getDirV() * getSpeed());
        }
        // if (shot_timer > 0) shot_timer -= deltaTime;
        // if (timer > 0) timer--;
        // shot();
    }


    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(getImage(), getX(),getY());

    }

    // public void shot(){
    //     int a = random.nextInt(200) + 500;
    //     Asteroid shot = new Asteroid(a, 0, -1);
    //     if (shot_timer <= 0 && timer <= 0) {
    //         Game.getInstance().addChar(shot);
    //         shot_timer = RELOAD_TIME;
    //     }
    // }
}
