import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Normal_Enemy extends Enemy {
    private Image image;

    public Normal_Enemy(int px,int py){
        super(px,py);
        setDirH(1);
        this.setImage();
    }
    public Normal_Enemy(int px,int py, int dirH){
        super(px,py);
        setDirH(dirH);
        this.setImage();
    }

    private void setImage(){
        try{
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image =  new Image("Normal_Enemy.png",0,40,true,true );            
            this.setLargAlt((int) image.getWidth(),(int) image.getHeight());
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
        graphicsContext.drawImage(image, getX(),getY());
    }

    @Override
    public void testaColisao(Character outro){
        if (outro instanceof Normal_Enemy){

            return;
        }else{
            super.testaColisao(outro);
        }
    }
}
