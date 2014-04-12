package vn.edu.rmit.youshallnotpass.universe.darkside;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;
import vn.edu.rmit.youshallnotpass.mechanism.Tuple;
import vn.edu.rmit.youshallnotpass.universe.GalaxyManager;
import vn.edu.rmit.youshallnotpass.universe.UFO;

public abstract class Enemy implements UFO {
	protected SpriteManager sprites;
	
	private TextureRegion normalFrame;
	private TextureRegion hitFrame;
	private Animation explodeAnimation;
	protected Tuple pos;                    // Save
	private Tuple offPos;
	private Tuple offPosHit;
	private Tuple offPosExplode;
	
	protected Array<TextureRegion> particles;   // Rebuilding
	protected Array<Tuple> parTups;         // Save
	protected Tuple offPar;
	
	protected int index = 0;                // Save
	private float des = 0;                  // Save
	private int eneCounter = 0;             // Save
	private float detonateTime = 0;
    private boolean called = false;         // Save

    protected int bulletSpeed;
    protected int bulletFrequency;
    protected int hSpeed;
    protected int vSpeed;
    protected int hInterval;
    protected int vInterval;
	protected float health;                 // Save
    protected int bullet;
    protected float power = 1;
    protected int score;
    protected int type;

    private boolean soundPlayed = false;
	
	public Enemy() {
        sprites = SpriteManager.getInstance();
    }

    protected void construct(int model, int explode, int bullet) {
        this.bullet = bullet;
        normalFrame = sprites.getEnemyShipFrame(model, SpriteManager.NORMAL);
        hitFrame = sprites.getEnemyShipFrame(model, SpriteManager.HIT);
        explodeAnimation = sprites.getExplosionAnimation(explode);
        explodeAnimation.setPlayMode(Animation.NORMAL);

        particles = new Array<TextureRegion>();
        parTups = new Array<Tuple>();

        offPosHit = new Tuple(sprites.getOffSetWidth(model, SpriteManager.HIT),
                sprites.getOffSetHeight(model, SpriteManager.HIT));

        offPos = new Tuple(sprites.getOffSetWidth(model, SpriteManager.NORMAL),
                sprites.getOffSetHeight(model, SpriteManager.NORMAL));

        offPosExplode = new Tuple(sprites.getOffSetWidth(explode, SpriteManager.NORMAL),
                sprites.getOffSetHeight(explode, SpriteManager.NORMAL));

        pos = new Tuple(SpriteManager.SCREEN_W / 2, SpriteManager.SCREEN_H + offPos.y);

        offPar = new Tuple(sprites.getOffSetWidth(bullet, SpriteManager.NORMAL),
                sprites.getOffSetHeight(bullet, SpriteManager.NORMAL));

        des = (float)Math.random() * SpriteManager.SCREEN_W + 1;
    }
	
	@Override
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

    protected boolean checkHealth(SpriteBatch batch) {
        if (health <= 0) {
            // Bullets remain moving
            for (int i = 0; i < particles.size; i++) {
                parTups.set(i, new Tuple(parTups.get(i).x, parTups.get(i).y - bulletSpeed));
                batch.draw(particles.get(i), parTups.get(i).x - offPar.x, parTups.get(i).y - offPar.y);
                if (parTups.get(i).y < 0) {
                    particles.removeIndex(i);
                    parTups.removeIndex(i);
                    index--;
                }
            }

            // Playing explode animation
            if (isDestroyed()) {
                return true;
            }
            if (!soundPlayed) {
                GalaxyManager.playSound(sprites.getExplosionSound());
                soundPlayed = true;
            }
            detonateTime += Gdx.graphics.getDeltaTime();
            batch.draw(explodeAnimation.getKeyFrame(detonateTime), pos.x - offPosExplode.x, pos.y - offPosExplode.y);
            return true;
        }
        return false;
    }

    protected abstract void shoot(SpriteBatch batch);

