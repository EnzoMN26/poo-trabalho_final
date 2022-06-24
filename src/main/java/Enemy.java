public abstract class Enemy extends BasicElement{

    public Enemy(int startX,int startY){
        super(startX, startY);
        posX = startX;
        posY = startY;
    }

    public Enemy(int startX,int startY, int vida){
        super(startX, startY, vida);
        posX = startX;
        posY = startY;
    }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu() && getVida() == 0){
            Game.getInstance().incPontos();
            deactivate();
        }else{
            if (getY()+getLargura() >= lmaxV) {
                // Adicionar mensagem de fim
                System.exit(-1);
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

    @Override
    public void testaColisao(Character outro){
        if (outro instanceof Enemy) {
            return;
        }
        super.testaColisao(outro);
    }
}
