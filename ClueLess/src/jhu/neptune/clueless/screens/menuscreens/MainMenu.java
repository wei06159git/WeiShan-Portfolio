package jhu.neptune.clueless.screens.menuscreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jhu.neptune.clueless.screens.api.AbstractScreen;
import jhu.neptune.clueless.screens.api.ClueLessImageButtons;
import jhu.neptune.clueless.screens.api.ClueLessMenuScreens;

public final class MainMenu extends AbstractScreen {

    private final Skin skin;
    private Table menuTable;
    private Table optionsTable;
    private Stage mainMenuStage;

    public MainMenu() {
        //camera = new OrthographicCamera();
        skin = new Skin(Gdx.files.internal("skins/terra/terramotherui/terra-mother-ui.json"));

        // Set the stage and allow input / output
        mainMenuStage = new Stage( new ScreenViewport());
        Gdx.input.setInputProcessor(mainMenuStage);

        //menuTable acts as root table
        menuTable = new Table();
        menuTable.setFillParent(true);
        menuTable.setBackground((skin.getTiledDrawable("tile-a")));
        //menuTable.setDebug(true); //Enables debug lines for tables.
        mainMenuStage.addActor(menuTable);

        // Add widgets to the table here.
        optionsTable = new Table(skin);
        optionsTable.setBackground("window-c");

        //Create Menu options
        optionsTable.add(ClueLessImageButtons.getInstance().getMenuButton("Host a Game", ClueLessMenuScreens.HOST_GAME)).align(Align.left);
        optionsTable.row();

        optionsTable.add(ClueLessImageButtons.getInstance().getMenuButton("Join a Game", ClueLessMenuScreens.CONNECT_TO_HOST)).align(Align.left);
        optionsTable.row();

        optionsTable.add(ClueLessImageButtons.getInstance().getMenuButton("Settings", ClueLessMenuScreens.SETTINGS_SCREEN)).align(Align.left);
        optionsTable.row();

        optionsTable.add(ClueLessImageButtons.getInstance().getMenuButton("Help", ClueLessMenuScreens.HELP_SCREEN)).align(Align.left);
        optionsTable.row();

        optionsTable.add(ClueLessImageButtons.getInstance().getMenuButton("Credits", ClueLessMenuScreens.CREDITS_SCREEN)).align(Align.left);
        optionsTable.row();

        optionsTable.add(ClueLessImageButtons.getInstance().getMenuButton("Exit Game", ClueLessMenuScreens.EXIT_GAME)).align(Align.left);
        optionsTable.row();

        //Build MainMenu table by adding options
        menuTable.add(optionsTable).height(500.0f).width(500.0f);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }


    @Override
    public void buildStage() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainMenuStage.act(delta);
        mainMenuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mainMenuStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


    @Override
    public void dispose() {
        mainMenuStage.dispose();
        skin.dispose();
    }
}

