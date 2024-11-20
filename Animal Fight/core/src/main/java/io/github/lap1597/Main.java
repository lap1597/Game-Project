package io.github.lap1597;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.Iterator;

import static com.badlogic.gdx.Input.Keys.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch1;
    private SpriteBatch batch2;
    private Player player1;
    private Player player2;
    private GameControl gc1;
    private GameControl gc2;

    private static TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;


    private float delta = 0f;
    protected float elapsedTime = 0f;

    Animation<TextureRegion> action1Animation;

    @Override
    public void create() {
        batch1 = new SpriteBatch();
     //   batch2 = new SpriteBatch();
        player1 = new Player(1,100,100);
        player2 = new Player(2,400,400);
        gc1 = new GameControl(player1);
        gc2 = new GameControl(player2);
      //  System.out.println(Gdx.files.internal("MapResource/map1.tmx").exists());
        map = MapManager.loadMap("MapResource/map1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();


    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.setView(camera);
        renderer.render();



        delta = Gdx.graphics.getDeltaTime();
        gc1.controlInput(delta, W,S,A,D,R,T,Y);
        gc2.controlInput(delta, UP, DOWN, LEFT, RIGHT,J,K,L);

        player1.update(delta);
        player2.update(delta);

        batch1.begin();
       // batch2.begin();
        player1.render(batch1, 1);

        player2.render(batch1, 2);
        batch1.end();
       // batch2.end();
    }
    @Override
    public void resize(int width, int height) {
//        camera.viewportWidth = Constant.GAME_SCREEN_WIDTH;
//        camera.viewportHeight = Constant.GAME_SCREEN_HEIGHT;
        camera.setToOrtho(false, Constant.GAME_SCREEN_WIDTH, Constant.GAME_SCREEN_HEIGHT);
        camera.update();
    }

    @Override
    public void dispose() {
        batch1.dispose();
        batch2.dispose();
        map.dispose();
        renderer.dispose();

    }

}
