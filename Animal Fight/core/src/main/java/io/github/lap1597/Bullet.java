package io.github.lap1597;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {

 //   private static Animation<TextureRegion> effectAnimation;
    private static Animation<TextureRegion> normalEffectAnimation;
    private static Animation<TextureRegion> energyEffectAnimation;
    private static Animation<TextureRegion> explosiveEffectAnimation;
    private float x, y; // Position of the bullet
    private float speed = 200; // Speed of the bullet in X and Y directions
    private float directionX, directionY; // Direction of the bullet's movement
    private Rectangle bounds; // Bounding box for collision detection
    private boolean active;
    private float animationTime; // Time tracker for animation
    public boolean remove = false;
    private boolean facingLeft; // To flip the bullet when needed
    private float angle; // Angle for rotation
    private BulletType type; // Bullet type
    static {
        normalEffectAnimation = initializeAnimation(1);
        energyEffectAnimation = initializeAnimation(2);
        explosiveEffectAnimation = initializeAnimation(3);
    }

    public Bullet(float x, float y, float directionX, float directionY, boolean facingLeft, BulletType type) {
        this.x = x;
        this.y = y;
        this.directionX = directionX;
        this.directionY = directionY;
        this.facingLeft = facingLeft;
        this.bounds = new Rectangle(x, y, 64, 64); // Adjust dimensions as needed
        this.active = true;
        this.animationTime = 0f;
        this.type = type;

        // Calculate the angle for rotation based on direction
        this.angle = (float) Math.toDegrees(Math.atan2(directionY, directionX));
    }

    private static Animation<TextureRegion> initializeAnimation(int type) {
        try {
            if(type == 1) {
                Texture texture = new Texture("shotEffect.png");
                TextureRegion[][] tmpFrames = TextureRegion.split(texture, 32, 32);
                TextureRegion[] gunShot = new TextureRegion[4];
                float frameDuration = 0.1f;
                for (int i = 0; i < 4; i++) {
                    gunShot[i] = tmpFrames[0][i];
                }
                return new Animation<>(frameDuration, gunShot);
            }else if(type == 2) {
                Texture texture = new Texture("shotEffect.png");
                TextureRegion[][] tmpFrames = TextureRegion.split(texture, 32, 32);
                TextureRegion[] gunShot = new TextureRegion[4];
                float frameDuration = 0.1f;
                for (int i = 0; i < 4; i++) {
                    gunShot[i] = tmpFrames[3][i];
                }
                return new Animation<>(frameDuration, gunShot);
            }else if(type == 3) {
                Texture texture = new Texture("shotEffect.png");
                TextureRegion[][] tmpFrames = TextureRegion.split(texture, 64, 64);
                TextureRegion[] gunShot = new TextureRegion[4];
                float frameDuration = 0.1f;
                for (int i = 0; i < 4; i++) {
                    gunShot[i] = tmpFrames[0][i+4];
                }
                return new Animation<>(frameDuration, gunShot);
            }
        } catch (Exception ex) {
            System.err.println("Bullet initialization failed: " + ex.getMessage());
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
    private Animation<TextureRegion> getEffectAnimationForType() {
        switch (type) {
            case ENERGY:
                return energyEffectAnimation;
            case EXPLOSIVE:
                return explosiveEffectAnimation;
            case NORMAL:
            default:
                return normalEffectAnimation;
        }
    }

    public void render(SpriteBatch batch) {
        // Get the current frame of the animation

        TextureRegion currentFrame = getEffectAnimationForType().getKeyFrame(animationTime,true);

        // Flip the bullet image if it's facing left
        float width = currentFrame.getRegionWidth();

        // Apply the rotation based on the angle calculated earlier
        batch.draw(currentFrame, x, y, width / 2, currentFrame.getRegionHeight() / 2, width, currentFrame.getRegionHeight(), 1, 1, angle);
    }

    public boolean isRemove() {
        return !active;
    }
    public BulletType getType() {
        return type;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }
}
