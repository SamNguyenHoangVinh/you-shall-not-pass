package vn.edu.rmit.youshallnotpass.universe.lightside;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;
import vn.edu.rmit.youshallnotpass.mechanism.Tuple;
import vn.edu.rmit.youshallnotpass.universe.GalaxyManager;

/**
 * Created by benjamin on 12/23/13.
 */
public class Destroyer extends MainShip {
    private static final float SHIP_SPEED = 7.0f;
    private static final float SHIP_HIGH_SPEED = 9.0f;
    private static final int BULLET_SPEED = 50;
    private static final int BULLET_FREQUENCY = 1;
    private static final float POWER = 0.5f;
    private static final int HEALTH = 10;
    private static final float MIDDLE = 0;
    private static final float LEFT = -16;
    private static final float RIGHT = 24;
    private static final int DELAY = 100;
    private static final int SUB_DELAY = 150;
    private static final int MAX_HEAT = 1200 + DELAY;
    private static final float HEALING_RATE = 0.02f;

    private TextureRegion heatFrame;
    private Tuple offPosHeat;
    private Array<Float> offset;
    private int counter;
    private int counterLeftRight;
    private int delay;
    private int heat;
    private boolean overheated = false;

    public Destroyer() {
        super(SpriteManager.SHIP3, SpriteManager.BULLET3);
        heatFrame = sprites.getMiscellaneousFrame(SpriteManager.HEAT);
        offPosHeat = new Tuple(sprites.getOffSetWidth(SpriteManager.HEAT, SpriteManager.NORMAL),
                sprites.getOffSetHeight(SpriteManager.HEAT, SpriteManager.NORMAL));
        offset = new Array<Float>();
        health  = HEALTH;
        maxHealth = HEALTH;
        speed = SHIP_SPEED;
        highSpeed = SHIP_HIGH_SPEED;
        counter = 0;
        counterLeftRight = 0;
        delay = 0;
        heat = 0;
        bulletPower = POWER;
        healingRate = HEALING_RATE;
        bulletSpeed = BULLET_SPEED;
    }

    @Override
    protected void shooting(SpriteBatch batch) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            int frequency = BULLET_FREQUENCY;
            if (delay < DELAY && !overheated) {
                GalaxyManager.playSound(sprites.getRayFocusSound());
            } else {
                GalaxyManager.stopSound(sprites.getRayFocusSound());
            }
            if (delay > DELAY) {
                if (counter % frequency == 0) {
                    particles.insert(indexBullet, sprites.getBulletFrame(SpriteManager.BULLET3));
                    offset.insert(indexBullet, MIDDLE);
                    parTups.insert(indexBullet, new Tuple(0, pos.y + offPos.y - 20));
                    indexBullet++;
                    GalaxyManager.playSound(sprites.getRaySound(), 0.2f);
                }
                counter++;
                heat += 2;
            }
            if (delay > SUB_DELAY) {
                if (counterLeftRight % frequency == 0) {
                    particles.insert(indexBullet, sprites.getBulletFrame(SpriteManager.BULLET4));
                    offset.insert(indexBullet, LEFT);
                    parTups.insert(indexBullet, new Tuple(0, pos.y + offPos.y + 15));
                    indexBullet++;
                    GalaxyManager.playSound(sprites.getRaySound(), 0.2f);
                    particles.insert(indexBullet, sprites.getBulletFrame(SpriteManager.BULLET4));
                    offset.insert(indexBullet, RIGHT);
                    parTups.insert(indexBullet, new Tuple(0, pos.y + offPos.y + 15));
                    indexBullet++;
                    GalaxyManager.playSound(sprites.getRaySound(), 0.2f);
                }
                counterLeftRight++;
            }
        } else {
            delay = 0;
            if (heat != 0) {
                heat--;
            }
            GalaxyManager.stopSound(sprites.getRaySound());
        }
        if (heat >= MAX_HEAT) {
            overheated = true;
            GalaxyManager.stopSound(sprites.getRaySound());
        } else {
            delay++;
        }
        if (overheated) {
            speed = SHIP_HIGH_SPEED;
            delay = 0;
            heat -= 4;
            if (heat <= 0) {
                overheated = false;
            }
        } else {
            speed = SHIP_SPEED;
        }
        for (int i = 0; i < particles.size; i++) {
            parTups.set(i, new Tuple(pos.x + offset.get(i), parTups.get(i).y + bulletSpeed));
            batch.draw(particles.get(i), parTups.get(i).x - offPar.x, parTups.get(i).y - offPar.y / 4);
            if (parTups.get(i).y > SpriteManager.SCREEN_H) {
                particles.removeIndex(i);
                offset.removeIndex(i);
                parTups.removeIndex(i);
                indexBullet--;
            }
        }
    }

    @Override
    public void miscellaneousStatus(SpriteBatch batch) {
        float posX = SpriteManager.SCREEN_W - offPosHeat.x * 2;
        float posY = (SpriteManager.SCREEN_H - (offPosHeat.y + 10) * MAX_HEAT / 100) / 2;
        for(int i = 0; i < MAX_HEAT / 100; i++) {
            batch.draw(heatFrame, posX, posY);
            if (i < (heat + 50) / 100) {
                batch.draw(heatFrame, posX, posY);
                batch.draw(heatFrame, posX, posY);
            }
            posY += offPosHeat.y + 10;
        }
    }

    @Override
    public void removeBullet(int index) {
        particles.removeIndex(index);
        offset.removeIndex(index);
        parTups.removeIndex(index);
        this.indexBullet--;
    }

    public float getParX(int index) {
        return pos.x + offset.get(index);
    }

    @Override
    public void save() {
        super.save();
        Preferences preferences = sprites.getPreferences();
        preferences.putInteger("ShipCounter", counter);
        preferences.putInteger("ShipCounterLeftRight", counterLeftRight);
        preferences.putInteger("ShipIndexBullet", indexBullet);
        preferences.putInteger("ShipDelay", delay);
        preferences.putInteger("ShipHeat", heat);
        preferences.putBoolean("ShipOverheated", overheated);
        for (int i = 0; i < offset.size; i++) {
            preferences.putFloat("ShipLaserOffset" + i, offset.get(i));
        }
        preferences.putInteger("ShipLaserOffsetSize", offset.size);
        preferences.putString("ShipClass", "Destroyer");
        preferences.flush();
    }

    @Override
    public void load() {
        super.load();
        Preferences preferences = sprites.getPreferences();
        counter = preferences.getInteger("ShipCounter");
        counterLeftRight = preferences.getInteger("ShipCounterLeftRight");
        indexBullet = preferences.getInteger("ShipIndexBullet");
        delay = preferences.getInteger("ShipDelay");
        heat = preferences.getInteger("ShipHeat");
        overheated = preferences.getBoolean("ShipOverheated");
        int size = preferences.getInteger("ShipLaserOffsetSize");
        for (int i = 0; i < size; i++) {
            float element = preferences.getFloat("ShipLaserOffset" + i);
            offset.insert(i, element);
            if (element == MIDDLE) {
                particles.insert(i, sprites.getBulletFrame(SpriteManager.BULLET3));
            } else {
                particles.insert(i, sprites.getBulletFrame(SpriteManager.BULLET4));
            }
        }
    }
}
