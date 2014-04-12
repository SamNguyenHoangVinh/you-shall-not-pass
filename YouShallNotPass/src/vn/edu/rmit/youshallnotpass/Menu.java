package vn.edu.rmit.youshallnotpass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import vn.edu.rmit.youshallnotpass.mechanism.SpriteManager;
import vn.edu.rmit.youshallnotpass.universe.GalaxyManager;
import vn.edu.rmit.youshallnotpass.universe.lightside.Destroyer;
import vn.edu.rmit.youshallnotpass.universe.lightside.Hunter;
import vn.edu.rmit.youshallnotpass.universe.lightside.Striker;

/**
 * Created by benjamin on 12/27/13.
 */
public class Menu {
    private SpriteManager sprites;
    private Initiator initiator;
    private GalaxyManager galaxyManager;
    private Stage stage;
    private Table table;

    private Image title;
    private ImageButton start;
    private ImageButton option;
    private ImageButton load;
    private ImageButton back;
    private Image difficulty;
    private ImageButton normal;
    private ImageButton insane;
    private ImageButton radioButton;
    private ImageButton radioButton2;
    private Image sound;
    private ImageButton music;
    private ImageButton effects;
    private ImageButton checkbox;
    private ImageButton checkbox2;
    private ImageButton resume;
    private ImageButton save;
    private ImageButton gameSaved;
    private Image background;
    private ImageButton striker;
    private ImageButton hunter;
    private ImageButton destroyer;
    private ImageButton strikerAvatar;
    private ImageButton hunterAvatar;
    private ImageButton destroyerAvatar;
    private Image score;
    private Image time;
    private Image victory;
    private Image defeat;
    private ImageButton quit;

    private Label label;

    public Menu(Initiator initiator, GalaxyManager galaxyManager) {
        this.initiator = initiator;
        this.galaxyManager = galaxyManager;
    }

