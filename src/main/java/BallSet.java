import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class BallSet extends BasicElement {
    private List<Ball> bolas;

    public BallSet(int startX, int startY) {
        super(startX, startY);
    }

    @Override
    public void start() {
        bolas = new ArrayList<>();
        bolas.add(new Ball(getX(), getY()));
        bolas.add(new Ball(getX() + 40, getY()));
        bolas.add(new Ball(getX() + 80, getY()));
        bolas.add(new Ball(getX() + 120, getY()));
        this.setLargAlt(150, 32);
        bolas.forEach(b->b.start());
    }

    @Override
    public void Update(long deltaTime) {
        if (jaColidiu()) {
            Game.getInstance().incPontos();
            deactivate();
        } else {
            for (Ball b : bolas) {
                b.setPosX(getX() + getDirH() * getSpeed());
            }
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX()+150 >= getLMaxH() || getX() < getLMinH()) {
                // Inverte a direção
                setDirH(getDirH() * -1);
                // Sorteia o passo de avanço [1,5]
                setSpeed(Params.getInstance().nextInt(5) + 5);
                // Se ainda não chegou perto do chão, desce
                /* if (getY() < 450){ */
                setPosY(getY() + 25);
                // }
            }
        }
    }

    @Override
    public void Draw(GraphicsContext graphicsContext) {
        for(Ball b:bolas){
            b.Draw(graphicsContext);
        }
    }

}
