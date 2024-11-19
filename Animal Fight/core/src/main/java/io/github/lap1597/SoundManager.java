package io.github.lap1597;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;

public class SoundManager {
    private static final HashMap<String, Sound> sounds = new HashMap<>();

    // Load a sound into the manager
    public static void loadSound(String key, String filePath) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal(filePath));
        sounds.put(key, sound);
    }

    // Play a sound by key
    public static void playSound(String key) {
        Sound sound = sounds.get(key);
        if (sound != null) {
            sound.play(); // Play the sound
        } else {
            System.err.println("Sound not found: " + key);
        }
    }

    // Dispose all sounds
    public static void dispose() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
        sounds.clear();
    }
}
