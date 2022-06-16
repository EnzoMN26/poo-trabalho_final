import javafx.scene.canvas.GraphicsContext;

/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public abstract class BasicElement implements Character{
    int direction_horizontal = 0, direction_vertical = 0;
    int lminV = Params.WINDOW_HEIGHT /2, lmaxV = Params.WINDOW_HEIGHT;
    int lminH = 0, lmaxH = Params.WINDOW_WIDTH;
    int largura , altura;
    boolean colidiu = false;
    boolean active = true;
    int posX, posY;
    int speed = 2;
    int vida = 1;

    public BasicElement(int startX,int startY){
        posX = startX;
        posY = startY;
    }

    @Override
    public int getX(){
        return(posX);
    }

    @Override
    public int getY(){
        return(posY);
    }

    @Override
    public int getAltura(){
        return(altura);
    }

    @Override
    public int getLargura(){
        return(largura);
    }

    @Override
    public int getVida() {
        return vida;
    }

    @Override
    public void reduzVida() {
        vida--;
    }

    @Override
    public void testaColisao(Character outro){
        if (colidiu && getVida() == 0){
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
        if ((op2x >= p1x && op1x <= p2x) && (op2y >= p1y && op1y <= p2y)) {
            colidiu = true;
            reduzVida();
        }
    }

    public int getDirH(){
        return(direction_horizontal);
    }

    public int getDirV(){
        return(direction_vertical);
    }

    public int getLMinH(){
        return(lminH);
    }

    public int getLMaxH(){
        return(lmaxH);
    }

    public int getLMinV(){
        return(lminV);
    }

    public int getLMaxV(){
        return(lmaxV);
    }

    public int getSpeed(){
        return(speed);
    }

    public void setPosX(int p){
        posX = p;
    }

    public void setPosY(int p){
        posY = p;
    }

    public void setLargAlt(int l,int a){
        largura = l;
        altura = a;
    }

    public void setDirH(int dirH){
        direction_horizontal = dirH;
    }

    public void setDirV(int dirV){
        direction_vertical = dirV;
    }

    public void setLimH(int min,int max){
        lminH = min;
        lmaxH = max;
    }

    public void setLimV(int min,int max){
        lminV = min;
        lmaxV = max;
    }

    public void setSpeed(int s){
        speed = s;
    }

    public void deactivate(){
        active = false;
        Game.getInstance().eliminate(this);
    }

    @Override
    public boolean jaColidiu(){
        return(colidiu);
    }

    @Override
    public void setColidiu(){
        colidiu = true;
    }

    @Override
    public  boolean isActive(){
        return(active);
    }

    @Override
    public abstract void start();

    @Override
    public abstract void Update(long deltaTime);

    @Override
    public abstract void Draw(GraphicsContext graphicsContext);
}
