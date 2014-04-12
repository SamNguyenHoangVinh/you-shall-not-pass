package vn.edu.rmit.youshallnotpass.universe.darkside;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;
import vn.edu.rmit.youshallnotpass.mechanism.Tuple;
import vn.edu.rmit.youshallnotpass.universe.GalaxyManager;

/**
 * Created by benjamin on 1/1/14.
 */

public class Executor {
    private static final int MAX_EXPLOSION = 20;

    private SpriteManager sprites;

    private TextureRegion normalFrame;
    private TextureRegion hitFrame;
    private Array<Animation> detonateExplosions;
    private Float[] detonateTimes;
    private Array<Boolean> detonateStarted;
    private Array<Tuple> detonatePositions;
    private Array<Sound> soundExplosions;
    private Array<Boolean> explosionPlayed;

    private Tuple pos;                          // Save
    private Tuple offPos;
    private Tuple offPosHit;
    private Tuple offPosExplode;

    private Array<TextureRegion> parBullets;    // Rebuilding
    private Array<Tuple> parTupBullets;         // Save
    private Tuple offParBullet;
    private Array<TextureRegion> parLasers;
    private Array<Tuple> parTupLasers;
    private Tuple offParLaser;
    private Array<TextureRegion> parRays;
    private Array<Tuple> parTupRays;
    private Tuple offParRay;

    private int bulletSpeed;
    private int bulletFrequency;
    private int laserSpeed;
    private int laserFrequency;
    private int raySpeed;
    private int rayFrequency;

    private float bulletPower;
    private float laserPower;
    private float rayPower;

    private int indexBullet = 0;            // Save
    private int indexLaser = 0;
    private int indexRay = 0;

    private Array<Float> offset;            // Save

    private int counterExplosion = 0;
    private int bulletLeft = 0;
    private int bulletRight = 0;
    private int laserLeft = 0;
    private int laserRight = 0;
    private int rayLeftRight = 0;
    private int rayCounter = 0;

    private float desX = 0;                 // Save
    private float desY = 0;
    private int moveCounter = 0;            // Save
    private boolean called = false;         // Save
    private boolean called2 = false;

    private int hSpeed;
    private int vSpeed;
    private int hInterval;
    private int vInterval;
    private float health;                   // Save
    private float maxHealth;

    private int score;
    private int focus = 0;
    private int focusLeftRight = 0;
    private int delay = 150;
    private int reset = 200;

    private int level;

    public Executor(int level) {
        sprites = SpriteManager.getInstance();
        this.level = level;

        constructLevel();
        constructDefault();
        constructAnimation();
    }

    private void constructLevel() {
        if (level == SpriteManager.NORMAL_LEVEL) {
            bulletSpeed = 7;
            bulletFrequency = 100;
            laserSpeed = 10;
            laserFrequency = 100;
            raySpeed = 50;
            rayFrequency = 1;

            bulletPower = 1;
            laserPower = 2;
            rayPower = 0.1f;

            hSpeed = 7;
            vSpeed = 5;
            hInterval = 2;
            vInterval = 10;
            maxHealth = 1000;
            health = 1000;
            score = 1000;
        } else if (level == SpriteManager.INSANE_LEVEL) {
            bulletSpeed = 10;
            bulletFrequency = 30;
            laserSpeed = 20;
            laserFrequency = 50;
            raySpeed = 50;
            rayFrequency = 1;

            bulletPower = 1;
            laserPower = 2;
            rayPower = 0.3f;

            hSpeed = 10;
            vSpeed = 7;
            hInterval = 2;
            vInterval = 10;
            maxHealth = 2000;
            health = 2000;
            score = 2000;

            delay = 100;
        }
    }

    private void constructDefault() {
        bulletLeft = bulletFrequency;
        bulletRight = bulletFrequency - 20;
        laserLeft = laserFrequency;
        laserRight = laserFrequency - 15;
        rayLeftRight = rayFrequency;
        rayCounter = rayFrequency;
    }

