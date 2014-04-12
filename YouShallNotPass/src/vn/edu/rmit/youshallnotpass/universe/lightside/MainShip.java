package vn.edu.rmit.youshallnotpass.universe.lightside;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;
import vn.edu.rmit.youshallnotpass.mechanism.Tuple;
import vn.edu.rmit.youshallnotpass.universe.GalaxyManager;
import vn.edu.rmit.youshallnotpass.universe.UFO;

public abstract class MainShip implements UFO {
	protected SpriteManager sprites;

	private TextureRegion idleFrame;
	private TextureRegion hitFrame;
	private TextureRegion leftFrame;
	private TextureRegion leftHitFrame;
	private TextureRegion rightFrame;
	private TextureRegion rightHitFrame;
    private Animation explodeAnimation;
    private TextureRegion energyFrame;

	private Tuple offPosHit;
	private Tuple offPosLeft;
	private Tuple offPosLeftHit;
	private Tuple offPosRight;
	private Tuple offPosRightHit;
    private Tuple offPosExplosion;
    private Tuple offPosEnergy;
    protected Tuple pos;                    // Need save
    protected Tuple offPos;

	protected Array<TextureRegion> particles;   // Need rebuilding
    protected Array<Tuple> parTups;         // Need save
    protected Tuple offPar;
    protected float bulletPower = 0;

	protected float speed = 0;
    protected float highSpeed = 0;
	protected float health = 10;            // Need save
    protected float maxHealth;
    protected float healingRate = 0.01f;

    private float detonateTime = 0;
    private boolean soundPlayed = false;
    protected int indexBullet = 0;
    protected int bulletSpeed;

	public MainShip(int model, int bullet) {
		construct(model, bullet, SpriteManager.EXPLO5);
	}

    private void construct(int model, int bullet, int explode) {
        sprites = SpriteManager.getInstance();

        idleFrame = sprites.getMainShipFrame(model, SpriteManager.IDLE);
        hitFrame = sprites.getMainShipFrame(model, SpriteManager.IDLE_HIT);
        leftFrame = sprites.getMainShipFrame(model, SpriteManager.LEFT);
        leftHitFrame = sprites.getMainShipFrame(model, SpriteManager.LEFT_HIT);
        rightFrame = sprites.getMainShipFrame(model, SpriteManager.RIGHT);
        rightHitFrame = sprites.getMainShipFrame(model, SpriteManager.RIGHT_HIT);
        explodeAnimation = sprites.getExplosionAnimation(explode);
        energyFrame = sprites.getMiscellaneousFrame(SpriteManager.ENERGY);

        particles = new Array<TextureRegion>();
        parTups = new Array<Tuple>();

        offPos = new Tuple(sprites.getOffSetWidth(model, SpriteManager.IDLE),
                sprites.getOffSetHeight(model, SpriteManager.IDLE));
        offPosHit = new Tuple(sprites.getOffSetWidth(model, SpriteManager.IDLE_HIT),
                sprites.getOffSetHeight(model, SpriteManager.IDLE_HIT));
        offPosLeft = new Tuple(sprites.getOffSetWidth(model, SpriteManager.LEFT),
                sprites.getOffSetHeight(model, SpriteManager.LEFT));
        offPosLeftHit = new Tuple(sprites.getOffSetWidth(model, SpriteManager.LEFT_HIT),
                sprites.getOffSetHeight(model, SpriteManager.LEFT_HIT));
        offPosRight = new Tuple(sprites.getOffSetWidth(model, SpriteManager.RIGHT),
                sprites.getOffSetHeight(model, SpriteManager.RIGHT));
        offPosRightHit = new Tuple(sprites.getOffSetWidth(model, SpriteManager.RIGHT_HIT),
                sprites.getOffSetHeight(model, SpriteManager.RIGHT_HIT));
        offPar = new Tuple(sprites.getOffSetWidth(bullet, SpriteManager.NORMAL),
                sprites.getOffSetHeight(bullet, SpriteManager.NORMAL));
        offPosExplosion = new Tuple(sprites.getOffSetWidth(explode, SpriteManager.NORMAL),
                sprites.getOffSetHeight(explode, SpriteManager.NORMAL));
        offPosEnergy = new Tuple(sprites.getOffSetWidth(SpriteManager.ENERGY, SpriteManager.NORMAL),
                sprites.getOffSetHeight(SpriteManager.ENERGY, SpriteManager.NORMAL));
        pos = new Tuple(SpriteManager.SCREEN_W / 2, offPos.y);
    }