    protected void move() {
        eneCounter++;
        if (eneCounter % hInterval == 0) {
            if (Math.abs(des - pos.x) > hSpeed && pos.x < des) {
                pos.x += hSpeed;
            } else if (Math.abs(des - pos.x) > hSpeed && pos.x > des) {
                pos.x -= hSpeed;
            } else if (pos.x >= SpriteManager.SCREEN_W - offPos.x || pos.x <= offPos.x || Math.abs(des - pos.x) < hSpeed){
                des = (float)Math.random() * SpriteManager.SCREEN_W + 1;
            }
        }
        if (eneCounter % vInterval == 0) {
            pos.y -= vSpeed;
        }
    }

    protected void checkHit(SpriteBatch batch, boolean isHit, float power) {
        if (isHit) {
            batch.draw(hitFrame, pos.x - offPosHit.x, pos.y - offPosHit.y);
            health -= power;
        } else {
            batch.draw(normalFrame, pos.x - offPos.x, pos.y - offPos.y);
        }
    }
	
	public boolean isOffScreen() {
        return pos.y + offPos.y < 0;
    }
	
	public Tuple getPos() {
		return pos;
	}
	
	public Tuple getOffPos() {
		return offPos;
	}
	
	public Array<Tuple> getParTups() {
		return parTups;
	}
	
	public Tuple getOffPar() {
		return offPar;
	}

    public void selfDestruct() {
        health = 0;
    }
	
	public boolean isDestroyed() {
		return explodeAnimation.isAnimationFinished(detonateTime);
	}

    public boolean isToBeRemoved() {
        return isDestroyed() && particles.size == 0;
    }

    public boolean isDead() {
        if (!called && health <= 0) {
            called = true;
            return true;
        }
        return false;
    }

	@Override
	public void removeBullet(int index) {
		particles.removeIndex(index);
		parTups.removeIndex(index);
		this.index--;
	}

    @Override
    public float getBulletPower() {
        return power;
    }

    public abstract int randomizeProduction();

    public int getScore() {
        return score;
    }

    public int getType() {
        return type;
    }

    public float getParX(int index) {
        return pos.x;
    }

    public void save(int id) {
        Preferences preferences = sprites.getPreferences();
        preferences.putFloat(id + "EnePosX", pos.x);
        preferences.putFloat(id + "EnePosY", pos.y);
        preferences.putInteger(id + "EneIndex", index);
        preferences.putInteger(id + "EneCounter", eneCounter);
        preferences.putFloat(id + "EneDes", des);
        preferences.putFloat(id + "EneHealth", health);
        preferences.putBoolean(id + "EneCalled", called);
        for (int i = 0; i < parTups.size; i++) {
            preferences.putFloat(id + "EneBulletX" + i, parTups.get(i).x);
            preferences.putFloat(id + "EneBulletY" + i, parTups.get(i).y);
        }
        preferences.putInteger(id + "EneBulletSize", parTups.size);
        preferences.flush();
    }

    public void load(int id) {
        Preferences preferences = sprites.getPreferences();
        pos = new Tuple(preferences.getFloat(id + "EnePosX"),
                preferences.getFloat(id + "EnePosY"));
        index = preferences.getInteger(id + "EneIndex");
        eneCounter = preferences.getInteger(id + "EneCounter");
        des = preferences.getFloat(id + "EneDes");
        health = preferences.getFloat(id + "EneHealth");
        called = preferences.getBoolean(id + "EneCalled");
        parTups = new Array<Tuple>();
        int size = preferences.getInteger(id + "EneBulletSize");
        for (int i = 0; i < size; i++) {
            parTups.insert(i, new Tuple(preferences.getFloat(id + "EneBulletX" + i),
                    preferences.getFloat(id + "EneBulletY" + i)));
            particles.insert(i, sprites.getBulletFrame(bullet));
        }
    }
}