    private void constructAnimation() {
        normalFrame = sprites.getEnemyShipFrame(SpriteManager.EXECUTOR, SpriteManager.NORMAL);
        hitFrame = sprites.getEnemyShipFrame(SpriteManager.EXECUTOR, SpriteManager.HIT);
        detonateExplosions = new Array<Animation>();
        detonateTimes = new Float[MAX_EXPLOSION];
        detonateStarted = new Array<Boolean>();
        detonatePositions = new Array<Tuple>();
        soundExplosions = new Array<Sound>();
        explosionPlayed = new Array<Boolean>();
        for (int i = 0; i < MAX_EXPLOSION; i++) {
            int model = (SpriteManager.EXPLO1 - 1) + (int)(Math.random() * (SpriteManager.EXPLO5 - (SpriteManager.EXPLO1 - 1)) + 1);
            Animation explosion = sprites.getExplosionAnimation(model);
            explosion.setPlayMode(Animation.NORMAL);
            detonateExplosions.insert(i, explosion);
            detonateTimes[i] = 0f;
            detonateStarted.insert(i, false);
            soundExplosions.insert(i, sprites.getShipExplosionSound());
            explosionPlayed.insert(i, false);
        }

        parBullets = new Array<TextureRegion>();
        parTupBullets = new Array<Tuple>();
        parLasers = new Array<TextureRegion>();
        parTupLasers = new Array<Tuple>();
        parRays = new Array<TextureRegion>();
        parTupRays = new Array<Tuple>();
        offset = new Array<Float>();

        offPosHit = new Tuple(sprites.getOffSetWidth(SpriteManager.EXECUTOR, SpriteManager.HIT),
                sprites.getOffSetHeight(SpriteManager.EXECUTOR, SpriteManager.HIT));

        offPos = new Tuple(sprites.getOffSetWidth(SpriteManager.EXECUTOR, SpriteManager.NORMAL),
                sprites.getOffSetHeight(SpriteManager.EXECUTOR, SpriteManager.NORMAL));

        offPosExplode = new Tuple(sprites.getOffSetWidth(SpriteManager.EXPLO5, SpriteManager.NORMAL),
                sprites.getOffSetHeight(SpriteManager.EXPLO5, SpriteManager.NORMAL));

        pos = new Tuple(SpriteManager.SCREEN_W / 2, SpriteManager.SCREEN_H + offPos.y);

        offParBullet = new Tuple(sprites.getOffSetWidth(SpriteManager.ENE_BULL_3, SpriteManager.NORMAL),
                sprites.getOffSetHeight(SpriteManager.ENE_BULL_3, SpriteManager.NORMAL));

        offParLaser = new Tuple(sprites.getOffSetWidth(SpriteManager.BULLET2, SpriteManager.NORMAL),
                sprites.getOffSetHeight(SpriteManager.BULLET2, SpriteManager.NORMAL));

        offParRay = new Tuple(sprites.getOffSetWidth(SpriteManager.BULLET4, SpriteManager.NORMAL),
                sprites.getOffSetHeight(SpriteManager.BULLET4, SpriteManager.NORMAL));

        desX = (float)Math.random() * SpriteManager.SCREEN_W + 1;

        desY = 200 + (float)Math.random() * (SpriteManager.SCREEN_H - 200) + 1;
    }

    public void setLevel(int level) {
        this.level = level;
        constructLevel();
    }

    public void render(SpriteBatch batch, boolean isHit, float power) {
        // Check health
        if (checkHealth(batch)) {
            return;
        }

        // Shoot
        shoot(batch);

        // Move
        move();

        // Check Hit
        checkHit(batch, isHit, power);
    }

