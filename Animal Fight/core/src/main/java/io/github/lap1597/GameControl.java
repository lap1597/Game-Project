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
    public void controlInput(float delta,
                             int Up,
                             int Down,
                             int Left,
                             int Right,
                             int Skill1,
                             int Skill2,
                             int Skill3) {

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

        //SKILL
        if (Gdx.input.isKeyPressed(Skill1)) {
            p.activateSkill1();
        } else {
            p.deactivateSkill1();
        }

        // Activate skill 2
        if (Gdx.input.isKeyPressed(Skill2)) {
            p.activateSkill2();
        } else {
            p.deactivateSkill2();
        }

        // Activate skill 3
        if (Gdx.input.isKeyPressed(Skill3)) {
            p.activateSkill3();
        } else {
            p.deactivateSkill3();
        }
        p.attack();
    }

}


