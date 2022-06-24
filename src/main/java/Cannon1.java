import javafx.scene.image.Image;
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
    protected void shotQuantity() {
        Game.getInstance().addChar(new Shot(getX()+((getLargura() / 2) - 20),getY()-4));
    }

}
