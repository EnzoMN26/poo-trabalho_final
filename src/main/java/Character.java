import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
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
