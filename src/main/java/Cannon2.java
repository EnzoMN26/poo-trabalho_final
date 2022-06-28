import javafx.scene.image.Image;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public class Cannon2 extends Cannon {

    public Cannon2(int px, int py, int vida) {
        super(px,py, vida);
        setSpeed(8);
        setLimV(Params.WINDOW_HEIGHT/2, getLMaxV());

        try{
            setImage(new Image("Ship2.png",0,100,true,true ));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("SHIP2");
            System.exit(1);
        }
    }

    @Override
    protected void shotQuantity() {
        Game.getInstance().addChar(new Shot(getX()+(5*(getLargura() / 9)),getY()-4));
        Game.getInstance().addChar(new Shot(getX()+(getLargura() / 9),getY()-4));
    }
}
