package io.github.lap1597;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class GameControl extends ApplicationAdapter {
    private Player p;
    private float delta;
    private int attack;
    public GameControl(Player p) {
        this.p = p;

    }
    public void controlInput(float delta, int Up,
                             int Down,
                             int Left,
                             int Right,
                             int Gun,
                             int Kick,
                             int Punch) {

        if (Gdx.input.isKeyPressed(Up)) {
            p.moveUp(delta);

        }

        if (Gdx.input.isKeyPressed(Down)) {
            p.moveDown(delta);

        }

        if (Gdx.input.isKeyPressed(Left)) {
            p.moveLeft(delta);

        }

        if (Gdx.input.isKeyPressed(Right)) {
            p.moveRight(delta);

        }
        //Load gun
        if (Gdx.input.isKeyPressed(Gun)) {
            p.attack(delta);
        }
        // Hit
        if (Gdx.input.isKeyPressed(Punch)) {
            p.hit(delta);

        }
        //kick
        if (Gdx.input.isKeyPressed(Kick)) {
            p.kick(delta);

        }

    }

}
