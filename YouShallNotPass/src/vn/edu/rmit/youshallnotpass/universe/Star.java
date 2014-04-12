package vn.edu.rmit.youshallnotpass.universe;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;

public class Star {
	private SpriteManager sprites;
	
	private TextureRegion starFirst1;
	private TextureRegion starFirst2;
	private TextureRegion starSecond1;
	private TextureRegion starSecond2;
	private TextureRegion starThird1;
	private TextureRegion starThird2;
	
	private float posYFirst1 = 0;
	private float posYFirst2 = 0;
	private float posYSecond1 = 50;
	private float posYSecond2 = 50;
	private float posYThird1 = 100;
	private float posYThird2 = 100;
	
	private int height;
	
	private int counter = 0;
	
	private static final int STAR_FIRST = 5;
	private static final int STAR_SECOND = 2;
	private static final int STAR_THIRD = 1;
	
	public Star() {
		sprites = SpriteManager.getInstance();
		
		starFirst1 = sprites.getStarFrame(SpriteManager.STAR1);
		starFirst2 = sprites.getStarFrame(SpriteManager.STAR1);
		starSecond1 = sprites.getStarFrame(SpriteManager.STAR2);
		starSecond2 = sprites.getStarFrame(SpriteManager.STAR2);
		starThird1 = sprites.getStarFrame(SpriteManager.STAR3);
		starThird2 = sprites.getStarFrame(SpriteManager.STAR3);
		
		height = sprites.getStarHeight();
		
		posYFirst2 += height;
		posYSecond2 += height;
		posYThird2 += height;
	}
	
	public void render(SpriteBatch batch) {
		counter++;
		if (counter % STAR_FIRST == 0) {
			posYFirst1--;
			posYFirst2--;
			if (posYFirst1 + height < 0) {
				posYFirst1 = posYFirst2 + height;
			} else if (posYFirst2 + height < 0) {
				posYFirst2 = posYFirst1 + height;
			}
		}
		if (counter % STAR_SECOND == 0) {
			posYSecond1--;
			posYSecond2--;
			if (posYSecond1 + height < 0) {
				posYSecond1 = posYSecond2 + height;
			} else if (posYSecond2 + height < 0) {
				posYSecond2 = posYSecond1 + height;
			}
		}
		if (counter % STAR_THIRD == 0) {
			posYThird1--;
			posYThird2--;
			if (posYThird1 + height < 0) {
				posYThird1 = posYThird2 + height;
			}else if (posYThird2 + height < 0) {
				posYThird2 = posYThird1 + height;
			}
		}
		batch.draw(starFirst1, 0, posYFirst1);
		batch.draw(starFirst2, 0, posYFirst2);
		batch.draw(starSecond1, 0, posYSecond1);
		batch.draw(starSecond2, 0, posYSecond2);
		batch.draw(starThird1, 0, posYThird1);
		batch.draw(starThird2, 0, posYThird2);
	}
}
