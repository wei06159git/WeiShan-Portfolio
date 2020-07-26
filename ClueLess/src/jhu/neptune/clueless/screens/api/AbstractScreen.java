package jhu.neptune.clueless.screens.api;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class AbstractScreen implements Screen {

    protected AbstractScreen() {
        super( );
    }
    public abstract void buildStage();

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void show() {

    }
    @Override
    public void resize(int width, int height){
    }

    @Override public void hide() {}
    @Override public void pause() {}
    @Override public void resume() {}
}