package jhu.neptune.clueless.screens.menuscreens.connectionScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import jhu.neptune.clueless.screens.api.AbstractScreen;
import jhu.neptune.clueless.screens.api.ClueLessCommon;
import jhu.neptune.clueless.screens.api.ClueLessImageButtons;
import jhu.neptune.clueless.screens.api.ClueLessMenuScreens;

public class ConnectToHostScreen extends AbstractScreen {
    private Skin skin;
    private Stage stage;
    private TextField textField;


    @Override
    public void buildStage() {
        skin = new Skin(Gdx.files.internal("skins/terra/terramotherui/terra-mother-ui.json"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Table root = new Table();
        root.setFillParent(true);
        root.setBackground(skin.getTiledDrawable("tile-a").tint(Color.GOLD));
        stage.addActor(root);

        Table content = new Table(skin);
        content.setBackground("window-c");
        root.add(content);

        ////////////////////////////////////////////////////////////////////////////////
        Label label = new Label("Enter your name: ", skin);
        content.add(label).left();

        textField = new TextField("", skin);
        textField.setMaxLength(20);
        textField.setTextFieldListener((textField, c) -> ClueLessCommon.player_name = textField.getText());

        content.add(textField).left().setActorX(textField.getX());
        stage.setKeyboardFocus(textField);
        textField.setCursorPosition(textField.getText().length());

        content.row();

        ////////////////////////////////////////////////////////////////////////////////
        label = new Label("Enter Host address: ", skin);
        content.add(label).left();

        textField = new TextField("", skin);
        textField.setMaxLength(20);
        textField.setTextFieldListener((textField, c) -> ClueLessCommon.hostname = textField.getText());

        content.add(textField).left().setActorX(textField.getX());
        stage.setKeyboardFocus(textField);
        textField.setCursorPosition(textField.getText().length());

        content.row();

        content.add(ClueLessImageButtons.getInstance().getMenuButton("Return to Main Menu", ClueLessMenuScreens.MAIN_MENU)).left();
        content.add(ClueLessImageButtons.getInstance().getMenuButton("Join Game", ClueLessMenuScreens.MAIN_GAME_SCREEN).center().left());


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        skin.dispose();
        stage.dispose();
    }
}
