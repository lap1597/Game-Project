package io.github.lap1597;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movement {


   private static final Map<String, Animation<TextureRegion>> animations = new HashMap<>();


   private static void loadAnimations(int type){
       int frameHeight = 64;
       int frameWidth = 64;
       String sheetNormal;
       String sheetPower;
       if(type == 1) {
           sheetNormal = Constant.DOGSHEET1;
           sheetPower = Constant.DOGSHEET2;
       }else{
           sheetNormal = Constant.CATSHEET1;
           sheetPower = Constant.CATSHEET2;
       }
       Texture sheet1 = new Texture(sheetNormal);
       Texture sheet2 = new Texture(sheetPower);
       TextureRegion[][] tmpFrames1 = TextureRegion.split(sheet1, frameWidth, frameHeight);
       TextureRegion[][]  tmpFrames2 = TextureRegion.split(sheet2, frameWidth, frameHeight);


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
       TextureRegion[] shotSuper = new TextureRegion[10];
       for (int i = 0; i < 10; i++) {
           shotSuper[i] = tmpFrames2[0][i];
       }
       allActivities.put("shotSuper", shotSuper);

       //Short AIr up Long
       TextureRegion[] shortUpLong = new TextureRegion[7];
       for (int i = 0; i < 7; i++) {
           shortUpLong[i] = tmpFrames2[2][i];
       }
       allActivities.put("shotUpLong", shortUpLong);
       TextureRegion[] shortUpFast = new TextureRegion[6];
       for (int i = 0; i < 6; i++) {
           shortUpFast[i] = tmpFrames2[3][i];
       }
       allActivities.put("shotUpFast", shortUpFast);

       TextureRegion[] shortDown = new TextureRegion[6];
       for (int i = 0; i < 6; i++) {
           shortDown[i] = tmpFrames2[5][i];
       }
       allActivities.put("shotDown", shortDown);

       //DIE - NORMAL
       TextureRegion[] die = new TextureRegion[7];
       for (int i = 0; i < 7; i++) {
           die[i] = tmpFrames1[4][i];

       }


       allActivities.put("die", die);

       for (String action : allActivities.keySet()) {
           TextureRegion[] frames = allActivities.get(action);
           float frameDuration = 0.1f;
           animations.put(action, new Animation<>(frameDuration, frames));
       }
       //Release resources
       sheet1.dispose();
       sheet2.dispose();
   }






}
