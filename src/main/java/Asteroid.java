import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Asteroid extends Enemy {
    private Image image;

    public Asteroid(int startX, int startY) {
        super(startX, startY);
        setDirH(-1);
        setDirV(0);

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        try{
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image =  new Image("asteroid.png",0,50,true,true );
            this.setLargAlt((int) image.getWidth(),(int) image.getHeight());
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("ASTEROID");
            System.exit(1);
        }
    }

    @Override
    public void start() {
        setSpeed(8);
        
    }

    @Override
    public void testaColisao(Character outro) {
        if (colidiu && getVida() == 0){
            return;
        }
        
        if (outro instanceof Enemy) {
            return;
        }

        // Monta pontos
        int p1x = this.getX();
        int p1y = this.getY();
        int p2x = p1x+this.getLargura();
        int p2y = p1y+this.getAltura();

        int op1x = outro.getX();
        int op1y = outro.getY();
        int op2x = op1x+outro.getLargura();
        int op2y = op1y+outro.getAltura();

        // Verifica colisão
        System.out.println(p2x);
        if (p2x < 0){
            colidiu = true;
            // reduzVida();
            // setVida(0);
        }

    }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            Game.getInstance().incPontos();
            deactivate();
        }else{
            if (getY()+getLargura() >= lmaxV) {
                // Adicionar mensagem de fim
                System.exit(-1);
            }

            setPosX(getX() + getDirH() * getSpeed());

       }
    }


    @Override
    public void Draw(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, getX(),getY());
        
    }
    
}
