import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

/**
 * Represents the game Gun
 * @author Bernardo Copstein, Rafael Copstein
 */
public class Canhao extends PlayerElement implements KeyboardCtrl{
    private int RELOAD_TIME = 508000000; // Time is in nanoseconds
    private int shot_timer = 0;

    public Canhao(int px,int py){
        super(px,py);
        setSpeed(8);
        setLimV(Params.WINDOW_HEIGHT/2, getLMaxV());

        try{
            setImage(new Image("Ship.png",0,100,true,true ));
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("SHIP");
            System.exit(1);
        }
    }

    @Override
    public void start() {
        setLimH(20,Params.WINDOW_WIDTH-20);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()){
            Game.getInstance().setGameOver();
        }

        if(getY() >= getLMinV() && getY() + getLargura() <= getLMaxV() && getX() >= getLMinH() && getX() + getAltura() <= getLMaxH()) {
            setPosX(getX() + getDirH() * getSpeed());
            setPosY(getY() + getDirV() * getSpeed());
        } else {
            setPosX(getX() + getDirH() + (getX() < getLMinH() ? -1 : 1) * getSpeed() * -1);
            setPosY(getY() + getDirV() + (getY() < getLMinV() ? -1 : 1) * getSpeed() * -1);
        }

        if (shot_timer > 0) shot_timer -= deltaTime;
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
            if (shot_timer <= 0) {
                Game.getInstance().addChar(new Shot(getX()+(getLargura() / 2),getY()-4));
                shot_timer = RELOAD_TIME;
            }
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(getImage(), getX(),getY());
    }
}
