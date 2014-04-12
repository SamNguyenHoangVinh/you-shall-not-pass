package vn.edu.rmit.youshallnotpass.universe.lightside;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;
import vn.edu.rmit.youshallnotpass.mechanism.Tuple;
import vn.edu.rmit.youshallnotpass.universe.GalaxyManager;

/**
 * Created by benjamin on 12/23/13.
 */
public class Striker extends MainShip {
    private static final float SHIP_SPEED = 8.0f;
    private static final float SHIP_HIGH_SPEED = 10.0f;
    private static final int BULLET_SPEED = 10;
    private static final int BULLET_FREQUENCY = 2;
    private static final float POWER = 1;
    private static final float HEALTH = 12;
    private static final float HEALING_RATE = 0.02f;
    private static final int MAX_TURBO = 1200;

    private TextureRegion turboFrame;
    private Tuple offPosTurbo;
    private int counter = 0;        // Need save
    private int turbo = 0;
    private boolean full = false;

    public Striker() {
        super(SpriteManager.SHIP1, SpriteManager.BULLET1);
        turboFrame = sprites.getMiscellaneousFrame(SpriteManager.HEAT);
        offPosTurbo = new Tuple(sprites.getOffSetWidth(SpriteManager.HEAT, SpriteManager.NORMAL),
                sprites.getOffSetHeight(SpriteManager.HEAT, SpriteManager.NORMAL));
        health  = HEALTH;
        maxHealth = HEALTH;
        speed = SHIP_SPEED + 5;
        highSpeed = SHIP_HIGH_SPEED;
        bulletPower = POWER;
        healingRate = HEALING_RATE;
        bulletSpeed = BULLET_SPEED;
    }

    @Override
    protected void shooting(SpriteBatch batch) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            int frequency = BULLET_FREQUENCY;
            if (turbo >= MAX_TURBO) {
                full = true;
            }
            if (full) {
                frequency = 1;
                bulletSpeed = 20;
                speed = SHIP_SPEED;
                turbo -= 1;
            } else {
                bulletSpeed = BULLET_SPEED;
                speed = SHIP_HIGH_SPEED;
            }
            if (turbo <= 0) {
                full = false;
            }
            if (counter == frequency * 2) {
                particles.insert(indexBullet, sprites.getBulletFrame(SpriteManager.BULLET1));
                parTups.insert(indexBullet, new Tuple(pos.x - 15, pos.y + offPos.y));
                indexBullet++;
                GalaxyManager.playSound(sprites.getBulletSound(), 0.2f);
                if (!full) {
                    turbo += 2;
                }
            }
            if (counter == frequency * 4) {
                particles.insert(indexBullet, sprites.getBulletFrame(SpriteManager.BULLET1));
                parTups.insert(indexBullet, new Tuple(pos.x + 15, pos.y + offPos.y));
                indexBullet++;
                GalaxyManager.playSound(sprites.getBulletSound(), 0.2f);
                if (!full) {
                    turbo += 2;
                }
            }
            if (counter == frequency * 6 - 4) {
                particles.insert(indexBullet, sprites.getBulletFrame(SpriteManager.BULLET1));
                parTups.insert(indexBullet, new Tuple(pos.x - 40, pos.y + offPos.y / 2));
                indexBullet++;
                GalaxyManager.playSound(sprites.getBulletSound(), 0.2f);
                if (!full) {
                    turbo += 2;
                }
            }
            if (counter == frequency * 8 - 4) {
                particles.insert(indexBullet, sprites.getBulletFrame(SpriteManager.BULLET1));
                parTups.insert(indexBullet, new Tuple(pos.x + 40, pos.y + offPos.y / 2));
                indexBullet++;
                GalaxyManager.playSound(sprites.getBulletSound(), 0.2f);
                if (!full) {
                    turbo += 2;
                }
            }
            counter++;
            if (counter > frequency * 8 - 4) {
                counter = 0;
            }
        }
        for (int i = 0; i < particles.size; i++) {
            parTups.set(i, new Tuple(parTups.get(i).x, parTups.get(i).y + bulletSpeed));
            batch.draw(particles.get(i), parTups.get(i).x - offPar.x, parTups.get(i).y - offPar.y);
            if (full) {
                batch.draw(particles.get(i), parTups.get(i).x - offPar.x, parTups.get(i).y - offPar.y);
            }
            if (parTups.get(i).y > SpriteManager.SCREEN_H) {
                particles.removeIndex(i);
                parTups.removeIndex(i);
                indexBullet--;
            }
        }
    }

    @Override
    protected void miscellaneousStatus(SpriteBatch batch) {
        float posX = SpriteManager.SCREEN_W - offPosTurbo.x * 2;
        float posY = (SpriteManager.SCREEN_H - (offPosTurbo.y + 10) * MAX_TURBO / 100) / 2;
        for(int i = 0; i < MAX_TURBO / 100; i++) {
            batch.draw(turboFrame, posX, posY);
            if (i < (turbo + 50) / 100) {
                batch.draw(turboFrame, posX, posY);
                batch.draw(turboFrame, posX, posY);
            }
            posY += offPosTurbo.y + 10;
        }
    }

    @Override
    public void removeBullet(int index) {
        particles.removeIndex(index);
        parTups.removeIndex(index);
        this.indexBullet--;
    }

    @Override
    public void save() {
        super.save();
        Preferences preferences = sprites.getPreferences();
        preferences.putInteger("ShipCounter", counter);
        preferences.putInteger("ShipIndexBullet", indexBullet);
        preferences.putInteger("ShipTurbo", turbo);
        preferences.putBoolean("ShipTurboFull", full);
        preferences.putString("ShipClass", "Striker");
        preferences.flush();
    }

    @Override
    public void load() {
        super.load();
        Preferences preferences = sprites.getPreferences();
        counter = preferences.getInteger("ShipCounter");
        indexBullet = preferences.getInteger("ShipIndexBullet");
        turbo = preferences.getInteger("ShipTurbo", turbo);
        full = preferences.getBoolean("ShipTurboFull", full);
        int size = preferences.getInteger("ShipBulletSize");
        for (int i = 0; i < size; i++) {
            particles.insert(i, sprites.getBulletFrame(SpriteManager.BULLET1));
        }
    }
}
