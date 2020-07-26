package jhu.neptune.clueless.screens.menuscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import jhu.neptune.clueless.screens.api.AbstractScreen;
import jhu.neptune.clueless.screens.api.ClueLessMenuScreens;
import jhu.neptune.clueless.screens.api.ScreenManager;

import static jhu.neptune.clueless.assets.ClueLessAssets.Icons.TEAM_NEPTUNE_LOGO;

public final class SplashScreen extends AbstractScreen {

    private SpriteBatch batch;
    private Texture img;
    private float waitTime = 4f;
    private BitmapFont font;
    private CharSequence credits = "ClueLess - Presented by Team Neptune";
    private Skin skin;

    public SplashScreen() {
        super();
    }

    @Override
    public void buildStage() {
        skin = new Skin(Gdx.files.internal("skins/terra/terramotherui/terra-mother-ui.json"));
        batch = new SpriteBatch();
        img = new Texture(TEAM_NEPTUNE_LOGO);
        font = skin.getFont("year199x");
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        //Center Team Neptune Logo
        float width  = (float) Gdx.graphics.getWidth()/ 2 - (float)img.getWidth() / 2;
        float height = (float) Gdx.graphics.getHeight()/2 - (float)img.getHeight() / 2;

        batch.draw(img, width, height);
        font.draw(batch, credits, width+30, height - 2);

        batch.end();

        waitTime -= delta; // remove delta from waitTime
        if(waitTime <= 0){ // 4 seconds are up
            //System.out.println("Time is up. Changing screens!");
            // tell parent to change screen
            ScreenManager.getInstance().showScreen(ClueLessMenuScreens.MAIN_MENU);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        skin.dispose();
        font.dispose();
    }

    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
}
