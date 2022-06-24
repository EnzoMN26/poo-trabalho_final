import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Shooting_Enemy extends Enemy {
    private int RELOAD_TIME = 800000000; // Time is in nanoseconds
    private int shot_timer = 0;
    private int timer = 50;

    public Shooting_Enemy(int px,int py, double multiplier, int vida){
        super(px,py, vida);
        setDirH(1);
        this.defineImage();
        RELOAD_TIME = (int) (RELOAD_TIME*multiplier);
    }

    // pode andar para a esquerda
    public Shooting_Enemy(int px, int py, int dirH, double multiplier, int vida){
        super(px, py, vida);
        setDirH(dirH);
        this.defineImage();
        RELOAD_TIME = (int) (RELOAD_TIME*multiplier);
    }

    private void defineImage(){
        try{
            setImage(new Image("Shooting_Enemy.png",0,40,true,true ));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("SHOOTING_ENEMY");
            System.exit(1);
        }
    }

    @Override
    public void start(){
        setSpeed(5);
    }

    @Override
    public void Update(long deltaTime) {
        super.Update(deltaTime);

        if (shot_timer > 0) shot_timer -= deltaTime;
        if (timer > 0) timer--;
        shot();
    }

    public void shot(){
        Shot_Enemy shot = new Shot_Enemy(getX()+(getLargura()/2),getY()+(getAltura()/2));
        if (shot_timer <= 0 && timer <= 0) {
            Game.getInstance().addChar(shot);
            shot_timer = RELOAD_TIME;
        }
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(getImage(), getX(),getY());
    }
}