    private boolean checkHealth(SpriteBatch batch) {
        if (health <= 0) {
            // Bullets remain moving
//            for (int i = 0; i < parBullets.size; i++) {
//                parTupBullets.set(i, new Tuple(parTupBullets.get(i).x, parTupBullets.get(i).y - bulletSpeed));
//                batch.draw(parBullets.get(i).getKeyFrame(5, true), parTupBullets.get(i).x - offParBullet.x, parTupBullets.get(i).y - offParBullet.y);
//                if (parTupBullets.get(i).y < 0) {
//                    parBullets.removeIndex(i);
//                    parTupBullets.removeIndex(i);
//                    indexBullet--;
//                }
//            }

            // Playing explode animation
            if (isDestroyed()) {
                return true;
            }
            int timeToStart = 5;
            if (counterExplosion % timeToStart == 0 && counterExplosion < MAX_EXPLOSION * timeToStart) {
                int index = counterExplosion / timeToStart;
                Gdx.app.log("i", String.valueOf(index));
                float x = (pos.x - offPosExplode.x) + (float)(Math.random() * ((pos.x - offPosExplode.x) - (pos.x + offPosExplode.x)) + 1);
                float y = (pos.y - offPosExplode.y) + (float)(Math.random() * ((pos.y - offPosExplode.y) - (pos.y + offPosExplode.y)) + 1);
                detonatePositions.insert(index, new Tuple(x, y));
                detonateStarted.insert(index, true);
                if (!explosionPlayed.get(index)) {
                    GalaxyManager.playSound(soundExplosions.get(index));
                    explosionPlayed.insert(index, false);
                }
            }
            counterExplosion++;
            for (int i = 0; i < MAX_EXPLOSION; i++) {
                if (detonateStarted.get(i)) {
                    detonateTimes[i] += Gdx.graphics.getDeltaTime();
                    if (!detonateExplosions.get(i).isAnimationFinished(detonateTimes[i])) {
                        batch.draw(detonateExplosions.get(i).getKeyFrame(detonateTimes[i]), detonatePositions.get(i).x, detonatePositions.get(i).y);
                    }
                }
            }
            return true;
        }
        return false;
    }

