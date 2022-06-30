/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public abstract class Enemy extends BasicElement{

    boolean saiuDaTela;

    public Enemy(int startX,int startY){
        super(startX, startY);
        posX = startX;
        posY = startY;
        saiuDaTela = false;
        setTxIncremento(2);
    }

    public Enemy(int startX,int startY, int vida){
        super(startX, startY, vida);
        posX = startX;
        posY = startY;
        saiuDaTela = false;
        setTxIncremento(2);
    }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu() && getVida() == 0){
            Game.getInstance().incPontos(getTxIncremento());
            deactivate();
        }else{
            if (getY()+getLargura() >= lmaxV && !(this instanceof Asteroid)) {
                // Adicionar mensagem de fim
                saiuDaTela = true;
            }

            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX()+getLargura() >= getLMaxH() || getX() < getLMinH()){
                // Inverte a direção
                setDirH(getDirH()*-1);
                setPosY(getY()+25);
            }
       }
    }

    public boolean isSaiuDaTela() {
        return saiuDaTela;
    }

    @Override
    public void testaColisao(Character outro){
        if (outro instanceof Enemy) {
            return;
        }
        super.testaColisao(outro);
    }

}
