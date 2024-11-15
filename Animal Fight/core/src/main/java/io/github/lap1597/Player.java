package io.github.lap1597;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.*;

public class Player {

    private Movement movement;
    private int health;
    private static int typePlayer;
    private float elapsedTime = 0f;
    private static final float MOVE_SPEED = 100f;
    private float x, y;
    private float width, height;
    private HashMap<String, TextureRegion[]> regions;
    private HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();  // Stores animations by action
    private Animation<TextureRegion> currentAnimation;
    private boolean facingLeft = false;
    private boolean facingUp = false;
    private boolean facingDown = false;
    private boolean facingRight = false;
    private Bullet bullet;
    private ArrayList<Bullet> activeBullets;
    public Player(int typePlayer, float initialX, float initialY) {
        if (typePlayer == 1) {
            // Get dog
            movement = new Movement(Constant.DOGSHEET1, Constant.DOGSHEET2, Constant.DOGSHEET3);
        } else {
            // Get Cat
            movement = new Movement(Constant.CATSHEET1, Constant.CATSHEET2, Constant.CATSHEET3);
        }


        this.x = initialX;
        this.y = initialY;
        this.width = 30;
        this.height = 30;
        regions = movement.activities();
        loadAnimations(regions);
        currentAnimation = animations.get("stand");
//        bullet = new Bullet(initialX, initialY);
        activeBullets = new ArrayList<>();

    }

    private void loadAnimations(HashMap<String, TextureRegion[]> activities) {
        for (String action : activities.keySet()) {
            TextureRegion[] frames = activities.get(action);
            float frameDuration = 0.1f;
            animations.put(action, new Animation<>(frameDuration, frames));
        }
    }

    public void update(float delta) {
        elapsedTime += delta;
        if (currentAnimation != null && currentAnimation.isAnimationFinished(elapsedTime)) {
            setAnimation("stand");
        }

        // Update bullets and remove inactive ones
        ArrayList<Bullet> bulletToRemove = new ArrayList<>();
        for (Bullet bullet1 : activeBullets) {
            bullet1.update(delta);
            if (bullet1.remove) {
                bulletToRemove.add(bullet1);
            }
        }
        activeBullets.removeAll(bulletToRemove);

    }

    public void render(SpriteBatch batch, int n) {
        TextureRegion frame = currentAnimation.getKeyFrame(elapsedTime, true);  // Looping animation
        batch.draw(frame, facingLeft ? x + frame.getRegionWidth() : x, y,
            facingLeft ? -frame.getRegionWidth() : frame.getRegionWidth(), frame.getRegionHeight());


        // Render active bullets
        for (Bullet bullet : activeBullets) {
            bullet.render(batch);
        }

    }

    public void move(Direction direction, float delta) {
        float speed = MOVE_SPEED * delta; // Adjust speed as needed
        x += direction.getXMultiplier() * speed;
        y += direction.getYMultiplier() * speed;
        facingLeft = (direction == Direction.LEFT);
        facingUp = (direction == Direction.UP);
        facingDown = (direction == Direction.DOWN);
        facingRight = (direction == Direction.RIGHT);

        // Set the appropriate animation
        setAnimation("walk");

        // Ensure the player doesn't move out of bounds
        checkBorder();
    }

    public void skill1() {
        // Handle diagonal shooting logic based on player's direction
        if (facingUp && facingLeft) {
            setAnimation("shortUp");
            fireBullet(-1, 1, "up"); // Fire bullet diagonally up-left at 45-degree angle
        } else if (facingUp && facingRight) {
            setAnimation("shortUp");
            fireBullet(1, 1, "up"); // Fire bullet diagonally up-right at 45-degree angle
        } else if (facingDown && facingLeft) {
            setAnimation("shortDownLeft");
            fireBullet(-1, -1, "down"); // Fire bullet diagonally down-left at 45-degree angle
        } else if (facingDown && facingRight) {
            setAnimation("shortDown");
            fireBullet(1, -1, "down"); // Fire bullet diagonally down-right at 45-degree angle
        } else if (facingUp) {
            setAnimation("shortUp");
            fireBullet(0, 1, "up"); // Fire bullet upwards
        } else if (facingDown) {
            setAnimation("shortDown");
            fireBullet(0, -1, "down"); // Fire bullet downwards
        } else if (facingLeft) {
            setAnimation("shortLeft");
            fireBullet(-1, 0, "left"); // Fire bullet to the left
        } else if (facingRight) {
            setAnimation("shortRight");
            fireBullet(1, 0, "right"); // Fire bullet to the right
        }
    }



    public void skill2() {
        setAnimation("shotEnergyLong");

    }

    public void skill3() {
        setAnimation("shotSuper");
    }


    private void setAnimation(String action) {
        Animation<TextureRegion> animation = animations.get(action);
        if (animation != null && animation != currentAnimation) {
            currentAnimation = animation;
            elapsedTime = 0;
        }
    }

    private void checkBorder() {
        if (x < 0) {
            x = 0; // Left boundary
        }
        if (x + width > Constant.GAME_SCREEN_WIDTH) {
            x = Constant.GAME_SCREEN_WIDTH - width; // Right boundary
        }
        if (y < 0) {
            y = 0; // Bottom boundary
        }
        if (y + height > Constant.GAME_SCREEN_HEIGHT) {
            y = Constant.GAME_SCREEN_HEIGHT - height; // Top boundary
        }
    }
    private void fireBullet(float directionX, float directionY, String bulletType) {
        // Normalize diagonal direction for 45-degree flight
        float magnitude = (float) Math.sqrt(directionX * directionX + directionY * directionY); // Get the magnitude
        float normalizedX = directionX / magnitude; // Normalize X component
        float normalizedY = directionY / magnitude; // Normalize Y component

        // Adjust bullet speed (we'll keep the speed consistent for diagonal movement)
        float bulletSpeed = 1f; // Adjust this value to control the bullet speed

        // Create the bullet with the normalized direction and speed
        activeBullets.add(new Bullet(x + width / 2, y + height / 2, normalizedX * bulletSpeed, normalizedY * bulletSpeed, facingLeft, bulletType));
        System.out.println("Bullet Position: (" + x + ", " + y + ")");
    }



}
