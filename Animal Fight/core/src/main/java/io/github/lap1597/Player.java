package io.github.lap1597;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class Player {
    private Movement movement;
    private Items items = new Items(15,0);;
    private int health;
    private static int typePlayer;
    private float elapsedTime = 0f;
    private float x, y;
    private float width, height;
    private HashMap<String, TextureRegion[]> regions;
    private HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();  // Stores animations by action
    private Animation<TextureRegion> currentAnimation;
    private boolean isLoadingGun = false;
    private boolean facingLeft = false;
    private boolean isWalking = false;
    private boolean isShooting = false;
    private boolean isSkill1Active = false;
    private boolean isSkill2Active = false;
    private boolean isSkill3Active = false;
    public Player(int typePlayer,float initialX, float initialY) {

        if(typePlayer == 1){
            //Get dog
            movement = new Movement(Constant.DOGSHEET1, Constant.DOGSHEET2, Constant.DOGSHEET3);
        }else{
            //get Cat
            movement = new Movement(Constant.CATSHEET1, Constant.CATSHEET2, Constant.CATSHEET3);
        }

//        items = new Items(15,0);
        this.x = initialX; // x coordinate
        this.y = initialY; // y coordinate
        this.width = 30;
        this.height = 30;
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
        attack();

        if (isShooting) {
            setAnimation("shoot");  // Assuming 'shooting' is an existing animation name
        }
        // Otherwise, set the animation based on the state of items (e.g., having bullets)
        else if (items.getBulletCount() > 0) {
            setAnimation("standGun");
        }
        // Check if the player has energy and the corresponding animations
        else if (items.getEnergyCount() > 0) {
            setAnimation("stand");
        }
        // If the animation finishes, reset to the standing animation
        else if (currentAnimation != null && currentAnimation.isAnimationFinished(elapsedTime) && !items.hasNoItems()) {
            setAnimation("stand");
        }


    }

    public void render(SpriteBatch batch, int n) {
        TextureRegion frame = currentAnimation.getKeyFrame(elapsedTime, true);  // Looping animation

        batch.draw(frame, facingLeft ? x + frame.getRegionWidth() : x, y,
            facingLeft ? -frame.getRegionWidth() : frame.getRegionWidth(), frame.getRegionHeight());
    }

    public void moveUp(float delta) {
        y += 100 * delta;
        isWalking = true;
        if( items.getBulletCount()>0){
                setAnimation("walkGun");
        }else if(items.getEnergyCount()>0 && isShooting ){
            setAnimation("walkShoot");
        }
        else {
            setAnimation("walk");
         //   isWalking = true;
        }
        checkBorder();
    }

    public void moveDown(float delta) {
        y -= 100*delta;
        setAnimation("walk");
        isWalking = true;
        checkBorder();
    }

    public void moveLeft(float delta) {
        x -= 100*delta;
        facingLeft = true;
        setAnimation("walk");
        isWalking = true;
        checkBorder();
    }

    public void moveRight(float delta) {

        x += 100*delta;
        facingLeft = false;
        setAnimation("walk");
        isWalking = true;
        checkBorder();
    }
    public void attack() {
        if (items.hasNoItems()) {
            if (isSkill1Active) skill1("spin");  // Ensure spin animation is activated
            if (isSkill2Active) skill2("combo");
            if (isSkill3Active) skill3("kick");
        } else if (items.getBulletCount() > 0) {
            if (isSkill1Active) skill1("stand");

            if (isSkill2Active) skill2("shoot");

                skill3("");  // No skill
        } else if (items.getEnergyCount() > 0) {
            if (isSkill1Active) skill1("shotEnergyLong");
            if (isSkill2Active) skill2("shotSuper");
            skill3("");  // No skill
        }
    }
    public void skill1(String action) {
        if (!action.isEmpty()) {
            //We decide to keep this Item or not
            // by unloading the item and set back to stand

            items.unload();
            setAnimation(action);
            isSkill1Active = false;

        }
    }
    public void skill2(String action) {
        if (!action.isEmpty()) {
            if (action.equals("shoot") || action.equals("shotEnergyLong") || action.equals("shotSuper")) {
                isShooting = true;  // Start shooting
            }
            setAnimation(action);
        }
        isSkill2Active = false;
    }

    public void skill3(String action) {
        if (!action.isEmpty()) {
            setAnimation(action);
        }
        isSkill2Active = false;
    }
    public void activateSkill1() {
        isSkill1Active = true;
    }

    public void activateSkill2() {
        isSkill2Active = true;
    }

    public void activateSkill3() {
        isSkill3Active = true;
    }

    public void deactivateSkill1() {
        isSkill1Active = false;
    }

    public void deactivateSkill2() {
        isSkill2Active = false;
    }

    public void deactivateSkill3() {
        isSkill3Active = false;
    }
    private void setAnimation(String action) {
        Animation<TextureRegion> animation = animations.get(action);
        if (animation != null && animation != currentAnimation) {
            currentAnimation = animation;
            elapsedTime = 0;

        }

    }
    private void checkBorder() {
        if (x < 0){
            x = 0; // Left boundary
        }
        if (x + width > Constant.GAME_SCREEN_WIDTH){
            x = Constant.GAME_SCREEN_WIDTH - width; // Right boundary
        }
        if (y < 0){
            y = 0; // Bottom boundary
        }
        if (y + height > Constant.GAME_SCREEN_HEIGHT){
            y = Constant.GAME_SCREEN_HEIGHT - height; // Top boundary
        }
    }


}
