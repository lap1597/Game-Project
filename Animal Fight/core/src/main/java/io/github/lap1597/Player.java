package io.github.lap1597;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Player {
    private Movement movement;
    private int health;
    private static int typePlayer;
    private float elapsedTime = 0f;
    private float x, y;
    private HashMap<String, TextureRegion[]> regions;
    private HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();  // Stores animations by action
    private Animation<TextureRegion> currentAnimation;
    private boolean isLoadingGun = false;
    public Player(int typePlayer) {

        if(typePlayer == 1){
            //Get dog
            movement = new Movement(Constant.DOGSHEET1, Constant.DOGSHEET2, Constant.DOGSHEET3);

        }else{
            //get Cat
            movement = new Movement(Constant.CATSHEET1, Constant.CATSHEET2, Constant.CATSHEET3);

        }
        regions = movement.activities();
        loadAnimations(regions);
        currentAnimation = animations.get("stand");


    }

    private void loadAnimations(HashMap<String, TextureRegion[]> activities) {
        for (String action : activities.keySet()) {
            TextureRegion[] frames = activities.get(action);
            float frameDuration = 0.1f;
            if(action.equals("loadGun")){
                frameDuration=0.2f;
            }
            animations.put(action, new Animation<>(frameDuration, frames));  // 0.1 seconds per frame
        }
    }
    public void update(float delta) {
        elapsedTime += delta;

        if (isLoadingGun) {
            if (currentAnimation.isAnimationFinished(elapsedTime)) {

                setAnimation("standGun");

            }
        } else if (currentAnimation != null && currentAnimation.isAnimationFinished(elapsedTime)) {
            setAnimation("stand");
        }
    }

    public void render(SpriteBatch batch, int n) {
        TextureRegion frame = currentAnimation.getKeyFrame(elapsedTime, true);  // Looping animation
        if(n ==1){
            batch.draw(frame, x, y);
        }
        else {

            batch.draw(frame, x+20, y+20);
        }
    }

    public void moveUp(float delta) {
        y += 100*delta;
        setAnimation("walk");
    }

    public void moveDown(float delta) {
        y -= 100*delta;

        setAnimation("walk");
    }

    public void moveLeft(float delta) {
        x -= 100*delta;

        setAnimation("walk");
    }

    public void moveRight(float delta) {
        x += 100*delta;

        setAnimation("walk");
    }

    public void attack(float delta) {
        setAnimation("loadGun");
        isLoadingGun = true;

    }

    public void hit(float delta) {
        setAnimation("combo");

    }

    public void kick(float delta) {
        setAnimation("kick");

    }
    private void setAnimation(String action) {
        Animation<TextureRegion> animation = animations.get(action);
        if (animation != null && animation != currentAnimation) {
            currentAnimation = animation;
            elapsedTime = 0;
        }

    }
    private void resetToStandAfterMove() {

        setAnimation("stand");
    }
}
