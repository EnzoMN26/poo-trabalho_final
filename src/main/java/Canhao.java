import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;

/**
 * Represents the game Gun
 * @author Bernardo Copstein, Rafael Copstein
 */
public class Canhao extends BasicElement implements KeyboardCtrl{
    private int RELOAD_TIME = 508000000; // Time is in nanoseconds
    private int shot_timer = 0;
    private Image image;

    public Canhao(int px,int py){
        super(px,py);
        setSpeed(8);

        try{
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image =  new Image("Ship.png",0,100,true,true );
            // this.setLargAlt(100, 100);
            this.setLargAlt((int) image.getWidth(),(int) image.getHeight());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("SHIP");
            System.exit(1);
        }
    }

    @Override
    public void start() {
        setLimH(20,Params.WINDOW_WIDTH-20);
        setLimV(Params.WINDOW_HEIGHT-100,Params.WINDOW_HEIGHT);
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()){
            Game.getInstance().setGameOver();
        }
        setPosX(getX() + getDirH() * getSpeed());
        setPosY(getY() + getDirV() * getSpeed());

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
                Game.getInstance().addChar(new Shot(getX()+getLargura(),getY()-4));
                shot_timer = RELOAD_TIME;
            }
        }
    }

    // @Override
    // public int getAltura(){
    //     return 80;
    // }

    // @Override
    // public int getLargura(){
    //     return 32;
    // }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, getX(),getY());
    }
}
