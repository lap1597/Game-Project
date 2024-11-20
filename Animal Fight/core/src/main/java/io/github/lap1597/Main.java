//package io.github.lap1597;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.graphics.g2d.Animation;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.utils.ScreenUtils;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//
//import static com.badlogic.gdx.Input.Keys.*;
//
///** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
//public class Main extends ApplicationAdapter {
//    private SpriteBatch batch1;
//    private SpriteBatch batch2;
//    private Player player1;
//    private Player player2;
//    private GameControl gc1;
//    private GameControl gc2;
//
//    private float delta = 0f;
//    protected float elapsedTime = 0f;
//
//    Animation<TextureRegion> action1Animation;
//
//    @Override
//    public void create() {
//        batch1 = new SpriteBatch();
//        batch2 = new SpriteBatch();
//        player1 = new Player(1,100,100);
//        player2 = new Player(2,400,400);
//        gc1 = new GameControl(player1);
//        gc2 = new GameControl(player2);
//
//    }
//
//    @Override
//    public void render() {
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        delta = Gdx.graphics.getDeltaTime();
//        gc1.controlInput(delta, W,S,A,D,R,T,Y);
//        gc2.controlInput(delta, UP, DOWN, LEFT, RIGHT,J,K,L);
//
//        player1.update(delta);
//        player2.update(delta);
//
//        batch1.begin();
//        batch2.begin();
//        player1.render(batch1, 1);
//
//        player2.render(batch2, 2);
//        batch1.end();
//        batch2.end();
//    }
//
//    @Override
//    public void dispose() {
//        batch1.dispose();
//        batch2.dispose();
//
//    }
//
//}
package io.github.lap1597;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
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
    private float delta = 0f;
    protected float elapsedTime = 0f;
     private MapAssetManager mapAssetManager;
 private MapRender mapRender;
    private int[][] map = MapLoader.loadMap(); // 2D map array loaded from CSV
    private final int tileSize = 32; // Size of each map tile in pixels
    Animation<TextureRegion> action1Animation;
    @Override
    public void create() {
        batch1 = new SpriteBatch();
// batch2 = new SpriteBatch();
        player1 = new Player(1,100,100);
        player2 = new Player(2,400,400);
        gc1 = new GameControl(player1);
        gc2 = new GameControl(player2);
 mapAssetManager = new MapAssetManager();
 mapAssetManager.loadAssets();
 mapRender = new MapRender(mapAssetManager, tileSize);

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
//batch2.begin();
 mapRender.render(batch1, map);
        player1.render(batch1, 1);
        player2.render(batch1, 2);
        batch1.end();
        batch2.end();
// if (mapAssetManager.isLoaded()) {
// // If assets are loaded, render the game
// batch1.begin();
// mapRender.render(batch1, map); // Render map
// player1.render(batch1, 1); // Render player 1
// player2.render(batch1, 2); // Render player 2
// batch1.end();
// } else {
// // If assets are still loading, display loading progress
// float progress = mapAssetManager.getProgress();
// System.out.println(&quot;Loading assets: &quot; + progress * 100 + &quot;%&quot;);
// }
    }
    @Override
    public void dispose() {
        batch1.dispose();
// batch2.dispose();
 mapAssetManager.dispose();
    }
}
