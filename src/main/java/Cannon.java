import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public abstract class Cannon extends PlayerElement implements KeyboardCtrl{
    private int RELOAD_TIME = 508000000; // Time is in nanoseconds
    private int shot_timer = 0;

    public Cannon(int px,int py, int vida){
        super(px, py, vida);
    }

    @Override
    public void start() {
        setLimH(20,Params.WINDOW_WIDTH-20);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu() && getVida() == 0){
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

    public void setRELOAD_TIME(int rELOAD_TIME) {
        RELOAD_TIME = rELOAD_TIME;
    }

    public void setShot_timer(int shot_timer) {
        this.shot_timer = shot_timer;
    }

    public int getRELOAD_TIME() {
        return RELOAD_TIME;
    }

    public int getShot_timer() {
        return shot_timer;
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
                this.shotQuantity();
                setShot_timer(getRELOAD_TIME());
            }
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(getImage(), getX(),getY());
    }

    protected abstract void shotQuantity();
}
