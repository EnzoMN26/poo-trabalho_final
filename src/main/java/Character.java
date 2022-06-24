import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Bernardo Haab - 21200707
 * @author Enzo Martins Nobre - 21200756
 * @author Érico Panassol - 21201229
 * @author Luana Thomas - 21200415
 */

public interface Character {
    int getX();
    int getY();
    int getAltura();
    int getLargura();
    int getVida();
    Image getImage();

    void testaColisao(Character c);
    boolean jaColidiu();
    void setColidiu();
    void reduzVida();
    void setImage(Image i);

    void start();
    boolean isActive();
    void Update(long deltaTime);
    void Draw(GraphicsContext graphicsContext);
}
