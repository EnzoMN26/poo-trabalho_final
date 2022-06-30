import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public class Boss extends Enemy{

    private final static int HP = 25;
    private int RELOAD_TIME = 150000000; // o pika = 150000000
    private int shot_timer = 0;
    private int timer = 50;
    private Random random = new Random();

    public Boss(int px,int py){
        super(px,py, HP);
        try{
            setImage(new Image("boss.gif",500,500,true,true));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("BOSS");
            System.exit(1);
        }
    }

    @Override
    public void start(){
        setSpeed(4);
        setDirH(1);
    }

    public void shot(){
        int a = random.nextInt(75 + 150) - 150;
        Boss_Shot shot = new Boss_Shot(getX()+(getLargura()/2)+a,getY()+(getAltura()-50));
        if (shot_timer <= 0 && timer <= 0) {
            Game.getInstance().addChar(shot);
            shot_timer = RELOAD_TIME;
        }
    }


    @Override
    public void Update(long deltaTime){
        if (jaColidiu() && getVida() == 0){
            Game.getInstance().incPontos(15);
            deactivate();
        }else{
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX()+getLargura() >= getLMaxH() || getX() < getLMinH()){
                // Inverte a direção
                setDirH(getDirH()*-1);
            }
       }
       if (shot_timer > 0) shot_timer -= deltaTime;
       if (timer > 0) timer--;
       shot();
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(getImage(), getX(),getY());
    }
}
