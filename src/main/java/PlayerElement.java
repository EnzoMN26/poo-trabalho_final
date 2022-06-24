public abstract class PlayerElement extends BasicElement {

    public PlayerElement(int startX, int startY) {
        super(startX, startY);
    }

    @Override
    public void testaColisao(Character outro) {
        if (outro instanceof PlayerElement) {
            return;
        }
        super.testaColisao(outro);
    }

}
