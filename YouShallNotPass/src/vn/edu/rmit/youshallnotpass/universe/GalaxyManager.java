package vn.edu.rmit.youshallnotpass.universe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import vn.edu.rmit.youshallnotpass.Initiator;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;
import vn.edu.rmit.youshallnotpass.mechanism.Tuple;
import vn.edu.rmit.youshallnotpass.universe.darkside.Executor;
import vn.edu.rmit.youshallnotpass.universe.darkside.Eliminator;
import vn.edu.rmit.youshallnotpass.universe.darkside.Enemy;
import vn.edu.rmit.youshallnotpass.universe.darkside.Terminator;
import vn.edu.rmit.youshallnotpass.universe.lightside.Destroyer;
import vn.edu.rmit.youshallnotpass.universe.lightside.Hunter;
import vn.edu.rmit.youshallnotpass.universe.lightside.MainShip;
import vn.edu.rmit.youshallnotpass.universe.lightside.Striker;

public class GalaxyManager {
	private static final int SPAWN_MAX = 100;
	private static final int SPAWN_RATE = 80;

    private static final int BULLET = 1;
    private static final int LASER = 2;
    private static final int RAY = 3;

    private static final int TERMINATOR = 1;
    private static final int ELIMINATOR = 2;

    private Initiator initiator;

    private SpriteManager sprites;

    private int index = 0;
	private Array<Enemy> enemies;   // save this
	private int counter = 0;        // save this
	
	private MainShip ship;          // save this

    private Executor executor;

    private int number = 0;         // save
    private Label scoreLabel;
    private int score = 0;          // and this

    private int level;              // save this

    private boolean wave1Completed = false; // if true save
    private boolean wave2Completed = false;
    private boolean wave3Completed = false;
    private boolean wave4Completed = false;
    private boolean wave5Completed = false;
    private boolean wave6Completed = false;
    private boolean releaseTheExecutor = false;

    private int spawnMaxScene;
    private int spawnMax;
    private int spawnRate;

    public static boolean soundEffects = true;  // save
    private boolean music = true;               // save
    private Music background;

    private float fade = 1f;
    private int fadeCounter = 0;
    private int duration = 0;
	
	public GalaxyManager(Initiator initiator, int level) {
        this.initiator = initiator;
        this.level = level;
        sprites = SpriteManager.getInstance();
        background = sprites.getBackgroundMusic();
		enemies = new Array<Enemy>();
		ship = new Striker();
        executor = new Executor(level);

        background.play();
        background.setLooping(true);

        Label.LabelStyle labelStyle = new Label.LabelStyle(sprites.getFontScore(), Color.WHITE);
        scoreLabel = new Label(String.valueOf(score), labelStyle);

        if (level == SpriteManager.NORMAL_LEVEL) {
            spawnMaxScene = 13;
            spawnMax = SPAWN_MAX / 2;
            spawnRate = SPAWN_RATE;
        } else if (level == SpriteManager.INSANE_LEVEL) {
            spawnMaxScene = 20;
            spawnMax = SPAWN_MAX;
            spawnRate = SPAWN_RATE / 2;
        }
	}

