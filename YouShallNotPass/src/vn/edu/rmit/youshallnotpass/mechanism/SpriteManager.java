package vn.edu.rmit.youshallnotpass.mechanism;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SpriteManager {
	private static SpriteManager instance;
    private Preferences preferences;
	private TextureAtlas spritesheet;
    private TextureAtlas menuSprite;
    private BitmapFont fontScore;
    private Music backgroundMusic;
	
	public static final int SHIP1 = 1;
	public static final int SHIP2 = 2;
	public static final int SHIP3 = 3;
	
	public static final int IDLE = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	
	public static final int IDLE_HIT = 4;
	public static final int LEFT_HIT = 5;
	public static final int RIGHT_HIT = 6;
	
	public static final int ENEMY1 = 4;
	public static final int ENEMY2 = 5;
	public static final int ENEMY3 = 6;
	public static final int ENEMY4 = 7;
	public static final int ENEMY5 = 8;
	public static final int ENEMY6 = 9;
	public static final int ENEMY7 = 10;
	public static final int ENEMY8 = 11;
    public static final int EXECUTOR = 12;
	
	public static final int NORMAL = 1;
	public static final int HIT = 2;
	
	public static final int BULLET1 = 13;
	public static final int BULLET2 = 14;
	public static final int BULLET3 = 15;
	public static final int BULLET4 = 16;
	public static final int ENE_BULL_1 = 17;
	public static final int ENE_BULL_2 = 18;
	public static final int ENE_BULL_3 = 19;
	public static final int ENE_BULL_4 = 20;
    public static final int ENE_LASER_1 = 21;
    public static final int ENE_LASER_2 = 22;
    public static final int ENE_LASER_3 = 23;
    public static final int ENE_LASER_4 = 24;
    public static final int ENE_RAY_1 = 25;
    public static final int ENE_RAY_2 = 26;
    public static final int ENE_RAY_3 = 27;
    public static final int ENE_RAY_4 = 28;
	
	public static final int EXPLO1 = 29;
	public static final int EXPLO2 = 30;
	public static final int EXPLO3 = 31;
	public static final int EXPLO4 = 32;
	public static final int EXPLO5 = 33;

    public static final int ENERGY = 34;
    public static final int HEAT = 35;

    public static final int VICTORY_TITLE = 36;
    public static final int DEFEAT_TITLE = 37;
    public static final int WAVEI = 38;
    public static final int WAVEII = 39;
    public static final int WAVEIII = 40;
    public static final int WAVEIV = 41;
    public static final int WAVEV = 42;
    public static final int WAVEVI = 43;
    public static final int RELEASE_THE_KRAKEN = 44;
    public static final int YOU_WILL_BE_EXECUTED = 45;
	
	public static final int STAR1 = 1;
	public static final int STAR2 = 2;
	public static final int STAR3 = 3;

    public static final int TITLE = 0;
    public static final int START_GAME = 1;
    public static final int START_GAME_HOVER = 2;
    public static final int OPTION = 3;
    public static final int OPTION_HOVER = 4;
    public static final int LOAD_GAME = 5;
    public static final int LOAD_GAME_HOVER = 6;
    public static final int DIFFICULTY = 7;
    public static final int NORMAL_TITLE = 8;
    public static final int NORMAL_HOVER = 9;
    public static final int INSANE = 10;
    public static final int INSANE_HOVER = 11;
    public static final int SOUND = 12;
    public static final int MUSIC = 13;
    public static final int MUSIC_HOVER = 14;
    public static final int EFFECTS = 15;
    public static final int EFFECTS_HOVER = 16;
    public static final int BACK = 17;
    public static final int BACK_HOVER = 18;
    public static final int RESUME = 19;
    public static final int RESUME_HOVER = 20;
    public static final int SAVE = 21;
    public static final int SAVE_HOVER = 22;
    public static final int GAME_SAVED = 23;
    public static final int GAME_SAVED_HOVER = 24;
    public static final int RADIO_BUTTON = 25;
    public static final int RADIO_BUTTON_CHECKED = 26;
    public static final int CHECKBOX = 27;
    public static final int CHECKBOX_CHECKED = 28;
    public static final int BACKGROUND = 29;
    public static final int SCORE = 30;
    public static final int TIME = 31;
    public static final int STRIKER = 32;
    public static final int STRIKER_HOVER = 33;
    public static final int HUNTER = 34;
    public static final int HUNTER_HOVER = 35;
    public static final int DESTROYER = 36;
    public static final int DESTROYER_HOVER = 37;
    public static final int VICTORY = 38;
    public static final int DEFEAT = 39;
    public static final int STRIKER_AVATAR = 40;
    public static final int HUNTER_AVATAR = 41;
    public static final int DESTROYER_AVATAR = 42;
    public static final int STRIKER_AVATAR_HOVER = 43;
    public static final int HUNTER_AVATAR_HOVER = 44;
    public static final int DESTROYER_AVATAR_HOVER = 45;
    public static final int QUIT = 46;
    public static final int QUIT_HOVER = 47;

    public static final int TYPEI = 1;
    public static final int TYPEII = 2;
    public static final int TYPEIII = 3;

    public static final int NORMAL_LEVEL = 1;
    public static final int INSANE_LEVEL = 2;
	
	public static final int SCREEN_W = Gdx.graphics.getWidth();
	public static final int SCREEN_H = Gdx.graphics.getHeight();

    public static final String PREF_NAME = "Saved";

    private Sound bulletSound;
    private Sound laserSound;
    private Sound raySound;
    private Sound rayFocusSound;
    private Sound explosionSound;
    private Sound shipExplosionSound;
	
	private SpriteManager() {
		spritesheet = new TextureAtlas(Gdx.files.internal("data/Spritesheet.atlas"));
        menuSprite = new TextureAtlas(Gdx.files.internal("data/Menu.atlas"));
        fontScore = new BitmapFont(Gdx.files.internal("data/Infinity.fnt"),
                Gdx.files.internal("data/Infinity.png"), false);
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("data/Hitman.mp3"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("data/bullet.wav"));
        laserSound = Gdx.audio.newSound(Gdx.files.internal("data/laser.wav"));
        raySound = Gdx.audio.newSound(Gdx.files.internal("data/ray.wav"));
        rayFocusSound = Gdx.audio.newSound(Gdx.files.internal("data/ray-focus.mp3"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("data/explosion2.wav"));
        shipExplosionSound = Gdx.audio.newSound(Gdx.files.internal("data/ship_explosion.wav"));
	}
	
	public static SpriteManager getInstance() {
		if (null == instance) {
			instance = new SpriteManager();
		}
		return instance;
	}

    public TextureRegion getMessage(int type) {
        switch (type) {
            case VICTORY_TITLE: return new TextureRegion(menuSprite.findRegion("Victory"));
            case DEFEAT_TITLE: return new TextureRegion(menuSprite.findRegion("Defeat"));
            case WAVEI: return new TextureRegion(menuSprite.findRegion("Wave1"));
            case WAVEII: return new TextureRegion(menuSprite.findRegion("Wave2"));
            case WAVEIII: return new TextureRegion(menuSprite.findRegion("Wave3"));
            case WAVEIV: return new TextureRegion(menuSprite.findRegion("Wave4"));
            case WAVEV: return new TextureRegion(menuSprite.findRegion("Wave5"));
            case WAVEVI: return new TextureRegion(menuSprite.findRegion("Wave6"));
            case RELEASE_THE_KRAKEN: return new TextureRegion(menuSprite.findRegion("ReleaseTheDraken"));
            case YOU_WILL_BE_EXECUTED: return new TextureRegion(menuSprite.findRegion("YouWillBeExecuted"));
            default: return null;
        }
    }
	
	public TextureRegion getMainShipFrame(int ship, int mode) {
		switch (ship) {
			case SHIP1:
				switch (mode) {
					case IDLE: return new TextureRegion(spritesheet.findRegion("Ship1"));
					case LEFT: return new TextureRegion(spritesheet.findRegion("Ship1Left"));
					case RIGHT: return new TextureRegion(spritesheet.findRegion("Ship1Right"));
					case IDLE_HIT: return new TextureRegion(spritesheet.findRegion("Ship1Hit"));
					case LEFT_HIT: return new TextureRegion(spritesheet.findRegion("Ship1LeftHit"));
					case RIGHT_HIT: return new TextureRegion(spritesheet.findRegion("Ship1RightHit"));
					default: return null;
				}
			case SHIP2:
				switch (mode) {
                    case IDLE: return new TextureRegion(spritesheet.findRegion("Ship2"));
                    case LEFT: return new TextureRegion(spritesheet.findRegion("Ship2Left"));
                    case RIGHT: return new TextureRegion(spritesheet.findRegion("Ship2Right"));
                    case IDLE_HIT: return new TextureRegion(spritesheet.findRegion("Ship2Hit"));
                    case LEFT_HIT: return new TextureRegion(spritesheet.findRegion("Ship2LeftHit"));
                    case RIGHT_HIT: return new TextureRegion(spritesheet.findRegion("Ship2RightHit"));
                    default: return null;
				}
			case SHIP3:
				switch (mode) {
                    case IDLE: return new TextureRegion(spritesheet.findRegion("Ship3"));
                    case LEFT: return new TextureRegion(spritesheet.findRegion("Ship3Left"));
                    case RIGHT: return new TextureRegion(spritesheet.findRegion("Ship3Right"));
                    case IDLE_HIT: return new TextureRegion(spritesheet.findRegion("Ship3Hit"));
                    case LEFT_HIT: return new TextureRegion(spritesheet.findRegion("Ship3LeftHit"));
                    case RIGHT_HIT: return new TextureRegion(spritesheet.findRegion("Ship3RightHit"));
                    default: return null;
				}
			default: return null;
		}
	}
	
	public TextureRegion getEnemyShipFrame(int type, int mode) {
		switch (type) {
			case ENEMY1:
				switch (mode) {
					case NORMAL: return new TextureRegion(spritesheet.findRegion("Ene1"));
					case HIT: return new TextureRegion(spritesheet.findRegion("Ene1Hit"));
					default: return null;
				}
			case ENEMY2:
				switch (mode) {
					case NORMAL: return new TextureRegion(spritesheet.findRegion("Ene2"));
					case HIT: return new TextureRegion(spritesheet.findRegion("Ene2Hit"));
					default: return null;
				}
			case ENEMY3:
				switch (mode) {
					case NORMAL: return new TextureRegion(spritesheet.findRegion("Ene3"));
					case HIT: return new TextureRegion(spritesheet.findRegion("Ene3Hit"));
					default: return null;
				}
			case ENEMY4:
				switch (mode) {
					case NORMAL: return new TextureRegion(spritesheet.findRegion("Ene4"));
					case HIT: return new TextureRegion(spritesheet.findRegion("Ene4Hit"));
					default: return null;
				}
			case ENEMY5:
				switch (mode) {
					case NORMAL: return new TextureRegion(spritesheet.findRegion("Ene5"));
					case HIT: return new TextureRegion(spritesheet.findRegion("Ene5Hit"));
					default: return null;
				}
			case ENEMY6:
				switch (mode) {
					case NORMAL: return new TextureRegion(spritesheet.findRegion("Ene6"));
					case HIT: return new TextureRegion(spritesheet.findRegion("Ene6Hit"));
					default: return null;
				}
			case ENEMY7:
				switch (mode) {
					case NORMAL: return new TextureRegion(spritesheet.findRegion("Ene7"));
					case HIT: return new TextureRegion(spritesheet.findRegion("Ene7Hit"));
					default: return null;
				}
			case ENEMY8:
				switch (mode) {
					case NORMAL: return new TextureRegion(spritesheet.findRegion("Ene8"));
					case HIT: return new TextureRegion(spritesheet.findRegion("Ene8Hit"));
					default: return null;
				}
            case EXECUTOR:
                switch (mode) {
                    case NORMAL: return new TextureRegion(spritesheet.findRegion("Battleship"));
                    case HIT: return new TextureRegion(spritesheet.findRegion("BattleshipHit"));
                    default: return null;
                }
			default: return null;
		}
	}
	
	public TextureRegion getBulletFrame(int type) {
		switch (type) {
			case BULLET1: return new TextureRegion(spritesheet.findRegion("BulletA"));
			case BULLET2: return new TextureRegion(spritesheet.findRegion("BulletB"));
			case BULLET3: return new TextureRegion(spritesheet.findRegion("BulletC"));
			case BULLET4: return new TextureRegion(spritesheet.findRegion("BulletD"));
			case ENE_BULL_1: return new TextureRegion(spritesheet.findRegion("EneBull1"));
			case ENE_BULL_2: return new TextureRegion(spritesheet.findRegion("EneBull2"));
			case ENE_BULL_3: return new TextureRegion(spritesheet.findRegion("EneBull3"));
			case ENE_BULL_4: return new TextureRegion(spritesheet.findRegion("EneBull4"));
            case ENE_LASER_1: return new TextureRegion(spritesheet.findRegion("EneLaser1"));
            case ENE_LASER_2: return new TextureRegion(spritesheet.findRegion("EneLaser2"));
            case ENE_LASER_3: return new TextureRegion(spritesheet.findRegion("EneLaser3"));
            case ENE_LASER_4: return new TextureRegion(spritesheet.findRegion("EneLaser4"));
            case ENE_RAY_1: return new TextureRegion(spritesheet.findRegion("EneRay1"));
            case ENE_RAY_2: return new TextureRegion(spritesheet.findRegion("EneRay2"));
            case ENE_RAY_3: return new TextureRegion(spritesheet.findRegion("EneRay3"));
            case ENE_RAY_4: return new TextureRegion(spritesheet.findRegion("EneRay4"));
			default: return null;
		}
	}
	
	public Animation getExplosionAnimation(int type) {
		switch (type) {
			case EXPLO1: return new Animation(1/30f,
					spritesheet.findRegion("EY1"),
					spritesheet.findRegion("EY2"),
					spritesheet.findRegion("EY3"),
					spritesheet.findRegion("EY4"),
					spritesheet.findRegion("EY5"),
					spritesheet.findRegion("EY6"),
					spritesheet.findRegion("EY7"),
					spritesheet.findRegion("EY8"));
			case EXPLO2: return new Animation(1/30f,
					spritesheet.findRegion("ER1"),
					spritesheet.findRegion("ER2"),
					spritesheet.findRegion("ER3"),
					spritesheet.findRegion("ER4"),
					spritesheet.findRegion("ER5"),
					spritesheet.findRegion("ER6"),
					spritesheet.findRegion("ER7"),
					spritesheet.findRegion("ER8"));
			case EXPLO3: return new Animation(1/30f,
					spritesheet.findRegion("EB1"),
					spritesheet.findRegion("EB2"),
					spritesheet.findRegion("EB3"),
					spritesheet.findRegion("EB4"),
					spritesheet.findRegion("EB5"),
					spritesheet.findRegion("EB6"),
					spritesheet.findRegion("EB7"),
					spritesheet.findRegion("EB8"));
			case EXPLO4: return new Animation(1/30f,
					spritesheet.findRegion("EG1"),
					spritesheet.findRegion("EG2"),
					spritesheet.findRegion("EG3"),
					spritesheet.findRegion("EG4"),
					spritesheet.findRegion("EG5"),
					spritesheet.findRegion("EG6"),
					spritesheet.findRegion("EG7"),
					spritesheet.findRegion("EG8"));
			case EXPLO5: return new Animation(1/30f,
					spritesheet.findRegion("EW1"),
					spritesheet.findRegion("EW2"),
					spritesheet.findRegion("EW3"),
					spritesheet.findRegion("EW4"),
					spritesheet.findRegion("EW5"),
					spritesheet.findRegion("EW6"),
					spritesheet.findRegion("EW7"),
					spritesheet.findRegion("EW8"));
			default: return null;
		}
	}

    public TextureRegion getMiscellaneousFrame(int type) {
        switch(type) {
            case ENERGY: return new TextureRegion(spritesheet.findRegion("EnergyLevel"));
            case HEAT: return new TextureRegion(spritesheet.findRegion("HeatLevel"));
            default: return null;
        }
    }
	
	public TextureRegion getStarFrame(int type) {
		switch (type) {
			case STAR1: return new TextureRegion(spritesheet.findRegion("Star1"));
			case STAR2: return new TextureRegion(spritesheet.findRegion("Star2"));
			case STAR3: return new TextureRegion(spritesheet.findRegion("Star3"));
			default: return null;
		}
	}
	
	public int getOffSetWidth(int type, int mode) {
		switch (type) {
			case SHIP1:
				switch (mode) {
					case IDLE: return spritesheet.findRegion("Ship1").getRegionWidth() / 2;
					case LEFT: return spritesheet.findRegion("Ship1Left").getRegionWidth() / 2;
					case RIGHT: return spritesheet.findRegion("Ship1Right").getRegionWidth() / 2;
					case IDLE_HIT: return spritesheet.findRegion("Ship1Hit").getRegionWidth() / 2;
					case LEFT_HIT: return spritesheet.findRegion("Ship1LeftHit").getRegionWidth() / 2;
					case RIGHT_HIT: return spritesheet.findRegion("Ship1RightHit").getRegionWidth() / 2;
					default: return 0;
				}
			case SHIP2:
				switch (mode) {
					case IDLE: return spritesheet.findRegion("Ship2").getRegionWidth() / 2;
					case LEFT: return spritesheet.findRegion("Ship2Left").getRegionWidth() / 2;
					case RIGHT: return spritesheet.findRegion("Ship2Right").getRegionWidth() / 2;
					case IDLE_HIT: return spritesheet.findRegion("Ship2Hit").getRegionWidth() / 2;
					case LEFT_HIT: return spritesheet.findRegion("Ship2LeftHit").getRegionWidth() / 2;
					case RIGHT_HIT: return spritesheet.findRegion("Ship2RightHit").getRegionWidth() / 2;
					default: return 0;
				}
			case SHIP3:
				switch (mode) {
					case IDLE: return spritesheet.findRegion("Ship3").getRegionWidth() / 2;
					case LEFT: return spritesheet.findRegion("Ship3Left").getRegionWidth() / 2;
					case RIGHT: return spritesheet.findRegion("Ship3Right").getRegionWidth() / 2;
					case IDLE_HIT: return spritesheet.findRegion("Ship3Hit").getRegionWidth() / 2;
					case LEFT_HIT: return spritesheet.findRegion("Ship3LeftHit").getRegionWidth() / 2;
					case RIGHT_HIT: return spritesheet.findRegion("Ship3RightHit").getRegionWidth() / 2;
					default: return 0;
				}
			case ENEMY1:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene1").getRegionWidth() / 2;
					case HIT: return spritesheet.findRegion("Ene1Hit").getRegionWidth() / 2;
					default: return 0;
				}
			case ENEMY2:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene2").getRegionWidth() / 2;
					case HIT: return spritesheet.findRegion("Ene2Hit").getRegionWidth() / 2;
					default: return 0;
				}
			case ENEMY3:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene3").getRegionWidth() / 2;
					case HIT: return spritesheet.findRegion("Ene3Hit").getRegionWidth() / 2;
					default: return 0;
				}
			case ENEMY4:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene4").getRegionWidth() / 2;
					case HIT: return spritesheet.findRegion("Ene4Hit").getRegionWidth() / 2;
					default: return 0;
				}
			case ENEMY5:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene5").getRegionWidth() / 2;
					case HIT: return spritesheet.findRegion("Ene5Hit").getRegionWidth() / 2;
					default: return 0;
				}
			case ENEMY6:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene6").getRegionWidth() / 2;
					case HIT: return spritesheet.findRegion("Ene6Hit").getRegionWidth() / 2;
					default: return 0;
				}
			case ENEMY7:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene7").getRegionWidth() / 2;
					case HIT: return spritesheet.findRegion("Ene7Hit").getRegionWidth() / 2;
					default: return 0;
				}
			case ENEMY8:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene8").getRegionWidth() / 2;
					case HIT: return spritesheet.findRegion("Ene8Hit").getRegionWidth() / 2;
					default: return 0;
				}
            case EXECUTOR:
                switch (mode) {
                    case NORMAL: return spritesheet.findRegion("Battleship").getRegionWidth() / 2;
                    case HIT: return spritesheet.findRegion("BattleshipHit").getRegionWidth() / 2;
                    default: return 0;
                }
			case BULLET1: return spritesheet.findRegion("BulletA").getRegionWidth() / 2;
			case BULLET2: return spritesheet.findRegion("BulletB").getRegionWidth() / 2;
			case BULLET3: return spritesheet.findRegion("BulletC").getRegionWidth() / 2;
			case BULLET4: return spritesheet.findRegion("BulletD").getRegionWidth() / 2;
			case ENE_BULL_1: return spritesheet.findRegion("EneBull1").getRegionWidth() / 2;
            case ENE_BULL_2: return spritesheet.findRegion("EneBull2").getRegionWidth() / 2;
            case ENE_BULL_3: return spritesheet.findRegion("EneBull3").getRegionWidth() / 2;
            case ENE_BULL_4: return spritesheet.findRegion("EneBull4").getRegionWidth() / 2;
            case ENE_LASER_1: return spritesheet.findRegion("EneLaser1").getRegionWidth() / 2;
            case ENE_LASER_2: return spritesheet.findRegion("EneLaser2").getRegionWidth() / 2;
            case ENE_LASER_3: return spritesheet.findRegion("EneLaser3").getRegionWidth() / 2;
            case ENE_LASER_4: return spritesheet.findRegion("EneLaser4").getRegionWidth() / 2;
            case ENE_RAY_1: return spritesheet.findRegion("EneRay1").getRegionWidth() / 2;
            case ENE_RAY_2: return spritesheet.findRegion("EneRay2").getRegionWidth() / 2;
            case ENE_RAY_3: return spritesheet.findRegion("EneRay3").getRegionWidth() / 2;
            case ENE_RAY_4: return spritesheet.findRegion("EneRay4").getRegionWidth() / 2;
			case EXPLO1: return spritesheet.findRegion("EY1").getRegionWidth() / 2;
            case EXPLO2: return spritesheet.findRegion("ER1").getRegionWidth() / 2;
            case EXPLO3: return spritesheet.findRegion("EB1").getRegionWidth() / 2;
            case EXPLO4: return spritesheet.findRegion("EG1").getRegionWidth() / 2;
            case EXPLO5: return spritesheet.findRegion("EW1").getRegionWidth() / 2;
            case ENERGY: return spritesheet.findRegion("EnergyLevel").getRegionWidth() / 2;
            case HEAT: return spritesheet.findRegion("HeatLevel").getRegionWidth() / 2;
            case VICTORY_TITLE: return menuSprite.findRegion("Victory").getRegionWidth() / 2;
            case DEFEAT_TITLE: return menuSprite.findRegion("Defeat").getRegionWidth() / 2;
            case WAVEI: return menuSprite.findRegion("Wave1").getRegionWidth() / 2;
            case WAVEII: return menuSprite.findRegion("Wave2").getRegionWidth() / 2;
            case WAVEIII: return menuSprite.findRegion("Wave3").getRegionWidth() / 2;
            case WAVEIV: return menuSprite.findRegion("Wave4").getRegionWidth() / 2;
            case WAVEV: return menuSprite.findRegion("Wave5").getRegionWidth() / 2;
            case WAVEVI: return menuSprite.findRegion("Wave6").getRegionWidth() / 2;
            case RELEASE_THE_KRAKEN: return menuSprite.findRegion("ReleaseTheDraken").getRegionWidth() / 2;
            case YOU_WILL_BE_EXECUTED: return menuSprite.findRegion("YouWillBeExecuted").getRegionWidth() / 2;
			default: return 0;
		}
	}
	
	public int getOffSetHeight(int type, int mode) {
		switch (type) {
			case SHIP1:
				switch (mode) {
					case IDLE: return spritesheet.findRegion("Ship1").getRegionHeight() / 2;
					case LEFT: return spritesheet.findRegion("Ship1Left").getRegionHeight() / 2;
					case RIGHT: return spritesheet.findRegion("Ship1Right").getRegionHeight() / 2;
					case IDLE_HIT: return spritesheet.findRegion("Ship1Hit").getRegionHeight() / 2;
					case LEFT_HIT: return spritesheet.findRegion("Ship1LeftHit").getRegionHeight() / 2;
					case RIGHT_HIT: return spritesheet.findRegion("Ship1RightHit").getRegionHeight() / 2;
					default: return 0;
				}
			case SHIP2:
				switch (mode) {
					case IDLE: return spritesheet.findRegion("Ship2").getRegionHeight() / 2;
					case LEFT: return spritesheet.findRegion("Ship2Left").getRegionHeight() / 2;
					case RIGHT: return spritesheet.findRegion("Ship2Right").getRegionHeight() / 2;
					case IDLE_HIT: return spritesheet.findRegion("Ship2Hit").getRegionHeight() / 2;
					case LEFT_HIT: return spritesheet.findRegion("Ship2LeftHit").getRegionHeight() / 2;
					case RIGHT_HIT: return spritesheet.findRegion("Ship2RightHit").getRegionHeight() / 2;
					default: return 0;
				}
			case SHIP3:
				switch (mode) {
					case IDLE: return spritesheet.findRegion("Ship3").getRegionHeight() / 2;
					case LEFT: return spritesheet.findRegion("Ship3Left").getRegionHeight() / 2;
					case RIGHT: return spritesheet.findRegion("Ship3Right").getRegionHeight() / 2;
					case IDLE_HIT: return spritesheet.findRegion("Ship3Hit").getRegionHeight() / 2;
					case LEFT_HIT: return spritesheet.findRegion("Ship3LeftHit").getRegionHeight() / 2;
					case RIGHT_HIT: return spritesheet.findRegion("Ship3RightHit").getRegionHeight() / 2;
					default: return 0;
				}
			case ENEMY1:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene1").getRegionHeight() / 2;
					case HIT: return spritesheet.findRegion("Ene1Hit").getRegionHeight() / 2;
					default: return 0;
				}
			case ENEMY2:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene2").getRegionHeight() / 2;
					case HIT: return spritesheet.findRegion("Ene2Hit").getRegionHeight() / 2;
					default: return 0;
				}
			case ENEMY3:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene3").getRegionHeight() / 2;
					case HIT: return spritesheet.findRegion("Ene3Hit").getRegionHeight() / 2;
					default: return 0;
				}
			case ENEMY4:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene4").getRegionHeight() / 2;
					case HIT: return spritesheet.findRegion("Ene4Hit").getRegionHeight() / 2;
					default: return 0;
				}
			case ENEMY5:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene5").getRegionHeight() / 2;
					case HIT: return spritesheet.findRegion("Ene5Hit").getRegionHeight() / 2;
					default: return 0;
				}
			case ENEMY6:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene6").getRegionHeight() / 2;
					case HIT: return spritesheet.findRegion("Ene6Hit").getRegionHeight() / 2;
					default: return 0;
				}
			case ENEMY7:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene7").getRegionHeight() / 2;
					case HIT: return spritesheet.findRegion("Ene7Hit").getRegionHeight() / 2;
					default: return 0;
				}
			case ENEMY8:
				switch (mode) {
					case NORMAL: return spritesheet.findRegion("Ene8").getRegionHeight() / 2;
					case HIT: return spritesheet.findRegion("Ene8Hit").getRegionHeight() / 2;
					default: return 0;
				}
            case EXECUTOR:
                switch (mode) {
                    case NORMAL: return spritesheet.findRegion("Battleship").getRegionHeight() / 2;
                    case HIT: return spritesheet.findRegion("BattleshipHit").getRegionHeight() / 2;
                    default: return 0;
                }
			case BULLET1: return spritesheet.findRegion("BulletA").getRegionHeight() / 2;
			case BULLET2: return spritesheet.findRegion("BulletB").getRegionHeight() / 2;
			case BULLET3: return spritesheet.findRegion("BulletC").getRegionHeight() / 2;
			case BULLET4: return spritesheet.findRegion("BulletD").getRegionHeight() / 2;
            case ENE_BULL_1: return spritesheet.findRegion("EneBull1").getRegionHeight() / 2;
            case ENE_BULL_2: return spritesheet.findRegion("EneBull2").getRegionHeight() / 2;
            case ENE_BULL_3: return spritesheet.findRegion("EneBull3").getRegionHeight() / 2;
            case ENE_BULL_4: return spritesheet.findRegion("EneBull4").getRegionHeight() / 2;
            case ENE_LASER_1: return spritesheet.findRegion("EneLaser1").getRegionHeight() / 2;
            case ENE_LASER_2: return spritesheet.findRegion("EneLaser2").getRegionHeight() / 2;
            case ENE_LASER_3: return spritesheet.findRegion("EneLaser3").getRegionHeight() / 2;
            case ENE_LASER_4: return spritesheet.findRegion("EneLaser4").getRegionHeight() / 2;
            case ENE_RAY_1: return spritesheet.findRegion("EneRay1").getRegionHeight() / 2;
            case ENE_RAY_2: return spritesheet.findRegion("EneRay2").getRegionHeight() / 2;
            case ENE_RAY_3: return spritesheet.findRegion("EneRay3").getRegionHeight() / 2;
            case ENE_RAY_4: return spritesheet.findRegion("EneRay4").getRegionHeight() / 2;
            case EXPLO1: return spritesheet.findRegion("EY1").getRegionHeight() / 2;
            case EXPLO2: return spritesheet.findRegion("ER1").getRegionHeight() / 2;
            case EXPLO3: return spritesheet.findRegion("EB1").getRegionHeight() / 2;
            case EXPLO4: return spritesheet.findRegion("EG1").getRegionHeight() / 2;
            case EXPLO5: return spritesheet.findRegion("EW1").getRegionHeight() / 2;
            case ENERGY: return spritesheet.findRegion("EnergyLevel").getRegionHeight() / 2;
            case HEAT: return spritesheet.findRegion("HeatLevel").getRegionHeight() / 2;
            case VICTORY_TITLE: return menuSprite.findRegion("Victory").getRegionHeight() / 2;
            case DEFEAT_TITLE: return menuSprite.findRegion("Defeat").getRegionHeight() / 2;
            case WAVEI: return menuSprite.findRegion("Wave1").getRegionHeight() / 2;
            case WAVEII: return menuSprite.findRegion("Wave2").getRegionHeight() / 2;
            case WAVEIII: return menuSprite.findRegion("Wave3").getRegionHeight() / 2;
            case WAVEIV: return menuSprite.findRegion("Wave4").getRegionHeight() / 2;
            case WAVEV: return menuSprite.findRegion("Wave5").getRegionHeight() / 2;
            case WAVEVI: return menuSprite.findRegion("Wave6").getRegionHeight() / 2;
            case RELEASE_THE_KRAKEN: return menuSprite.findRegion("ReleaseTheDraken").getRegionHeight() / 2;
            case YOU_WILL_BE_EXECUTED: return menuSprite.findRegion("YouWillBeExecuted").getRegionHeight() / 2;
			default: return 0;
		}
	}

    public TextureRegionDrawable getTitle(int title) {
        switch(title) {
            case TITLE: return new TextureRegionDrawable(menuSprite.findRegion("YouShallNotPass"));
            case START_GAME: return new TextureRegionDrawable(menuSprite.findRegion("StartGame"));
            case START_GAME_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("StartGameHover"));
            case OPTION: return new TextureRegionDrawable(menuSprite.findRegion("Option"));
            case OPTION_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("OptionHover"));
            case LOAD_GAME: return new TextureRegionDrawable(menuSprite.findRegion("LoadGame"));
            case LOAD_GAME_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("LoadGameHover"));
            case DIFFICULTY: return new TextureRegionDrawable(menuSprite.findRegion("Difficulty"));
            case NORMAL_TITLE: return new TextureRegionDrawable(menuSprite.findRegion("Normal"));
            case NORMAL_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("NormalHover"));
            case INSANE: return new TextureRegionDrawable(menuSprite.findRegion("Insane"));
            case INSANE_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("InsaneHover"));
            case SOUND: return new TextureRegionDrawable(menuSprite.findRegion("Sound"));
            case MUSIC: return new TextureRegionDrawable(menuSprite.findRegion("Music"));
            case MUSIC_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("MusicHover"));
            case EFFECTS: return new TextureRegionDrawable(menuSprite.findRegion("Effects"));
            case EFFECTS_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("EffectsHover"));
            case BACK: return new TextureRegionDrawable(menuSprite.findRegion("Back"));
            case BACK_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("BackHover"));
            case RESUME: return new TextureRegionDrawable(menuSprite.findRegion("Resume"));
            case RESUME_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("ResumeHover"));
            case SAVE: return new TextureRegionDrawable(menuSprite.findRegion("Save"));
            case SAVE_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("SaveHover"));
            case GAME_SAVED: return new TextureRegionDrawable(menuSprite.findRegion("GameSaved"));
            case GAME_SAVED_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("GameSavedHover"));
            case RADIO_BUTTON: return new TextureRegionDrawable(menuSprite.findRegion("RadioButton"));
            case RADIO_BUTTON_CHECKED: return new TextureRegionDrawable(menuSprite.findRegion("RadioButtonChecked"));
            case CHECKBOX: return new TextureRegionDrawable(menuSprite.findRegion("CheckBox"));
            case CHECKBOX_CHECKED: return new TextureRegionDrawable(menuSprite.findRegion("CheckBoxChecked"));
            case BACKGROUND: return new TextureRegionDrawable(spritesheet.findRegion("BG"));
            case SCORE: return new TextureRegionDrawable(menuSprite.findRegion("Score"));
            case TIME: return new TextureRegionDrawable(menuSprite.findRegion("Time"));
            case STRIKER: return new TextureRegionDrawable(menuSprite.findRegion("Striker"));
            case STRIKER_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("StrikerHover"));
            case HUNTER: return new TextureRegionDrawable(menuSprite.findRegion("Hunter"));
            case HUNTER_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("HunterHover"));
            case DESTROYER: return new TextureRegionDrawable(menuSprite.findRegion("Destroyer"));
            case DESTROYER_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("DestroyerHover"));
            case VICTORY: return new TextureRegionDrawable(menuSprite.findRegion("Victory"));
            case DEFEAT: return new TextureRegionDrawable(menuSprite.findRegion("Defeat"));
            case STRIKER_AVATAR: return new TextureRegionDrawable(spritesheet.findRegion("Ship1"));
            case STRIKER_AVATAR_HOVER: return new TextureRegionDrawable(spritesheet.findRegion("Ship1Hit"));
            case HUNTER_AVATAR: return new TextureRegionDrawable(spritesheet.findRegion("Ship2"));
            case HUNTER_AVATAR_HOVER: return new TextureRegionDrawable(spritesheet.findRegion("Ship2Hit"));
            case DESTROYER_AVATAR: return new TextureRegionDrawable(spritesheet.findRegion("Ship3"));
            case DESTROYER_AVATAR_HOVER: return new TextureRegionDrawable(spritesheet.findRegion("Ship3Hit"));
            case QUIT: return new TextureRegionDrawable(menuSprite.findRegion("Quit"));
            case QUIT_HOVER: return new TextureRegionDrawable(menuSprite.findRegion("QuitHover"));
            default: return null;
        }
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }
	
	public int getStarHeight() {
		return spritesheet.findRegion("Star1").getRegionHeight();
	}

    public BitmapFont getFontScore() {
        return fontScore;
    }

    public Sound getBulletSound() {
        return bulletSound;
    }

    public Sound getLaserSound() {
        return laserSound;
    }

    public Sound getRaySound() {
        return raySound;
    }

    public Sound getRayFocusSound() {
        return rayFocusSound;
    }

    public Sound getExplosionSound() {
        return explosionSound;
    }

    public Sound getShipExplosionSound() {
        return shipExplosionSound;
    }

    public void dispose() {
		spritesheet.dispose();
        menuSprite.dispose();
        fontScore.dispose();
        backgroundMusic.dispose();
        bulletSound.dispose();
        laserSound.dispose();
        raySound.dispose();
        rayFocusSound.dispose();
        explosionSound.dispose();
	}

    public Preferences getPreferences() {
        if (preferences == null) {
            preferences = Gdx.app.getPreferences(PREF_NAME);
        }
        return preferences;
    }
}
