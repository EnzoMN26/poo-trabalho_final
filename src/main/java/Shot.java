import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public class Shot extends PlayerElement{

    public Shot(int px,int py){
        super(px,py);
        try{
            setImage(new Image("Shot.png",0,40,true,true ));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("SHOT");
            System.exit(1);
        }
    }

    @Override
    public void start(){
        setDirV(-1);
        setSpeed(10);
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