    private void normalWave1() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Terminator(SpriteManager.TYPEI, SpriteManager.NORMAL_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave1Completed = true;
                duration = 0;
            }
            number++;
        }
    }

    private void normalWave2() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Terminator(SpriteManager.TYPEII, SpriteManager.NORMAL_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave2Completed = true;
                duration = 0;
            }
            number++;
        }
    }

    private void normalWave3() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Terminator(SpriteManager.TYPEIII, SpriteManager.NORMAL_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave3Completed = true;
                duration = 0;
            }
            number++;
        }
    }

    private void normalWave4() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Eliminator(SpriteManager.TYPEI, SpriteManager.NORMAL_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave4Completed = true;
                duration = 0;
            }
            number++;
        }
    }

    private void normalWave5() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Eliminator(SpriteManager.TYPEII, SpriteManager.NORMAL_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave5Completed = true;
                duration = 0;
            }
            number++;
        }
    }

    private void normalWave6() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Eliminator(SpriteManager.TYPEIII, SpriteManager.NORMAL_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave6Completed = true;
                releaseTheExecutor = true;
                duration = 0;
            }
            number++;
        }
    }

    private void normalWave7() {
        spawnMaxScene = 17;
        spawnRate = 40;
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            int type = (SpriteManager.TYPEI - 1) + (int)(Math.random() * (SpriteManager.TYPEIII - (SpriteManager.TYPEI - 1)) + 1);
            int model = (TERMINATOR - 1) + (int)(Math.random() * (ELIMINATOR - (TERMINATOR - 1)) + 1);
            if (model == TERMINATOR) {
                enemies.insert(index, new Terminator(type, SpriteManager.NORMAL_LEVEL));
            } else if (model == ELIMINATOR) {
                enemies.insert(index, new Eliminator(type, SpriteManager.NORMAL_LEVEL));
            }
            index++;
        }
    }

    private void insaneWave1() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Terminator(SpriteManager.TYPEI, SpriteManager.INSANE_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave1Completed = true;
                duration = 0;
            }
            number++;
        }
    }

    private void insaneWave2() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Terminator(SpriteManager.TYPEII, SpriteManager.INSANE_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave2Completed = true;
                duration = 0;
            }
            number++;
        }
    }

    private void insaneWave3() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Terminator(SpriteManager.TYPEIII, SpriteManager.INSANE_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave3Completed = true;
                duration = 0;
            }
            number++;
        }
    }

    private void insaneWave4() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Eliminator(SpriteManager.TYPEI, SpriteManager.INSANE_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave4Completed = true;
                duration = 0;
            }
            number++;
        }
    }

    private void insaneWave5() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Eliminator(SpriteManager.TYPEII, SpriteManager.INSANE_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave5Completed = true;
                duration = 0;
            }
            number++;
        }
    }

    private void insaneWave6() {
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            if (number < spawnMax) {
                enemies.insert(index, new Eliminator(SpriteManager.TYPEIII, SpriteManager.INSANE_LEVEL));
                index++;
            } else if (enemies.size == 0) {
                number = 0;
                wave6Completed = true;
                releaseTheExecutor = true;
                duration = 0;
            }
            number++;
        }
    }

    private void insaneWave7() {
        spawnMaxScene = 25;
        spawnRate = 20;
        counter++;
        if (counter % spawnRate == 0 && enemies.size < spawnMaxScene) {
            int type = (SpriteManager.TYPEI - 1) + (int)(Math.random() * (SpriteManager.TYPEIII - (SpriteManager.TYPEI - 1)) + 1);
            int model = (TERMINATOR - 1) + (int)(Math.random() * (ELIMINATOR - (TERMINATOR - 1)) + 1);
            if (model == TERMINATOR) {
                enemies.insert(index, new Terminator(type, SpriteManager.INSANE_LEVEL));
            } else if (model == ELIMINATOR) {
                enemies.insert(index, new Eliminator(type, SpriteManager.INSANE_LEVEL));
            }
            index++;
        }
    }
	
	public void render(SpriteBatch batch) {
        boolean isHit = false;
        float damage = 0;

        if (!releaseTheExecutor) {
            if (level == SpriteManager.NORMAL_LEVEL) {
                if (!wave1Completed) {
                    waveStatus(batch, SpriteManager.WAVEI);
                    normalWave1();
                } else if (!wave2Completed) {
                    waveStatus(batch, SpriteManager.WAVEII);
                    normalWave2();
                } else if (!wave3Completed) {
                    waveStatus(batch, SpriteManager.WAVEIII);
                    normalWave3();
                } else if (!wave4Completed) {
                    waveStatus(batch, SpriteManager.WAVEIV);
                    normalWave4();
                } else if (!wave5Completed) {
                    waveStatus(batch, SpriteManager.WAVEV);
                    normalWave5();
                } else if (!wave6Completed) {
                    waveStatus(batch, SpriteManager.WAVEVI);
                    normalWave6();
                }
            } else if (level == SpriteManager.INSANE_LEVEL) {
                if (!wave1Completed) {
                    waveStatus(batch, SpriteManager.WAVEI);
                    insaneWave1();
                } else if (!wave2Completed) {
                    waveStatus(batch, SpriteManager.WAVEII);
                    insaneWave2();
                } else if (!wave3Completed) {
                    waveStatus(batch, SpriteManager.WAVEIII);
                    insaneWave3();
                } else if (!wave4Completed) {
                    waveStatus(batch, SpriteManager.WAVEIV);
                    insaneWave4();
                } else if (!wave5Completed) {
                    waveStatus(batch, SpriteManager.WAVEV);
                    insaneWave5();
                } else if (!wave6Completed) {
                    waveStatus(batch, SpriteManager.WAVEVI);
                    insaneWave6();
                }
            }

            for (int i = 0; i < enemies.size; i++) {
                Enemy e = enemies.get(i);
                if (e != null) {
                    if (e.isToBeRemoved()) {
                        enemies.removeIndex(i);
                        index--;
                        i--;
                    } else {
                        if (e.isDead()) {
                            score += e.getScore();
                            scoreLabel.setText(String.valueOf(score));
                        }
                        if (isHit(e, ship.getPos(), ship.getOffPos(), e.getParTups(), e.getOffPar())) {
                            damage = e.getBulletPower();
                            isHit = true;
                        }
                        if (e.isDestroyed()) {
                            e.render(batch, false, 0);
                        } else {
                            boolean getHit = isHit(ship, e.getPos(), e.getOffPos(), ship.getParTups(), ship.getOffPar());
                            e.render(batch, getHit, ship.getBulletPower());
                            if (e.isOffScreen()) {
                                enemies.removeIndex(0);
                                index--;
                            }
                        }
                    }
                }
            }
        } else {
            if (executor.isUnleashExecution()) {
                duration = 0;
            }
            if (executor.unleashExecution()) {
                waveStatus(batch, SpriteManager.YOU_WILL_BE_EXECUTED);
                if (!executor.signalSelfDestruct()) {
                    if (level == SpriteManager.NORMAL_LEVEL) {
                        normalWave7();
                    } else if (level == SpriteManager.INSANE_LEVEL) {
                        insaneWave7();
                    }
                }
                for (int i = 0; i < enemies.size; i++) {
                    Enemy e = enemies.get(i);
                    if (executor.signalSelfDestruct()) {
                        e.selfDestruct();
                    }
                    if (e != null) {
                        if (e.isToBeRemoved()) {
                            enemies.removeIndex(i);
                            index--;
                            i--;
                        } else {
                            if (e.isDead()) {
                                score += e.getScore();
                                scoreLabel.setText(String.valueOf(score));
                            }
                            if (isHit(e, ship.getPos(), ship.getOffPos(), e.getParTups(), e.getOffPar())) {
                                damage = e.getBulletPower();
                                isHit = true;
                            }
                            if (e.isDestroyed()) {
                                e.render(batch, false, 0);
                            } else {
                                boolean getHit = isHit(ship, e.getPos(), e.getOffPos(), ship.getParTups(), ship.getOffPar());
                                e.render(batch, getHit, ship.getBulletPower());
                                if (e.isOffScreen()) {
                                    enemies.removeIndex(0);
                                    index--;
                                }
                            }
                        }
                    }
                }
            } else {
                waveStatus(batch, SpriteManager.RELEASE_THE_KRAKEN);
            }
            if (executor.isDead()) {
                score += executor.getScore();
                scoreLabel.setText(String.valueOf(score));
            }
            int outcome = isHitByTheExecutor(executor, ship.getPos(), ship.getOffPos(),
                    executor.getParTupBullets(),
                    executor.getParTupLasers(),
                    executor.getParTupRays());
            if (outcome != 0) {
                if (outcome == BULLET) {
                    damage = executor.getBulletPower();
                } else if (outcome == LASER) {
                    damage = executor.getLaserPower();
                } else if (outcome == RAY) {
                    damage = executor.getRayPower();
                }
                isHit = true;
            }
            if (executor.isDestroyed()) {
                gameOver(batch, true);
            } else {
                boolean getHit = isHit(ship, executor.getPos(), executor.getOffPos(), ship.getParTups(), ship.getOffPar());
                executor.render(batch, getHit, ship.getBulletPower());
            }
        }

        if (ship.isDestroyed()) {
            gameOver(batch, false);
        } else {
            ship.render(batch, isHit, damage);
        }

        displayScore(batch);
	}

    private void gameOver(SpriteBatch batch, boolean isWon) {
        int title;
        if (isWon) {
            title = SpriteManager.VICTORY_TITLE;
        } else {
            title = SpriteManager.DEFEAT_TITLE;
        }
        batch.setColor(1.0f, 1.0f, 1.0f, fade);
        batch.draw(sprites.getMessage(title),
                SpriteManager.SCREEN_W / 2 - sprites.getOffSetWidth(title, 0),
                SpriteManager.SCREEN_H / 2 - sprites.getOffSetHeight(title, 0) + 50);
        if (fadeCounter % 5 == 0) {
            fade -= 0.02f;
            if (fade <= 0) {
                initiator.proceedEnding(isWon, score);
            }
        }
        fadeCounter++;
    }

    private void waveStatus(SpriteBatch batch, int wave) {
        if (duration < 300) {
            batch.draw(sprites.getMessage(wave),
                    SpriteManager.SCREEN_W / 2 - sprites.getOffSetWidth(wave, 0),
                    SpriteManager.SCREEN_H / 2 - sprites.getOffSetHeight(wave, 0) + 50);
            duration++;
        }
    }

    private void displayScore(SpriteBatch batch) {
        scoreLabel.setPosition(20, SpriteManager.SCREEN_H - 60);
        scoreLabel.draw(batch, 1.0f);
    }

    public void setLevel(int level) {
        this.level = level;
        executor.setLevel(level);
    }

    public void setShip(MainShip ship) {
        this.ship = ship;
    }

    public int isHitByTheExecutor(Executor executor, Tuple pos, Tuple offPos,
                                  Array<Tuple> parTupBullet,
                                  Array<Tuple> parTupLaser,
                                  Array<Tuple> parTupRay) {
        Tuple firstPos, lastPos;
        firstPos = getFirstPos(pos, new Tuple(offPos.x / 2, offPos.y / 2));
        lastPos = getLastPos(pos, new Tuple(offPos.x / 2, offPos.y / 2));
        for (int i = 0; i < parTupBullet.size; i++) {
            Tuple par = parTupBullet.get(i);
            if (par.x >= firstPos.x && par.x <= lastPos.x &&
                    par.y >= firstPos.y && par.y <= lastPos.y) {
                executor.removeBullet(i);
                return BULLET;
            }
        }
        for (int i = 0; i < parTupLaser.size; i++) {
            Tuple par = parTupLaser.get(i);
            if (par.x >= firstPos.x && par.x <= lastPos.x &&
                    par.y >= firstPos.y && par.y <= lastPos.y) {
                executor.removeLaser(i);
                return LASER;
            }
        }
        for (int i = 0; i < parTupRay.size; i++) {
            Tuple par = parTupRay.get(i);
            par.x = executor.getParX(i);
            if (par.x >= firstPos.x && par.x <= lastPos.x &&
                    par.y >= firstPos.y && par.y <= lastPos.y) {
                executor.removeRay(i);
                return RAY;
            }
        }
        return 0;
    }

    public boolean isHit(UFO object, Tuple pos, Tuple offPos, Array<Tuple> parTups, Tuple offPar) {
        Tuple firstPos, lastPos;
        if (object instanceof MainShip) {
            firstPos = getFirstPos(pos, new Tuple(offPos.x * 3 / 4, offPos.y * 3 / 4));
            lastPos = getLastPos(pos, new Tuple(offPos.x * 3 / 4, offPos.y * 3 / 4));
        } else {
            firstPos = getFirstPos(pos, new Tuple(offPos.x / 2, offPos.y / 2));
            lastPos = getLastPos(pos, new Tuple(offPos.x / 2, offPos.y / 2));
        }
        for (int i = 0; i < parTups.size; i++) {
            Tuple par = parTups.get(i);
            if (object instanceof Destroyer) {
                par.x = ((Destroyer)object).getParX(i);
            } else if (object instanceof Enemy && ((Enemy)object).getType() == SpriteManager.TYPEIII) {
                par.x = ((Enemy)object).getParX(i);
            }
//			Tuple firstPar = getFirstPos(par, offParBullet);
//			Tuple lastPar = getLastPos(par, offParBullet);
            if (par.x >= firstPos.x && par.x <= lastPos.x &&
                    par.y >= firstPos.y && par.y <= lastPos.y) {
                object.removeBullet(i);
                return true;
            }
        }
        return false;
    }

    private Tuple getFirstPos(Tuple pos, Tuple offPos) {
        return new Tuple(pos.x - offPos.x, pos.y - offPos.y);
    }

    private Tuple getLastPos(Tuple pos, Tuple offPos) {
        return new Tuple(pos.x + offPos.x, pos.y + offPos.y);
    }

    public static void playSound(Sound sound) {
        if (soundEffects) {
            sound.play();
        }
    }

    public static void playSound(Sound sound, float volume) {
        if (soundEffects) {
            sound.play(volume);
        }
    }

    public static void stopSound(Sound sound) {
        sound.stop();
    }

    public int getLevel() {
        return level;
    }

    public void setMusic(boolean music) {
        this.music = music;
        if (music) {
            background.play();
        } else {
            background.pause();
        }
    }

    public boolean isMusicOn() {
        return music;
    }

    public void save() {
        Preferences preferences = sprites.getPreferences();
        if (preferences.getBoolean("isSaveExisted")) {
            preferences.clear();
        }
        preferences.putBoolean("isSaveExisted", true);
        preferences.putInteger("GalaxyCounter", counter);
        preferences.putInteger("GalaxyScore", score);
        preferences.putInteger("GalaxyLevel", level);
        preferences.putInteger("GalaxyNumber", number);
        preferences.putBoolean("GalaxyWave1", wave1Completed);
        preferences.putBoolean("GalaxyWave2", wave2Completed);
        preferences.putBoolean("GalaxyWave3", wave3Completed);
        preferences.putBoolean("GalaxyWave4", wave4Completed);
        preferences.putBoolean("GalaxyWave5", wave5Completed);
        preferences.putBoolean("GalaxyWave6", wave6Completed);
        preferences.putBoolean("GalaxyExecutorOn", releaseTheExecutor);
        for (int i = 0; i < enemies.size; i++) {
            if (enemies.get(i) != null) {
                enemies.get(i).save(i);
            }
        }
        preferences.putInteger("GalaxyEnemySize", enemies.size);
        ship.save();
        if (releaseTheExecutor) {
            executor.save();
        }
        preferences.flush();
    }

    public void load() {
        Preferences preferences = sprites.getPreferences();
        counter = preferences.getInteger("GalaxyManager");
        score = preferences.getInteger("GalaxyScore");
        scoreLabel.setText(String.valueOf(score));
        level = preferences.getInteger("GalaxyLevel");
        number = preferences.getInteger("GalaxyNumber");
        wave1Completed = preferences.getBoolean("GalaxyWave1");
        wave2Completed = preferences.getBoolean("GalaxyWave2");
        wave3Completed = preferences.getBoolean("GalaxyWave3");
        wave4Completed = preferences.getBoolean("GalaxyWave4");
        wave5Completed = preferences.getBoolean("GalaxyWave5");
        wave6Completed = preferences.getBoolean("GalaxyWave6");
        releaseTheExecutor = preferences.getBoolean("GalaxyExecutorOn");
        String shipClass = preferences.getString("ShipClass");
        Gdx.app.log("load", shipClass);
        if (shipClass.equals("Striker")) {
            ship = new Striker();
            ship.load();
        } else if (shipClass.equals("Hunter")) {
            ship = new Hunter();
            ship.load();
        } else if (shipClass.equals("Destroyer")) {
            ship = new Destroyer();
            ship.load();
        }
        int size = preferences.getInteger("GalaxyEnemySize");
        index = size;
        for (int i = 0; i < size; i++) {
            String eneClass = preferences.getString(i + "EneClass");
            int type = preferences.getInteger(i + "EneType");
            if (eneClass.equals("Terminator")) {
                enemies.insert(i, new Terminator(type, level));
                enemies.get(i).load(i);
            } else if (eneClass.equals("Eliminator")) {
                enemies.insert(i, new Eliminator(type, level));
                enemies.get(i).load(i);
            } else {
                enemies.insert(i, null);
            }
        }
        if (releaseTheExecutor) {
            executor = new Executor(level);
            executor.load();
        }
    }
}
