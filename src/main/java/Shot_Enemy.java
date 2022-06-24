import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Shot_Enemy extends Enemy{

    public Shot_Enemy(int px,int py){
        super(px,py);
        try{
            setImage(new Image("Enemy_Shot.png",0,50,true,true ));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("SHOT");
            System.exit(1);
        }
    }

    @Override
    public void start(){
        setDirV(1);
        setSpeed(4);
    }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            deactivate();
        }else{
            setPosY(getY() + getDirV() * getSpeed());
            // Se chegou na parte superior da tela ...
            if (getY() <= getLMinV()){
                // Desaparece
                deactivate();
            }
        }
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(getImage(), getX(),getY());
    }
}
