package io.github.lap1597;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.HashMap;

public class Bullet {
    private static Animation<TextureRegion> effectAnimation;  // Static animation shared across all Bullet instances
    private float x, y;            // Position of the bullet
    private float speed = 300;     // Speed of the bullet in X and Y directions
    private float directionX, directionY; // Direction of the bullet's movement
    private Rectangle bounds;      // Bounding box for collision detection
    private boolean active;
    private float animationTime;   // Time tracker for animation
    public boolean remove = false;
    private boolean facingLeft;
    private HashMap<String, TextureRegion[]> bullets = initializeAnimation();
    // Static block to initialize the effect animation once
//    static {
//        effectAnimation = initializeAnimation();
//    }

    public Bullet(float x, float y, float directionX, float directionY,boolean facingLeft, String type ) {
        this.x = x;
        this.y = y;
        this.directionX = directionX;
        this.directionY = directionY;
        this.facingLeft = facingLeft;
        this.bounds = new Rectangle(x, y, 64, 64); // Adjust dimensions as needed
        this.active = true;
        this.animationTime = 0.3f;
        effectAnimation = new Animation<>(animationTime, bullets.get(type));
    }

    // Static method to initialize the animation once
    private HashMap<String, TextureRegion[]> initializeAnimation() {
        try {
           HashMap<String, TextureRegion[]> differentBullets = new HashMap<>();
            float frameDuration = 1f;
            Texture texture = new Texture("shotEffect.png");

            // Check if the texture is loaded correctly
            if (texture == null) {
                System.err.println("Failed to load texture: shotEffect.png");
              return null;
            }

            TextureRegion[][] tmpFrames = TextureRegion.split(texture, 32, 32);
            TextureRegion[][] tmpFrames1 = TextureRegion.split(texture, 64, 64);
            // Check if the texture splitting worked correctly
            if (tmpFrames.length == 0 || tmpFrames[0].length < 3) {
                throw new RuntimeException("Invalid sprite sheet: Ensure there are at least 3 frames");
            }

            TextureRegion[] straightShot = new TextureRegion[4];
            for (int i = 0; i < 4; i++) {
                straightShot[i] = tmpFrames[0][i];
            }
            differentBullets.put("straight", straightShot);

            TextureRegion[] upShot = new TextureRegion[4];
            for (int i = 0; i < 4; i++) {
                upShot[i] = tmpFrames[1][i];
            }
            differentBullets.put("up", upShot);
            TextureRegion[] downShot = new TextureRegion[4];
            for (int i = 0; i < 4; i++) {
                downShot[i] = tmpFrames[2][i];
            }
            differentBullets.put("down", downShot);

            TextureRegion[] bigShot = new TextureRegion[4];
            for (int i = 0; i < 4; i++) {
                bigShot[i] = tmpFrames[3][i];
            }
            differentBullets.put("big", bigShot);
            TextureRegion[] superShot = new TextureRegion[4];
            for (int i = 0; i < 4; i++) {
                superShot[i] = tmpFrames[0][i+9];
            }
            differentBullets.put("big", superShot);




            // Return the animation created from the frames
            return differentBullets;
        } catch (Exception ex) {
            System.err.println("Bullet animation initialization failed: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public void update(float deltaTime) {
        // Move the bullet based on its direction
        x += directionX * speed * deltaTime;
        y += directionY * speed * deltaTime;

        // Deactivate if off-screen
        if (y > Constant.END_MENU_SCREEN_WIDTH || y < 0 || x < 0 || x > Constant.END_MENU_SCREEN_HEIGHT) {
            active = false;
        }

        // Update animation time
        animationTime += deltaTime;
    }

    public void render(SpriteBatch batch) {
        // Check if the effectAnimation is null before rendering
        if (effectAnimation != null) {
            // Get the current frame of the animation
            TextureRegion currentFrame = effectAnimation.getKeyFrame(animationTime, true);

            // Check if the current frame is null
            float width = currentFrame.getRegionWidth();
            if (facingLeft) {
                // Flip the texture horizontally
                batch.draw(currentFrame, x + width, y, -width, currentFrame.getRegionHeight());
            } else {
                batch.draw(currentFrame, x, y);
            }
        } else {
            System.err.println("Error: effectAnimation is null.");
        }
    }

    public void dispose() {
        // Dispose of the texture when done, but the static animation is shared across instances
        // No need to dispose the animation texture here since it's managed statically
    }
}
