package io.github.lap1597;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

public class MapAssetManager implements Disposable {
    private final AssetManager assetManager = new AssetManager();
    private final HashMap<String, Texture> assetMap = new HashMap<>();

    // Load assets
    public void loadAssets() {
        // Queue assets for loading
        assetManager.load("MapResource/rock_in_water_01.png", Texture.class);
        assetManager.load("MapResource/grass.png", Texture.class);

        // Finish loading assets
        assetManager.finishLoading();

        // Store textures in HashMap for quick access
        assetMap.put("rock", assetManager.get("MapResource/rock_in_water_01.png", Texture.class));
        assetMap.put("grass", assetManager.get("MapResource/grass.png", Texture.class));
    }

    // Get an asset
    public Texture getAsset(String key) {
        return assetMap.get(key);
    }

    // Dispose assets to free memory
    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
