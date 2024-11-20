package io.github.lap1597;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {

    private static final Map<String, Animation<TextureRegion>> animations = new HashMap<>();
    private static final int FRAME_WIDTH = 64;
    private static final int FRAME_HEIGHT = 64;

    /**
     * Loads animations based on player type.
     *
     * @param type 1 for dog animations, 2 for cat animations.
     */
    public static void loadAnimations(int type) {
        String sheetNormal = (type == 1) ? Constant.DOGSHEET1 : Constant.CATSHEET1;
        String sheetPower = (type == 1) ? Constant.DOGSHEET2 : Constant.CATSHEET2;

        Texture sheet1 = new Texture(sheetNormal);
        Texture sheet2 = new Texture(sheetPower);

        TextureRegion[][] tmpFrames1 = TextureRegion.split(sheet1, FRAME_WIDTH, FRAME_HEIGHT);
        TextureRegion[][] tmpFrames2 = TextureRegion.split(sheet2, FRAME_WIDTH, FRAME_HEIGHT);

        // Load animations for each activity
        animations.put("stand", createAnimation(tmpFrames1, 0, 4, 0.1f));
        animations.put("walk", createAnimation(tmpFrames1, 1, 8, 0.1f));
        animations.put("combo", createAnimation(tmpFrames1, 9, 10, 0.08f));
        animations.put("spin", createAnimation(tmpFrames1, 3, 10, 0.1f));
        animations.put("kick", createAnimation(tmpFrames1, 10, 12, 0.1f));
        animations.put("shotEnergyLong", createAnimation(tmpFrames1, 5, 7, 0.1f));
        animations.put("shotEnergyFast", createAnimation(tmpFrames1, 6, 6, 0.1f));
        animations.put("shotSuper", createAnimation(tmpFrames2, 0, 10, 0.1f));
        animations.put("shotUpLong", createAnimation(tmpFrames2, 2, 7, 0.1f));
        animations.put("shotUpFast", createAnimation(tmpFrames2, 3, 6, 0.1f));
        animations.put("shotDown", createAnimation(tmpFrames2, 5, 6, 0.1f));
        animations.put("die", createAnimation(tmpFrames1, 4, 7, 0.1f));

        // Dispose textures after loading animations
        sheet1.dispose();
        sheet2.dispose();
    }

    /**
     * Returns the animation for the specified action.
     *
     * @param key The action name (e.g., "stand", "walk").
     * @return The Animation<TextureRegion> for the specified key, or null if not found.
     */
    public static Animation<TextureRegion> getAction(String key) {
        return animations.getOrDefault(key, null);
    }

    /**
     * Creates an animation for a specific action from a sprite sheet.
     *
     * @param frames      The 2D array of TextureRegions split from the sheet.
     * @param row         The row index where the animation frames are located.
     * @param frameCount  The number of frames for the animation.
     * @param frameDuration The duration of each frame in seconds.
     * @return An Animation<TextureRegion> object.
     */
    private static Animation<TextureRegion> createAnimation(TextureRegion[][] frames, int row, int frameCount, float frameDuration) {
        TextureRegion[] animationFrames = new TextureRegion[frameCount];
        for (int i = 0; i < frameCount; i++) {
            animationFrames[i] = frames[row][i];
        }
        return new Animation<>(frameDuration, animationFrames);
    }

    /**
     * Disposes all animations to free resources.
     * This should be called when the game is closing or changing context.
     */
    public static void dispose() {
        animations.clear();
    }
}
