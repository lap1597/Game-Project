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

        // Variables to store movement direction
        float directionX = 0;
        float directionY = 0;

        // Capture key presses for movement
        if (Gdx.input.isKeyPressed(up)) {
            directionY += 1; // Moving up
        }
        if (Gdx.input.isKeyPressed(down)) {
            directionY -= 1; // Moving down
        }
        if (Gdx.input.isKeyPressed(left)) {
            directionX -= 1; // Moving left
        }
        if (Gdx.input.isKeyPressed(right)) {
            directionX += 1; // Moving right
        }

        // Normalize direction vector for consistent speed in diagonal movement
        if (directionX != 0 || directionY != 0) {
            float length = (float) Math.sqrt(directionX * directionX + directionY * directionY);
            directionX /= length;
            directionY /= length;


            // Move the player based on the calculated direction
            player.moveWithAngle(directionX, directionY, delta);

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
