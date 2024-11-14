package io.github.lap1597;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Movement {
    private Texture sheet1;
    private Texture sheet2;
    private Texture sheet3;

    private int frameHeight = 64;
    private int frameWidth = 64;
    private TextureRegion[][] tmpFrames1;
    private TextureRegion[][] tmpFrames2;
    private TextureRegion[][] tmpFrames3;


    public Movement(String sheetNomal, String sheetGun, String sheetPower) {
        sheet1 = new Texture(sheetNomal);
        sheet2 = new Texture(sheetGun);
        sheet3 = new Texture(sheetPower);
        tmpFrames1 = TextureRegion.split(sheet1, frameWidth, frameHeight);
        tmpFrames2 = TextureRegion.split(sheet2, frameWidth, frameHeight);
        tmpFrames3 = TextureRegion.split(sheet3, frameWidth, frameHeight);
    }

    public HashMap<String,TextureRegion []> activities(){
        HashMap<String, TextureRegion[]> allActivities = new HashMap<>();
        //STAND
        TextureRegion[] stand = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            stand[i] = tmpFrames1[0][i];

        }
        allActivities.put("stand", stand);

        //Walk
        TextureRegion[] walk = new TextureRegion[8];
        for (int i = 0; i < 8; i++) {
            walk[i] = tmpFrames1[1][i];

        }
        allActivities.put("walk", walk);
        //COMBO attack
        TextureRegion[] combo = new TextureRegion[10];
        for (int i = 0; i < 10; i++) {
            combo[i] = tmpFrames1[9][i];

        }
        allActivities.put("combo", combo);
        //Spin
        TextureRegion[] spin = new TextureRegion[10];
        for (int i = 0; i < 10; i++) {
            spin[i] = tmpFrames1[3][i];

        }
        allActivities.put("spin", spin);
        // KICK
        TextureRegion[] kick = new TextureRegion[12];
        for (int i = 0; i < 12; i++) {
            kick[i] = tmpFrames1[10][i];

        }
        allActivities.put("kick", kick);
        //SHORT ENERGY LONG
        TextureRegion[] shotEnergyLong = new TextureRegion[7];
        for (int i = 0; i < 7; i++) {
            shotEnergyLong[i] = tmpFrames1[5][i];

        }

        allActivities.put("shotEnergyLong", shotEnergyLong);
        //SHOT ENERGY Fast
        TextureRegion[] shotEnergyFast = new TextureRegion[6];
        for (int i = 0; i < 6; i++) {
            shotEnergyFast[i] = tmpFrames1[6][i];
        }
        allActivities.put("shotEnergyFast", shotEnergyFast);
        //SUPER ENERGY
        TextureRegion[] shotSuper = new TextureRegion[13];
        for (int i = 0; i < 13; i++) {
            shotSuper[i] = tmpFrames3[0][i];
        }
        allActivities.put("shotSuper", shotSuper);

        //DIE - NORMAL
        TextureRegion[] die = new TextureRegion[7];
        for (int i = 0; i < 7; i++) {
            die[i] = tmpFrames1[4][i];

        }

        allActivities.put("die", die);
        //FOR GUN
        //Load gun
        TextureRegion[] loadGun = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            loadGun[i] = tmpFrames2[0][i];
        }
        allActivities.put("loadGun", loadGun);
        //Stand gun
        TextureRegion[] standGun = new TextureRegion[8];
        for (int i = 0; i < 8; i++) {
            standGun[i] = tmpFrames2[1][i];
        }

        allActivities.put("standGun", standGun);

        //Stand shot
        TextureRegion[] standShot = new TextureRegion[8];
        for (int i = 0; i < 8; i++) {
            standShot[i] = tmpFrames2[2][i];
        }
        allActivities.put("shoot", standShot);
        // Walk gun
        TextureRegion[] walkGun = new TextureRegion[8];
        for (int i = 0; i < 8; i++) {
            walkGun[i] = tmpFrames2[3][i];
        }

        allActivities.put("walkGun", walkGun);

        //Walk shoot
        TextureRegion[] walkShoot = new TextureRegion[8];
        for (int i = 0; i < 8; i++) {
            walkShoot[i] = tmpFrames2[4][i];
        }

        allActivities.put("walkShoot", walkShoot);
        //DIe gun
        TextureRegion[] dieGun = new TextureRegion[7];
        for (int i = 0; i < 7; i++) {
            dieGun[i] = tmpFrames2[8][i];
        }

        allActivities.put("dieGun", dieGun);

        return allActivities;
    }

    public void dispose() {

        sheet1.dispose();
        sheet2.dispose();
        sheet3.dispose();

    }
}
