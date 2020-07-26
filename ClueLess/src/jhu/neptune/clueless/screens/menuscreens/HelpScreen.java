package jhu.neptune.clueless.screens.menuscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jhu.neptune.clueless.ClueLessGame;
import jhu.neptune.clueless.screens.api.AbstractScreen;
import jhu.neptune.clueless.screens.api.ClueLessImageButtons;
import jhu.neptune.clueless.screens.api.ClueLessMenuScreens;

public final class HelpScreen extends AbstractScreen {
    private final Table table;
    private final Music hoverSound;
    private Stage stage;
    private Skin skin;
    private Table creditsTable;

    public HelpScreen() {
        skin = new Skin(Gdx.files.internal("skins/terra/terramotherui/terra-mother-ui.json"));
        hoverSound = Gdx.audio.newMusic(Gdx.files.internal("audio/fx/XF_HatMisc19.wav"));

        // Set the stage and allow input / output
        stage = new Stage( new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //table acts as root table for this screen
        table = new Table();
        table.setFillParent(true);
        table.setBackground((skin.getTiledDrawable("tile-a")).tint(Color.VIOLET));
        //table.setDebug(true); //Enables debug lines for tables.

        stage.addActor(table);

        // Add widgets to the table here.
        // return to menu
        ImageTextButton main_menu = ClueLessImageButtons.getInstance().getMenuButton("Return to Main Menu", ClueLessMenuScreens.MAIN_MENU);

        creditsTable = new Table(skin);
        creditsTable.setBackground("window-c");

        creditsTable.add(main_menu);
        creditsTable.row();

        generateLabel("\nTODO\n");

        table.add(creditsTable).height(500.0f).width(500.0f);
    }

    private void generateLabel(String s) {

        //Credits
        Label label = new Label(s, skin);
        creditsTable.add(label);
        creditsTable.row();
    }
    @Override
    public void show() {

    }

    @Override
    public void buildStage() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }
}
