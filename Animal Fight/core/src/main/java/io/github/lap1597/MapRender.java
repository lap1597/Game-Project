package io.github.lap1597;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MapRender {
    private final int tileSize;
    private final MapAssetManager assetManager;

    public MapRender(MapAssetManager assetManager, int tileSize) {
        this.assetManager = assetManager;
        this.tileSize = tileSize; // Tile size in pixels
    }

    public void render(SpriteBatch batch, int[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                Texture tile = getTileTexture(map[y][x]);
                if (tile != null) {
                    batch.draw(tile, x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        }
    }

    private Texture getTileTexture(int tileCode) {
        Texture texture = null;
        switch (tileCode) {
            case 9:
                texture = assetManager.getAsset("rock");
                break;
            case 0:
                texture = assetManager.getAsset("grass");
                break;
            default:
                texture = null;
                break;
        }
        return texture;
    }

}