    private void shoot(SpriteBatch batch) {
        bulletLeft++;
        bulletRight++;
        laserLeft++;
        laserRight++;
        focus++;
        focusLeftRight++;
        if (bulletLeft % bulletFrequency == 0) {
            parBullets.insert(indexBullet, sprites.getBulletFrame(SpriteManager.ENE_BULL_3));
            parTupBullets.insert(indexBullet, new Tuple(pos.x - 92, pos.y - offParBullet.y * 5));
            indexBullet++;
            parBullets.insert(indexBullet, sprites.getBulletFrame(SpriteManager.ENE_BULL_3));
            parTupBullets.insert(indexBullet, new Tuple(pos.x + 92, pos.y - offParBullet.y * 5));
            indexBullet++;
            GalaxyManager.playSound(sprites.getBulletSound(), 0.2f);
        }
        if (bulletRight % bulletFrequency == 0) {
            parBullets.insert(indexBullet, sprites.getBulletFrame(SpriteManager.ENE_BULL_3));
            parTupBullets.insert(indexBullet, new Tuple(pos.x - 60, pos.y - offParBullet.y * 5));
            indexBullet++;
            parBullets.insert(indexBullet, sprites.getBulletFrame(SpriteManager.ENE_BULL_3));
            parTupBullets.insert(indexBullet, new Tuple(pos.x + 60, pos.y - offParBullet.y * 5));
            indexBullet++;
            GalaxyManager.playSound(sprites.getBulletSound(), 0.2f);
        }
        for (int i = 0; i < parBullets.size; i++) {
            parTupBullets.set(i, new Tuple(parTupBullets.get(i).x, parTupBullets.get(i).y - bulletSpeed));
            batch.draw(parBullets.get(i), parTupBullets.get(i).x - offParBullet.x, parTupBullets.get(i).y - offParBullet.y);
            if (parTupBullets.get(i).y < 0) {
                parBullets.removeIndex(i);
                parTupBullets.removeIndex(i);
                indexBullet--;
            }
        }
        if (laserLeft % laserFrequency == 0) {
            parLasers.insert(indexLaser, sprites.getBulletFrame(SpriteManager.BULLET2));
            parTupLasers.insert(indexLaser, new Tuple(pos.x - 164, pos.y - offParLaser.y * 2 + 40));
            indexLaser++;
            parLasers.insert(indexLaser, sprites.getBulletFrame(SpriteManager.BULLET2));
            parTupLasers.insert(indexLaser, new Tuple(pos.x + 164, pos.y - offParLaser.y * 2 + 40));
            indexLaser++;
            GalaxyManager.playSound(sprites.getLaserSound(), 0.2f);
        }
        if (laserRight % laserFrequency == 0) {
            parLasers.insert(indexLaser, sprites.getBulletFrame(SpriteManager.BULLET2));
            parTupLasers.insert(indexLaser, new Tuple(pos.x - 140, pos.y - offParLaser.y * 2 + 25));
            indexLaser++;
            parLasers.insert(indexLaser, sprites.getBulletFrame(SpriteManager.BULLET2));
            parTupLasers.insert(indexLaser, new Tuple(pos.x + 140, pos.y - offParLaser.y * 2 + 25));
            indexLaser++;
            GalaxyManager.playSound(sprites.getLaserSound(), 0.2f);sprites.getLaserSound().play(0.2f);
        }
        for (int i = 0; i < parLasers.size; i++) {
            parTupLasers.set(i, new Tuple(parTupLasers.get(i).x, parTupLasers.get(i).y - laserSpeed));
            batch.draw(parLasers.get(i), parTupLasers.get(i).x - offParLaser.x, parTupLasers.get(i).y - offParLaser.y);
            if (parTupLasers.get(i).y < 0) {
                parLasers.removeIndex(i);
                parTupLasers.removeIndex(i);
                indexLaser--;
            }
        }
        if (focus > delay) {
            if (rayCounter % rayFrequency == 0) {
                parRays.insert(indexRay, sprites.getBulletFrame(SpriteManager.BULLET3));
                offset.insert(indexRay, -5f);
                parTupRays.insert(indexRay, new Tuple(0, pos.y));
                indexRay++;
//                GalaxyManager.playSound(sprites.getRaySound(), 0.1f);
            }
            rayCounter++;
        }
        if (focusLeftRight > delay + 50) {
            if (rayLeftRight % rayFrequency == 0) {
                parRays.insert(indexRay, sprites.getBulletFrame(SpriteManager.BULLET4));
                offset.insert(indexRay, -21f);
                parTupRays.insert(indexRay, new Tuple(0, pos.y));
                indexRay++;
                parRays.insert(indexRay, sprites.getBulletFrame(SpriteManager.BULLET4));
                offset.insert(indexRay, +19f);
                parTupRays.insert(indexRay, new Tuple(0, pos.y));
                indexRay++;
            }
            rayLeftRight++;
        }
        if (focus > reset) {
            focus = 0;
        }
        if (focusLeftRight > reset + 50) {
            focusLeftRight = 0;
        }
        for (int i = 0; i < parRays.size; i++) {
            parTupRays.set(i, new Tuple(pos.x + offset.get(i), parTupRays.get(i).y - raySpeed));
            batch.draw(parRays.get(i), parTupRays.get(i).x - offParRay.x, parTupRays.get(i).y - offParRay.y * 2);
            if (parTupRays.get(i).y < 0) {
                parRays.removeIndex(i);
                offset.removeIndex(i);
                parTupRays.removeIndex(i);
                indexRay--;
            }
        }
    }

    private void move() {
        moveCounter++;
        if (moveCounter % hInterval == 0) {
            if (Math.abs(desX - pos.x) > hSpeed && pos.x < desX) {
                pos.x += hSpeed;
            } else if (Math.abs(desX - pos.x) > hSpeed && pos.x > desX) {
                pos.x -= hSpeed;
            } else if (pos.x >= SpriteManager.SCREEN_W - offPos.x || pos.x <= offPos.x || Math.abs(desX - pos.x) < hSpeed){
                desX = (float)Math.random() * SpriteManager.SCREEN_W + 1;
            }
        }
        if (moveCounter % vInterval == 0) {
            if (Math.abs(desY - pos.y) > vSpeed && pos.y < desY) {
                pos.y += vSpeed;
            } else if (Math.abs(desY - pos.y) > vSpeed && pos.y > desY) {
                pos.y -= vSpeed;
            } else if (pos.y >= SpriteManager.SCREEN_H - offPos.y || pos.y <= offPos.y + 200 || Math.abs(desY - pos.y) < vSpeed){
                desY = 200 + (float)Math.random() * (SpriteManager.SCREEN_H - 200) + 1;
            }
//            if (pos.y >= 550) {
//                pos.y -= vSpeed;
//            }
        }
    }

