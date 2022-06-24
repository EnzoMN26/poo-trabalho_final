import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Asteroid extends Enemy {

    public Asteroid(int startX, int startY) {
        super(startX, startY);
        setDirH(-1);
        setDirV(0);

        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        try{
            setImage(new Image("asteroid.png",0,50,true,true ));
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
        if (colidiu || (outro instanceof Enemy)) {
            return;
        }

        // Monta pontos
        int p2x = this.getX()+this.getLargura();

        // Verifica se sai da tela
        if (p2x < 0){
            setColidiu();
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
        graphicsContext.drawImage(getImage(), getX(),getY());

    }

}
