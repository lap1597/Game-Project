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
    private Player player;
    private GameControl gc;
    float delta = 0f;
    float elapsedTime = 0f;

    Animation<TextureRegion> action1Animation;
    @Override
    public void create() {

        batch = new SpriteBatch();
        player = new Player(1);
        gc = new GameControl(player);




    }


    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        delta = Gdx.graphics.getDeltaTime();
        gc.controlInput(delta);
        player.update(delta);


        batch.begin();
        player.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }
}
