package vn.edu.rmit.youshallnotpass;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;
import vn.edu.rmit.youshallnotpass.universe.GalaxyManager;
import vn.edu.rmit.youshallnotpass.universe.Star;
import vn.edu.rmit.youshallnotpass.universe.lightside.Destroyer;
import vn.edu.rmit.youshallnotpass.universe.lightside.Hunter;
import vn.edu.rmit.youshallnotpass.universe.lightside.MainShip;
import vn.edu.rmit.youshallnotpass.universe.lightside.Striker;

public class Initiator implements ApplicationListener {
	private SpriteManager sprites;
	private static SpriteBatch batch;
    private static Menu menu;
	
	private Star star;
	private GalaxyManager galaxy;

    private boolean started = false;
    private boolean paused = false;
	
	@Override
	public void create() {
		sprites = SpriteManager.getInstance();
		batch = new SpriteBatch();
		
		star = new Star();
		galaxy = new GalaxyManager(this, SpriteManager.NORMAL_LEVEL);

        menu = new Menu(this, galaxy);
        menu.create();
	}

	@Override
	public void dispose() {
		batch.dispose();
		sprites.dispose();
        menu.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && started) {
            paused = true;
            pause();
        }

        if (!started || paused) {
            menu.render();
        } else {
            star.render(batch);
    		galaxy.render(batch);
        }

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
        menu.pause();
	}

	@Override
	public void resume() {
	}

    public void setLevel(int level) {
        galaxy.setLevel(level);
    }

    public void setMusic(boolean music) {
        galaxy.setMusic(music);
    }

    public void setSoundEffects(boolean soundEffects) {
        GalaxyManager.soundEffects = soundEffects;
    }

    public void setShip(MainShip ship) {
        galaxy.setShip(ship);
    }

    public int getLevel() {
        return galaxy.getLevel();
    }

    public boolean isMusicOn() {
        return galaxy.isMusicOn();
    }

    public boolean isSoundEffectsOn() {
        return GalaxyManager.soundEffects;
    }

    public void restart() {
        started = false;
        paused = false;
        int level = galaxy.getLevel();
        boolean music = galaxy.isMusicOn();
        boolean soundEffects = GalaxyManager.soundEffects;
        galaxy = null;
        galaxy = new GalaxyManager(this, level);
        galaxy.setMusic(music);
        GalaxyManager.soundEffects = soundEffects;
        menu.setGalaxyManager(galaxy);
    }

    public void proceedEnding(boolean isWon, int score) {
        restart();
        menu.displayScore(isWon, score);
        batch.setColor(1.0f, 1.0f, 1.0f, 1f);
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStarted() {
        return started;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }
}
