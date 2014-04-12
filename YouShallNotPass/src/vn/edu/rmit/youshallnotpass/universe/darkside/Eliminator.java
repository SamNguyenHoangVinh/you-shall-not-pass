package vn.edu.rmit.youshallnotpass.universe.darkside;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;
import vn.edu.rmit.youshallnotpass.mechanism.Tuple;
import vn.edu.rmit.youshallnotpass.universe.GalaxyManager;

/**
 * Created by benjamin on 12/27/13.
 */
public class Eliminator extends Enemy {
//    private static final int BULLET_SPEED = 5;
//    private static final int BULLET_FREQUENCY = 50;
//    private static final int H_SPEED = 5;
//    private static final int V_SPEED = 5;
//    private static final int H_INTERVAL = 2;
//    private static final int V_INTERVAL = 10;
//    private static final int HEALTH = 20;
//    private static final int POWER = 1;
//    private static final int SCORE = 90;

    private int counterLeft = 0;
    private int counterRight = 10;
    private int focus;
    private int delay;
    private int reset;
    private Array<Float> offset;

    public Eliminator(int type, int level) {
        int model = randomizeProduction();
        production(model, type);
        aggressiveLevel(level, type);
    }

    private void production(int model, int type) {
        super.type = type;
        switch (type) {
            case SpriteManager.TYPEI:
                switch (model) {
                    case SpriteManager.ENEMY5: construct(SpriteManager.ENEMY5, SpriteManager.EXPLO1, SpriteManager.ENE_BULL_1);
                        break;
                    case SpriteManager.ENEMY6: construct(SpriteManager.ENEMY6, SpriteManager.EXPLO2, SpriteManager.ENE_BULL_2);
                        break;
                    case SpriteManager.ENEMY7: construct(SpriteManager.ENEMY7, SpriteManager.EXPLO3, SpriteManager.ENE_BULL_3);
                        break;
                    case SpriteManager.ENEMY8: construct(SpriteManager.ENEMY8, SpriteManager.EXPLO4, SpriteManager.ENE_BULL_4);
                        break;
                }
                break;
            case SpriteManager.TYPEII:
                switch (model) {
                    case SpriteManager.ENEMY5: construct(SpriteManager.ENEMY5, SpriteManager.EXPLO1, SpriteManager.ENE_LASER_1);
                        break;
                    case SpriteManager.ENEMY6: construct(SpriteManager.ENEMY6, SpriteManager.EXPLO2, SpriteManager.ENE_LASER_2);
                        break;
                    case SpriteManager.ENEMY7: construct(SpriteManager.ENEMY7, SpriteManager.EXPLO3, SpriteManager.ENE_LASER_3);
                        break;
                    case SpriteManager.ENEMY8: construct(SpriteManager.ENEMY8, SpriteManager.EXPLO4, SpriteManager.ENE_LASER_4);
                        break;
                }
                break;
            case SpriteManager.TYPEIII:
                switch (model) {
                    case SpriteManager.ENEMY5: construct(SpriteManager.ENEMY5, SpriteManager.EXPLO1, SpriteManager.ENE_RAY_1);
                        break;
                    case SpriteManager.ENEMY6: construct(SpriteManager.ENEMY6, SpriteManager.EXPLO2, SpriteManager.ENE_RAY_2);
                        break;
                    case SpriteManager.ENEMY7: construct(SpriteManager.ENEMY7, SpriteManager.EXPLO3, SpriteManager.ENE_RAY_3);
                        break;
                    case SpriteManager.ENEMY8: construct(SpriteManager.ENEMY8, SpriteManager.EXPLO4, SpriteManager.ENE_RAY_4);
                        break;
                }
                break;
        }
    }

    private void aggressiveLevel(int level, int type) {
        switch (type) {
            case SpriteManager.TYPEI:
                switch (level) {
                    case SpriteManager.NORMAL_LEVEL:
                        bulletSpeed = 7;
                        bulletFrequency = 50;
                        hSpeed = 7;
                        vSpeed = 5;
                        hInterval = 2;
                        vInterval = 10;
                        health = 20;
                        power = 1;
                        score = 90;
                        break;
                    case SpriteManager.INSANE_LEVEL:
                        bulletSpeed = 10;
                        bulletFrequency = 40;
                        hSpeed = 10;
                        vSpeed = 7;
                        hInterval = 2;
                        vInterval = 10;
                        health = 20;
                        power = 2;
                        score = 90;
                        break;
                }
                break;
            case SpriteManager.TYPEII:
                switch (level) {
                    case SpriteManager.NORMAL_LEVEL:
                        bulletSpeed = 15;
                        bulletFrequency = 70;
                        hSpeed = 7;
                        vSpeed = 5;
                        hInterval = 2;
                        vInterval = 10;
                        health = 20;
                        power = 2;
                        score = 90;
                        break;
                    case SpriteManager.INSANE_LEVEL:
                        bulletSpeed = 20;
                        bulletFrequency = 50;
                        hSpeed = 10;
                        vSpeed = 7;
                        hInterval = 2;
                        vInterval = 10;
                        health = 20;
                        power = 2;
                        score = 90;
                        break;
                }
                break;
            case SpriteManager.TYPEIII:
                switch (level) {
                    case SpriteManager.NORMAL_LEVEL:
                        bulletSpeed = 50;
                        bulletFrequency = 1;
                        hSpeed = 7;
                        vSpeed = 5;
                        hInterval = 2;
                        vInterval = 10;
                        health = 10;
                        power = 0.3f;
                        score = 50;
                        delay = 150;
                        reset = 200;
                        break;
                    case SpriteManager.INSANE_LEVEL:
                        bulletSpeed = 50;
                        bulletFrequency = 1;
                        hSpeed = 10;
                        vSpeed = 7;
                        hInterval = 2;
                        vInterval = 10;
                        health = 12;
                        power = 0.3f;
                        score = 60;
                        delay = 100;
                        reset = 200;
                        break;
                }
                offset = new Array<Float>();
                break;
        }
    }

