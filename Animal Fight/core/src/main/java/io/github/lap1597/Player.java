package io.github.lap1597;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.*;

public class Player {

 //   private AssetManager assetManager;

    private int  health;
    private int  speed;
    private static int typePlayer;
    private float elapsedTime = 0f;
    private static final float MOVE_SPEED = 100f;
    private float x, y;
    private float width, height;

    private Animation<TextureRegion> currentAnimation;

    private boolean facingLeft = false;
    private boolean facingUp = false;
    private boolean facingDown = false;
    private boolean facingRight = false;
    private boolean collisionX = false;
    private boolean collisionY = false;


    private boolean collision;
    private static TiledMap map = MapManager.loadMap("MapResource/map1.tmx");

    private ArrayList<Bullet> activeBullets;
    private SoundManager shootingSound;
    private Items items;
    private float timeSinceLastShot = 0f; // Time tracker for shooting

    public Player(int type,float initialX, float initialY) {

        //Default
        this.typePlayer = type;
        AssetManager.loadAnimations(typePlayer);
        this.items = new Items(1);
        this.health = 5;
        this.speed = 5;

        this.x = initialX;
        this.y = initialY;
        this.width = 10;
        this.height = 10;

        currentAnimation = AssetManager.getAction("stand");
        activeBullets = new ArrayList<>();
//        shootingSound.loadSound("shoot","soundFile/shotfiring.wav");
    }
    private void setAnimation(String action, float del) {

        Animation<TextureRegion> animation = AssetManager.getAction(action);
        if (animation != null && animation != currentAnimation) {
            currentAnimation = animation;
            elapsedTime = del;
        }
    }

    public void update(float delta) {
        elapsedTime += delta;
        timeSinceLastShot += delta; // Update the cooldown timer

        if (currentAnimation != null && currentAnimation.isAnimationFinished(elapsedTime)) {
            setAnimation("stand", 0);
        }


        // Update bullets and remove inactive ones
        ArrayList<Bullet> bulletToRemove = new ArrayList<>();
        for (Bullet bullet1 : activeBullets) {

            bullet1.update(delta);
            if (bullet1.remove) {
                bulletToRemove.add(bullet1);
            }
        }
        activeBullets.removeAll(bulletToRemove);
    }

    public void render(SpriteBatch batch, int n) {
        System.out.println(currentAnimation);
        TextureRegion frame = currentAnimation.getKeyFrame(elapsedTime, true);  // Looping animation
        batch.draw(frame, facingLeft ? x + frame.getRegionWidth() : x, y,
            facingLeft ? -frame.getRegionWidth() : frame.getRegionWidth(), frame.getRegionHeight());

        // Render active bullets

        for (Bullet bullet : activeBullets) {
            if( bullet.getType() == BulletType.ENERGY || bullet.getType() == BulletType.EXPLOSIVE){
                if(currentAnimation.isAnimationFinished(elapsedTime)){
                    bullet.render(batch);
                }
            }
            bullet.render(batch);
        }

    }

    // New method to handle movement based on angle (supports diagonal movement)
    public void moveWithAngle(float directionX, float directionY, float delta) {
        // Normalize the direction vector (to maintain consistent speed in all directions)



        float speed = MOVE_SPEED * delta;
        float length = (float) Math.sqrt(directionX * directionX + directionY * directionY);
        directionX /= length;
        directionY /= length;

        // Move the player based on the normalized direction
        float newX =x + directionX * speed;
        float newY =y + directionY * speed;
        // Check collision for the new position
        boolean collisionLeft = isCollision(newX, y); // Check left
        boolean collisionRight = isCollision(newX + width, y); // Check right
        boolean collisionUp = isCollision(x, newY + height); // Check top
        boolean collisionDown = isCollision(x, newY); // Check bottom

        // Update position only if no collision
        if (!collisionLeft && !collisionRight) {
            x = newX;
        }
        if (!collisionUp && !collisionDown) {
            y = newY;
        }
        // Determine facing direction for animation (optional)
        facingLeft = directionX < 0;
        facingRight = directionX > 0;
        facingUp = directionY > 0;
        facingDown = directionY < 0;

        // Set the appropriate animation for movement
        setAnimation("walk", 0);

        // Ensure the player doesn't move out of bounds

        checkBorder();
    }

    public void skill1() {
        if(items.getEnergyCount() >0) {


            if (facingUp) {
                setAnimation("shortUpFast", 0.5f);
                fireBullet(0, 1, BulletType.NORMAL, 0.3f); // Fire bullet upwards
            } else if (facingDown) {
                setAnimation("shortDown", 0.5f);
                fireBullet(0, -1, BulletType.NORMAL, 0.3f); // Fire bullet downwards
            } else if (facingLeft) {
                setAnimation("shotEnergyFast", 0.5f);
                fireBullet(-1, 0, BulletType.NORMAL, 0.3f); // Fire bullet to the left
            } else if (facingRight) {
                setAnimation("shotEnergyFast", 0.5f);
                fireBullet(1, 0, BulletType.NORMAL, 0.3f); // Fire bullet to the right
            }
        }else{
            setAnimation("combo",0f);
        }


    }


    public void skill2() {
        if(items.getEnergyCount() >0) {
            if (facingUp) {
                setAnimation("shotUpFast", 0.3f);

                fireBullet(0, 1, BulletType.ENERGY, 0.5f); // Fire bullet upwards
            } else if (facingDown) {
                setAnimation("shotDown", 0.3f);
                fireBullet(0, -1, BulletType.ENERGY, 0.5f); // Fire bullet downwards
            } else if (facingLeft) {
                setAnimation("shotEnergyLong", 0.3f);
                fireBullet(-1, 0, BulletType.ENERGY, 0.5f); // Fire bullet to the left
            } else if (facingRight) {
                setAnimation("shotEnergyLong", 0.3f);
                fireBullet(1, 0, BulletType.ENERGY, 0.5f); // Fire bullet to the right
            }
        }else{
            setAnimation("kick",0f);
        }

    }