    private void checkHit(SpriteBatch batch, boolean isHit, float power) {
        if (isHit) {
            batch.draw(hitFrame, pos.x - offPosHit.x, pos.y - offPosHit.y);
            health -= power;
        } else {
            batch.draw(normalFrame, pos.x - offPos.x, pos.y - offPos.y);
        }
    }

    public boolean isOffScreen() {
        if (pos.y + offPos.y < 0) {
            return true;
        }
        return false;
    }

    public Tuple getPos() {
        return pos;
    }

    public Tuple getOffPos() {
        return offPos;
    }

    public Array<Tuple> getParTupBullets() {
        return parTupBullets;
    }

    public Tuple getOffParBullet() {
        return offParBullet;
    }

    public Array<Tuple> getParTupLasers() {
        return parTupLasers;
    }

    public Tuple getOffParLaser() {
        return offParLaser;
    }

    public Array<Tuple> getParTupRays() {
        return parTupRays;
    }

    public Tuple getOffParRay() {
        return offParRay;
    }

    public boolean isDestroyed() {
        for (int i = 0; i < MAX_EXPLOSION; i++) {
            if (!detonateExplosions.get(i).isAnimationFinished(detonateTimes[i]) || !detonateStarted.get(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean isToBeRemoved() {
        for (int i = 0; i < MAX_EXPLOSION; i++) {
            soundExplosions.get(i).dispose();
        }
        return isDestroyed() && parBullets.size == 0;
    }

    public boolean isDead() {
        if (!called && health <= 0) {
            called = true;
            return true;
        }
        return false;
    }

    public boolean signalSelfDestruct() {
        return health <= 0;
    }

    public boolean unleashExecution() {
        return health < maxHealth / 2;
    }

    public boolean isUnleashExecution() {
        if (!called2 && health < maxHealth / 2) {
            called2 = true;
            return true;
        }
        return false;
    }

    public void removeBullet(int index) {
        parBullets.removeIndex(index);
        parTupBullets.removeIndex(index);
        this.indexBullet--;
    }

    public void removeLaser(int index) {
        parLasers.removeIndex(index);
        parTupLasers.removeIndex(index);
        this.indexLaser--;
    }

    public void removeRay(int index) {
        parRays.removeIndex(index);
        offset.removeIndex(index);
        parTupRays.removeIndex(index);
        this.indexRay--;
    }

    public float getBulletPower() {
        return bulletPower;
    }

    public float getLaserPower() {
        return laserPower;
    }

    public float getRayPower() {
        return rayPower;
    }

    public int getScore() {
        return score;
    }

    public float getParX(int index) {
        return pos.x + offset.get(index);
    }

    public void save() {
        Preferences preferences = sprites.getPreferences();
        preferences.putFloat("ExePosX", pos.x);
        preferences.putFloat("ExePosY", pos.y);
        preferences.putInteger("ExeIndexBullet", indexBullet);
        preferences.putInteger("ExeIndexLaser", indexLaser);
        preferences.putInteger("ExeIndexRay", indexRay);
        preferences.putInteger("ExeMoveCounter", moveCounter);
        preferences.putFloat("ExeDesX", desX);
        preferences.putFloat("ExeDesY", desY);
        preferences.putFloat("ExeHealth", health);
        preferences.putFloat("ExeMaxHealth", maxHealth);
        preferences.putBoolean("ExeCalled", called);
        preferences.putBoolean("ExeCalled2", called2);
        preferences.putInteger("ExeBulletLeft", bulletLeft);
        preferences.putInteger("ExeBulletRight", bulletRight);
        preferences.putInteger("ExeLaserLeft", laserLeft);
        preferences.putInteger("ExeLaserRight", laserRight);
        preferences.putInteger("ExeRayLeftRight", rayLeftRight);
        preferences.putInteger("ExeRayCounter", rayCounter);
        for (int i = 0; i < parTupBullets.size; i++) {
            preferences.putFloat("ExeBulletX" + i, parTupBullets.get(i).x);
            preferences.putFloat("ExeBulletY" + i, parTupBullets.get(i).y);
        }
        preferences.putInteger("ExeBulletSize", parTupBullets.size);
        for (int i = 0; i < parTupLasers.size; i++) {
            preferences.putFloat("ExeLaserX" + i, parTupLasers.get(i).x);
            preferences.putFloat("ExeLaserY" + i, parTupLasers.get(i).y);
        }
        preferences.putInteger("ExeLaserSize", parTupLasers.size);
        for (int i = 0; i < parTupRays.size; i++) {
            preferences.putFloat("ExeRayX" + i, parTupRays.get(i).x);
            preferences.putFloat("ExeRayY" + i, parTupRays.get(i).y);
        }
        preferences.putInteger("ExeRaySize", parTupRays.size);
        for (int i = 0; i < offset.size; i++) {
            preferences.putFloat("ExeOffset" + i, offset.get(i));
        }
        preferences.putInteger("ExeOffsetSize", offset.size);
        preferences.putInteger("ExeFocus", focus);
        preferences.putInteger("ExeFocusLeftRight", focusLeftRight);
        preferences.flush();
    }

    public void load() {
        Preferences preferences = sprites.getPreferences();
        pos = new Tuple(preferences.getFloat("ExePosX"),
                preferences.getFloat("ExePosY"));
        indexBullet = preferences.getInteger("ExeIndexBullet");
        indexLaser = preferences.getInteger("ExeIndexLaser");
        indexRay = preferences.getInteger("ExeIndexRay");
        moveCounter = preferences.getInteger("ExeMoveCounter");
        desX = preferences.getFloat("ExeDesX");
        desY = preferences.getFloat("ExeDesY");
        health = preferences.getFloat("ExeHealth");
        maxHealth = preferences.getFloat("ExeMaxHealth");
        called = preferences.getBoolean("ExeCalled");
        called2 = preferences.getBoolean("ExeCalled2");
        bulletLeft = preferences.getInteger("ExeBulletLeft");
        bulletRight = preferences.getInteger("ExeBulletRight");
        laserLeft = preferences.getInteger("ExeLaserLeft");
        laserRight = preferences.getInteger("ExeLaserRight");
        rayLeftRight = preferences.getInteger("ExeRayLeftRight");
        rayCounter = preferences.getInteger("ExeRayCounter");
        int size = preferences.getInteger("ExeBulletSize");
        for (int i = 0; i < size; i++) {
            parTupBullets.insert(i, new Tuple(preferences.getFloat("ExeBulletX" + i),
                    preferences.getFloat("ExeBulletY" + i)));
            parBullets.insert(i, sprites.getBulletFrame(SpriteManager.ENE_BULL_3));
        }
        size = preferences.getInteger("ExeLaserSize");
        for (int i = 0; i < size; i++) {
            parTupLasers.insert(i, new Tuple(preferences.getFloat("ExeLaserX" + i),
                    preferences.getFloat("ExeLaserY" + i)));
            parLasers.insert(i, sprites.getBulletFrame(SpriteManager.BULLET2));
        }
        size = preferences.getInteger("ExeOffsetSize");
        for (int i = 0; i < size; i++) {
            offset.insert(i, preferences.getFloat("ExeOffset" + i));
        }
        size = preferences.getInteger("ExeRaySize");
        for (int i = 0; i < size; i++) {
            parTupRays.insert(i, new Tuple(preferences.getFloat("ExeRayX" + i),
                    preferences.getFloat("ExeRayY" + i)));
            if (offset.get(i) == -5f) {
                parRays.insert(i, sprites.getBulletFrame(SpriteManager.BULLET3));
            } else {
                parRays.insert(i, sprites.getBulletFrame(SpriteManager.BULLET4));
            }
        }
        focus = preferences.getInteger("ExeFocus");
        focusLeftRight = preferences.getInteger("ExeFocusLeftRight");
    }
}