    public void create() {
        sprites = SpriteManager.getInstance();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        title = new Image(sprites.getTitle(SpriteManager.TITLE));
        start = new ImageButton(sprites.getTitle(SpriteManager.START_GAME),
                sprites.getTitle(SpriteManager.START_GAME_HOVER),
                sprites.getTitle(SpriteManager.START_GAME));
        option = new ImageButton(sprites.getTitle(SpriteManager.OPTION),
                sprites.getTitle(SpriteManager.OPTION_HOVER),
                sprites.getTitle(SpriteManager.OPTION));
        load = new ImageButton(sprites.getTitle(SpriteManager.LOAD_GAME),
                sprites.getTitle(SpriteManager.LOAD_GAME_HOVER),
                sprites.getTitle(SpriteManager.LOAD_GAME));
        back = new ImageButton(sprites.getTitle(SpriteManager.BACK),
                sprites.getTitle(SpriteManager.BACK_HOVER),
                sprites.getTitle(SpriteManager.BACK));
        difficulty = new Image(sprites.getTitle(SpriteManager.DIFFICULTY));
        normal = new ImageButton(sprites.getTitle(SpriteManager.NORMAL_TITLE),
                sprites.getTitle(SpriteManager.NORMAL_TITLE),
                sprites.getTitle(SpriteManager.NORMAL_TITLE));
        insane = new ImageButton(sprites.getTitle(SpriteManager.INSANE),
                sprites.getTitle(SpriteManager.INSANE),
                sprites.getTitle(SpriteManager.INSANE));
        radioButton = new ImageButton(sprites.getTitle(SpriteManager.RADIO_BUTTON),
                sprites.getTitle(SpriteManager.RADIO_BUTTON_CHECKED),
                sprites.getTitle(SpriteManager.RADIO_BUTTON_CHECKED));
        radioButton2 = new ImageButton(sprites.getTitle(SpriteManager.RADIO_BUTTON),
                sprites.getTitle(SpriteManager.RADIO_BUTTON_CHECKED),
                sprites.getTitle(SpriteManager.RADIO_BUTTON_CHECKED));
        sound = new Image(sprites.getTitle(SpriteManager.SOUND));
        music = new ImageButton(sprites.getTitle(SpriteManager.MUSIC),
                sprites.getTitle(SpriteManager.MUSIC),
                sprites.getTitle(SpriteManager.MUSIC));
        effects = new ImageButton(sprites.getTitle(SpriteManager.EFFECTS),
                sprites.getTitle(SpriteManager.EFFECTS),
                sprites.getTitle(SpriteManager.EFFECTS));
        checkbox = new ImageButton(sprites.getTitle(SpriteManager.CHECKBOX),
                sprites.getTitle(SpriteManager.CHECKBOX_CHECKED),
                sprites.getTitle(SpriteManager.CHECKBOX_CHECKED));
        checkbox2 = new ImageButton(sprites.getTitle(SpriteManager.CHECKBOX),
                sprites.getTitle(SpriteManager.CHECKBOX_CHECKED),
                sprites.getTitle(SpriteManager.CHECKBOX_CHECKED));
        resume = new ImageButton(sprites.getTitle(SpriteManager.RESUME),
                sprites.getTitle(SpriteManager.RESUME_HOVER),
                sprites.getTitle(SpriteManager.RESUME));
        save = new ImageButton(sprites.getTitle(SpriteManager.SAVE),
                sprites.getTitle(SpriteManager.SAVE_HOVER),
                sprites.getTitle(SpriteManager.SAVE));
        gameSaved = new ImageButton(sprites.getTitle(SpriteManager.GAME_SAVED),
                sprites.getTitle(SpriteManager.GAME_SAVED_HOVER),
                sprites.getTitle(SpriteManager.GAME_SAVED));
        background = new Image(sprites.getTitle(SpriteManager.BACKGROUND));
        hunter = new ImageButton(sprites.getTitle(SpriteManager.HUNTER),
                sprites.getTitle(SpriteManager.HUNTER_HOVER),
                sprites.getTitle(SpriteManager.HUNTER));
        destroyer = new ImageButton(sprites.getTitle(SpriteManager.DESTROYER),
                sprites.getTitle(SpriteManager.DESTROYER_HOVER),
                sprites.getTitle(SpriteManager.DESTROYER));
        striker = new ImageButton(sprites.getTitle(SpriteManager.STRIKER),
                sprites.getTitle(SpriteManager.STRIKER_HOVER),
                sprites.getTitle(SpriteManager.STRIKER));
        hunterAvatar = new ImageButton(sprites.getTitle(SpriteManager.HUNTER_AVATAR),
                sprites.getTitle(SpriteManager.HUNTER_AVATAR_HOVER),
                sprites.getTitle(SpriteManager.HUNTER_AVATAR));
        destroyerAvatar = new ImageButton(sprites.getTitle(SpriteManager.DESTROYER_AVATAR),
                sprites.getTitle(SpriteManager.DESTROYER_AVATAR_HOVER),
                sprites.getTitle(SpriteManager.DESTROYER_AVATAR));
        strikerAvatar = new ImageButton(sprites.getTitle(SpriteManager.STRIKER_AVATAR),
                sprites.getTitle(SpriteManager.STRIKER_AVATAR_HOVER),
                sprites.getTitle(SpriteManager.STRIKER_AVATAR));
        score = new Image(sprites.getTitle(SpriteManager.SCORE));
        time = new Image(sprites.getTitle(SpriteManager.TIME));
        victory = new Image(sprites.getTitle(SpriteManager.VICTORY));
        defeat = new Image(sprites.getTitle(SpriteManager.DEFEAT));
        quit = new ImageButton(sprites.getTitle(SpriteManager.QUIT),
                sprites.getTitle(SpriteManager.QUIT),
                sprites.getTitle(SpriteManager.QUIT));

        label = new Label("", new Label.LabelStyle(sprites.getFontScore(), Color.WHITE));

        table = new Table();
        table.setFillParent(true);

        stage.addActor(background);
        stage.addActor(table);

        // Add widgets to the table here

        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                start();
            }
        });
        option.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                option();
            }
        });
        load.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                load();
            }
        });
        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!initiator.isPaused()) {
                    mainMenu();
                } else {
                    pause();
                }
            }
        });
        normal.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!radioButton.isChecked()) {
                    radioButton.setChecked(true);
                    radioButton2.setChecked(false);
                    initiator.setLevel(SpriteManager.NORMAL_LEVEL);
                }
            }
        });
        insane.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!radioButton2.isChecked()) {
                    radioButton2.setChecked(true);
                    radioButton.setChecked(false);
                    initiator.setLevel(SpriteManager.INSANE_LEVEL);
                }
            }
        });
        radioButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                radioButton.toggle();
            }
        });
        radioButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                radioButton2.toggle();
            }
        });
        music.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                checkbox.toggle();
            }
        });
        effects.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                checkbox2.toggle();
            }
        });
        checkbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!checkbox.isChecked()) {
                    initiator.setMusic(false);
                } else {
                    initiator.setMusic(true);
                }
            }
        });
        checkbox2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (!checkbox2.isChecked()) {
                    initiator.setSoundEffects(false);
                } else {
                    initiator.setSoundEffects(true);
                }
            }
        });
        resume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                initiator.setPaused(false);
            }
        });
        save.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                galaxyManager.save();
            }
        });
        gameSaved.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (sprites.getPreferences().getBoolean("isSaveExisted")) {
                    galaxyManager.load();
                    initiator.setStarted(true);
                }
            }
        });
        striker.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                initiator.setShip(new Striker());
                initiator.setStarted(true);
            }
        });
        hunter.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                initiator.setShip(new Hunter());
                initiator.setStarted(true);
            }
        });
        destroyer.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                initiator.setShip(new Destroyer());
                initiator.setStarted(true);
            }
        });
        strikerAvatar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                initiator.setShip(new Striker());
                initiator.setStarted(true);
            }
        });
        hunterAvatar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                initiator.setShip(new Hunter());
                initiator.setStarted(true);
            }
        });
        destroyerAvatar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                initiator.setShip(new Destroyer());
                initiator.setStarted(true);
            }
        });
        quit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                initiator.restart();
                loadSettings();
                mainMenu();
            }
        });

