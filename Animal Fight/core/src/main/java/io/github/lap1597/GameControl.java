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
    public void controlInput(float delta) {
        boolean isMoving = false;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            p.moveUp(delta);
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            p.moveDown(delta);
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            p.moveLeft(delta);
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            p.moveRight(delta);
            isMoving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            p.attack(delta);
        }
        // Hit
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            p.hit(delta);
            isMoving = true;
        }
        //kick
        if (Gdx.input.isKeyPressed(Input.Keys.T)) {
            p.kick(delta);
            isMoving = true;
        }

//
//        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
//            p.kick(attack);
//        }
    }

}
