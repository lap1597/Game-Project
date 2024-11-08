package io.github.lap1597;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    float frameDuration = 0.1f;
    float elapsedTime = 0f;
    Movement test;
    TextureRegion[] walk;
    Animation<TextureRegion> action1Animation;
    @Override
    public void create() {

        batch = new SpriteBatch();
        test = new Movement();
        walk = test.getAction1();
        action1Animation = new Animation<>(frameDuration, walk);

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        elapsedTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = action1Animation.getKeyFrame(elapsedTime, true);

        batch.begin();
        batch.draw(currentFrame, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        test.dispose();
    }
}
