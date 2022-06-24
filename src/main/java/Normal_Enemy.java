import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Normal_Enemy extends Enemy {

    public Normal_Enemy(int px,int py, int vida){
        super(px,py, vida);
        setDirH(1);
        this.defineImage();
    }
    public Normal_Enemy(int px,int py, int dirH, int vida){
        super(px,py, vida);
        setDirH(dirH);
        this.defineImage();
    }

    private void defineImage(){
        try{
            setImage(new Image("Normal_Enemy.png",0,40,true,true ));
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

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(getImage(), getX(),getY());
    }
}
