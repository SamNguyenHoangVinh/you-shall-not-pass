package vn.edu.rmit.youshallnotpass;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuScreen implements Screen {
	Skin skin;
	Stage stage;
	SpriteBatch batch;

	Game g;
	public MenuScreen(Game g){
		create();
		this.g=g;
	}

	public MenuScreen(){
		create();
	}
	public void create(){
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		// A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
		// recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
		skin = new Skin();
		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(128, 128, Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fill();

		skin.add("white", new Texture(pixmap));

		// Store the default libgdx font under the name "default".
		BitmapFont bfont=new BitmapFont();
		bfont.scale(1);
		skin.add("default",bfont);

		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

		textButtonStyle.font = skin.getFont("default");

		skin.add("default", textButtonStyle);

		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
		final TextButton playBttn=new TextButton("PLAY",textButtonStyle);
		playBttn.setPosition(200, 200);
		stage.addActor(playBttn);
		stage.addActor(playBttn);
		stage.addActor(playBttn);

		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
		// revert the checked state.
		playBttn.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				//System.out.println("Clicked! Is checked: " + button.isChecked());
				playBttn.setText("Starting new game");
//				g.setScreen( new GameScreen());

			}
		});
//
//		final TextButton optBttn=new TextButton("Option",textButtonStyle);
//		optBttn.setPosition(200, 200);
//		stage.addActor(optBttn);
//		stage.addActor(optBttn);
//		stage.addActor(optBttn);
//
//		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
//		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
//		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
//		// revert the checked state.
//		optBttn.addListener(new ChangeListener() {
//			public void changed (ChangeEvent event, Actor actor) {
//				//System.out.println("Clicked! Is checked: " + button.isChecked());
//				optBttn.setText("Starting new game");
////				g.setScreen( new GameScreen());
//
//			}
//		});

	}

	public void render (float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize (int width, int height) {
		stage.setViewport(width, height, false);
	}

	@Override
	public void dispose () {
		stage.dispose();
		skin.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}
}