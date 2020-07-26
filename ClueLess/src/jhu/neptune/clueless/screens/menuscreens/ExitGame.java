package jhu.neptune.clueless.screens.menuscreens;

import com.badlogic.gdx.Gdx;
import jhu.neptune.clueless.screens.api.AbstractScreen;

public class ExitGame extends AbstractScreen {
    @Override
    public void buildStage() {
        Gdx.app.exit();
        System.exit(0);
    }

    @Override
    public void dispose() {
    }
}