	@Override
	public void render(SpriteBatch batch, boolean isHit, float damage) {
        // Checking health
        if (checkHealth(batch)) {
            return;
        }

        // Shooting
		shooting(batch);

        // Moving
        moving(batch, isHit);

        // Hitting
        checkHit(isHit, damage);

        // Status
        energyStatus(batch);
        miscellaneousStatus(batch);
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
                    indexBullet--;
                }
            }

            // Playing explode animation
            if (isDestroyed()) {
                return true;
            }
            if (!soundPlayed) {
                GalaxyManager.playSound(sprites.getShipExplosionSound());
                soundPlayed = true;
            }
            detonateTime += Gdx.graphics.getDeltaTime();
            batch.draw(explodeAnimation.getKeyFrame(detonateTime), pos.x - offPosExplosion.x, pos.y - offPosExplosion.y);
            return true;
        }
        return false;
    }

    protected void miscellaneousStatus(SpriteBatch batch) {}

    protected void energyStatus(SpriteBatch batch) {
        float posX = 0;
        float posY = (SpriteManager.SCREEN_H - (offPosEnergy.y + 10) * maxHealth) / 2;
        for(int i = 0; i < maxHealth; i++) {
            batch.draw(energyFrame, posX, posY);
            if (i < health) {
                batch.draw(energyFrame, posX, posY);
                batch.draw(energyFrame, posX, posY);
            }
            posY += offPosEnergy.y + 10;
        }
    }

    protected abstract void shooting(SpriteBatch batch);

    protected void moving(SpriteBatch batch, boolean isHit) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && pos.x != 0) {
            if (isHit) {
                batch.draw(leftHitFrame, pos.x - offPosLeftHit.x, pos.y - offPosLeftHit.y);
            } else {
                batch.draw(leftFrame, pos.x - offPosLeft.x, pos.y - offPosLeft.y);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                if (pos.x - offPos.x >= 0) {
                    pos.x -= highSpeed;
                }
            } else {
                if (pos.x - offPos.x >= 0) {
                    pos.x -= speed;
                }
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && pos.x != SpriteManager.SCREEN_W) {
            if (isHit) {
                batch.draw(rightHitFrame, pos.x - offPosRightHit.x, pos.y - offPosRightHit.y);
            } else {
                batch.draw(rightFrame, pos.x - offPosRight.x, pos.y - offPosRight.y);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                if (pos.x + offPos.x <= SpriteManager.SCREEN_W) {
                    pos.x += highSpeed;
                }
            } else {
                if (pos.x + offPos.x <= SpriteManager.SCREEN_W) {
                    pos.x += speed;
                }
            }
        } else {
            if (isHit) {
                batch.draw(hitFrame, pos.x - offPosHit.x, pos.y - offPosHit.y);
            } else {
                batch.draw(idleFrame, pos.x - offPos.x, pos.y - offPos.y);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && pos.y != SpriteManager.SCREEN_H) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                if (pos.y + offPos.y + SpriteManager.SCREEN_H / 4 <= SpriteManager.SCREEN_H) {
                    pos.y += highSpeed;
                }
            } else {
                if (pos.y + offPos.y + SpriteManager.SCREEN_H / 4  <= SpriteManager.SCREEN_H) {
                    pos.y += speed;
                }
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && pos.y != 0) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                if (pos.y - offPos.y >= 0) {
                    pos.y -= highSpeed;
                }
            } else {
                if (pos.y - offPos.y >= 0) {
                    pos.y -= speed;
                }
            }
        }
    }

    protected void checkHit(boolean isHit, float damage) {
        if (isHit) {
            health -= damage;
        }
        if (health < maxHealth) {
            health += healingRate;
        }
    }

    public boolean isDestroyed() {
        return explodeAnimation.isAnimationFinished(detonateTime);
    }

    public boolean isToBeRemoved() {
        return isDestroyed() && particles.size == 0;
    }

	public Tuple getPos() {
		return pos;
	}

	public Tuple getOffPos() {
		return offPos;
	}

	public Tuple getOffPosLeft() {
		return offPosLeft;
	}

	public Tuple getOffPosRight() {
		return offPosRight;
	}

	public Array<Tuple> getParTups() {
		return parTups;
	}

	public Tuple getOffPar() {
		return offPar;
	}

    @Override
    public float getBulletPower() {
        return bulletPower;
    }

	@Override
	public abstract void removeBullet(int index);

    public void save() {
        Preferences preferences = sprites.getPreferences();
        preferences.putFloat("ShipX", pos.x);
        preferences.putFloat("ShipY", pos.y);
        preferences.putFloat("ShipHealth", health);
        for (int i = 0; i < parTups.size; i++) {
            preferences.putFloat("ShipBulletX" + i, parTups.get(i).x);
            preferences.putFloat("ShipBulletY" + i, parTups.get(i).y);
        }
        preferences.putInteger("ShipBulletSize", parTups.size);
        preferences.flush();
    }

    public void load() {
        Preferences preferences = sprites.getPreferences();
        pos = new Tuple(preferences.getFloat("ShipX"), preferences.getFloat("ShipY"));
        health = preferences.getFloat("ShipHealth");
        parTups = new Array<Tuple>();
        int size = preferences.getInteger("ShipBulletSize");
        for (int i = 0; i < size; i++) {
            parTups.insert(i, new Tuple(preferences.getFloat("ShipBulletX" + i),
                    preferences.getFloat("ShipBulletY" + i)));
        }
    }
}
