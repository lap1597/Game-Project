package io.github.lap1597;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;

public class GameControl extends ApplicationAdapter {
    private final Player player; // Player instance controlled by this GameControl

    public GameControl(Player player) {
        this.player = player;
    }

    /**
     * Processes input for controlling the player.
     *
     * @param delta   Time elapsed since the last frame, for smooth movement
     * @param up      Key for moving up
     * @param down    Key for moving down
     * @param left    Key for moving left
     * @param right   Key for moving right
     * @param skill1  Key for activating Skill 1
     * @param skill2  Key for activating Skill 2
     * @param skill3  Key for activating Skill 3
     */
    public void controlInput(float delta,
                             int up,
                             int down,
                             int left,
                             int right,
                             int skill1,
                             int skill2,
                             int skill3) {

        // Movement controls
        if (Gdx.input.isKeyPressed(up)) {
            player.move(Direction.UP, delta);
        }
        if (Gdx.input.isKeyPressed(down)) {
            player.move(Direction.DOWN, delta);
        }
        if (Gdx.input.isKeyPressed(left)) {
            player.move(Direction.LEFT, delta);
        }
        if (Gdx.input.isKeyPressed(right)) {
            player.move(Direction.RIGHT, delta);
        }



        // Skill activation
        if (Gdx.input.isKeyPressed(skill1)) {
            player.skill1();
        }
        if (Gdx.input.isKeyPressed(skill2)) {
            player.skill2();
        }
        if (Gdx.input.isKeyPressed(skill3)) {
            player.skill3();
        }
    }
}
