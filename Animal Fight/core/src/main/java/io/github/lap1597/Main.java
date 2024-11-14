package io.github.lap1597;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import static com.badlogic.gdx.Input.Keys.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch1;
    private SpriteBatch batch2;
    private Player player1;
    private Player player2;
    private GameControl gc1;
    private GameControl gc2;
    float delta = 0f;
    float elapsedTime = 0f;

    Animation<TextureRegion> action1Animation;
    @Override
    public void create() {

        batch1 = new SpriteBatch();
        batch2 = new SpriteBatch();
        player1 = new Player(1,100,100);
        player2 = new Player(2,400,400);
        gc1 = new GameControl(player1);
        gc2 = new GameControl(player2);


    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        delta = Gdx.graphics.getDeltaTime();
        gc1.controlInput(delta, W,S,A,D,R,T,Y);
        gc2.controlInput(delta, UP, DOWN, LEFT, RIGHT,J,K,L);


        player1.update(delta);
        player2.update(delta);
        batch1.begin();
        batch2.begin();
        player1.render(batch1,1);
        player2.render(batch2,2);
        batch1.end();
        batch2.end();
    }

    @Override
    public void dispose() {
        batch1.dispose();
        batch2.dispose();

    }
}