//        table.debug();
        mainMenu();
        loadSettings();
    }

    public void setGalaxyManager(GalaxyManager galaxyManager) {
        this.galaxyManager = galaxyManager;
    }

    public void loadSettings() {
        int level = initiator.getLevel();
        if (level == SpriteManager.NORMAL_LEVEL) {
            radioButton.setChecked(true);
            radioButton2.setChecked(false);
        } else {
            radioButton2.setChecked(true);
            radioButton.setChecked(false);
        }
        checkbox.setChecked(initiator.isMusicOn());
        checkbox2.setChecked(initiator.isSoundEffectsOn());
    }

    public void mainMenu() {
        table.clear();
        table.add(title).padBottom(100);
        table.row().height(70);
        table.add(start);
        table.row().height(70);
        table.add(option);
        if (sprites.getPreferences().getBoolean("isSaveExisted")) {
            table.row().height(70);
            table.add(load);
        }
        table.pack();
    }

    public void start() {
        table.clear();
        table.add(title).padBottom(100).colspan(3);
        table.row().width(200).height(250);
        table.add(strikerAvatar);
        table.add(hunterAvatar);
        table.add(destroyerAvatar);
        table.row().height(70);
        table.add(striker);
        table.add(hunter);
        table.add(destroyer);
        table.row().height(70);
        table.add(back).colspan(3);
        table.pack();
    }

    public void option() {
        table.clear();
        table.add(title).padBottom(100).colspan(2);
        table.row();
        if (!initiator.isPaused()) {
            table.add(difficulty).padTop(25).padBottom(10).colspan(2);
            table.row();
            table.add(radioButton).width(50).right();
            table.add(normal).height(40).left();
            table.row();
            table.add(radioButton2).width(50).right();
            table.add(insane).height(40).left();
            table.row();
        }
        table.add(sound).padTop(25).padBottom(10).colspan(2);
        table.row();
        table.add(checkbox).width(50).right();
        table.add(music).height(40).left();
        table.row();
        table.add(checkbox2).width(50).right();
        table.add(effects).height(40).left();
        table.row();
        table.add(back).height(100).colspan(2);
        table.pack();
    }

    public void pause() {
        table.clear();
        table.add(title).padBottom(100);
        table.row();
        table.add(resume).height(70);
        table.row();
        table.add(option).height(70);
        table.row();
        table.add(save).height(70);
        table.row();
        table.add(quit).height(70);
        table.pack();
    }

    public void load() {
        table.clear();
        table.add(title).padBottom(100);
        table.row();
        table.add(gameSaved).height(70);
        table.row();
        table.add(back).height(70);
        table.pack();
    }

    public void displayScore(boolean isWon, int score) {
        if (isWon) {
            table.clear();
            table.add(victory).padBottom(100).colspan(2);
            table.row();
            table.add(this.score).top();
            label.setText(String.valueOf(score));
            table.add(label).padBottom(30);
            table.row();
            table.add(back).height(70).colspan(2);
            table.pack();
        } else {
            table.clear();
            table.add(defeat).padBottom(100).colspan(2);
            table.row();
            table.add(this.score).top();
            label.setText(String.valueOf(score));
            table.add(label).padBottom(30);
            table.row();
            table.add(back).height(70).colspan(2);
            table.pack();
        }
    }

    public void resize(int width, int height) {
        stage.setViewport(width, height, true);
    }

    public void render() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        Table.drawDebug(stage);
    }

    public void dispose() {
        stage.dispose();
    }
}
