package io.github.lap1597;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
public class Movement {
    private Texture sheet;


    int frameHeight = 64;
    int frameWidth = 64;
    TextureRegion[][] tmpFrames;

    public Movement() {
        System.out.println("Its here");
        sheet = new Texture("sprite_sheet.png");
        tmpFrames = TextureRegion.split(sheet, frameWidth, frameHeight);
    }

    public TextureRegion[] getAction1(){
        TextureRegion[] action1Frames = new TextureRegion[8];
        for (int i = 0; i < 8; i++) {
            action1Frames[i] = tmpFrames[1][i];  // Extract row 0 frames
        }
        return action1Frames;
    }
    public void dispose() {
        if (sheet != null) {
            sheet.dispose();
        }
    }
}
