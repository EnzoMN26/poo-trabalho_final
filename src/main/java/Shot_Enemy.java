import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Shot_Enemy extends Enemy{
    private Image image;
    
    public Shot_Enemy(int px,int py){
        super(px,py);
        try{
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image =  new Image("Enemy_Shot.png",0,50,true,true );
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

    // @Override
    // public void testaColisao(Character outro){
    //     // Não verifica colisão de um tiro com outro tiro
    //     if (outro instanceof Shot){
    //         return;
    //     }else{
    //         super.testaColisao(outro);
    //     }
    // }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            deactivate();
        }else{
            setPosY(getY() + getDirV() * getSpeed());
            //System.out.println(getY() + " " + getDirV());
            // Se chegou na parte superior da tela ...
            if (getY() <= getLMinV()){
                // Desaparece
                deactivate();
            }
        }
    }

    @Override
    public int getAltura(){
        return 16;
    }

    @Override
    public int getLargura(){
        return 8;
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(image, getX(),getY());
    }
}