    @Override
    protected void shoot(SpriteBatch batch) {
        if (type == SpriteManager.TYPEIII) {
            if (focus > delay) {
                if (counterLeft % bulletFrequency == 0) {
                    particles.insert(index, sprites.getBulletFrame(bullet));
                    offset.insert(index, -10f);
                    parTups.insert(index, new Tuple(0, pos.y));
                    index++;
                    GalaxyManager.playSound(sprites.getRaySound(), 0.1f);
                }
                if (counterRight % bulletFrequency == 0) {
                    particles.insert(index, sprites.getBulletFrame(bullet));
                    offset.insert(index, 10f);
                    parTups.insert(index, new Tuple(0, pos.y));
                    index++;
//                    GalaxyManager.playSound(sprites.getRaySound(), 0.1f);
                }
                counterLeft++;
                counterRight++;
            }
            if (focus > reset) {
                focus = 0;
            }
            focus++;
            for (int i = 0; i < particles.size; i++) {
                parTups.set(i, new Tuple(0, parTups.get(i).y - bulletSpeed));
                batch.draw(particles.get(i), pos.x + offset.get(i) - offPar.x, parTups.get(i).y - offPar.y * 2);
                if (parTups.get(i).y < 0) {
                    particles.removeIndex(i);
                    offset.removeIndex(i);
                    parTups.removeIndex(i);
                    index--;
                }
            }
        } else {
            counterLeft++;
            counterRight++;
            if (counterLeft % bulletFrequency == 0) {
                particles.insert(index, sprites.getBulletFrame(bullet));
                parTups.insert(index, new Tuple(pos.x - 10, pos.y));
                index++;
                if (type == SpriteManager.TYPEI) {
                    GalaxyManager.playSound(sprites.getBulletSound(), 0.2f);
                } else {
                    GalaxyManager.playSound(sprites.getLaserSound(), 0.2f);
                }
            }
            if (counterRight % bulletFrequency == 0) {
                particles.insert(index, sprites.getBulletFrame(bullet));
                parTups.insert(index, new Tuple(pos.x + 10, pos.y));
                index++;
                if (type == SpriteManager.TYPEI) {
                    GalaxyManager.playSound(sprites.getBulletSound(), 0.2f);
                } else {
                    GalaxyManager.playSound(sprites.getLaserSound(), 0.2f);
                }
            }
            for (int i = 0; i < particles.size; i++) {
                parTups.set(i, new Tuple(parTups.get(i).x, parTups.get(i).y - bulletSpeed));
                batch.draw(particles.get(i), parTups.get(i).x - offPar.x, parTups.get(i).y - offPar.y);
                if (parTups.get(i).y < 0) {
                    particles.removeIndex(i);
                    parTups.removeIndex(i);
                    index--;
                }
            }
        }
    }

    public float getParX(int index) {
        return pos.x + offset.get(index);
    }

    @Override
    public void removeBullet(int index) {
        super.removeBullet(index);
        if (type == SpriteManager.TYPEIII) {
            offset.removeIndex(index);
        }
    }

    @Override
    public int randomizeProduction() {
        return SpriteManager.ENEMY4 + (int)(Math.random() * (SpriteManager.ENEMY8 - SpriteManager.ENEMY4) + 1);
    }

    @Override
    public void save(int id) {
        super.save(id);
        Preferences preferences = sprites.getPreferences();
        preferences.putInteger(id + "EneCounterLeft", counterLeft);
        preferences.putInteger(id + "EneCounterRight", counterRight);
        preferences.putInteger(id + "EneType", type);
        if (offset != null) {
            for (int i = 0; i < offset.size; i++) {
                preferences.putFloat(id + "EneOffset" + i, offset.get(i));
            }
            preferences.putInteger(id + "EneOffsetSize", offset.size);
        }
        preferences.putString(id + "EneClass", "Eliminator");
        preferences.flush();
    }

    @Override
    public void load(int id) {
        super.load(id);
        Preferences preferences = sprites.getPreferences();
        counterLeft = preferences.getInteger(id + "EneCounterLeft");
        counterRight = preferences.getInteger(id + "EneCounterRight");
        if (preferences.contains(id + "EneOffsetSize")) {
            int size = preferences.getInteger(id + "EneOffsetSize");
            for (int i = 0; i < size; i++) {
                offset.insert(i, preferences.getFloat(id + "EneOffset" + i));
            }
        }
    }
}
