package io.github.lap1597;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.HashMap;

public class MapManager {
    // Static HashMap to store maps
    private static HashMap<String, TiledMap> mapCache = new HashMap<>();

    // Load a map and store it in the mapCache
    public static TiledMap loadMap(String mapName) {
        if (mapCache.containsKey(mapName)) {
            return mapCache.get(mapName); // Return cached map if it exists
        } else {
            TiledMap map = new TmxMapLoader().load(mapName); // Load the map
            mapCache.put(mapName, map); // Store it in the cache
            return map;
        }
    }

    // Optionally, you can clear the map cache if needed
    public static void clearCache() {
        mapCache.clear();
    }

    // Get map by name
    public static TiledMap getMap(String mapName) {
        return mapCache.get(mapName);
    }
}
