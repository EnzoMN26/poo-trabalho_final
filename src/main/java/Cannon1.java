import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public class Cannon1 extends Cannon {

    public Cannon1(int px, int py, int vida) {
        super(px, py, vida);
        setSpeed(8);
        setLimV(Params.WINDOW_HEIGHT/2, getLMaxV());

        try{
            setImage(new Image("Ship1.png",0,100,true,true ));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("SHIP1");
            System.exit(1);
        }
    }

    @Override
    public void OnInput(KeyCode keyCode, boolean isPressed) {
        if (keyCode == KeyCode.LEFT){
            int dh = isPressed ? -1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.RIGHT){
            int dh = isPressed ? 1 : 0;
            setDirH(dh);
        }
        if (keyCode == KeyCode.UP){
            int dv = isPressed ? -1 : 0;
            setDirV(dv);
        }
        if (keyCode == KeyCode.DOWN){
            int dv = isPressed ? 1 : 0;
            setDirV(dv);
        }

        if (keyCode == KeyCode.SPACE){
            if (getShot_timer() <= 0) {
                Game.getInstance().addChar(new Shot(getX()+(getLargura() / 4 * 2),getY()-4));

                Game.getInstance().addChar(new Shot(getX()+(getLargura() / 4),getY()-4));

                setShot_timer(getRELOAD_TIME());
            }
        }
    }

}
