/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public abstract class PlayerElement extends BasicElement {

    public PlayerElement(int startX, int startY) {
        super(startX, startY);
    }

     public PlayerElement(int startX, int startY, int vida) {
        super(startX, startY, vida);
    }


    @Override
    public void testaColisao(Character outro) {
        if (outro instanceof PlayerElement) {
            return;
        }
        super.testaColisao(outro);
    }

}
