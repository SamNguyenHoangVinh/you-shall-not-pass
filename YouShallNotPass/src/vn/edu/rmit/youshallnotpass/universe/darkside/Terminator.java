package vn.edu.rmit.youshallnotpass.universe.darkside;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;
import vn.edu.rmit.youshallnotpass.mechanism.Tuple;
import vn.edu.rmit.youshallnotpass.universe.GalaxyManager;

/**
 * Created by benjamin on 12/26/13.
 */
public class Terminator extends Enemy {
//    private static final int BULLET_SPEED = 2;
//    private static final int BULLET_FREQUENCY = 100;
//    private static final int H_SPEED = 5;
//    private static final int V_SPEED = 5;
//    private static final int H_INTERVAL = 2;
//    private static final int V_INTERVAL = 10;
//    private static final int HEALTH = 10;
//    private static final float POWER = 1;
//    private static final int SCORE = 30;

    private int counter = 0;
    private int type;
    private int focus = 0;
    private int delay;
    private int reset;

    public Terminator(int type, int level) {
        super();
        int model = randomizeProduction();
        production(model, type);
        aggressiveLevel(level, type);
        this.type = type;
    }

    private void production(int model, int type) {
        super.type = type;
        switch (type) {
            case SpriteManager.TYPEI:
                switch (model) {
                    case SpriteManager.ENEMY1: construct(SpriteManager.ENEMY1, SpriteManager.EXPLO1, SpriteManager.ENE_BULL_1);
                        break;
                    case SpriteManager.ENEMY2: construct(SpriteManager.ENEMY2, SpriteManager.EXPLO2, SpriteManager.ENE_BULL_2);
                        break;
                    case SpriteManager.ENEMY3: construct(SpriteManager.ENEMY3, SpriteManager.EXPLO3, SpriteManager.ENE_BULL_3);
                        break;
                    case SpriteManager.ENEMY4: construct(SpriteManager.ENEMY4, SpriteManager.EXPLO4, SpriteManager.ENE_BULL_4);
                        break;
                }
                break;
            case SpriteManager.TYPEII:
                switch (model) {
                    case SpriteManager.ENEMY1: construct(SpriteManager.ENEMY1, SpriteManager.EXPLO1, SpriteManager.ENE_LASER_1);
                        break;
                    case SpriteManager.ENEMY2: construct(SpriteManager.ENEMY2, SpriteManager.EXPLO2, SpriteManager.ENE_LASER_2);
                        break;
                    case SpriteManager.ENEMY3: construct(SpriteManager.ENEMY3, SpriteManager.EXPLO3, SpriteManager.ENE_LASER_3);
                        break;
                    case SpriteManager.ENEMY4: construct(SpriteManager.ENEMY4, SpriteManager.EXPLO4, SpriteManager.ENE_LASER_4);
                        break;
                }
                break;
            case SpriteManager.TYPEIII:
                switch (model) {
                    case SpriteManager.ENEMY1: construct(SpriteManager.ENEMY1, SpriteManager.EXPLO1, SpriteManager.ENE_RAY_1);
                        break;
                    case SpriteManager.ENEMY2: construct(SpriteManager.ENEMY2, SpriteManager.EXPLO2, SpriteManager.ENE_RAY_2);
                        break;
                    case SpriteManager.ENEMY3: construct(SpriteManager.ENEMY3, SpriteManager.EXPLO3, SpriteManager.ENE_RAY_3);
                        break;
                    case SpriteManager.ENEMY4: construct(SpriteManager.ENEMY4, SpriteManager.EXPLO4, SpriteManager.ENE_RAY_4);
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
                        bulletFrequency = 100;
                        hSpeed = 7;
                        vSpeed = 5;
                        hInterval = 2;
                        vInterval = 10;
                        health = 10;
                        power = 1;
                        score = 30;
                        break;
                    case SpriteManager.INSANE_LEVEL:
                        bulletSpeed = 10;
                        bulletFrequency = 70;
                        hSpeed = 10;
                        vSpeed = 7;
                        hInterval = 2;
                        vInterval = 10;
                        health = 12;
                        power = 1.2f;
                        score = 40;
                        break;
                }
                break;
            case SpriteManager.TYPEII:
                switch (level) {
                    case SpriteManager.NORMAL_LEVEL:
                        bulletSpeed = 10;
                        bulletFrequency = 100;
                        hSpeed = 7;
                        vSpeed = 5;
                        hInterval = 2;
                        vInterval = 10;
                        health = 10;
                        power = 2;
                        score = 40;
                        break;
                    case SpriteManager.INSANE_LEVEL:
                        bulletSpeed = 20;
                        bulletFrequency = 70;
                        hSpeed = 10;
                        vSpeed = 7;
                        hInterval = 2;
                        vInterval = 10;
                        health = 12;
                        power = 2.4f;
                        score = 50;
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
                break;
        }
    }

    @Override
    protected void shoot(SpriteBatch batch) {
        if (type == SpriteManager.TYPEIII) {
//            if (focus < delay) {
//                sprites.getRayFocusSound().play();
//            } else {
//                sprites.getRayFocusSound().stop();
//            }
            if (focus > delay) {
                if (counter % bulletFrequency == 0) {
                    particles.insert(index, sprites.getBulletFrame(bullet));
                    parTups.insert(index, new Tuple(0, pos.y));
                    index++;
                    GalaxyManager.playSound(sprites.getRaySound(), 0.1f);
                }
                counter++;
            }
            if (focus > reset) {
                focus = 0;
//                sprites.getRaySound().stop();
            }
            focus++;
            for (int i = 0; i < particles.size; i++) {
                parTups.set(i, new Tuple(pos.x, parTups.get(i).y - bulletSpeed));
                batch.draw(particles.get(i), pos.x - offPar.x, parTups.get(i).y - offPar.y * 2);
                if (parTups.get(i).y < 0) {
                    particles.removeIndex(i);
                    parTups.removeIndex(i);
                    index--;
                }
            }
        } else {
            counter++;
            if (counter % bulletFrequency == 0) {
                particles.insert(index, sprites.getBulletFrame(bullet));
                parTups.insert(index, pos);
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

    @Override
    public int randomizeProduction() {
        return (SpriteManager.ENEMY1 - 1) + (int)(Math.random() * (SpriteManager.ENEMY4 - (SpriteManager.ENEMY1 - 1)) + 1);
    }

    @Override
    public void save(int id) {
        super.save(id);
        Preferences preferences = sprites.getPreferences();
        preferences.putInteger(id + "EneCounter", counter);
        preferences.putInteger(id + "EneFocus", focus);
        preferences.putInteger(id + "EneType", type);
        preferences.putString(id + "EneClass", "Terminator");
        preferences.flush();
    }

    @Override
    public void load(int id) {
        super.load(id);
        Preferences preferences = sprites.getPreferences();
        counter = preferences.getInteger(id + "EneCounter");
        focus = preferences.getInteger(id + "EneFocus");
    }
}
