package vn.edu.rmit.youshallnotpass.universe;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface UFO {
	void render(SpriteBatch batch, boolean isHit, float damage);
	void removeBullet(int index);
    float getBulletPower();
}
