package jhu.neptune.clueless.screens.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ClueLessImageButtons {
    private static ClueLessImageButtons instance;
    private final Skin skin;
    private Music hoverSound;

    private ClueLessImageButtons() {
        super();
        skin = new Skin(Gdx.files.internal("skins/terra/terramotherui/terra-mother-ui.json"));
        hoverSound = Gdx.audio.newMusic(Gdx.files.internal("audio/fx/XF_HatMisc19.wav"));
    }

    public static ClueLessImageButtons getInstance() {
        if (instance == null) {
            instance = new ClueLessImageButtons();
        }
        return instance;
    }

    public ImageTextButton getMenuButton(String label, ClueLessMenuScreens screen, Object... obj) {
        ImageTextButton button = new ImageTextButton(label, skin);
        button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (screen != null) {
                    ScreenManager.getInstance().showScreen(screen, obj);
                }
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.setVolume(0.50f);
                hoverSound.play();
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hoverSound.stop();
            }
        });
        return button;
    }
}
