package io.github.lap1597;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameGUI extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    private Button playButton;
    private Slider volumeSlider;
    private Music backgroundMusic;
    private SpriteBatch batch;

    @Override
    public void create() {
        // Initialize stage and skin
        stage = new Stage(new ScreenViewport());
      //  skin = new Skin(Gdx.files.internal("uiskin.json"));  // Assume you have a skin JSON file

        // Set up play button
        playButton = new Button();
        playButton.setPosition(100, 200);
        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                startGame();
            }
        });

        // Set up volume slider
        volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        volumeSlider.setPosition(100, 100);
        volumeSlider.setValue(0.5f);  // Set default volume level to 50%
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = volumeSlider.getValue();
                setVolume(volume);
            }
        });

        // Add the buttons and slider to the stage
        stage.addActor(playButton);
        stage.addActor(volumeSlider);

        // Load background music
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("soundFile/s6.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        // Set up batch for rendering
        batch = new SpriteBatch();

        // Set the stage to capture input
        Gdx.input.setInputProcessor(stage);
    }

    private void startGame() {
        // Your logic to start the game (switch to game screen, etc.)
        System.out.println("Play button clicked. Game starting...");
    }

    private void setVolume(float volume) {
        // Adjust the volume of the background music
        backgroundMusic.setVolume(volume);
    }

    @Override
    public void render() {
        // Clear screen
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the stage and UI
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Adjust stage viewport when the window size changes
        stage.getViewport().update(width, height, true);
    }

//    @Override
//    public void hide() {
//        // Clean up resources when this screen is no longer visible
//        backgroundMusic.stop();
//    }

    @Override
    public void dispose() {
        // Dispose of resources like stage and skin
        stage.dispose();
        skin.dispose();
        backgroundMusic.dispose();
        batch.dispose();
    }
}