    public void skill3() {
        if(items.getEnergyCount() >0) {
            if (facingUp) {
                setAnimation("shotSuper", 0.5f);

                fireBullet(0, 1, BulletType.EXPLOSIVE, 1f); // Fire bullet upwards
            } else if (facingDown) {
                setAnimation("shotSuper", 0.5f);
                fireBullet(0, -1, BulletType.EXPLOSIVE, 1f); // Fire bullet downwards
            } else if (facingLeft) {
                setAnimation("shotSuper", 0.5f);
                fireBullet(-1, 0, BulletType.EXPLOSIVE, 1f); // Fire bullet to the left
            } else if (facingRight) {
                setAnimation("shotSuper", 0.5f);
                fireBullet(1, 0, BulletType.EXPLOSIVE, 1f); // Fire bullet to the right
            }
        }else{
            setAnimation("spin",0f);
        }
    }



    private void checkBorder() {
        if (x < 0) {
            x = 0; // Left boundary
        }
        if (x + width > Constant.GAME_SCREEN_WIDTH) {
            x = Constant.GAME_SCREEN_WIDTH - width; // Right boundary
        }
        if (y < 0) {
            y = 0; // Bottom boundary
        }
        if (y + height > Constant.GAME_SCREEN_HEIGHT) {
            y = Constant.GAME_SCREEN_HEIGHT - height; // Top boundary
        }
    }
//    private void checkCollision() {
//        TiledMapTileLayer unPassLayer = (TiledMapTileLayer) map.getLayers().get("Unbreakable");
//        boolean collisionX =false;
//        boolean collisionY= false;
//        float tiledWith =unPassLayer.getTileWidth();
//        float tiledHeight = unPassLayer.getTileHeight();
//        //top left
//       collisionX = unPassLayer.getCell(
//           (int)(x/tiledWith),
//           (int)((y+ height)/tiledHeight))
//           .getTile()
//           .getProperties()
//           .containsKey("Unbreakable");
//        //middle left
//        //
//        if(!collisionX)
//
//
//           collisionX |=unPassLayer.getCell(
//               (int)(x/tiledWith),
//               (int)((y+tiledHeight/2)/tiledHeight))
//               .getTile()
//               .getProperties()
//               .containsKey("Unbreakable");
//       //bottom left
//        if(!collisionX)
//            collisionX |= unPassLayer.getCell(
//                (int)(x/tiledWith),
//                (int)(y/tiledHeight))
//            .getTile()
//            .getProperties()
//            .containsKey("Unbreakable");
//
//
//        //top right
//        collisionX = unPassLayer.getCell(
//                (int)((x+ width)/tiledWith),
//                (int)((y+ height)/tiledHeight))
//            .getTile()
//            .getProperties()
//            .containsKey("Unbreakable");
//        //middle right
//        if(!collisionX){
//            collisionX = unPassLayer.getCell(
//                    (int)((x+ width)/tiledWith),
//                    (int)((y+ width/2)/tiledHeight))
//                .getTile()
//                .getProperties()
//                .containsKey("Unbreakable");
//        }
//        //bottom right
//        if(!collisionX)
//            collisionX |= unPassLayer.getCell(
//                    (int)((x+width)/tiledWith),
//                    (int)((y )/tiledHeight))
//                .getTile()
//                .getProperties()
//                .containsKey("Unbreakable");
//
//
//    }
private boolean isCollision(float x, float y) {
    // Get the specific layer (e.g., layer3)
    TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get("Unbreakable");

    // Convert world coordinates to tile coordinates
    int tileX = (int) (x / collisionLayer.getTileWidth());
    int tileY = (int) (y / collisionLayer.getTileHeight());

    // Get the cell at the tile position
    TiledMapTileLayer.Cell cell = collisionLayer.getCell(tileX, tileY);

    if (cell != null) {
        // Check if the tile has the 'collision' property
        MapProperties properties = cell.getTile().getProperties();
        if (properties.containsKey("Test") && properties.get("Test", Boolean.class)) {
            return true; // Collision detected
        }
    }

    return false; // No collision
}

//    private void checkCollision(float newX, float newY) {
//        // Check horizontal collision
//        if (isColliding(newX, y) || isColliding(newX + width, y) || isColliding(newX, y + height) || isColliding(newX + width, y + height)) {
//            collisionX = true;
//        } else {
//            collisionX = false;
//        }
//
//        // Check vertical collision
//        if (isColliding(x, newY) || isColliding(x + width, newY) || isColliding(x, newY + height) || isColliding(x + width, newY + height)) {
//            collisionY = true;
//        } else {
//            collisionY = false;
//        }
//    }


    private void fireBullet(float directionX, float directionY,  BulletType type,float cooldownTime) {
        // Check if the cooldown period has passed
        if (timeSinceLastShot >= cooldownTime) {
            // Add a new bullet with the given direction
            activeBullets.add(new Bullet(
                x + width / 2, // Spawn bullet at player's center
                y + height / 2,
                directionX,
                directionY,
                facingLeft,
                type
            ));
            SoundManager.playSound("shoot");
            // Reset the cooldown timer
            timeSinceLastShot = 0f;

            System.out.println("Bullet fired! Direction: (" + directionX + ", " + directionY + ")");
        } else {
            System.out.println("Cooldown active: Can't fire yet.");
        }
    }
}
